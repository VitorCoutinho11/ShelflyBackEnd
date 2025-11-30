############################
# 1) Build do BACKEND Java #
############################
FROM maven:3.9.9-amazoncorretto-21-alpine AS backend-build

WORKDIR /backend

# Copia apenas o necessário para cachear melhor
COPY backend/pom.xml .
RUN mvn dependency:go-offline

COPY backend/src ./src
RUN mvn clean package -DskipTests


#########################################
# 2) Build do APK React Native (Android)#
#########################################
FROM openjdk:17-jdk AS mobile-build

# Instala Node, npm, e ferramentas necessárias
RUN apt-get update && \
    apt-get install -y curl git unzip && \
    curl -fsSL https://deb.nodesource.com/setup_20.x | bash - && \
    apt-get install -y nodejs && \
    npm install -g yarn && \
    rm -rf /var/lib/apt/lists/*

# Instala Android SDK
ENV ANDROID_HOME=/opt/android-sdk
ENV PATH=$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools:$PATH

RUN mkdir -p $ANDROID_HOME/cmdline-tools && \
    curl -Lo sdk.zip https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip && \
    unzip sdk.zip -d $ANDROID_HOME/cmdline-tools && \
    rm sdk.zip && \
    mv $ANDROID_HOME/cmdline-tools/cmdline-tools $ANDROID_HOME/cmdline-tools/latest && \
    yes | sdkmanager --sdk_root=${ANDROID_HOME} "platform-tools" "platforms;android-34" "build-tools;34.0.0"

WORKDIR /mobile

# Dependências do RN
COPY mobile/package.json mobile/yarn.lock ./
RUN yarn install

# Copia o restante do projeto mobile
COPY mobile/ .

# Dá permissão e gera o APK release
WORKDIR /mobile/android
# Corrigindo o comando do Gradle para usar a arquitetura específica (mais rápido)
RUN chmod +x ./gradlew && \
    ./gradlew assembleRelease -PreactNativeArchitectures=arm64-v8a


#########################################
# 3) Imagem final de runtime do backend #
#########################################
FROM amazoncorretto:21-alpine

WORKDIR /app

# 1. Copia o JAR do backend
COPY --from=backend-build /backend/target/*.jar app.jar

# 2. COPIA O APK para o diretório de recursos estáticos do Spring Boot
# Assim, ele será acessível em http://servidor:8411/download/shelflybackend.apk
COPY --from=mobile-build /mobile/android/app/build/outputs/apk/release/app-release.apk BOOT-INF/classes/static/download/shelflybackend.apk

# 3. Garante que o index.html (que já está no JAR) e o APK sejam servidos
EXPOSE 8411

CMD ["java", "-jar", "/app/app.jar"]
FROM maven:3.9.9-amazoncorretto-21-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine

COPY shelflybackend/apk-dist/shelflybackend.apk BOOT-INF/classes/static/download/shelflybackend.apk

COPY --from=build target/*.jar app.jar
EXPOSE 8411
CMD ["java", "-jar", "/app.jar"]
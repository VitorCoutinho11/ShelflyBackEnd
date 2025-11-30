pipeline {
    agent any

    environment {
        APP_NAME = 'shelflybackend'
        // PATH do Maven é importante para a build na máquina do Jenkins
        MAVEN_HOME = '/usr/local/maven' // AJUSTE SE NECESSÁRIO
    }

    stages {
        stage('Verificar Repositório') {
            steps {
                // Seu repositório principal
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], useRemoteConfigs: [[url: 'https://github.com/VitorCoutinho11/shelflybackend']]])
            }
        }

        stage('Instalar Dependências Backend') {
            steps {
                script {
                   // Usamos bat (Windows) ou sh (Linux/macOS) - Mude se o agente for Linux
                   bat 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Construir Imagem Docker') {
            steps {
                script {
                    def imageTag = "${env.APP_NAME}:${env.BUILD_ID}"

                    // O build deve ser executado da raiz para que o Docker acesse as subpastas 'backend' e 'mobile'
                    // O Dockerfile faz todo o trabalho de build do JAR e do APK
                    bat "docker build -t ${imageTag} ."
                }
            }
        }

        stage('Fazer Deploy') {
            steps {
                script {
                    def appName = "${env.APP_NAME}"

                    // Parar e remover o container existente
                    bat "docker stop ${appName} || echo 0"
                    bat "docker rm -v ${appName} || echo 0"

                    // Rodar o novo container (você pode usar docker-compose se tiver um)
                    bat "docker run -d --name ${appName} -p 8411:8411 ${appName}:${env.BUILD_ID}"
                }
            }
        }
    }

    post {
        success {
            echo 'Deploy e Build do APK concluídos com sucesso!'
            echo 'Acesse a página de download do APK em: http://seu-servidor:8411/'
        }
        failure {
            echo 'Houve um erro durante o deploy ou a compilação. Verifique o console.'
        }
    }
}
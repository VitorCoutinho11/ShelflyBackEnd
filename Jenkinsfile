pipeline {
    agent any

    environment {
        APP_NAME = 'shelflybackend'
        ANDROID_HOME = '/opt/android-sdk'
        GRADLE_USER_HOME = "${env.WORKSPACE}/.gradle"
    }

    tools {
        nodejs 'node-18'
    }

    stages {
        stage('Verificar Repositório') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], useRemoteConfigs: [[url: 'https://github.com/VitorCoutinho11/shelflybackend']]])
            }
        }

        stage('Instalar Dependências Backend') {
            steps {
                script {
                    dir('backend') {
                       bat 'mvn clean install -DskipTests'
                    }
                }
            }
        }

        stage('Construir Imagem Docker Backend') {
            steps {
                script {
                    def imageTag = "${env.APP_NAME}:${env.BUILD_ID}"
                    dir('backend') {
                        bat "docker build -t ${imageTag} ."
                    }
                }
            }
        }

        stage('Fazer Deploy Backend') {
            steps {
                script {
                    bat "docker-compose up -d --build"
                }
            }
        }

        stage('Compilar APK Release') {
            steps {
                script {
                    dir('mobile') {

                        bat 'npm install'

                        dir('android') {
                            bat 'gradlew assembleRelease -PreactNativeArchitectures=arm64-v8a'
                        }
                    }
                }
            }
        }

        stage('Arquivar APK') {
            steps {
                archiveArtifacts artifacts: 'mobile/android/app/build/outputs/apk/release/app-release.apk', onlyIfSuccessful: true
            }
        }
    }

    post {
        success {
            echo 'Deploy e Build do APK concluídos com sucesso!'
        }
        failure {
            echo 'Houve um erro durante o deploy ou a compilação do APK.'
        }
    }
}
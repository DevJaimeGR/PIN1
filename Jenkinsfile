def functionsLib = load 'functionslib.groovy'

pipeline {
    agent any
    
    environment {
        IMAGE_NAME = 'node-pin1'
        VERSION = ''
    }
    
    stages {
        stage('Extract Version from package.json') {
            steps {
                script {
                    VERSION = sh(script: "jq -r '.version' package.json", returnStdout: true).trim()
                    echo "La versión es: $VERSION"
                }
            }
        }
        
        stage('docker build') {
            steps {
                script {
                    docker.build("$IMAGE_NAME:$VERSION")
                }
            }
        }
        
        stage('Deploy to Docker') {
            environment {
                DOCKER_CRED = credentials('PersonalDockerHub')
                REGISTRY = 'devbackend1997'
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'PersonalDockerHub', usernameVariable: 'DOCKER_CRED_USR', passwordVariable: 'DOCKER_CRED_PSW')]) {
                    script {
                        functionsLib.pushDockerImage(DOCKER_CRED_USR,DOCKER_CRED_PSW,IMAGE_NAME,VERSION,REGISTRY)
                        }
                    }
                }
            }
    }
}


//this is a pipeline implementing github+docker+dockerhub
pipeline{
    agent any
    stages{
        stage('obtain repository of github'){
            steps{
                sh 'rm -rf PIN1'
                sh 'git clone https://github.com/DevJaimeGR/PIN1.git'
            }
        }
        stage('docker build'){
            steps{
                sh 'cd PIN1 && docker build -t node-pin1:v1 .'
            }
        }
        stage('Deploy to Docker'){
            environment{
                DOCKER_CRED = credentials('PersonalDockerHub')
                REGISTRY = 'devbackend1997'
            }
            steps{
                sh '''
                docker login --username=$DOCKER_CRED_USR --password=$DOCKER_CRED_PSW
                docker tag node-pin1:v1 $REGISTRY/node-pin1:v1
                docker push $REGISTRY/node-pin1:v1
                '''
            }
        }
    }
}
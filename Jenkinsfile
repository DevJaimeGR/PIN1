//this is a pipeline implementing github+docker+dockerhub
pipeline{
    agent any
    environment{
        DOCKER_CRED = credentials('PersonalDockerHub')
        REGISTRY = 'devbackend1997'
        IMAGE_NAME = 'node-pin1'
        VERSION = ''
        }
    stages{
        stage('Extract Version from package.json') {
            steps {
                script {
                    VERSION = sh(script: "jq -r '.version' package.json", returnStdout: true).trim()
                }
                sh 'echo la version es: $VERSION'
            }
        }
        stage('docker build'){
            steps{
                sh """
                docker build -t $IMAGE_NAME:$VERSION .
                """ 
            }
        }
        stage('Deploy to Docker'){

            steps{
                sh """
                docker login --username=$DOCKER_CRED_USR --password=$DOCKER_CRED_PSW
                docker tag $IMAGE_NAME:$VERSION $REGISTRY/$IMAGE_NAME:$VERSION
                docker push $REGISTRY/$IMAGE_NAME:$VERSION
                """
            }
        }
    }
}
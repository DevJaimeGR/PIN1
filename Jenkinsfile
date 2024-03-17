//this is a pipeline implementing github+docker+dockerhub
pipeline{
    agent any
    environment{
        DOCKER_CRED = credentials('PersonalDockerHub')
        REGISTRY = 'devbackend1997'
        IMAGE_NAME = 'node-pin1'
        }
    stages{
        stage('docker build'){
            steps{
                sh '''
                VERSION=$(jq --raw-output .version package.json)
                docker build -t $IMAGE_NAME:$(cat version.txt) .
                ''' 
            }
        }
        stage('Deploy to Docker'){

            steps{
                sh '''
                docker login --username=$DOCKER_CRED_USR --password=$DOCKER_CRED_PSW
                docker tag $IMAGE_NAME:(cat version.txt) $REGISTRY/$IMAGE_NAME:(cat version.txt)
                docker push $REGISTRY/$IMAGE_NAME:(cat version.txt)
                '''
            }
        }
    }
}
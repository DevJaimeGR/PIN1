// dockerlib.groovy

def pushDockerImage(String DOCKER_CRED_USR, String DOCKER_CRED_PSW, String IMAGE_NAME, String VERSION, String REGISTRY) {
    node{
        sh """
        docker login --username=$DOCKER_CRED_USR --password=$DOCKER_CRED_PSW
        docker tag $IMAGE_NAME:$VERSION $REGISTRY/$IMAGE_NAME:$VERSION
        docker push $REGISTRY/$IMAGE_NAME:$VERSION
        """
    }
    
}

def buildImage(String IMAGE_NAME, String VERSION){
    node{
        sh """
        docker build -t $IMAGE_NAME:$VERSION .
        """ 
    }

}

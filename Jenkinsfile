pipeline{
    agent any

    tools{
        maven "Maven3"
    }

    environment {
        PATH = "/opt/homebrew/bin:/Applications/Docker.app/Contents/Resources/bin:${env.PATH}"
        DOCKERHUB_CREDENTIALS_ID = 'docker-pat'
        DOCKERHUB_REPO = 'leevivl/metropolia-shopping-cart'
        DOCKER_IMAGE_TAG = 'latest'

        SSH_KEY_PATH = '~/.ssh/leevivl-875.pem'
        REMOTE_USER = 'leevivl'
        REMOTE_HOST = 'shell.metropolia.fi'
        REMOTE_PATH = '/home1-2/l/leevivl/public_html/shopping-cart-jacoco'
    }

    stages{
        stage('Build and Test'){
            steps {
                sh 'mvn clean test'
            }
        }
        stage ('Code coverage'){
            steps {
                sh 'mvn jaococ:report'
            }
        }
        stage ('Public Test Results'){
            steps {
                junit '**/target/surefire-report/*.xml'
                sh 'chmod 600 ${SSH_KEY_PATH}'
                sh "scp -i ${SSH_KEY_PATH} -o StrictHostKeyChecking -r target/site ${REMOTE_USER}@${REMOTE_HOST}:${REMOTE_PATH}"
            }
        }
        stage('Build & Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: DOCKERHUB_CREDENTIALS_ID,
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                        set -e

                        # Login to Docker Hub
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin

                        # Build Docker image
                        docker build -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} .

                        # Push Docker image
                        docker push ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}
                    '''
                }
            }
        }
    }
}
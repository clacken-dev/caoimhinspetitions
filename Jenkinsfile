pipeline {
    agent any

    tools {
        jdk 'Java17'
        maven 'Maven'
    }

    environment {
        WAR_NAME = "caoimhinspetitions.war"
        IMAGE_NAME = "myapp"
        CONTAINER_NAME = "myappcontainer"
        DEPLOY_PORT = "9090"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/clacken-dev/caoimhinspetitions.git', credentialsId: 'github-pat'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Archive WAR') {
            steps {
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker rm -f ${CONTAINER_NAME} || true"
                    sh "docker build -f Dockerfile -t ${IMAGE_NAME} ."
                }
            }
        }

        stage('Deploy') {
            steps {
                input message: 'Deploy the application?', ok: 'Deploy'
                script {
                    sh "docker run --name ${CONTAINER_NAME} -p ${DEPLOY_PORT}:8080 --detach ${IMAGE_NAME}:latest"
                }
            }
        }
    }
}

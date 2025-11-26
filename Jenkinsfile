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
        DEPLOY_PORT = "9090"  // map container 8080 to host 9090
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/clacken-dev/caoimhinspetitions.git', credentialsId: 'github-pat'
            }
        }

        stage('Build WAR') {
            steps {
                sh 'mvn clean package'
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Remove old container if exists
                    sh "docker rm -f ${CONTAINER_NAME} || true"

                    // Build Docker image with WAR deployed into Tomcat
                    sh "docker build -f Dockerfile -t ${IMAGE_NAME} ."
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    // Run container, map container's 8080 -> host 9090, detach mode
                    sh "docker run --name ${CONTAINER_NAME} -p ${DEPLOY_PORT}:8080 --detach ${IMAGE_NAME}:latest"
                }
            }
        }
    }
}

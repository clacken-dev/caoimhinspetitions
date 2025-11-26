pipeline {
    agent any

    tools {
        jdk 'Java17'
        maven 'Maven'
    }

    environment {
        DEPLOY_DIR = "/var/lib/jenkins/deploy"
        WAR_NAME   = "caoimhinspetitions.war"
        RUN_SCRIPT = "${DEPLOY_DIR}/run.sh"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/clacken-dev/caoimhinspetitions.git',
                    credentialsId: 'github-pat'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Archive WAR') {
            steps {
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
            }
        }

        stage('Approval to Deploy') {
            steps {
                input message: "Deploy latest WAR to Spring Boot (port 9090)?"
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Copy WAR to deploy dir
                    sh "cp target/*.war ${DEPLOY_DIR}/${WAR_NAME}"

                    // Run the script to kill old process and launch new one in background
                    sh "${RUN_SCRIPT}"
                }
            }
        }
    }
}

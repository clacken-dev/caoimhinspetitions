pipeline {
    agent any

    tools {
        jdk 'Java17'
        maven 'Maven'
    }

    environment {
        DEPLOY_DIR = "/var/lib/jenkins/deploy"
        WAR_NAME   = "caoimhinspetitions.war"
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
                    sh """
                        # Stop any running app
                        pkill -f caoimhinspetitions.war || true

                        # Copy new WAR to deploy dir
                        cp target/*.war /var/lib/jenkins/deploy/caoimhinspetitions.war

                        # Delete old log
                        rm -f /var/lib/jenkins/deploy/app.log

                        # Start app in foreground (no nohup)
                        cd /var/lib/jenkins/deploy
                        java -jar caoimhinspetitions.war --server.port=9090
                    """
                }
            }
        }

    }
}

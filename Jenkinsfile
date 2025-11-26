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
                script {
                    timeout(time: 10, unit: 'MINUTES') {
                        input message: "Deploy latest WAR to Spring Boot on port 9090?"
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    sh """
                        # Create deploy directory if first time
                        mkdir -p $DEPLOY_DIR

                        # Copy WAR into the deploy directory
                        cp target/*.war $DEPLOY_DIR/$WAR_NAME

                        # Stop any running app on port 9090 (ignore errors if none)
                        pkill -f $WAR_NAME || true

                        # Start new app under nohup on port 9090
                        nohup java -jar $DEPLOY_DIR/$WAR_NAME --server.port=9090 > $DEPLOY_DIR/app.log 2>&1 &
                    """
                }
            }
        }
    }
}

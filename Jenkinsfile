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
                        # Create deploy directory if it doesn't exist
                        mkdir -p $DEPLOY_DIR

                        # Copy WAR into deploy directory
                        cp target/*.war $DEPLOY_DIR/$WAR_NAME

                        # Stop any running instance of this WAR
                        pkill -f $WAR_NAME || true

                        # Start the WAR in the background
                        cd $DEPLOY_DIR
                        nohup java -jar $WAR_NAME --server.port=9090 > app.log 2>&1 &
                    """
                }
            }
        }
    }
}

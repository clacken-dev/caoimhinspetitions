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
                java -jar caoimhinspetitions.war
            """
        }
    }
}

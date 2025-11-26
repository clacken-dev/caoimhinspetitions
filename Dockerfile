FROM tomcat:latest
ADD target/caoimhinspetitions.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]

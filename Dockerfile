FROM tomcat:10.1.31-jdk21
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war
ENV JAVA_OPTS="-Xms256m -Xmx1000m -XX:+UseContainerSupport"
EXPOSE 8080
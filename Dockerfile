FROM library/tomcat:7-jre7
RUN rm -rf /usr/local/tomcat/webapps/*
ADD ./target/message-server-0.1.0-SNAPSHOT-standalone.war /usr/local/tomcat/webapps/ROOT.war

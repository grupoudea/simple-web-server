FROM openjdk:8-jdk-alpine
RUN mkdir /opt/jgkserver
RUN cd /opt/jgkserver
COPY config config
COPY httpages httpages
COPY logs logs
ADD target/simple-web-server-v1.0.1-jar-with-dependencies.jar /opt/jgkserver/jgkserver.jar
EXPOSE 80
WORKDIR /opt/jgkserver
ENTRYPOINT ["java", "-jar","jgkserver.jar"]
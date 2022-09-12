FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 7001
ADD target/*.jar authorization-microservice.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /authorization-microservice.jar" ]

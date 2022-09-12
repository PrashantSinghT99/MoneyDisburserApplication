FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 7002
ADD target/*.jar pensioner-details-microservice.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /pensioner-details-microservice.jar" ]
FROM openjdk:8-jre-alpine
ADD target/user-service.jar user-service.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar user-service.jar"]
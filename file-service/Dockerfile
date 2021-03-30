FROM openjdk:8-jre-alpine
ADD target/file-service.jar file-service.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar file-service.jar"]
FROM openjdk:8-jre-alpine
ADD target/registry-service.jar registry-service.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar registry-service.jar"]
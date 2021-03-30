FROM openjdk:8-jre-alpine
ADD target/api-gateway.jar api-gateway.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar api-gateway.jar"]
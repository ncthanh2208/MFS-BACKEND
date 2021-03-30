FROM openjdk:8-jre-alpine
ADD target/auth-service.jar auth-service.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar auth-service.jar"]
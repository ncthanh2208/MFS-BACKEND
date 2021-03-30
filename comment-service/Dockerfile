FROM openjdk:8-jre-alpine
ADD target/comment-service.jar comment-service.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar comment-service.jar"]
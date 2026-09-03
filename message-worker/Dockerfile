FROM eclipse-temurin:21-jre
ARG JAR_FILE=target/message-worker-*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
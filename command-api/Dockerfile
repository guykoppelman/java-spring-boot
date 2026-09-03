FROM eclipse-temurin:21-jre
ARG JAR_FILE=target/command-api-*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
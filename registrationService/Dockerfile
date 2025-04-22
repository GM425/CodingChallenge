FROM openjdk:17-jdk-alpine
EXPOSE 8088
VOLUME /temp
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ADD target/registrationService-0.0.1-SNAPSHOT.jar registrationService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "registrationService-0.0.1-SNAPSHOT.jar", "/app/app.jar"]

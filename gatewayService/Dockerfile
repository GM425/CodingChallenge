FROM openjdk:17-jdk-alpine
EXPOSE 8080
VOLUME /temp
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ADD target/gateway-0.0.1-SNAPSHOT.jar gateway-0.0.1-SNAPSHOT.jar
# /app/app.jar
ENTRYPOINT ["java", "-jar", "gateway-0.0.1-SNAPSHOT.jar", "/app/app.jar"]
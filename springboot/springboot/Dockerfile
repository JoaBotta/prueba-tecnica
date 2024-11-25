FROM openjdk:17-jdk-slim

WORKDIR /app



COPY target/springboot-0.0.1-SNAPSHOT.jar springboot-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
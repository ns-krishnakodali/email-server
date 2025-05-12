# Build stage
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/email-server-1.0-SNAPSHOT.jar /app/email-server.jar
EXPOSE 4567
CMD ["java", "-jar", "email-server.jar"]

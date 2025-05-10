# Use the official OpenJDK 17 image as a base
FROM openjdk:17-jdk-slim

RUN apt-get update && apt-get install -y maven && apt-get clean

WORKDIR /app

COPY pom.xml /app/
COPY src /app/src

RUN mvn clean package

COPY target/email-server-1.0-SNAPSHOT.jar /app/email-server.jar

EXPOSE 4567

CMD ["java", "-jar", "email-server.jar"]

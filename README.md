# Email Server

A lightweight email server built using Spark Java. Provides a simple REST API to send emails using SMTP.

## Features

- REST API for sending emails using SMTP
- Uses SMTP for delivery

## Requirements

- Java 11 or later
- Maven or Gradle

## Setup

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/email-server.git
cd email-server
```

### 2. Configure SMTP Settings

Update your SMTP credentials and settings in the `application.properties` file located in the `src/main/resources`
directory:

```properties
smtp.host=smtp.example.com
smtp.port=587
smtp.username=your-email@example.com
smtp.password=your-password
```

> **Note:** Avoid hardcoding sensitive values in version-controlled files. Consider using external config management for
> production.

### 3. Build and run

#### Using Maven

```bash
mvn clean install
java -jar target/email-server.jar
```

#### Using Gradle

```bash
./gradlew build
java -jar build/libs/email-server.jar
```

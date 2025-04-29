# Email Server

A lightweight email server built using Spark Java. Provides a simple REST API to send emails using SMTP.

## Features

- REST API for sending emails using SMTP
- Uses SMTP for delivery

## Requirements

- Java 8 or later
- Maven or Gradle

## Setup

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/email-server.git
cd email-server
```

### 2. Set environment variables

```bash
export SMTP_HOST=smtp.example.com
export SMTP_PORT=587
export SMTP_USERNAME=your-email@example.com
export SMTP_PASSWORD=your-password
```

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

[//]: # (Add about application.properties)
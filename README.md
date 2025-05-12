# Email Server

A lightweight email server built using Spark Java. Provides a simple REST API to send emails using SMTP.

---
## Requirements
* Java 11 or later
* Maven or Gradle
* Docker (optional)

---
## Setup

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/email-server.git
cd email-server
```

### 2. Configure SMTP Settings via Environment Variables

Before running the application, set the following environment variables:

| Variable        | Description                    |
| --------------- | ------------------------------ |
| `SMTP_EMAIL`    | Your SMTP email address        |
| `SMTP_PASSWORD` | Your SMTP email password/token |
| `SMTP_PORT`     | SMTP port (default is `587`)   |

#### On Linux/macOS:

```bash
export SMTP_EMAIL=your-email@example.com
export SMTP_PASSWORD=your-password
export SMTP_PORT=587
```

#### On Windows (PowerShell):

```powershell
$env:SMTP_EMAIL="your-email@example.com"
$env:SMTP_PASSWORD="your-password"
$env:SMTP_PORT="587"
```

#### In IntelliJ IDEA:

In IntelliJ, add the **Environment Variables** (`SMTP_EMAIL`, `SMTP_PASSWORD`, `SMTP_PORT`) and their values.

### 3. Build and Run

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

---

## Running with Docker

### 1. Build the Docker image

```bash
docker build -t email-server .
```

### 2. Run the container with environment variables

```bash
docker run -d \
  -e SMTP_EMAIL=your-email@example.com \
  -e SMTP_PASSWORD=your-password \
  -e SMTP_PORT=587 \
  -p 4567:4567 \
  email-server
```

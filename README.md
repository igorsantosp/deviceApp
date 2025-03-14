# 📱 Device Management API

## 📌 Overview
The **Device App** is a RESTful service for managing devices. It supports CRUD operations, filtering by brand/state, and enforces business rules such as preventing updates/deletions of in-use devices. The application is built with **Spring Boot**, uses **MySQL** for persistence, and is containerized with **Docker**.

## ⚡ Features
- ✅ Create a new device
- ✅ Retrieve a single or all devices
- ✅ Filter devices by brand and state
- ✅ Update (fully or partially) a device
- ✅ Prevent updates to `name` and `brand` if the device is in use
- ✅ Prevent deletion of in-use devices
- ✅ Containerized with Docker and Docker Compose
- ✅ Database schema management with Liquibase
- ✅ Comprehensive test coverage

## 🛠️ Technologies Used
- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA** (Hibernate & Liquibase for DB migrations)
- **MySQL Database**
- **Docker & Docker Compose**
- **JUnit 5 & Mockito** (Unit Testing)
- **Maven** (Build Tool)

## 🚀 Getting Started

### Prerequisites
Ensure you have the following installed:
- [JDK 21+](https://adoptium.net/)
- [Maven 3.9+](https://maven.apache.org/download.cgi)
- [Docker & Docker Compose](https://docs.docker.com/get-docker/)

### 🏗️ Setup
Clone the repository:
```sh
git clone https://github.com/igorsantosp/deviceApp.git
cd deviceApp
```


### 🏃 Running Locally (Without Docker)
If you prefer to run it manually:

#### 1️⃣ Start MySQL Database (Locally)
Make sure MySQL is running and update `application.properties` with your DB credentials.

#### 2️⃣ Build and Run the App
```sh
mvn clean install
mvn spring-boot:run
```

### 📦 Running with Docker RECOMMENDED
First be sure that you are the last jar of you code generated (can be obtained running mvn clean install for example) then follow:
To start the **API & MySQL** using Docker:
```sh
docker-compose up --build
```
This will:
- Start the MySQL database
- Run Liquibase migrations to create the `device` table
- Start the Spring Boot API
- With this one since the API needs for the db to be up to work it might be needed to restart the API container after the container of the db becomes stable.

## 🔥 API Endpoints
| HTTP Method | Endpoint | Description |
|------------|---------|-------------|
| `POST` | `/device` | Create a new device |
| `GET` | `/device/{id}` | Get device by ID |
| `GET` | `/device` | Get all devices |
| `GET` | `/device?brand=XYZ` | Filter devices by brand |
| `GET` | `/device?state=available` | Filter devices by state |
| `PUT` | `/device/{id}` | Fully update a device |
| `DELETE` | `/device/{id}` | Delete a device (if not in use) |

## 📝 Business Rules
- 🚫 **Cannot update `name` or `brand`** if the device is `in-use`
- 🚫 **Cannot delete** devices that are `in-use`
- 🕒 **Creation timestamp** cannot be modified

## ✅ Running Tests
To run unit tests:
```sh
mvn test
```

## 📜 Database Schema & Sample Data (Liquibase)
Liquibase manages the DB schema. A sample device entry is preloaded with this information:
-id:1
-name: "Sample Device"
-brand: "BrandX"
-state: "AVAILABLE"


## 🔄 Stopping the App
To stop Docker containers:
```sh
docker-compose down
```

## 📋 Documentation - Using Swagger
- Swagger is accessible by this path: http://{host:port}/swagger-ui/index.html

## 📌 Future Improvements
- 📊 Add pagination for `GET /api/devices`
- 🔐 Implement authentication & authorization

---

🚀 **Happy Coding!** 🎯


# 🎓 StudyRoom Booking API

A REST API for managing and booking university study rooms, built with **Spring Boot 3.3**, **Spring Data JPA**, and an **H2 In-Memory Database**.

## 🛠 Tech Stack

| Component | Version / Details |
| :--- | :--- |
| **Java** | 21 |
| **Spring Boot** | 3.3.5 (Web, Data JPA) |
| **Database** | H2 (In-Memory) |
| **Lombok** | Boilerplate reduction |
| **springdoc-openapi** | 2.6.0 (Swagger UI) |
| **Build** | Maven (Wrapper included) |

## 🚀 Getting Started

**Prerequisites:** JDK 21 and a configured `JAVA_HOME` environment variable.

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run

# Create a new room
curl -X POST http://localhost:8081/api/rooms \
  -H "Content-Type: application/json" \
  -d '{"roomName":"OH14 - Raum E01","capacity":6,"hasWhiteboard":true}'

# Get all rooms
curl http://localhost:8081/api/rooms

# Get available rooms
curl http://localhost:8081/api/rooms/available

# Book room with ID 1
curl -X PUT http://localhost:8081/api/rooms/1/book

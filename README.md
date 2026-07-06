# Order Notification Microservices

A distributed microservices application built using **Spring Boot** to demonstrate how independent services communicate in a production-style backend system. The project combines **synchronous REST APIs** with **asynchronous event-driven communication using Apache Kafka**, leverages **Redis** for caching, and uses **PostgreSQL** and **MongoDB** for data persistence. The application is containerized using **Docker** and deployed on an **AWS EC2** instance.

---

## Project Structure

```
Microservices-Project
│
├── OrderService
├── NotificationService
└── docker-compose.yml
```

---

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Data MongoDB
- PostgreSQL
- MongoDB
- Apache Kafka
- Redis
- Docker & Docker Compose
- AWS EC2
- Maven
- Lombok

---

## Services

### Order Service

- Creates new orders.
- Stores order details in PostgreSQL.
- Publishes order events to Apache Kafka.
- Exposes REST APIs for order management.

### Notification Service

- Consumes order events from Apache Kafka.
- Stores notification details in MongoDB.
- Simulates sending notifications.
- Uses Redis to cache frequently accessed notification data.

---

## Architecture

```
                 REST API

                 Client
                    |
                    v
             Order Service
             (PostgreSQL)
                    |
             Publish Event
                    |
                    v
             Apache Kafka
                    |
             Consume Event
                    |
                    v
        Notification Service
              (MongoDB)
                    |
                    v
                 Redis Cache
```

---

## Request Flow

1. Client sends a request to create an order.
2. Order Service stores the order in PostgreSQL.
3. Order Service publishes an **Order Created** event to Apache Kafka.
4. Notification Service consumes the event.
5. Notification details are stored in MongoDB.
6. Frequently accessed notification data is served from Redis cache.
7. The application runs inside Docker containers and is deployed on an AWS EC2 instance.

---

## Running the Project

### 1. Start Infrastructure

```bash
docker compose up -d
```

This starts:

- PostgreSQL
- MongoDB
- Apache Kafka
- Redis

---

### 2. Start Notification Service

```bash
cd NotificationService
mvn spring-boot:run
```

Runs on:

```
http://localhost:8082
```

---

### 3. Start Order Service

```bash
cd OrderService
mvn spring-boot:run
```

Runs on:

```
http://localhost:8081
```

---

## API Endpoints

### Create Order

```
POST /api/orders
```

Sample Request

```json
{
  "customerName": "Khushi",
  "product": "Laptop",
  "quantity": 1,
  "totalPrice": 75000
}
```

---

### Get Order

```
GET /api/orders/{id}
```

---

### Get All Orders

```
GET /api/orders
```

---

## Current Features

- ✅ Spring Boot Microservices
- ✅ REST APIs
- ✅ PostgreSQL Integration
- ✅ MongoDB Integration
- ✅ Apache Kafka Integration
- ✅ Redis Caching
- ✅ Docker & Docker Compose
- ✅ AWS EC2 Deployment
- ✅ Event-Driven Communication

---

## Future Enhancements

- Retry Mechanism
- Dead Letter Queue (DLQ)
- Circuit Breaker using Resilience4j
- API Gateway
- Distributed Tracing
- Prometheus & Grafana Monitoring
- Kubernetes Deployment
- CI/CD Pipeline using GitHub Actions

---

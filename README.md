# Order Notification Microservices

This project is a simple microservices application built using Spring Boot. The goal of this project is to understand how multiple services communicate with each other and how a distributed system is designed.

The application uses synchronous REST APIs for client interactions and asynchronous communication between services using Apache Kafka. It also leverages Redis for caching and is deployed on an AWS EC2 instance.

## Project Structure

```
Microservices-Project
│
├── OrderService
├── NotificationService
└── docker-compose.yml
```

## Technologies Used

* Java 17
* Spring Boot
* Spring Data JPA
* Spring Data MongoDB
* PostgreSQL
* MongoDB
* Apache Kafka
* Redis
* Docker & Docker Compose
* AWS EC2
* Maven
* Lombok

## Services

### Order Service

* Creates new orders.
* Stores order details in PostgreSQL.
* Publishes order events to Apache Kafka.
* Exposes REST APIs for order management.

### Notification Service

* Consumes order events from Apache Kafka.
* Stores notifications in MongoDB.
* Uses Redis for caching notification data.
* Logs the notification as if it was sent successfully.

## Architecture

```
                REST API

Client
   |
   v
Order Service
(PostgreSQL)
   |
   | Publish Event
   v
Apache Kafka
   |
   | Consume Event
   v
Notification Service
(MongoDB)
   |
   v
Redis
```

## Request Flow

1. Client sends a request to create an order.
2. Order Service saves the order in PostgreSQL.
3. Order Service publishes an event to Apache Kafka.
4. Notification Service consumes the event.
5. Notification Service stores the notification in MongoDB.
6. Redis caches frequently accessed notification data.

## Running the Project

### 1. Start Docker containers

```bash
docker compose up -d
```

This starts:

* PostgreSQL
* MongoDB
* Apache Kafka
* Redis

### 2. Start Notification Service

```bash
cd NotificationService
mvn spring-boot:run
```

Notification Service runs on:

```
http://localhost:8082
```

### 3. Start Order Service

```bash
cd OrderService
mvn spring-boot:run
```

Order Service runs on:

```
http://localhost:8081
```

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

### Get Order

```
GET /api/orders/{id}
```

### Get All Orders

```
GET /api/orders
```

## Current Status

-> Order Service

-> Notification Service

-> PostgreSQL Integration

-> MongoDB Integration

-> Apache Kafka Integration

-> Redis Integration

-> Docker Compose

-> AWS EC2 Deployment

-> Event-driven communication using Kafka

## Next Steps

I plan to improve this project step by step by adding:

* Retry mechanism
* Dead Letter Queue (DLQ)
* Circuit Breaker using Resilience4j
* Distributed Tracing
* Prometheus & Grafana monitoring
* Kubernetes deployment

The idea is to gradually evolve this project into a production-style distributed system by incorporating advanced reliability, observability, and scalability features.

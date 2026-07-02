# Order Notification Microservices

This project is a simple microservices application built using Spring Boot. The goal of this project is to understand how multiple services communicate with each other and how a distributed system is designed.

Currently, the services communicate synchronously using REST APIs. In the next phase, I will replace the REST communication with Apache Kafka to make it event-driven and asynchronous.

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
* Docker & Docker Compose
* Maven
* Lombok

## Services

### Order Service

* Creates new orders.
* Stores order details in PostgreSQL.
* Calls Notification Service using REST.
* Updates order status based on whether the notification was sent successfully.

### Notification Service

* Receives notification requests from Order Service.
* Stores notifications in MongoDB.
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
   | HTTP POST
   v
Notification Service
(MongoDB)
```

## Request Flow

1. Client sends a request to create an order.
2. Order Service saves the order in PostgreSQL.
3. Order Service calls Notification Service using REST.
4. Notification Service stores the notification in MongoDB.
5. If the notification is successful, the order status becomes `NOTIFIED`.
6. If the notification fails, the order status becomes `NOTIFICATION_FAILED`.

## Running the Project

### 1. Start Docker containers

```bash
docker compose up -d
```

This starts:

* PostgreSQL
* MongoDB

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

-> Docker Compose

-> REST-based communication

## Next Steps

I plan to improve this project step by step by adding:

* Apache Kafka for asynchronous communication
* Retry mechanism
* Dead Letter Queue (DLQ)
* Circuit Breaker using Resilience4j
* Distributed Tracing
* Prometheus & Grafana monitoring
* Kubernetes deployment

The idea is to gradually evolve this project from a simple synchronous microservices application into a production-style distributed system.

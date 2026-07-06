# Order Notification Microservices

This project is a simple microservices application built using Spring Boot to demonstrate how multiple services communicate in a distributed system.

The application uses synchronous REST APIs for client interactions and asynchronous communication between services using Apache Kafka. Order data is stored in PostgreSQL, notifications are stored in MongoDB, and Redis is used to cache frequently accessed order data for improved performance. The application is containerized using Docker and deployed on an AWS EC2 instance.

---

# Project Structure

```text
Microservices-Project
│
├── OrderService
├── NotificationService
└── docker-compose.yml
```

---

# Technologies Used

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

---

# Services

## Order Service

* Creates new orders.
* Stores order details in PostgreSQL.
* Caches frequently accessed order data in Redis.
* Publishes order events to Apache Kafka.
* Exposes REST APIs for order management.

## Notification Service

* Consumes order events from Apache Kafka.
* Stores notifications in MongoDB.
* Logs the notification as successfully sent (simulated notification delivery).

---

# Architecture

```text
                      REST API

                         Client
                            |
                            v
                   +------------------+
                   |   Order Service  |
                   +------------------+
                     |             |
                     |             |
      Store Order    |             | Cache Order
                     |             |
                     v             v
               PostgreSQL       Redis
                     |
                     | Publish Event
                     v
                Apache Kafka
                     |
                     | Consume Event
                     v
             +-----------------------+
             | Notification Service  |
             +-----------------------+
                     |
                     | Store Notification
                     v
                  MongoDB
```

---

# Request Flow

1. Client sends a request to create an order.
2. Order Service stores the order in PostgreSQL.
3. Order Service caches the order in Redis.
4. Order Service publishes an order event to Apache Kafka.
5. Notification Service consumes the event.
6. Notification Service stores the notification in MongoDB.
7. Notification Service logs that the notification was successfully sent.

---

# Running the Project

## 1. Start Docker Containers

```bash
docker compose up -d
```

This starts:

* PostgreSQL
* MongoDB
* Apache Kafka
* Redis

---

## 2. Start Notification Service

```bash
cd NotificationService
mvn spring-boot:run
```

Notification Service runs on:

```
http://localhost:8082
```

---

## 3. Start Order Service

```bash
cd OrderService
mvn spring-boot:run
```

Order Service runs on:

```
http://localhost:8081
```

---

# API Endpoints

## Create Order

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

## Get Order

```
GET /api/orders/{id}
```

Returns the order by its ID. If available, the Order Service first attempts to retrieve the order from Redis before querying PostgreSQL.

---

## Get All Orders

```
GET /api/orders
```

Returns all orders stored in PostgreSQL.

---

# Current Status

* ✅ Order Service
* ✅ Notification Service
* ✅ PostgreSQL Integration
* ✅ MongoDB Integration
* ✅ Redis Integration (Order Cache)
* ✅ Apache Kafka Integration
* ✅ Event-driven communication using Kafka
* ✅ Docker Compose
* ✅ AWS EC2 Deployment

---

# Future Enhancements

The project will be extended with production-ready features, including:

* Retry mechanism for failed message processing
* Dead Letter Queue (DLQ)
* Circuit Breaker using Resilience4j
* Distributed Tracing
* Prometheus & Grafana Monitoring
* Kubernetes Deployment
* API Gateway
* Service Discovery
* Centralized Configuration
* Authentication & Authorization (JWT)

The goal is to evolve this project into a production-style distributed microservices system by incorporating advanced reliability, observability, scalability, and fault-tolerance patterns.

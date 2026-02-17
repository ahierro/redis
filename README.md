# Redis Hello World

A minimal Spring Boot application that demonstrates using **Redis** as a data store. It exposes a REST API for Product CRUD operations, with all data persisted in Redis.

## What's in this project

- **Spring Boot 4** + **Spring Data Redis** – in-memory data store
- **REST API** – Product resource with create, read, update, delete
- **OpenAPI / Swagger** – interactive API documentation
- **Docker Compose** – Redis server + Redis Insight (Redis UI)

## Prerequisites

- Java 25+
- Maven
- Docker & Docker Compose (for Redis and Redis Insight)

## Quick start

### 1. Start Redis and Redis Insight

```bash
docker compose up -d
```

This starts:

- **Redis** on `localhost:6379` (with persistence via `appendonly`)
- **Redis Insight** (Redis UI) on port **5540**

### 2. Run the application

```bash
./mvnw spring-boot:run
```

The app runs on the default port **8080** and connects to Redis at `localhost:6379` (overridden automatically when using Docker Compose).

## URLs

| Resource   | URL |
|-----------|-----|
| **Swagger UI** (API docs) | http://localhost:8080/swagger-ui.html |
| **Redis UI** (Redis Insight) | http://localhost:5540 |

- **Swagger**: explore and try the Product API (create, get, list, update, delete).
- **Redis Insight**: inspect keys, run commands, and monitor the Redis instance.

## API overview

Base path: `/api/products`

| Method | Path      | Description        |
|--------|-----------|--------------------|
| POST   | `/api/products`     | Create a product   |
| GET    | `/api/products`     | List all products  |
| GET    | `/api/products/{id}`| Get product by ID  |
| PUT    | `/api/products/{id}`| Update a product   |
| DELETE | `/api/products/{id}`| Delete a product   |

Example create request:

```json
POST /api/products
Content-Type: application/json

{ "name": "My Product" }
```

## Project structure

- `src/main/java/com/example/redis/` – main application, config, controller, repository, entity
- `compose.yaml` – Redis and Redis Insight services
- `connections.json` – Redis Insight connection preset (host: `redis`, port: 6379)

## License

See repository license.

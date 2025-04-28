# Auth API — Spring Boot Authentication Practice Project

## 📋 About the Project

A backend application built with Spring Boot focused on practicing **JWT authentication** and **basic user management**.
---

## 🚀 Features

- Register new users
- Authenticate and receive a JWT token
- View current authenticated user's information
- Update current authenticated user's information
- Get a paginated list of users (admin only)
- Delete users by ID (admin only)
- Global exception handling with custom error responses
- Docker Compose support for running PostgreSQL locally

---

## ⚙️ Technologies Used

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- JWT
- PostgreSQL
- Docker
- Springdoc OpenAPI (Swagger)

---

## 🐳 How to Run Locally

### 1. Clone the project

```bash
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name
```

### 2. Start PostgreSQL with Docker Compose

```bash
docker-compose up -d
```

This will start a PostgreSQL container using the provided `.env` file.

### 3. Run the application

```bash
./mvnw spring-boot:run
```

Or run it directly from your IDE (IntelliJ IDEA, Eclipse, etc).

> **Note:** Ensure you have a `.env` file with correct database credentials. A sample `.env` file is already included.

---

## 🛠️ API Endpoints (current)

| Method | Endpoint | Description |
|:------|:---------|:------------|
| POST | `/api/v1/auth/register` | Register a new user |
| POST | `/api/v1/auth/authenticate` | Login and receive a JWT token |
| GET  | `/api/v1/users/me` | Get current authenticated user's info |
| PATCH | `/api/v1/users/me` | Update current authenticated user's info |
| GET  | `/api/v1/users` | Get paginated list of users (admin only) |
| DELETE | `/api/v1/users/{id}` | Delete user by ID (admin only) |

---

## 📚 API Documentation

After running the application, you can access the Swagger UI for testing and exploring the API:

```
http://localhost:8080/swagger-ui.html
```
or
```
http://localhost:8080/swagger-ui/index.html
```

The Swagger UI provides a full interactive documentation for all available API endpoints, allowing you to test requests and view response examples directly from the browser.

---




# Auth API — Spring Boot Authentication Practice Project

## 📋 About the Project

A backend application built with Spring Boot focused on practicing authentication concepts(JWT, OAuth)

It currently supports registration, authentication via JWT, and basic exception handling.  
Future improvements will include CRUD for users, OAuth2 integration (Google, GitHub), and API documentation via Swagger.

---

## 🚀 Features

- Register new users
- Authenticate and receive a JWT token
- Global exception handling with custom error responses
- Secure password storage using BCrypt
- Clean modular project structure
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
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/authenticate` | Login and receive JWT token |

---

## 📈 Planned Features

- [ ] Add User Service and CRUD Controller
- [ ] Integrate Swagger / OpenAPI documentation
- [ ] OAuth2 login via Google and GitHub
- [ ] Role-based access control (USER / ADMIN)
- [ ] Refine error handling responses
- [ ] Add Unit and Integration tests

---

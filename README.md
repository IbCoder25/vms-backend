# Vehicle Management System (VMS) - Backend

> **Independent Subproject**: `vms-backend` (Java Spring Boot 3.x, Java 21)

This repository contains the backend REST API service for the Vehicle Management System. It is strictly decoupled and independent from `vms-frontend` (Flutter Web).

---

## 🏗️ Architecture & Boundaries

- **Pattern**: Clean Layered Architecture / Modular Monolith
- **Communication Protocol**: Stateless REST API over JSON (`/api/v1/*`)
- **Persistence**: Spring Data JPA + PostgreSQL 16
- **Documentation**: OpenAPI 3.0 / Swagger UI (`/api/v1/swagger-ui.html`)
- **Cross-Origin**: CORS filter configured for local Flutter Web development

```
vms-backend/
├── compose.yaml                     # Local PostgreSQL + pgAdmin containers
├── pom.xml                          # Maven build & dependencies (Java 21, Spring Boot 3.3.x)
├── .env.example                     # Environment variables template
├── src/
│   ├── main/
│   │   ├── java/com/vms/backend/
│   │   │   ├── VmsBackendApplication.java
│   │   │   ├── config/              # Infrastructure & Framework Configs (CORS, Swagger)
│   │   │   ├── common/              # Shared ApiResponse, Exception Handlers
│   │   │   ├── security/            # Security & Auth filters (placeholder)
│   │   │   └── modules/             # Business modules (to be implemented)
│   │   └── resources/
│   │       ├── application.yml      # Base application configuration
│   │       ├── application-dev.yml  # Local development profile (PostgreSQL)
│   │       └── application-prod.yml # Production profile
│   └── test/                        # Integration and unit tests
```

---

## 🚀 Quick Start (Development)

### 1. Start Local Database

```bash
docker compose up -d
```

This starts:

- **PostgreSQL**: `localhost:5432` (`vms_db` / `vms_user` / `vms_password`)
- **pgAdmin**: `http://localhost:5050` (`admin@vms.local` / `admin123`)

### 2. Run the Spring Boot Application

```bash
mvn spring-boot:run
```

The API server starts at: `http://localhost:8080/api/v1`
Interactive Swagger UI: `http://localhost:8080/api/v1/swagger-ui.html`

---

## 🔒 Separation of Concerns Rule

- **Never couple or merge with `vms-frontend`**: All interactions must happen exclusively through standard REST endpoints and JSON DTOs.
- Domain features should be organized in clean modules under `com.vms.backend.modules.<feature>`.

# vms-backend

Java Spring Boot backend for the Stark Group Vehicle Management System

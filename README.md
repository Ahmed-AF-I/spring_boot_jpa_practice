# 🚀 Employee Management System (JPA Relationship Lab & Secured API)

A robust Spring Boot application designed to master advanced JPA Entity Relationships, Data Mapping, Dynamic Filtering Specifications, and Enterprise-Grade Security.

## 🛠️ Tech Stack & Advanced Concepts

- **Java 25** & **Spring Boot 4.0.6 (GA)**
- **Spring Data JPA** (Hibernate)
- **PostgreSQL**
- **Spring Security** (Authentication & Role-Based Access Control)
- **Argon2id** (Advanced Password Hashing via `Argon2PasswordEncoder`)
- **Bucket4j** (IP-based Rate Limiting Filter)
- **Lombok** (Boilerplate reduction)
- **Jakarta Validation** (Input Sanitization & `@Valid` Constraints)

## 🧩 Key Features & Patterns Implemented

- **Advanced Security**: Protected endpoints using HTTP Basic Auth, secured with industry-standard **Argon2id** hashing ($64\text{MB}$ memory cost, $3$ iterations) configurable via environment properties.
- **API Rate Limiting**: Built-in IP-based rate limiting using a custom `OncePerRequestFilter` backed by Bucket4j ($5$ requests/min limit per IP) to mitigate Brute-Force and DoS attacks.
- **Entity Relationships**: Deep dive into `@OneToOne`, `@OneToMany`, and `@ManyToMany` (Employee-Mission)
- **Data Transfer Objects (DTOs)**: Using Java Records for immutable and clean data transfer (`RequestDTO` / `ResponseDTO`).
- **Structural Mapping**: Custom Mappers for full control over Entity-DTO conversion, preventing circular references and handling lazy loading mapping gracefully (`open-in-view: false`).
- **Advanced Filtering**: Implementation of JPA Specifications for dynamic, complex searching (`firstName`, `lastName`, `email`, `street`, etc.).
- **Clean Controller Design**: Full RESTful CRUD operations with appropriate HTTP status codes (`201 Created` for resources creation).

## 🗺️ Database Architecture

- **Employee ↔ Address**: Unidirectional One-to-One
- **Department ↔ Employee**: Bidirectional One-to-Many
- **Employee ↔ Mission**: Bidirectional Many-to-Many with a custom JoinTable

---

## 🔒 Security & Access Control

The application is secured using **Spring Security**. Access to endpoints is tightly controlled based on the user's roles and authorities:

* **`ROLE_ADMIN`**: Has full administrative access to all endpoints (Create, Update, Delete across all modules).
* **`ROLE_USER`**: Restricted access. Can interact with Departments and Missions, but cannot modify Employee or Address records.

### How to Access Secured Endpoints (Postman / cURL)
1. Go to the **Authorization** tab in Postman.
2. Select **Basic Auth**.
3. Enter the employee's `email` as the username and their password.

---

## 📡 API Endpoints

### Address Endpoints (`/api/address`)

| Method | Endpoint | Description | Allowed Roles |
|:--- |:--- |:--- |:--- |
| `GET` | `/api/address/search` | Search addresses using `street`, `houseNumber`, or `zipCode` | `ADMIN` |
| `GET` | `/api/address/{id}` | Retrieve a specific address by ID | `ADMIN` |
| `POST` | `/api/address` | Create a new address (**Returns `201 Created`**) | `ADMIN` |
| `PUT` | `/api/address/{id}` | Update an existing address | `ADMIN` |
| `DELETE` | `/api/address/{id}` | Delete an address (**Returns `204 No Content`**) | `ADMIN` |

---

### Employee Endpoints (`/api/employees`)

| Method | Endpoint | Description | Allowed Roles |
|:--- |:--- |:--- |:--- |
| `GET` | `/api/employees` | List all employees | `ADMIN` |
| `GET` | `/api/employees/{id}` | Retrieve a specific employee by ID | `ADMIN` |
| `POST` | `/api/employees` | Create a new employee (**Validated Input**) | `ADMIN` |
| `PUT` | `/api/employees/{id}` | Update an employee's details (**Validated Input**) | `ADMIN` |
| `DELETE` | `/api/employees/{id}` | Delete an employee | `ADMIN` |
| `GET` | `/api/employees/find` | Advanced Specification Search by `firstName`, `lastName`, `email` | `ADMIN` |
| `POST` | `/api/employees/{empId}/missions/{mId}` | Assign a mission to an employee | `ADMIN` |
| `DELETE` | `/api/employees/{empId}/missions/{mId}` | Remove a mission from an employee | `ADMIN` |

---

### Department Endpoints (`/api/department`)

| Method | Endpoint | Description | Allowed Roles |
|:--- |:--- |:--- |:--- |
| `GET` | `/api/department` | List all departments | `ADMIN`, `USER` |
| `GET` | `/api/department/{id}` | Retrieve a specific department by ID | `ADMIN`, `USER` |
| `POST` | `/api/department` | Create a new department | `ADMIN` |
| `PUT` | `/api/department/{id}` | Update a department | `ADMIN` |
| `DELETE` | `/api/department/{id}` | Delete a department | `ADMIN` |
| `GET` | `/api/department/search` | Search departments by `id` or `name` | `ADMIN`, `USER` |

---

### Mission Endpoints (`/api/missions`)

| Method | Endpoint | Description | Allowed Roles |
|:--- |:--- |:--- |:--- |
| `GET` | `/api/missions` | List all missions | `ADMIN`, `USER` |
| `GET` | `/api/missions/{id}` | Retrieve a specific mission by ID | `ADMIN`, `USER` |
| `POST` | `/api/missions` | Create a new mission | `ADMIN` |
| `PUT` | `/api/missions/{id}` | Update an existing mission | `ADMIN` |
| `DELETE` | `/api/missions/{id}` | Delete a mission | `ADMIN` |
| `GET` | `/api/missions/employee/{employeeId}` | Retrieve all missions assigned to a specific employee | `ADMIN`, `USER` |

---

## 🚀 Getting Started

### Prerequisites

- Java 25 LTS
- PostgreSQL Database
- Maven

### Configuration

Update your database and Argon2 parameters in `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/workshop
    username: your_postgres_user
    password: your_postgres_password

app:
  security:
    crypto:
      argon2:
        memory: 65536      # 64MB RAM Cost (OWASP Recommendation)
        parallelism: 1     # Degree of parallelism
        iterations: 3      # Number of hashing passes

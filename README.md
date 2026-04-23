# 🚀 Employee Management System (JPA Relationship Lab)

A robust Spring Boot application designed to master advanced JPA Entity Relationships, Data Mapping, and Filtering Specifications.

## 🛠️ Tech Stack & Advanced Concepts

- **Java 25** & **Spring Boot 4.0.5**
- **Spring Data JPA** (Hibernate)
- **PostgreSQL**
- **Lombok** (Boilerplate reduction)
- **Jakarta Validation** (Input Sanitization)

## 🧩 Key Features & Patterns Implemented

- **Entity Relationships**: Deep dive into `@OneToOne`, `@OneToMany`, and `@ManyToMany` (Employee-Mission)
- **Data Transfer Objects (DTOs)**: Using Java Records for immutable and clean data transfer
- **Structural Mapping**: Manual Mappers for full control over Entity-DTO conversion, preventing circular references
- **Advanced Filtering**: Implementation of JPA Specifications for dynamic, complex searching (firstName, lastName, email)
- **Clean Controller Design**: Full RESTful CRUD operations with appropriate HTTP status codes

## 🗺️ Database Architecture

- **Employee ↔ Address**: Unidirectional One-to-One
- **Department ↔ Employee**: Bidirectional One-to-Many
- **Employee ↔ Mission**: Bidirectional Many-to-Many with a custom JoinTable

---

## 📡 API Endpoints

### Address Endpoints (`/api/address`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/address/search` | Search addresses using `street`, `houseNumber`, or `zipCode` query parameters |
| `GET` | `/api/address/{id}` | Retrieve a specific address by ID |
| `PUT` | `/api/address/{id}` | Update an existing address |
| `DELETE` | `/api/address/{id}` | Delete an address |

> ⚠️ **Note**: The create method in `AddressController` is missing a `@PostMapping` annotation, so it is currently not exposed as an endpoint.

---

### Employee Endpoints (`/api/employees`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/employees` | List all employees |
| `GET` | `/api/employees/{id}` | Retrieve a specific employee by ID |
| `POST` | `/api/employees` | Create a new employee |
| `PUT` | `/api/employees/{id}` | Update an employee's details |
| `DELETE` | `/api/employees/{id}` | Delete an employee |
| `GET` | `/api/employees/find` | Search employees by `firstName`, `lastName`, or `email` |
| `POST` | `/api/employees/{employeeId}/missions/{missionId}` | Assign a mission to an employee |
| `DELETE` | `/api/employees/{employeeId}/missions/{missionId}` | Remove a mission from an employee |

---

### Department Endpoints (`/api/department`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/department` | List all departments |
| `GET` | `/api/department/{id}` | Retrieve a specific department by ID |
| `POST` | `/api/department` | Create a new department |
| `PUT` | `/api/department/{id}` | Update a department |
| `DELETE` | `/api/department/{id}` | Delete a department |
| `GET` | `/api/department/search` | Search departments by `id` or `name` |

---

### Mission Endpoints (`/api/missions`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/missions` | List all missions |
| `GET` | `/api/missions/{id}` | Retrieve a specific mission by ID |
| `POST` | `/api/missions` | Create a new mission |
| `POST` | `/api/missions/{id}` | Update an existing mission |
| `DELETE` | `/api/missions/{id}` | Delete a mission |
| `GET` | `/api/missions/employee/{employeeId}` | Retrieve all missions assigned to a specific employee |

---

## 🚀 Getting Started

### Prerequisites

- Java 25 or higher
- PostgreSQL database
- Maven or Gradle (depending on your build tool)

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd employee-management-system
```

2. Configure PostgreSQL database in `application.properties` or `application.yml`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Build the project:
```bash
./mvnw clean install
```

4. Run the application:
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

---

## 📝 API Usage Examples

### Create an Employee
```bash
POST /api/employees
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "departmentId": 1,
  "addressId": 1
}
```

### Search Employees
```bash
GET /api/employees/find?firstName=John&email=john.doe@example.com
```

### Assign Mission to Employee
```bash
POST /api/employees/1/missions/5
```

---

## 🏗️ Project Structure

```
src/main/java/
├── controller/      # REST Controllers
├── service/         # Business Logic Layer
├── repository/      # JPA Repositories
├── entity/          # JPA Entities
├── dto/             # Data Transfer Objects (Records)
├── mapper/          # Entity-DTO Mappers
└── specification/   # JPA Specifications for filtering
```

---

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

### 🛣️ Future Roadmap
- [ ] Implement **Spring Security** for Role-based Access Control (RBAC).
- [ ] Add **Unit Testing** with JUnit 5 and Mockito.
- [ ] Integrate **Swagger/OpenAPI** for better documentation.
---

## 👨‍💻 Author

Built with ❤️ as a learning project for mastering JPA relationships and Spring Boot best practices.

---

## 💬 Let's Connect
This project is part of my learning journey. If you want to discuss the code, suggest improvements, or just chat about tech trends, you are more than welcome to join our Slack!

* [**Join our Slack Workspace**](YOUR_INVITE_LINK)

# User Service

Professional Spring Boot User Service implementing a REST/JSON API for user management.

## Features

- **CRUD Operations**: Create, Read, Update, Delete users.
- **RESTful API**: Standard HTTP methods and status codes.
- **Validation**: Input validation for data integrity.
- **Documentation**: Integrated OpenAPI (Swagger) UI.
- **In-Memory Database**: Pre-configured H2 database for zero-config startup.
- **Architecture**: Clean Layered Architecture (Controller -> Service -> Repository -> Model).

## Tech Stack

- Java 21
- Spring Boot 3.4.5
- Spring Data JPA
- H2 Database
- Lombok
- JUnit 5 & Mockito

## Getting Started

### Prerequisites

- Java 21 or higher

### Running the Application

This is a standard Gradle project.

```bash
./gradlew bootRun
```

The application will start on port `8080`.

### API Documentation (Swagger/OpenAPI)

Once the application is running, you can access the interactive API documentation at:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Database Console

Access the H2 Database console at:

[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **User Name**: `sa`
- **Password**: `password`

## Running Tests

Run the full test suite (Unit & Integration):

```bash
./gradlew test
```

# Fivvy Backend Challenge

## Introduction

This is a solution for the Fivvy Backend Challenge [https://gitlab.com/fivvy_challenges/fivvy_backend_challenge]

## Considerations and Assumptions

- The application is built using Java 17 and Spring Boot 3.1.0
- The application is built using Maven
- The database is PostgreSQL 15.3
- The application can be run using Docker
- The application is configured to run on port 8081.
- I have created a bundle inside the resources folder for internationalization. Right now the only language supported is English.
- I have created a custom exception handler to handle all the exceptions and return a proper response to the client.
- The user_id related to the acceptance entity is not being linked to a user entity given that the user entity is not part of the challenge.
- The optional parameter for the text in the disclaimer LIST endpoint is a string that can be included in the Disclaimer's text but not necessarily the same.
- I have not included unit tests because of the time. I have included integration tests instead.
- The application is configured to create the database and the tables on startup and drop them on shutdown.
  - This is an attributed that can be changed in the application.properties file [spring.jpa.hibernate.ddl-auto]
  - It is configured this way because it is just a challenge and this way the Database can be used to show the funcionality and run the tests at the same time.
  
## Prerequisites
- Docker

## How to run the application with Docker
- `docker compose up`
  - This has to be excecuted inside the folder where the docker-compose.yml file is located [main folder].

## How to compile/test without Docker

### Prerequisites
- Maven 3.8.3
- Java 17
- PostgreSQL 15.3

### Compile

`mvn clean package -DSERVER=localhost -DDATABASE_USERNAME={postgres_user} -DDATABASE_PASSWORD={postgres_password} -DDATABASE_PORT={postgres_port}`

### Test

- The application coverage is measured with Jacoco and has a 100% test coverage.

`mvn clean test -DSERVER=localhost -DDATABASE_USERNAME={postgres_user} -DDATABASE_PASSWORD={postgres_password} -DDATABASE_PORT={postgres_port}`

## Open API Documentation

The Open API documentation is available at `https://app.swaggerhub.com/apis/AndresJalife/solution-api/1.0.0`

The Open API yaml can be found in this repository by the name `api-documentation.yaml`.
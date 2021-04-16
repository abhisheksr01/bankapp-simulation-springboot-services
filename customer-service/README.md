# Spring Boot Demo Customer Service

This is simple Customer Service built using Spring Boot framework.

### Dependencies

- Java8
- Postgres Database
- Docker (Optional, until you wish to build image locally)

### Starting application locally

The Spring Boot Customer Microservice uses Postgres Database as storage layer.
Hence we should have the DB setup before standing the application else the application start will fail.

Two ways to install Postgres

1. Downloading the installer, follow steps [here](https://www.postgresqltutorial.com/install-postgresql/)
2. If you have docker installed the easiest way is to pull an official postgres image and run.

- Pull docker image (latest default)

  ```ssh
  docker pull postgres
  ```

- Optional, creates a local persistent volume to store the data even though the container is removed.

  ```ssh
  mkdir -p $HOME/docker/volumes/postgres
  ```

  To start the container

  ```ssh
  docker run --rm --name postgres -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data postgres
  ```

  The above command follows below syntax:

  ```ssh
  docker run --rm --name [CONTAINER_NAME] -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data postgres
  ```

- Only if you have not executed the above command run to start the container

  ```ssh
  docker run --rm --name postgres -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres
  ```

- Check the running container

```
docker ps
```

- Clone this repo and open in your favorite IDE. Then update the [application.yml](/src/main/resources/application.yml) with the postgres password.

- To start the application open [CustomerApplication.java](/src/main/java/com/service/customer/CustomerApplication.java) and in your IDE start run the application.

- If you wish to start the application from CLI run below command:

  ```ssh
  ./gradlew bootRun
  ```

### Accessing application locally

The application default starts at port 8081 and exposes below two http endpoints:

- GET - http://localhost:8081/customer/search/{CUSTOMER_ID}
  Response:
  ```json
  {
      "firstName" : "Abhishek"
      "surname"   : "Rajput"
  }
  ```
- POST - http://localhost:8081/customer/create

  Request:

  ```json
  {
      "firstName" : "Abhishek"
      "surname"   : "Rajput"
  }
  ```

  Response:

  ```json
  {
      "id"        : 1000,
      "firstName" : "Abhishek"
      "surname"   : "Rajput"
  }
  ```

### Features

- Swagger Integration

  For the purpose of API document we have swagger integrated can be accessed by using below url :
  http://localhost:8081/customer/swagger-ui.html

- Actuator

  For health check of the application :
  http://localhost:8081/customer/actuator/health

- Cucumber Integration for BDD:

  The cucumber is fully integrated for you to use and also an additional feature being used to share the context with the adjoining the step definitions.

- Postgres embed Database:

  In order to test the Repository and be able to perform BDD tests without creating an dependency on the actual DB.

- Jacoco

  For test code coverage, at the moment the report shows 100% code coverage.

- Mapstruct

  An easy to manage dependency and very helpful to map difference bean objects in respective layers.

- Lombok

  Helps to reduce boilerplate code.

If you wish to learn more about some best practices click [here](https://github.com/abhisheksr01/companies-house-microservice-template)

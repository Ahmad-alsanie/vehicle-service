## Vehicle Service
A service that determines if a certain car/vehicle is within a polygon/zone

### Table of Contents
- [Setup & Run Instructions](#Setup-&-Run-Instructions)
- [API documentation](#API-Documentation)

### Setup & Run Instructions
Detailed steps to get your development environment running:

#### Prerequisites:
- Java JDK 17 or higher
- Maven

#### Clone the Repository
```shell
git clone https://github.com/Ahmad-alsanie/vehicle-service.git
cd vehicle-service
```

#### Build & Run the Service
```shell
mvn clean install
```

```shell
 mvn spring-boot:run
```


### API Documentation
Navigate to [Swagger API documentation](http://localhost:8080/swagger-ui/index.html) to view swagger documentation of the endpoints.

| API            | supported methods | onSuccess | onFailure |
|----------------|-------------------|-----------|-----------|
| vehicles       | GET               | 200       | NONE      | 
| polygons/{id}  | GET               | 200       | NONE      | 
| polygons       | GET               | 200       | NONE      | 


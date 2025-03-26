# Running the service

To start the application, navigate to the project root directory and run:

``` mvnw spring-boot:run ```

Then run the following curl requests in the terminal:

```curl -X POST http://localhost:8080/tax -H "Content-Type: application/json" -d "{\"traderId\": 1, \"playedAmount\": 5, \"odd\": 3.2}"```

```curl -X POST http://localhost:8080/tax -H "Content-Type: application/json" -d "{\"traderId\": 2, \"playedAmount\": 5, \"odd\": 3.2}"```

```curl -X POST http://localhost:8080/tax -H "Content-Type: application/json" -d "{\"traderId\": 3, \"playedAmount\": 5, \"odd\": 3.2}"```

```curl -X POST http://localhost:8080/tax -H "Content-Type: application/json" -d "{\"traderId\": 4, \"playedAmount\": 5, \"odd\": 3.2}"```

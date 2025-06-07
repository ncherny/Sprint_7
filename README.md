# Sprint_7

## Description
Tests for API of scooter ordering service https://qa-scooter.praktikum-services.ru/
### Tests for creation of courier account (POST /api/v1/courier)
#### Positive tests:
- with sending firstName field
- without sending firstName field
#### Negative tests:
- without sending password field
- without sending login field
- using a login already taken by another user
### Tests for login into courier account (POST /api/v1/courier/login)
#### Positive tests:
- login using valid data
#### Negative tests:
- without sending password field
- without sending login field
- with valid login, but incorrect password
- with a login for a nonexistent account
### Tests for creating an order (POST /api/v1/orders)
#### Positive tests:
- with different combinations of possible color choices
### Tests for receiving a list of orders (GET /api/v1/orders)
#### Positive tests:
- List of orders is present in response
## Console command to launch tests
```mvn clean test```
## Console command to open allure tests overview
```mvn allure:serve```

## Used technologies
- JUnit 4.13.2
- Rest-assured 5.3.0
- AspectJ 1.9.7
- Allure 2.20.0
- Gson 2.10
- jackson 2.14.0g
- Lombok 1.18.22
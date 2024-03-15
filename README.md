# Bank-java-API
___
## About the API
### An API for bank management. It is built with Java, Spring Boot, and Spring Framework. A toy-project to strengthen my knowledge about these technologies.
### The API main URL is ```/bank-api```.

## Features
+ Save a client: ```POST/bank-api/save```
+ Update a client (by id): ```PUT/bank-api/update/1```
+ Get a list of existing clients: ```GET/bank-api/clients```
+ Delete a client (by id): ```DELETE/bank-api/delete/1```
+ Get a balance of a client (by id): ```GET/bank-api/balance/1```
+ Take money from a client (by id): ```PUT/bank-api/takemoney/1```
+ Give the client money (by id): ```PUT/bank-api/putmoney/1```
+ Get a list of all completed operations: ```GET/bank-api/operations```
+ Get all operations of a client after the date (by id): ```GET/bank-api/operationsafter/1```
+ Get all operations of a client before the date (by id): ```GET/bank-api/operationsbefore/1```
+ Get all operations of a client between two dates (by id): ```GET/bank-api/operationsbetween/1```
+ Transfer money from one client to another: ```PUT/bank-api/transfermoney/1/2```


## Details
```POST/bank-api/save```

```PUT/bank-api/update/1```
#### Body(Json):
```
{
    "balance": 777
}
```

___

```PUT/bank-api/takemoney/1```

```PUT/bank-api/putmoney/1```

```PUT/bank-api/transfermoney/1/2```
#### Body(Text):
```
777
```

___

```GET/bank-api/operationsafter/1```

```GET/bank-api/operationsbefore/1```
#### Body(Text):
```
2024-03-12T13:40:08
```

___

```GET/bank-api/operationsbetween/1```
#### Body(Text):
```
2024-03-11T13:04:20 2024-03-14T13:04:20
```

## Technologies used and studied
+ **Java 21**
+ **Spring boot 3.2.3**
+ **Maven**
+ **PostgreSQL 42.7.2**
+ **pgAdmin 4**
+ **web.postman.co**


# Existing database tables
## Clients
![img.png](src/main/resources/photos/clients.png)
## Operations
![img_1.png](src/main/resources/photos/operations.png)
___
## Developed by [Bannikov Danil](https://github.com/DanilBannik0v)


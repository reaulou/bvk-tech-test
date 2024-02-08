# Product Management Microservice
This is my result for the Buana Varia Komputama technical test. This application is a service that can manage products that are stored inside a in-memory database.


## Tech-Stack used:
- Java 17
- Maven 3.8
- Spring Boot 3.2.2
- H2 In Memory Database

To connect to database you can go to: http://localhost:8080/h2-ui \
Fill the login form according to the following:
- JDBC URL: jdbc:h2:mem:testdb
- User Name: sa
- Password: admin

## Operations
The APIs that can be called are the simple CRUD(Create Read Update Delete) and Order Product:

### Order Product
> POST \
> /product/order

Request Message:
```json
{
    "message":{
        "id" : 3,
        "quantity" : 5
    }
}
```
Response Message:
```json
{
    "returnCode": {
        "code": "00",
        "desc": "success"
    },
    "message": "Operation success"
}
```

### Add Product
> POST \
> /product/add

Request Message:
```json
{
    "message":{
        "name" : "pencil",
        "price" : 2500,
        "quantity" : 63
    }
}
```
Response Message:
```json
{
    "returnCode": {
        "code": "00",
        "desc": "success"
    },
    "message": "Operation success"
}
```

### Get All Product
> GET \
> /product/get-all

Response Message:
```json
{
    "returnCode": {
        "code": "00",
        "desc": "success"
    },
    "payload": [
        {
            "quantity": 63,
            "price": 11000,
            "name": "pencil",
            "id": 1
        },
        {
            "quantity": 12,
            "price": 23500,
            "name": "book",
            "id": 2
        },
        {
            "quantity": 28,
            "price": 17500,
            "name": "ruler",
            "id": 3
        }
    ],
    "message": "Operation success"
}
```

### Get Product by Id
> GET \
> /product/get/{Id}

Response Message:
```json
{
    "returnCode": {
        "code": "00",
        "desc": "success"
    },
    "payload": {
        "quantity": 28,
        "price": 17500,
        "name": "ruler",
        "id": 3
    },
    "message": "Operation success"
}
```

### Update Product by Id
> POST \
> /product/update

Request Message:
```json
{
    "message":{
        "id":4,
        "name" : "ruler",
        "price" : 750000,
        "quantity" : 11
    }
}
```

Response Message:
```json
{
    "returnCode": {
        "code": "00",
        "desc": "success"
    },
    "message": "Operation success"
}
```

### Delete Product by Id
> POST \
> /product/delete

Request Message:
```json
{
    "message":{
        "id" : 1
    }
}
```

Response Message:
```json
{
    "returnCode": {
        "code": "00",
        "desc": "success"
    },
    "message": "Operation success"
}
```

## Other responses

### Bad Request
This can be returned by all of the APIs that send a request body. It will trigger the request json has wrong format e.g. missing curly brackets.
```json
{
    "returnCode": {
        "code": "70",
        "desc": "Bad Request"
    }
}
```

### Product not Exist
This is returned when a request is made to retrieve or alter a nonexistent product. Like when updating or ordering a product with a product Id that doesn't exist.
The success just means the request ran with no errors.
```json
{
    "returnCode": {
        "code": "60",
        "desc": "success"
    },
    "message": "Product not exist"
}
```

### Product quantity not sufficient
This is only returned when ordering products. This returns when product quantity is not enough to satisfy ordered quantity. The success just means the request ran with no errors.
```json
{
    "returnCode": {
        "code": "61",
        "desc": "success"
    },
    "message": "Insufficient quantity"
}
```

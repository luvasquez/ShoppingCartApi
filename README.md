## Environment:
- Java version: 1.8
- Maven version: 3.*
- Spring Boot version: 2.7.5.RELEASE


## Data:
Example of a order data JSON object:
```
{
    "id": 1,
    "orderDescription": "status: new order in progress...",
    "client": {
        "id": 1,
        "name": "Luis Eduardo",
        "lastName": "Vásquez Villalta",
        "dui": "12345678-9",
        "email": "vasquez.eduardoluis@gmail.com",
        "address": "Lost city, San Salvador +503 El Salvador"
    },
    "details": [
        {
            "idProduct": 2,
            "productDetail": "Mens Casual Premium Slim Fit T-Shirts ",
            "quantity": 2,
            "amount": 44.6
        }
    ]
}
```


## Commands
- run:
```bash
mvn clean package; java -jar target/app-1.0-SNAPSHOT.jar
```
- install:
```bash
mvn clean install
```
- test:
```bash
mvn clean test
```

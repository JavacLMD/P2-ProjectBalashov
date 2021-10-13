# Cassandra Microservice

This is the Item Database Service. In charge of anything item related.

## Endpoints
- GET "/items": Returns all items </br>  `curl localhost:8080/items`
- POST "/items": Creates an item from the passed JSON string </br>  `curl -X POST -H 'Content-Type: application/json' -d '{JSON}' localhost:8080/items`
- GET "/items/byId/{id}": Returns the item with corresponding ID </br>  `curl localhost:8080/items/byId/{id}`
- GET "/items/byCategory/{category}": Returns all items with the specified category </br>  `curl localhost:8080/items/byCategory/Mass%20Effect`
- GET "/items/byName/{name}": Returns all items with the specified name </br>  `curl localhost:8080/items/byName/{name}`
- PUT "/items/update/{id}": Creates an item from the passed JSON string </br>  `curl -X PUT -H 'Content-Type: application/json' -d '{JSON}' localhost:8080/items/update/{id}`

Example JSON: 
```json
{
        "id": 91508,
        "name": "Normandy SR3",
        "price": 2333.5,
        "category": [
            "Mass Effect",
            "Spaceship"
        ]
    }
```

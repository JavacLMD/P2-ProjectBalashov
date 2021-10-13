Feature:KarateTest


Scenario: Testing valid GET endpoint
Given url 'http://localhost:8081/orders/111333'
When method GET
Then status 200

Scenario: Testing the exact response of a GET endpoint
Given url 'http://localhost:8081/orders/111333'
When method GET
Then status 200
And request {"id":111333,"item_id":30,"price":300.0}


Scenario: Testing all valid GET endpoints
Given url 'http://localhost:8081/orders/all'
When method GET
Then status 200

Scenario: Testing a POST endpoint with request body
Given url 'http://localhost:8081/orders/111999'
And request {"id":111999,"item_id":9,"price":100.0}
When method POST
Then status 200
And match $ contains {id:"#notnull"}



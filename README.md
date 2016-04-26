# MyRetail-RestFullService
TargetCaseStudy


myRetail RESTful service

myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 
The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 
Your goal is to create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define, but try to follow some sort of logical convention.
Build an application that performs the following actions: 

Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 
Example product IDs: 15117729, 16483589, 16696652, 16752456, 15643793) 
Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}
Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from api.target.com, but let’s just pretend this is an internal resource hosted by myRetail)  o Example: https://api.target.com/products/v3/13860428?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz
Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response.  
Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store.  


Key Notes:
--> Application is build using STS IDE
--> Mongo DB is used as DB

Steps to Build the application:
-----------------------------------

1) TargetCaseStudyApplication class is the main class  with @springbootApplication annotation
2) Right Click and Run as SpringBoot application. This will start the GET and PUT services in localHost port 8080
3) There are two Junit Test cases available to Check the Pojo structre is intact. It is available in TargetCaseStudyApplicationTests class
4) Unit Test and Error Scenario Results are captured in MyRestFull Service Case Study Unit Test Results and Error Messages.doc

Dependacy Jars:

gson-2.2.2.jar: This is used for Map<String, String> to JSON conversation in PUT service.

Use https://github.com/Bonythomasv/MyRetail-RestFullService.git to clone the repository


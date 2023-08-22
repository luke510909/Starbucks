## Starbucks Storefront Cloud Deployment Project
### This project is simulating the Starbucks storefront, with a comprehensive system showcasing various components and functionalities. It includes a Cashier's App for order processing, support for admin logins, in-store order management, scalable cloud deployment on Google Cloud Platform (GCP), integration with cloud databases, and a Starbucks API for mobile and store-front applications. The project highlights technologies like Spring MVC, MySQL, RabbitMQ, and Kong API Gateway. 

## Scalable Cloud Deployment on GCP
### Internal Load Balancer for Starbucks API behind Kong API Gateway
![image](https://github.com/luke510909/Starbucks/assets/85315948/3eb48567-e731-461d-82b7-905280253e90)
###  External Load Balancer as Ingress for Cashier's App 
![image](https://github.com/luke510909/Starbucks/assets/85315948/af13a729-3b27-4f30-993b-6fa5b3f6c833)

#### 
#### 
####
***
## Starbucks REST API
### The integration between the API and GKE (Google Kubernetes Engine) pod allows for seamless communication and data synchronization between the Starbucks cashier system and the backend infrastructure. In this case, the system logs in the GKE pod capture and display the same orders that were placed through the Starbucks cashier.
### By leveraging the API, the Starbucks cashier system can transmit order information to the GKE pod, which acts as the backend processing unit. This integration ensures that all orders are recorded and made available for further processing and ### analysis. As a result, the GKE pod's logs serve as a valuable source of information, providing a detailed record of the orders received.
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/cd440e3c-d7f4-4665-bc5c-eca78df101d6)
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/9645e7ed-7c9a-4f0d-9031-8d80ffd243f3)

###

## Cloud Deployments

### How does the deployment scale?  Can it handle > 1 Million Mobile Devices? Explain.

It scales utilizing load balancing, which distributes incoming requests across multiple instances of the application. This helps handle potential high volume of traffic from a large number of devices. Since we are using Kubernetes, horizontal scaling is there which involves adding more instances, and pods to the infrastructure to handle any demand. By doing this, we can accommadate a massive amount of devices by distributing the workload across multiple instances. Kong and Ingress enable efficient traffic routng and load balancing, and RabbitMQ workers facilitate asynchronous processing of tasks. Since we are using a combination of GKE, Kong, and ingress, I do believe its possible to handle over 1 million mobile devices.

# Technical Requirements
## Cashier's App
Web rendereding is performed in resources/templates, utilizing thymeleaf, allows to write html templates with special attributes
which is processed by the server to generate dynamic content
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/9737e588-5372-4d5d-992b-6080b09aeca9)

#### the cashiercontroller processes json responses from the api through the resourceurl, which passes it to the view
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/27f72f53-bb7e-4e14-a692-fa098c3ef015)
#### utilized the @Value annotation to inject values from environment variables such as docker compose or the deployment.yaml
#### allows for specifying a placeholder expression that references a property value
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/0c87a6a1-7ee1-44bd-b5f4-6e659c739bd7)
#### Rest client has custom query methods to utilize spring jpa to automatically generate code, which allows for CRUD operations
#### utilizes spring jpa for managing the starbucksorders, previously it was utilizing a hashmap which would not work with GKE
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/ec1e5be2-0b43-4678-b44a-36c1b793c3a1)
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/60b74789-d7f8-46a5-887d-edde70c1a1d6)

### Support Admin Logins for Starbucks Employees
#### defines users, information about the user to spring security for authentication and authorization. Stores user information in database, does not store it in memory.
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/eb21cbb5-c2ff-44ce-86fc-327b8cfa305f)
#### New account registration If there are no validation errors, it checks if the user is already registered by calling isUserPresent() method of the UserService. If the user is already registered, it adds an appropriate error message to the model and redirects back to the registration page with the userexists parameter set to true. If the user is not already registered, it sets the user's role as Role.USER, saves the user using the saveUser() method of the UserService, adds a success message to the model, and redirects to the login page.
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/cf8377ab-c539-4522-aeba-350a8608f0f4)
### Support In Store Order Processing

#### in cashier controller, sends api request to place and retrieve the orders
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/a9a46c4e-4166-4b7c-a0de-48f181470357)
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/d9ff86f4-1db6-4efc-afee-8c5882a30160)


## Implementation Uses Required Cloud Databases
### MYsql 8.0 configuration
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/bddea77b-9e70-4304-988f-43674d0341fc)
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/a5b7d619-f926-423a-8b8a-6e115fe09c67)

#### host matches my cloudsql private ip, as well as the user
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/e9366441-a1f4-4bfb-9b21-51c57694e1b5)
## RabbitMQ
### Must use GKE RabbitMQ Operator
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/4861edc8-4615-408f-8db8-5f354b815042)
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/1c12d59c-90a1-42b3-a3a1-2f02b7bc6f0c)

![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/2daf9f0b-2983-4fe6-8125-b64fc4bc543f)
same endpoint ip to access the rabbitmq console
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/18b52a5d-36ca-4ab9-8c57-20e1d2d7588d)

### Extends the Starbucks API to support async order processing (to use RabbitMQ)
### utilizes the spring amqp interface, an open standard for passing business messages between applications or organizations.
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/96423d96-0913-4581-94fc-a0e991876931)
### creates a new queue named "cashier" if it doesn't already exist. This queue will then be used to send order information using rabbit.convertAndSend(queue.getName(), orderNumber);. The convertAndSend method will convert the orderNumber into a message and send it to the queue.
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/d537403b-f8b5-4786-a424-83427af54c7b)
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/40bfaf4c-5960-4ba0-8080-9a36638720e3)
## Starbucks API for Mobile App and Store Front
### Deployed with Kong API Gateway with API Key Authentication
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/ca057ae3-4919-4aa8-b8c2-2466292ef9d5)
### doesn't allow curling without api key, but works with api key
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/a9b848b2-1a5a-46bb-8bf8-8cdc23d3b65d)
### Implement RabbitMQ Check Order Status for "Drinks Available"
### Async Request API to "Make the Drink" once Order has been Paid (i.e. put request into a Queue)
#### rabbit.convertAndSend is taking the orderNumber, converting it into a message, and then sending it to the queue once an order has been paid
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/49f719a5-fc0e-4e6c-a2a7-ef6151aa78b5)
#### Async Check Order Status API to "Check Status of Drink" in the Starbucks Database
#### defines the Starbucks order to check the status of drink to the order table in the Starbucks database
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/268de6be-3fc7-4f62-bc61-c9dc7c399c95)
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/36277881-396f-4c68-acd9-3e984fbe1656)

### Background Worker Job  to pick up Orders and Make Drinks

#### Responsible for processing Starbucks cashier orders using RabbitMQ for message queuing. Inside the method, it logs the order number as received. It then simulates busy work by sleeping for 60 seconds using Thread.sleep(). It then processes the order by updating its status to "FULFILLED" after simulating some busy work, and saves the updated order to the database.
![image](https://github.com/luke510909/Starbucks/assets/85315948/af560f3e-170a-4e5e-9ede-2cf1e9012a7f)






## Starbucks Storefront Cloud Deployment Project
### This project is simulating the Starbucks storefront, with a comprehensive system showcasing various components and functionalities. It includes a Cashier's App for order processing, support for admin logins, in-store order management, scalable cloud deployment on Google Cloud Platform (GCP), integration with cloud databases, and a Starbucks API for mobile and store-front applications. The project highlights technologies like Spring MVC, MySQL, RabbitMQ, and Kong API Gateway. 

***
# Overall Architecture Diagram of the Cloud Deployment
![image](https://github.com/luke510909/Starbucks/assets/85315948/7d95684a-c76a-47e1-8891-1df456dd2af5)
***
## Cashier's App
#### New login page, with user registration and support
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/2c756585-789b-4d6b-8723-d0672893061c)
####  Used  in conjunction with spring mvc to handle user authentication and authorization. The th:action, th:placeholder, th:text, and th:if attributes are used to dynamically populate the form fields, button text, and display error messages.
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/86579c95-04e5-4922-aa11-6831dd2efb32)
#### added additional dropdown menu from the initial only being a register, also added sign out button
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/2272b53b-3551-4de4-9000-6fe732ef0989)
### th:href="@{/styles.css}": This Thymeleaf attribute is used to link the CSS file, styles.css. The @{} syntax is used to link to the URL of the resource on the server.
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/ad400a2f-6b62-4526-8a23-1c56e0c09971)
### th:action="@{/logout}" and th:action="@{/user/starbucks-cashier}": The th:action attribute is used to specify the logout action. When the form is submitted, a POST request is sent to the specified URL.
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/f383004d-9a1f-4066-b0b4-fcb351a08ef5)
### referenced the way the register form field is generated to create the custom order fields
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/cdf2d4bd-d06d-433b-b642-ed0082091446)
### information retrieval
#### obtains order information through sending an api request to the specified button. Try and catch fields are added to every action to support any error handling
### if action is place order, it performs a post request to the api
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/ac9c8b8d-1f3d-4ee6-98e5-5cf74c38e206)

![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/cb84fec6-2c7e-4f45-b9b0-f0abee94b92d)
### if action is get order, it performs an api get request
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/044059b1-811e-47e8-9af2-24a1d0543a75)

![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/6bacbd20-6c3d-47c5-98d4-99d0ab52e7f4)

### if action is clear order, it performs an api delete request
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/bbe34546-8711-4095-bc86-aaa85d6d9d71)

![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/86ac2823-0470-458a-b9e0-f15e73082c94)

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
***
## Cloud Deployments
### Design Notes on GitHub an Architecture Diagram of the overall Deployment.
### How does your Solution Scale?  Can it handle > 1 Million Mobile Devices? Explain.

It scales utilizing load balancing, which distributes incoming requests across multiple instances of the application. This helps handle potential high volume of traffic from a large number of devices. Since we are using Kubernetes, horizontal scaling is there which involves adding more instances, and pods to the ifrastructure to handle any demand. By doing this, we can accommadate a massive amount of devices by distrubuting the workload across multiple instances. Kong and Ingress enable efficient traffic routng and load balancing, and RabbitMQ workers facilitate asynchronous processing of tasks. Since we are using a combination of GKE, Kong, and ingress, I do believe its possible to handle over 1 million mobile devices.
***
***
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
#### Rest client is not as is, removed hashmap implementation in replacement with lists, custom query methods to utilize spring jpa to automatically generate code, which allows for CRUD operations
#### utilizes spring jpa for managing the starbucksorders, previously it was utilizing a hashmap
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

## Scalable Cloud Deployment on GCP
### both External Load Balancer as Ingress for Cashier's App and Internal Load Balancer for Starbucks API behind Kong API Gateway
![image](https://github.com/luke510909/Starbucks/assets/85315948/af13a729-3b27-4f30-993b-6fa5b3f6c833)

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

### Extend the Starbucks API to support async order processing (to use RabbitMQ)
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

### Will need a Background Worker Job (i.e. Spring Scheduler) to pick up Orders and Make Drinks
#### in starbucks cashier worker
#### Responsible for processing Starbucks cashier orders using RabbitMQ for message queuing. Inside the method, it logs the order number as received. It then simulates busy work by sleeping for 60 seconds using Thread.sleep(). It then processes the order by updating its status to "FULFILLED" after simulating some busy work, and saves the updated order to the database.
![image](https://github.com/luke510909/Starbucks/assets/85315948/af560f3e-170a-4e5e-9ede-2cf1e9012a7f)

#### once paid for order, as you can see cashier worker pod gets received worker
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/a073a41e-155b-4844-8e7b-4ae479f538b3)
#### and as you can see in the timestamp, exactly after a minute it gets processed
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/6430ae2c-b722-4ad6-b008-2a3162cc6e3f)
### Background Worker should be a "Single Resilient POD" which auto restarts on crashes
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/d892a46e-897a-4fa6-ad73-e2e7fe621164)





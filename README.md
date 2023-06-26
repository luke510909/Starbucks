# CMPE 172 Project Journal
## Day 1 5/3/2023
Ported the node js app to spring mvc, aka midterm, currently does not process json responses from api, but has the look and feel of the node js app.
#### in order.java, added necessary jpa annotations such as @Id and @Table(name = "order"), which makes it so that
#### instances of the order class will be mapped to the database table 'order', and since it has @Id, its primary key
#### for the order table and its value will be automatically generated by jpa
![image](https://user-images.githubusercontent.com/85315948/236607676-ec3aba5e-d176-49cd-944f-e5ce2ffa304d.png)
#### doesnt retrieve orders from api, randomly generates order using this method
![image](https://user-images.githubusercontent.com/85315948/236607696-4e134376-5580-4019-acc4-670cda4f087a.png)
#### JPA repository, which utilizes the spring crudrepository interface which provides basic create, read, update, delete
#### operations, with the @Repoistory annotation, it would be detected by spring during compiling,
#### the method findbyregisteractive returns a list of orders which comply with the passed register and active parameters
![image](https://user-images.githubusercontent.com/85315948/236607888-b64725fa-c50a-4f20-ae4a-643922fbeacf.png)

##### current log in page
![image](https://user-images.githubusercontent.com/85315948/236127537-52b93bf9-6ad8-453f-b71d-58c5e8aa3783.png)
##### places order at store
![image](https://user-images.githubusercontent.com/85315948/236127587-e66981e8-f97a-406a-982a-fabaa0f5e611.png)
##### same order is visible in database
![image](https://user-images.githubusercontent.com/85315948/236127700-6fefe40f-41be-4ce0-acd1-526ff0094db9.png)
##### can run both locally and on docker
![image](https://user-images.githubusercontent.com/85315948/236127289-f491c108-e58e-4a1b-97e3-728314dfc0a2.png)
***
## Day 2 5/4/2023
#### No longer do orders come from randomly generated orders
#### comes directly from api and stores the respective order in the database,kong not yet implemented
#### in cashiercontroller, still uses an order object, but obtains an api response rather than the hardcoded random orders
![image](https://user-images.githubusercontent.com/85315948/236608033-403b1ac3-0f57-4492-893c-5b980f7c2fa6.png)

#### ping endpoint example
![image](https://user-images.githubusercontent.com/85315948/236390825-e4eb193f-721b-4373-9e8b-4b36086798dc.png)
#### all the orders from api are listed here when clicked order in two stores
![image](https://user-images.githubusercontent.com/85315948/236393794-23085494-6a56-4283-b9e5-d196ee02605f.png)

#### same two orders from different stores are saved in database
![image](https://user-images.githubusercontent.com/85315948/236393715-d8ba2cb9-4161-4d6d-920a-43b544d73541.png)
## Day 3 5/5/2023
#### removed all redis implementation that was from the midterm to make it compatible with the final
#### no longer in application.properties, pom.xml, or docker-compose
![image](https://user-images.githubusercontent.com/85315948/236608092-bcac7d5d-7439-4ec2-a077-f393d05d61e3.png)
## Day 4 5/6/2023
#### No longer does it come directly from api, but utilizes kong api, also attempting to remove all midterm content related to mysql
#### added kong
![image](https://user-images.githubusercontent.com/85315948/236662158-e3afcf24-a53e-4cfe-9b69-852f63c972fd.png)

#### orderrepository deleted, order.java changed, application.properties, as well as cashiercontroller.
![image](https://user-images.githubusercontent.com/85315948/236661739-1c128339-3be8-4b0d-a09b-c7cbbc6dedfe.png)
#### have kong running in container as well as api, cashier client still runs locally havent deployed to docker yet
![image](https://user-images.githubusercontent.com/85315948/236661916-2523e1eb-6e48-447a-97b3-9cb8f3ec02f1.png)

#### the logic with cashiercontrller still needs to change, but have a majority of it finished and will attempt to resolve it soon.
![image](https://user-images.githubusercontent.com/85315948/236661967-2e987c5d-7b45-4ff9-90b2-55ca7bcfffbf.png)
***
## Day 5 5/7/2023
### api client has no capability with kong.
![image](https://user-images.githubusercontent.com/85315948/236747985-9f6b200a-c2aa-4674-9e28-34cc0796dd3c.png)
### currently if you ping, it would result in this error:
![image](https://user-images.githubusercontent.com/85315948/236748051-27260122-0d2e-4830-a99b-300ca1e78111.png)
### added new lines to application.properties including api key
![image](https://user-images.githubusercontent.com/85315948/236748739-45d883bb-5750-483d-9191-5b31fff94b37.png)
### in consolecontroller, each resourceurl now takes in the api key to be compatable withe the kong configuration
![image](https://user-images.githubusercontent.com/85315948/236749143-6f630161-2ba3-4d4d-85a8-0406bfcd95bc.png)
### now works with kong implementation
![image](https://user-images.githubusercontent.com/85315948/236748525-ee53a532-3d24-473d-9a4b-ac5878fd1c06.png)
## Day 6 5/14/2023
### client now has custom drink selection feature
### no longer does it come randomly from api
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/7c44639f-42a0-4f48-9e56-9e413d7dc881)
when placing a get on postman, same custom order appears:
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/5758efcb-0a73-4fde-b01e-f17951448c54)
simply adjusted the getneworder from order.java to pass in a httpservlet request, so when the user submits the order the code
retrieves values from the request object using the getparameter method.
### added similar html code like the register to add the custom drinks
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/43ee8172-a52f-4a3a-8332-d2160bccd176)
### simply added the request parameter to the Order order request object in cashiercontroller to make it compatible with this implementation
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/2a3963bb-dc8a-4ade-b716-6114f3ecfab2)
## Day 7 5/15/2023
### attempting to implement multiple user implmeentation, with assistance from gumball3.4
### still incomplete, but current progress is almost done with correctly implementing it
### added login folder to spring cashier folder as well as config for all the websecurityconfig
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/d4532c3b-1c69-4c24-88e8-eaae1849b9ae)
### also modified the templates to include admin folder, auth folder, and user folder
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/acee06ca-ce6d-42b8-baf7-5393cf14dd34)
### in the added dashboard.html under the user folder, it is a thymeleaf tmeplate for user dashboard page in the app. It displays the authenticated users name, and gets the currently authenticated user's details
### there is also a button which can log the user out
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/8248c4bf-70d0-4286-bce4-a346e965a074)
### the homepage.html serves as the main page. For authenticated users, the applications displays the username and roles, and depending on the user's authority, it provides a link to their corresponding dashboard. An authenticated user also has the option to log out.
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/50453c7b-aeb3-4fb9-b270-0c8b0c488bef)
## Day 8 5/20/2023
### added starbucks worker
### worker receives the payments in the queue from cashier
### fixed cashiercontroller to allow use values passed in for api and the host, rather than hardcoded
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/162f1d1f-3087-4ec1-a9b7-f1dd317f7ead)
### utilized inject values from dockercompose into the corresponding variables.
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/f5d10010-6179-4ee9-ad55-6d633ae2f438)
### previously had my resourceurl's hardcoded
### added a cashierworker, so it receives the order from the rabbitmq Queue and the status then becomes from paid to fulfilled
grabbed an order first
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/144e39c2-f0bd-40f8-93de-6b85f2ef3c10)
then i paid for it, as you can see the status:
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/b4963b01-22f3-4371-bb8d-caa2a745c225)

as you can see, no more orders in the queue
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/08b6a249-c088-48f4-a500-2128f0feca6e)
the order changed to fulfilled
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/6230ce8e-5ba8-4114-88f5-2b24e05efdb7)
## Day 9 5/21/2023
### changes to the api. Had to remove every instance of hashmaps being used and replace it with the List from the order repository. Was simple in theory, but very complicated to locate and alter everything to be compatible with the repository list. Took a while to get it fully configured. Can now delete orders from the database when the cashier clicks clear order.
### utilize Lists using spring jpa to help with CRUD for order status
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/9a9bdd0d-f54d-463f-8b88-17581890aee6)
### every instance of hashmap removed
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/9dbbef7b-0dab-4095-85fe-14a751aa6b2b)
### gke configurations added.
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/4da91b4e-8396-41cc-b7c4-3719b9199533)


***
***
***
# Overall Architecture Diagram of your Cloud Deployment
***
***
# A section for each of the following discussing the features implemented with Screenshot "Demos" of the Feature
## Cashier's App
#### New login page, with user registration and support
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/2c756585-789b-4d6b-8723-d0672893061c)
#### retrieved from gumball 3.5 as reference, Used  in conjunction with spring mvc to handle user authentication and authorization. The th:action, th:placeholder, th:text, and th:if attributes are used to dynamically populate the form fields, button text, and display error messages.
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
### Port Node.js App to Spring MVC (required)
#### node js app is ported to spring mvc as you can see
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/ad04ea36-538e-4fe8-a056-ffcc8db93344)
web rendereding is performed in resources/templates, utilizing thymeleaf, allows to write html templates with special attributes
which is processed by the server to generate dynamic content
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/9737e588-5372-4d5d-992b-6080b09aeca9)
style sheet is stored in auth folder
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/dc94307f-9d2c-4ae6-b979-55794673f1d4)

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
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/498b6f9b-2a7e-401c-889f-6f5502bf818b)
### as you can see, the api matches the kong api gateway
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/fb946613-21f3-45a1-abd3-c4498b03f5d7)
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/de580ba5-b59f-40a7-97a0-f26a3cb196b3)

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
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/818deff3-a5a5-4c8e-9b68-62df83dfae63)
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/fb2857f8-fc32-4018-a876-02630fd89835)
#### once paid for order, as you can see cashier worker pod gets received worker
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/a073a41e-155b-4844-8e7b-4ae479f538b3)
#### and as you can see in the timestamp, exactly after a minute it gets processed
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/6430ae2c-b722-4ad6-b008-2a3162cc6e3f)
### Background Worker should be a "Single Resilient POD" which auto restarts on crashes
![image](https://github.com/nguyensjsu/cmpe172-luke510909/assets/85315948/d892a46e-897a-4fa6-ad73-e2e7fe621164)





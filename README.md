# README



## INTRODUCTION

For this week's homework, we were assigned a project from a previous class. This means that no one on our team knew the code since none of us had a hand in building it. This is likely what we'll be confronted with on-the-job as it is very common that we'll find ourself in a position where us and our team will take over an existing or abandoned project. 



## REQUIREMENTS

As a team, decide the best microservice architecture. Consider where edge services might be advantageous and necessary individual services. Make sure that you are designing together how the application will be broken into services before starting.

Then, rebuild the exact project from the ground up utilizing micro-services architecture and creating API routes for every current CLI command. 

Remember to make the application as robust as possible.



## STRUCTURE

We decided to separate the original application into various **micro-services**:

- account-service
- contact-service
- lead-service
- opportunity-service
- salesRep-service

(These micro-services can communicate with each other to make some kind of query).

We also have an **edge-service** that will communicate with all of them, and to outsource the configuration of each micro-service (databases, ports ...) we have deployed a **configuration-server**, whose configuration is stored in a git repository.

Finally, we have a **eureka-server** that centralizes all the micro-services.



## FIRST STEPS

First, you have to grant privileges to your user in the database. Then, you have to execute the crm-microservices script.

Order of execution:

1. eureka-server
2. configuration-server
3. micro-services

To start the servers you have to execute this command: **mvn spring-boot:run**.



## ROUTES

Below we detail the list of available urls, and what they are for.

### GET METHODS

```java
// Get an account by Id
localhost:8085/get-account/{id}

// Get all accounts
localhost:8085/get-account
    
// Get a Lead by Id
localhost:8085/get-lead/{id}

// Get all Leads
localhost:8085/get-leads
    
// Get Opportunity by Id
localhost:8085/get-opportunity/{id}

// Get a SalesRep by Id
localhost:8085/get-salesrep/{id}

// get all SalesRep
localhost:8085/get-salesre
    
// The mean number of Opportunities associated with an Account   
localhost:8085/report/opps-per-account/mean
    
// The maximum number of Opportunities associated with an Account
localhost:8085/report/opps-per-account/max
    
// The minimum number of Opportunities associated with an Account
localhost:8085/report/opps-per-account/min
    
// The median number of Opportunities associated with an Account
localhost:8085/report/opps-per-account/median
    
// The mean employeeCount
localhost:8085/report/employee-count/mean
    
// The maximum employeeCount
localhost:8085/report/employee-count/max
    
// The minimum employeeCount
localhost:8085/report/employee-count/min
    
// The median employeeCount
localhost:8085/report/employee-count/median
    
// The mean quantity of products
localhost:8085/report/quantity-ordered-products/mean
    
// The maximum quantity of products order
localhost:8085/report/quantity-ordered-products/max
    
// The minimum quantity of products order 
localhost:8085/report/quantity-ordered-products/min
    
// The median quantity of products order
localhost:8085/report/quantity-ordered-products/median
    
// A count of Leads by SalesRep
localhost:8085/report/leads-by-salesRep
    
// A count of Opportunities by SalesRep
localhost:8085/report/opps-by-salesRep
    
// A count of all Opportunities by SalesRep and Status (OPEN, CLOSED_WON, CLOSED_LOST)
localhost:8085/report/opps-by-salesRep/{status}

// A count of Opportunities by Product
localhost:8085/report/opps-by-product
    
// A count of all Opportunities by Product and Status (OPEN, CLOSED_WON, CLOSED_LOST)
localhost:8085/report/opps-by-product/{status}

// A count of Opportunities by Industry
localhost:8085/report/opps-by-industry
    
// A count of Opportunities by Industry and Status (OPEN, CLOSED_WON, CLOSED_LOST)
localhost:8085/report/opps-by-industry/{status}

// A count of Opportunities by City
localhost:8085/report/opps-by-city
    
// A count of Opportunities by City and Status (OPEN, CLOSED_WON, CLOSED_LOST)
localhost:8085report/opps-by-city/{status}

// A count of Opportunities by Country
localhost:8085/report/opps-by-country
    
// A count of Opportunities by Country and Status (OPEN, CLOSED_WON, CLOSED_LOST)
localhost:8085/report/opps-by-country/{status}
```



### POST METHODS

```java
// Store a new account in the database
localhost:8085/store-account

// Store a new contact in the database
localhost:8085/store-contact

// Store a new lead in the database
localhost:8085/store-lead

// Convert lead to opportunity
localhost:8085/convert-opportunity

// Store a new salesRep in the database
localhost:8085/store-salesrep
```



### PUT METHODS

```java
// Update an account
localhost:8085/update-account

// Close an pportunity
localhost:8085/close-opportunity

// Associate a lead to a salesRep
localhost:8085/salesrep-lead/{salesRepId}/{leadId}

// Associate an opportunity to a salesRep
localhost:8085/salesrep-opp/{salesRepId}/{oppId}
```



### DELETE METHODS

```java
// Delete a lead 
localhost:8085/delete-lead/{id}
```














# Shopify Application
## Table of Contents
 Overview
 
Features

Technologies

Getting Started

Prerequisites

Installation

Running the Application

Testing

Swagger Integration


## Overview
The ShopifyApplication is a Spring Boot application that provides RESTful APIs for managing customers, their transactions, and calculating reward points based on their purchases. The system awards points for every dollar spent, with additional points for purchases over specific amounts.

## Features
Customer registration and login/logout.
CRUD operations for customer transactions.
Calculation of reward points based on transaction amounts.
Monthly and total reward points reporting.
Swagger integration for API documentation and testing.

## Technologies
Java
Spring Boot
Spring Data JPA
MySQL
Maven
Swagger
JUnit

## Getting Started
### Prerequisites
Java 8 or later
Maven
MySQL
Spring Boot

## Installation
### Clone the repository:

bash
Copy code
git clone https://github.com/arushikk/ShopifyApplication.git
cd shopifyApplication
Configure MySQL:

### Create a database named ShopifyDB and update the application.properties file with your MySQL credentials:

properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/ShopifyDB
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

### Build the project:

bash
Copy code
mvn clean install

### Run the application:

bash
Copy code
mvn spring-boot:run
Usage
Running the Application
After running the application, it will be available at http://localhost:8080.

#### Use Postman collection Shopify.postman_collection to interact with the application 

###  Run RewardsPointController to test the application

### use the link http://localhost:8080/v3/api-docs or http://localhost:8080/swagger-ui/index.html#/ for api documentation

# Spring Cloud Functions Demo for MS Azure

This project is a demo project for Azure Functions written with Spring Cloud Functions.

The code is largely inspired by [Spring cloud functions for Azure](https://github.com/spring-cloud/spring-cloud-function/tree/main/spring-cloud-function-samples/function-sample-azure)

## Requirements

This project was built on a Mac with M1 CPU so your mileage may vary.

* brew install azure-cli
* brew tap azure/functions
* brew install azure-functions-core-tools@4

## Start and test

* mvn package
* mvn azure-functions:run
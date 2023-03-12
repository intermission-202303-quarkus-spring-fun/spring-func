# Spring Cloud Functions Demo for MS Azure

This project is a demo project for Azure Functions written with Spring Cloud Functions.

The code is largely inspired by 
[Spring cloud functions for Azure](https://github.com/spring-cloud/spring-cloud-function/tree/main/spring-cloud-function-samples/function-sample-azure)
as well as
[Microsoft documentation for Spring Functions on Azure](https://learn.microsoft.com/de-de/azure/developer/java/spring-framework/getting-started-with-spring-cloud-function-in-azure?toc=%2Fazure%2Fazure-functions%2Ftoc.json)

## Requirements

This project was built on a Mac, package requirements for other platforms will be different.

* brew install azure-cli
* brew tap azure/functions
* brew install azure-functions-core-tools@4
* Azure Toolkit for IntelliJ

## Start and local test

* mvn package
* mvn azure-functions:run

## Deploy to Azure

* Login to your AZ Account
* mvn azure-functions:deploy
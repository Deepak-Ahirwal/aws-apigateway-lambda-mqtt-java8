# AWS IoT MQTT for Java
The aws-iot-mqtt-poc application enables Java developers to access the AWS IoT Platform through MQTT protocol. This sample is built with several support, to publish a message using MQTT client and save the sent message in DynamoDB.
Here we are implementing below operations 
1.	Create a Lambda function and publish an API using API Gateway.
2.	Create a Rule to evaluate messages sent by your things and specify what to do when a message is received using MQTT client
3.	Invoke a Lambda function and write data into a DynamoDB table. 
A simple example helps you to provide basic idea about the Aws-Iot core and Aws services like (Lambda, API Gateway, Simple Notification Service Dynamo DB, CloudWatch etc.)

To get started, use the Maven repository or download the latest-jar.

# Overview
This document provides instructions for installing and configuring the AWS IoT device SDK for Java. It also includes some examples that demonstrate the use of different APIs.

## Prerequisites
You must have assigned access permission to an IAM user. The IAM user also needs to have below full access to work with
* AWS IOT like (AWSIoTFullAccess).
* SNS access to work with SNS (AmazonSNSFullAccess).
* Lambda (AWSLambdaBasicExecutionRole or AWSLambdaFullAccess).

# Install the SDK
## Minimum Requirements
To use the SDK, you will need Java 1.8+
##  Install the SDK Using Maven
The recommended way to use the AWS IoT Device SDK for Java in your project is to consume it from Maven. Simply add the following dependency to the POM file of your Maven project.
``` xml
<dependencies>
  <dependency>
    <groupId>com.amazonaws</groupId>
    <artifactId>aws-iot-device-sdk-java</artifactId>
    <version>1.1.1</version>
  </dependency>
</dependencies>
```
#Arguments for the Applications
To run the samples, you will also need to provide the following arguments through the command line:
* clientEndpoint: client endpoint, in the form of <prefix>.iot.<region>.amazonaws.com
* clientId: client ID
* thingName: AWS IoT thing name (not required for the Publish sample)
You will also need to provide either set of the following arguments for authentication. For an MQTT connection, provide these arguments:
* certificateFile: X.509 based certificate file (For Just-in-time registration, this is the concatenated file from both the device certificate and CA certificate. For more information about Just-in-Time Registration, please see this blog.
* privateKeyFile: private key file
* keyAlgorithm: (optional) RSA or EC. If not specified, RSA is used.
  
# Create Lambda Function

##  Step 1: Create an API

1.	Sign into the API Gateway console at https://console.aws.amazon.com/lambda
2.	Click on Create function button.
3.	Select Author from scratch.
4.	In Author from scratch section specify function name, name could be anything (for example myLambdaFunction).
5.	Select runtime environment “Select java 8”
6.	Specify Role either create new or create custom or select existing if any.
7.	After specify role click on create function button.
8.	Select Add triggers here you can select any services to trigger lambda function here we select API Gateway to trigger our lambda function.In Function code “upload your jar or zip file”, “select runtime environment” and in handler specify Either “com.amazon.cts.service.app.LambdaFunctionHandler:: handleRequest” or package.class-name. For example
"com.amazon.cts.service.app.LambdaFunctionHandler" would call the handleRequest method defined in LambdaFunctionHandler.java.
9.	Click on upload button.




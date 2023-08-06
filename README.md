ASSIGNMENT DETAILS AND INSTRUCTIONS TO RUN

About:

This repository is built as part of assignment submission for DPDZero.
Framework used - Spring Boot
Database used - MySQL
Instructions to run the code:

Simpler way

A publc docker images is created for this project
Pull the project with command "docker pull jysappa/assignmentapidocker:v1.0"
Command to run the project in docker environment "docker run -p 8081:8080 jysappa/assignmentapidocker:v1.0"
This command will run the application at locahost:8081. You can use this address in postman to test the APIs
Drawbacks of docker image:
I was unable to create docker image along with Mysql setup. Encountering lot of errors.
So, utilised H2 inbuilt database of spring boot framework for persisting data.
H2 database persists data only in a session. You will not find the data when you re-run the application 5.So, For testing purpose this image works.
Source code way:

Eclipse IDE can be used to run the code. Import the project. Jdk version is 17.
Let the maven download all required dependencies.
Make changes to application.properties file. Make sure you have a database in mysql with name assignment. Change port number also in url if required.
spring.datasource.url = jdbc:mysql://localhost:3306/assignment?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username = root //Set your mysql user name here
spring.datasource.password = Secret@123 //Set your mysql password here
Right click on project. Select run as. Select maven build. In goals give command "spring-boot:run" and finish
After the build is succesful. You can access the application at port 8080 localhost.
My Observations and Improvements:

All required funcationality has been implemented.
It would have been great if I was able to create a complete docker image along with Mysql.
Thank you. Ajay Babu Sappa.

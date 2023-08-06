ASSIGNMENT DETAILS AND INSTRUCTIONS TO RUN

About:
1. This repository is built as part of assignment submission for DPDZero.
2. Framework used - Spring Boot
3. Database used - MySQL

Instructions to run the code:
1. Simpler way
    1. A publc docker images is created for this project
    2. Pull the project with command "docker pull jysappa/assignmentapidocker:v1.0"
    3. Command to run the project in docker environment "docker run -p 8081:8080 jysappa/assignmentapidocker:v1.0"
        1. This command will run the application at locahost:8081. You can use this address in postman to test the APIs
    4. Drawbacks of docker image:
       1. I was unable to create docker image along with Mysql setup. Encountering lot of errors.
       2. So, utilised H2 inbuilt database of spring boot framework for persisting data.
       3. H2 database persists data only in a session. You will not find the data when you re-run the application
     5.So, For testing purpose this image works.

2. Source code way:
   1. Clone the project from master branch of the repository. All the code is in master branch only. Not in main branch.
   2. Eclipse IDE can be used to run the code. Import the project. Jdk version is 17.
   3. Let the maven download all required dependencies.
   4. Make changes to application.properties file. Make sure you have a database in mysql with name assignment. Change port number also in url if required.
      1. spring.datasource.url = jdbc:mysql://localhost:3306/assignment?useSSL=false&allowPublicKeyRetrieval=true
      2. spring.datasource.username = root //Set your mysql user name here
      3. spring.datasource.password = Secret@123 //Set your mysql password here
    5. Right click on project. Select run as. Select maven build. In goals give command "spring-boot:run" and finish
    6. After the build is succesful. You can access the application at port 8080 localhost.

My Observations and Improvements:
  1. All required funcationality has been implemented.
  2. It would have been great if I was able to create a complete docker image along with Mysql.

Thank you.
Ajay Babu Sappa.

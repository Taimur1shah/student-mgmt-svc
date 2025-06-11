# student-mgmt-svc
Repository for Student Management microservice , responsible for student related activities

Steps
1. This microservice need to be run first
2. Compile the Project using gradle build
   OpenAPI tool  will create the StudentApi controller interface stub
   student-api --> build -->classes --> com.skiply.student.api --> StudentApi
3. Run the application , it will start on port 8080
4. Import the Postman collection and execute the curl requests
5. H2 db console can be connected in browser
   JDBC URL : jdbc:h2:mem:studentdb
   userName : sa
6.OpenAPI Spec file is located at
   student-mgmt-svc/student-api/src/main/resources/student-openapi-spec-file.yaml


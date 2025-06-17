# student-mgmt-svc
Repository for Student Management microservice , responsible for student related activities

## Techonology Stack
1. Java 17
2. Spring boot 3.2.5
3. Gradle 
4. gradle-7.5
5. H2 db
6. Openapitools
7. openapi: 3.0.3

## Steps to run the application

1. This microservice need to be run first
2. Compile the Project using gradle build in cmd

   ./gradlew clean build

3. OpenAPI tool  will create the StudentApi controller interface stub
   student-api --> build -->classes --> com.skiply.student.api --> StudentApi
   All the compilation errors will be resolved as the imported classes will be generated
4. Run the application , it will start on port 8080
5. Import the Postman collection and execute the curl requests
6. H2 db console can be connected in browser
   http://localhost:8080/h2-console
   JDBC URL : jdbc:h2:mem:studentdb
   userName : sa 
7. .OpenAPI Spec file is located at
   student-mgmt-svc/student-api/src/main/resources/student-openapi-spec-file.yaml
8. Performance, Scalability, and Reliability Targets
   health checks and readiness
   http://localhost:8080/actuator/metrics
   http://localhost:8080/actuator/health/readiness
   http://localhost:8080/actuator/health/liveness
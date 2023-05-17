# Getting Started

## Background
This is a sample application to demonstrate the capabilities of the Spring Boot framework
to easily develop and deploy a REST based micor service in quick time
### Reference Documentation


### To get the application running
1. Install Docker
2. Run the maria-db container
   docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql
3. Install Maven
4. Install Java
5. Extract the zip file to a folder and execute 6 and 7 on the same folder
6. Execute `mvn clean install` in terminal
7. Execute `java -jar target/drones-0.0.1-SNAPSHOT.jar` in terminal

### Assumptions

1. A Drone is always created with the status LOADING if the battery capacity is  > 25. 
   Otherwise it's created with the status IDLE
2. If the battery capacity drops below 25% then the drone moves to IDLE - if it's LOADING or LOADED. This is applicable 
   for end user update as well as upadtes by the periodic task
3. When a drone is moved from LOADING/LOADED -> IDLE there is no change to loaded content
4. Only the drones in LOADING status can be loaded.
5. Image attribute of the Drone is stored as a public url


### TODO

Following improvements are possible 

1. Introduce Liquibase for DB management
2. Implement a messaging based solution to integrate with real drones
3. Integrate with observability tools like new relic etc.
4. Add sonar analysis for static code checks
5. Spring event framework can be used to generate events of Drone LifeCycle events.
6. Secure endpoints with suitable authentication

### Requirement Verification

1.Registering a drone - /api/drones (POST)
2.Loading a drone with medication items - /api/drones/{sno} (PUT)
3.Checking loaded medication items for a given drone - /api/drones/{sno} (GET)
4.Checking available drones for loading - /api/drones (GET) 
   use state=IDLE
5. Check drone battery level for a given drone - /api/drones/{sno} (GET)
   Can check any attribute of a drone
   
6. Periodic task is implemented via a cron job
7. Functional requirements are implemented as validations on the domain level.
   

    



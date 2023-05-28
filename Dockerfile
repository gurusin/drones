# Use a base image with Java installed
FROM openjdk:17-jdk-slim-buster

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file to the container
COPY target/drones.jar app.jar

# Expose the port that your Spring Boot app listens on
EXPOSE 8080

# Define the command to run your Spring Boot app
CMD ["java", "-jar", "app.jar"]
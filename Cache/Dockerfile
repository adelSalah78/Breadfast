# Use a base image with OpenJDK installed
FROM openjdk:11-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/Cache.jar /app

# Command to run the Spring Boot application
CMD ["java", "-jar", "Cache.jar"]

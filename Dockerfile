# Stage 1: Build the application
FROM maven:3.6.3-jdk-8 as builder

# Set the working directory
WORKDIR /app

# Copy your pom.xml and source code to the container
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:8-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the packaged jar from the builder stage
COPY --from=builder /app/target/RestaurantApi-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application listens on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
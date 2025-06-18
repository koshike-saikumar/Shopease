# Use Maven to build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory in container
WORKDIR /app

# Copy pom.xml and resolve dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the full project source
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use a lightweight JDK base image to run the app
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port for Render
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]

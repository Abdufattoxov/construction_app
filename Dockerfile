# Use an official Maven image to build the application
FROM maven:3.6.3-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn dependency:go-offline

RUN mvn package -DskipTests

# Use an OpenJDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/Contractors-app.jar ./Contractors-app.jar

# Expose the port your application runs on (if applicable)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "Contractors-app.jar"]
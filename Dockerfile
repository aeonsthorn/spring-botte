# Use the Eclipse Temurin image with JDK 17 and Alpine as a base image for build
FROM eclipse-temurin:17-jdk-alpine AS build

# Set the working directory in the Docker container
WORKDIR /app

# Install Gradle (consider adding the version you want after 'gradle' in the command)
RUN apk --no-cache add gradle

# Copy the Gradle configuration files first for better caching
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Copy the rest of the source code
COPY src src

# Build the project using Gradle
RUN ./gradlew bootJar

# Use the Eclipse Temurin with JRE 17 and Alpine for runtime
FROM eclipse-temurin:17-jre-alpine

# Set the working directory in the Docker container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# The ENTRYPOINT specifies a command that will always be executed when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]


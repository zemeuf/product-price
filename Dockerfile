# Stage 1: Build the application
FROM gradle:latest AS builder

# Set the working directory
WORKDIR /app

# Copy the build.gradle and settings.gradle files
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Copy the source code
COPY src/ src/

# Build the application
RUN gradle build --no-daemon

# Stage 2: Create the final image
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=builder /app/build/libs/product-price-0.0.1-SNAPSHOT.jar .

# Set the command to run the application
CMD ["java", "-jar", "product-price-0.0.1-SNAPSHOT.jar"]

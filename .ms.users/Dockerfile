### Multistage Dockerfile with gradle ###
#
#FROM gradle:7.4.0-jdk17 AS build
#
#WORKDIR /apl
#
#COPY --chown=gradle:gradle . /apl
#
#RUN gradle clean build --no-daemon

# --- Stage 1: The Build Environment ---
FROM maven:3-eclipse-temurin-17-alpine AS builder

# Set the working directory
WORKDIR /apl

# Copy only the dependency definitions to leverage Docker caching
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download dependencies offline (cached unless pom.xml changes)
RUN mvn dependency:go-offline -B

# Maven or Gradle stage ↑

# Copy the source code
COPY ./src ./src

# Pack the application into a fat JAR without running tests
RUN ./mvnw clean install -DskipTests

# Stage 2 copy jar to work directory
FROM openjdk:17.0.2-jdk-slim AS worker

# Create work directory
WORKDIR /application

# Copy the compiled JAR from the build stage
COPY --from=builder /apl/target/*.jar /application/app.jar

# Extract parts from jar file
RUN java -Djarmode=tools -jar /application/app.jar extract --layers --destination /extracted/


# Stage 3 load jdk
FROM openjdk:17.0.2-jdk-slim AS final

# Build project with hash parts
COPY --from=worker /extracted/dependencies/ application/
COPY --from=worker /extracted/spring-boot-loader/ application/
COPY --from=worker /extracted/snapshot-dependencies/ application/
COPY --from=worker /extracted/application/ application/

# Create a non-root user for security
RUN addgroup --system --gid 1001 buildgroup && adduser --system --uid 1001 --ingroup buildgroup builder

# Switch to the non-root user
USER builder

# Expose the application port
EXPOSE 8081

# Init entrypoint for jarfile
ENTRYPOINT ["java", "-jar", "application/app.jar"]
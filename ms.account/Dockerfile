
### Base Dockerfile ###

#FROM eclipse-temurin:17-alpine AS geo-service
#WORKDIR /geo
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#COPY ./src ./src
#COPY ./countries.json ./countries.json
#RUN ./mvnw clean install -DskipTests
#
#FROM eclipse-temurin:17-alpine
#WORKDIR /geo
#COPY --from=geo-service /geo/target/*.jar /geo/*.jar
#COPY --from=geo-service /geo/countries.json /geo/countries.json
#EXPOSE 8077
#ENTRYPOINT ["java", "-jar", "/geo/*.jar", "src.main.java.social.network.ms_geo.MsGeoApplication.java"]

### Multistage Dockerfile with maven ###

# --- Stage 1: The Build Environment ---
FROM maven:3.9.6-eclipse-temurin-21-jammy AS builder

# Set the working directory
WORKDIR /app

# Copy only the dependency definitions to leverage Docker caching
COPY pom.xml .

# Download dependencies offline (cached unless pom.xml changes)
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Pack the application into a fat JAR without running tests
RUN mvn clean package -DskipTests

# --- Stage 2: The Runtime Environment ---
FROM eclipse-temurin:21-jre-jammy AS runner

# Create a non-root user for security
RUN addgroup --system --gid 1001 appgroup && \
    adduser --system --uid 1001 --ingroup appgroup appuser

WORKDIR /app

# Copy the compiled JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Switch to the non-root user
USER appuser

# Expose the application port
EXPOSE 8080

# Run the Java application with optimization flags
ENTRYPOINT ["java", "-jar", "app.jar"]


### Multistage Dockerfile with gradle ###
#
#FROM gradle:7.4.0-jdk17 AS build
#
#WORKDIR /apl
#
#COPY --chown=gradle:gradle . /apl
#
#RUN gradle clean build --no-daemon
#
#FROM openjdk:17.0.2-slim AS builder
#
#WORKDIR /apl
#
#COPY --from=build /apl/build/libs/right-devops-1.0.0.jar /apl/right-devops.jar
#
#RUN java -Djarmode=layertools - jar right-devops.jar extract
#
#FROM openjdk:17.0.2-slim
#
#WORKDIR /apl
#
#COPY --from=builder apl/dependencies/ ./
#
#COPY --from=builder apl/spring-boot-loader/ ./
#
#COPY --from=builder apl/snapshot-dependencies ./
#
#COPY --from=builder apl/application/ ./
#
#ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]


### Multistage Dockerfile with gradle SpringBoot V.1 ###

## Perform the extraction in a separate builder container
#FROM bellsoft/liberica-openjre-debian:25-cds AS builder
#WORKDIR /builder
#
## This points to the built jar file in the target folder
## Adjust this to 'build/libs/*.jar' if you're using Gradle
#ARG JAR_FILE=target/*.jar
#
## Copy the jar file to the working directory and rename it to application.jar
#COPY ${JAR_FILE} application.jar
#
## Extract the jar file using an efficient layout
#RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted
#
## This is the runtime container
#FROM bellsoft/liberica-openjre-debian:25-cds
#WORKDIR /application
#
## Copy the extracted jar contents from the builder container into the working directory in the runtime container
## Every copy step creates a new docker layer
## This allows docker to only pull the changes it really needs
#COPY --from=builder /builder/extracted/dependencies/ ./
#COPY --from=builder /builder/extracted/spring-boot-loader/ ./
#COPY --from=builder /builder/extracted/snapshot-dependencies/ ./
#COPY --from=builder /builder/extracted/application/ ./
## Start the application jar - this is not the uber jar used by the builder
## This jar only contains application code and references to the extracted jar files
## This layout is efficient to start up and AOT cache (and CDS) friendly
#ENTRYPOINT ["java", "-jar", "application.jar"]

### Multistage Dockerfile with gradle SpringBoot V.2 ###

## Perform the extraction in a separate builder container
#FROM bellsoft/liberica-openjre-debian:25-cds AS builder
#WORKDIR /builder
#
## This points to the built jar file in the target folder
## Adjust this to 'build/libs/*.jar' if you're using Gradle
#ARG JAR_FILE=target/*.jar
#
## Copy the jar file to the working directory and rename it to application.jar
#COPY ${JAR_FILE} application.jar
#
## Extract the jar file using an efficient layout
#RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted
#
## This is the runtime container
#FROM bellsoft/liberica-openjre-debian:25-cds
#WORKDIR /application
#
## Copy the extracted jar contents from the builder container into the working directory in the runtime container
## Every copy step creates a new docker layer
## This allows docker to only pull the changes it really needs
#COPY --from=builder /builder/extracted/dependencies/ ./
#COPY --from=builder /builder/extracted/spring-boot-loader/ ./
#COPY --from=builder /builder/extracted/snapshot-dependencies/ ./
#COPY --from=builder /builder/extracted/application/ ./
#
## Execute the AOT cache training run
#RUN java -XX:AOTCacheOutput=app.aot -Dspring.context.exit=onRefresh -jar application.jar
#
## Start the application jar with AOT cache enabled - this is not the uber jar used by the builder
## This jar only contains application code and references to the extracted jar files
## This layout is efficient to start up and AOT cache friendly
#ENTRYPOINT ["java", "-XX:AOTCache=app.aot", "-jar", "application.jar"]
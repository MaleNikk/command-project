FROM eclipse-temurin:17-jdk AS builder
WORKDIR /gateway
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /gateway
COPY --from=builder /gateway/target/*.jar /gateway/*.jar
EXPOSE 8765
ENTRYPOINT ["java", "-jar", "/gateway/*.jar","social.network.ms_gateway.MsGatewayApplication.jar"]
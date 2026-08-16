FROM eclipse-temurin:17-alpine AS eureka-service
WORKDIR /eureka
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:17-alpine
WORKDIR /eureka
COPY --from=eureka-service /eureka/target/*.jar /eureka/*.jar
EXPOSE 8778
ENTRYPOINT ["java", "-jar", "/eureka/*.jar", "src/main/java/social/network/ms_eureka/MsEurekaApplication.java"]
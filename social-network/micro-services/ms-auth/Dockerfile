FROM eclipse-temurin:17-alpine AS auth-service
WORKDIR /auth
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:17-alpine
WORKDIR /auth
COPY --from=auth-service /auth/target/*.jar /auth/*.jar
EXPOSE 8077
ENTRYPOINT ["java", "-jar", "/auth/*.jar", "src/main/java/social/network/ms_auth/MsAuthApplication.java"]
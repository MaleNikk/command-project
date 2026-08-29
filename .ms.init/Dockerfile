FROM eclipse-temurin:17-alpine AS init-service
WORKDIR /init
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:17-alpine
WORKDIR /init
COPY --from=init-service /init/target/*.jar /init/*.jar
EXPOSE 8077
ENTRYPOINT ["java", "-jar", "/init/*.jar", "src/main/java/social/network/.ms.init/MsAuthApplication.java"]
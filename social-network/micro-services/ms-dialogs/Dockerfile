FROM eclipse-temurin:17-alpine AS dialogs-service
WORKDIR /dialogs
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:17-alpine
WORKDIR /dialogs
COPY --from=dialogs-service /geo/target/*.jar /geo/*.jar
EXPOSE 8077
ENTRYPOINT ["java", "-jar", "/dialogs/*.jar", "src/main/java/social/network/ms_dialogs/MsDialogsApplication.java"]
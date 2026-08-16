FROM eclipse-temurin:17-alpine AS geo-service
WORKDIR /geo
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
COPY ./countries.json ./countries.json
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:17-alpine
WORKDIR /geo
COPY --from=geo-service /geo/target/*.jar /geo/*.jar
COPY --from=geo-service /geo/countries.json /geo/countries.json
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "/geo/*.jar", "src.main.java.social.network.ms_geo.MsGeoApplication.java"]
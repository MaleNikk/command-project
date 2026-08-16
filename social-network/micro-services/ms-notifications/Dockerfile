FROM eclipse-temurin:17-alpine AS notifications-service
WORKDIR /notifications
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install
# -DskipTests

FROM eclipse-temurin:17-alpine
WORKDIR /notifications
COPY --from=notifications-service /notifications/target/*.jar /notifications/*.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/notifications/*.jar", "src.main.java.social.network.ms_notifications.MsNotificationsApplication.java"]
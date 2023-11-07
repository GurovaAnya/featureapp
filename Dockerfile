FROM maven:3.8.5-openjdk-17 AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean package -e

FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=MAVEN_BUILD target/feature-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
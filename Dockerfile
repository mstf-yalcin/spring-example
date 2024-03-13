FROM openjdk:17-slim as build
WORKDIR /app
COPY pom.xml mvnw ./
COPY .mvn .mvn

#RUN apt-get update && apt-get install -y dos2unix
#RUN dos2unix ./mvnw
RUN sed -i 's/\r$//' mvnw


RUN /bin/sh mvnw dependency:resolve

COPY src src
RUN /bin/sh mvnw package -DskipTests

FROM eclipse-temurin:17-jre-alpine AS jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]



#Configuración Dockerfile para compilación y despliegue en Render
FROM maven:3-amazoncorretto-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine
WORKDIR /app
COPY --from=build /app/target/fct-connect-0.0.1-SNAPSHOT.jar fct-connect-back.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "fct-connect-back.jar"]

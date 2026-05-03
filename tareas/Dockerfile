# ETAPA 1: Construcción (El taller)
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
# Copiamos el archivo de configuración de Maven
COPY pom.xml .
# Copiamos el código fuente
COPY src ./src
# Fabricamos el JAR adentro de Docker
RUN mvn clean package -DskipTests

# ETAPA 2: Ejecución (El auto listo para andar)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Traemos el JAR que fabricamos en la ETAPA 1
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Duser.timezone=UTC", "-jar", "app.jar"]
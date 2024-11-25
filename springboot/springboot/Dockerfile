# Usa una imagen de Maven para compilar y empaquetar la aplicación
FROM maven:3.8.1-openjdk-8 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo de configuración de Maven
COPY pom.xml .

# Descarga las dependencias necesarias
RUN mvn dependency:go-offline

# Copia el resto del código fuente
COPY src ./src

# Compila y empaqueta la aplicación
RUN mvn package -DskipTests

# Usa una imagen de OpenJDK para ejecutar la aplicación
FROM openjdk:8-jdk-alpine

# Establece el archivo JAR como un argumento
ARG JAR_FILE=target/*.jar

# Copia el archivo JAR desde la fase de compilación
COPY --from=build /app/${JAR_FILE} app.jar

# Expone el puerto 8080
EXPOSE 8080

# Define el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-Xmx256m", "-Xms256m", "-jar", "/app.jar"]
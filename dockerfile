# Usa una imagen base con Java 21 instalada
FROM openjdk:21-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado desde el proyecto Spring al contenedor
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación Spring Boot se ejecutará
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]

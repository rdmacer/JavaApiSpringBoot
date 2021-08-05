FROM openjdk8
WORKDIR /app/

# Copiamos el JAR de nuestra aplicación a la imagen Docker
COPY target/JavaApiSpringBoot-1.0.jar .

# Corremos el archivo JAR
CMD java -jar JavaApiSpringBoot-1.0.jar
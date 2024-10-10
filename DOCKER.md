# Documentación de Docker

Este documento proporciona información sobre cómo configurar y ejecutar los microservicios utilizando Docker.

## Requisitos Previos

Antes de comenzar, asegúrate de tener instalados:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/)

## Estructura de Archivos Docker

El proyecto incluye un archivo `docker-compose.yml` en la raíz, que define los servicios de los microservicios y la base de datos. Cada microservicio tiene su propio `Dockerfile` para construir la imagen del servicio.

### docker-compose.yml

Este archivo contiene la configuración para levantar los siguientes servicios:

- `cliente_persona_service`
- `cuenta_movimientos_service`
- `mysql` (base de datos)

#### Ejemplo de docker-compose.yml

```yaml
version: '3.8'

services:
  cliente_persona_service:
    build:
      context: ./cliente_persona_service
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/gestionbancaria
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root
    depends_on:
      - mysql

  cuenta_movimientos_service:
    build:
      context: ./cuenta-movimientos-service
    ports:
      - "8081:8081"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/gestionbancaria
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root
    depends_on:
      - mysql

  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: gestionbancaria
    ports:
      - "3306:3306"

Instrucciones para Construir y Ejecutar
Navega a la carpeta raíz del proyecto donde se encuentra el archivo docker-compose.yml.

Construye los contenedores utilizando el siguiente comando:

docker-compose build

Ejecuta los contenedores:

docker-compose up

Accede a los microservicios en los siguientes puertos:

cliente_persona_service: http://localhost:8080
cuenta_movimientos_service: http://localhost:8081

Para detener los contenedores, presiona Ctrl + C en la terminal donde ejecutaste docker-compose up, o utiliza:

docker-compose down

Inicialización de la Base de Datos
El contenedor de MySQL iniciara el motor de BD.
Despues ejecutar el script BaseDatos.sql al iniciar, creando la base de datos y las tablas necesarias. 
Asegúrate de que el archivo esté correctamente configurado y ubicado en la raíz del proyecto.

Notas Adicionales
Puedes personalizar los parámetros de configuración en el archivo docker-compose.yml según sea necesario.
Asegúrate de que los puertos no estén en uso por otros servicios en tu máquina.
Para más información sobre Docker y Docker Compose, consulta la documentación oficial de Docker.

# Gestión Bancaria

Este proyecto consiste en dos microservicios para la gestión de clientes y cuentas bancarias.

## Estructura del Proyecto

Este proyecto está organizado en varias carpetas para facilitar el desarrollo y mantenimiento de los microservicios. A continuación se presenta la estructura de carpetas:

## Descripción de las Carpetas

- **BaseDatos.sql**: Script de creación y poblamiento de la base de datos.
- **docker-compose.yml**: Archivo de configuración para levantar los servicios con Docker.
- **estructura.txt**: Descripción general de la estructura del proyecto.
- **cliente_persona_service/**: Contiene el microservicio de cliente y persona.
  - **src/**: Código fuente del servicio.
  - **.gitignore**: Archivos que se deben ignorar por Git.
  - **Dockerfile**: Instrucciones para construir la imagen Docker.
- **cuenta-movimientos-service/**: Contiene el microservicio de cuentas y movimientos.
  - **src/**: Código fuente del servicio.
  - **.gitignore**: Archivos que se deben ignorar por Git.
  - **Dockerfile**: Instrucciones para construir la imagen Docker.

## Notas

- Cada microservicio está diseñado para ser independiente y puede ejecutarse en su propio contenedor.
- Asegúrate de que todos los servicios estén correctamente configurados en el archivo `docker-compose.yml`.

## Microservicios

### cliente_persona_service
- **Funcionalidad**: Maneja la información de clientes y personas.
- **Endpoints**: 
  - `/clientes`: Para obtener, crear, actualizar y eliminar clientes.

### cuenta_movimientos_service
- **Funcionalidad**: Maneja cuentas bancarias y movimientos.
- **Endpoints**: 
  - `/cuentas`: Para obtener, crear, actualizar y eliminar cuentas.
  - `/movimientos`: Para manejar movimientos de cuentas.

## Requisitos

- Java 11 o superior
- Docker y Docker Compose
- MySQL 8.0

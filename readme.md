
# Proyecto de Gestión de Exámenes

Este proyecto es una aplicación completa de gestión de exámenes, desarrollada utilizando una arquitectura de microservicios para el backend y Next.js para el frontend. Incluye cuatro microservicios desarrollados en Spring Boot, cada uno con su propia base de datos Dockerizada. El frontend se ha desarrollado con Next.js y Tailwind CSS para los estilos.

## Tabla de Contenidos

- [Descripción del Proyecto](#descripción-del-proyecto)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Configuración del Backend](#configuración-del-backend)
    - [Microservicio de Usuarios](#microservicio-de-usuarios)
    - [Microservicio de Cursos](#microservicio-de-cursos)
    - [Microservicio de Exámenes](#microservicio-de-exámenes)
    - [Microservicio de Respuestas](#microservicio-de-respuestas)
- [Configuración del Frontend](#configuración-del-frontend)
- [Ejecutando el Proyecto](#ejecutando-el-proyecto)
- [Uso de la Aplicación](#uso-de-la-aplicación)


## Descripción del Proyecto

El objetivo de este proyecto es proporcionar una solución completa para la gestión de exámenes. Los usuarios pueden crear, editar y eliminar exámenes, así como gestionar las respuestas asociadas a cada examen. El proyecto está dividido en cuatro microservicios, cada uno con su propia base de datos.

## Tecnologías Utilizadas

- **Backend**: Spring Boot (JDK 15)
- **Frontend**: Next.js
- **Estilos**: Tailwind CSS
- **Bases de Datos**: MySQL y PostgreSQL, todas ejecutándose en contenedores Docker
- **IDE**: IntelliJ IDEA

## Estructura del Proyecto

- **msvc-usuarios**: Microservicio que gestiona los usuarios. Corre en el puerto 8001 y utiliza MySQL.
- **msvc-cursos**: Microservicio que gestiona los cursos. Corre en el puerto 8002 y utiliza PostgreSQL.
- **msvc-examenes**: Microservicio que gestiona los exámenes. Corre en el puerto 8003 y utiliza PostgreSQL.
- **msvc-respuestas**: Microservicio que gestiona las respuestas. Corre en el puerto 8004 y utiliza PostgreSQL.
- **frontend**: Aplicación frontend desarrollada con Next.js y Tailwind CSS.

## Configuración del Backend

### Microservicio de Usuarios

- **Puerto**: 8001
- **Base de Datos**: MySQL
- **Configuración de MySQL en Docker**:
  ```bash
  docker run --name mysql-usuarios -e MYSQL_ROOT_PASSWORD=123 -e MYSQL_DATABASE=usuarios_db -p 3306:3306 -d mysql:latest
  ```

### Microservicio de Cursos

- **Puerto**: 8002
- **Base de Datos**: PostgreSQL
- **Configuración de PostgreSQL en Docker**:
  ```bash
  docker run --name postgres-cursos -e POSTGRES_PASSWORD=123 -e POSTGRES_DB=cursos_db -p 5432:5432 -d postgres:latest
  ```

### Microservicio de Exámenes

- **Puerto**: 8003
- **Base de Datos**: PostgreSQL
- **Configuración de PostgreSQL en Docker**:
  ```bash
  docker run --name postgres-examenes -e POSTGRES_PASSWORD=123 -e POSTGRES_DB=examenes_db -p 5433:5432 -d postgres:latest
  ```

### Microservicio de Respuestas

- **Puerto**: 8004
- **Base de Datos**: PostgreSQL
- **Configuración de PostgreSQL en Docker**:
  ```bash
  docker run --name postgres-respuestas -e POSTGRES_PASSWORD=123 -e POSTGRES_DB=respuestas_db -p 5434:5432 -d postgres:latest
  ```

## Configuración del Frontend

El frontend está desarrollado en Next.js y utiliza Tailwind CSS para los estilos.

### Instalación de Dependencias

Asegúrate de tener Node.js y npm instalados. Luego, instala las dependencias:

```bash
cd sistem_academic
npm install
```

### Ejecución del Frontend

```bash
npm run dev
```

Esto iniciará el servidor de desarrollo en `http://localhost:3000`.

## Ejecutando el Proyecto

### Backend

1. Asegúrate de que todos los contenedores de Docker estén corriendo para las bases de datos:
    ```bash
    docker ps
    ```

2. Inicia cada uno de los microservicios desde IntelliJ IDEA o desde la línea de comandos:
    ```bash
    mvn spring-boot:run
    ```
   (Ejecuta este comando en cada microservicio dentro de sus respectivas carpetas).

### Frontend

1. Navega a la carpeta del frontend:
    ```bash
    cd sistem_academic
    ```

2. Inicia el servidor de desarrollo:
    ```bash
    npm run dev
    ```

3. Abre tu navegador y ve a `http://localhost:3000`.

## Uso de la Aplicación

1. **Usuarios**: Gestiona la lista de usuarios, incluyendo la creación, edición y eliminación.
2. **Cursos**: Gestiona la lista de cursos disponibles.
3. **Exámenes**: Crea y gestiona exámenes, asignando cursos y usuarios.
4. **Respuestas**: Gestiona las respuestas de los exámenes, permitiendo la creación, edición y eliminación de respuestas.



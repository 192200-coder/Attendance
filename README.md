Sistema de Asistencia (Attendance System)
Este es un proyecto Fullstack para la gestión de asistencias, desarrollado con Spring Boot para el Backend y Angular 18 para el Frontend.

Tecnologías Utilizadas

Backend: Java 17, Spring Boot 3, Spring Security (JWT), Hibernate/JPA.

Frontend: Angular 18, RxJS, Interceptores HTTP.

Base de Datos: MySQL 8.

Configuración e Instalación

1. Backend (Spring Boot)
   Navega a la carpeta attendance-system/.

Asegúrate de tener una base de datos MySQL llamada attendance_db.

Modifica el archivo src/main/resources/application.properties con tus credenciales de MySQL:

Properties

spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
Ejecuta el proyecto usando tu IDE o con ./gradlew bootRun.

Nota: Al iniciar por primera vez, se creará automáticamente un usuario admin: admin / admin123.

2. Frontend (Angular)
   Navega a la carpeta attendance-front/.

Instala las dependencias necesarias:

Bash

npm install
Inicia el servidor de desarrollo:

Bash

ng serve
Abre tu navegador en http://localhost:4200.

Seguridad
El sistema utiliza JSON Web Tokens (JWT) para la autenticación y un mecanismo de Refresh Tokens para mantener la sesión segura sin necesidad de loguearse constantemente.

Estructura del Proyecto
/attendance-system: API REST robusta con seguridad implementada.

/attendance-front: Interfaz de usuario moderna y reactiva.

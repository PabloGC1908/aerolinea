# Aerolinea-backend

Front-end: https://github.com/PabloGC1908/aerolinea-front

## Dependencias de Spring Boot utilizadas

### Spring Data JPA
Spring Data JPA es parte del proyecto Spring Data, que simplifica el acceso a bases de datos relacionales desde aplicaciones Spring. JPA (Java Persistence API) es una especificación de Java para el manejo de datos en una base de datos relacional. Spring Data JPA proporciona una abstracción sobre JPA, lo que facilita la creación de repositorios de datos y consultas utilizando interfaces y anotaciones en lugar de escribir consultas SQL.
### Validation
La validación en el contexto de Spring se refiere a la validación de datos de entrada o formularios en una aplicación web. Spring proporciona un módulo de validación que utiliza anotaciones, como ``@NotNull``, ``@Size``, ``@Email``, etc., para definir reglas de validación en los campos de formulario. Esto ayuda a garantizar que los datos ingresados por el usuario cumplan con ciertos criterios antes de procesarlos en la aplicación.
### Lombok
Lombok es una biblioteca que simplifica la creación de código en Java al generar automáticamente código repetitivo, como getters, setters, constructores, ``equals()``, ``hashCode()``, y más, a través de anotaciones. Esto reduce la cantidad de código "boilerplate" que los desarrolladores deben escribir, mejorando la legibilidad y la productividad.
### Spring Web
Spring Web es un módulo de Spring que proporciona herramientas y abstracciones para desarrollar aplicaciones web. Incluye soporte para controladores basados en anotaciones, manejo de formularios, gestión de sesiones, manejo de peticiones HTTP y muchas otras características esenciales para el desarrollo web en Java. Spring Web MVC es una parte importante de este módulo y se utiliza comúnmente para construir aplicaciones web basadas en Spring.
### Spring Security
Spring Security es un módulo de Spring que se utiliza para gestionar la seguridad y la autenticación en aplicaciones web. Proporciona un conjunto de características que permiten proteger recursos y controlar el acceso de usuarios. Puedes configurar la seguridad de tus aplicaciones mediante anotaciones o configuración XML. Spring Security es altamente personalizable y se utiliza para implementar autenticación, autorización, manejo de sesiones, y más.
### MySQL Driver
El controlador MySQL (también conocido como MySQL Connector o MySQL JDBC Driver) es una biblioteca que permite que una aplicación Java se conecte y comunique con una base de datos MySQL. Este controlador es esencial para interactuar con bases de datos MySQL desde una aplicación Java. Proporciona las clases y métodos necesarios para establecer conexiones, enviar consultas y recibir resultados desde la base de datos MySQL.

## Requisitos Previos

Asegúrate de tener instalados los siguientes componentes antes de comenzar:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) - versión 17.0.5
- [Maven](https://maven.apache.org/download.cgi) - versión 3.8.6
- [MySQL](https://www.mysql.com/downloads/) - versión 8.0.28
- (Opcional) [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) - IntelliJ IDEA.

> Se recomienda el uso de IntelliJ IDEA por su simplicidad y rapidez en manejar proyectos en Java,
> ademas que tiene un gran soporte con Spring

## Configuración

1. Ejecuta el script SQL proporcionado en la carpeta ``resources.db.database.sql`` para crear la base de datos y
   las tablas necesarias. Puedes usar la línea de comandos de MySQL o una herramienta de administración de bases de datos como phpMyAdmin.
   
   ```shell
   mysql -u {tu_usuario} -p {tu_contraseña} < database.sql

2. Configurar el nombre de usuario y contraseña en el application.properties
   
   ```
    spring.datasource.url=jdbc:mysql://localhost:3306/aerolinea_db
    spring.datasource.username={tu_nombre_de usuario}
    spring.datasource.password={tu_contraseña}
   ```

3. Ejecutar la aplicacion desde la clase principal
   ``
    AerolineaApplication
   ``

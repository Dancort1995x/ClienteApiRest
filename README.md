# BACK-END SPRINGBOOT CLIENTE

_Creacion de api rest cliente con SPRINGBOOT_ 

### Pre-requisitos 📋

_Requisitos para levantar backend :_

* Java 11 o superior
* Maven 3.6.0 en adelante
* Uso de algun ide (STS, eclipse,intellij,VSCODE..)
* Conexion con alguna Base de datos (En esta ocasión se utiliza POSTGRESQL)
* Crear base datos con el nombre a gusto

### variables de entorno 👇
* Sera necesario indicar en la configuracion del ide, los siguientes variables de entorno:
```
${ENV_DATASOURCE_URL}
${ENV_USR_POSTGRESS}
${ENV_PSW_POSTGRESS}

```
* Cada una de estas son para la conexion de la bd, usuario y contraseña



#### Ejecutar Api 👨🏻‍💻
* Para realizar prueba con la api sera necesario de POSTMAN y/o en su defecto con swagger (URL : http://localhost:8080/swagger-ui.html)


### Properties ✍️
* Si levanta por primera vez el proyecto, debera descomentar (quitar simbolo #) los siguientes properties para que se creen las tablas
* Tener como observacion que es necesario Crear la base de datos antes de poder levantar el proyecto
```
#spring.jpa.generate-ddl=true
#spring.jpa.hibernate.ddl-auto=create
#spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true

```

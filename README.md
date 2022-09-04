# BACK-END SPRINGBOOT CLIENTE

_Creacion de api rest cliente con SPRINGBOOT_ 

### Pre-requisitos 📋

_Requisitos para levantar backend :_

* Java 13
* Maven 3.6.0 en adelante
* Uso de algun ide (STS, eclipse,intellij,VSCODE..)
* Conexion con alguna Base de datos (En esta ocasión se utiliza POSTGRESQL)
* Crear base datos con el nombre a gusto

### variables de entorno 👇
* Sera necesario indicar en la configuracion del ide, los siguientes variables de entorno:
```
${DATABASE_URL}
${ENV_USR_POSTGRESS}
${ENV_PSW_POSTGRESS}

```
* Cada una de estas son para la conexion de la bd, usuario y contraseña

#### Ejecutar Api 👨🏻‍💻
* Para realizar prueba con la api sera necesario de POSTMAN y/o en su defecto con swagger (URL : http://localhost:8080/swagger-ui.html)


### Heroku

* Para revisar y hacer pruebas de la api, debe ingresar a la siguiente url: https://dct-cliente-spring.herokuapp.com/swagger-ui.html
* Tambien puede hacer pruebas directo llamando a los endpoint correspondientes ejemplo: https://dct-cliente-spring.herokuapp.com/api/v1/clientes
* Tambien puede probar directo por Postman.
* OBS: puede que al principio se demore en cargar el sitio de heroku, cuando carge ya podra hacer todas las pruebas. 


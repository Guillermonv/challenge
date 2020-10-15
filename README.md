#Challenge Meet Up


## Tecnología
Java 8
Spring boot 
Swagger
JWT
H2

Para ejecutar la application debera ejecutarse el comando `mvn spring-boot:run` en el root del projecto (carpeta challenge)

## Swagger
http://localhost:8080/swagger-ui.html

## Usuarios

Los usuairos se crean en runtime en la clase que se llama dataLoader en el paquete init, cualquier cambio de usuarios se puede realizar ahi.

|id|Username   | Password   | Role   |
| ------------| ------------ | ------------ | ------------ |
|1|  admin | qwerty123  | ADMIN  |
|2|  staff |  qwerty123 | ADMIN  |
|3| user1  | qwerty123  |  USER |
|4|  user2 |   qwerty123 |  USER |
|5|  user3 |   qwerty123 |  USER |
|6-10|  user....n< 10 |   qwerty123 | USER   |


## Seguirdad
>  el endpiint de autenticacion http://localhost:8080/auth es el unico que no se puede acceder por swagger , debera accederse por postman 

El endpoint para generar el token es el siguiente: **POST** http://localhost:8080/auth. Este metodo recibe un json con los parametros username y password. 
```
{
    "username": "Jhon.doe",
    "password": "qwerty1"
}
```
>de acuerdo al tipo de rol que tenga el usuario **(ADMIN/USER)** podra acceder a diferentes lugares

La respuesta es un json con el barrier token con diferentes permisos
```
{
    "username": "Jhon.doe",
    "password": null,
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzYW50YW5kZXJUZWNub2xvZ2lhSldUIiwic3ViIjoiQWRtaW4iLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImlhdCI6MTYwMjY4NTczNCwiZXhwIjoxNjAyNjg2MzM0fQ.NB9F_BionjjrSSTT4IizxTEnhT7QrFlTHRv9R10yiiP8XJCA4DmZhv1Bf98kG3KVN0BKPBTwr_5Qfmm_r-6lJg**"
}
```
cada endpoint en swagger tiene un campos llamado Authorization que es de tipo header y deberan ingresar el token (incluyendo la palabra header)

## Endpoints

**GET** /amountBeer/{meetUpId}
**Descripcion**: devuelve la cantidad de cervezas y six packs para una meetUp en base a la temperatura de la meetUp y la cantidad de gente inscripta, puede arrojar si la meetUp no existe o si se quiere el rango para obtener el clima esta fuera de los proximos 7 dias.

**Role Necesario** : ADMIN
**Parametros** : id de la meetUp
**Respuesta** : 
```
{
  "beerQty": 2,
  "sixPackQty": 1
}
```
------

**POST** /invite/{meetUpId}/user/{userId}
**Descripcion**: agrega a la lista de la meetup al user y su estado queda en pending hasta que realice el checkin
**Role Necesario** : ADMIN/USER
**Parametros** : id de la meetUp y del user
**Respuesta** :mesaje que informa que el user {userId} se agrego a la meeting meetUpId}

------

**POST** /checkin/{meetUpId}/user/{userId}
**Descripcion**: cambia el estado del user en la meeting de PENDING a ACCEPTED y
 en la info del usuario aparece en la lista de cursos que asistio
**Role Necesario** : ADMIN/USER
**Parametros** : id de la meetUp y del user
**Respuesta** :mesaje que informa que el user {userId} confirmo la asistencia a la {meetUpId}

------
**PUT** /createMeetUp
**Descripcion**: crea una meetUp
**Role Necesario** : ADMIN
**Parametros** : json con informacion de la meetUp
```
{
  "city": "Cordoba",
  "meetUpDate": "2020-10-14",
  "name": "Best practices for Java Programming"
}
```
**Respuesta ** :confirma que la meeting se creo exitosamente

------

**GET** /getUser/{userId}
**Descripcion**: se obtiene informacion del usuario junto con las MeetUps en las que se enlisto, puede arrojar una exception si el user no existe.
**Role Necesario** : ADMIN/USER
**Parametros** : userId

**Respuesta** :json con la informacion del usuario
```
{
  "firstName": "Guillermo",
  "userName": "Jhon.doe",
  "lastName": "Varelli",
  "assistedTo": [
    "Best practices for Java Programming",
	"Cloud computing meetUp"
  ]
}
```


------
**GET**  /getWeather/{meetUpId}
**Descripcion**: devuelve la temperatura maxima y minima el dia de la meet up , arroja una exception si la meeting no existe o si el rango para obtener el clima es invalido
**Role Necesario** : ADMIN
**Parametros** : meetUpId

**Respuesta * :json con la informacion del clima
```
{
  "temperature_min": 16.8,
  "temperature_max": 25.8
}
```

------
**GET**  /notification/
**Descripcion**: devuelve la informacion de todas las meetings 
**Role Necesario** : ADMIN/USER

**Respuesta** :json con la informacion de todas las meeting y el esdtado de las invitaciones usuarios que asistiran
	
Response body
Download
```
[
  {
    "id": 1,
    "name": "Java Programming",
    "meetUpDate": "2020-02-26",
    "city": "Cordoba",
    "enrrolled": [
      "Guillermo Varelli STATUS ACEPTED",
      "Jhony Tolengo STATUS PENDING",
      "Juan Carlos Batman STATUS PENDING"
    ]
  },
  {
    "id": 2,
    "name": "Cloud MeetUp",
    "meetUpDate": "2020-03-29",
    "city": "Salta",
    "enrrolled": [
      "Dummy user3 STATUS ACCEPTED"
    ]
  },...
```



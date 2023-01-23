# Documentacion prueba tecnica
Para realizar la prueba utilice spring-boot como framework y Maven como manejador de dependencias.
La api cuenta con los siguientes características:

-Spring security y jwt para la seguridad de la misma.
-Swagger para la documentación de los endpoints (en este documento solo se aclaran algunos puntos a tener en cuenta).
-Seeder para crear el usuario inicial que es Admin y la clave 123456

## Creacion de usuarios
Para este servicio solo se deben enviar los siguientes campos:
{
  "usuaPassword": "12134546",
  "usuaFullName": "Camilo Vargas",
  "usuaMail": "vargas.camilo@gmail.com",
  "usuaPhone": "3212184711",
  "usuaDocumentNumber": "1121869644",
  "rolId": 1,
  "usuaStatus": true
}

## Creacion de tareas
Para este servicio solo se deben enviar los siguientes campos:
{
  "tasTitle": "Test unit",
  "tasDecription": "This is a test",
  "tasBeginDate": "2023-02-01",
  "tasEndDate": "2023-03-30",
  "tasHoursExpect": 150,
  "tasAssignTo": 3,
  "tasStatus": true  
}

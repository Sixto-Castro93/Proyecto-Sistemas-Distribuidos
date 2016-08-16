# SISTEMA DE MENSAJERÍA UTILIZANDO REDIS PUB/SUB

## Integrantes:
* Sixto Castro R.
* Jordy Vásquez C.
* Marlon Espinoza P.

## OBJETIVOS
* Comunicación en tiempo real, es un tópico indispensable en un sistema de mensajería instantánea.
* Envío y recepción de mensajes entre varios clientes suscriptos a un canal específico.
* Implementación multicanal de tal manera que el Cliente pueda suscribirse y chatear en distintos canales.


## DESCRIPCIÓN DEL PROYECTO

Este proyecto consiste en la implementación de un sistema de mensajería multiusuario y multicanal mediante la utilización de Redis, un middleware que abstrae la gestión del puerto de escucha y envío de mensajes bajo un esquema publish-subscribe.

El programa está hecho en Java, en el cual el usuario puede crear grupos de chat o suscribirse en grupos ya creados para luego poder acceder al chat respectivo con los usuarios que forman parte del canal. También se le da al usuario la opción de desafiliarse de un grupo (unsubscribe) si ya no quiere recibir mensajes o pertenecer a un grupo en específico.

Y como extra al proyecto, se ha implementado esta aplicación con sus respectivas interfaces gráficas (GUI) con el fin de tener una mejor interacción por parte del usuario ya que en un principio se lo había hecho sin interfaz gráfica (solamente en consola).


## MANUAL
# Instalar Redis
Siguiendo los pasos de la página de Redis: http://redis.io/topics/quickstart. Ejecutar los siguientes comandos.
* sudo mkdir /etc/redis
* sudo mkdir /var/redis

Y el siguiente paso para activar el servidor de Redis, es ejecutando el comando:
redis-server

# Ejecutar el programa
Para ejecutar el programa, solo se necesita el .jar del programa que se encuentra en el repositorio y ejecutar el siguiente comando por terminal de linux:
* java -jar nombre-del-archivo.jar localhost

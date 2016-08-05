/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RedisChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *
 * @author Sixto
 */
public class RedisPubSub {

    /**
     * @param args the command line arguments
     */
    public static final String channel_name = "redisChannel";
    public static boolean validaVerMsj=false;

    public static void main(String[] args) throws Exception {
        final String IpServidor=args[0];
        JedisPool jedispool = new JedisPool(IpServidor);
        boolean bandera = true;
        HashMap<String,String> canalesSub = new HashMap<String,String>();
        while (bandera) {
            System.out.println("-----Bienvenidos a nuestro sistema de mensajeria-----");
            System.out.println("Elija una de las siguientes opciones:");
            System.out.println("1.-Subscribir/Crear Grupo");
            System.out.println("2.-Ver Grupos Subscritos");
            System.out.println("3.-Eliminar Grupos Subscritos");
            System.out.println("4.-Ayuda y quienes somos");
            System.out.println("5.-Salir");
            RedisPubSub.validaVerMsj=false;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String canalElejido = reader.readLine();
            switch (canalElejido) {
                case "1":
                    Subcribir_Crear.Subcribir_Crear(IpServidor);
                    break;

                case "2":
                    canalesSub=Subcribir_Crear.canalesSuscritos;
                    if(canalesSub.isEmpty()){
                        System.out.println("No existe canales disponibles. Por favor, ingrese en la opcion 1");
                        break;
                    }
                    else{
                        validaVerMsj=true;
                        VerGrupos.ver_grupos(canalesSub, jedispool);
                        break;
                    }
                case "3":
                    canalesSub=Subcribir_Crear.canalesSuscritos;
                    if(canalesSub.isEmpty()){
                        System.out.println("No existe canales disponibles. Por favor, ingrese en la opcion 1");
                        break;
                    }
                    else{
                        //validaVerMsj=true;
                        EliminarGrupos.eliminar_grupos(canalesSub, jedispool);
                        break;
                    }
                case "4":
                    break;
                case "5":
                    bandera = false;
                    break;
                default:
                    break;
            }

        }

        //Jedis publisherJedis = jedispool.getResource();
        //new Publisher(publisherJedis, channel_name).start();
        //subscriber.unsubscribe();
        //jedispool.returnResource(subscriberJedis);
       // jedispool.returnResource(publisherJedis);
    }

   
}

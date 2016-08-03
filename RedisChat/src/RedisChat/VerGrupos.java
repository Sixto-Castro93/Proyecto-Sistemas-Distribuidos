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
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *
 * @author Sixto
 */
public class VerGrupos {
    public static String canalVerifica;
    
    
    public static void ver_grupos(ArrayList<String> canales, JedisPool jedispool) throws IOException{
        int tam = canales.size();
        Jedis publisherJedis = jedispool.getResource();
        ArrayList<String> mensajes = new ArrayList<>();
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Grupos suscritos");
        for(int i=0; i<tam; i++){
            System.out.println(/*(i+1)+".- "+*/canales.get(i));
        }
        System.out.println("Seleccione el nombre de grupo para ver los mensajes respectivos");
        String canal = reader1.readLine();
        if(Subscriber.canalesCliente.get(canal)==null){
            System.out.println("El canal "+ canal+" no existe o no se encuentra suscrito a ese canal");
        }
        
//        if ((mensajes.isEmpty()) || (mensajes==null)) {
//            System.out.println("No existen mensajes en el canal "+ canal);
//                        
//               
//        } 
        else {
            mensajes = (ArrayList) Subscriber.canalesCliente.get(canal);
            int longitud= mensajes.size();
            for(int j=0; j<longitud; j++){
                System.out.println("Canal "+canal+" Mensaje: "+mensajes.get(j));
            }
            
        }
        canalVerifica=canal;
        new Publisher(publisherJedis,canal).start();
        
        
    }
    
    
}

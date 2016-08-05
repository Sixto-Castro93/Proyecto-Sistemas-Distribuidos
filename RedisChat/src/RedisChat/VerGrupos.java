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
import java.util.Iterator;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *
 * @author Sixto
 */
public class VerGrupos {

    public static String canalVerifica = "";

    public static void ver_grupos(HashMap<String, String> canales, JedisPool jedispool) throws IOException {

        Jedis publisherJedis = jedispool.getResource();
        ArrayList<String> mensajes = new ArrayList<>();
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
        boolean bandera1 = true;
        while (bandera1) {
            System.out.println("Grupos suscritos");
            String clave;
            Iterator<String> cns = canales.keySet().iterator();
            HashMap<String, String> grupos = new HashMap<>();
            int z = 1;
            while (cns.hasNext()) {
                clave = cns.next();
                System.out.println(z + " " + clave);
                grupos.put(Integer.toString(z), clave);
                z++;
            }

            System.out.println("Seleccione el numero de grupo para ver los mensajes respectivos, 0 para regresar");
            String canal = reader1.readLine();
            switch (canal) {
                case "0":
                    bandera1 = false;
                    break;
                default:

                    if ((canal) != null) {
                        canal = (String) grupos.get(canal);
                        if (canal != null) {
                            mensajes = (ArrayList) Subscriber.canalesCliente.get(canal);
                            System.out.println("Escriba su mensaje....'exit' para salir de la conversacion");

                            if (mensajes != null) {
                                int longitud = mensajes.size();

                                for (int j = 0; j < longitud; j++) {
                                    System.out.println("Mensaje: " + mensajes.get(j));
                                }
                            }
                            else{
                                System.out.println("Bienvenid@ al canal: " + canal+"!!");
                
                            }
                           
                            canalVerifica = canal;

                            new Publisher(publisherJedis, canal, canales.get(canal))
                                    .start();
                        } else {
                            System.out.println("Ese canal no esta dentro del rango de opciones");
                        }
                    } //        if ((mensajes.isEmpty()) || (mensajes==null)) {
                    else {
                        System.out.println("Seleccione un canal valido");
                    }
                    break;
            }

        }

    }

}

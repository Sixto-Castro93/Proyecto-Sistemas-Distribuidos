/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distribuidos.distribuidos;

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
public class EliminarGrupos {
    
    public static void eliminar_grupos(HashMap<String, String> canales, JedisPool jedispool) throws IOException{
        final Jedis subscriberJedis = jedispool.getResource();
        Subscriber subscriber;
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
            System.out.println("Seleccione el canal al cual desea eliminar, 0 para regresar");
            String canal = reader1.readLine();
            switch (canal) {
                case "0":
                    bandera1 = false;
                    break;
                default:
                {
                    if ((canal) != null) {
                        canal = (String) grupos.get(canal);
                        if (canal != null) {
                            Subscriber.canalesCliente.remove(canal);
                            Subcribir_Crear.canalesSuscritos.remove(canal);
                            System.out.println("Usted acaba de eliminar el canal/grupo "+ canal);
                            subscriber = Subcribir_Crear.subscriberCanales.get(canal);
                            subscriber.unsubscribe();
                            jedispool.returnResource(subscriberJedis);
                            bandera1 = false;
                        }
                    }
                }
            }
        }
    }
    
}


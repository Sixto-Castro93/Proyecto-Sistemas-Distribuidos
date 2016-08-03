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
 * @author emele_000
 */
public class Subcribir_Crear {
    public static ArrayList<String> canalesSuscritos = new ArrayList<>();
    
    public static void Subcribir_Crear(String IpServidor) throws IOException {

        JedisPool jedispool = new JedisPool(IpServidor);
        final Jedis subscriberJedis = jedispool.getResource();
        final Subscriber subscriber = new Subscriber();
        boolean bandera1 = true;
        while (bandera1) {
            System.out.println("Elija una opcion");
            System.out.println("1.-Subscribirse a un grupo existente");
            System.out.println("2.-Crear nuevo grupo");
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
            String opcion = reader1.readLine();
            switch (opcion) {
                case "1":
                    int i = 1;
                    System.out.println("Elija el canal para subscribirse:");
                    List<String> listaCanales = subscriberJedis.pubsubChannels("*");
                    if (listaCanales.isEmpty()) {
                        System.out.println("No hay canales por el momento, cree uno en su defecto");
                        opcion = "2";
                    } else {
                        HashMap Base = new HashMap();
                        for (String canal : listaCanales) {

                            Base.put(Integer.toString(i), canal);
                            System.out.println("canal_" + i + ": " + canal);
                            i++;
                        }
                        String opcion_canal = reader1.readLine();
                        final String cn = (String) Base.get(opcion_canal);
                        if (cn != null) {
                            canalesSuscritos.add(cn);    
                            System.out.println("Ingrese un nombre para identificarse en este grupo");
                            String name = reader1.readLine();
                            if (name != null) {

                                bandera1 = false;
                                System.out.println("Subscrito a  " + cn + " con nombre :" + name);
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {

                                            subscriberJedis.subscribe(subscriber, (String) cn);
                                            System.out.println("Subscricion terminada a: " + cn);

                                        } catch (Exception e) {
                                            System.out.println("Subscribing failed." + e);
                                        }
                                    }
                                }).start();
                            }
                        } else {
                            System.out.println("Ese canal no esta dentro del rango de opciones");
                        }
                        break;
                    }

                case "2":
                    System.out.println("Ingrese el nombre del canal:");
                    final String canal_new = reader1.readLine();
                    if (canal_new != null) {
                        canalesSuscritos.add(canal_new);
                        System.out.println("Ingrese un nombre para identificarse en este grupo");
                        String name = reader1.readLine();
                        if (name != null) {

                            bandera1 = false;
                            System.out.println("Subscrito a  " + canal_new + " con nombre :" + name);

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {

                                        subscriberJedis.subscribe(subscriber, (String) canal_new);
                                        System.out.println("Subscricion terminada a: " + canal_new);

                                    } catch (Exception e) {
                                        System.out.println("Subscribing failed." + e);
                                    }
                                }
                            }).start();
                        }
                    }
                    break;
                default:
                    break;
            }
        }

    }
}

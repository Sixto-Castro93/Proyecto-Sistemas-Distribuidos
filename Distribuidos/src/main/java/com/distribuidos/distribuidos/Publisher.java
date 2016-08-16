/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distribuidos.distribuidos;
import static com.distribuidos.distribuidos.Subscriber.canalesCliente;
import static com.distribuidos.distribuidos.Subscriber.listaMensaje;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import redis.clients.jedis.Jedis;

/**
 *
 * @author Sixto
 */

public class Publisher
{
    private final Jedis publisherJedis ;
    private final String channel_name;
        private final String name_user;
    public static boolean valida;
  
    public Publisher(Jedis publisherJedis, String channel,String user)
    {
           this.publisherJedis = publisherJedis;
            this.channel_name = channel;
            this.name_user=user;
    }
    public void start()
    {
           try
      {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  
             while (true)
             {
                  String line = reader.readLine();
                  if (!"exit".equals(line)) {
                     publisherJedis.publish(channel_name,name_user+":"+ line);

                    }
            else {
                    break;
                 }
                }
        }
    catch (IOException e) {
                System.out.println("IO failure while reading input, e");
        }
    }
}

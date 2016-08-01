/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RedisChat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import redis.clients.jedis.Jedis;

/**
 *
 * @author Sixto
 */

public class Publisher
{
    private final Jedis publisherJedis ;
    private final String channel_name;
  
    public Publisher(Jedis publisherJedis, String channel)
    {
           this.publisherJedis = publisherJedis;
            this.channel_name = channel;
    }
    public void start()
    {
        System.out.println("Type your message....exit for terminate");
        try
      {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  
             while (true)
             {
                  String line = reader.readLine();
                  if (!"exit".equals(line)) {
                     publisherJedis.publish(channel_name, line);
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
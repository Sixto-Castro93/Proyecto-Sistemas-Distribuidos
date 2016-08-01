/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RedisChat;
import redis.clients.jedis.JedisPubSub;
/**
 *
 * @author Sixto
 */

public class Subscriber extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message)
    {
            System.out.println("Message received from channel: "+channel+ " Msg: " + message);
    }
    @Override
        public void onPMessage(String pattern, String channel, String message) {
        }
    @Override
        public void onSubscribe(String channel, int subscribedChannels) {
    }
    @Override
        public void onUnsubscribe(String channel, int subscribedChannels) {
    }
    @Override
        public void onPUnsubscribe(String pattern, int subscribedChannels) {
    }
    @Override
        public void onPSubscribe(String pattern, int subscribedChannels) {
    }
     }

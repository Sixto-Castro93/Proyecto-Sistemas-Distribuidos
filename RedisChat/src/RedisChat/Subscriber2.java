/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RedisChat;

import static RedisChat.VentanaChat.jScrollPane1;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JScrollBar;
import redis.clients.jedis.JedisPubSub;

/**
 *
 * @author USUARIO
 */
public class Subscriber2 extends JedisPubSub {
    public static HashMap canalesCliente = new HashMap();
    public static ArrayList<String> listaMensaje = new ArrayList<>();
     
    @Override
    public void onMessage(String channel, String message)
    {
    //    System.out.println("Message received from channel: "+channel+ " Msg: " + message);
           
            ArrayList<String> lista;
            System.out.println(channel);
            System.out.println(VentanaVerGrupos.chats);
            if(VentanaVerGrupos.chats.containsKey(channel)){
                System.out.println("Mensaje: " + message);
                VentanaVerGrupos.chats.get(channel).addElement(message);
                //VentanaChat.listModel.addElement(message);
            }
            if(VentanaVerGrupos.canalVerifica.equals(channel)){
                System.out.println("Mensaje: " + message);
                //VentanaChat.listModel.addElement(message);
            }
            if(canalesCliente.containsKey(channel)){
                lista= (ArrayList)canalesCliente.get(channel);
                lista.add(message);
                canalesCliente.put(channel, lista);


            } else {
                listaMensaje = new ArrayList<>();
                listaMensaje.add(message);
                canalesCliente.put(channel, listaMensaje);

            }
                
            
            
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

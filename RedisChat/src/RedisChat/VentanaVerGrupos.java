/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RedisChat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *
 * @author USUARIO
 */
public class VentanaVerGrupos extends javax.swing.JFrame {

    /**
     * Creates new form VentanaVerGrupos
     */
    public static String canalVerifica = "";
    public static HashMap<String, DefaultListModel> chats = new HashMap<>();
    private final Jedis publisherJedis;
    private final JedisPool jedispool;
    private int indexMenuItem=0;
    private String nombreCanalEscogido=null;
    
    public VentanaVerGrupos(final HashMap<String, String> canales, JedisPool jedispool) {
        initComponents();
        final VentanaVerGrupos vg = this;
        this.jedispool = jedispool;
        publisherJedis = jedispool.getResource();
        
        final DefaultListModel listModel = new DefaultListModel();
        Iterator<String> cns = canales.keySet().iterator();
        String claveCanal;
        while (cns.hasNext()) {
            claveCanal = cns.next();
            listModel.addElement(claveCanal);
        }
        jList1 = new JList(listModel);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(jList1);
        jList1.addMouseListener(new MouseAdapter() {
            
            public void mouseClicked(MouseEvent evt) {
                ArrayList<String> mensajes = null;
                JList list = (JList)evt.getSource();
                if(evt.getClickCount()== 1){
                        if(evt.getButton()==MouseEvent.BUTTON3){
                            int index = list.locationToIndex(evt.getPoint());
                            String nombre= listModel.getElementAt(index).toString();
                            System.out.println(nombre);
                            jPopupMenu1.show(list, evt.getX(), evt.getY());
                            indexMenuItem=index;
                            nombreCanalEscogido=nombre;
                            //eliminarCanalMenuItem.addActionListener(new elminarCanalActionListener(index,nombre));
                            
                        }                     
                }
                if (evt.getClickCount() == 2 && evt.getButton()==MouseEvent.BUTTON1) {
                    int index = list.locationToIndex(evt.getPoint());
                    String nombreCanal = jList1.getSelectedValue().toString();
                    canalVerifica=nombreCanal;
                    
                    if ((nombreCanal) != null){
                        mensajes =  (ArrayList<String>) Subscriber2.canalesCliente.get(nombreCanal);
                        System.out.println(mensajes);
                    }
                    
                    
                    
                    VentanaChat v = null;
                    try {
                        v = new VentanaChat(mensajes,publisherJedis,nombreCanal,canales.get(nombreCanal));
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaVerGrupos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    chats.put(nombreCanal, v.getListModel());
                    v.setVisible(true);
                    v.setLocationRelativeTo(null);
                    vg.dispose();
                    
                    // Double-click detected
                } else if (evt.getClickCount() == 3) {

                    // Triple-click detected
                    int index = list.locationToIndex(evt.getPoint());
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        eliminarCanalMenuItem = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        eliminarCanalMenuItem.setText("Eliminar Canal");
        eliminarCanalMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarCanalMenuItemActionPerformed(evt);
            }
        });
        jPopupMenu1.add(eliminarCanalMenuItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        jLabel1.setFont(new java.awt.Font("Traditional Arabic", 1, 24)); // NOI18N
        jLabel1.setText("Escoja un Grupo para ver chat");

        jLabel2.setFont(new java.awt.Font("Traditional Arabic", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Grupos Disponibles:");

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void eliminarCanalMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarCanalMenuItemActionPerformed
        // TODO add your handling code here:
        System.out.println(indexMenuItem+" "+nombreCanalEscogido);
        Subscriber2.canalesCliente.remove(nombreCanalEscogido);
        Subcribir_Crear.canalesSuscritos.remove(nombreCanalEscogido);
        VentanaCliente.canalesSuscritos.remove(nombreCanalEscogido);
        System.out.println("Usted acaba de eliminar el canal/grupo "+ nombreCanalEscogido);
        Subscriber2 subscriber = VentanaCliente.subscriberCanales.get(nombreCanalEscogido);
        subscriber.unsubscribe();
        jedispool.returnResource(publisherJedis);
        ((DefaultListModel)jList1.getModel()).removeElementAt(indexMenuItem);
    }//GEN-LAST:event_eliminarCanalMenuItemActionPerformed
    
    private class elminarCanalActionListener implements ActionListener {
        private String nombreCanal;

        private int index;
        //private 
        public elminarCanalActionListener(int index,String nombreCanal) {
            this.nombreCanal = nombreCanal;

            this.index = index;
        }
        public void actionPerformed(ActionEvent e) {
            System.out.println(eliminarCanalMenuItem.isSelected());
            
            if(eliminarCanalMenuItem.isSelected()){
                System.out.println(index+" "+nombreCanal);
                Subscriber2.canalesCliente.remove(nombreCanal);
                Subcribir_Crear.canalesSuscritos.remove(nombreCanal);
                VentanaCliente.canalesSuscritos.remove(nombreCanal);
                System.out.println("Usted acaba de eliminar el canal/grupo "+ nombreCanal);
                Subscriber2 subscriber = VentanaCliente.subscriberCanales.get(nombreCanal);
                subscriber.unsubscribe();
                jedispool.returnResource(publisherJedis);
                ((DefaultListModel)jList1.getModel()).removeElementAt(index);
            }
            
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaVerGrupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaVerGrupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaVerGrupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaVerGrupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new VentanaVerGrupos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem eliminarCanalMenuItem;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

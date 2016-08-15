/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RedisChat;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *
 * @author USUARIO
 */
public class VentanaSuscribir extends javax.swing.JFrame {

    public static HashMap<String, String> canalesSuscritos;
    public static HashMap<String, Subscriber2> subscriberCanales;
    private final Subscriber2 subscriber;
    String nombreCanal;
    final Jedis subscriberJedis;
Component padre;
    /**
     * Creates new form VentanaSuscribir
     */
    public VentanaSuscribir(String ipServidor, final HashMap<String, String> canalesSuscritos, HashMap<String, Subscriber2> subscriberCanales, Component suscribirCrearDialog) {
        initComponents();
        padre=suscribirCrearDialog;
        this.canalesSuscritos = canalesSuscritos;
        this.subscriberCanales = subscriberCanales;
        HashMap<String, Subscriber> suscriberCanales = new HashMap<>();
        JedisPool jedispool = new JedisPool(ipServidor);
        subscriberJedis = jedispool.getResource();
        subscriber = new Subscriber2();
        List<String> listaCanales = subscriberJedis.pubsubChannels("*");
        final DefaultListModel listModel = new DefaultListModel();
        if (listaCanales.isEmpty()) {
            System.out.println("No hay canales por el momento, cree uno en su defecto");
            jScrollPane1.setEnabled(false);
        } else {
            HashMap Base = new HashMap();
            for (String canal : listaCanales) {
                listModel.addElement(canal);
            }
            Font font;
            font = new Font("Tempus Sans ITC", Font.BOLD, 16);
            jList1 = new JList(listModel);
            jList1.setFont(font);
            jList1.setBackground(Color.DARK_GRAY);
            jList1.setForeground(Color.WHITE);
            jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            jScrollPane1.setViewportView(jList1);
            jList1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    JList list = (JList) evt.getSource();
                    if (evt.getClickCount() == 2) {
                        int index = list.locationToIndex(evt.getPoint());
                        nombreCanal = jList1.getSelectedValue().toString();
                        String canal_verificacion = (String) canalesSuscritos.get(nombreCanal);
                        if (canal_verificacion != null) {
                             Icon imagen;
                imagen=new ImageIcon(ClassLoader.getSystemResource("imagenes/error_opt.jpg"));
         
                            JOptionPane.showMessageDialog(aceptarBoton, "Ya se encuentra registrado en este canal", "Error", JOptionPane.ERROR_MESSAGE,imagen);
                        } else {
                            nombreUsuarioField1.setEditable(true);
                            aceptarBoton.setEnabled(true);
                            nombreUsuarioField1.setText(null);
                            nombreUsuarioField1.setEnabled(true);
                            nombreUsuarioField1.setText("");
                        }

                        // Double-click detected
                    }
                }
            });
        }
    }
   public Image getIconImage() {
        Image retValeu = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/logo.jpe"));
        return retValeu;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        nombreUsuarioField1 = new javax.swing.JTextField();
        aceptarBoton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setIconImage(getIconImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(0, 102, 102));
        jScrollPane1.setFont(new java.awt.Font("MV Boli", 1, 16)); // NOI18N

        jList1.setBackground(java.awt.Color.darkGray);
        jList1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 16)); // NOI18N
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 420, 140));

        jLabel1.setFont(new java.awt.Font("Traditional Arabic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Escoja un Grupo (Doble clic)");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Traditional Arabic", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Grupos Disponibles:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        jButton1.setBackground(new java.awt.Color(0, 51, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 370, -1, -1));

        jLabel5.setBackground(new java.awt.Color(0, 51, 51));
        jLabel5.setFont(new java.awt.Font("Traditional Arabic", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre de Usuario:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        nombreUsuarioField1.setEditable(false);
        nombreUsuarioField1.setText("                   Desabilitado");
        nombreUsuarioField1.setEnabled(false);
        nombreUsuarioField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreUsuarioField1ActionPerformed(evt);
            }
        });
        getContentPane().add(nombreUsuarioField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 190, -1));

        aceptarBoton.setBackground(new java.awt.Color(0, 51, 51));
        aceptarBoton.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        aceptarBoton.setForeground(new java.awt.Color(255, 255, 255));
        aceptarBoton.setText("Aceptar");
        aceptarBoton.setEnabled(false);
        aceptarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarBotonActionPerformed(evt);
            }
        });
        getContentPane().add(aceptarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/escogergrupo_opt.jpg"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       padre.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void aceptarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarBotonActionPerformed
        // TODO add your handling code here:
         Icon imagen;
                imagen=new ImageIcon(ClassLoader.getSystemResource("imagenes/OK_opt.png"));
           Icon imagen2;
                imagen2=new ImageIcon(ClassLoader.getSystemResource("imagenes/error_opt.jpg"));
         
        if (nombreUsuarioField1.getText() != null && !(nombreUsuarioField1.getText().equals(""))) {
            subscriberCanales.put(nombreCanal, subscriber);
            canalesSuscritos.put(nombreCanal, nombreUsuarioField1.getText().toString());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        subscriberJedis.subscribe(subscriber, nombreCanal);
                        //System.out.println("Subscricion terminada a: " + cn);

                    } catch (Exception e) {
                        System.out.println("Subscribing failed." + e);
                        JOptionPane.showMessageDialog(null, "Suscripción falló", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }).start();
            JOptionPane.showMessageDialog(this, "Se ha suscrito al grupo con éxito","",JOptionPane.INFORMATION_MESSAGE,imagen);
            this.setVisible(false);
            padre.setVisible(true);

        }
        else {
        JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre válido","",JOptionPane.INFORMATION_MESSAGE,imagen2);
            }
    }//GEN-LAST:event_aceptarBotonActionPerformed

    private void nombreUsuarioField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreUsuarioField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreUsuarioField1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
      padre.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(VentanaSuscribir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaSuscribir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaSuscribir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaSuscribir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                /*VentanaSuscribir v = new VentanaSuscribir();
                 v.setBackground(Color.yellow);
                 v.setVisible(true);*/
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarBoton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombreUsuarioField1;
    // End of variables declaration//GEN-END:variables
}

package Sinarelektronikapp.util;

import java.awt.Dialog;

public class loading extends javax.swing.JDialog {

    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private int val = 0;
    
    public loading(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();        
    }

    public loading(Dialog owner, boolean modal) {
        super(owner, modal);
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Please Wait......");
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/loading_animation.gif"))); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 10, 180, 130);

        getContentPane().add(jPanel1);

        setSize(new java.awt.Dimension(180, 141));
        setLocationRelativeTo(null);
    }

}

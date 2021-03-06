/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.pengaturanBarangBonusKaryawan.setupBonus;

import Sinarelektronikapp.config.InternetProtocol;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author FAUZI
 */
public class JIFSetupBonusView extends javax.swing.JInternalFrame {

    /**
     * Creates new form SetupBonusView
     */
    public JIFSetupBonusView() {
        initComponents();
        loadDatabase();
        setEnableInputan(false);
    }
    
    public void setEnableInputan(Boolean b){
        txtBonusLangsungBK.setEditable(b);
        txtPatokanBB.setEditable(b);
        txtPatokanBK.setEditable(b);
        txtBonusLangsungBB.setEditable(b);
    }
    
    private Connection connection;
            
	
    static InternetProtocol ip1 = new InternetProtocol();    
    public void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JIFSetupBonusView.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://"+ip1.getIpServer()+"/sinarelektronik?;", "root", "P@ssw0rd");
        } catch (SQLException ex) {
            Logger.getLogger(JIFSetupBonusView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPatokanBK = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtBonusLangsungBK = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtPatokanBB = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtBonusLangsungBB = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btUbah = new javax.swing.JButton();
        btSimpan = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Setup Bonus Insentif");

        jPanel3.setLayout(new java.awt.GridLayout(2, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Barang Kecil"));
        jPanel1.setLayout(new java.awt.GridLayout(2, 2));

        jLabel1.setText("Patokan :");
        jPanel1.add(jLabel1);

        txtPatokanBK.setEditable(false);
        txtPatokanBK.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jPanel1.add(txtPatokanBK);

        jLabel2.setText("Bonus Langsung :");
        jPanel1.add(jLabel2);

        txtBonusLangsungBK.setEditable(false);
        txtBonusLangsungBK.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jPanel1.add(txtBonusLangsungBK);

        jPanel3.add(jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Barang Besar"));
        jPanel2.setLayout(new java.awt.GridLayout(2, 2));

        jLabel3.setText("Patokan :");
        jPanel2.add(jLabel3);

        txtPatokanBB.setEditable(false);
        txtPatokanBB.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jPanel2.add(txtPatokanBB);

        jLabel4.setText("Bonus Langsung :");
        jPanel2.add(jLabel4);

        txtBonusLangsungBB.setEditable(false);
        txtBonusLangsungBB.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jPanel2.add(txtBonusLangsungBB);

        jPanel3.add(jPanel2);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setLayout(new java.awt.GridLayout(1, 2));

        btUbah.setText("Ubah");
        btUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUbahActionPerformed(evt);
            }
        });
        jPanel4.add(btUbah);

        btSimpan.setText("Simpan");
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });
        jPanel4.add(btSimpan);

        getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUbahActionPerformed
        // TODO add your handling code here:
        setEnableInputan(true);
        txtPatokanBK.requestFocus();
    }//GEN-LAST:event_btUbahActionPerformed

    
    private void btSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanActionPerformed
        // TODO add your handling code here:
        koneksi();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE setupbonus set patokanbk=?, bonuslangsungbk=?, patokanbb=?, bonuslangsungbb=?");
            ps.setString(1, txtPatokanBK.getText());
            ps.setString(2, txtBonusLangsungBK.getText());
            ps.setString(3, txtPatokanBB.getText());
            ps.setString(4, txtBonusLangsungBB.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "pengaturan bonus telah tersimpan", "perhatian", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error karena = "+e);
        }
        loadDatabase();
        setEnableInputan(false);
    }//GEN-LAST:event_btSimpanActionPerformed

    
    public void loadDatabase(){
        koneksi();
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("select * from setupbonus");
            if(rs.next()){
                txtPatokanBK.setText(rs.getString("patokanbk"));
                txtBonusLangsungBK.setText(rs.getString("bonuslangsungbk"));
                txtPatokanBB.setText(rs.getString("patokanbb"));
                txtBonusLangsungBB.setText(rs.getString("bonuslangsungbb"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error karena = "+e);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSimpan;
    private javax.swing.JButton btUbah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField txtBonusLangsungBB;
    private javax.swing.JTextField txtBonusLangsungBK;
    private javax.swing.JTextField txtPatokanBB;
    private javax.swing.JTextField txtPatokanBK;
    // End of variables declaration//GEN-END:variables
}

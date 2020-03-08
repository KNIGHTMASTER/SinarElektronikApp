/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.report.view;

import Sinarelektronikapp.config.HostName;
import Sinarelektronikapp.masterdata.report.core.ReportParam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class JIFReportReturBarangBesar extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFReportReturBarangBesar
     */
    public JIFReportReturBarangBesar() {
        initComponents();
        koneksi();
        loadSupplier();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTransaksi1 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTanggalAwal1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtTanggalAkhir1 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        cmbSupplier = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        btRetur = new javax.swing.JButton();
        btDetailRetur = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        panelTransaksi1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Laporan Retur Barang Besar"));
        panelTransaksi1.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(4, 2));

        jLabel3.setText("Tanggal Awal :");
        jPanel1.add(jLabel3);
        jPanel1.add(txtTanggalAwal1);

        jLabel4.setText("Tanggal Akhir :");
        jPanel1.add(jLabel4);
        jPanel1.add(txtTanggalAkhir1);

        jLabel5.setText("Supplier :");
        jPanel1.add(jLabel5);

        jPanel1.add(cmbSupplier);

        jLabel6.setText("Status :");
        jPanel1.add(jLabel6);

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "belum diproses", "sedang diproses" }));
        jPanel1.add(cmbStatus);

        panelTransaksi1.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridLayout(1, 2));

        btRetur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/returtombol.png"))); // NOI18N
        btRetur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReturActionPerformed(evt);
            }
        });
        jPanel2.add(btRetur);

        btDetailRetur.setText("Detail Retur");
        btDetailRetur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDetailReturActionPerformed(evt);
            }
        });
        jPanel2.add(btDetailRetur);

        panelTransaksi1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(panelTransaksi1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public Connection conn = null;
    HostName ip1 = new HostName();
    public void koneksi(){
        String url = "jdbc:mysql://"+ip1.getIpServer()+"/sinarelektronik?;";
        String pass = "P@ssw0rd";
        String user = "root";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JIFReportReturBarangBesar.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(JIFReportReturBarangBesar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadSupplier(){
        Statement s = null;
        try {
            s = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(JIFReportReturBarangBesar.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = null;
        try {
            rs = s.executeQuery("select nama from supplier");
        } catch (SQLException ex) {
            Logger.getLogger(JIFReportReturBarangBesar.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs.next()){
                String supplier = null;
                supplier = rs.getString("nama");
                cmbSupplier.addItem(supplier);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JIFReportReturBarangBesar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void btReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReturActionPerformed
        // TODO add your handling code here:
        if(txtTanggalAwal1.getDate()==null){
            txtTanggalAwal1.requestFocus();
            JOptionPane.showMessageDialog(null, "Tanggal Awal masih kosong");
            return;
        }else if (txtTanggalAkhir1.getDate() == null){
            txtTanggalAkhir1.requestFocus();
            JOptionPane.showMessageDialog(null, "Tanggal Akhir masih kosong");
            return;
        }
        else{
            ReportParam rp=new ReportParam();
            rp.buildReportDoubleParam("returbarangbesar", txtTanggalAwal1.getDate(), txtTanggalAkhir1.getDate());
        }
    }//GEN-LAST:event_btReturActionPerformed

    private void btDetailReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDetailReturActionPerformed
        // TODO add your handling code here:
        String supplier = cmbSupplier.getSelectedItem().toString();
        String status = cmbStatus.getSelectedItem().toString();
        if(txtTanggalAwal1.getDate()==null){
            txtTanggalAwal1.requestFocus();
            JOptionPane.showMessageDialog(null, "Tanggal Awal masih kosong");
            return;
        }else if (txtTanggalAkhir1.getDate() == null){
            txtTanggalAkhir1.requestFocus();
            JOptionPane.showMessageDialog(null, "Tanggal Akhir masih kosong");
            return;
        }
        else{
            ReportParam rp=new ReportParam();
            rp.buildReportQuadParam("detailreturbarangbesar", txtTanggalAwal1.getDate(), txtTanggalAkhir1.getDate(), supplier, status);
        }
    }//GEN-LAST:event_btDetailReturActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDetailRetur;
    private javax.swing.JButton btRetur;
    private javax.swing.JComboBox cmbStatus;
    private javax.swing.JComboBox cmbSupplier;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelTransaksi1;
    private com.toedter.calendar.JDateChooser txtTanggalAkhir1;
    private com.toedter.calendar.JDateChooser txtTanggalAwal1;
    // End of variables declaration//GEN-END:variables
}

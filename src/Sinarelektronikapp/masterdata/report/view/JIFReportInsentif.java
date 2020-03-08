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
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class JIFReportInsentif extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFReportInsentif
     */
    public JIFReportInsentif() {
        initComponents();
        loadPenjual();
    }

    private Connection conn;
    HostName ip = new HostName();
    
    public void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+ip.getIpServer()+"/sinarelektronik?;", "root", "P@ssw0rd");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error koneksi pada barangkecil view karena = "+ex);
        }
    }
    
    public void loadPenjual(){
        koneksi();
        try{
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT nama FROM penjualbb");
            while (rs.next()) {                
                cmbPenjual.addItem(rs.getString("nama"));
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam mendapatkan nama penjual", "Perhatian", JOptionPane.ERROR_MESSAGE);
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

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTanggalAwal = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtTanggalAkhir = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        cmbPenjual = new javax.swing.JComboBox();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Laporan Insentif Karyawan Barang Besar");

        jPanel2.setLayout(new java.awt.BorderLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/login.png"))); // NOI18N
        jButton1.setText("Create");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel1.setLayout(new java.awt.GridLayout(3, 2));

        jLabel1.setText("Tanggal Awal :");
        jPanel1.add(jLabel1);
        jPanel1.add(txtTanggalAwal);

        jLabel2.setText("Tanggal Akhir :");
        jPanel1.add(jLabel2);
        jPanel1.add(txtTanggalAkhir);

        jLabel3.setText("Penjual :");
        jPanel1.add(jLabel3);

        jPanel1.add(cmbPenjual);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(txtTanggalAwal.getDate()==null){
            txtTanggalAwal.requestFocus();
            JOptionPane.showMessageDialog(null, "Tanggal Awal masih kosong", "Perhatian", JOptionPane.WARNING_MESSAGE);
            return;
        }else if (txtTanggalAkhir.getDate() == null){
            txtTanggalAkhir.requestFocus();
            JOptionPane.showMessageDialog(null, "Tanggal Akhir masih kosong", "Perhatian", JOptionPane.WARNING_MESSAGE);
            return;
        }
        else{
            ReportParam rp=new ReportParam();
            koneksi();
            int jumlahPersentaseBonus = 0;
            try {                
                Statement s = conn.createStatement();
                ResultSet rs = s.executeQuery("select insentif from penjualbb where nama like '%"+cmbPenjual.getSelectedItem().toString()+"%'");
                if(rs.next()){
                    jumlahPersentaseBonus = rs.getInt("insentif");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Tidak dapat memperoleh persentase insetif karyawan barangkecil besar", "perhatian", JOptionPane.ERROR_MESSAGE);
            }
            rp.buildReportLimaParamInsentif("insentif", cmbPenjual.getSelectedItem().toString(), txtTanggalAwal.getDate(), txtTanggalAkhir.getDate(), jumlahPersentaseBonus);
        }        
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbPenjual;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.toedter.calendar.JDateChooser txtTanggalAkhir;
    private com.toedter.calendar.JDateChooser txtTanggalAwal;
    // End of variables declaration//GEN-END:variables
}

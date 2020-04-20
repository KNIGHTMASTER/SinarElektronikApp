/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.barangbesar.view;

import Sinarelektronikapp.masterdata.barangkecil.view.*;
import Sinarelektronikapp.config.InternetProtocol;
import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fauzi
 */
public class JIFHistoryBarangBesar extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFUpdateGlobalBK
     */
    
    private final String SELECTALLBARANG = "SELECT * FROM barangbesarhistory";    
    
    private String berdasarkan = "";
    
    private final String SORTBARANGBY = "SELECT * FROM barangbesarhistory ORDER BY ";
            
    
    public JIFHistoryBarangBesar() {
        koneksi();
        initComponents();
        loadData();
    }

    private Connection conn = null;
    
    InternetProtocol ip = new InternetProtocol();    
    
    public void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+ip.getIpServer()+"/sinarelektronik?;", "root", "5430trisin9");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error koneksi pada barangkecil view karena = "+ex);
        }
    }
    
    private void loadData(){
        DefaultTableModel model = new DefaultTableModel();
        Statement s = null;
        try{
            s = conn.createStatement();
            ResultSet rs = s.executeQuery(SELECTALLBARANG);
            String [] kolom = {"tanggal", "idbarang", "nama", "tipe", "merek", "modal", "grosir", "eceran", "stok", "supplier"};
            model.setColumnIdentifiers(kolom);
            String [] data = new String[11];
            while (rs.next()) {           
                data[0] = rs.getString("tanggal");
                data[1] = rs.getString("idbarang");
                data[2] = rs.getString("namabarang");
                data[3] = rs.getString("tipe");
                data[4] = rs.getString("merek");
                data[5] = rs.getString("modal");
                data[6] = rs.getString("grosir");
                data[7] = rs.getString("eceran");
                data[8] = rs.getString("stok");
                data[9] = rs.getString("supplier");
                model.addRow(data);
            }            
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Terjadi Error Pada Load Data Karena = "+exception, "Perhatian", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFHistoryBarang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        tabelBarang.setModel(model);
        this.setSize(getWidth(), getHeight());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        atas = new javax.swing.JPanel();
        first = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cmbKataKunci1 = new javax.swing.JComboBox();
        cmbKataKunci2 = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        txtKataKunci1 = new javax.swing.JTextField();
        panelPilihan = new javax.swing.JPanel();
        teks = new javax.swing.JPanel();
        txtKataKunci2 = new javax.swing.JTextField();
        tanggal = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        btCari = new javax.swing.JButton();
        tengah = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbCari = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("History Barang Besar");

        atas.setLayout(new java.awt.BorderLayout());

        first.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Pencarian Barang"));
        first.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        cmbKataKunci1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbKataKunci1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id barangkecil", "nama barangkecil", "tipe", "merek", "stok", "supplier" }));
        jPanel1.add(cmbKataKunci1);

        cmbKataKunci2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbKataKunci2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id barangkecil", "nama barangkecil", "tipe", "merek", "stok", "supplier" }));
        cmbKataKunci2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKataKunci2ActionPerformed(evt);
            }
        });
        jPanel1.add(cmbKataKunci2);

        first.add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel4.setLayout(new java.awt.GridLayout(2, 0));

        txtKataKunci1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKataKunci1ActionPerformed(evt);
            }
        });
        jPanel4.add(txtKataKunci1);

        panelPilihan.setLayout(new java.awt.CardLayout());

        teks.setLayout(new java.awt.BorderLayout());

        txtKataKunci2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKataKunci2ActionPerformed(evt);
            }
        });
        teks.add(txtKataKunci2, java.awt.BorderLayout.CENTER);

        panelPilihan.add(teks, "teks");

        tanggal.setLayout(new java.awt.GridLayout(1, 2));

        jDateChooser1.setToolTipText("Tanggal Awal");
        tanggal.add(jDateChooser1);

        jDateChooser2.setToolTipText("Tanggal Akhir");
        tanggal.add(jDateChooser2);

        panelPilihan.add(tanggal, "tanggal");

        jPanel4.add(panelPilihan);

        first.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new java.awt.BorderLayout());

        btCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/cari.png"))); // NOI18N
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });
        jPanel5.add(btCari, java.awt.BorderLayout.CENTER);

        first.add(jPanel5, java.awt.BorderLayout.EAST);

        atas.add(first, java.awt.BorderLayout.CENTER);

        tengah.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Pengurutan Barang"));
        tengah.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Berdasarkan :");
        tengah.add(jLabel1, java.awt.BorderLayout.WEST);

        cmbCari.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id barangkecil", "nama barangkecil", "tipe", "merek", "stok", "supplier" }));
        cmbCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCariActionPerformed(evt);
            }
        });
        tengah.add(cmbCari, java.awt.BorderLayout.CENTER);

        atas.add(tengah, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(atas, java.awt.BorderLayout.PAGE_START);

        jPanel3.setLayout(new java.awt.BorderLayout());

        tabelBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelBarang.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabelBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelBarangMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelBarang);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    String comboConverter(String value){
        String hasil = "";
        switch(value){
            case "id barangkecil": hasil = "idbarang"; break;
            case "nama barangkecil": hasil = "namabarang"; break;
            default:hasil = value;
        }
        return hasil;
    }
    
    private void cari(){
        String kategori1 = "";    
        String kategori2 = "";    
        String kataKunci1 = "";    
        String kataKunci2 = "";        
        
        String SEARCHBARANGBY = "";
                
        kategori1 = comboConverter(cmbKataKunci1.getSelectedItem().toString());
        kategori2 = comboConverter(cmbKataKunci2.getSelectedItem().toString());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        if(kategori2.trim().equals("tanggal")){
            kataKunci1 = txtKataKunci1.getText();
            Date tanggal1 = jDateChooser1.getDate();
            Date tanggal2 = jDateChooser2.getDate();
            String tgl1 = dateFormat.format(tanggal1);
            String tgl2 = dateFormat.format(tanggal2);
            SEARCHBARANGBY = "SELECT * FROM barangbesarhistory WHERE "+kategori1+" LIKE '%"+kataKunci1+"%' AND (tanggal>='"+tgl1+"' and tanggal<='"+tgl2+"')";
        }else{
            kataKunci1 = txtKataKunci1.getText();
            kataKunci2 = txtKataKunci2.getText();        
            
            SEARCHBARANGBY = "SELECT * FROM barangbesarhistory WHERE "+kategori1+" LIKE '%"+kataKunci1+"%' AND "+kategori2+" LIKE '%"+kataKunci2+"%'";   
        }

        DefaultTableModel model = new DefaultTableModel();
        String [] kolom = {"tanggal", "idbarang", "nama", "tipe", "merek", "modal", "grosir", "eceran", "stok", "supplier"};
        model.setColumnIdentifiers(kolom);
        Statement s = null;
        try{
            s = conn.createStatement();
            ResultSet rs = s.executeQuery(SEARCHBARANGBY);
            String [] data = new String[10];
            while(rs.next()){
                data[0] = rs.getString("tanggal");
                data[1] = rs.getString("idbarang");
                data[2] = rs.getString("namabarang");
                data[3] = rs.getString("tipe");
                data[4] = rs.getString("merek");
                data[5] = rs.getString("modal");
                data[6] = rs.getString("grosir");
                data[7] = rs.getString("eceran");
                data[8] = rs.getString("stok");
                data[9] = rs.getString("supplier");
                model.addRow(data);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error dalam pencarian barangkecil karena = "+e, "Perhatian", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFHistoryBarang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        tabelBarang.setModel(model);        
    }
    
    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_btCariActionPerformed

    private void cmbCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCariActionPerformed
        // TODO add your handling code here:
        berdasarkan = comboConverter(cmbCari.getSelectedItem().toString());
        DefaultTableModel model = new DefaultTableModel();
        Statement s = null;
        try{
            s = conn.createStatement();       
            String query = SORTBARANGBY + berdasarkan;
            ResultSet rs = s.executeQuery(query);            
            String [] kolom = {"tanggal", "idbarang", "nama", "tipe", "merek", "modal", "grosir", "eceran", "stok", "supplier"};
            model.setColumnIdentifiers(kolom);
            String [] data = new String[10];
            while (rs.next()) {           
                data[0] = rs.getString("tanggal");
                data[1] = rs.getString("idbarang");
                data[2] = rs.getString("namabarang");
                data[3] = rs.getString("tipe");
                data[4] = rs.getString("merek");
                data[5] = rs.getString("modal");
                data[6] = rs.getString("grosir");
                data[7] = rs.getString("eceran");
                data[8] = rs.getString("stok");
                data[9] = rs.getString("supplier");
                model.addRow(data);
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam Pengurutan barangkecil karena ="+exception, "Perhatian", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFHistoryBarang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        tabelBarang.setModel(model);
    }//GEN-LAST:event_cmbCariActionPerformed

    private void txtKataKunci1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKataKunci1ActionPerformed
        // TODO add your handling code here:
        txtKataKunci2.requestFocus();
    }//GEN-LAST:event_txtKataKunci1ActionPerformed

    private void txtKataKunci2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKataKunci2ActionPerformed
        // TODO add your handling code here:
        btCari.doClick();
    }//GEN-LAST:event_txtKataKunci2ActionPerformed

    private void tabelBarangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarangMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelBarangMouseReleased

    private void cmbKataKunci2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKataKunci2ActionPerformed
        // TODO add your handling code here:        
        if(cmbKataKunci2.getSelectedItem().toString().equals("tanggal")){            
            CardLayout cl=(CardLayout) panelPilihan.getLayout();
            cl.show(panelPilihan, "tanggal");
        }else{
            CardLayout cl=(CardLayout) panelPilihan.getLayout();
            cl.show(panelPilihan, "teks");
        }
    }//GEN-LAST:event_cmbKataKunci2ActionPerformed
       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel atas;
    private javax.swing.JButton btCari;
    private javax.swing.JComboBox cmbCari;
    private javax.swing.JComboBox cmbKataKunci1;
    private javax.swing.JComboBox cmbKataKunci2;
    private javax.swing.JPanel first;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelPilihan;
    private javax.swing.JTable tabelBarang;
    private javax.swing.JPanel tanggal;
    private javax.swing.JPanel teks;
    private javax.swing.JPanel tengah;
    private javax.swing.JTextField txtKataKunci1;
    private javax.swing.JTextField txtKataKunci2;
    // End of variables declaration//GEN-END:variables
}

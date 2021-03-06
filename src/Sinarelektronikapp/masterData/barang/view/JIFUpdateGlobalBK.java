/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.barang.view;

import Sinarelektronikapp.config.InternetProtocol;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class JIFUpdateGlobalBK extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFUpdateGlobalBK
     */
    
    private final String SELECTALLBARANG = "SELECT * FROM barang";    
    
    private String berdasarkan = "";
    
    private final String SORTBARANGBY = "SELECT * FROM barang ORDER BY ";
            
    
    public JIFUpdateGlobalBK() {
        koneksi();
        initComponents();
        loadData();
    }

    private Connection conn = null;
    
    InternetProtocol ip = new InternetProtocol();    
    
    public void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+ip.getIpServer()+"/sinarelektronik?;", "root", "P@ssw0rd");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error koneksi pada barang view karena = "+ex);
        }
    }
    
    private void loadData(){
        DefaultTableModel model = new DefaultTableModel();
        Statement s = null;
        try{
            s = conn.createStatement();
            ResultSet rs = s.executeQuery(SELECTALLBARANG);
            String [] kolom = {"idbarang", "nama", "tipe", "merek", "modal", "grosir", "eceran", "stok", "supplier", "kategori"};
            model.setColumnIdentifiers(kolom);
            String [] data = new String[10];
            while (rs.next()) {           
                data[0] = rs.getString("idbarang");
                data[1] = rs.getString("namabarang");
                data[2] = rs.getString("tipe");
                data[3] = rs.getString("merek");
                data[4] = rs.getString("hargamodal");
                data[5] = rs.getString("grosir");
                data[6] = rs.getString("eceran");
                data[7] = rs.getString("stok");
                data[8] = rs.getString("supplier");
                data[9] = rs.getString("kategori");
                model.addRow(data);
            }            
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Terjadi Error Pada Load Data Karena = "+exception, "Perhatian", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFUpdateGlobalBK.class.getName()).log(Level.SEVERE, null, ex);
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

        popUpUpdate = new javax.swing.JPopupMenu();
        MenuUpdate = new javax.swing.JMenuItem();
        DialogUpdateHarga = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        txtPerubahan = new javax.swing.JTextField();
        cmbTipePerubahan = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        atas = new javax.swing.JPanel();
        first = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cmbKataKunci1 = new javax.swing.JComboBox();
        cmbKataKunci2 = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        txtKataKunci1 = new javax.swing.JTextField();
        txtKataKunci2 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btCari = new javax.swing.JButton();
        tengah = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbCari = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();

        MenuUpdate.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        MenuUpdate.setText("Update");
        MenuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuUpdateActionPerformed(evt);
            }
        });
        popUpUpdate.add(MenuUpdate);

        DialogUpdateHarga.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DialogUpdateHarga.setTitle("Update Harga Global");

        jPanel2.setLayout(new java.awt.BorderLayout());

        txtPerubahan.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jPanel2.add(txtPerubahan, java.awt.BorderLayout.CENTER);

        cmbTipePerubahan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Persentase", "Nominal" }));
        jPanel2.add(cmbTipePerubahan, java.awt.BorderLayout.WEST);

        DialogUpdateHarga.getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton1.setText("Proses");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        DialogUpdateHarga.getContentPane().add(jButton1, java.awt.BorderLayout.PAGE_END);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Update Global Barang Kecil");

        atas.setLayout(new java.awt.BorderLayout());

        first.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Pencarian Barang"));
        first.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        cmbKataKunci1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbKataKunci1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id barang", "nama barang", "tipe", "merek", "stok", "supplier", "kategori" }));
        jPanel1.add(cmbKataKunci1);

        cmbKataKunci2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbKataKunci2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id barang", "nama barang", "tipe", "merek", "stok", "supplier", "kategori" }));
        jPanel1.add(cmbKataKunci2);

        first.add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel4.setLayout(new java.awt.GridLayout(2, 0));

        txtKataKunci1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKataKunci1ActionPerformed(evt);
            }
        });
        jPanel4.add(txtKataKunci1);

        txtKataKunci2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKataKunci2ActionPerformed(evt);
            }
        });
        jPanel4.add(txtKataKunci2);

        first.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new java.awt.BorderLayout());

        btCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/cari.png"))); // NOI18N
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
        cmbCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id barang", "nama barang", "tipe", "merek", "stok", "supplier", "kategori" }));
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
            case "id barang": hasil = "idbarang"; break;
            case "nama barang": hasil = "namabarang"; break;
            default:hasil = value;
        }
        return hasil;
    }
    
    private void cari(){
        String kategori1 = "";    
        String kategori2 = "";    
        String kataKunci1 = "";    
        String kataKunci2 = "";        
        
        kategori1 = comboConverter(cmbKataKunci1.getSelectedItem().toString());
        kategori2 = comboConverter(cmbKataKunci2.getSelectedItem().toString());
        kataKunci1 = txtKataKunci1.getText();
        kataKunci2 = txtKataKunci2.getText();        
    
        String SEARCHBARANGBY = "SELECT * FROM barang WHERE "+kategori1+" LIKE '%"+kataKunci1+"%' AND "+kategori2+" LIKE '%"+kataKunci2+"%'";                

        DefaultTableModel model = new DefaultTableModel();
        String [] kolom = {"idbarang", "nama", "tipe", "merek", "modal", "grosir", "eceran", "stok", "supplier", "kategori"};
        model.setColumnIdentifiers(kolom);
        Statement s = null;
        try{
            s = conn.createStatement();
            ResultSet rs = s.executeQuery(SEARCHBARANGBY);
            String [] data = new String[10];
            while(rs.next()){
                data[0] = rs.getString("idbarang");
                data[1] = rs.getString("namabarang");
                data[2] = rs.getString("tipe");
                data[3] = rs.getString("merek");
                data[4] = rs.getString("hargamodal");
                data[5] = rs.getString("grosir");
                data[6] = rs.getString("eceran");
                data[7] = rs.getString("stok");
                data[8] = rs.getString("supplier");
                data[9] = rs.getString("kategori");                
                model.addRow(data);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error dalam pencarian barang karena = "+e, "Perhatian", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFUpdateGlobalBK.class.getName()).log(Level.SEVERE, null, ex);
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
            String [] kolom = {"idbarang", "nama", "tipe", "merek", "modal", "grosir", "eceran", "stok", "supplier", "kategori"};
            model.setColumnIdentifiers(kolom);
            String [] data = new String[10];            
            while (rs.next()) {           
                data[0] = rs.getString("idbarang");
                data[1] = rs.getString("namabarang");
                data[2] = rs.getString("tipe");
                data[3] = rs.getString("merek");
                data[4] = rs.getString("hargamodal");
                data[5] = rs.getString("grosir");
                data[6] = rs.getString("eceran");
                data[7] = rs.getString("stok");
                data[8] = rs.getString("supplier");
                data[9] = rs.getString("kategori");
                model.addRow(data);
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam Pengurutan barang karena ="+exception, "Perhatian", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFUpdateGlobalBK.class.getName()).log(Level.SEVERE, null, ex);
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
        if(evt.isPopupTrigger()){
            popUpUpdate.show(evt.getComponent(), evt.getX(), evt.getY());
        }            
    }//GEN-LAST:event_tabelBarangMouseReleased

    private double getHarga(String harga, String id){
        double hasil = 0;
        Statement s = null;
        try{
            s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT "+harga+" FROM barang WHERE idbarang = '"+id+"'");
            if(rs.next()){
                hasil = Double.valueOf(rs.getString(harga));
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam mendapatkan harga karena = "+exception, "Perhatian", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFUpdateGlobalBK.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
        return hasil;
    }
    
    private double getHargaBaru(double hargaAwal, double perubahan){
        double hasil = 0;
        hasil = (perubahan/100 * hargaAwal) + hargaAwal;
        return hasil;
    }
    
    private double getHargaBaruNominal(double hargaAwal, double perubahan){
        double hasil = 0;
        hasil = hargaAwal + perubahan;
        return hasil;
    }    
    
    
    Date date=new Date();        
    
    SimpleDateFormat formatTanggal=new SimpleDateFormat("yyyy-MM-dd");    
    
    private String loadTanggal(){        
        return formatTanggal.format(date);
    }    
    private String[] getIdentitasBarang(String id){
        String query = "SELECT * FROM barang WHERE idbarang = '"+id+"'";
        String [] hasil = new String[15];
        Statement s = null;
        try{
            s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            while(rs.next()){
                hasil[0] = rs.getString("idbarang");
                hasil[1] = rs.getString("namabarang");
                hasil[2] = rs.getString("tipe");
                hasil[3] = rs.getString("merek");
                hasil[4] = rs.getString("hargamodal");
                hasil[5] = rs.getString("grosir");
                hasil[6] = rs.getString("eceran");
                hasil[7] = rs.getString("satuan");
                hasil[8] = rs.getString("stok");
                hasil[9] = rs.getString("stok_minimum");
                hasil[10] = rs.getString("supplier");
                hasil[11] = rs.getString("keterangan");
                hasil[12] = rs.getString("garansi");
                hasil[13] = rs.getString("lamagaransi");
                hasil[14] = rs.getString("kategori");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam mendapatkan identitas barang karena = "+exception, "perhatian", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFUpdateGlobalBK.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return hasil;
    }
    
    public void insertBarangHistory(String id){
        String query = "INSERT INTO baranghistory (tanggal, idbarang, namabarang, tipe, merek, hargamodal, grosir, eceran, satuan, stok, stok_minimum, supplier, keterangan, garansi, lamagaransi, kategori) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";       
        PreparedStatement ps = null;
        String[] identitasBarang = new String[15];
        identitasBarang = getIdentitasBarang(id);
        String tanggal = loadTanggal();
        try{
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(query, ps.RETURN_GENERATED_KEYS);
            ps.setString(1, tanggal);
            ps.setString(2, id);
            ps.setString(3, identitasBarang[1]);
            ps.setString(4, identitasBarang[2]);
            ps.setString(5, identitasBarang[3]);
            ps.setString(6, identitasBarang[4]);
            ps.setString(7, identitasBarang[5]);
            ps.setString(8, identitasBarang[6]);
            ps.setString(9, identitasBarang[7]);
            ps.setString(10, identitasBarang[8]);
            ps.setString(11, identitasBarang[9]);
            ps.setString(12, identitasBarang[10]);
            ps.setString(13, identitasBarang[11]);
            ps.setString(14, identitasBarang[12]);
            ps.setString(15, identitasBarang[13]);
            ps.setString(16, identitasBarang[14]);            
            ps.executeUpdate();
            conn.commit();
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error insert into barang history karena = "+exception, "perhatian", JOptionPane.ERROR_MESSAGE);
            try {
                conn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(JIFUpdateGlobalBK.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFUpdateGlobalBK.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(JIFUpdateGlobalBK.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(cmbTipePerubahan.getSelectedItem().toString().equals("Persentase")){
            double persentasePerubahan = Double.valueOf(txtPerubahan.getText());
            int [] rowsSelected = tabelBarang.getSelectedRows();
            String query = ("Update barang set hargamodal=?, grosir=?, eceran=? WHERE idbarang = ?");
            for(int a=0; a<rowsSelected.length; a++){            
                String id = tabelBarang.getValueAt(rowsSelected[a], 0).toString();
                double modal = getHarga("hargamodal", id);
                double grosir = getHarga("grosir", id);
                double eceran = getHarga("eceran", id);
                insertBarangHistory(id);            
                double hasilModal = getHargaBaru(modal, persentasePerubahan);
                double hasilGrosir = getHargaBaru(grosir, persentasePerubahan);
                double hasilEceran = getHargaBaru(eceran, persentasePerubahan);
                PreparedStatement ps = null;
                try {
                    ps = conn.prepareStatement(query);
                    ps.setString(1, String.valueOf(hasilModal));
                    ps.setString(2, String.valueOf(hasilGrosir));
                    ps.setString(3, String.valueOf(hasilEceran));
                    ps.setString(4, id);                
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "error dalam update perubahan harga karena = "+ex, "perhatian", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(JIFUpdateGlobalBK.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    if(ps!=null){
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(JIFUpdateGlobalBK.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }else if(cmbTipePerubahan.getSelectedItem().toString().equals("Nominal")){
            double nominalPerubahan = Double.valueOf(txtPerubahan.getText());
            int [] rowsSelected = tabelBarang.getSelectedRows();
            String query = ("Update barang set hargamodal=?, grosir=?, eceran=? WHERE idbarang = ?");
            for(int a=0; a<rowsSelected.length; a++){            
                String id = tabelBarang.getValueAt(rowsSelected[a], 0).toString();
                double modal = getHarga("hargamodal", id);
                double grosir = getHarga("grosir", id);
                double eceran = getHarga("eceran", id);
                insertBarangHistory(id);            
                double hasilModal = getHargaBaruNominal(modal, nominalPerubahan);
                double hasilGrosir = getHargaBaruNominal(grosir, nominalPerubahan);
                double hasilEceran = getHargaBaruNominal(eceran, nominalPerubahan);
                PreparedStatement ps = null;
                try {
                    ps = conn.prepareStatement(query);
                    ps.setString(1, String.valueOf(hasilModal));
                    ps.setString(2, String.valueOf(hasilGrosir));
                    ps.setString(3, String.valueOf(hasilEceran));
                    ps.setString(4, id);                
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "error dalam update perubahan harga karena = "+ex, "perhatian", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(JIFUpdateGlobalBK.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    if(ps!=null){
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(JIFUpdateGlobalBK.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }            
        }
        cari();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void callDialogUpdateHarga(){   
        DialogUpdateHarga.dispose();
        DialogUpdateHarga.setSize(500, 200);        
        DialogUpdateHarga.show();
        DialogUpdateHarga.setModal(true);
        DialogUpdateHarga.setLocationRelativeTo(null);                        
    }
    
    private void MenuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuUpdateActionPerformed
        // TODO add your handling code here:
        callDialogUpdateHarga();
    }//GEN-LAST:event_MenuUpdateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogUpdateHarga;
    private javax.swing.JMenuItem MenuUpdate;
    private javax.swing.JPanel atas;
    private javax.swing.JButton btCari;
    private javax.swing.JComboBox cmbCari;
    private javax.swing.JComboBox cmbKataKunci1;
    private javax.swing.JComboBox cmbKataKunci2;
    private javax.swing.JComboBox cmbTipePerubahan;
    private javax.swing.JPanel first;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu popUpUpdate;
    private javax.swing.JTable tabelBarang;
    private javax.swing.JPanel tengah;
    private javax.swing.JTextField txtKataKunci1;
    private javax.swing.JTextField txtKataKunci2;
    private javax.swing.JTextField txtPerubahan;
    // End of variables declaration//GEN-END:variables
}

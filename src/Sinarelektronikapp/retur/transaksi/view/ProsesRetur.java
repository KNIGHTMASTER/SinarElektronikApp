/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.retur.transaksi.view;

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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fauzi
 */
public class ProsesRetur extends javax.swing.JPanel {

    /**
     * Creates new form ProsesRetur
     */   
    
    
    public ProsesRetur() {
        
        initComponents();           
        loadData();
        loadKodeUpdate();
    }

    public void loadData(){
        DefaultTableModel modelTable = new DefaultTableModel();        
        String[] kolom = {"id", "no_retur", "user", "kode", "nama", "jumlah", "subharga"};
        modelTable.setColumnIdentifiers(kolom);
        String[] data = new String[7];
        koneksi();
        Statement s = null;
        try{
            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM detailreturfinal");
            while (rs.next()) {                
                data[0] = rs.getString("id");
                data[1] = rs.getString("no_retur");
                data[2] = rs.getString("user");
                data[3] = rs.getString("kode");
                data[4] = rs.getString("nama");
                data[5] = rs.getString("jumlah");
                data[6] = rs.getString("subharga");
                modelTable.addRow(data);
            }
            tabelPRosesRetur.setModel(modelTable);
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam select barang proses retur karena = "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        this.setSize(getWidth(), getHeight());        
    }
    
    Connection c;
    InternetProtocol ip = new InternetProtocol();
    public void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            c = DriverManager.getConnection("jdbc:mysql://"+ip.getIpServer()+"/sinarelektronik?;", "root", "5430trisin9");
        } catch (SQLException ex) {
            Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
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

        DialogUpdateProsesRetur = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtIdUpdate = new javax.swing.JTextField();
        cmbKodeUpdate = new javax.swing.JComboBox();
        txtNamaUpdate = new javax.swing.JTextField();
        txtJumlahUpdate = new javax.swing.JTextField();
        txtSubhargaUpdate = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        Update = new javax.swing.JButton();
        Reset = new javax.swing.JButton();
        atas = new javax.swing.JPanel();
        up2 = new javax.swing.JPanel();
        panelBackgroundUp1 = new Sinarelektronikapp.swingResource.panelGradUser();
        kiri2 = new javax.swing.JPanel();
        labeling7 = new Sinarelektronikapp.swingResource.labeling();
        labeling8 = new Sinarelektronikapp.swingResource.labeling();
        labeling9 = new Sinarelektronikapp.swingResource.labeling();
        kanan1 = new javax.swing.JPanel();
        atas2 = new javax.swing.JPanel();
        txtKataKunciProsesRetur = new javax.swing.JTextField();
        btCariProsesRetur = new javax.swing.JButton();
        tengah2 = new javax.swing.JPanel();
        cmbCariPRosesRetur = new javax.swing.JComboBox();
        bawah2 = new javax.swing.JPanel();
        cmbUrutProsesRetur = new javax.swing.JComboBox();
        tengah = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPRosesRetur = new javax.swing.JTable();
        bawah = new javax.swing.JPanel();
        btProses = new javax.swing.JButton();
        btUpdate = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();

        jPanel1.setLayout(new java.awt.GridLayout(5, 0));

        jLabel1.setText("Id :");
        jPanel1.add(jLabel1);

        jLabel2.setText("Kode :");
        jPanel1.add(jLabel2);

        jLabel3.setText("Nama :");
        jPanel1.add(jLabel3);

        jLabel4.setText("Jumlah :");
        jPanel1.add(jLabel4);

        jLabel5.setText("Sub Harga :");
        jPanel1.add(jLabel5);

        DialogUpdateProsesRetur.getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setLayout(new java.awt.GridLayout(5, 0));

        txtIdUpdate.setEditable(false);
        jPanel2.add(txtIdUpdate);

        cmbKodeUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKodeUpdateActionPerformed(evt);
            }
        });
        jPanel2.add(cmbKodeUpdate);

        txtNamaUpdate.setEditable(false);
        jPanel2.add(txtNamaUpdate);

        txtJumlahUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahUpdateActionPerformed(evt);
            }
        });
        txtJumlahUpdate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtJumlahUpdateFocusLost(evt);
            }
        });
        jPanel2.add(txtJumlahUpdate);

        txtSubhargaUpdate.setEditable(false);
        jPanel2.add(txtSubhargaUpdate);

        DialogUpdateProsesRetur.getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/update2.png"))); // NOI18N
        Update.setMnemonic('U');
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });
        jPanel3.add(Update);

        Reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/reset.png"))); // NOI18N
        Reset.setMnemonic('R');
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });
        jPanel3.add(Reset);

        DialogUpdateProsesRetur.getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        setLayout(new java.awt.BorderLayout());

        atas.setLayout(new java.awt.BorderLayout());

        up2.setLayout(new java.awt.BorderLayout());

        panelBackgroundUp1.setLayout(new java.awt.BorderLayout());

        kiri2.setOpaque(false);
        kiri2.setLayout(new java.awt.GridLayout(3, 1));

        labeling7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labeling7.setText("Masukkan kata kunci :");
        kiri2.add(labeling7);

        labeling8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labeling8.setText("Cari data berdasarkan :");
        kiri2.add(labeling8);

        labeling9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labeling9.setText("Urutkan data berdasarkan :");
        kiri2.add(labeling9);

        panelBackgroundUp1.add(kiri2, java.awt.BorderLayout.WEST);

        kanan1.setOpaque(false);
        kanan1.setLayout(new java.awt.GridLayout(3, 0));

        atas2.setOpaque(false);

        txtKataKunciProsesRetur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKataKunciProsesReturActionPerformed(evt);
            }
        });

        btCariProsesRetur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/cari.png"))); // NOI18N
        btCariProsesRetur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariProsesReturActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout atas2Layout = new javax.swing.GroupLayout(atas2);
        atas2.setLayout(atas2Layout);
        atas2Layout.setHorizontalGroup(
            atas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(atas2Layout.createSequentialGroup()
                .addComponent(txtKataKunciProsesRetur, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCariProsesRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        atas2Layout.setVerticalGroup(
            atas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtKataKunciProsesRetur)
            .addComponent(btCariProsesRetur, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );

        kanan1.add(atas2);

        tengah2.setOpaque(false);
        tengah2.setLayout(new java.awt.BorderLayout());

        cmbCariPRosesRetur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id", "user", "kode", "nama", "jumlah", "subharga" }));
        cmbCariPRosesRetur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCariPRosesReturActionPerformed(evt);
            }
        });
        tengah2.add(cmbCariPRosesRetur, java.awt.BorderLayout.CENTER);

        kanan1.add(tengah2);

        bawah2.setOpaque(false);
        bawah2.setLayout(new java.awt.BorderLayout());

        cmbUrutProsesRetur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id", "user", "kode", "nama", "jumlah", "subharga" }));
        cmbUrutProsesRetur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUrutProsesReturActionPerformed(evt);
            }
        });
        bawah2.add(cmbUrutProsesRetur, java.awt.BorderLayout.CENTER);

        kanan1.add(bawah2);

        panelBackgroundUp1.add(kanan1, java.awt.BorderLayout.CENTER);

        up2.add(panelBackgroundUp1, java.awt.BorderLayout.CENTER);

        atas.add(up2, java.awt.BorderLayout.CENTER);

        add(atas, java.awt.BorderLayout.PAGE_START);

        tengah.setLayout(new java.awt.BorderLayout());

        tabelPRosesRetur.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelPRosesRetur);

        tengah.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(tengah, java.awt.BorderLayout.CENTER);

        bawah.setLayout(new java.awt.GridLayout(1, 3));

        btProses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/proses.png"))); // NOI18N
        btProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProsesActionPerformed(evt);
            }
        });
        bawah.add(btProses);

        btUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/update2.png"))); // NOI18N
        btUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateActionPerformed(evt);
            }
        });
        bawah.add(btUpdate);

        btHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/hapus.png"))); // NOI18N
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });
        bawah.add(btHapus);

        add(bawah, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void txtKataKunciProsesReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKataKunciProsesReturActionPerformed
        // TODO add your handling code here:
        btCariProsesRetur.doClick();
    }//GEN-LAST:event_txtKataKunciProsesReturActionPerformed

    private void btCariProsesReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariProsesReturActionPerformed
        String katakunci = txtKataKunciProsesRetur.getText();
        String berdasarkan = cmbCariPRosesRetur.getSelectedItem().toString();
        DefaultTableModel modelTable = new DefaultTableModel();        
        String[] kolom = {"id", "user", "kode", "nama", "jumlah", "subharga"};
        modelTable.setColumnIdentifiers(kolom);
        String[] data = new String[6];
        koneksi();
        Statement s = null;
        try{
            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM detailreturfinal WHERE "+berdasarkan+" like '%"+katakunci+"%'");
            while (rs.next()) {                
                data[0] = rs.getString("id");
                data[1] = rs.getString("user");
                data[2] = rs.getString("kode");
                data[3] = rs.getString("nama");
                data[4] = rs.getString("jumlah");
                data[5] = rs.getString("subharga");
                modelTable.addRow(data);
            }
            tabelPRosesRetur.setModel(modelTable);
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam mencari barang dalam proses retur karena = "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        this.setSize(getWidth(), getHeight());
    }//GEN-LAST:event_btCariProsesReturActionPerformed

    private void cmbCariPRosesReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCariPRosesReturActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCariPRosesReturActionPerformed

    public void urut(){
        String berdasarkan = cmbUrutProsesRetur.getSelectedItem().toString();
        DefaultTableModel modelTable = new DefaultTableModel();        
        String[] kolom = {"id", "user", "kode", "nama", "jumlah", "subharga"};
        modelTable.setColumnIdentifiers(kolom);
        String[] data = new String[6];
        koneksi();
        Statement s = null;
        try{
            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM detailreturfinal ORDER BY "+berdasarkan);
            while (rs.next()) {                
                data[0] = rs.getString("id");
                data[1] = rs.getString("user");
                data[2] = rs.getString("kode");
                data[3] = rs.getString("nama");
                data[4] = rs.getString("jumlah");
                data[5] = rs.getString("subharga");
                modelTable.addRow(data);
            }
            tabelPRosesRetur.setModel(modelTable);
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam mengurutkan barang dalam proses retur karena = "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
    }
    
    private void cmbUrutProsesReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUrutProsesReturActionPerformed
        urut();
    }//GEN-LAST:event_cmbUrutProsesReturActionPerformed

    private int getJumlahStokBarang(String kode){
        int stok = 0;
        Statement s = null;
        try{
            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT stok FROM barang WHERE idbarang = '"+kode+"'");
            if(rs.next()){
                stok = rs.getInt(1);
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam mendapatkan jumlah stok barang karena "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return  stok;
    }
    
    private void btProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProsesActionPerformed
        // TODO add your handling code here:
        if(tabelPRosesRetur.getSelectedRowCount()<=0){
            JOptionPane.showMessageDialog(null, "Pilih data yang akan diproses");
            return;
        }else{
            String id = tabelPRosesRetur.getValueAt(tabelPRosesRetur.getSelectedRow(), 0).toString();
            String kode = tabelPRosesRetur.getValueAt(tabelPRosesRetur.getSelectedRow(), 2).toString();
            int jumlah = Integer.parseInt(tabelPRosesRetur.getValueAt(tabelPRosesRetur.getSelectedRow(), 5).toString());
            if(JOptionPane.showConfirmDialog(null, "Apakah data dengan id "+id+" akan diproses ?") == JOptionPane.YES_OPTION){
                koneksi();
                PreparedStatement ps = null; 
                try{
                    //insert stok barang tambahan dari retur pabrik
                    int stokBefore = getJumlahStokBarang(kode);
                    int stokAfter = stokBefore+jumlah;
                    ps = c.prepareStatement("Update Barang set stok = "+stokAfter+" WHERE idbarang='"+kode+"'");
                                                                                //WHERE idbarang='"+txtkodeitem.getText()+"'"
                    //Update Barang set stok = 50 WHERE idbarang ='tes'
                    ps.executeUpdate();
                
                    //delete from retur proses
                    ps = c.prepareStatement("DELETE FROM detailreturfinal WHERE id= '"+id+"'");
                    ps.executeUpdate();
                    }catch(SQLException exception){
                        JOptionPane.showMessageDialog(null, "error dalam btprosess act performed karena "+exception);
                        }finally{
                            if(ps!=null){
                                try {
                                    ps.close();
                                } catch (SQLException ex) {
                                Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
                                }   
                            }
                        }            
                }  
        }
        urut();
    }//GEN-LAST:event_btProsesActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        // TODO add your handling code here:
        if(tabelPRosesRetur.getSelectedRowCount()<=0){
            JOptionPane.showMessageDialog(null, "Pilih data yang akan dihapus");
            return;
        }else{
            String id = tabelPRosesRetur.getValueAt(tabelPRosesRetur.getSelectedRow(), 0).toString();
            if(JOptionPane.showConfirmDialog(null, "Apakah data dengan id "+id+" alan dihapus ?") == JOptionPane.YES_OPTION){
                PreparedStatement ps = null;
                try{
                    ps = c.prepareStatement("DELETE FROM detailreturfinal WHERE id='"+id+"'");
                    ps.executeUpdate();
                }catch(SQLException exception){
                    JOptionPane.showMessageDialog(null, "error dalam proses delete data dari retur proses karena = "+exception);
                }finally{
                    if(ps!=null){
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                urut();
            }
        }
    }//GEN-LAST:event_btHapusActionPerformed

    public void loadKodeUpdate(){
        Statement s = null;        
        try{
            s=c.createStatement();
            ResultSet rs = s.executeQuery("SELECT idbarang From Barang ORDER BY idbarang");
            while (rs.next()) {                
                cmbKodeUpdate.addItem(rs.getString("idbarang"));
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam load kode update karena = "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    private void btUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateActionPerformed
        // TODO add your handling code here:
        if(tabelPRosesRetur.getSelectedRowCount()<=0){
            JOptionPane.showMessageDialog(null, "Pilih data yang akan di update");
            return;
        }else{
            String id = tabelPRosesRetur.getValueAt(tabelPRosesRetur.getSelectedRow(), 0).toString();
            Statement s = null;            
            try{
                s=c.createStatement();
                ResultSet rs = s.executeQuery("SELECT * FROM detailreturfinal WHERE id = '"+id+"'");
                if (rs.next()) {                    
                    String idr = rs.getString("id");
                    String user = rs.getString("user");
                    String tanggal = rs.getString("tanggal");
                    String jam = rs.getString("jam");
                    String kode = rs.getString("kode");
                    String nama = rs.getString("nama");
                    String jumlah = rs.getString("jumlah");
                    String subharga = rs.getString("subharga");
                    
                    txtIdUpdate.setText(idr);
                    cmbKodeUpdate.setSelectedItem(kode);
                    txtNamaUpdate.setText(nama);
                    txtJumlahUpdate.setText(jumlah);
                    txtSubhargaUpdate.setText(subharga);
                    
                    DialogUpdateProsesRetur.setModal(true);
                    DialogUpdateProsesRetur.setSize(400, 500);
                    DialogUpdateProsesRetur.setTitle("Update Proses Retur");
                    DialogUpdateProsesRetur.setLocationRelativeTo(null);
                    DialogUpdateProsesRetur.show();
                }
            }catch(SQLException exception){
                JOptionPane.showMessageDialog(null, "Error dalam retrive data untuk update karena ="+exception);
            }finally{
                if(s!=null){
                    try {
                        s.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }        
    }//GEN-LAST:event_btUpdateActionPerformed

    private void cmbKodeUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKodeUpdateActionPerformed
        // TODO add your handling code here:
        Statement s = null;
        try{
            String kode = cmbKodeUpdate.getSelectedItem().toString();
            s=c.createStatement();
            ResultSet rs=s.executeQuery("SELECT namabarang FROM barang WHERE idbarang = '"+kode+"'");
            if(rs.next()){
                txtNamaUpdate.setText(rs.getString("namabarang"));
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam get nama barang pada cmbkode update karena = "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_cmbKodeUpdateActionPerformed

    public int getHargaGrosirBarang(String kode){
        int harga = 0;
        Statement s = null;
        try{
            s=c.createStatement();
            ResultSet rs=s.executeQuery("SELECT grosir FROM barang WHERE idbarang = '"+kode+"'");
            if(rs.next()){
                harga = rs.getInt("grosir");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam getharga grosir barang pada update retur karena ="+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return harga;
    }
    private void txtJumlahUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahUpdateActionPerformed
        // TODO add your handling code here:
        int jumlah = Integer.parseInt(txtJumlahUpdate.getText());
        int hargaGrosir = getHargaGrosirBarang(cmbKodeUpdate.getSelectedItem().toString());
        
        txtSubhargaUpdate.setText(String.valueOf((jumlah*hargaGrosir)));
    }//GEN-LAST:event_txtJumlahUpdateActionPerformed

    private void txtJumlahUpdateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtJumlahUpdateFocusLost
        // TODO add your handling code here:
        int jumlah = Integer.parseInt(txtJumlahUpdate.getText());
        int hargaGrosir = getHargaGrosirBarang(cmbKodeUpdate.getSelectedItem().toString());
        
        txtSubhargaUpdate.setText(String.valueOf((jumlah*hargaGrosir)));        
    }//GEN-LAST:event_txtJumlahUpdateFocusLost

    public void updateExec(){
        PreparedStatement ps=null;
        try{
            ps=c.prepareStatement("UPDATE detailreturfinal SET kode=?, nama=?, jumlah=?, subharga=? WHERE id="+txtIdUpdate.getText());
            ps.setString(1, cmbKodeUpdate.getSelectedItem().toString());
            ps.setString(2, txtNamaUpdate.getText());
            ps.setString(3, txtJumlahUpdate.getText());
            ps.setString(4, txtSubhargaUpdate.getText());
            ps.executeUpdate();
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam update execute proses retur karena = "+exception);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Update Retur Berhasil");
        urut();
    }
    
    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        // TODO add your handling code here:
        updateExec();
    }//GEN-LAST:event_UpdateActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        // TODO add your handling code here:
        txtNamaUpdate.setText("");
        txtJumlahUpdate.setText("0");
        txtSubhargaUpdate.setText("0");
    }//GEN-LAST:event_ResetActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogUpdateProsesRetur;
    private javax.swing.JButton Reset;
    private javax.swing.JButton Update;
    private javax.swing.JPanel atas;
    private javax.swing.JPanel atas2;
    private javax.swing.JPanel bawah;
    private javax.swing.JPanel bawah2;
    private javax.swing.JButton btCariProsesRetur;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btProses;
    private javax.swing.JButton btUpdate;
    private javax.swing.JComboBox cmbCariPRosesRetur;
    private javax.swing.JComboBox cmbKodeUpdate;
    private javax.swing.JComboBox cmbUrutProsesRetur;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel kanan1;
    private javax.swing.JPanel kiri2;
    private Sinarelektronikapp.swingResource.labeling labeling7;
    private Sinarelektronikapp.swingResource.labeling labeling8;
    private Sinarelektronikapp.swingResource.labeling labeling9;
    private Sinarelektronikapp.swingResource.panelGradUser panelBackgroundUp1;
    private javax.swing.JTable tabelPRosesRetur;
    private javax.swing.JPanel tengah;
    private javax.swing.JPanel tengah2;
    private javax.swing.JTextField txtIdUpdate;
    private javax.swing.JTextField txtJumlahUpdate;
    private javax.swing.JTextField txtKataKunciProsesRetur;
    private javax.swing.JTextField txtNamaUpdate;
    private javax.swing.JTextField txtSubhargaUpdate;
    private javax.swing.JPanel up2;
    // End of variables declaration//GEN-END:variables

}

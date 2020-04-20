package Sinarelektronikapp.transaction.barangkecil.view;

import Sinarelektronikapp.transaction.barangkecil.controller.TransaksiPenjualanController;
import Sinarelektronikapp.transaction.barangkecil.database.TransaksiPenjualanDatabase;
import Sinarelektronikapp.transaction.barangkecil.error.TransaksiPenjualanException;
import Sinarelektronikapp.transaction.barangkecil.model.event.TransaksiPenjualanListener;
import Sinarelektronikapp.transaction.barangkecil.model.TabelModelTransaksiPenjualan;
import Sinarelektronikapp.transaction.barangkecil.model.TransaksiPenjualanModel;
import Sinarelektronikapp.transaction.barangkecil.service.TransaksiPenjualanDao;
import Sinarelektronikapp.config.InternetProtocol;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fauzi
 */
public class JIFManajemenTransaksiPenjualan extends javax.swing.JInternalFrame implements TransaksiPenjualanListener{

    /**
     * Creates new form JIFManajemenTransaksiPenjualan
     */
    private TransaksiPenjualanModel transaksiPenjualanModel;
    
    private TransaksiPenjualanController transaksiPenjualanController;
    
    private TabelModelTransaksiPenjualan tabelModelTransaksiPenjualan;
    
    public JIFManajemenTransaksiPenjualan() {
        tabelModelTransaksiPenjualan = new TabelModelTransaksiPenjualan();
        
        transaksiPenjualanModel = new TransaksiPenjualanModel();
        transaksiPenjualanModel.setTpl(this);
        
        transaksiPenjualanController = new TransaksiPenjualanController();
        transaksiPenjualanController.setTransaksiPenjualanModel(transaksiPenjualanModel);
                
        initComponents();
        
        tabelTransaksiPenjualan.setModel(tabelModelTransaksiPenjualan);
        loadDatabase();
    }

    private void loadDatabase(){
        TransaksiPenjualanDao dao=TransaksiPenjualanDatabase.getTransaksiPenjualanDao();
        try {
            tabelModelTransaksiPenjualan.setList(dao.showAllTransaksiPenjualan());
        } catch (TransaksiPenjualanException ex) {
            Logger.getLogger(JIFManajemenTransaksiPenjualan.class.getName()).log(Level.SEVERE, null, ex);
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

        DialogDetail = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelDetailTransaksi = new javax.swing.JTable();
        Up = new javax.swing.JPanel();
        Kiri = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Kanan = new javax.swing.JPanel();
        atas = new javax.swing.JPanel();
        txtKataKunci = new javax.swing.JTextField();
        btCari = new javax.swing.JButton();
        tengah = new javax.swing.JPanel();
        cmbCari = new javax.swing.JComboBox();
        bawah = new javax.swing.JPanel();
        cmbUrut = new javax.swing.JComboBox();
        Middle = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelTransaksiPenjualan = new javax.swing.JTable();
        Bottom = new javax.swing.JPanel();
        PanewlBottom2 = new javax.swing.JPanel();
        btHapus = new javax.swing.JButton();
        btDetail = new javax.swing.JButton();

        DialogDetail.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DialogDetail.setTitle("Detail Transaksi");
        DialogDetail.setModal(true);

        tabelDetailTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabelDetailTransaksi);

        DialogDetail.getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Manajemen Transaksi Penjualan Barang Kecil");
        setToolTipText("Manajemen Untuk Transaksi Barang");

        Up.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Up.setLayout(new java.awt.BorderLayout());

        Kiri.setLayout(new java.awt.GridLayout(3, 0));

        jLabel1.setText("Kata Kunci :");
        Kiri.add(jLabel1);

        jLabel2.setText("Cari :");
        Kiri.add(jLabel2);

        jLabel3.setText("Urutkan :");
        Kiri.add(jLabel3);

        Up.add(Kiri, java.awt.BorderLayout.WEST);

        Kanan.setLayout(new java.awt.GridLayout(3, 0));

        atas.setOpaque(false);

        txtKataKunci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKataKunciActionPerformed(evt);
            }
        });

        btCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/cari.png"))); // NOI18N
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout atasLayout = new javax.swing.GroupLayout(atas);
        atas.setLayout(atasLayout);
        atasLayout.setHorizontalGroup(
            atasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(atasLayout.createSequentialGroup()
                .addComponent(txtKataKunci, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCari, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        atasLayout.setVerticalGroup(
            atasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtKataKunci)
            .addComponent(btCari, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        Kanan.add(atas);

        tengah.setOpaque(false);
        tengah.setLayout(new java.awt.BorderLayout());

        cmbCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id", "user", "tanggal", "jam", "total" }));
        cmbCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCariActionPerformed(evt);
            }
        });
        tengah.add(cmbCari, java.awt.BorderLayout.CENTER);

        Kanan.add(tengah);

        bawah.setOpaque(false);
        bawah.setLayout(new java.awt.BorderLayout());

        cmbUrut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id", "user", "tanggal", "jam", "total" }));
        cmbUrut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUrutActionPerformed(evt);
            }
        });
        bawah.add(cmbUrut, java.awt.BorderLayout.CENTER);

        Kanan.add(bawah);

        Up.add(Kanan, java.awt.BorderLayout.CENTER);

        getContentPane().add(Up, java.awt.BorderLayout.PAGE_START);

        Middle.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Middle.setLayout(new java.awt.BorderLayout());

        tabelTransaksiPenjualan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelTransaksiPenjualan);

        Middle.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(Middle, java.awt.BorderLayout.CENTER);

        Bottom.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Bottom.setLayout(new java.awt.BorderLayout());

        PanewlBottom2.setLayout(new java.awt.GridLayout(1, 2));

        btHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/hapus.png"))); // NOI18N
        btHapus.setMnemonic('H');
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });
        PanewlBottom2.add(btHapus);

        btDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/preview.png"))); // NOI18N
        btDetail.setMnemonic('H');
        btDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDetailActionPerformed(evt);
            }
        });
        PanewlBottom2.add(btDetail);

        Bottom.add(PanewlBottom2, java.awt.BorderLayout.CENTER);

        getContentPane().add(Bottom, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JComboBox getCmbCari() {
        return cmbCari;
    }

    public void setCmbCari(JComboBox cmbCari) {
        this.cmbCari = cmbCari;
    }

    public JComboBox getCmbUrut() {
        return cmbUrut;
    }

    public void setCmbUrut(JComboBox cmbUrut) {
        this.cmbUrut = cmbUrut;
    }

    public JTable getTabelTransaksiPenjualan() {
        return tabelTransaksiPenjualan;
    }

    public void setTabelTransaksiPenjualan(JTable tabelTransaksiPenjualan) {
        this.tabelTransaksiPenjualan = tabelTransaksiPenjualan;
    }

    public JTextField getTxtKataKunci() {
        return txtKataKunci;
    }

    public void setTxtKataKunci(JTextField txtKataKunci) {
        this.txtKataKunci = txtKataKunci;
    }

    
    private void txtKataKunciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKataKunciActionPerformed
        // TODO add your handling code here:
        btCari.doClick();
    }//GEN-LAST:event_txtKataKunciActionPerformed

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        try {
            TransaksiPenjualanDao dao = TransaksiPenjualanDatabase.getTransaksiPenjualanDao();
            tabelModelTransaksiPenjualan.setList(dao.getTransaksiPenjualan(txtKataKunci.getText(), cmbCari.getSelectedItem().toString()));
        } catch (TransaksiPenjualanException ex) {
            Logger.getLogger(JIFManajemenTransaksiPenjualan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btCariActionPerformed

    private void cmbCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCariActionPerformed

    private void cmbUrutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUrutActionPerformed
        try {
            TransaksiPenjualanDao dao = TransaksiPenjualanDatabase.getTransaksiPenjualanDao();
            tabelModelTransaksiPenjualan.setList(dao.sortTransaksiPenjualan(cmbUrut.getSelectedItem().toString()));
        } catch (TransaksiPenjualanException ex) {
            Logger.getLogger(JIFManajemenTransaksiPenjualan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmbUrutActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        // TODO add your handling code here:
        transaksiPenjualanController.deleteTransaksiPenjualan(this);
    }//GEN-LAST:event_btHapusActionPerformed

    InternetProtocol ip1 = new InternetProtocol();
    Connection c =null;
    public void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JIFManajemenTransaksiPenjualan.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            c = DriverManager.getConnection("jdbc:mysql://"+ip1.getIpServer()+"/sinarelektronik?;", "root", "5430trisin9");
        } catch (SQLException ex) {
            Logger.getLogger(JIFManajemenTransaksiPenjualan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void searchAndShow(int idPencarian){
        koneksi();
        String query = "select * from detailtransaksipenjualan where iddetailtransaksi="+idPencarian;
        try {
            DefaultTableModel model = new DefaultTableModel();
            String [] kolom = {"id", "kode", "nama barangkecil", "jumlah", "harga", "modal", "potongan", "tambahan", "total"};
            model.setColumnIdentifiers(kolom);
            Object[] data = new Object[9];
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {                
                data[0] = rs.getString("iddetailtransaksi");
                data[1] = rs.getString("kode");
                data[2] = rs.getString("namabarang");
                data[3] = rs.getString("jumlah");
                data[4] = rs.getString("harga");
                data[5] = rs.getString("modal");
                data[6] = rs.getString("potongan");
                data[7] = rs.getString("tambahan");
                data[8] = rs.getString("total");               
                model.addRow(data);
            }
            tabelDetailTransaksi.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(JIFManajemenTransaksiPenjualan.class.getName()).log(Level.SEVERE, null, ex);
        }
            DialogDetail.setSize(800, 400);
            DialogDetail.show();
            DialogDetail.setModal(true);
            DialogDetail.setTitle("Detail Transaksi Barang Besar");
            DialogDetail.setLocationRelativeTo(null);
    }
    
    private void btDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDetailActionPerformed
        // TODO add your handling code here:
        if(tabelTransaksiPenjualan.getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(null, "pilih data detail");
            return;
        }else {
            String no = tabelTransaksiPenjualan.getValueAt(tabelTransaksiPenjualan.getSelectedRow(), 0).toString();
            int idPencarian = Integer.parseInt(no);
            searchAndShow(idPencarian);
        }                
    }//GEN-LAST:event_btDetailActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Bottom;
    private javax.swing.JDialog DialogDetail;
    private javax.swing.JPanel Kanan;
    private javax.swing.JPanel Kiri;
    private javax.swing.JPanel Middle;
    private javax.swing.JPanel PanewlBottom2;
    private javax.swing.JPanel Up;
    private javax.swing.JPanel atas;
    private javax.swing.JPanel bawah;
    private javax.swing.JButton btCari;
    private javax.swing.JButton btDetail;
    private javax.swing.JButton btHapus;
    private javax.swing.JComboBox cmbCari;
    private javax.swing.JComboBox cmbUrut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelDetailTransaksi;
    private javax.swing.JTable tabelTransaksiPenjualan;
    private javax.swing.JPanel tengah;
    private javax.swing.JTextField txtKataKunci;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onDelete() {
        int index = tabelTransaksiPenjualan.getSelectedRow();
        tabelModelTransaksiPenjualan.remove(index);
    }

    @Override
    public void onSearch(List list) {
        tabelModelTransaksiPenjualan.setList(list);
    }

    @Override
    public void onSort(List list) {
        tabelModelTransaksiPenjualan.setList(list);
    }
    
}

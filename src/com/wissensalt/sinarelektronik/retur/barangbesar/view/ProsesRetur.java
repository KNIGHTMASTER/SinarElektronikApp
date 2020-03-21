package com.wissensalt.sinarelektronik.retur.barangbesar.view;

import com.wissensalt.sinarelektronik.config.HostName;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.controller.BarangBesarController;
import com.wissensalt.sinarelektronik.model.BarangBesarModel;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.model.TabelModelBarangBesar;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.model.event.BarangBesarListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fauzi
 */
public class ProsesRetur extends javax.swing.JPanel implements BarangBesarListener {

    /**
     * Creates new form ProsesRetur
     */   
    private TabelModelBarangBesar tabelmodelBarangBesar;
    
    private BarangBesarController controllerBarang;
    
    private BarangBesarModel modelBarang;
    
    public ProsesRetur() {
        tabelmodelBarangBesar = new TabelModelBarangBesar();
        
        modelBarang=  new BarangBesarModel();
        modelBarang.setListener(this);
        
        controllerBarang = new BarangBesarController();
        controllerBarang.setModel(modelBarang);
        
        initComponents();           
        loadData();
        loadKodeUpdate();
        tabelPRosesRetur.setRowSelectionAllowed(true);
        tabelPRosesRetur.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cmbCariPRosesRetur.setSelectedItem("supplier");        
        
        tabelBarang1.setModel(tabelmodelBarangBesar);
        loadDatabaseCariBarang();        
    }

    public JComboBox getCmbCari1() {
        return cmbCari1;
    }

    public void setCmbCari1(JComboBox cmbCari1) {
        this.cmbCari1 = cmbCari1;
    }

    public JComboBox getCmbUrut1() {
        return cmbUrut1;
    }

    public void setCmbUrut1(JComboBox cmbUrut1) {
        this.cmbUrut1 = cmbUrut1;
    }

    public JTextField getTxtKataKunci1() {
        return txtKataKunci1;
    }

    public void setTxtKataKunci1(JTextField txtKataKunci1) {
        this.txtKataKunci1 = txtKataKunci1;
    }
    
    public void loadDatabaseCariBarang(){
        BarangDao dao = null;
        try {
            dao = barangDatabase.getBarangDao();
        } catch (SQLException ex) {
            Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            tabelmodelBarangBesar.setList(dao.selectAllBarang());
        } catch (BarangException ex) {
            Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void loadData(){
        DefaultTableModel modelTable = new DefaultTableModel();        
        String[] kolom = {"id", "no_retur", "user", "tanggal", "kode", "nama", "jumlah", "subharga", "asal barangkecil", "penukaran", "supplier", "status"};
        modelTable.setColumnIdentifiers(kolom);
        String[] data = new String[12];
        koneksi();
        Statement s = null;
        try{
            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * from detailreturfinalbarangbesar");
            while (rs.next()) {                
                data[0] = rs.getString("id");
                data[1] = rs.getString("no_retur");
                data[2] = rs.getString("user");
                data[3] = rs.getString("tanggal");
                data[4] = rs.getString("kode");
                data[5] = rs.getString("nama");
                data[6] = rs.getString("jumlah");
                data[7] = rs.getString("subharga");
                data[8] = rs.getString("asalbarang");
                data[9] = rs.getString("penukaran");
                data[10] = rs.getString("supplier");
                data[11] = rs.getString("status");
                modelTable.addRow(data);
            }
            tabelPRosesRetur.setModel(modelTable);
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam select barangkecil proses retur karena = "+exception);
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
    HostName ip = new HostName();
    public void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            c = DriverManager.getConnection("jdbc:mysql://"+ip.getIpServer()+"/sinarelektronik?;", "root", "P@ssw0rd");
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
        DialogPilihanRetur = new javax.swing.JDialog();
        btProsesPengambilanReturSupplier = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        cmbPilihanReturSupplier = new javax.swing.JComboBox();
        cmbPosisiPengembalian = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        popUpSimpanSupplier = new javax.swing.JPopupMenu();
        menuSimpanSupplier = new javax.swing.JMenuItem();
        DialogCariBarang = new javax.swing.JDialog();
        up4 = new javax.swing.JPanel();
        panelBackgroundUp3 = new com.wissensalt.sinarelektronik.swinglib.panelGradUser();
        kiri4 = new javax.swing.JPanel();
        labeling13 = new com.wissensalt.sinarelektronik.swinglib.labeling();
        labeling14 = new com.wissensalt.sinarelektronik.swinglib.labeling();
        labeling15 = new com.wissensalt.sinarelektronik.swinglib.labeling();
        kanan3 = new javax.swing.JPanel();
        atas4 = new javax.swing.JPanel();
        txtKataKunci1 = new javax.swing.JTextField();
        btCari1 = new javax.swing.JButton();
        tengah4 = new javax.swing.JPanel();
        cmbCari1 = new javax.swing.JComboBox();
        bawah4 = new javax.swing.JPanel();
        cmbUrut1 = new javax.swing.JComboBox();
        middle2 = new javax.swing.JPanel();
        panelGradUser4 = new com.wissensalt.sinarelektronik.swinglib.panelGradUser();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelBarang1 = new javax.swing.JTable();
        popUpTabel = new javax.swing.JPopupMenu();
        pilih = new javax.swing.JMenuItem();
        atas = new javax.swing.JPanel();
        up2 = new javax.swing.JPanel();
        panelBackgroundUp1 = new com.wissensalt.sinarelektronik.swinglib.panelGradUser();
        kiri2 = new javax.swing.JPanel();
        labeling7 = new com.wissensalt.sinarelektronik.swinglib.labeling();
        labeling8 = new com.wissensalt.sinarelektronik.swinglib.labeling();
        labeling9 = new com.wissensalt.sinarelektronik.swinglib.labeling();
        kanan1 = new javax.swing.JPanel();
        atas2 = new javax.swing.JPanel();
        txtKataKunciProsesRetur = new javax.swing.JTextField();
        btCariProsesRetur = new javax.swing.JButton();
        tengah2 = new javax.swing.JPanel();
        cmbCariPRosesRetur = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cmbstatus = new javax.swing.JComboBox();
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

        Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/update2.png"))); // NOI18N
        Update.setMnemonic('U');
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });
        jPanel3.add(Update);

        Reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/reset.png"))); // NOI18N
        Reset.setMnemonic('R');
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });
        jPanel3.add(Reset);

        DialogUpdateProsesRetur.getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        DialogPilihanRetur.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DialogPilihanRetur.setTitle("Opsional Pengembalian Dari Supplier");

        btProsesPengambilanReturSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/login.png"))); // NOI18N
        btProsesPengambilanReturSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProsesPengambilanReturSupplierActionPerformed(evt);
            }
        });
        DialogPilihanRetur.getContentPane().add(btProsesPengambilanReturSupplier, java.awt.BorderLayout.PAGE_END);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.GridLayout(2, 0));

        cmbPilihanReturSupplier.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tukar Barang", "Tukar Uang", "Tukar Barang (beda harga sama)" }));
        jPanel6.add(cmbPilihanReturSupplier);

        cmbPosisiPengembalian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Toko", "Gudang" }));
        jPanel6.add(cmbPosisiPengembalian);

        jPanel5.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel7.setLayout(new java.awt.GridLayout(2, 0));

        jLabel7.setText("Penukaran : ");
        jPanel7.add(jLabel7);

        jLabel8.setText("Posisi Pengembalian :");
        jPanel7.add(jLabel8);

        jPanel5.add(jPanel7, java.awt.BorderLayout.WEST);

        DialogPilihanRetur.getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        menuSimpanSupplier.setText("Proses Menuju Supplier");
        menuSimpanSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSimpanSupplierActionPerformed(evt);
            }
        });
        popUpSimpanSupplier.add(menuSimpanSupplier);

        up4.setLayout(new java.awt.BorderLayout());

        panelBackgroundUp3.setLayout(new java.awt.BorderLayout());

        kiri4.setOpaque(false);
        kiri4.setLayout(new java.awt.GridLayout(3, 1));

        labeling13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labeling13.setText("Masukkan kata kunci :");
        kiri4.add(labeling13);

        labeling14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labeling14.setText("Cari data berdasarkan :");
        kiri4.add(labeling14);

        labeling15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labeling15.setText("Urutkan data berdasarkan :");
        kiri4.add(labeling15);

        panelBackgroundUp3.add(kiri4, java.awt.BorderLayout.WEST);

        kanan3.setOpaque(false);
        kanan3.setLayout(new java.awt.GridLayout(3, 0));

        atas4.setOpaque(false);

        txtKataKunci1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKataKunci1ActionPerformed(evt);
            }
        });

        btCari1.setText("cari");
        btCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCari1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout atas4Layout = new javax.swing.GroupLayout(atas4);
        atas4.setLayout(atas4Layout);
        atas4Layout.setHorizontalGroup(
            atas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(atas4Layout.createSequentialGroup()
                .addComponent(txtKataKunci1, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCari1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        atas4Layout.setVerticalGroup(
            atas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtKataKunci1)
            .addComponent(btCari1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        kanan3.add(atas4);

        tengah4.setOpaque(false);
        tengah4.setLayout(new java.awt.BorderLayout());

        cmbCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id barangkecil", "id barcode", "nama barangkecil", "tipe", "MerekDTO", "harga", "satuan", "stok", "stok minimum", "supplier", "keterangan" }));
        cmbCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCari1ActionPerformed(evt);
            }
        });
        tengah4.add(cmbCari1, java.awt.BorderLayout.CENTER);

        kanan3.add(tengah4);

        bawah4.setOpaque(false);
        bawah4.setLayout(new java.awt.BorderLayout());

        cmbUrut1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id barangkecil", "id barcode", "nama barangkecil", "tipe", "MerekDTO", "harga", "satuan", "stok", "stok minimum", "supplier", "keterangan" }));
        cmbUrut1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUrut1ActionPerformed(evt);
            }
        });
        bawah4.add(cmbUrut1, java.awt.BorderLayout.CENTER);

        kanan3.add(bawah4);

        panelBackgroundUp3.add(kanan3, java.awt.BorderLayout.CENTER);

        up4.add(panelBackgroundUp3, java.awt.BorderLayout.CENTER);

        DialogCariBarang.getContentPane().add(up4, java.awt.BorderLayout.PAGE_START);

        middle2.setLayout(new java.awt.BorderLayout());

        panelGradUser4.setLayout(new java.awt.BorderLayout());

        tabelBarang1.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelBarang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBarang1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabelBarang1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelBarang1MouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tabelBarang1);

        panelGradUser4.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        middle2.add(panelGradUser4, java.awt.BorderLayout.CENTER);

        DialogCariBarang.getContentPane().add(middle2, java.awt.BorderLayout.CENTER);

        pilih.setText("Pilih");
        pilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihActionPerformed(evt);
            }
        });
        popUpTabel.add(pilih);

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

        btCariProsesRetur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/cari.png"))); // NOI18N
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
                .addComponent(txtKataKunciProsesRetur, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCariProsesRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        atas2Layout.setVerticalGroup(
            atas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtKataKunciProsesRetur)
            .addComponent(btCariProsesRetur, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        kanan1.add(atas2);

        tengah2.setOpaque(false);
        tengah2.setLayout(new java.awt.BorderLayout());

        cmbCariPRosesRetur.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        cmbCariPRosesRetur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id", "no_retur", "user", "tanggal", "kode", "jumlah", "harga", "penukaran", "subharga", "supplier" }));
        cmbCariPRosesRetur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCariPRosesReturActionPerformed(evt);
            }
        });
        tengah2.add(cmbCariPRosesRetur, java.awt.BorderLayout.WEST);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel6.setText("Status :");
        jPanel4.add(jLabel6, java.awt.BorderLayout.WEST);

        cmbstatus.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        cmbstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "belum diproses", "sedang diproses" }));
        jPanel4.add(cmbstatus, java.awt.BorderLayout.CENTER);

        tengah2.add(jPanel4, java.awt.BorderLayout.CENTER);

        kanan1.add(tengah2);

        bawah2.setOpaque(false);
        bawah2.setLayout(new java.awt.BorderLayout());

        cmbUrutProsesRetur.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
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
        tabelPRosesRetur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelPRosesReturMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPRosesRetur);

        tengah.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(tengah, java.awt.BorderLayout.CENTER);

        bawah.setLayout(new java.awt.GridLayout(1, 3));

        btProses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/proses.png"))); // NOI18N
        btProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProsesActionPerformed(evt);
            }
        });
        bawah.add(btProses);

        btUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/update2.png"))); // NOI18N
        btUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateActionPerformed(evt);
            }
        });
        bawah.add(btUpdate);

        btHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/hapus.png"))); // NOI18N
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
        String[] kolom = {"id", "no_retur", "user", "tanggal", "kode", "nama", "jumlah", "subharga", "asal barangkecil", "penukaran", "supplier", "status"};
        modelTable.setColumnIdentifiers(kolom);
        String[] data = new String[12];
        koneksi();
        Statement s = null;
        try{
            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * from detailreturfinalbarangbesar WHERE "+berdasarkan+" like '%"+katakunci+"%' and status like '%"+cmbstatus.getSelectedItem().toString()+"%'");
            while (rs.next()) {                
                data[0] = rs.getString("id");
                data[1] = rs.getString("no_retur");
                data[2] = rs.getString("user");
                data[3] = rs.getString("tanggal");
                data[4] = rs.getString("kode");
                data[5] = rs.getString("nama");
                data[6] = rs.getString("jumlah");
                data[7] = rs.getString("subharga");
                data[8] = rs.getString("asalbarang");
                data[9] = rs.getString("penukaran");
                data[10] = rs.getString("supplier");
                data[11] = rs.getString("status");
                modelTable.addRow(data);
            }
            tabelPRosesRetur.setModel(modelTable);
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam mencari barangkecil dalam proses retur karena = "+exception);
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
        String[] kolom = {"id", "no_retur", "user", "tanggal", "kode", "nama", "jumlah", "subharga", "asal barangkecil", "penukaran", "supplier", "status"};
        modelTable.setColumnIdentifiers(kolom);
        String[] data = new String[12];
        koneksi();
        Statement s = null;
        try{
            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * from detailreturfinalbarangbesar ORDER BY "+berdasarkan);
            while (rs.next()) {                
                data[0] = rs.getString("id");
                data[1] = rs.getString("no_retur");
                data[2] = rs.getString("user");
                data[3] = rs.getString("tanggal");
                data[4] = rs.getString("kode");
                data[5] = rs.getString("nama");
                data[6] = rs.getString("jumlah");
                data[7] = rs.getString("subharga");
                data[8] = rs.getString("asalbarang");
                data[9] = rs.getString("penukaran");
                data[10] = rs.getString("supplier");
                data[11] = rs.getString("status");
                modelTable.addRow(data);
            }
            tabelPRosesRetur.setModel(modelTable);
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam mengurutkan barangkecil dalam proses retur karena = "+exception);
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
            ResultSet rs = s.executeQuery("SELECT stok from barangbesar WHERE idbarang = '"+kode+"'");
            if(rs.next()){
                stok = rs.getInt(1);
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam mendapatkan jumlah stok barangkecil karena "+exception);
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
    
    private int getJumlahStokBarangToko(String kode){
        int stok = 0;
        Statement s = null;
        try{
            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT stok from barangtoko WHERE idbarang = '"+kode+"'");
            if(rs.next()){
                stok = rs.getInt(1);
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam mendapatkan jumlah stok barangkecil karena "+exception);
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

    int [] rowsSelected = null;
    
    private void btProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProsesActionPerformed
        // TODO add your handling code here:
        rowsSelected = tabelPRosesRetur.getSelectedRows();
        
        if(tabelPRosesRetur.getSelectedRowCount()<=0){
            JOptionPane.showMessageDialog(null, "Pilih data yang akan diproses");
            return;
        }else{
            
            int y = 250;
            int x = 500;
            DialogPilihanRetur.setModal(true);
            DialogPilihanRetur.setLocation(x, y);
            DialogPilihanRetur.setSize(370, 160);
            DialogPilihanRetur.show();
            
            /*String id = tabelPRosesRetur.getValueAt(tabelPRosesRetur.getSelectedRow(), 0).toString();
            String kode = tabelPRosesRetur.getValueAt(tabelPRosesRetur.getSelectedRow(), 2).toString();
            int jumlah = Integer.parseInt(tabelPRosesRetur.getValueAt(tabelPRosesRetur.getSelectedRow(), 5).toString());
            if(JOptionPane.showConfirmDialog(null, "Apakah data dengan id "+id+" akan diproses ?") == JOptionPane.YES_OPTION){
                koneksi();
                PreparedStatement ps = null; 
                try{
                    //insert stok barangkecil tambahan dari retur pabrik
                    int stokBefore = getJumlahStokBarang(kode);
                    int stokAfter = stokBefore+jumlah;
                    ps = c.prepareStatement("update barangbesar set stok = "+stokAfter+" WHERE idbarang='"+kode+"'");
                                                                                //WHERE idbarang='"+txtkodeitem.getText()+"'"
                    //update barangbesar set stok = 50 WHERE idbarang ='tes'
                    ps.executeUpdate();
                
                    //delete from retur proses
                    ps = c.prepareStatement("DELETE from detailreturfinalbarangbesar WHERE id= '"+id+"'");
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
                }  */
        }
        //urut();
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
                    ps = c.prepareStatement("DELETE from detailreturfinalbarangbesar WHERE id='"+id+"'");
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
            ResultSet rs = s.executeQuery("SELECT idbarang from barangbesar ORDER BY idbarang");
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
                ResultSet rs = s.executeQuery("SELECT * from detailreturfinalbarangbesar WHERE id = '"+id+"'");
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
            ResultSet rs=s.executeQuery("SELECT namabarang from barangbesar WHERE idbarang = '"+kode+"'");
            if(rs.next()){
                txtNamaUpdate.setText(rs.getString("namabarang"));
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam get nama barangkecil pada cmbkode update karena = "+exception);
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
            ResultSet rs=s.executeQuery("SELECT grosir from barangbesar WHERE idbarang = '"+kode+"'");
            if(rs.next()){
                harga = rs.getInt("grosir");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam getharga grosir barangkecil pada update retur karena ="+exception);
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
            ps=c.prepareStatement("update detailreturfinalbarangbesar SET kode=?, nama=?, jumlah=?, subharga=? WHERE id="+txtIdUpdate.getText());
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

    Date date=new Date();        
    
    SimpleDateFormat formatTanggal=new SimpleDateFormat("yyyy-MM-dd");    
    
    private String loadTanggal(){        
        return formatTanggal.format(date);
    }    
    
    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        // TODO add your handling code here:
        txtNamaUpdate.setText("");
        txtJumlahUpdate.setText("0");
        txtSubhargaUpdate.setText("0");
    }//GEN-LAST:event_ResetActionPerformed

    public void prosesPenukaranBarangOlehSupplier(){
            koneksi();
            PreparedStatement ps = null; 
            //konversi posisi pengembalian ke nama tabel
            int stokBefore = 0;
            String tabelPengembalian = "";
            if(posisiPengembalian.equals("Toko")){
                tabelPengembalian = "barangtoko";                
            }else if(posisiPengembalian.equals("Gudang")){
                tabelPengembalian = "barangbesar";
            }
            
            for(int a=0; a<rowsSelected.length; a++){
                String id = tabelPRosesRetur.getValueAt(rowsSelected[a], 0).toString();
                String kode = tabelPRosesRetur.getValueAt(rowsSelected[a], 4).toString();
                int jumlah = Integer.parseInt(tabelPRosesRetur.getValueAt(rowsSelected[a], 6).toString());                    
                int harga = getHargaGrosirBarang(kode);
                int subHarga = Integer.parseInt(tabelPRosesRetur.getValueAt(rowsSelected[a], 7).toString());
                String asalBarang = tabelPRosesRetur.getValueAt(rowsSelected[a], 8).toString();                
                String penukaran  = "Barang";
                String supplier  = tabelPRosesRetur.getValueAt(rowsSelected[a], 10).toString();
                    try{
                    //insert stok barangkecil tambahan dari retur pabrik
                        if(tabelPengembalian.equals("barangtoko")){
                            stokBefore = getJumlahStokBarangToko(kode);
                        }else if(tabelPengembalian.equals("barangbesar")){
                            stokBefore = getJumlahStokBarang(kode);
                        }
                        int stokAfter = stokBefore+jumlah;
                        ps = c.prepareStatement("Update "+tabelPengembalian+" set stok = "+stokAfter+" WHERE idbarang='"+kode+"'");
                        //WHERE idbarang='"+txtkodeitem.getText()+"'"
                        //Update Barang set stok = 50 WHERE idbarang ='tes'
                        ps.executeUpdate();
                        
                        //delete from retur proses
                        ps = c.prepareStatement("DELETE FROM detailreturfinalbarangbesar WHERE id= '"+id+"'");
                        ps.executeUpdate();
                        
                        //insert into laporan pengembalian barangkecil kecil
                        
                        ps = c.prepareStatement("INSERT INTO laporanpengembalianbarangbesar (tanggal, kode, jumlah, harga, penukaran, subharga, supplier) values (?, ?, ?, ?, ?, ?, ?) ");
                        ps.setString(1, loadTanggal());
                        ps.setString(2, kode);
                        ps.setInt(3, jumlah);
                        ps.setInt(4, harga);
                        ps.setString(5, penukaran);
                        ps.setInt(6, subHarga);
                        ps.setString(7, supplier);
                        ps.executeUpdate();
                        
                }catch(SQLException exception){
                    JOptionPane.showMessageDialog(null, "error dalam btproses act performed karena "+exception);
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
    
    public void prosesPenukaranUangOlehSupplier(){
            koneksi();
            PreparedStatement ps = null; 
            for(int a=0; a<rowsSelected.length; a++){
                String id = tabelPRosesRetur.getValueAt(rowsSelected[a], 0).toString();
                String kode = tabelPRosesRetur.getValueAt(rowsSelected[a], 4).toString();
                int jumlah = Integer.parseInt(tabelPRosesRetur.getValueAt(rowsSelected[a], 6).toString());                    
                int harga = getHargaGrosirBarang(kode);
                int subHarga = Integer.parseInt(tabelPRosesRetur.getValueAt(rowsSelected[a], 7).toString());
                String asalBarang = tabelPRosesRetur.getValueAt(rowsSelected[a], 8).toString();                
                String penukaran  = "Uang";
                String supplier  = tabelPRosesRetur.getValueAt(rowsSelected[a], 10).toString();
                    try{
                    //insert stok barangkecil tambahan dari retur pabrik
                        /*int stokBefore = getJumlahStokBarang(kode);
                        int stokAfter = stokBefore+jumlah;
                        ps = c.prepareStatement("Update Barang set stok = "+stokAfter+" WHERE idbarang='"+kode+"'");
                        //WHERE idbarang='"+txtkodeitem.getText()+"'"
                        //Update Barang set stok = 50 WHERE idbarang ='tes'
                        ps.executeUpdate();*/
                        
                        //delete from retur proses
                        ps = c.prepareStatement("DELETE FROM detailreturfinalbarangbesar WHERE id= '"+id+"'");
                        ps.executeUpdate();
                        
                        //insert into laporan pengembalian barangkecil kecil
                        
                        ps = c.prepareStatement("INSERT INTO laporanpengembalianbarangbesar (tanggal, kode, jumlah, harga, penukaran, subharga, supplier) values (?, ?, ?, ?, ?, ?, ?) ");
                        ps.setString(1, loadTanggal());
                        ps.setString(2, kode);
                        ps.setInt(3, jumlah);
                        ps.setInt(4, harga);
                        ps.setString(5, penukaran);
                        ps.setInt(6, subHarga);
                        ps.setString(7, supplier);
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
    
    public void callDialogTukarBarang(){
        DialogPilihanRetur.dispose();
        DialogCariBarang.setSize(800, 700);        
        DialogCariBarang.show();
        DialogCariBarang.setModal(true);
        DialogCariBarang.setTitle("Pencarian Barang");
        DialogCariBarang.setLocationRelativeTo(null);            
    }
    
    public String posisiPengembalian = "";
        
    private void btProsesPengambilanReturSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProsesPengambilanReturSupplierActionPerformed
        // TODO add your handling code here:
        posisiPengembalian = cmbPosisiPengembalian.getSelectedItem().toString();
        switch(cmbPilihanReturSupplier.getSelectedItem().toString()){
            case "Tukar Barang" : prosesPenukaranBarangOlehSupplier();urut();break;
            case "Tukar Uang"   : prosesPenukaranUangOlehSupplier();urut();break;
            case "Tukar Barang (beda harga sama)" :callDialogTukarBarang();break;
            default:;
        }
    }//GEN-LAST:event_btProsesPengambilanReturSupplierActionPerformed

    private void menuSimpanSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSimpanSupplierActionPerformed
        // TODO add your handling code here:
        rowsSelected = tabelPRosesRetur.getSelectedRows();
        for(int a=0; a<rowsSelected.length; a++){
            String id = tabelPRosesRetur.getValueAt(rowsSelected[a], 0).toString();
            try{
                PreparedStatement ps = c.prepareStatement("update detailreturfinalbarangbesar set status='sedang diproses' where id = "+id+"");
                ps.executeUpdate();
            }catch(SQLException exception){
                JOptionPane.showMessageDialog(null, "error dalam update status detail retur final");
            }
        }
        urut();
    }//GEN-LAST:event_menuSimpanSupplierActionPerformed

    private void txtKataKunci1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKataKunci1ActionPerformed
        // TODO add your handling code here:
        btCari1.doClick();
    }//GEN-LAST:event_txtKataKunci1ActionPerformed

    private void btCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCari1ActionPerformed
        try {
            controllerBarang.cariProsesReturBB(this, this);
        } catch (SQLException ex) {
            Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BarangException ex) {
            Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setSize(getWidth(), getHeight());
    }//GEN-LAST:event_btCari1ActionPerformed

    private void cmbCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCari1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCari1ActionPerformed

    private void cmbUrut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUrut1ActionPerformed
        try {
            controllerBarang.sortProsesRetur(this);
        } catch (SQLException ex) {
            Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BarangException ex) {
            Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmbUrut1ActionPerformed

    private void tabelBarang1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarang1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelBarang1MouseClicked

    private void tabelBarang1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarang1MousePressed
        // TODO add your handling code here:
        if(evt.isPopupTrigger()){
            popUpTabel.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tabelBarang1MousePressed

    private void tabelBarang1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarang1MouseReleased
        // TODO add your handling code here:
        if(evt.isPopupTrigger()){
            popUpTabel.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tabelBarang1MouseReleased

        
    private void pilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihActionPerformed
        // TODO add your handling code here:
        if(tabelBarang1.getSelectedRowCount()==1){
            String data=tabelBarang1.getValueAt(tabelBarang1.getSelectedRow(), 0).toString();
            for(int a=0; a<rowsSelected.length; a++){
                String id = tabelPRosesRetur.getValueAt(rowsSelected[a], 0).toString();
                String kode = tabelPRosesRetur.getValueAt(rowsSelected[a], 4).toString();
                int jumlah = Integer.parseInt(tabelPRosesRetur.getValueAt(rowsSelected[a], 6).toString());                    
                int harga = getHargaGrosirBarang(kode);
                int subHarga = Integer.parseInt(tabelPRosesRetur.getValueAt(rowsSelected[a], 7).toString());
                String asalBarang = tabelPRosesRetur.getValueAt(rowsSelected[a], 8).toString();                
                String penukaran  = "Barang Beda Nominal Sama";
                String supplier  = tabelPRosesRetur.getValueAt(rowsSelected[a], 10).toString();
                
                prosesPergantianBarang(data, kode, jumlah, id, harga, penukaran, subHarga, supplier);
            }
            DialogCariBarang.dispose();
            urut();
        }
    }//GEN-LAST:event_pilihActionPerformed

    private void tabelPRosesReturMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPRosesReturMouseReleased
        // TODO add your handling code here:
        if(evt.isPopupTrigger()){
            popUpSimpanSupplier.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tabelPRosesReturMouseReleased

    public void prosesPergantianBarang(String kodePengembali, String kodeBarang, int jumlah, String id, int harga, String penukaran, int subHarga, String supplier){
            String tabelPengembalian = "";
            if(posisiPengembalian.equals("Toko")){
                tabelPengembalian = "barangtoko";
            }else if(posisiPengembalian.equals("Gudang")){
                tabelPengembalian = "barangbesar";
            }        
            
        //ambil stok barangkecil sebelumnya
        int jumlahSebelumnya = getJumlahStokBarangToko(kodePengembali);
        int jumlahSekarang = jumlah + jumlahSebelumnya;
        //update stok barangkecil pengganti (bertambah)
        PreparedStatement ps = null;
                    try{
                    //insert stok barangkecil tambahan dari retur pabrik
                        ps = c.prepareStatement("Update "+tabelPengembalian+" set stok = "+jumlahSekarang+" WHERE idbarang='"+kodePengembali+"'");
                        //WHERE idbarang='"+txtkodeitem.getText()+"'"
                        //Update Barang set stok = 50 WHERE idbarang ='tes'
                        ps.executeUpdate();
                        
                        //delete from retur proses
                        ps = c.prepareStatement("DELETE FROM detailreturfinalbarangbesar WHERE id= '"+id+"'");
                        ps.executeUpdate();
                        
                        //insert into laporan pengembalian barangkecil kecil
                        
                        ps = c.prepareStatement("INSERT INTO laporanpengembalianbarangbesar (tanggal, kode, jumlah, harga, penukaran, subharga, supplier) values (?, ?, ?, ?, ?, ?, ?) ");
                        ps.setString(1, loadTanggal());
                        ps.setString(2, kodeBarang);
                        ps.setInt(3, jumlah);
                        ps.setInt(4, harga);
                        ps.setString(5, penukaran);
                        ps.setInt(6, subHarga);
                        ps.setString(7, supplier);
                        ps.executeUpdate();
                        
                }catch(SQLException exception){
                    JOptionPane.showMessageDialog(null, "error dalam btproses act performed karena "+exception);
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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogCariBarang;
    private javax.swing.JDialog DialogPilihanRetur;
    private javax.swing.JDialog DialogUpdateProsesRetur;
    private javax.swing.JButton Reset;
    private javax.swing.JButton Update;
    private javax.swing.JPanel atas;
    private javax.swing.JPanel atas2;
    private javax.swing.JPanel atas4;
    private javax.swing.JPanel bawah;
    private javax.swing.JPanel bawah2;
    private javax.swing.JPanel bawah4;
    private javax.swing.JButton btCari1;
    private javax.swing.JButton btCariProsesRetur;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btProses;
    private javax.swing.JButton btProsesPengambilanReturSupplier;
    private javax.swing.JButton btUpdate;
    private javax.swing.JComboBox cmbCari1;
    private javax.swing.JComboBox cmbCariPRosesRetur;
    private javax.swing.JComboBox cmbKodeUpdate;
    private javax.swing.JComboBox cmbPilihanReturSupplier;
    private javax.swing.JComboBox cmbPosisiPengembalian;
    private javax.swing.JComboBox cmbUrut1;
    private javax.swing.JComboBox cmbUrutProsesRetur;
    private javax.swing.JComboBox cmbstatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel kanan1;
    private javax.swing.JPanel kanan3;
    private javax.swing.JPanel kiri2;
    private javax.swing.JPanel kiri4;
    private com.wissensalt.sinarelektronik.swinglib.labeling labeling13;
    private com.wissensalt.sinarelektronik.swinglib.labeling labeling14;
    private com.wissensalt.sinarelektronik.swinglib.labeling labeling15;
    private com.wissensalt.sinarelektronik.swinglib.labeling labeling7;
    private com.wissensalt.sinarelektronik.swinglib.labeling labeling8;
    private com.wissensalt.sinarelektronik.swinglib.labeling labeling9;
    private javax.swing.JMenuItem menuSimpanSupplier;
    private javax.swing.JPanel middle2;
    private com.wissensalt.sinarelektronik.swinglib.panelGradUser panelBackgroundUp1;
    private com.wissensalt.sinarelektronik.swinglib.panelGradUser panelBackgroundUp3;
    private com.wissensalt.sinarelektronik.swinglib.panelGradUser panelGradUser4;
    private javax.swing.JMenuItem pilih;
    private javax.swing.JPopupMenu popUpSimpanSupplier;
    private javax.swing.JPopupMenu popUpTabel;
    private javax.swing.JTable tabelBarang1;
    private javax.swing.JTable tabelPRosesRetur;
    private javax.swing.JPanel tengah;
    private javax.swing.JPanel tengah2;
    private javax.swing.JPanel tengah4;
    private javax.swing.JTextField txtIdUpdate;
    private javax.swing.JTextField txtJumlahUpdate;
    private javax.swing.JTextField txtKataKunci1;
    private javax.swing.JTextField txtKataKunciProsesRetur;
    private javax.swing.JTextField txtNamaUpdate;
    private javax.swing.JTextField txtSubhargaUpdate;
    private javax.swing.JPanel up2;
    private javax.swing.JPanel up4;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onChange(BarangBesarModel model) {
        txtKataKunci1.setText(modelBarang.getCari());
    }

    @Override
    public void onInsert(barang barang) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onUpdate(barang barang) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onDelete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onSearch(List list) {
        tabelmodelBarangBesar.setList(list);
    }

    @Override
    public void onSort(List list) {
        tabelmodelBarangBesar.setList(list);
    }

}

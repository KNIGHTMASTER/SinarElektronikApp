/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.retur.transaksi.view;

import Sinarelektronikapp.config.InternetProtocol;
import Sinarelektronikapp.masterData.barang.controller.BarangController;
import Sinarelektronikapp.masterData.barang.database.barangDatabase;
import Sinarelektronikapp.masterData.barang.entity.barang;
import Sinarelektronikapp.masterData.barang.error.BarangException;
import Sinarelektronikapp.masterData.barang.model.barangModel;
import Sinarelektronikapp.masterData.barang.model.event.barangListener;
import Sinarelektronikapp.masterData.barang.model.tabelModelBarang;
import Sinarelektronikapp.masterData.barang.service.BarangDao;
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
public class ProsesRetur extends javax.swing.JPanel implements barangListener{

    /**
     * Creates new form ProsesRetur
     */   
    private  tabelModelBarang tabelmodelBarang ;
    
    private BarangController controllerBarang;
    
    private  barangModel modelBarang;    
    
    public ProsesRetur() {
        tabelmodelBarang = new tabelModelBarang();
        
        modelBarang=  new barangModel();
        modelBarang.setListener(this);        
        
        controllerBarang = new BarangController();
        controllerBarang.setModel(modelBarang);                
        initComponents();           
        
        tabelBarang1.setModel(tabelmodelBarang);       
        try {
            loadDatabaseCariBarang();
        } catch (SQLException ex) {
            Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BarangException ex) {
            Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadData();
        loadKodeUpdate();
        tabelPRosesRetur.setRowSelectionAllowed(true);
        tabelPRosesRetur.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cmbCariPRosesRetur.setSelectedItem("supplier");
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
    
    public void loadDatabaseCariBarang() throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        tabelmodelBarang.setList(dao.selectAllBarang());
    }        
    
    public void loadData(){
        DefaultTableModel modelTable = new DefaultTableModel();        
        String[] kolom = {"id", "no_retur", "user", "tanggal", "kode", "jumlah", "harga", "penukaran (pembeli)", "subharga", "supplier", "status"};
        modelTable.setColumnIdentifiers(kolom);
        String[] data = new String[11];
        koneksi();
        Statement s = null;
        try{
            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM detailreturfinal");
            while (rs.next()) {                
                data[0] = rs.getString("id");
                data[1] = rs.getString("no_retur");
                data[2] = rs.getString("user");
                data[3] = rs.getString("tanggal");
                data[4] = rs.getString("kode");
                data[5] = rs.getString("jumlah");
                data[6] = rs.getString("harga");
                data[7] = rs.getString("penukaran");
                data[8] = rs.getString("subharga");
                data[9] = rs.getString("supplier");
                data[10] = rs.getString("status");
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
        cmbPilihanReturSupplier = new javax.swing.JComboBox();
        btProsesPengambilanReturSupplier = new javax.swing.JButton();
        popUpSimpanSupplier = new javax.swing.JPopupMenu();
        menuSimpanSupplier = new javax.swing.JMenuItem();
        DialogCariBarang = new javax.swing.JDialog();
        up3 = new javax.swing.JPanel();
        panelBackgroundUp2 = new Sinarelektronikapp.swingResource.panelGradUser();
        kiri3 = new javax.swing.JPanel();
        labeling10 = new Sinarelektronikapp.swingResource.labeling();
        labeling11 = new Sinarelektronikapp.swingResource.labeling();
        labeling12 = new Sinarelektronikapp.swingResource.labeling();
        kanan2 = new javax.swing.JPanel();
        atas3 = new javax.swing.JPanel();
        txtKataKunci1 = new javax.swing.JTextField();
        btCari1 = new javax.swing.JButton();
        tengah3 = new javax.swing.JPanel();
        cmbCari1 = new javax.swing.JComboBox();
        bawah3 = new javax.swing.JPanel();
        cmbUrut1 = new javax.swing.JComboBox();
        middle2 = new javax.swing.JPanel();
        panelGradUser4 = new Sinarelektronikapp.swingResource.panelGradUser();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelBarang1 = new javax.swing.JTable();
        popUpTabel = new javax.swing.JPopupMenu();
        pilih = new javax.swing.JMenuItem();
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

        DialogPilihanRetur.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DialogPilihanRetur.setTitle("Penukaran Dari Supplier");

        cmbPilihanReturSupplier.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tukar Barang", "Tukar Uang", "Tukar Barang (beda harga sama)" }));
        DialogPilihanRetur.getContentPane().add(cmbPilihanReturSupplier, java.awt.BorderLayout.CENTER);

        btProsesPengambilanReturSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/login.png"))); // NOI18N
        btProsesPengambilanReturSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProsesPengambilanReturSupplierActionPerformed(evt);
            }
        });
        DialogPilihanRetur.getContentPane().add(btProsesPengambilanReturSupplier, java.awt.BorderLayout.PAGE_END);

        menuSimpanSupplier.setText("Proses Menuju Supplier");
        menuSimpanSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSimpanSupplierActionPerformed(evt);
            }
        });
        popUpSimpanSupplier.add(menuSimpanSupplier);

        up3.setLayout(new java.awt.BorderLayout());

        panelBackgroundUp2.setLayout(new java.awt.BorderLayout());

        kiri3.setOpaque(false);
        kiri3.setLayout(new java.awt.GridLayout(3, 1));

        labeling10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labeling10.setText("Masukkan kata kunci :");
        kiri3.add(labeling10);

        labeling11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labeling11.setText("Cari data berdasarkan :");
        kiri3.add(labeling11);

        labeling12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labeling12.setText("Urutkan data berdasarkan :");
        kiri3.add(labeling12);

        panelBackgroundUp2.add(kiri3, java.awt.BorderLayout.WEST);

        kanan2.setOpaque(false);
        kanan2.setLayout(new java.awt.GridLayout(3, 0));

        atas3.setOpaque(false);

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

        javax.swing.GroupLayout atas3Layout = new javax.swing.GroupLayout(atas3);
        atas3.setLayout(atas3Layout);
        atas3Layout.setHorizontalGroup(
            atas3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(atas3Layout.createSequentialGroup()
                .addComponent(txtKataKunci1, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCari1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        atas3Layout.setVerticalGroup(
            atas3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtKataKunci1)
            .addComponent(btCari1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        kanan2.add(atas3);

        tengah3.setOpaque(false);
        tengah3.setLayout(new java.awt.BorderLayout());

        cmbCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id barang", "id barcode", "nama barang", "tipe", "merek", "harga", "satuan", "stok", "stok minimum", "supplier", "keterangan" }));
        cmbCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCari1ActionPerformed(evt);
            }
        });
        tengah3.add(cmbCari1, java.awt.BorderLayout.CENTER);

        kanan2.add(tengah3);

        bawah3.setOpaque(false);
        bawah3.setLayout(new java.awt.BorderLayout());

        cmbUrut1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id barang", "id barcode", "nama barang", "tipe", "merek", "harga", "satuan", "stok", "stok minimum", "supplier", "keterangan" }));
        cmbUrut1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUrut1ActionPerformed(evt);
            }
        });
        bawah3.add(cmbUrut1, java.awt.BorderLayout.CENTER);

        kanan2.add(bawah3);

        panelBackgroundUp2.add(kanan2, java.awt.BorderLayout.CENTER);

        up3.add(panelBackgroundUp2, java.awt.BorderLayout.CENTER);

        DialogCariBarang.getContentPane().add(up3, java.awt.BorderLayout.PAGE_START);

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

        txtKataKunciProsesRetur.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
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
                .addComponent(txtKataKunciProsesRetur, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCariProsesRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        atas2Layout.setVerticalGroup(
            atas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtKataKunciProsesRetur)
            .addComponent(btCariProsesRetur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        kanan1.add(atas2);

        tengah2.setMaximumSize(new java.awt.Dimension(272, 80));
        tengah2.setOpaque(false);
        tengah2.setPreferredSize(new java.awt.Dimension(272, 80));
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
        cmbUrutProsesRetur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id", "no_retur", "user", "tanggal", "kode", "jumlah", "harga", "penukaran", "subharga", "supplier" }));
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
        String[] kolom = {"id", "no_retur", "user", "tanggal", "kode", "jumlah", "harga", "penukaran", "subharga", "supplier", "status"};
        modelTable.setColumnIdentifiers(kolom);
        String[] data = new String[11];
        koneksi();
        Statement s = null;
        try{
            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM detailreturfinal WHERE "+berdasarkan+" like '%"+katakunci+"%' and status like '%"+cmbstatus.getSelectedItem().toString()+"%'");
            while (rs.next()) {                
                data[0] = rs.getString("id");
                data[1] = rs.getString("no_retur");
                data[2] = rs.getString("user");
                data[3] = rs.getString("tanggal");
                data[4] = rs.getString("kode");
                data[5] = rs.getString("jumlah");
                data[6] = rs.getString("harga");
                data[7] = rs.getString("penukaran");
                data[8] = rs.getString("subharga");
                data[9] = rs.getString("supplier");
                data[10] = rs.getString("status");
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
        String[] kolom = {"id", "no_retur", "user", "tanggal", "kode", "jumlah", "harga", "penukaran", "subharga", "supplier", "status"};
        modelTable.setColumnIdentifiers(kolom);
        String[] data = new String[11];
        koneksi();
        Statement s = null;
        try{
            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM detailreturfinal ORDER BY "+berdasarkan);
            while (rs.next()) {                
                data[0] = rs.getString("id");
                data[1] = rs.getString("no_retur");
                data[2] = rs.getString("user");
                data[3] = rs.getString("tanggal");
                data[4] = rs.getString("kode");
                data[5] = rs.getString("jumlah");
                data[6] = rs.getString("harga");
                data[7] = rs.getString("penukaran");
                data[8] = rs.getString("subharga");
                data[9] = rs.getString("supplier");
                data[10] = rs.getString("status");
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
            DialogPilihanRetur.setSize(300, 120);
            DialogPilihanRetur.show();
        }                                
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

    Date date=new Date();        
    
    SimpleDateFormat formatTanggal=new SimpleDateFormat("yyyy-MM-dd");    
    
    private String loadTanggal(){        
        return formatTanggal.format(date);
    }
    
    public void prosesPenukaranBarangOlehSupplier(){
            koneksi();
            PreparedStatement ps = null; 
            for(int a=0; a<rowsSelected.length; a++){
                String id = tabelPRosesRetur.getValueAt(rowsSelected[a], 0).toString();
                String kode = tabelPRosesRetur.getValueAt(rowsSelected[a], 4).toString();
                int jumlah = Integer.parseInt(tabelPRosesRetur.getValueAt(rowsSelected[a], 5).toString());                    
                int harga = Integer.parseInt(tabelPRosesRetur.getValueAt(rowsSelected[a], 6).toString());
                String penukaran  = "Barang";
                int subHarga = Integer.parseInt(tabelPRosesRetur.getValueAt(rowsSelected[a], 8).toString());
                String supplier  = tabelPRosesRetur.getValueAt(rowsSelected[a], 9).toString();
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
                        
                        //insert into laporan pengembalian barang kecil
                        
                        ps = c.prepareStatement("INSERT INTO laporanpengembalianbarangkecil (tanggal, kode, jumlah, harga, penukaran, subharga, supplier) values (?, ?, ?, ?, ?, ?, ?) ");
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
                int jumlah = Integer.parseInt(tabelPRosesRetur.getValueAt(rowsSelected[a], 5).toString());                    
                int harga = Integer.parseInt(tabelPRosesRetur.getValueAt(rowsSelected[a], 6).toString());
                String penukaran  = "Uang";
                int subHarga = Integer.parseInt(tabelPRosesRetur.getValueAt(rowsSelected[a], 8).toString());
                String supplier  = tabelPRosesRetur.getValueAt(rowsSelected[a], 9).toString();                
                    try{
                    //insert stok barang tambahan dari retur pabrik
                        /*int stokBefore = getJumlahStokBarang(kode);
                        int stokAfter = stokBefore+jumlah;
                        ps = c.prepareStatement("Update Barang set stok = "+stokAfter+" WHERE idbarang='"+kode+"'");
                        //WHERE idbarang='"+txtkodeitem.getText()+"'"
                        //Update Barang set stok = 50 WHERE idbarang ='tes'
                        ps.executeUpdate();*/
                        
                        //delete from retur proses
                        ps = c.prepareStatement("DELETE FROM detailreturfinal WHERE id= '"+id+"'");
                        ps.executeUpdate();
                        
                        //insert into laporan pengembalian barang kecil
                        
                        ps = c.prepareStatement("INSERT INTO laporanpengembalianbarangkecil (tanggal, kode, jumlah, harga, penukaran, subharga, supplier) values (?, ?, ?, ?, ?, ?, ?) ");
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
    
    
    
    private void btProsesPengambilanReturSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProsesPengambilanReturSupplierActionPerformed
        // TODO add your handling code here:        
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
                PreparedStatement ps = c.prepareStatement("update detailreturfinal set status='sedang diproses' where id = "+id+"");
                ps.executeUpdate();
            }catch(SQLException exception){
                JOptionPane.showMessageDialog(null, "error dalam update status detail retur final");
            }
        }
        urut();
    }//GEN-LAST:event_menuSimpanSupplierActionPerformed

    private void tabelPRosesReturMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPRosesReturMouseReleased
        // TODO add your handling code here:
        if(evt.isPopupTrigger()){            
            popUpSimpanSupplier.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tabelPRosesReturMouseReleased

    private void txtKataKunci1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKataKunci1ActionPerformed
        // TODO add your handling code here:
        btCari1.doClick();
    }//GEN-LAST:event_txtKataKunci1ActionPerformed

    private void btCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCari1ActionPerformed
        try {
            controllerBarang.cariProsesRetur(this, this);
            this.setSize(getWidth(), getHeight());
        } catch (SQLException ex) {
            Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BarangException ex) {
            Logger.getLogger(ProsesRetur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btCari1ActionPerformed

    private void cmbCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCari1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCari1ActionPerformed

    private void cmbUrut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUrut1ActionPerformed
        try {
            controllerBarang.sortProsesRetur(this);
            this.setSize(getWidth(), getHeight());
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
    }//GEN-LAST:event_tabelBarang1MousePressed

    private void tabelBarang1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarang1MouseReleased
        // TODO add your handling code here:
        if(evt.isPopupTrigger()){
            popUpTabel.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tabelBarang1MouseReleased

    public void prosesPergantianBarang(String kodeBarang, int jumlah, String id, int harga, String penukaran, int subHarga, String supplier){
        //ambil stok barang sebelumnya
        int jumlahSebelumnya = getJumlahStokBarang(kodeBarang);
        int jumlahSekarang = jumlah + jumlahSebelumnya;
        //update stok barang pengganti (bertambah)
        PreparedStatement ps = null;
                    try{
                    //insert stok barang tambahan dari retur pabrik
                        ps = c.prepareStatement("Update Barang set stok = "+jumlahSekarang+" WHERE idbarang='"+kodeBarang+"'");
                        //WHERE idbarang='"+txtkodeitem.getText()+"'"
                        //Update Barang set stok = 50 WHERE idbarang ='tes'
                        ps.executeUpdate();
                        
                        //delete from retur proses
                        ps = c.prepareStatement("DELETE FROM detailreturfinal WHERE id= '"+id+"'");
                        ps.executeUpdate();
                        
                        //insert into laporan pengembalian barang kecil
                        
                        ps = c.prepareStatement("INSERT INTO laporanpengembalianbarangkecil (tanggal, kode, jumlah, harga, penukaran, subharga, supplier) values (?, ?, ?, ?, ?, ?, ?) ");
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
    
    private void pilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihActionPerformed
        // TODO add your handling code here:
        if(tabelBarang1.getSelectedRowCount()==1){
            String kodeBarang=tabelBarang1.getValueAt(tabelBarang1.getSelectedRow(), 0).toString();
            for(int a=0; a<rowsSelected.length; a++){
                String id = tabelPRosesRetur.getValueAt(rowsSelected[a], 0).toString();
                String kode = tabelPRosesRetur.getValueAt(rowsSelected[a], 4).toString();
                int jumlah = Integer.parseInt(tabelPRosesRetur.getValueAt(rowsSelected[a], 5).toString());                    
                int harga = Integer.parseInt(tabelPRosesRetur.getValueAt(rowsSelected[a], 6).toString());
                String penukaran  = "Barang Beda Nominal Sama";
                int subHarga = Integer.parseInt(tabelPRosesRetur.getValueAt(rowsSelected[a], 8).toString());
                String supplier  = tabelPRosesRetur.getValueAt(rowsSelected[a], 9).toString();
                
                prosesPergantianBarang(kodeBarang, jumlah, id, harga, penukaran, subHarga, supplier);
            }            
            DialogCariBarang.dispose();
            urut();
        }
    }//GEN-LAST:event_pilihActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogCariBarang;
    private javax.swing.JDialog DialogPilihanRetur;
    private javax.swing.JDialog DialogUpdateProsesRetur;
    private javax.swing.JButton Reset;
    private javax.swing.JButton Update;
    private javax.swing.JPanel atas;
    private javax.swing.JPanel atas2;
    private javax.swing.JPanel atas3;
    private javax.swing.JPanel bawah;
    private javax.swing.JPanel bawah2;
    private javax.swing.JPanel bawah3;
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
    private javax.swing.JComboBox cmbUrut1;
    private javax.swing.JComboBox cmbUrutProsesRetur;
    private javax.swing.JComboBox cmbstatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel kanan1;
    private javax.swing.JPanel kanan2;
    private javax.swing.JPanel kiri2;
    private javax.swing.JPanel kiri3;
    private Sinarelektronikapp.swingResource.labeling labeling10;
    private Sinarelektronikapp.swingResource.labeling labeling11;
    private Sinarelektronikapp.swingResource.labeling labeling12;
    private Sinarelektronikapp.swingResource.labeling labeling7;
    private Sinarelektronikapp.swingResource.labeling labeling8;
    private Sinarelektronikapp.swingResource.labeling labeling9;
    private javax.swing.JMenuItem menuSimpanSupplier;
    private javax.swing.JPanel middle2;
    private Sinarelektronikapp.swingResource.panelGradUser panelBackgroundUp1;
    private Sinarelektronikapp.swingResource.panelGradUser panelBackgroundUp2;
    private Sinarelektronikapp.swingResource.panelGradUser panelGradUser4;
    private javax.swing.JMenuItem pilih;
    private javax.swing.JPopupMenu popUpSimpanSupplier;
    private javax.swing.JPopupMenu popUpTabel;
    private javax.swing.JTable tabelBarang1;
    private javax.swing.JTable tabelPRosesRetur;
    private javax.swing.JPanel tengah;
    private javax.swing.JPanel tengah2;
    private javax.swing.JPanel tengah3;
    private javax.swing.JTextField txtIdUpdate;
    private javax.swing.JTextField txtJumlahUpdate;
    private javax.swing.JTextField txtKataKunci1;
    private javax.swing.JTextField txtKataKunciProsesRetur;
    private javax.swing.JTextField txtNamaUpdate;
    private javax.swing.JTextField txtSubhargaUpdate;
    private javax.swing.JPanel up2;
    private javax.swing.JPanel up3;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onChange(barangModel model) {
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
        tabelmodelBarang.setList(list);
    }

    @Override
    public void onSort(List list) {
        tabelmodelBarang.setList(list);
    }

}

package com.wissensalt.sinarelektronik.transfer.barangbesar.view;

import com.wissensalt.sinarelektronik.masterdata.barangtoko.database.barangDatabase;
import com.wissensalt.sinarelektronik.masterdata.barangtoko.entity.barang;
import com.wissensalt.sinarelektronik.masterdata.barangtoko.service.BarangDao;
import com.wissensalt.sinarelektronik.model.BarangBesarModel;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.model.TabelModelBarangBesar;
import com.wissensalt.sinarelektronik.util.FormatRupiah;
import com.wissensalt.sinarelektronik.util.InputHarusAngka;
import com.wissensalt.sinarelektronik.transfer.barangbesar.controller.penjualanController;
import com.wissensalt.sinarelektronik.transfer.barangbesar.entity.ProsesKasir;
import com.wissensalt.sinarelektronik.transfer.barangbesar.model.PenjualanModel;
import com.wissensalt.sinarelektronik.transfer.barangbesar.model.TabelModelPenjualan;
import com.wissensalt.sinarelektronik.transfer.barangbesar.model.event.penjualanListener;
import com.wissensalt.sinarelektronik.config.ActiveUser;
import com.wissensalt.sinarelektronik.config.HostName;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.controller.BarangBesarController;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.error.BarangException;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.model.event.BarangBesarListener;
import com.wissensalt.sinarelektronik.masterdata.report.core.ReportParam;
import com.wissensalt.sinarelektronik.swinglib.AutoComplete.DefaultModelAutoComplete;
import com.wissensalt.sinarelektronik.swinglib.AutoComplete.TextFieldAutoComplete;
import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Blob;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fauzi
 */
public class JIFTransferBB extends javax.swing.JInternalFrame implements penjualanListener, BarangBesarListener {

    /**
     * Creates new form JIFTransferBB
     */
    InputHarusAngka iha = new InputHarusAngka();
    
    TabelModelPenjualan modelPenjualan;
    
    private PenjualanModel model;
    
    private penjualanController controller;
    
    private TabelModelBarangBesar tabelmodelBarangBesar;
    
    private BarangBesarController controllerBarang;
    
    private BarangBesarModel modelBarang;
    
    private FormatRupiah formatter=new FormatRupiah();
    
    String ip;

    public JIFTransferBB() {

        tabelmodelBarangBesar = new TabelModelBarangBesar();
        
        modelBarang=  new BarangBesarModel();
        modelBarang.setListener(this);        
        
        controllerBarang = new BarangBesarController();
        controllerBarang.setModel(modelBarang);
        
        modelPenjualan=new TabelModelPenjualan();
    
        model=new PenjualanModel();
        model.setListener(this);
        
        controller=new penjualanController();
        controller.setModel(model);
        
        initComponents();
        
        tabelBarang1.setModel(tabelmodelBarangBesar);
        try {
            loadDatabaseCariBarang();
        } catch (SQLException ex) {
            Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BarangException ex) {
            Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        tabelTransaksi.setModel(modelPenjualan);
        //koneksi();
        loadView();
        setTxtkodeAuto();
        setEnableTransaksi(false);        

    ActionListener taskPerformer = new ActionListener() {
        @Override
      public void actionPerformed(ActionEvent evt) {
        String nol_jam = "";
        String nol_menit = "";
        String nol_detik = "";
        // Membuat Date
        Date dt = new Date();
        // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
        int nilai_jam = dt.getHours();
        int nilai_menit = dt.getMinutes();
        int nilai_detik = dt.getSeconds();
        // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
        if (nilai_jam <= 9) {
          // Tambahkan "0" didepannya
          nol_jam = "0";
        }
        // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
        if (nilai_menit <= 9) {
          // Tambahkan "0" didepannya
          nol_menit = "0";
        }
        // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
        if (nilai_detik <= 9) {
          // Tambahkan "0" didepannya
          nol_detik = "0";
        }
        // Membuat String JAM, MENIT, DETIK
        String jam = nol_jam + Integer.toString(nilai_jam);
        String menit = nol_menit + Integer.toString(nilai_menit);
        String detik = nol_detik + Integer.toString(nilai_detik);
        // Menampilkan pada Layar
        txtJam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
        
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
              @Override
              public boolean dispatchKeyEvent(KeyEvent e) {
                  if(e.getID() == KeyEvent.KEY_PRESSED){
                        if(e.getKeyCode() == KeyEvent.VK_SPACE){
                          entryKodeItem();
                      }
                  }
                return false;
              }
          });
      }
    };
    // Timer
    new Timer(1000, taskPerformer).start();                        
    
    //set combo pencarian data
    cmbCari1.setSelectedItem("id barangkecil");
    }    
    

    
    public penjualanController getController() {
        return controller;
    }

    public void setController(penjualanController controller) {
        this.controller = controller;
    }

    public TabelModelBarangBesar getTabelmodelBarangBesar() {
        return tabelmodelBarangBesar;
    }

    public void setTabelmodelBarangBesar(TabelModelBarangBesar tabelmodelBarangBesar) {
        this.tabelmodelBarangBesar = tabelmodelBarangBesar;
    }

    public BarangBesarController getControllerBarang() {
        return controllerBarang;
    }

    public void setControllerBarang(BarangBesarController controllerBarang) {
        this.controllerBarang = controllerBarang;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    public JTextField getTxtJam() {
        return txtJam;
    }

    public void setTxtJam(JTextField txtJam) {
        this.txtJam = txtJam;
    }

    public JTextField getTxtNoTransaksi() {
        return txtNoTransaksi;
    }

    public void setTxtNoTransaksi(JTextField txtNoTransaksi) {
        this.txtNoTransaksi = txtNoTransaksi;
    }
    
    public JTextField getTxtTanggal() {
        return txtTanggal;
    }

    public void setTxtTanggal(JTextField txtTanggal) {
        this.txtTanggal = txtTanggal;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }
    
    
    public JIFTransferBB(String ip) {
        initComponents();
        this.ip = ip;
    }

    public void loadDatabaseCariBarang() throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        tabelmodelBarangBesar.setList(dao.selectAllBarang());
    }    
    public void setEnableTransaksi(boolean enable){
        txtKodeItem.setEnabled(enable);
        txtJumlah.setEnabled(enable);
        txtDetailItem.setEnabled(enable);
        btViewBarang.setEnabled(enable);
        btGo.setEnabled(enable);
        btTransfer.setEnabled(enable);
    }
    
    public int getStokBarang(String text){
        koneksi();
        int stok=0;
        Statement s = null;
        try{
            s=connection.createStatement();
            ResultSet rs=s.executeQuery("SELECT stok from barangbesar WHERE idbarang='"+text+"'");
            if(rs.next()){
                stok=rs.getInt("stok");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam mengambil stok barangkecil karena = "+stok);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return stok;
    }
    
    public int getJumlahProsesKasir(String kode){
        int hasil=0;
        koneksi();
        Statement statement = null;
        try{
            statement=connection.createStatement();
            ResultSet rs=statement.executeQuery("SELECT jml from prosestransaksibb where kode='"+kode+"'");
            while(rs.next()){
                hasil += rs.getInt("jml");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "tidak bisa mengambil jumlah proseskasir real time karena ="+exception);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return hasil;
    }
     
    
    public int getHargaModal(){
        String grosir = "SELECT modal from barangbesar WHERE idbarang = '"+txtKodeItem.getText()+"'";
        int hargaModal = 0;
        try{
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(grosir);
            if(rs.next()){
                hargaModal = rs.getInt("modal");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam mendapatkan biaya ekspedisi karena "+exception);
        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }

        return hargaModal;
    }
    
   
    public JTextField getTxtDetailItem() {
        return txtDetailItem;
    }

    public JComboBox getCmbCari1() {
        return cmbCari1;
    }

    public JComboBox getCmbUrut1() {
        return cmbUrut1;
    }

    public JTextField getTxtKataKunci1() {
        return txtKataKunci1;
    }

    public void setTxtDetailItem(JTextField txtDetailItem) {
        this.txtDetailItem = txtDetailItem;
    }

    public JTextField getTxtJumlah() {
        return txtJumlah;
    }

    public void setTxtJumlah(JTextField txtJumlah) {
        this.txtJumlah = txtJumlah;
    }

    public TextFieldAutoComplete getTxtKodeItem() {
        return txtKodeItem;
    }

    public void setTxtKodeItem(TextFieldAutoComplete txtKodeItem) {
        this.txtKodeItem = txtKodeItem;
    }   

    public JTable getTabelTransaksi() {
        return tabelTransaksi;
    }           
    
    private void showTable(){
        try{
            DefaultTableModel modeltabbel=new DefaultTableModel();            
        }catch(Exception e){
            
        }
    }
    
    public JTextField getTxtUser() {
        return txtUser;
    }
    

    public void setTxtUser(JTextField txtUser) {
        this.txtUser = txtUser;
    }    
    
    private void setTxtkodeAuto(){
        DefaultModelAutoComplete modelAutoComplete=new DefaultModelAutoComplete(getDataList());
        txtKodeItem.setAutoComplete(modelAutoComplete);
    }
    
    public String [] getDataList(){                        
        String [] data = new String[WIDTH];
        koneksi();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs1=statement.executeQuery("select count(idbarang) total from barangbesar");
            if(rs1.next()){
                data=new String[rs1.getInt("total")];
            }
            ResultSet rs=statement.executeQuery("select idbarang from barangbesar");
            int inc=0;
            while (rs.next()) {                
                String idbarang=rs.getString("idbarang");
                data[inc] = idbarang;
                inc++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return data;
    }    
    
    public void loadView(){        
        ActiveUser activeUser = new ActiveUser();
        txtTanggal.setText(loadTanggal());
        txtUser.setText(activeUser.getUserActive());
    }     
    
    
    private String loadId(){
        koneksi();
        String id="";
        try{
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery("SELECT COUNT(iddetailtransaksi)+1 TOTAL FROM detailtransaksitransferbb");
            if(rs.next()){
                id=rs.getString("TOTAL");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Tidak bisa mengambil id transaksi karena = "+e, "Peringatan", JOptionPane.WARNING_MESSAGE);
        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return id;
    }
    Date date=new Date();        
    
    SimpleDateFormat formatTanggal=new SimpleDateFormat("dd:MM:yyyy");
    SimpleDateFormat formatTanggal2=new SimpleDateFormat("yyyy-MM-dd");
    
    private String loadTanggal(){        
        return formatTanggal.format(date);
    }
    
    private Connection connection;
            
	
    static HostName ip1 = new HostName();
    public void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://"+ip1.getIpServer()+"/sinarelektronik?;", "root", "P@ssw0rd");
        } catch (SQLException ex) {
            Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
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

        DialogCariBarang = new javax.swing.JDialog();
        up2 = new javax.swing.JPanel();
        panelBackgroundUp1 = new com.wissensalt.sinarelektronik.swinglib.panelGradUser();
        kiri2 = new javax.swing.JPanel();
        labeling7 = new com.wissensalt.sinarelektronik.swinglib.labeling();
        labeling8 = new com.wissensalt.sinarelektronik.swinglib.labeling();
        labeling9 = new com.wissensalt.sinarelektronik.swinglib.labeling();
        kanan1 = new javax.swing.JPanel();
        atas2 = new javax.swing.JPanel();
        txtKataKunci1 = new javax.swing.JTextField();
        btCari1 = new javax.swing.JButton();
        btSetCari = new javax.swing.JButton();
        tengah2 = new javax.swing.JPanel();
        cmbCari1 = new javax.swing.JComboBox();
        bawah2 = new javax.swing.JPanel();
        cmbUrut1 = new javax.swing.JComboBox();
        middle2 = new javax.swing.JPanel();
        panelGradUser4 = new com.wissensalt.sinarelektronik.swinglib.panelGradUser();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelBarang1 = new javax.swing.JTable();
        popUpTabel = new javax.swing.JPopupMenu();
        pilih = new javax.swing.JMenuItem();
        DialogPreview = new javax.swing.JDialog();
        lblPreview = new javax.swing.JLabel();
        up = new javax.swing.JPanel();
        atas = new javax.swing.JPanel();
        kiri = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtNoTransaksi = new javax.swing.JTextField();
        txtUser = new javax.swing.JTextField();
        txtTanggal = new javax.swing.JTextField();
        txtJam = new javax.swing.JTextField();
        Kanan = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtTotal = new javax.swing.JTextField();
        bawah = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        btViewBarang = new javax.swing.JButton();
        txtKodeItem = new com.wissensalt.sinarelektronik.swinglib.AutoComplete.TextFieldAutoComplete();
        jPanel8 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtDetailItem = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        btGo = new javax.swing.JButton();
        middle = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelTransaksi = new javax.swing.JTable();
        bottom = new javax.swing.JPanel();
        centerBottom = new javax.swing.JPanel();
        left = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        below = new javax.swing.JPanel();
        btTambah = new javax.swing.JButton();
        btTransfer = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();
        btCetak = new javax.swing.JButton();

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
        atas2.setLayout(new java.awt.BorderLayout());

        txtKataKunci1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKataKunci1ActionPerformed(evt);
            }
        });
        atas2.add(txtKataKunci1, java.awt.BorderLayout.CENTER);

        btCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/cari.png"))); // NOI18N
        btCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCari1ActionPerformed(evt);
            }
        });
        atas2.add(btCari1, java.awt.BorderLayout.EAST);

        btSetCari.setMnemonic('C');
        btSetCari.setText("C");
        btSetCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSetCariActionPerformed(evt);
            }
        });
        atas2.add(btSetCari, java.awt.BorderLayout.WEST);

        kanan1.add(atas2);

        tengah2.setOpaque(false);
        tengah2.setLayout(new java.awt.BorderLayout());

        cmbCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id barangkecil", "id barcode", "nama barangkecil", "tipe", "MerekDTO", "harga", "satuan", "stok", "stok minimum", "supplier", "keterangan" }));
        cmbCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCari1ActionPerformed(evt);
            }
        });
        tengah2.add(cmbCari1, java.awt.BorderLayout.CENTER);

        kanan1.add(tengah2);

        bawah2.setOpaque(false);
        bawah2.setLayout(new java.awt.BorderLayout());

        cmbUrut1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id barangkecil", "id barcode", "nama barangkecil", "tipe", "MerekDTO", "harga", "satuan", "stok", "stok minimum", "supplier", "keterangan" }));
        cmbUrut1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUrut1ActionPerformed(evt);
            }
        });
        bawah2.add(cmbUrut1, java.awt.BorderLayout.CENTER);

        kanan1.add(bawah2);

        panelBackgroundUp1.add(kanan1, java.awt.BorderLayout.CENTER);

        up2.add(panelBackgroundUp1, java.awt.BorderLayout.CENTER);

        DialogCariBarang.getContentPane().add(up2, java.awt.BorderLayout.PAGE_START);

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
        tabelBarang1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelBarang1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tabelBarang1KeyTyped(evt);
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

        DialogPreview.setMinimumSize(new java.awt.Dimension(165, 170));
        DialogPreview.getContentPane().add(lblPreview, java.awt.BorderLayout.CENTER);

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Proses Transfer Barang Besar");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        up.setPreferredSize(new java.awt.Dimension(700, 250));
        up.setLayout(new java.awt.BorderLayout());

        atas.setLayout(new java.awt.BorderLayout());

        kiri.setLayout(new java.awt.GridLayout(1, 2));

        jPanel1.setLayout(new java.awt.GridLayout(4, 0));

        jLabel1.setText("No. Transaksi :");
        jPanel1.add(jLabel1);

        jLabel2.setText("User               :");
        jPanel1.add(jLabel2);

        jLabel3.setText("Tanggal         :");
        jPanel1.add(jLabel3);

        jLabel12.setText("Jam               :");
        jPanel1.add(jLabel12);

        kiri.add(jPanel1);

        jPanel2.setLayout(new java.awt.GridLayout(4, 1));

        txtNoTransaksi.setEditable(false);
        txtNoTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoTransaksiActionPerformed(evt);
            }
        });
        jPanel2.add(txtNoTransaksi);

        txtUser.setEditable(false);
        jPanel2.add(txtUser);

        txtTanggal.setEditable(false);
        txtTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTanggalActionPerformed(evt);
            }
        });
        jPanel2.add(txtTanggal);

        txtJam.setEditable(false);
        jPanel2.add(txtJam);

        kiri.add(jPanel2);

        atas.add(kiri, java.awt.BorderLayout.WEST);

        Kanan.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel4.setText("Total :");
        jPanel3.add(jLabel4, java.awt.BorderLayout.CENTER);

        Kanan.add(jPanel3, java.awt.BorderLayout.WEST);

        jPanel4.setLayout(new java.awt.BorderLayout());

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(255, 0, 51));
        jPanel4.add(txtTotal, java.awt.BorderLayout.CENTER);

        Kanan.add(jPanel4, java.awt.BorderLayout.CENTER);

        atas.add(Kanan, java.awt.BorderLayout.CENTER);

        up.add(atas, java.awt.BorderLayout.CENTER);

        bawah.setMaximumSize(new java.awt.Dimension(32767, 85));
        bawah.setMinimumSize(new java.awt.Dimension(496, 85));
        bawah.setPreferredSize(new java.awt.Dimension(496, 85));
        bawah.setLayout(new java.awt.GridLayout(1, 4));

        jPanel7.setLayout(new java.awt.GridLayout(2, 1));

        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel12.setLayout(new java.awt.BorderLayout());

        jLabel7.setText("Kode Item      :");
        jPanel12.add(jLabel7, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel12, java.awt.BorderLayout.WEST);

        jPanel13.setLayout(new java.awt.BorderLayout());

        btViewBarang.setMnemonic('F');
        btViewBarang.setText("...");
        btViewBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btViewBarangActionPerformed(evt);
            }
        });
        jPanel13.add(btViewBarang, java.awt.BorderLayout.EAST);

        txtKodeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeItemActionPerformed(evt);
            }
        });
        txtKodeItem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtKodeItemFocusLost(evt);
            }
        });
        jPanel13.add(txtKodeItem, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel10);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel24.setText("Nama             :");
        jPanel8.add(jLabel24, java.awt.BorderLayout.WEST);

        txtDetailItem.setEditable(false);
        txtDetailItem.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        txtDetailItem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDetailItemFocusGained(evt);
            }
        });
        jPanel8.add(txtDetailItem, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel8);

        bawah.add(jPanel7);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel22.setLayout(new java.awt.BorderLayout());

        jLabel8.setText("Jumlah :");
        jPanel22.add(jLabel8, java.awt.BorderLayout.WEST);

        txtJumlah.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahActionPerformed(evt);
            }
        });
        txtJumlah.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtJumlahFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtJumlahFocusLost(evt);
            }
        });
        jPanel22.add(txtJumlah, java.awt.BorderLayout.CENTER);

        jPanel9.add(jPanel22, java.awt.BorderLayout.CENTER);

        btGo.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        btGo.setText("Go");
        btGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGoActionPerformed(evt);
            }
        });
        jPanel9.add(btGo, java.awt.BorderLayout.EAST);

        bawah.add(jPanel9);

        up.add(bawah, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(up, java.awt.BorderLayout.PAGE_START);

        middle.setPreferredSize(new java.awt.Dimension(700, 200));
        middle.setLayout(new java.awt.BorderLayout());

        tabelTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelTransaksi);

        middle.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(middle, java.awt.BorderLayout.CENTER);

        bottom.setPreferredSize(new java.awt.Dimension(700, 115));
        bottom.setLayout(new java.awt.BorderLayout());

        centerBottom.setLayout(new java.awt.GridLayout(1, 0));

        left.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Short Cut"));
        left.setPreferredSize(new java.awt.Dimension(196, 92));
        left.setLayout(new java.awt.GridLayout(1, 2));

        jPanel5.setLayout(new java.awt.GridLayout(3, 0));

        jLabel17.setText("Tambah Transaksi : Alt + T");
        jPanel5.add(jLabel17);

        jLabel18.setText("Cancel : Alt + C");
        jPanel5.add(jLabel18);

        jLabel19.setText("Hapus Item Transaksi : Alt + H");
        jPanel5.add(jLabel19);

        left.add(jPanel5);

        jPanel25.setLayout(new java.awt.GridLayout(3, 0));

        jLabel16.setText("Cetak Transaksi : Alt + E");
        jPanel25.add(jLabel16);

        jLabel20.setText("Pilih Barang : Spasi");
        jPanel25.add(jLabel20);

        jLabel21.setText("Cari barangkecil : Alt+F");
        jPanel25.add(jLabel21);

        left.add(jPanel25);

        centerBottom.add(left);

        bottom.add(centerBottom, java.awt.BorderLayout.CENTER);

        below.setLayout(new java.awt.GridLayout(1, 5));

        btTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/tambah.png"))); // NOI18N
        btTambah.setMnemonic('T');
        btTambah.setFocusable(false);
        btTambah.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btTambah.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });
        below.add(btTambah);

        btTransfer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/transfer.png"))); // NOI18N
        btTransfer.setMnemonic('R');
        btTransfer.setEnabled(false);
        btTransfer.setFocusable(false);
        btTransfer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btTransfer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTransferActionPerformed(evt);
            }
        });
        below.add(btTransfer);

        btCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/cance.png"))); // NOI18N
        btCancel.setMnemonic('C');
        btCancel.setFocusable(false);
        btCancel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCancel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });
        below.add(btCancel);

        btHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/hapus.png"))); // NOI18N
        btHapus.setMnemonic('H');
        btHapus.setFocusable(false);
        btHapus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btHapus.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });
        below.add(btHapus);

        btCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/cetak.png"))); // NOI18N
        btCetak.setMnemonic('E');
        btCetak.setFocusable(false);
        btCetak.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCetak.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCetakActionPerformed(evt);
            }
        });
        below.add(btCetak);

        bottom.add(below, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(bottom, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNoTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoTransaksiActionPerformed

    private void txtTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTanggalActionPerformed
    private void cariBarang(){
        txtKodeItem.setText("");
        txtDetailItem.setText("");
        txtJumlah.setText("");
        DialogCariBarang.setSize(800, 900);
        DialogCariBarang.show();
        DialogCariBarang.setModal(true);
        DialogCariBarang.setTitle("Pencarian Barang");
        DialogCariBarang.setLocationRelativeTo(null);        
    }
    
    private void btViewBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btViewBarangActionPerformed
        // TODO add your handling code here:
        cariBarang();
    }//GEN-LAST:event_btViewBarangActionPerformed

    public String getDetailItem(String namaItem){
        koneksi();
        String hasil="";
        Statement s = null;
        try {
            s=connection.createStatement();
            ResultSet rs=  s.executeQuery("SELECT namabarang from barangbesar where idbarang = '"+namaItem+"'");
            if(rs.next()){
                hasil=rs.getString("namabarang");
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return hasil;
    }
    
    
    private void txtKodeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeItemActionPerformed
        // TODO add your handling code here:
        txtKodeItem.setForeground(Color.BLACK);
        txtDetailItem.setText(getDetailItem(txtKodeItem.getText()));
        if(!txtDetailItem.getText().trim().equals("")){
            txtJumlah.requestFocus();
        }else{
            txtKodeItem.requestFocus();
            JOptionPane.showMessageDialog(null, "Kode item masih kosong");
            return;
        }
    }//GEN-LAST:event_txtKodeItemActionPerformed

    private void txtKodeItemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtKodeItemFocusLost
        // TODO add your handling code here:
        /*if(txtKodeItem.getText().trim().equals("")){
            txtKodeItem.setText("");
            JOptionPane.showMessageDialog(null, "Kode item masih kosong", "informasi", JOptionPane.INFORMATION_MESSAGE);
            txtKodeItem.requestFocus();
            return;
        }else{
            txtKodeItem.setForeground(Color.BLACK);
            txtDetailItem.setText(getDetailItem(txtKodeItem.getText()));
            if(!txtDetailItem.getText().trim().equals("")){
                txtHargaPerItem.setText(String.valueOf(getHarga()));
                txtJumlah.requestFocus();
            }
        }*/
    }//GEN-LAST:event_txtKodeItemFocusLost

    private void txtDetailItemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDetailItemFocusGained
        // TODO add your handling code here:
        if(txtKodeItem.getText().trim().equals("")){
            txtKodeItem.requestFocus();
            JOptionPane.showMessageDialog(null, "Kode item masih kosong detail", "informasi", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else{
            txtKodeItem.setForeground(Color.BLACK);
            txtDetailItem.setText(getDetailItem(txtKodeItem.getText()));
            if(!txtDetailItem.getText().trim().equals("")){
                txtJumlah.requestFocus();
            }
        }
    }//GEN-LAST:event_txtDetailItemFocusGained

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed
        // TODO add your handling code here:
        if(!txtJumlah.getText().trim().equals("")){
            if(!txtJumlah.getText().matches("[0-9]*")){
                txtJumlah.setText("");
                JOptionPane.showMessageDialog(null, "Jumlah harus angka");
                txtJumlah.requestFocus();
                return;
            }
            if(Integer.parseInt(txtJumlah.getText())<=0){
                JOptionPane.showMessageDialog(null, "Jumlah tidak boleh kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
                txtJumlah.setText("");
                txtJumlah.requestFocus();
            }
            if(Integer.parseInt(txtJumlah.getText()) > (getStokBarang(txtKodeItem.getText()) - getJumlahProsesKasir(txtKodeItem.getText()))){
                JOptionPane.showMessageDialog(null, "Stok tidak mencukupi\nTotal stok untuk barangkecil "+txtDetailItem.getText()+" adalah = "+(getStokBarang(txtKodeItem.getText()) - getJumlahProsesKasir(txtKodeItem.getText())), "Peringatan", JOptionPane.WARNING_MESSAGE);
                txtJumlah.setText("");
                txtJumlah.requestFocus();
            }
            else{
                btGo.doClick();
            }
        }
    }//GEN-LAST:event_txtJumlahActionPerformed

    private void txtJumlahFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtJumlahFocusGained
        // TODO add your handling code here:
        if(txtKodeItem.getText().trim().equals("")){
            txtKodeItem.requestFocus();
            JOptionPane.showMessageDialog(null, "kode item masih kosong");
            return;
        }
    }//GEN-LAST:event_txtJumlahFocusGained

    private void txtJumlahFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtJumlahFocusLost
        // TODO add your handling code here:
        /*if(txtJumlah.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "jumlah item masih kosong jumlah focus lost");
            txtJumlah.requestFocus();
            return;
        } */
    }//GEN-LAST:event_txtJumlahFocusLost
    
    public void resetAfterInsert(){
        txtKodeItem.setText("");
        txtDetailItem.setText("");
        txtJumlah.setText("");
        txtJumlah.setText("");
        txtKodeItem.requestFocus();
    }
     private int getJumlahBarangBeli(){
        koneksi();
        int hasil=0;
        Statement s = null;
        try{
            s=connection.createStatement();
            ResultSet resultSet=s.executeQuery("SELECT jml from prosestransaksibb");
            while (resultSet.next()) {
                hasil+=resultSet.getInt("jml");
            }            
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam mendapatkan jumlah total barangkecil yang dibeli");
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return hasil;
    }   
     
    private void btGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGoActionPerformed
        // TODO add your handling code here:
        if(txtKodeItem.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "kode item masih kosong");
            txtKodeItem.requestFocus();
            return;
        }else if(txtJumlah.getText().equals("")){
            JOptionPane.showMessageDialog(null, "jumlah item masih kosong");
            txtJumlah.requestFocus();
            return;
        }
        controller.insertPenjualan(this);
        resetAfterInsert();
    }//GEN-LAST:event_btGoActionPerformed

    public int getHargaGrosir(){
        String grosir = "SELECT grosir from barangbesar WHERE idbarang = '"+txtKodeItem.getText()+"'";
        int biayaEkspedisi = 0;
        koneksi();
        try{
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(grosir);
            if(rs.next()){
                biayaEkspedisi = rs.getInt("grosir");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam mendapatkan biaya ekspedisi karena "+exception);
        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }

        return biayaEkspedisi;
    }    

    private void executeUpdate(int angkaUpdate, String kodeProses) {
        koneksi();
        Statement statement = null;
        try{
            statement=connection.createStatement();
            statement.execute("UPDATE barangbesar SET stok='"+angkaUpdate+"' WHERE idbarang='"+kodeProses+"'");
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error update barangkecil karena "+exception);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }    
    
private void updateStokSetelahPembayaran() throws SQLException{
    koneksi();
    try(Statement statement = connection.createStatement()) {
        ResultSet rs=statement.executeQuery("SELECT kode, jml from prosestransaksibb");
        while(rs.next()){
            String kodeProses=rs.getString("kode");				
            int jumlah=rs.getInt("jml");
            int stok=getStokBarang(kodeProses);
            int angkaUpdate=stok-jumlah;
            executeUpdate(angkaUpdate, kodeProses);
        }            
    }catch(SQLException exception){
        JOptionPane.showMessageDialog(null, "Error update stok karena = "+exception);
    }
    }           
    
/*    private void isiDataTransaksi(){
        koneksi();
        PreparedStatement ps = null;
        try{
            ps =connection.prepareStatement("insert into transaksipenjualanbarangbesar (idtransaksi, user, Tanggal, jam, total, pembayaran, sisa, profit, penjual, bonuslangsung, bonuskumulatif) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, txtNoTransaksi.getText());
            ps.setString(2, txtUser.getText());
            String tanggal = formatTanggal2.format(date);
            ps.setString(3, tanggal);
            ps.setString(4, txtJam.getText());
            //ps.setInt(6, Integer.valueOf(txtPembayaran.getText()));
            ps.setInt(7, sisa);
            ps.setString(9, cmbPenjual2.getSelectedItem().toString());
            ps.executeUpdate();
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Gagal mengisi tabel transaksi karena = "+exception);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }*/

    
    private int getModal(String kode){
        koneksi();
        int modal = 0;
        Statement s = null;
        try{
            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select modal from barangbesar where idbarang = '"+kode+"'");
            if(rs.next()){
                modal = rs.getInt("modal");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam mengambil harga modal karena "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return modal;        
    }
    
    private int getEceran(String kode){
        koneksi();
        int modal = 0;
        Statement s = null;
        try{
            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select eceran from barangbesar where idbarang = '"+kode+"'");
            if(rs.next()){
                modal = rs.getInt("eceran");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam mengambil harga eceran karena "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return modal;        
    }    
    
 private int getGrosir(String kode){
        koneksi();
        int modal = 0;
        Statement s = null;
        try{
            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select grosir from barangbesar where idbarang = '"+kode+"'");
            if(rs.next()){
                modal = rs.getInt("grosir");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam mengambil harga grosir karena "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return modal;        
    }    
    
    private void isiDataDetailTransaksi(){
        koneksi();
        tabelTransaksi.selectAll();
        int row=tabelTransaksi.getSelectedRowCount();        
            PreparedStatement ps = null;
        for(int a=0; a<row; a++){
            try{
                ProsesKasir pk = modelPenjualan.get(a);
                ps=connection.prepareStatement("insert into detailtransaksitransferbb (iddetailtransaksi, user, Tanggal, jam, kode, namaBarang, jumlah) VALUES (?, ?, ?, ?, ?, ?, ?)");
                //ps.setString(1, txtNoTransaksi.getText());
                ps.setString(1, txtNoTransaksi.getText());
                ps.setString(2, txtUser.getText());
                String tanggal = formatTanggal2.format(date);
                ps.setString(3, tanggal);
                ps.setString(4, txtJam.getText());
                ps.setString(5, pk.getKode());//kode
                ps.setString(6, pk.getNama());//nama
                ps.setString(7, String.valueOf(pk.getJml()));//jumlah               
                ps.executeUpdate();
            }catch(SQLException exception){
                JOptionPane.showMessageDialog(null, "Gagal mengisi tabel detail transaksi karena = "+exception);
            }finally{
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
//                if (connection != null) {
//                    try {
//                        connection.close();
//                    } catch (SQLException ex) {
//                        Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//            }
            }         
        }
    }

    private String getTipe(String kode){
        koneksi();
        String result="";
        try{
            Statement s = connection.createStatement();
            ResultSet rs=s.executeQuery("select tipe from barangbesar where idbarang = '"+kode+"'");
            if(rs.next()){
                result=rs.getString("tipe");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam mendapatkan tipe barangkecil karena "+exception, "Peringatan", JOptionPane.ERROR_MESSAGE);
        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return result;
    }
    
    private String getMerek(String kode){
        koneksi();
        String result="";
        try{
            Statement s = connection.createStatement();
            ResultSet rs=s.executeQuery("select merek from barangbesar where idbarang = '"+kode+"'");
            if(rs.next()){
                result=rs.getString("MerekDTO");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam mendapatkan MerekDTO barangkecil karena "+exception, "Peringatan", JOptionPane.ERROR_MESSAGE);
        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return result;
    }
    
    private String getSupplier(String kode){
        koneksi();
        String result="";
        try{
            Statement s = connection.createStatement();
            ResultSet rs=s.executeQuery("select supplier from barangbesar where idbarang = '"+kode+"'");
            if(rs.next()){
                result=rs.getString("supplier");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam mendapatkan supplier barangkecil karena "+exception, "Peringatan", JOptionPane.ERROR_MESSAGE);
        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return result;
    }    
    
    private String getGaransi(String kode){
        koneksi();
        String result="";
        try{
            Statement s = connection.createStatement();
            ResultSet rs=s.executeQuery("select garansi from barangbesar where idbarang = '"+kode+"'");
            if(rs.next()){
                result=rs.getString("garansi");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam mendapatkan garansi barangkecil karena "+exception, "Peringatan", JOptionPane.ERROR_MESSAGE);
        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return result;
    }        
    
    private int getLamaGaransi(String kode){
        koneksi();
        int result=0;
        try{
            Statement s = connection.createStatement();
            ResultSet rs=s.executeQuery("select lamagaransi from barangbesar where idbarang = '"+kode+"'");
            if(rs.next()){
                result=rs.getInt("lamagaransi");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam mendapatkan lamagaransi barangkecil karena "+exception, "Peringatan", JOptionPane.ERROR_MESSAGE);
        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return result;
    } 
    
    private Blob getGambar(String kode){
        koneksi();
        Blob result=null;
        try{
            Statement s = connection.createStatement();
            ResultSet rs=s.executeQuery("select gambar from barangbesar where idbarang = '"+kode+"'");
            if(rs.next()){
                result=rs.getBlob("gambar");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam mendapatkan supplier barangkecil karena "+exception, "Peringatan", JOptionPane.ERROR_MESSAGE);
        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return result;
    }    
    
    private int getStokToko(String kode){
        koneksi();
        int result=0;
        try{
            Statement s = connection.createStatement();
            ResultSet rs=s.executeQuery("select stok from barangtoko where idbarang = '"+kode+"'");
            if(rs.next()){
                result=rs.getInt("stok");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam mendapatkan stok barangkecil toko karena "+exception, "Peringatan", JOptionPane.ERROR_MESSAGE);
        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return result;
    }    
    
    private void isiDataBarangToko(){
        koneksi();
        tabelTransaksi.selectAll();
        int row=tabelTransaksi.getSelectedRowCount();        
        PreparedStatement ps = null;
        for(int a=0; a<row; a++){
            ResultSet rs = null;
            ProsesKasir pk = modelPenjualan.get(a);
            String kode = pk.getKode();
            try{                
                Statement s=connection.createStatement();                                                
                rs=s.executeQuery("select idbarang from barangtoko where idbarang = '"+kode+"'");
            }catch(SQLException exception){
                JOptionPane.showMessageDialog(null, "Gagal mendapatkan data barangkecil toko karena = "+exception);
            }finally{
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
                
            }            
            try{                
            //insert process
                if(rs.next()){
                    //update
                    int stokToko = getStokToko(kode);
                    int stokUpdate = stokToko + pk.getJml();
                    PreparedStatement ps1=connection.prepareStatement("update barangtoko set stok="+stokUpdate+" where idbarang='"+kode+"'");
                    ps1.executeUpdate();
                }else{
                    ps=connection.prepareStatement("insert into barangtoko (idbarang, idbarcode, namaBarang, tipe, merek, modal, grosir, eceran, stok, stok_minimum, supplier, keterangan, gambar, garansi, lamagaransi) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    String tipe = getTipe(kode);
                    String merek = getMerek(kode);                    
                    int modal = getModal(kode);
                    int grosir = getGrosir(kode);
                    int ecerang = getEceran(kode);
                    String supplier = getSupplier(kode);
                    Blob gambar = getGambar(kode);
                    String garansi = getGaransi(kode);
                    int lamagaransi = getLamaGaransi(kode);
                    
                    ps.setString(1, kode);
                    ps.setString(2, "");
                    ps.setString(3, pk.getNama());
                    ps.setString(4, tipe);
                    ps.setString(5, merek);
                    ps.setInt(6, modal);
                    ps.setInt(7, grosir);
                    ps.setInt(8, ecerang);
                    ps.setInt(9, pk.getJml());
                    ps.setInt(10, 1);
                    ps.setString(11, supplier);
                    ps.setString(12, "");
                    ps.setBlob(13, gambar);
                    ps.setString(14, garansi);
                    ps.setInt(15, lamagaransi);
                    ps.executeUpdate();
                }            
            }catch(SQLException exception){
                JOptionPane.showMessageDialog(null, "Gagal insert barangkecil toko karena = "+exception);
            }finally{
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
//                if (connection != null) {
//                    try {
//                        connection.close();
//                    } catch (SQLException ex) {
//                        Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//            }
            }            
        }
    }    
    
    int sisa = 0;
    private void tambahPenjualan(Boolean tambah){
        controller.tambahPenjualan(this);
        txtNoTransaksi.setText(loadId());
        setEnableTransaksi(tambah);
        txtKodeItem.requestFocus();
        txtTotal.setText("");
        txtKodeItem.setText("");
        txtDetailItem.setText("");
    }
    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        // TODO add your handling code here:        
        tambahPenjualan(true);
    }//GEN-LAST:event_btTambahActionPerformed

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        // TODO add your handling code here:
        controller.truncatePenjualan(this);
        resetAfterInsert();
    }//GEN-LAST:event_btCancelActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        // TODO add your handling code here:
        controller.deletePenjualan(this);
        resetAfterInsert();
    }//GEN-LAST:event_btHapusActionPerformed

    private void btCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCetakActionPerformed
        // TODO add your handling code here:
        ReportParam rp=new ReportParam();
        rp.buildReportStruk("struktransfer", Integer.valueOf(txtNoTransaksi.getText()));
    }//GEN-LAST:event_btCetakActionPerformed

    private void txtKataKunci1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKataKunci1ActionPerformed
        // TODO add your handling code here:
        btCari1.doClick();
    }//GEN-LAST:event_txtKataKunci1ActionPerformed

    private void btCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCari1ActionPerformed
        try {
            // TODO add your handling code here:
            controllerBarang.cari(this, this);
        } catch (SQLException | BarangException ex) {
            Logger.getLogger(ViewSearchBarangBesar2.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setSize(getWidth(), getHeight());
        tabelBarang1.requestFocus();
    }//GEN-LAST:event_btCari1ActionPerformed

    private void btSetCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSetCariActionPerformed
        // TODO add your handling code here:
        txtKataKunci1.selectAll();
        txtKataKunci1.requestFocus();
    }//GEN-LAST:event_btSetCariActionPerformed

    private void cmbCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCari1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCari1ActionPerformed

    private void cmbUrut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUrut1ActionPerformed
        try {
            controllerBarang.sort(this);
        } catch (SQLException | BarangException ex) {
            Logger.getLogger(ViewSearchBarangBesar2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmbUrut1ActionPerformed

    private void tabelBarang1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarang1MouseClicked
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null, "isi tabel = "+tabelBarang1.getValueAt(tabelBarang1.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_tabelBarang1MouseClicked

    private void tabelBarang1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarang1MousePressed
        // TODO add your handling code here:
        if(evt.isPopupTrigger()){
            popUpTabel.show(evt.getComponent(), evt.getX(), evt.getY());
        }//JOptionPane.showMessageDialog(null, "isi tabel = "+tabelBarang1.getValueAt(tabelBarang1.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_tabelBarang1MousePressed

    private void tabelBarang1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarang1MouseReleased
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null, "isi tabel = "+tabelBarang1.getValueAt(tabelBarang1.getSelectedRow(), 0).toString());
        if(evt.isPopupTrigger()){
            popUpTabel.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tabelBarang1MouseReleased

    public void entryKodeItem(){
         if(tabelBarang1.getSelectedRowCount()==1){
            String data=tabelBarang1.getValueAt(tabelBarang1.getSelectedRow(), 0).toString();
            txtKodeItem.setText(data);
            txtDetailItem.requestFocus();
            DialogCariBarang.dispose();            
        }
    }
    private void pilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihActionPerformed
        // TODO add your handling code here:
        if(tabelBarang1.getSelectedRowCount()==1){
            String data=tabelBarang1.getValueAt(tabelBarang1.getSelectedRow(), 0).toString();
            txtKodeItem.setText(data);
            txtDetailItem.requestFocus();
            DialogCariBarang.dispose();            
        }
    }//GEN-LAST:event_pilihActionPerformed

    private void tabelBarang1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelBarang1KeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tabelBarang1KeyPressed

    private void tabelBarang1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelBarang1KeyTyped
        // TODO add your handling code here:        
    }//GEN-LAST:event_tabelBarang1KeyTyped
    
    private void btTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTransferActionPerformed
        if(JOptionPane.showConfirmDialog(null, "Apakah anda yaking transaksi ini akan diproses ?") == JOptionPane.YES_OPTION){
            tabelTransaksi.selectAll();
            int total = tabelTransaksi.getSelectedRowCount();
                if(total>=1){
                try {
                    // TODO add your handling code here:
                    updateStokSetelahPembayaran();
                    tabelTransaksi.setEnabled(false);
                    //isiDataTransaksi();
                    isiDataDetailTransaksi();
                    isiDataBarangToko();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFTransferBB.class.getName()).log(Level.SEVERE, null, ex);
                }
                setEnableTransaksi(false);            
            }            
        }
    }//GEN-LAST:event_btTransferActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogCariBarang;
    private javax.swing.JDialog DialogPreview;
    private javax.swing.JPanel Kanan;
    private javax.swing.JPanel atas;
    private javax.swing.JPanel atas2;
    private javax.swing.JPanel bawah;
    private javax.swing.JPanel bawah2;
    private javax.swing.JPanel below;
    private javax.swing.JPanel bottom;
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btCari1;
    private javax.swing.JButton btCetak;
    private javax.swing.JButton btGo;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btSetCari;
    private javax.swing.JButton btTambah;
    private javax.swing.JButton btTransfer;
    private javax.swing.JButton btViewBarang;
    private javax.swing.JPanel centerBottom;
    private javax.swing.JComboBox cmbCari1;
    private javax.swing.JComboBox cmbUrut1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel kanan1;
    private javax.swing.JPanel kiri;
    private javax.swing.JPanel kiri2;
    private com.wissensalt.sinarelektronik.swinglib.labeling labeling7;
    private com.wissensalt.sinarelektronik.swinglib.labeling labeling8;
    private com.wissensalt.sinarelektronik.swinglib.labeling labeling9;
    private javax.swing.JLabel lblPreview;
    private javax.swing.JPanel left;
    private javax.swing.JPanel middle;
    private javax.swing.JPanel middle2;
    private com.wissensalt.sinarelektronik.swinglib.panelGradUser panelBackgroundUp1;
    private com.wissensalt.sinarelektronik.swinglib.panelGradUser panelGradUser4;
    private javax.swing.JMenuItem pilih;
    private javax.swing.JPopupMenu popUpTabel;
    private javax.swing.JTable tabelBarang1;
    private javax.swing.JTable tabelTransaksi;
    private javax.swing.JPanel tengah2;
    private javax.swing.JTextField txtDetailItem;
    private javax.swing.JTextField txtJam;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKataKunci1;
    private com.wissensalt.sinarelektronik.swinglib.AutoComplete.TextFieldAutoComplete txtKodeItem;
    private javax.swing.JTextField txtNoTransaksi;
    private javax.swing.JTextField txtTanggal;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtUser;
    private javax.swing.JPanel up;
    private javax.swing.JPanel up2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onChange(PenjualanModel penjualan) {
        txtKodeItem.setText(penjualan.getKode());
        txtDetailItem.setText(penjualan.getNama());
        txtJumlah.setText("");
    }

    @Override
    public void onInsert(ProsesKasir prosesKasir) {
        modelPenjualan.add(prosesKasir);
    }

    @Override
    public void onDelete() {
        int index=tabelTransaksi.getSelectedRow();
        modelPenjualan.remove(index);
    }

    @Override
    public void onTruncate() {
        tabelTransaksi.selectAll();
        int b= tabelTransaksi.getSelectedRowCount();
        int a=0;
        while (b>0) {            
            modelPenjualan.remove(a);
            tabelTransaksi.selectAll();
            b= tabelTransaksi.getSelectedRowCount();
        }
    }    

    @Override
    public void onChange(BarangBesarModel model) {
        txtKataKunci1.setText(modelBarang.getCari());
    }

    @Override
    public void onInsert(barang barang) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onUpdate(barang barang) {
        throw new UnsupportedOperationException("Not supported yet.");
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

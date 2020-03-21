/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.penjualan.barangbesar.view;

import com.wissensalt.sinarelektronik.dto.BarangBesarDTO;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.controller.BarangBesarController;
import com.wissensalt.sinarelektronik.masterdata.barangtoko.controller.BarangTokoController;
import com.wissensalt.sinarelektronik.masterdata.barangtoko.model.TabelModelBarangToko;
import com.wissensalt.sinarelektronik.masterdata.barangtoko.model.event.BarangTokoListener;
import com.wissensalt.sinarelektronik.dao.BarangTokoDAO;
import com.wissensalt.sinarelektronik.model.BarangBesarModel;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.model.TabelModelBarangBesar;
import com.wissensalt.sinarelektronik.util.FormatRupiah;
import com.wissensalt.sinarelektronik.util.InputHarusAngka;
import com.wissensalt.sinarelektronik.penjualan.barangbesar.controller.penjualanController;
import com.wissensalt.sinarelektronik.penjualan.barangbesar.entity.ProsesKasir;
import com.wissensalt.sinarelektronik.penjualan.barangbesar.model.PenjualanModel;
import com.wissensalt.sinarelektronik.penjualan.barangbesar.model.TabelModelPenjualan;
import com.wissensalt.sinarelektronik.penjualan.barangbesar.model.event.penjualanListener;
import com.wissensalt.sinarelektronik.config.ActiveUser;
import com.wissensalt.sinarelektronik.config.HostName;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.database.barangDatabase;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.error.BarangException;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.model.event.BarangBesarListener;
import com.wissensalt.sinarelektronik.dao.BarangBesarDAO;
import com.wissensalt.sinarelektronik.masterdata.report.core.ReportParam;
import com.wissensalt.sinarelektronik.swinglib.AutoComplete.DefaultModelAutoComplete;
import com.wissensalt.sinarelektronik.swinglib.AutoComplete.TextFieldAutoComplete;
import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fauzi
 */
public class JIFKasirView2 extends javax.swing.JInternalFrame implements penjualanListener, BarangBesarListener, BarangTokoListener {

    /**
     * Creates new form JIFKasirView2
     */
    InputHarusAngka iha = new InputHarusAngka();
    
    TabelModelPenjualan modelPenjualan;
    
    private PenjualanModel model;
    
    private penjualanController controller;
    
    private TabelModelBarangBesar tabelmodelBarangBesar;
    
    private BarangBesarController controllerBarang;
    
    private BarangBesarModel modelBarang;
    
    private TabelModelBarangToko TabelmodelBarangToko;
    
    private BarangTokoController controllerBarangToko;
    
    private com.wissensalt.sinarelektronik.masterdata.barangtoko.model.barangModel modelBarangToko;
    
    private FormatRupiah formatter=new FormatRupiah();
    
    private String ip;
    
    private String sumberBarang = "";

    public JIFKasirView2() {

        tabelmodelBarangBesar = new TabelModelBarangBesar();
        
        modelBarang=  new BarangBesarModel();
        modelBarang.setListener(this);        
        
        TabelmodelBarangToko = new TabelModelBarangToko();
        modelBarangToko = new com.wissensalt.sinarelektronik.masterdata.barangtoko.model.barangModel();
        modelBarangToko.setListener(this);
        controllerBarangToko = new BarangTokoController();
        controllerBarangToko.setModel(modelBarangToko);        
        
        controllerBarang = new BarangBesarController();
        controllerBarang.setModel(modelBarang);
               
        modelPenjualan=new TabelModelPenjualan();
    
        model=new PenjualanModel();
        model.setListener(this);
        
        controller=new penjualanController();
        controller.setModel(model);
        
        initComponents();                                
        tabelTransaksi.setModel(modelPenjualan);

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
                      if(e.getKeyCode() == KeyEvent.VK_F5){
                          inputPembayaran();
                      }else if(e.getKeyCode() == KeyEvent.VK_SPACE){
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
        cmbModePenjualan.setSelectedIndex(1); 
        loadPenjual();
        //koneksi();
        loadView();        
        setEnableTransaksi(false);            
    }

    public JLabel getLblStokBarang() {
        return lblStokBarang;
    }

    public void setLblStokBarang(JLabel lblStokBarang) {
        this.lblStokBarang = lblStokBarang;
    }
            
    int bonuslangsung;
    
    int bonuskumulatif;

    public JLabel getLblPenjual() {
        return lblPenjual;
    }

    public void setLblPenjual(JLabel lblPenjual) {
        this.lblPenjual = lblPenjual;
    }
    
    
    public void loadPenjual(){
        koneksi();
        try {
            Statement s = connection.createStatement();
            ResultSet rs=s.executeQuery("select nama from penjualbb");
            while(rs.next()){
                cmbPenjual2.addItem(rs.getString("nama"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    public int getBonuslangsung() {
        return bonuslangsung;
    }

    public void setBonuslangsung(int bonuslangsung) {
        this.bonuslangsung = bonuslangsung;
    }

    public int getBonuskumulatif() {
        return bonuskumulatif;
    }

    public void setBonuskumulatif(int bonuskumulatif) {
        this.bonuskumulatif = bonuskumulatif;
    }

    public JComboBox getCmbPenjual2() {
        return cmbPenjual2;
    }

    public void setCmbPenjual2(JComboBox cmbPenjual2) {
        this.cmbPenjual2 = cmbPenjual2;
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

    public JComboBox getCmbModePenjualan() {
        return cmbModePenjualan;
    }

    public void setCmbModePenjualan(JComboBox cmbModePenjualan) {
        this.cmbModePenjualan = cmbModePenjualan;
    }

    public JTextField getTxtHargaPerItem() {
        return txtHargaPerItem;
    }

    public void setTxtHargaPerItem(JTextField txtHargaPerItem) {
        this.txtHargaPerItem = txtHargaPerItem;
    }

    public JTextField getTxtJam() {
        return txtJam;
    }

    public void setTxtJam(JTextField txtJam) {
        this.txtJam = txtJam;
    }

    public JTextField getTxtJumlahBarangBawah() {
        return txtJumlahBarangBawah;
    }

    public void setTxtJumlahBarangBawah(JTextField txtJumlahBarangBawah) {
        this.txtJumlahBarangBawah = txtJumlahBarangBawah;
    }


    public JTextField getTxtNoTransaksi() {
        return txtNoTransaksi;
    }

    public void setTxtNoTransaksi(JTextField txtNoTransaksi) {
        this.txtNoTransaksi = txtNoTransaksi;
    }

    public JTextField getTxtPembayaran() {
        return txtPembayaran;
    }

    public void setTxtPembayaran(JTextField txtPembayaran) {
        this.txtPembayaran = txtPembayaran;
    }

    public JTextField getTxtSisa() {
        return txtSisa;
    }

    public void setTxtSisa(JTextField txtSisa) {
        this.txtSisa = txtSisa;
    }

    public JTextField getTxtSubTotal() {
        return txtSubTotal;
    }

    public void setTxtSubTotal(JTextField txtSubTotal) {
        this.txtSubTotal = txtSubTotal;
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
    
    
    public JIFKasirView2(String ip) {
        initComponents();
        this.ip = ip;
    }

    public void loadDatabaseCariBarang() throws SQLException, BarangException{
        BarangBesarDAO dao = barangDatabase.getBarangDao();
        tabelmodelBarangBesar.setList(dao.selectAllBarang());
    }    
    
    public void loadDatabaseCariBarangToko() throws com.wissensalt.sinarelektronik.masterdata.barangtoko.error.BarangException, SQLException {
        BarangTokoDAO dao = com.wissensalt.sinarelektronik.masterdata.barangtoko.database.barangDatabase.getBarangDao();
        TabelmodelBarangToko.setList(dao.selectAllBarang());
    }    
    
    public void setEnableTransaksi(boolean enable){
        txtKodeItem.setEnabled(enable);
        txtJumlah.setEnabled(enable);
        txtPotongan.setEnabled(enable);
        txtTambahan.setEnabled(enable);
        txtDetailItem.setEnabled(enable);
        txtHargaPerItem.setEnabled(enable);
        txtHarga.setEnabled(enable);        
        btViewBarang.setEnabled(enable);
        btGo.setEnabled(enable);
    }
    
    public int getStokBarang(String text){
        koneksi();
        int stok=0;
        Statement s = null;
        try{
            s=connection.createStatement();
            ResultSet rs=s.executeQuery("SELECT stok from "+sumberBarang+" WHERE idbarang='"+text+"'");
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
                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
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
            ResultSet rs=statement.executeQuery("SELECT jml from proseskasir2 where kode='"+kode+"'");
            while(rs.next()){
                hasil += rs.getInt("jml");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "tidak bisa mengambil jumlah proseskasir real time karena ="+exception);
        }finally{
//            if(statement!=null){
//                try {
//                    statement.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return hasil;
    }
    
    
    public JTextField getTxtTambahan() {
        return txtTambahan;
    }

    public void setTxtTambahan(JTextField txtTambahan) {
        this.txtTambahan = txtTambahan;
    }   
    
    public int profit;
    
    public int getHargaModal(){
        koneksi();
        String grosir = "SELECT modal from "+sumberBarang+" WHERE idbarang = '"+txtKodeItem.getText()+"'";
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
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }

        return hargaModal;
    }
    
    
    public int getProfit() {
        profit = getSubTotal() - (getHargaModal()*Integer.parseInt(txtJumlah.getText()));
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
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

    public JTextField getTxtHarga() {
        return txtHarga;
    }

    public void setTxtHarga(JTextField txtHarga) {
        this.txtHarga = txtHarga;
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

    public JTextField getTxtPotongan() {
        return txtPotongan;
    }

    public void setTxtPotongan(JTextField txtPotongan) {
        this.txtPotongan = txtPotongan;
    }
    
    public int getSubTotal(){
        int harga = Integer.parseInt(txtHarga.getText());
        int potongan = Integer.parseInt(txtPotongan.getText())*Integer.parseInt(txtJumlah.getText());
        int tambahan  = Integer.parseInt(txtTambahan.getText())*Integer.parseInt(txtJumlah.getText());
        int subtotal = harga-potongan+tambahan;        
        return subtotal;
    }

    public JTable getTabelTransaksi() {
        return tabelTransaksi;
    }
        
    
    private void inputPembayaran(){
        txtTotal.setText(txtSubTotal.getText());
        txtPembayaran.requestFocus();
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
            ResultSet rs1=statement.executeQuery("select count(idbarang) total from "+sumberBarang);
            if(rs1.next()){
                data=new String[rs1.getInt("total")];
            }
            ResultSet rs=statement.executeQuery("select idbarang from "+sumberBarang);
            int inc=0;
            while (rs.next()) {                
                String idbarang=rs.getString("idbarang");
                data[inc] = idbarang;
                inc++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
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
            ResultSet rs=statement.executeQuery("SELECT max(idtransaksi)+1 TOTAL FROM transaksipenjualanbarangbesar");
            if(rs.next()){
                id=rs.getString("TOTAL");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Tidak bisa mengambil id transaksi karena = "+e);
        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://"+ip1.getIpServer()+"/sinarelektronik?;", "root", "P@ssw0rd");
        } catch (SQLException ex) {
            Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
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
        DialogPenjual = new javax.swing.JDialog();
        jPanel26 = new javax.swing.JPanel();
        cmbPenjual2 = new javax.swing.JComboBox();
        jPanel27 = new javax.swing.JPanel();
        btOkPenjual = new javax.swing.JButton();
        btCancelPenjual = new javax.swing.JButton();
        DialogStokBarang = new javax.swing.JDialog();
        jPanel28 = new javax.swing.JPanel();
        cmbStokBarang = new javax.swing.JComboBox();
        jPanel29 = new javax.swing.JPanel();
        btOkPenjual1 = new javax.swing.JButton();
        btCancelPenjual1 = new javax.swing.JButton();
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
        tengah = new javax.swing.JPanel();
        panelSisa = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        cmbModePenjualan = new javax.swing.JComboBox();
        lblStokBarang = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        txtSisa = new javax.swing.JTextField();
        bawah = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblPenjual = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        btViewBarang = new javax.swing.JButton();
        txtKodeItem = new com.wissensalt.sinarelektronik.swinglib.AutoComplete.TextFieldAutoComplete();
        jPanel8 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtDetailItem = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtHargaPerItem = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        btGo = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        txtPotongan = new javax.swing.JTextField();
        txtTambahan = new javax.swing.JTextField();
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
        right = new javax.swing.JPanel();
        satu = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtJumlahBarangBawah = new javax.swing.JTextField();
        dua = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        empat = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtPembayaran = new javax.swing.JTextField();
        below = new javax.swing.JPanel();
        btTambah = new javax.swing.JButton();
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

        DialogPenjual.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DialogPenjual.setTitle("Pilih Penjual");

        jPanel26.setLayout(new java.awt.BorderLayout());

        cmbPenjual2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPenjual2ActionPerformed(evt);
            }
        });
        jPanel26.add(cmbPenjual2, java.awt.BorderLayout.CENTER);

        DialogPenjual.getContentPane().add(jPanel26, java.awt.BorderLayout.CENTER);

        jPanel27.setLayout(new java.awt.GridLayout(1, 2));

        btOkPenjual.setText("Ok");
        btOkPenjual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkPenjualActionPerformed(evt);
            }
        });
        jPanel27.add(btOkPenjual);

        btCancelPenjual.setText("Cancel");
        btCancelPenjual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelPenjualActionPerformed(evt);
            }
        });
        jPanel27.add(btCancelPenjual);

        DialogPenjual.getContentPane().add(jPanel27, java.awt.BorderLayout.PAGE_END);

        DialogStokBarang.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DialogStokBarang.setTitle("Pilih Posisi Barang Terjual");
        DialogStokBarang.setMinimumSize(new java.awt.Dimension(300, 120));
        DialogStokBarang.setModal(true);
        DialogStokBarang.setResizable(false);

        jPanel28.setLayout(new java.awt.BorderLayout());

        cmbStokBarang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Toko", "Gudang" }));
        cmbStokBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStokBarangActionPerformed(evt);
            }
        });
        jPanel28.add(cmbStokBarang, java.awt.BorderLayout.CENTER);

        DialogStokBarang.getContentPane().add(jPanel28, java.awt.BorderLayout.CENTER);

        jPanel29.setLayout(new java.awt.GridLayout(1, 2));

        btOkPenjual1.setText("Ok");
        btOkPenjual1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkPenjual1ActionPerformed(evt);
            }
        });
        jPanel29.add(btOkPenjual1);

        btCancelPenjual1.setText("Cancel");
        btCancelPenjual1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelPenjual1ActionPerformed(evt);
            }
        });
        jPanel29.add(btCancelPenjual1);

        DialogStokBarang.getContentPane().add(jPanel29, java.awt.BorderLayout.PAGE_END);

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Point Of Sale [POS] Barang Besar Sinar Elektronik");
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

        up.add(atas, java.awt.BorderLayout.PAGE_START);

        tengah.setPreferredSize(new java.awt.Dimension(500, 100));
        tengah.setLayout(new java.awt.BorderLayout());

        panelSisa.setMinimumSize(new java.awt.Dimension(193, 100));
        panelSisa.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.GridLayout(1, 2));

        jPanel18.setLayout(new java.awt.GridLayout(2, 0));

        jLabel22.setText("Mode             :");
        jPanel18.add(jLabel22);

        jLabel25.setText("Stok Barang :");
        jPanel18.add(jLabel25);

        jPanel6.add(jPanel18);

        jPanel19.setLayout(new java.awt.GridLayout(2, 0));

        cmbModePenjualan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "grosir", "eceran" }));
        cmbModePenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbModePenjualanActionPerformed(evt);
            }
        });
        jPanel19.add(cmbModePenjualan);

        lblStokBarang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblStokBarang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel19.add(lblStokBarang);

        jPanel6.add(jPanel19);

        panelSisa.add(jPanel6, java.awt.BorderLayout.WEST);

        jPanel15.setLayout(new java.awt.BorderLayout());

        jPanel16.setLayout(new java.awt.BorderLayout());

        jLabel5.setText("             Sisa   :");
        jPanel16.add(jLabel5, java.awt.BorderLayout.CENTER);

        jPanel15.add(jPanel16, java.awt.BorderLayout.WEST);

        jPanel17.setLayout(new java.awt.BorderLayout());

        txtSisa.setEditable(false);
        txtSisa.setBackground(new java.awt.Color(255, 255, 255));
        txtSisa.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jPanel17.add(txtSisa, java.awt.BorderLayout.CENTER);

        jPanel15.add(jPanel17, java.awt.BorderLayout.CENTER);

        panelSisa.add(jPanel15, java.awt.BorderLayout.CENTER);

        tengah.add(panelSisa, java.awt.BorderLayout.CENTER);

        up.add(tengah, java.awt.BorderLayout.CENTER);

        bawah.setMaximumSize(new java.awt.Dimension(32767, 85));
        bawah.setMinimumSize(new java.awt.Dimension(496, 85));
        bawah.setPreferredSize(new java.awt.Dimension(496, 85));
        bawah.setLayout(new java.awt.GridLayout(1, 4));

        jPanel7.setLayout(new java.awt.GridLayout(2, 1));

        jPanel11.setLayout(new java.awt.GridLayout(1, 2));

        jLabel6.setText("Penjual :");
        jPanel11.add(jLabel6);

        lblPenjual.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel11.add(lblPenjual);

        jPanel7.add(jPanel11);

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

        bawah.add(jPanel7);

        jPanel8.setLayout(new java.awt.GridLayout(2, 2));

        jLabel24.setText("Nama :");
        jPanel8.add(jLabel24);

        txtDetailItem.setEditable(false);
        txtDetailItem.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        txtDetailItem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDetailItemFocusGained(evt);
            }
        });
        jPanel8.add(txtDetailItem);

        jLabel14.setText("Harga/ Item:");
        jPanel8.add(jLabel14);

        txtHargaPerItem.setEditable(false);
        txtHargaPerItem.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtHargaPerItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaPerItemActionPerformed(evt);
            }
        });
        jPanel8.add(txtHargaPerItem);

        bawah.add(jPanel8);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel22.setLayout(new java.awt.GridLayout(2, 0));

        jPanel21.setLayout(new java.awt.BorderLayout());

        jLabel8.setText("Jumlah :");
        jPanel21.add(jLabel8, java.awt.BorderLayout.WEST);

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
        jPanel21.add(txtJumlah, java.awt.BorderLayout.CENTER);

        jPanel22.add(jPanel21);

        jPanel14.setLayout(new java.awt.BorderLayout());

        jLabel15.setText("Sub Harga :");
        jPanel14.add(jLabel15, java.awt.BorderLayout.WEST);

        txtHarga.setEditable(false);
        txtHarga.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaActionPerformed(evt);
            }
        });
        txtHarga.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHargaFocusGained(evt);
            }
        });
        jPanel14.add(txtHarga, java.awt.BorderLayout.CENTER);

        jPanel22.add(jPanel14);

        jPanel9.add(jPanel22, java.awt.BorderLayout.CENTER);

        jPanel23.setLayout(new java.awt.GridLayout(2, 0));

        jLabel11.setText("Potongan :");
        jPanel23.add(jLabel11);

        jLabel23.setText("Tambahan :");
        jPanel23.add(jLabel23);

        jPanel9.add(jPanel23, java.awt.BorderLayout.EAST);

        bawah.add(jPanel9);

        jPanel20.setLayout(new java.awt.BorderLayout());

        btGo.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        btGo.setText("Go");
        btGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGoActionPerformed(evt);
            }
        });
        jPanel20.add(btGo, java.awt.BorderLayout.EAST);

        jPanel24.setLayout(new java.awt.GridLayout(2, 0));

        txtPotongan.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtPotongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPotonganActionPerformed(evt);
            }
        });
        txtPotongan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPotonganFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPotonganFocusLost(evt);
            }
        });
        jPanel24.add(txtPotongan);

        txtTambahan.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtTambahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTambahanActionPerformed(evt);
            }
        });
        txtTambahan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTambahanFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTambahanFocusLost(evt);
            }
        });
        jPanel24.add(txtTambahan);

        jPanel20.add(jPanel24, java.awt.BorderLayout.CENTER);

        bawah.add(jPanel20);

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

        jLabel20.setText("Proses Transaksi : F5");
        jPanel25.add(jLabel20);

        jLabel21.setText("Cari barangkecil : Alt+F");
        jPanel25.add(jLabel21);

        left.add(jPanel25);

        centerBottom.add(left);

        right.setLayout(new java.awt.GridLayout(3, 0));

        satu.setLayout(new java.awt.BorderLayout());

        jLabel9.setText("Jumlah Barang  :");
        satu.add(jLabel9, java.awt.BorderLayout.WEST);

        txtJumlahBarangBawah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahBarangBawahActionPerformed(evt);
            }
        });
        satu.add(txtJumlahBarangBawah, java.awt.BorderLayout.CENTER);

        right.add(satu);

        dua.setLayout(new java.awt.BorderLayout());

        jLabel10.setText(" Sub Total         :");
        dua.add(jLabel10, java.awt.BorderLayout.WEST);

        txtSubTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubTotalActionPerformed(evt);
            }
        });
        dua.add(txtSubTotal, java.awt.BorderLayout.CENTER);

        right.add(dua);

        empat.setLayout(new java.awt.BorderLayout());

        jLabel13.setText("Pembayaran     :");
        empat.add(jLabel13, java.awt.BorderLayout.WEST);

        txtPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPembayaranActionPerformed(evt);
            }
        });
        txtPembayaran.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPembayaranFocusGained(evt);
            }
        });
        empat.add(txtPembayaran, java.awt.BorderLayout.CENTER);

        right.add(empat);

        centerBottom.add(right);

        bottom.add(centerBottom, java.awt.BorderLayout.CENTER);

        below.setLayout(new java.awt.GridLayout(1, 4));

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

    private void cmbModePenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbModePenjualanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbModePenjualanActionPerformed
    private void cariBarang(){
        txtKodeItem.setText("");
        txtDetailItem.setText("");
        txtHargaPerItem.setText("");     
        txtJumlah.setText("");
        txtPotongan.setText("");
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
            ResultSet rs=  s.executeQuery("SELECT namabarang from "+sumberBarang+" where idbarang = '"+namaItem+"'");
            if(rs.next()){
                hasil=rs.getString("namabarang");
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return hasil;
    }
    
    public String getHarga(){
        koneksi();
        int harga=0;
        String mode = cmbModePenjualan.getSelectedItem().toString();
        String queryEceran = "SELECT eceran as harga from "+sumberBarang+" WHERE idbarang='"+txtKodeItem.getText()+"'";
        String queryGrosir = "SELECT grosir as harga from "+sumberBarang+" WHERE idbarang='"+txtKodeItem.getText()+"'";
        ResultSet rs = null;
        Statement statement = null;
        try{
            statement=connection.createStatement();
            if(mode .equals("grosir")){
                rs=statement.executeQuery(queryGrosir);
            }else if(mode.equals("eceran")){
                rs=statement.executeQuery(queryEceran);
            }
            if(rs.next()){
                harga=rs.getInt("harga");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error pengambilan harga karena "+exception);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return String.valueOf(harga);
    }
    
    private void txtKodeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeItemActionPerformed
        // TODO add your handling code here:
        txtKodeItem.setForeground(Color.BLACK);
        txtDetailItem.setText(getDetailItem(txtKodeItem.getText()));
        if(!txtDetailItem.getText().trim().equals("")){
            txtHargaPerItem.setText(String.valueOf(getHarga()));
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
                txtHargaPerItem.setText(String.valueOf(getHarga()));
                txtJumlah.requestFocus();
            }
        }
    }//GEN-LAST:event_txtDetailItemFocusGained

    private void txtHargaPerItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaPerItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaPerItemActionPerformed

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
                JOptionPane.showMessageDialog(null, "Stok tidak mencukupi\nTotal stok untuk barangkecil "+txtDetailItem.getText()+" adalah = "+(getStokBarang(txtKodeItem.getText()) - getJumlahProsesKasir(txtKodeItem.getText())));
                txtJumlah.setText("");
                txtJumlah.requestFocus();
            }
            else{
                int harga=Integer.valueOf(getHarga())*Integer.valueOf(txtJumlah.getText());
                txtHarga.setText(String.valueOf(harga));
                txtPotongan.requestFocus();
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

    private void txtHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaActionPerformed

    private void txtHargaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHargaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaFocusGained
    
    public void resetAfterInsert(){
        txtKodeItem.setText("");
        txtDetailItem.setText("");
        txtHarga.setText("");
        txtHargaPerItem.setText("");
        txtJumlah.setText("");
        txtPotongan.setText("");
        txtTambahan.setText("");
        txtJumlah.setText("");
        txtKodeItem.requestFocus();
    }
     private int getJumlahBarangBeli(){
        koneksi();
        int hasil=0;
        Statement s = null;
        try{
            s=connection.createStatement();
            ResultSet resultSet=s.executeQuery("SELECT jml from proseskasir2");            
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
                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return hasil;
    }   
     
    private int getSubTotalBarangBeli(){
        koneksi();
        int hasil=0;
        Statement s = null;
        try{
            s=connection.createStatement();
            ResultSet resultSet=s.executeQuery("SELECT total from proseskasir2");            
            while (resultSet.next()) {
                hasil+=resultSet.getInt("total");
            }            
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam mendapatkan Subtotal barangkecil yang dibeli");
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return hasil;
    }
    
    public int [] getPatokanBonusLangsungBB(){
        koneksi();
        int []hasil=new int[2];
        try{
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("select patokanbb, bonuslangsungbb from setupbonus");
            if(rs.next()){
                hasil[0] = rs.getInt("patokanbb");
                hasil[1] = rs.getInt("bonuslangsungbb");
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return hasil;
    }
    
    public String [] getBarangBonus(){
        String [] barangBonus = null;
        try{
            koneksi();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("select count(kodebarang) as jumlah from barangbonuskaryawanbb");
            int jumlah = 0;
            if(rs.next()){
                barangBonus = new String[rs.getInt("jumlah")];
                jumlah = rs.getInt("jumlah");
            }
            
            Statement s2 = connection.createStatement();
            ResultSet rs2 = s2.executeQuery("select kodebarang from barangbonuskaryawanbb");
            int a=0;
            while(rs2.next()){
                barangBonus[a] = rs2.getString("kodebarang");
                a++;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error karena "+e);
        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return barangBonus;
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
        int modal = getModal(txtKodeItem.getText()) * Integer.parseInt(txtJumlah.getText());
        int [] patokanLangsung = getPatokanBonusLangsungBB();
        int patokan = modal+patokanLangsung[0];
        int jual = getSubTotal();            
        int profit = getProfit();
        
        String [] barangBonus = getBarangBonus();
        int panjang = barangBonus.length;        
        int a=0;
        int flag=0;
        while(a<panjang){
            if(txtKodeItem.getText().equals(barangBonus[a])){
                flag=1;
                break;
            }else{
                flag=0;
            }
            a++;
        }
        
        if(flag==1){            
            if(jual == patokan){
                bonuslangsung=patokanLangsung[1];
                bonuskumulatif = 0;
            }else if(jual>patokan){
                bonuslangsung=patokanLangsung[1];
                int tambahanKumulatif = jual - patokan;
                bonuskumulatif = tambahanKumulatif;
            }else if((jual>=modal) && (jual<patokan)){
                //masuk kumulatif
                bonuslangsung=0;
                bonuskumulatif = profit;
            }
        }else{
            bonuslangsung=0;
            bonuskumulatif = profit;
        }
        
        controller.insertPenjualan(this);
        resetAfterInsert();
        txtJumlahBarangBawah.setText(String.valueOf(getJumlahBarangBeli()));
        txtSubTotal.setText(formatter.FormatRupiah(String.valueOf(getSubTotalBarangBeli())));
    }//GEN-LAST:event_btGoActionPerformed

    public int getHargaGrosir(){
        koneksi();
        String grosir = "SELECT grosir from "+sumberBarang+" WHERE idbarang = '"+txtKodeItem.getText()+"'";
        int biayaEkspedisi = 0;
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
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }

        return biayaEkspedisi;
    }    
    
    private void txtPotonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPotonganActionPerformed
        // TODO add your handling code here:
        if(iha.cek(txtPotongan) == false){
            JOptionPane.showMessageDialog(null, "potongan harus angka");
            return;
        }else if(!txtPotongan.getText().trim().equals("")){
            int potongan=Integer.valueOf(txtPotongan.getText());
            int hargaGrosir = getHargaGrosir();
            int hargaJual = Integer.valueOf(txtHargaPerItem.getText());
            int hargaSelisih = hargaJual - hargaGrosir;
            if(potongan> hargaSelisih){
                JOptionPane.showMessageDialog(null, "Potongan tidak boleh lebih besar dari harga grosir ("+hargaSelisih+")", "Peringatan", JOptionPane.WARNING_MESSAGE);
                txtPotongan.setText("");
                txtPotongan.requestFocus();
            }/*else if(potongan>hargaGrosir){
                JOptionPane.showMessageDialog(null, "Potongan tidak boleh lebih besar dari biaya ekspedisi ('"+biayaEkspedisi+"')", "Peringatan", JOptionPane.WARNING_MESSAGE);
                txtPotongan.setText("");
                txtPotongan.requestFocus();
            }*/else{
                //btGo.doClick();
                txtTambahan.requestFocus();
            }
        }else{
            txtPotongan.setText("0");
        }

    }//GEN-LAST:event_txtPotonganActionPerformed

    private void txtPotonganFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPotonganFocusGained
        // TODO add your handling code here:
        if(txtJumlah.getText().trim().equals("")){
            txtJumlah.requestFocus();
            JOptionPane.showMessageDialog(null, "jumlah barangkecil masih kosong ");
            return;
        }
    }//GEN-LAST:event_txtPotonganFocusGained

    private void txtPotonganFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPotonganFocusLost
        // TODO add your handling code here:
        if(txtPotongan.getText().trim().equals("")){
            txtPotongan.setText("0");
        }
    }//GEN-LAST:event_txtPotonganFocusLost

    private void txtTambahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTambahanActionPerformed
        // TODO add your handling code here:
        if(!iha.cek(txtTambahan)){
            JOptionPane.showMessageDialog(null, "Tambahan harus angka");
        }else if(txtTambahan.getText().trim().equals("") || txtTambahan.getText()==null){
            txtTambahan.setText("0");
        }else{
            btGo.doClick();
        }
    }

    private int getAllTotal(){
        int hasil=0;
        koneksi();
        Statement s = null;
        try{
            s=connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT total from proseskasir2");
            while (rs.next()) {                
                hasil+=rs.getInt("total");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Tidak bisa melakukan getAll Total karena "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return hasil;
    }

    private void executeUpdate(int angkaUpdate, String kodeProses) {
        koneksi();
        Statement statement = null;
        try{
            statement=connection.createStatement();
            statement.execute("UPDATE "+sumberBarang+" SET stok='"+angkaUpdate+"' WHERE idbarang='"+kodeProses+"'");
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error update barangkecil karena "+exception);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }    
    
private void updateStokSetelahPembayaran() throws SQLException{
    koneksi();
    try(Statement statement = connection.createStatement()) {
        ResultSet rs=statement.executeQuery("SELECT kode, jml from proseskasir2");			
        while(rs.next()){
            String kodeProses=rs.getString("kode");				
            int jumlah=rs.getInt("jml");
            int stok=getStokBarang(kodeProses);
            int angkaUpdate=stok-jumlah;
            executeUpdate(angkaUpdate, kodeProses);
        }            
    }catch(SQLException exception){
        JOptionPane.showMessageDialog(null, "Error update stok karena = "+exception);
    } finally {
//        if (connection != null) {
//            connection.close();
//        }
    }
    }    

    private int getProfitPerBarisTransaksi(){
        koneksi();
        int hasil=0;
        Statement s = null;
        try{
            s=connection.createStatement();
            ResultSet resultSet=s.executeQuery("SELECT profit from proseskasir2");            
            while (resultSet.next()) {
                hasil+=resultSet.getInt("profit");
            }            
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam mendapatkan Subtotal barangkecil yang dibeli");
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return hasil;
    }
    
    private double getTotalBonusLangsung(){
        int totalBonusLangsung = 0;
        koneksi();
        try{
            Statement s= connection.createStatement();
            ResultSet rs=s.executeQuery("select bonuslangsung from proseskasir2");
            while (rs.next()) {                
                totalBonusLangsung+=rs.getInt("bonuslangsung");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dalam mendapatkan bonus langsung total");
        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return totalBonusLangsung;
    }
    
    private double getTotalBonusKumulatif(){
        int totalBonusKumulatif = 0;
        koneksi();
        try{
            Statement s= connection.createStatement();
            ResultSet rs=s.executeQuery("select bonuskumulatif from proseskasir2");
            while (rs.next()) {                
                totalBonusKumulatif+=rs.getInt("bonuskumulatif");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dalam mendapatkan bonus langsung total");
        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return totalBonusKumulatif;
    }    
    
    
    
    private void isiDataTransaksi(){
        koneksi();
        PreparedStatement ps = null;
        try{
            ps =connection.prepareStatement("insert into transaksipenjualanbarangbesar (idtransaksi, user, Tanggal, jam, total, pembayaran, sisa, profit, penjual, bonuslangsung, bonuskumulatif) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, txtNoTransaksi.getText());
            ps.setString(2, txtUser.getText());
            String tanggal = formatTanggal2.format(date);
            ps.setString(3, tanggal);
            ps.setString(4, txtJam.getText());
            ps.setInt(5, getSubTotalBarangBeli());
            ps.setInt(6, Integer.valueOf(txtPembayaran.getText()));
            ps.setInt(7, sisa);
            ps.setDouble(8, getProfitPerBarisTransaksi());
            ps.setString(9, cmbPenjual2.getSelectedItem().toString());
            ps.setDouble(10, getTotalBonusLangsung());
            ps.setDouble(11, getTotalBonusKumulatif());
            ps.executeUpdate();
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Gagal mengisi tabel transaksi karena = "+exception);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    
    private int getModal(String kode){
        koneksi();
        int modal = 0;
        Statement s = null;
        try{
            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select modal from "+sumberBarang+" where idbarang = '"+kode+"'");
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
                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
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
            ResultSet rs = s.executeQuery("select eceran from "+sumberBarang+" where idbarang = '"+kode+"'");
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
                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
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
            ResultSet rs = s.executeQuery("select grosir from "+sumberBarang+" where idbarang = '"+kode+"'");
            if(rs.next()){
                modal = rs.getInt("modal");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam mengambil harga grosir karena "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return modal;        
    }    
    
    private int getNoTransaksi(){
        koneksi();
        int hasil=0;
        try{
            String query = "select max(idtransaksi) from transaksipenjualanbarangbesar";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                hasil = rs.getInt(1);
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam mendapatkan max idtransaksi penjualan bb karena = "+exception, "perhatian", JOptionPane.ERROR_MESSAGE);
        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return hasil;
    }
    
    private void isiDataDetailTransaksi(){
        koneksi();        
        tabelTransaksi.selectAll();
        int row=tabelTransaksi.getSelectedRowCount();        
        PreparedStatement ps = null;
        for(int a=0; a<row; a++){
            try{
                ProsesKasir pk = modelPenjualan.get(a);
                ps=connection.prepareStatement("insert into detailtransaksipenjualanbarangbesar (iddetailtransaksi, user, Tanggal, jam, kode, namaBarang, jumlah, harga, modal, potongan, tambahan, total, profit, penjual, bonuslangsung, bonuskumulatif, asalbarang) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, String.valueOf(getNoTransaksi()));
                ps.setString(2, txtUser.getText());
                String tanggal = formatTanggal2.format(date);
                ps.setString(3, tanggal);
                ps.setString(4, txtJam.getText());
                ps.setString(5, pk.getKode());
                ps.setString(6, pk.getNama());
                ps.setString(7, String.valueOf(pk.getJml()));
                ps.setString(8, String.valueOf(pk.getHarga()));                
                ps.setInt(9, getModal(pk.getKode()));
                ps.setString(10, String.valueOf(pk.getPotongan()));                
                ps.setString(11, String.valueOf(pk.getTambahan()));
                ps.setString(12, String.valueOf(pk.getTotal()));
                ps.setString(13, String.valueOf(pk.getProfit()));
                ps.setString(14, String.valueOf(pk.getPenjual()));
                ps.setInt(15, pk.getBonuslangsung());
                ps.setInt(16, pk.getBonuskumulatif());
                ps.setString(17, pk.getAsalBarang());
                ps.executeUpdate();
            }catch(SQLException exception){
                JOptionPane.showMessageDialog(null, "Gagal mengisi tabel detail transaksi karena = "+exception);
            }finally{
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
//                if (connection != null) {
//                    try {
//                        connection.close();
//                    } catch (SQLException ex) {
//                        Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//            }
            }         
        }
    }
    
    int sisa = 0;
    private void txtPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPembayaranActionPerformed
        // TODO add your handling code here:
        if(!txtPembayaran.getText().trim().equals("")){
            int pembayaran = Integer.parseInt(txtPembayaran.getText());
            if(pembayaran<getAllTotal()){
                JOptionPane.showMessageDialog(null, "Pembayaran Lebih Kecil dari Total Harga", "Peringatan",JOptionPane.WARNING_MESSAGE);
                txtPembayaran.setText("");
                txtPembayaran.requestFocus();
            }else {
                String message = "Total Pembelian = "+formatter.FormatRupiah(String.valueOf(getAllTotal())) +"\nPembayaran = "+formatter.FormatRupiah(String.valueOf(pembayaran));
                message = message.toUpperCase();
                JOptionPane.showMessageDialog(null, message,"Informasi", JOptionPane.INFORMATION_MESSAGE);
                if(JOptionPane.showConfirmDialog(null, "Proses Transaksi ?") == JOptionPane.YES_OPTION){
                    sisa=pembayaran-getAllTotal();
                    txtSisa.setText(formatter.FormatRupiah(String.valueOf(sisa)));
                    try {
                        updateStokSetelahPembayaran();
                    } catch (SQLException ex) {
                        Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //tabelTransaksi.setEnabled(false);
                    //memindahkan data ke tabel transaksi
                    isiDataTransaksi();
                    isiDataDetailTransaksi();
                    txtJumlahBarangBawah.setEnabled(false);
                    txtSubTotal.setEnabled(false);
                    txtPembayaran.setEnabled(false);
                    cmbModePenjualan.setEnabled(true);
                }
            }
        }
    }//GEN-LAST:event_txtPembayaranActionPerformed

    private void txtPembayaranFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPembayaranFocusGained
        // TODO add your handling code here:
        inputPembayaran();
    }//GEN-LAST:event_txtPembayaranFocusGained

    private void tambahPenjualan(Boolean tambah){
        controller.tambahPenjualan(this);
        txtNoTransaksi.setText(loadId());
        setEnableTransaksi(tambah);
        txtKodeItem.requestFocus();
        txtTotal.setText("");
        txtSisa.setText("");
        txtJumlahBarangBawah.setText("");
        txtSubTotal.setText("");
        txtPembayaran.setText("");
        txtKodeItem.setText("");
        txtDetailItem.setText("");
        txtHargaPerItem.setText("");
        txtPembayaran.setEnabled(tambah);
        txtJumlahBarangBawah.setEnabled(tambah);
        txtSubTotal.setEnabled(tambah);
    }
    
    private void callDialogPenjual(){
        int y = 250;
        int x = 500;
        DialogPenjual.setModal(true);
        DialogPenjual.setLocation(x, y);
        DialogPenjual.setSize(300, 120);
        DialogPenjual.show();                
    }
    
    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        // TODO add your handling code here:        
        switch(cmbModePenjualan.getSelectedItem().toString()){
            case "grosir":
                if(JOptionPane.showConfirmDialog(null, "apakah anda akan menggunakan mode grosir ?", "perhatian", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
                    txtPotongan.setEditable(false);
                    txtPotongan.setText("0");
                    txtTambahan.setEditable(false);
                    txtTambahan.setText("0");
                    lblPenjual.setText("");                                        
                    callStokBarang();
                }else{
                    cmbModePenjualan.setSelectedItem("eceran");
                }
            break;
            case "eceran":
                txtPotongan.setEditable(true);
                txtTambahan.setEditable(true);
                callDialogPenjual();
                break;
            default:;
        }                
    }//GEN-LAST:event_btTambahActionPerformed

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        // TODO add your handling code here:
        controller.truncatePenjualan(this);
        resetAfterInsert();
        tambahPenjualan(false);
        txtJumlahBarangBawah.setText(String.valueOf(getJumlahBarangBeli()));
        txtSubTotal.setText(String.valueOf(getSubTotalBarangBeli()));
        cmbModePenjualan.setEnabled(true);
    }//GEN-LAST:event_btCancelActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        // TODO add your handling code here:
        controller.deletePenjualan(this);
        resetAfterInsert();
        txtJumlahBarangBawah.setText(String.valueOf(getJumlahBarangBeli()));
        txtSubTotal.setText(String.valueOf(getSubTotalBarangBeli()));
    }//GEN-LAST:event_btHapusActionPerformed

    private void btCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCetakActionPerformed
        // TODO add your handling code here:
        ReportParam rp=new ReportParam();
        rp.buildReportStruk("strukbb", Integer.valueOf(txtNoTransaksi.getText()));
    }//GEN-LAST:event_btCetakActionPerformed

    private void txtKataKunci1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKataKunci1ActionPerformed
        // TODO add your handling code here:
        btCari1.doClick();
    }//GEN-LAST:event_txtKataKunci1ActionPerformed

    private void btCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCari1ActionPerformed
        if(sumberBarang.equals("barangtoko")){
            try {
                controllerBarangToko.cari(this, this);
            } catch (SQLException ex) {
                Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (com.wissensalt.sinarelektronik.masterdata.barangtoko.error.BarangException ex) {
                Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(sumberBarang.equals("barangbesar")){
            try {
                // TODO add your handling code here:
                controllerBarang.cari(this, this);
            } catch (SQLException | BarangException ex) {
                Logger.getLogger(ViewSearchBarangBesar2.class.getName()).log(Level.SEVERE, null, ex);
            }            
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
        if(sumberBarang.equals("barangbesar")){
            try {
                controllerBarang.sort(this);
            } catch (SQLException | BarangException ex) {
                Logger.getLogger(ViewSearchBarangBesar2.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }else if(sumberBarang.equals("barangtoko")){
            try { 
                controllerBarangToko.sort(this);
            } catch (SQLException ex) {
                Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (com.wissensalt.sinarelektronik.masterdata.barangtoko.error.BarangException ex) {
                Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    private void callStokBarang(){
        DialogStokBarang.setSize(300, 120);
        int y = 250;
        int x = 500;                              
        DialogStokBarang.setLocation(x, y);
        DialogStokBarang.show();        
    }
    private void btOkPenjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkPenjualActionPerformed
        // TODO add your handling code here:
        lblPenjual.setText(cmbPenjual2.getSelectedItem().toString());
        DialogPenjual.dispose();        
        callStokBarang();
    }//GEN-LAST:event_btOkPenjualActionPerformed

    private void btCancelPenjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelPenjualActionPerformed
        // TODO add your handling code here:
        DialogPenjual.dispose();
        lblPenjual.setText("");
        lblStokBarang.setText("");
        tambahPenjualan(false);
    }//GEN-LAST:event_btCancelPenjualActionPerformed

    private void cmbPenjual2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPenjual2ActionPerformed
        // TODO add your handling code here:        
    }//GEN-LAST:event_cmbPenjual2ActionPerformed

    private void tabelBarang1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelBarang1KeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tabelBarang1KeyPressed

    private void tabelBarang1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelBarang1KeyTyped
        // TODO add your handling code here:        
    }//GEN-LAST:event_tabelBarang1KeyTyped

    private void cmbStokBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStokBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStokBarangActionPerformed

    private void btOkPenjual1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkPenjual1ActionPerformed
        // TODO add your handling code here:
        lblStokBarang.setText(cmbStokBarang.getSelectedItem().toString());
        String sumber = cmbStokBarang.getSelectedItem().toString();
        if(sumber.equals("Toko")){
            try {
                sumberBarang = "barangtoko";
                tabelBarang1.setModel(TabelmodelBarangToko);
                loadDatabaseCariBarangToko();
            } catch (com.wissensalt.sinarelektronik.masterdata.barangtoko.error.BarangException ex) {
                Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(sumber.equals("Gudang")){
            sumberBarang = "barangbesar";
            tabelBarang1.setModel(tabelmodelBarangBesar);
            try {
                loadDatabaseCariBarang();
            } catch (SQLException ex) {
                Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BarangException ex) {
                Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }       
        cmbModePenjualan.setEnabled(false);
        setTxtkodeAuto();        
        tambahPenjualan(true);        
        DialogStokBarang.dispose();
    }//GEN-LAST:event_btOkPenjual1ActionPerformed

    private void btCancelPenjual1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelPenjual1ActionPerformed
        // TODO add your handling code here:
        switch(cmbModePenjualan.getSelectedItem().toString()){
            case "grosir":
                DialogPenjual.dispose();
                lblPenjual.setText("");
                lblStokBarang.setText("");
                tambahPenjualan(false);
                break;
            case "eceran":
                DialogStokBarang.dispose();
                callDialogPenjual();
            break;
            default:;
        }                
    }//GEN-LAST:event_btCancelPenjual1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogCariBarang;
    private javax.swing.JDialog DialogPenjual;
    private javax.swing.JDialog DialogPreview;
    private javax.swing.JDialog DialogStokBarang;
    private javax.swing.JPanel Kanan;
    private javax.swing.JPanel atas;
    private javax.swing.JPanel atas2;
    private javax.swing.JPanel bawah;
    private javax.swing.JPanel bawah2;
    private javax.swing.JPanel below;
    private javax.swing.JPanel bottom;
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btCancelPenjual;
    private javax.swing.JButton btCancelPenjual1;
    private javax.swing.JButton btCari1;
    private javax.swing.JButton btCetak;
    private javax.swing.JButton btGo;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btOkPenjual;
    private javax.swing.JButton btOkPenjual1;
    private javax.swing.JButton btSetCari;
    private javax.swing.JButton btTambah;
    private javax.swing.JButton btViewBarang;
    private javax.swing.JPanel centerBottom;
    private javax.swing.JComboBox cmbCari1;
    private javax.swing.JComboBox cmbModePenjualan;
    private javax.swing.JComboBox cmbPenjual2;
    private javax.swing.JComboBox cmbStokBarang;
    private javax.swing.JComboBox cmbUrut1;
    private javax.swing.JPanel dua;
    private javax.swing.JPanel empat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
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
    private javax.swing.JLabel lblPenjual;
    private javax.swing.JLabel lblPreview;
    private javax.swing.JLabel lblStokBarang;
    private javax.swing.JPanel left;
    private javax.swing.JPanel middle;
    private javax.swing.JPanel middle2;
    private com.wissensalt.sinarelektronik.swinglib.panelGradUser panelBackgroundUp1;
    private com.wissensalt.sinarelektronik.swinglib.panelGradUser panelGradUser4;
    private javax.swing.JPanel panelSisa;
    private javax.swing.JMenuItem pilih;
    private javax.swing.JPopupMenu popUpTabel;
    private javax.swing.JPanel right;
    private javax.swing.JPanel satu;
    private javax.swing.JTable tabelBarang1;
    private javax.swing.JTable tabelTransaksi;
    private javax.swing.JPanel tengah;
    private javax.swing.JPanel tengah2;
    private javax.swing.JTextField txtDetailItem;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtHargaPerItem;
    private javax.swing.JTextField txtJam;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtJumlahBarangBawah;
    private javax.swing.JTextField txtKataKunci1;
    private com.wissensalt.sinarelektronik.swinglib.AutoComplete.TextFieldAutoComplete txtKodeItem;
    private javax.swing.JTextField txtNoTransaksi;
    private javax.swing.JTextField txtPembayaran;
    private javax.swing.JTextField txtPotongan;
    private javax.swing.JTextField txtSisa;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTambahan;
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
        txtHarga.setText("");
        txtPotongan.setText("");
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
    public void onInsert(BarangBesarDTO BarangBesarDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onUpdate(BarangBesarDTO BarangBesarDTO) {
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

    @Override
    public void onChange(com.wissensalt.sinarelektronik.masterdata.barangtoko.model.barangModel model) {
        txtKataKunci1.setText(modelBarangToko.getCari());
    }

    @Override
    public void onInsert(com.wissensalt.sinarelektronik.masterdata.barangtoko.entity.barang barang) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onUpdate(com.wissensalt.sinarelektronik.masterdata.barangtoko.entity.barang barang) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onSearchToko(List list) {
        TabelmodelBarangToko.setList(list);
    }

    @Override
    public void onSortToko(List list) {
        TabelmodelBarangToko.setList(list);
    }
}

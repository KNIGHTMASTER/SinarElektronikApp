/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.tambahBarang.view;

import Sinarelektronikapp.config.InternetProtocol;
import Sinarelektronikapp.masterData.barang.entity.barang;
import Sinarelektronikapp.masterData.barang.model.barangModel;
import Sinarelektronikapp.masterData.barang.model.event.tambahBarangListener;
import Sinarelektronikapp.masterData.barang.model.tabelModelBarang;
import Sinarelektronikapp.masterData.tambahBarang.controller.tambahBarangController;
import Sinarelektronikapp.masterData.tambahBarang.model.tambahBarangModel;
import com.toedter.components.JSpinField;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Fauzi
 */
public class tambahBarangView extends javax.swing.JPanel implements tambahBarangListener{

    /**
     * Creates new form tambahBarangView
     */
    
    tambahBarangController controller;
    
    tambahBarangModel model;
    
    
    static InternetProtocol ip1 = new InternetProtocol();
    
        
    public tambahBarangView(){        
        model = new tambahBarangModel();
        model.setListener(this);
        controller = new tambahBarangController();
        controller.setModel(model);
                
        initComponents();
        resetManual();
        koneksi();
        loadNamaBarang();
        loadDataMerek();
        loadDataSupplier();
        loadDataTipe();
        loadDataSatuan();
        LblImage.setIcon(null);
        hideButton();        
        setIdBarang();
    }
    
    private void setIdBarang(){
        String idBarang = cmbMerek.getSelectedItem().toString()+cmbTipe.getSelectedItem().toString()+"-"+cmbNamaBarang.getSelectedItem().toString();
        txtIdBarang.setText(idBarang);
    }
    private void hideButton(){
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jButton4.setVisible(false);
        jButton5.setVisible(false);
        jButton6.setVisible(false);
        jButton7.setVisible(false);
        jButton8.setVisible(false);
        jButton9.setVisible(false);
        jButton10.setVisible(false);
        jButton11.setVisible(false);
        jButton12.setVisible(false);
    }
    private void setPanelGaransi() {        
        if(rbYa.isSelected() == true){
            PanellamaGaransi.setVisible(true);
        }else if(rbTidak.isSelected() == true){
            PanellamaGaransi.setVisible(true);
        }else{
            PanellamaGaransi.setVisible(true);
        }
    }

    public JComboBox getCmbNamaBarang() {
        return cmbNamaBarang;
    }

    public void setCmbNamaBarang(JComboBox cmbNamaBarang) {
        this.cmbNamaBarang = cmbNamaBarang;
    }
    
    barangModel barangmodel=new barangModel();

    private Connection conn;
	
    public void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+ip1.getIpServer()+"/sinarelektronik?;", "root", "5430trisin9");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Driver not Found");
        }        
    }        
    
    public void loadNamaBarang(){
        Statement statement=null;
        try{
            statement=conn.createStatement();            
            ResultSet rs=statement.executeQuery("SELECT namabarang FROM namabarang ORDER BY namabarang");
            while (rs.next()) {
                cmbNamaBarang.addItem(rs.getString("namabarang"));
            }
        }catch(SQLException e)         {
            JOptionPane.showMessageDialog(null, "tidak bisa me-load data nama barang");
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(tambahBarangView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
    }
    
    public void loadDataMerek(){
        Statement statement=null;
        try{
            statement=conn.createStatement();            
            ResultSet rs=statement.executeQuery("SELECT namamerek FROM merek ORDER BY namamerek");
            while (rs.next()) {
                cmbMerek.addItem(rs.getString("namamerek"));
            }
        }catch(SQLException e)         {
            JOptionPane.showMessageDialog(null, "tidak bisa me-load data merek");
            e.printStackTrace();
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(tambahBarangView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void loadDataTipe(){
        Statement statement=null;
        try{
            statement=conn.createStatement();            
            ResultSet rs=statement.executeQuery("SELECT namaTipe FROM tipe ORDER BY namaTipe");
            while (rs.next()) {
                cmbTipe.addItem(rs.getString("namaTipe"));
            }
        }catch(SQLException e)         {
            JOptionPane.showMessageDialog(null, "tidak bisa me-load data tipe");
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(tambahBarangView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public void loadDataSupplier(){
        Statement statement=null;
        try{
            statement=conn.createStatement();            
            ResultSet rs=statement.executeQuery("SELECT nama FROM supplier ORDER BY nama");
            while (rs.next()) {
                cmbSupplier.addItem(rs.getString("nama"));
            }
        }catch(SQLException e)         {
            JOptionPane.showMessageDialog(null, "tidak bisa me-load data supplier");
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(tambahBarangView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }    
    
    public void loadDataSatuan(){
        Statement statement=null;
        try{
            statement=conn.createStatement();            
            ResultSet rs=statement.executeQuery("SELECT namasatuan FROM satuan ORDER  BY namasatuan");
            while (rs.next()) {
                cmbSatuan.addItem(rs.getString("namasatuan"));
            }
        }catch(SQLException e)         {
            JOptionPane.showMessageDialog(null, "tidak bisa me-load data Satuan");
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(tambahBarangView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }        

    public JSpinField getJsStok() {
        return JsStok;
    }

    public JSpinField getJsstokMin() {
        return JsstokMin;
    }

    public JComboBox getCmbMerek() {
        return cmbMerek;
    }

    public JComboBox getCmbSatuan() {
        return cmbSatuan;
    }

    public JComboBox getCmbSupplier() {
        return cmbSupplier;
    }

    public JComboBox getCmbTipe() {
        return cmbTipe;
    }

    public JRadioButton getRbTidak() {
        return rbTidak;
    }

    public JRadioButton getRbYa() {
        return rbYa;
    }

    public JTextField getTxtHarga() {
        return txtHargaEceran;
    }

    public JTextField getTxtHargaEceran() {
        return txtHargaEceran;
    }

    public void setTxtHargaEceran(JTextField txtHargaEceran) {
        this.txtHargaEceran = txtHargaEceran;
    }

    public JTextField getTxtHargaGrosir() {
        return txtHargaGrosir;
    }

    public void setTxtHargaGrosir(JTextField txtHargaGrosir) {
        this.txtHargaGrosir = txtHargaGrosir;
    }



    public JTextField getTxtHargaModal() {
        return txtHargaModal;
    }

    public JTextField getTxtIdBarang() {
        return txtIdBarang;
    }

    public JTextField getTxtIdBarcode() {
        return txtIdBarcode;
    }

    public JTextArea getTxtKet() {
        return txtKet;
    }
        
    final Toolkit toolkit = Toolkit.getDefaultToolkit();

    public JSpinField getJsBulan() {
        return jsBulan;
    }

    public JSpinField getJsHari() {
        return jsHari;
    }

    public JSpinField getJsTahun() {
        return jsTahun;
    }

    public JComboBox getCmbKategori() {
        return cmbKategori;
    }

    public void setCmbKategori(JComboBox cmbKategori) {
        this.cmbKategori = cmbKategori;
    }
    
    
    final Dimension screenSize = toolkit.getScreenSize();
    final int x = (screenSize.width - this.getWidth()) / 4;
    final int y = (screenSize.height - this.getHeight()) /4;
            
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogTambahTipe = new javax.swing.JDialog();
        try {
            tipeView1 = new Sinarelektronikapp.masterData.tipe.view.TipeView();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        } catch (Sinarelektronikapp.masterData.tipe.error.TipeException e2) {
            e2.printStackTrace();
        }
        dialogTambahSatuan = new javax.swing.JDialog();
        try {
            satuanView1 = new Sinarelektronikapp.masterData.satuan.view.SatuanView();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        } catch (Sinarelektronikapp.masterData.satuan.error.SatuanException e2) {
            e2.printStackTrace();
        }
        dialogTambahSupplier = new javax.swing.JDialog();
        try {
            supplierView1 = new Sinarelektronikapp.masterData.supplier.view.supplierView();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        } catch (Sinarelektronikapp.masterData.supplier.error.supplierException e2) {
            e2.printStackTrace();
        }
        dialogTambahMerek = new javax.swing.JDialog();
        merekView21 = new Sinarelektronikapp.masterData.merek.view.MerekView2();
        buttonGroup1 = new javax.swing.ButtonGroup();
        dialogTambahNamaBarang = new javax.swing.JDialog();
        namaBarangView1 = new Sinarelektronikapp.masterData.namabarang.view.NamaBarangView();
        atas = new javax.swing.JPanel();
        panelLabelKiri = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        inputTengah = new javax.swing.JPanel();
        panelIsianTengah1 = new javax.swing.JPanel();
        txtIdBarang = new javax.swing.JTextField();
        txtIdBarcode = new javax.swing.JTextField();
        cmbNamaBarang = new javax.swing.JComboBox();
        cmbTipe = new javax.swing.JComboBox();
        cmbMerek = new javax.swing.JComboBox();
        txtHargaModal = new javax.swing.JTextField();
        txtHargaGrosir = new javax.swing.JTextField();
        txtHargaEceran = new javax.swing.JTextField();
        cmbSatuan = new javax.swing.JComboBox();
        JsStok = new com.toedter.components.JSpinField();
        JsstokMin = new com.toedter.components.JSpinField();
        cmbSupplier = new javax.swing.JComboBox();
        panelRB = new javax.swing.JPanel();
        rbYa = new javax.swing.JRadioButton();
        rbTidak = new javax.swing.JRadioButton();
        PanellamaGaransi = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jsTahun = new com.toedter.components.JSpinField();
        jLabel17 = new javax.swing.JLabel();
        jsBulan = new com.toedter.components.JSpinField();
        jLabel18 = new javax.swing.JLabel();
        jsHari = new com.toedter.components.JSpinField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtKet = new javax.swing.JTextArea();
        cmbKategori = new javax.swing.JComboBox();
        panelTambah = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btTambahNamaBarang = new javax.swing.JButton();
        btTambahItipe = new javax.swing.JButton();
        btTambahMerek = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btTambahSatuan = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        btTambahSupplier = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        panelImage = new javax.swing.JPanel();
        LblImage = new javax.swing.JLabel();
        btTambahGambar = new javax.swing.JButton();
        bawah = new javax.swing.JPanel();
        btTambah = new javax.swing.JButton();
        btReset = new javax.swing.JButton();

        dialogTambahTipe.setTitle("Tambah Tipe");
        dialogTambahTipe.getContentPane().add(tipeView1, java.awt.BorderLayout.CENTER);

        dialogTambahSatuan.setTitle("Tambah Satuan");
        dialogTambahSatuan.getContentPane().add(satuanView1, java.awt.BorderLayout.CENTER);

        dialogTambahSupplier.setTitle("Tambah Supplier");
        dialogTambahSupplier.getContentPane().add(supplierView1, java.awt.BorderLayout.CENTER);

        dialogTambahMerek.setTitle("Tambah Merek");
        dialogTambahMerek.getContentPane().add(merekView21, java.awt.BorderLayout.LINE_START);

        dialogTambahNamaBarang.getContentPane().add(namaBarangView1, java.awt.BorderLayout.CENTER);

        setMinimumSize(new java.awt.Dimension(700, 950));
        setPreferredSize(new java.awt.Dimension(700, 950));
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        atas.setLayout(new java.awt.BorderLayout());

        panelLabelKiri.setLayout(new java.awt.GridLayout(16, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Id Barang :");
        panelLabelKiri.add(jLabel1);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Id Barcode Barang :");
        panelLabelKiri.add(jLabel2);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nama Barang :");
        panelLabelKiri.add(jLabel3);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tipe :");
        panelLabelKiri.add(jLabel4);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Merek :");
        panelLabelKiri.add(jLabel5);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Harga Modal :");
        panelLabelKiri.add(jLabel12);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Harga Grosir :");
        panelLabelKiri.add(jLabel13);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Harga Eceran :");
        panelLabelKiri.add(jLabel14);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Satuan :");
        panelLabelKiri.add(jLabel6);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Stok :");
        panelLabelKiri.add(jLabel7);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Stok Minimum :");
        panelLabelKiri.add(jLabel8);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Supplier :");
        panelLabelKiri.add(jLabel9);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Garansi :");
        panelLabelKiri.add(jLabel15);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Lama Garansi :");
        panelLabelKiri.add(jLabel16);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Keterangan :");
        panelLabelKiri.add(jLabel10);

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Kategori");
        panelLabelKiri.add(jLabel19);

        atas.add(panelLabelKiri, java.awt.BorderLayout.WEST);

        inputTengah.setLayout(new java.awt.BorderLayout());

        panelIsianTengah1.setLayout(new java.awt.GridLayout(16, 0));

        txtIdBarang.setEditable(false);
        txtIdBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdBarangActionPerformed(evt);
            }
        });
        panelIsianTengah1.add(txtIdBarang);

        txtIdBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdBarcodeActionPerformed(evt);
            }
        });
        panelIsianTengah1.add(txtIdBarcode);

        cmbNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNamaBarangActionPerformed(evt);
            }
        });
        cmbNamaBarang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbNamaBarangFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbNamaBarangFocusLost(evt);
            }
        });
        panelIsianTengah1.add(cmbNamaBarang);

        cmbTipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipeActionPerformed(evt);
            }
        });
        cmbTipe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbTipeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbTipeFocusLost(evt);
            }
        });
        panelIsianTengah1.add(cmbTipe);

        cmbMerek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMerekActionPerformed(evt);
            }
        });
        cmbMerek.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbMerekFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbMerekFocusLost(evt);
            }
        });
        panelIsianTengah1.add(cmbMerek);

        txtHargaModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaModalActionPerformed(evt);
            }
        });
        txtHargaModal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHargaModalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHargaModalFocusLost(evt);
            }
        });
        panelIsianTengah1.add(txtHargaModal);

        txtHargaGrosir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaGrosirActionPerformed(evt);
            }
        });
        txtHargaGrosir.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHargaGrosirFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHargaGrosirFocusLost(evt);
            }
        });
        panelIsianTengah1.add(txtHargaGrosir);

        txtHargaEceran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaEceranActionPerformed(evt);
            }
        });
        txtHargaEceran.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHargaEceranFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHargaEceranFocusLost(evt);
            }
        });
        panelIsianTengah1.add(txtHargaEceran);

        cmbSatuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSatuanActionPerformed(evt);
            }
        });
        cmbSatuan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbSatuanFocusGained(evt);
            }
        });
        panelIsianTengah1.add(cmbSatuan);

        JsStok.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                JsStokFocusGained(evt);
            }
        });
        panelIsianTengah1.add(JsStok);

        JsstokMin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                JsstokMinFocusGained(evt);
            }
        });
        panelIsianTengah1.add(JsstokMin);

        cmbSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSupplierActionPerformed(evt);
            }
        });
        cmbSupplier.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbSupplierFocusGained(evt);
            }
        });
        panelIsianTengah1.add(cmbSupplier);

        panelRB.setLayout(new java.awt.GridLayout(1, 2));

        buttonGroup1.add(rbYa);
        rbYa.setText("Ya");
        rbYa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbYaActionPerformed(evt);
            }
        });
        panelRB.add(rbYa);

        buttonGroup1.add(rbTidak);
        rbTidak.setText("Tidak");
        rbTidak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTidakActionPerformed(evt);
            }
        });
        panelRB.add(rbTidak);

        panelIsianTengah1.add(panelRB);

        PanellamaGaransi.setLayout(new java.awt.GridLayout(1, 6));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Tahun :");
        PanellamaGaransi.add(jLabel11);
        PanellamaGaransi.add(jsTahun);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("bulan :");
        PanellamaGaransi.add(jLabel17);
        PanellamaGaransi.add(jsBulan);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("hari :");
        PanellamaGaransi.add(jLabel18);
        PanellamaGaransi.add(jsHari);

        panelIsianTengah1.add(PanellamaGaransi);

        txtKet.setColumns(20);
        txtKet.setRows(5);
        jScrollPane1.setViewportView(txtKet);

        panelIsianTengah1.add(jScrollPane1);

        cmbKategori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ALAT LISTRIK", "HOME APPLIANCES", "SPARE PARTS" }));
        panelIsianTengah1.add(cmbKategori);

        inputTengah.add(panelIsianTengah1, java.awt.BorderLayout.CENTER);

        panelTambah.setMaximumSize(new java.awt.Dimension(50, 32767));
        panelTambah.setMinimumSize(new java.awt.Dimension(50, 345));
        panelTambah.setPreferredSize(new java.awt.Dimension(50, 345));
        panelTambah.setLayout(new java.awt.GridLayout(16, 0));

        jButton1.setText("jButton1");
        panelTambah.add(jButton1);

        jButton2.setText("jButton1");
        panelTambah.add(jButton2);

        btTambahNamaBarang.setText("+");
        btTambahNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahNamaBarangActionPerformed(evt);
            }
        });
        panelTambah.add(btTambahNamaBarang);

        btTambahItipe.setText("+");
        btTambahItipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahItipeActionPerformed(evt);
            }
        });
        panelTambah.add(btTambahItipe);

        btTambahMerek.setText("+");
        btTambahMerek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahMerekActionPerformed(evt);
            }
        });
        panelTambah.add(btTambahMerek);

        jButton4.setText("jButton1");
        panelTambah.add(jButton4);

        jButton5.setText("jButton1");
        panelTambah.add(jButton5);

        jButton6.setText("jButton1");
        panelTambah.add(jButton6);

        btTambahSatuan.setText("+");
        btTambahSatuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahSatuanActionPerformed(evt);
            }
        });
        panelTambah.add(btTambahSatuan);

        jButton7.setText("jButton1");
        panelTambah.add(jButton7);

        jButton8.setText("jButton1");
        panelTambah.add(jButton8);

        btTambahSupplier.setText("+");
        btTambahSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahSupplierActionPerformed(evt);
            }
        });
        panelTambah.add(btTambahSupplier);

        jButton9.setText("jButton1");
        panelTambah.add(jButton9);

        jButton10.setText("jButton1");
        panelTambah.add(jButton10);

        jButton11.setText("jButton1");
        panelTambah.add(jButton11);

        jButton12.setText("jButton1");
        panelTambah.add(jButton12);

        inputTengah.add(panelTambah, java.awt.BorderLayout.EAST);

        atas.add(inputTengah, java.awt.BorderLayout.CENTER);

        panelImage.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelImage.setMinimumSize(new java.awt.Dimension(170, 27));
        panelImage.setPreferredSize(new java.awt.Dimension(170, 27));
        panelImage.setLayout(new java.awt.BorderLayout());
        panelImage.add(LblImage, java.awt.BorderLayout.CENTER);

        btTambahGambar.setText("Gambar");
        btTambahGambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahGambarActionPerformed(evt);
            }
        });
        panelImage.add(btTambahGambar, java.awt.BorderLayout.PAGE_END);

        atas.add(panelImage, java.awt.BorderLayout.EAST);

        add(atas, java.awt.BorderLayout.CENTER);

        bawah.setLayout(new java.awt.GridLayout(1, 2));

        btTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/simpan.png"))); // NOI18N
        btTambah.setMnemonic('S');
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });
        bawah.add(btTambah);

        btReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/reset.png"))); // NOI18N
        btReset.setMnemonic('R');
        btReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetActionPerformed(evt);
            }
        });
        bawah.add(btReset);

        add(bawah, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    public void resetManual(){
        txtIdBarang.setText("");
        txtHargaEceran.setText("");
        txtIdBarcode.setText("");
        txtKet.setText("");
        jsTahun.setValue(0);
        JsstokMin.setValue(0);      
    }
    
    private void reset(){
        controller.resetTambahBarang();
        resetManual();        
    }
    
    private void btResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetActionPerformed
        // TODO add your handling code here:        
        reset();
    }//GEN-LAST:event_btResetActionPerformed

    private void btTambahItipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahItipeActionPerformed
        // TODO add your handling code here:
        dialogTambahTipe.setModal(true);
        dialogTambahTipe.setLocation(x, y);
        dialogTambahTipe.setSize(320, 400);
        dialogTambahTipe.show();
    }//GEN-LAST:event_btTambahItipeActionPerformed

    private void btTambahMerekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahMerekActionPerformed
        // TODO add your handling code here:
        dialogTambahMerek.setLocation(x, y);
        dialogTambahMerek.setSize(390, 400);
        dialogTambahMerek.setModal(true);
        dialogTambahMerek.show();                       
    }//GEN-LAST:event_btTambahMerekActionPerformed

    private void btTambahSatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahSatuanActionPerformed
        // TODO add your handling code here:
        dialogTambahSatuan.setLocation(x, y);
        dialogTambahSatuan.setSize(320, 400);
        dialogTambahSatuan.setModal(true);
        dialogTambahSatuan.show();                
    }//GEN-LAST:event_btTambahSatuanActionPerformed

    private void btTambahSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahSupplierActionPerformed
        // TODO add your handling code here:
        dialogTambahSupplier.setLocation(x, y);
        dialogTambahSupplier.setSize(800, 500);
        dialogTambahSupplier.setModal(true);
        dialogTambahSupplier.show();                
    }//GEN-LAST:event_btTambahSupplierActionPerformed
    
    File gambar ;

    public File getGambar() {
        return gambar;
    }

    public void setGambar(File gambar) {
        this.gambar = gambar;
    }
        
    private void save(){        
        if(txtIdBarang.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "ID barang masih kosong");
            txtIdBarang.getText();
            return;
        }else if(txtHargaEceran.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Harga masih kosong");
            txtHargaEceran.getText();
            return;                                    
        }else if(pathGambar!=null || pathGambar!=""){
        gambar = new File(pathGambar);                            
        controller.insertBarang(this);
        resetManual();
        txtIdBarang.requestFocus();                    
        }
    }
    
    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        // TODO add your handling code here:                
        setIdBarang();
        save();
    }//GEN-LAST:event_btTambahActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_formFocusGained

    private void cmbTipeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbTipeFocusGained
        // TODO add your handling code here:
        cmbTipe.removeAllItems();
        loadDataTipe();
        setIdBarang();
    }//GEN-LAST:event_cmbTipeFocusGained

    private void cmbMerekFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbMerekFocusGained
        // TODO add your handling code here:        
        cmbMerek.removeAllItems();
        loadDataMerek();        
        setIdBarang();
    }//GEN-LAST:event_cmbMerekFocusGained

    private void cmbSatuanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSatuanFocusGained
        // TODO add your handling code here:
        cmbSatuan.removeAllItems();
        loadDataSatuan();        
        setIdBarang();
    }//GEN-LAST:event_cmbSatuanFocusGained

    private void cmbSupplierFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSupplierFocusGained
        // TODO add your handling code here:
        cmbSupplier.removeAllItems();
        loadDataSupplier();
        setIdBarang();
    }//GEN-LAST:event_cmbSupplierFocusGained

    private void txtHargaEceranFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHargaEceranFocusLost
        // TODO add your handling code here:
 int hargaGrosir = Integer.valueOf(txtHargaGrosir.getText());
        int hargaEceran = Integer.valueOf(txtHargaEceran.getText());
        
        if(!txtHargaEceran.getText().matches("[0-9]*")){
            JOptionPane.showMessageDialog(null, "harga harus angka");
            txtHargaEceran.setText("0");
            txtHargaEceran.requestFocus();
        }else if(hargaEceran<=hargaGrosir){
            JOptionPane.showMessageDialog(null, "Harga eceran harus lebih besar dari harga grosir ");
            txtHargaEceran.setText(String.valueOf(hargaGrosir)+1);
            txtHargaEceran.requestFocus();
        }     
    }//GEN-LAST:event_txtHargaEceranFocusLost

    private void txtIdBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdBarangActionPerformed
        // TODO add your handling code here:
        if(txtIdBarang.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "id barang masih kosong");
            txtIdBarang.requestFocus();
        }else{
            txtIdBarcode.requestFocus();
        }
    }//GEN-LAST:event_txtIdBarangActionPerformed

    private void txtIdBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdBarcodeActionPerformed
        // TODO add your handling code here:
        cmbNamaBarang.requestFocus();
    }//GEN-LAST:event_txtIdBarcodeActionPerformed

    private void cmbTipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipeActionPerformed
        // TODO add your handling code here:
        loadDataTipe();
        //setIdBarang();
    }//GEN-LAST:event_cmbTipeActionPerformed

    private void cmbMerekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMerekActionPerformed
        // TODO add your handling code here:
        loadDataMerek();
        //setIdBarang();
    }//GEN-LAST:event_cmbMerekActionPerformed

    private void txtHargaEceranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaEceranActionPerformed
        // TODO add your handling code here:
 int hargaGrosir = Integer.valueOf(txtHargaGrosir.getText());
        int hargaEceran = Integer.valueOf(txtHargaEceran.getText());
        
        if(!txtHargaEceran.getText().matches("[0-9]*")){
            JOptionPane.showMessageDialog(null, "harga harus angka");
            txtHargaEceran.setText("0");
            txtHargaEceran.requestFocus();
        }else if(hargaEceran<=hargaGrosir){
            JOptionPane.showMessageDialog(null, "Harga eceran harus lebih besar dari harga grosir ");
            txtHargaEceran.setText(String.valueOf(hargaGrosir)+1);
            txtHargaEceran.requestFocus();
        }     
    }//GEN-LAST:event_txtHargaEceranActionPerformed

    private void cmbSatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSatuanActionPerformed
        // TODO add your handling code here:      
        loadDataSatuan();
    }//GEN-LAST:event_cmbSatuanActionPerformed

    private void cmbSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSupplierActionPerformed
        // TODO add your handling code here:
        loadDataSupplier();
    }//GEN-LAST:event_cmbSupplierActionPerformed

    String pathGambar="";

    public String getPathGambar() {
        return pathGambar;
    }

    public void setPathGambar(String pathGambar) {
        this.pathGambar = pathGambar;
    }
    
    private void btTambahGambarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahGambarActionPerformed
        // TODO add your handling code here:        
        Image thumb=null;
        JFileChooser ambil=new JFileChooser();
        int ok=ambil.showOpenDialog(this);
        if(ok==JFileChooser.APPROVE_OPTION){                        
            pathGambar = ambil.getSelectedFile().getPath();
            File file=new File(pathGambar);
            
            BufferedImage image = null;            
            try {
                image = ImageIO.read(file); 
                thumb=image.getScaledInstance(150, 150, Image.SCALE_FAST);
            } catch (IOException ex) {
                Logger.getLogger(tambahBarangView.class.getName()).log(Level.SEVERE, null, ex);
            }
            LblImage.setSize(new Dimension(150, 150));
            ImageIcon gambar=new ImageIcon(thumb);
            LblImage.setIcon(gambar);
        }
    }//GEN-LAST:event_btTambahGambarActionPerformed

    private void txtHargaModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaModalActionPerformed
        // TODO add your handling code here:
        if(!txtHargaModal.getText().matches("[0-9]*")){
            JOptionPane.showMessageDialog(null, "harga harus angka");
            txtHargaModal.setText("0");
            txtHargaModal.requestFocus();
        }                
    }//GEN-LAST:event_txtHargaModalActionPerformed

    private void txtHargaModalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHargaModalFocusLost
        // TODO add your handling code here:
        if(!txtHargaModal.getText().matches("[0-9]*")){
            JOptionPane.showMessageDialog(null, "harga harus angka");
            txtHargaModal.setText("0");
            txtHargaModal.requestFocus();
        }                
    }//GEN-LAST:event_txtHargaModalFocusLost

    private void txtHargaGrosirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaGrosirActionPerformed
        // TODO add your handling code here:
    int hargaModal = Integer.valueOf(txtHargaModal.getText());
        int hargaGrosir = Integer.valueOf(txtHargaGrosir.getText());
        
        if(!txtHargaGrosir.getText().matches("[0-9]*")){
            JOptionPane.showMessageDialog(null, "harga grosir harus angka");
            txtHargaGrosir.setText("0");
            txtHargaGrosir.requestFocus();
        }else if(hargaGrosir<=hargaModal){
            JOptionPane.showMessageDialog(null, "Harga grosi harus lebih besar harga modal ");
            txtHargaGrosir.setText(String.valueOf((hargaModal+1)));
            txtHargaGrosir.requestFocus();
       }                
    }//GEN-LAST:event_txtHargaGrosirActionPerformed

    private void txtHargaGrosirFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHargaGrosirFocusLost
        // TODO add your handling code here:    
        int hargaModal = Integer.valueOf(txtHargaModal.getText());
        int hargaGrosir = Integer.valueOf(txtHargaGrosir.getText());
        
        if(!txtHargaGrosir.getText().matches("[0-9]*")){
            JOptionPane.showMessageDialog(null, "harga grosir harus angka");
            txtHargaGrosir.setText("0");
            txtHargaGrosir.requestFocus();
        }else if(hargaGrosir<=hargaModal){
            JOptionPane.showMessageDialog(null, "Harga grosi harus lebih besar harga modal ");
            txtHargaGrosir.setText(String.valueOf((hargaModal+1)));
            txtHargaGrosir.requestFocus();
        }                
    }//GEN-LAST:event_txtHargaGrosirFocusLost

    private void rbYaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbYaActionPerformed
        // TODO add your handling code here:
        PanellamaGaransi.setVisible(true);
    }//GEN-LAST:event_rbYaActionPerformed

    private void rbTidakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTidakActionPerformed
        // TODO add your handling code here:
        PanellamaGaransi.setVisible(false);
    }//GEN-LAST:event_rbTidakActionPerformed

    private void btTambahNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahNamaBarangActionPerformed
        // TODO add your handling code here:
        dialogTambahNamaBarang.setModal(true);
        dialogTambahNamaBarang.setLocation(x, y);
        dialogTambahNamaBarang.setSize(320, 400);
        dialogTambahNamaBarang.show();        
    }//GEN-LAST:event_btTambahNamaBarangActionPerformed

    private void cmbNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNamaBarangActionPerformed
        // TODO add your handling code here:        
        loadNamaBarang();
        //setIdBarang();
    }//GEN-LAST:event_cmbNamaBarangActionPerformed

    private void cmbNamaBarangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbNamaBarangFocusLost
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_cmbNamaBarangFocusLost

    private void cmbTipeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbTipeFocusLost
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_cmbTipeFocusLost

    private void cmbMerekFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbMerekFocusLost
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_cmbMerekFocusLost

    private void cmbNamaBarangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbNamaBarangFocusGained
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_cmbNamaBarangFocusGained

    private void txtHargaModalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHargaModalFocusGained
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_txtHargaModalFocusGained

    private void txtHargaGrosirFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHargaGrosirFocusGained
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_txtHargaGrosirFocusGained

    private void txtHargaEceranFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHargaEceranFocusGained
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_txtHargaEceranFocusGained

    private void JsStokFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JsStokFocusGained
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_JsStokFocusGained

    private void JsstokMinFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JsstokMinFocusGained
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_JsstokMinFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.components.JSpinField JsStok;
    private com.toedter.components.JSpinField JsstokMin;
    private javax.swing.JLabel LblImage;
    private javax.swing.JPanel PanellamaGaransi;
    private javax.swing.JPanel atas;
    private javax.swing.JPanel bawah;
    private javax.swing.JButton btReset;
    private javax.swing.JButton btTambah;
    private javax.swing.JButton btTambahGambar;
    private javax.swing.JButton btTambahItipe;
    private javax.swing.JButton btTambahMerek;
    private javax.swing.JButton btTambahNamaBarang;
    private javax.swing.JButton btTambahSatuan;
    private javax.swing.JButton btTambahSupplier;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbKategori;
    private javax.swing.JComboBox cmbMerek;
    private javax.swing.JComboBox cmbNamaBarang;
    private javax.swing.JComboBox cmbSatuan;
    private javax.swing.JComboBox cmbSupplier;
    private javax.swing.JComboBox cmbTipe;
    private javax.swing.JDialog dialogTambahMerek;
    private javax.swing.JDialog dialogTambahNamaBarang;
    private javax.swing.JDialog dialogTambahSatuan;
    private javax.swing.JDialog dialogTambahSupplier;
    private javax.swing.JDialog dialogTambahTipe;
    private javax.swing.JPanel inputTengah;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.components.JSpinField jsBulan;
    private com.toedter.components.JSpinField jsHari;
    private com.toedter.components.JSpinField jsTahun;
    private Sinarelektronikapp.masterData.merek.view.MerekView2 merekView21;
    private Sinarelektronikapp.masterData.namabarang.view.NamaBarangView namaBarangView1;
    private javax.swing.JPanel panelImage;
    private javax.swing.JPanel panelIsianTengah1;
    private javax.swing.JPanel panelLabelKiri;
    private javax.swing.JPanel panelRB;
    private javax.swing.JPanel panelTambah;
    private javax.swing.JRadioButton rbTidak;
    private javax.swing.JRadioButton rbYa;
    private Sinarelektronikapp.masterData.satuan.view.SatuanView satuanView1;
    private Sinarelektronikapp.masterData.supplier.view.supplierView supplierView1;
    private Sinarelektronikapp.masterData.tipe.view.TipeView tipeView1;
    private javax.swing.JTextField txtHargaEceran;
    private javax.swing.JTextField txtHargaGrosir;
    private javax.swing.JTextField txtHargaModal;
    private javax.swing.JTextField txtIdBarang;
    private javax.swing.JTextField txtIdBarcode;
    private javax.swing.JTextArea txtKet;
    // End of variables declaration//GEN-END:variables



    @Override
    public void onInsert(barang barang) {        
        tabelModelBarang modelBarang=new tabelModelBarang();
        modelBarang.add(barang);
    }

    @Override
    public void onUpdate(barang barang) {

    }

    @Override
    public void onChange(tambahBarangModel model) {
        txtIdBarang.setText(model.getIdBarang());
        txtIdBarcode.setText(model.getIdBarcode());
        txtHargaGrosir.setText(String.valueOf(model.getGrosir()));
        txtHargaEceran.setText(String.valueOf(model.getEceran()));        
        txtKet.setText(model.getKeterangan());
        cmbNamaBarang.setSelectedItem(model.getNamaBarang());        
        jsTahun.setValue((model.getStok()));
        JsstokMin.setValue(model.getStokMinimum());
        cmbMerek.setSelectedItem(model.getMerek());
        cmbSatuan.setSelectedItem(model.getSatuan());
        cmbSupplier.setSelectedItem(model.getSupplier());
        cmbTipe.setSelectedItem(model.getTipe());
    }



}

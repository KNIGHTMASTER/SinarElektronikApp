/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.barangbesar.stokreminder.view;

import Sinarelektronikapp.config.HostName;
import Sinarelektronikapp.config.UserLevel;
import Sinarelektronikapp.masterdata.barangbesar.entity.barang;
import Sinarelektronikapp.masterdata.barangbesar.error.BarangException;
import Sinarelektronikapp.masterdata.barangbesar.model.tabelModelBarang;
import Sinarelektronikapp.masterdata.barangbesar.stokreminder.controller.ReminderController;
import Sinarelektronikapp.masterdata.barangbesar.stokreminder.database.ReminderDatabase;
import Sinarelektronikapp.masterdata.barangbesar.stokreminder.model.ReminderModel;
import Sinarelektronikapp.masterdata.barangbesar.stokreminder.model.event.ReminderListener;
import Sinarelektronikapp.masterdata.barangbesar.stokreminder.service.ReminderDao;
import Sinarelektronikapp.masterdata.barangbesar.view.*;
import Sinarelektronikapp.masterdata.tambahbarang.kecil.view.tambahBarangView;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Fauzi
 */
public class ReminderView extends javax.swing.JPanel implements ReminderListener, ListSelectionListener{

    /**
     * Creates new form ReminderView
     */
    
    ReminderController controller;
    
    tabelModelBarang tabelmodelbarang;
    
    ReminderModel model;
    
    HostName ip = new HostName();
    
    ReminderDatabase database = new ReminderDatabase();
        
    public ReminderView() {

        tabelmodelbarang = new tabelModelBarang();
        
        model=new ReminderModel();
        model.setListener(this);
        
        controller = new ReminderController();
        controller.setModel(model);        
        
        initComponents();

        tabelBarang.getSelectionModel().addListSelectionListener(this);
        tabelBarang.setModel(tabelmodelbarang);
        koneksi();
        loadDatabase();
        loadNamaBarang();
        loadTipe();
        loadMerek();
        loadSupplier();                
        setIdBarang();
        setLevel();
    }    

    public void setLevel(){
        UserLevel userLevel = new UserLevel();
        String levelAktif = userLevel.getUserLevelActive();
        switch(levelAktif){
            case "Pemilik Toko":
                    btReset.setEnabled(true);
                    btUpdate.setEnabled(true);
                break;
            case "Administrator":
                    btReset.setEnabled(true);
                    btUpdate.setEnabled(true);
                break;
            case "Karyawan":
                    btReset.setEnabled(false);
                    btUpdate.setEnabled(false);                
                break;                        
            default:;
        }
    }    
    private void hideUnusableButton(){
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jButton4.setVisible(false);
        jButton5.setVisible(false);
        jButton6.setVisible(false);
        jButton7.setVisible(false);
        jButton8.setVisible(false);
        jButton8.setVisible(false);
        jButton9.setVisible(false);
        jButton10.setVisible(false);
        jButton11.setVisible(false);
    }
    
    public void loadTipe(){
        Statement s = null;
        try {
            s = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rs=s.executeQuery("SELECT namaTipe FROM tipe");
            while (rs.next()) {
                cmbTipe.addItem(rs.getString("namaTipe"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void loadMerek(){
        Statement s = null;
        try {
            s = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rs=s.executeQuery("SELECT namamerek FROM merek");
            while (rs.next()) {
                cmbMerek.addItem(rs.getString("namamerek"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }    
    
    public void loadSupplier(){
        Statement s = null;
        try {
            s = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rs=s.executeQuery("SELECT nama FROM supplier");
            while (rs.next()) {
                cmbSupplier.addItem(rs.getString("nama"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
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
            JOptionPane.showMessageDialog(null, "tidak bisa me-load data nama barangkecil");
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }      
    }  
    
   
    public void loadDatabase(){
        try {
            ReminderDao dao=database.getReminderDao();
            try {
                tabelmodelbarang.setList(dao.selectAllBarang());
            } catch (BarangException ex) {
                Logger.getLogger(ReminderView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReminderView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
//generate getter and setter

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

    public JTable getTabelBarang() {
        return tabelBarang;
    }

    public void setTabelBarang(JTable tabelBarang) {
        this.tabelBarang = tabelBarang;
    }

    public JTextField getTxtKataKunci() {
        return txtKataKunci;
    }

    public void setTxtKataKunci(JTextField txtKataKunci) {
        this.txtKataKunci = txtKataKunci;
    }
       

    final Toolkit toolkit = Toolkit.getDefaultToolkit();
    final Dimension screenSize = toolkit.getScreenSize();
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogUpdate = new javax.swing.JDialog();
        panelAtas = new javax.swing.JPanel();
        panelImage = new javax.swing.JPanel();
        LblImage = new javax.swing.JLabel();
        btTambahGambar = new javax.swing.JButton();
        PanelLabelKiri = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        panelMasuk = new javax.swing.JPanel();
        panelInputTengah = new javax.swing.JPanel();
        txtIdBarang = new javax.swing.JTextField();
        txtIdBarcode = new javax.swing.JTextField();
        cmbNamaBarang = new javax.swing.JComboBox();
        cmbTipe = new javax.swing.JComboBox();
        cmbMerek = new javax.swing.JComboBox();
        txtHargaModal = new javax.swing.JTextField();
        txtHargaGrosir = new javax.swing.JTextField();
        txtHargaEceran = new javax.swing.JTextField();
        JsStok = new com.toedter.components.JSpinField();
        JsstokMin = new com.toedter.components.JSpinField();
        cmbSupplier = new javax.swing.JComboBox();
        panelRB = new javax.swing.JPanel();
        rbYa = new javax.swing.JRadioButton();
        rbTidak = new javax.swing.JRadioButton();
        PanellamaGaransi = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jsTahun = new com.toedter.components.JSpinField();
        jLabel17 = new javax.swing.JLabel();
        jsBulan = new com.toedter.components.JSpinField();
        jLabel18 = new javax.swing.JLabel();
        jsHari = new com.toedter.components.JSpinField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtKet = new javax.swing.JTextArea();
        panelTambah = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btTambahNamaBarang = new javax.swing.JButton();
        btTambahItipe = new javax.swing.JButton();
        btTambahMerek = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        btTambahSupplier = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        panelTombol = new javax.swing.JPanel();
        btReset = new javax.swing.JButton();
        btTambah1 = new javax.swing.JButton();
        dialogPreview = new javax.swing.JDialog();
        lblPreview = new javax.swing.JLabel();
        dialogTambahTipe = new javax.swing.JDialog();
        dialogTambahMerek = new javax.swing.JDialog();
        dialogTambahSupplier = new javax.swing.JDialog();
        dialogTambahSatuan = new javax.swing.JDialog();
        dialogTambah = new javax.swing.JDialog();
        tambahBarangView1 = new tambahBarangView();
        buttonGroup1 = new javax.swing.ButtonGroup();
        dialogTambahNamaBarang = new javax.swing.JDialog();
        namaBarangView1 = new Sinarelektronikapp.masterdata.namabarang.view.NamaBarangView();
        up = new javax.swing.JPanel();
        bgUp = new javax.swing.JPanel();
        kiri = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        kanan = new javax.swing.JPanel();
        atas = new javax.swing.JPanel();
        txtKataKunci = new javax.swing.JTextField();
        btCari = new javax.swing.JButton();
        tengah = new javax.swing.JPanel();
        cmbCari = new javax.swing.JComboBox();
        bawah = new javax.swing.JPanel();
        cmbUrut = new javax.swing.JComboBox();
        middle = new javax.swing.JPanel();
        panelTengah = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();
        bottom = new javax.swing.JPanel();
        PanewlBottom2 = new javax.swing.JPanel();
        btUpdate = new javax.swing.JButton();
        btPreview = new javax.swing.JButton();

        dialogUpdate.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogUpdate.setTitle("Update Barang");
        dialogUpdate.setMinimumSize(new java.awt.Dimension(580, 584));

        panelAtas.setLayout(new java.awt.BorderLayout());

        panelImage.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelImage.setLayout(new java.awt.BorderLayout());
        panelImage.add(LblImage, java.awt.BorderLayout.CENTER);

        btTambahGambar.setText("Gambar");
        btTambahGambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahGambarActionPerformed(evt);
            }
        });
        panelImage.add(btTambahGambar, java.awt.BorderLayout.PAGE_END);

        panelAtas.add(panelImage, java.awt.BorderLayout.EAST);

        PanelLabelKiri.setLayout(new java.awt.GridLayout(14, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Id Barang :");
        PanelLabelKiri.add(jLabel1);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Id Barcode Barang :");
        PanelLabelKiri.add(jLabel2);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nama Barang :");
        PanelLabelKiri.add(jLabel3);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tipe :");
        PanelLabelKiri.add(jLabel4);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Merek :");
        PanelLabelKiri.add(jLabel5);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Harga Modal :");
        PanelLabelKiri.add(jLabel20);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Harga Grosir :");
        PanelLabelKiri.add(jLabel21);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Harga Eceran :");
        PanelLabelKiri.add(jLabel11);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Stok :");
        PanelLabelKiri.add(jLabel7);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Stok Minimum :");
        PanelLabelKiri.add(jLabel8);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Supplier :");
        PanelLabelKiri.add(jLabel9);

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Garansi :");
        PanelLabelKiri.add(jLabel19);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Lama Garansi :");
        PanelLabelKiri.add(jLabel16);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Keterangan :");
        PanelLabelKiri.add(jLabel10);

        panelAtas.add(PanelLabelKiri, java.awt.BorderLayout.WEST);

        panelMasuk.setLayout(new java.awt.BorderLayout());

        panelInputTengah.setLayout(new java.awt.GridLayout(14, 0));

        txtIdBarang.setEditable(false);
        panelInputTengah.add(txtIdBarang);

        txtIdBarcode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIdBarcodeFocusGained(evt);
            }
        });
        panelInputTengah.add(txtIdBarcode);

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
        panelInputTengah.add(cmbNamaBarang);

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
        panelInputTengah.add(cmbTipe);

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
        panelInputTengah.add(cmbMerek);

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
        panelInputTengah.add(txtHargaModal);

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
        panelInputTengah.add(txtHargaGrosir);

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
        panelInputTengah.add(txtHargaEceran);
        panelInputTengah.add(JsStok);
        panelInputTengah.add(JsstokMin);

        cmbSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSupplierActionPerformed(evt);
            }
        });
        cmbSupplier.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbSupplierFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbSupplierFocusLost(evt);
            }
        });
        panelInputTengah.add(cmbSupplier);

        panelRB.setLayout(new java.awt.GridLayout(1, 2));

        rbYa.setText("Ya");
        rbYa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbYaActionPerformed(evt);
            }
        });
        panelRB.add(rbYa);

        rbTidak.setText("Tidak");
        rbTidak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTidakActionPerformed(evt);
            }
        });
        panelRB.add(rbTidak);

        panelInputTengah.add(panelRB);

        PanellamaGaransi.setLayout(new java.awt.GridLayout(1, 6));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Tahun :");
        PanellamaGaransi.add(jLabel15);
        PanellamaGaransi.add(jsTahun);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("bulan :");
        PanellamaGaransi.add(jLabel17);
        PanellamaGaransi.add(jsBulan);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("hari :");
        PanellamaGaransi.add(jLabel18);
        PanellamaGaransi.add(jsHari);

        panelInputTengah.add(PanellamaGaransi);

        txtKet.setColumns(20);
        txtKet.setRows(5);
        jScrollPane2.setViewportView(txtKet);

        panelInputTengah.add(jScrollPane2);

        panelMasuk.add(panelInputTengah, java.awt.BorderLayout.CENTER);

        panelTambah.setLayout(new java.awt.GridLayout(14, 0));

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

        panelMasuk.add(panelTambah, java.awt.BorderLayout.EAST);

        panelAtas.add(panelMasuk, java.awt.BorderLayout.CENTER);

        dialogUpdate.getContentPane().add(panelAtas, java.awt.BorderLayout.CENTER);

        panelTombol.setLayout(new java.awt.GridLayout(1, 2));

        btReset.setMnemonic('R');
        btReset.setText("Reset");
        btReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetActionPerformed(evt);
            }
        });
        panelTombol.add(btReset);

        btTambah1.setMnemonic('T');
        btTambah1.setText("Update");
        btTambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambah1ActionPerformed(evt);
            }
        });
        panelTombol.add(btTambah1);

        dialogUpdate.getContentPane().add(panelTombol, java.awt.BorderLayout.PAGE_END);

        dialogPreview.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogPreview.setTitle("Preview");
        dialogPreview.setMinimumSize(new java.awt.Dimension(165, 170));
        dialogPreview.setModal(true);
        dialogPreview.getContentPane().add(lblPreview, java.awt.BorderLayout.CENTER);

        dialogTambahTipe.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogTambahTipe.setTitle("Tambah Tipe Barang");

        dialogTambahMerek.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogTambahMerek.setTitle("Tambah Merek Barang");

        dialogTambahSupplier.setTitle("Tambah Supplier");

        dialogTambahSatuan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogTambahSatuan.setTitle("Tambah Satuan");

        dialogTambah.setTitle("Tambah Barang");
        dialogTambah.getContentPane().add(tambahBarangView1, java.awt.BorderLayout.LINE_END);

        dialogTambahNamaBarang.getContentPane().add(namaBarangView1, java.awt.BorderLayout.CENTER);

        setLayout(new java.awt.BorderLayout());

        up.setLayout(new java.awt.BorderLayout());

        bgUp.setLayout(new java.awt.BorderLayout());

        kiri.setOpaque(false);
        kiri.setLayout(new java.awt.GridLayout(3, 1));

        jLabel12.setText("Kata Kunci :");
        kiri.add(jLabel12);

        jLabel13.setText("Cari :");
        kiri.add(jLabel13);

        jLabel14.setText("Urutkan :");
        kiri.add(jLabel14);

        bgUp.add(kiri, java.awt.BorderLayout.WEST);

        kanan.setOpaque(false);
        kanan.setLayout(new java.awt.GridLayout(3, 0));

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
                .addComponent(txtKataKunci, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCari, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        atasLayout.setVerticalGroup(
            atasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtKataKunci)
            .addComponent(btCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        kanan.add(atas);

        tengah.setOpaque(false);
        tengah.setLayout(new java.awt.BorderLayout());

        cmbCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id barangkecil", "id barcode", "nama barangkecil", "tipe", "merek", "harga", "satuan", "stok", "stok minimum", "supplier", "keterangan" }));
        cmbCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCariActionPerformed(evt);
            }
        });
        tengah.add(cmbCari, java.awt.BorderLayout.CENTER);

        kanan.add(tengah);

        bawah.setOpaque(false);
        bawah.setLayout(new java.awt.BorderLayout());

        cmbUrut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id barangkecil", "id barcode", "nama barangkecil", "tipe", "merek", "harga", "satuan", "stok", "stok minimum", "supplier", "keterangan" }));
        cmbUrut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUrutActionPerformed(evt);
            }
        });
        bawah.add(cmbUrut, java.awt.BorderLayout.CENTER);

        kanan.add(bawah);

        bgUp.add(kanan, java.awt.BorderLayout.CENTER);

        up.add(bgUp, java.awt.BorderLayout.CENTER);

        add(up, java.awt.BorderLayout.PAGE_START);

        middle.setLayout(new java.awt.BorderLayout());

        panelTengah.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

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
        tabelBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabelBarangMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelBarangMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelBarang);

        panelTengah.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        middle.add(panelTengah, java.awt.BorderLayout.CENTER);

        add(middle, java.awt.BorderLayout.CENTER);

        bottom.setLayout(new java.awt.BorderLayout());

        PanewlBottom2.setLayout(new java.awt.GridLayout(1, 4));

        btUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/update2.png"))); // NOI18N
        btUpdate.setMnemonic('U');
        btUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateActionPerformed(evt);
            }
        });
        PanewlBottom2.add(btUpdate);

        btPreview.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/preview.png"))); // NOI18N
        btPreview.setMnemonic('P');
        btPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPreviewActionPerformed(evt);
            }
        });
        PanewlBottom2.add(btPreview);

        bottom.add(PanewlBottom2, java.awt.BorderLayout.CENTER);

        add(bottom, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    
    public void copyFile(){
        File file=new File(pathGambar);
        File dir=new File("dir");        
            if(!dir.exists()){
                dir.mkdir();
            }            
        File directory = new File("");
        String target=directory.getAbsolutePath()+"\\src\\SinarElektronikApp\\imageresource";
        //memberi alamat gambar pada database sesuai dengan data yang telah dipindah ke user directory
        pathGambar = target+"\\"+file.getName();
        FileChannel in=null;
        FileChannel out=null;
        try {
            in =new FileInputStream(file).getChannel();
            File outFile =new File(target, file.getName());
            out =new FileOutputStream(outFile).getChannel();
            try {
                in.transferTo(0, in.size(), out);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } catch (FileNotFoundException ex) {            
            JOptionPane.showMessageDialog(null, ex);
        }finally{
            if(in!=null){
                try {
                    in.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
            if(out!=null){
                try {
                    out.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }                            
    }
    
    
    public void updateBarangExec(){        
        String QUERY = "UPDATE barangbesar SET idbarang = ?, idbarcode = ?, namabarang = ?, tipe=?, merek = ?, modal=?, grosir =?, eceran = ?, stok = ?, stok_minimum = ?, supplier = ?, keterangan = ?, gambar = ?, garansi=?, lamagaransi=? WHERE idbarang =?";
        String QUERY2 = "UPDATE barangbesar SET idbarang = ?, idbarcode = ?, namabarang = ?, tipe=?, merek = ?, modal=?, grosir =?, eceran = ?, stok = ?, stok_minimum = ?, supplier = ?, keterangan = ?, garansi=?, lamagaransi=? WHERE idbarang = ?";
        if(pathGambar == null || pathGambar.trim().equals("")){                        
           try {
                PreparedStatement ps=conn.prepareStatement(QUERY2);
                ps.setString(1, txtIdBarang.getText());
                ps.setString(2, txtIdBarcode.getText());
                ps.setString(3, cmbNamaBarang.getSelectedItem().toString());
                ps.setString(4, cmbTipe.getSelectedItem().toString());
                ps.setString(5, cmbMerek.getSelectedItem().toString());
                ps.setString(6, txtHargaModal.getText());
                ps.setString(7, txtHargaGrosir.getText());
                ps.setString(8, txtHargaEceran.getText());
                ps.setString(9, String.valueOf(JsStok.getValue()));
                ps.setString(10, String.valueOf(JsstokMin.getValue()));
                ps.setString(11, cmbSupplier.getSelectedItem().toString());
                ps.setString(12, txtKet.getText());
                /*try {
                    ps.setBlob(13, new FileInputStream(gambar));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
                }*/
            
            String garansi = "";
            int hari =  0, bulan = 0, tahun = 0;
            hari =  jsHari.getValue();
            bulan = jsBulan.getValue() * 30;
            tahun = jsTahun.getValue() * 360;
        
            int lamaGaransi = (hari+bulan+tahun);
        
            if(rbTidak.isSelected()){
                garansi = "tidak";
                lamaGaransi = 0;
            }else if(rbYa.isSelected()){
                garansi = "ya";
            }
                ps.setString(13, garansi);
                ps.setString(14, String.valueOf(lamaGaransi));
                ps.setString(15, tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 0).toString());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Update barangkecil berhasil");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Update barangkecil gagal karena = "+ex);
            }            
        }        
        else if(pathGambar!=null || !(pathGambar.trim().equals(""))){
            //ambil gambar awal
            Blob gambarAwal =  null;  
            byte [] data = null ;
            try{
                Statement s = conn.createStatement();
                ResultSet rs = s.executeQuery("SELECT gambar from barangbesar where idbarang='"+txtIdBarang.getText()+"'");
                if(rs.next()){
                    gambarAwal = rs.getBlob("gambar");
                    data = gambarAwal.getBytes(1, (int)gambarAwal.length());
                }
            }catch(SQLException exception){
                
            }        
            //jika gambar diubah || gambfar baru diisi
            ImageIcon iconUpdate=new ImageIcon(data);            
           try {
                File gambar = new File(pathGambar);
                PreparedStatement ps=conn.prepareStatement(QUERY);
                ps.setString(1, txtIdBarang.getText());
                ps.setString(2, txtIdBarcode.getText());
                ps.setString(3, cmbNamaBarang.getSelectedItem().toString());
                ps.setString(4, cmbTipe.getSelectedItem().toString());
                ps.setString(5, cmbMerek.getSelectedItem().toString());
                ps.setString(6, txtHargaModal.getText());
                ps.setString(7, txtHargaGrosir.getText());
                ps.setString(8, txtHargaEceran.getText());       
                ps.setString(9, String.valueOf(JsStok.getValue()));
                ps.setString(10, String.valueOf(JsstokMin.getValue()));
                ps.setString(11, cmbSupplier.getSelectedItem().toString());
                ps.setString(12, txtKet.getText());
                  try {
                    ps.setBlob(13, new FileInputStream(gambar));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
                }
            String garansi = "";
            int hari =  0, bulan = 0, tahun = 0;
            hari =  jsHari.getValue();
            bulan = jsBulan.getValue() * 30;
            tahun = jsTahun.getValue() * 360;
        
            int lamaGaransi = (hari+bulan+tahun);
        
            if(rbTidak.isSelected()){
                garansi = "tidak";
                lamaGaransi = 0;
            }else if(rbYa.isSelected()){
                garansi = "ya";
            }
                ps.setString(14, garansi);
                ps.setString(15, String.valueOf(lamaGaransi));
                ps.setString(16, tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 0).toString());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Update barangkecil berhasil");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Update barangkecil gagal karena = "+ex);
            }                        
        }
        btCari.doClick();             
    }
    
    
    String pathGambar = "";

    public String getPathGambar() {
        return pathGambar;
    }

    public void setPathGambar(String pathGambar) {
        this.pathGambar = pathGambar;
    }
    
    private void txtKataKunciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKataKunciActionPerformed
        // TODO add your handling code here:
        btCari.doClick();
    }//GEN-LAST:event_txtKataKunciActionPerformed

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        try {
            // TODO add your handling code here:
     controller.cari(this, this);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ReminderView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BarangException ex) {
            java.util.logging.Logger.getLogger(ReminderView.class.getName()).log(Level.SEVERE, null, ex);
        }
            this.setSize(getWidth(), getHeight());
    }//GEN-LAST:event_btCariActionPerformed

    private void cmbCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCariActionPerformed

    private void cmbUrutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUrutActionPerformed
        try {
            // TODO add your handling code here:
        controller.sort(this);
        } catch (SQLException ex) {
            Logger.getLogger(ReminderView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BarangException ex) {
            Logger.getLogger(ReminderView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmbUrutActionPerformed

    private void tabelBarangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarangMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelBarangMousePressed

    private void tabelBarangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarangMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelBarangMouseReleased

    private void btUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateActionPerformed
        updateManual();
    }//GEN-LAST:event_btUpdateActionPerformed

    private void btPreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPreviewActionPerformed
        // TODO add your handling code here:
        preview();
    }//GEN-LAST:event_btPreviewActionPerformed

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
                JOptionPane.showMessageDialog(null, "error karena = "+ex);
            }
            LblImage.setSize(new Dimension(150, 150));
            ImageIcon gambar=new ImageIcon(thumb);
            LblImage.setIcon(gambar);
        }
    }//GEN-LAST:event_btTambahGambarActionPerformed

     private void setIdBarang(){
        String idBarang = cmbMerek.getSelectedItem().toString()+cmbTipe.getSelectedItem().toString()+"-"+cmbNamaBarang.getSelectedItem().toString();
        txtIdBarang.setText(idBarang);
    }
     
    private void txtIdBarcodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdBarcodeFocusGained
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_txtIdBarcodeFocusGained
    
    
    private void cmbNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNamaBarangActionPerformed
        // TODO add your handling code here:
        loadNamaBarang();
    }//GEN-LAST:event_cmbNamaBarangActionPerformed

    private void cmbNamaBarangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbNamaBarangFocusGained
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_cmbNamaBarangFocusGained

    private void cmbNamaBarangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbNamaBarangFocusLost
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_cmbNamaBarangFocusLost

    private void cmbTipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipeActionPerformed
        // TODO add your handling code here:
        loadTipe();
    }//GEN-LAST:event_cmbTipeActionPerformed

    private void cmbTipeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbTipeFocusGained
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_cmbTipeFocusGained

    private void cmbTipeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbTipeFocusLost
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_cmbTipeFocusLost

    private void cmbMerekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMerekActionPerformed
        // TODO add your handling code here:
        loadMerek();
    }//GEN-LAST:event_cmbMerekActionPerformed

    private void cmbMerekFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbMerekFocusGained
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_cmbMerekFocusGained

    private void cmbMerekFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbMerekFocusLost
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_cmbMerekFocusLost

    private void txtHargaModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaModalActionPerformed
        // TODO add your handling code here:
        if(!txtHargaModal.getText().matches("[0-9]*")){
            JOptionPane.showMessageDialog(null, "harga harus angka");
            txtHargaModal.setText("0");
            txtHargaModal.requestFocus();
        }
    }//GEN-LAST:event_txtHargaModalActionPerformed

    private void txtHargaModalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHargaModalFocusGained
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_txtHargaModalFocusGained

    private void txtHargaModalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHargaModalFocusLost
        // TODO add your handling code here:

        if(!txtHargaModal.getText().matches("[0-9]*")){
            JOptionPane.showMessageDialog(null, "harga harus angka");
            txtHargaModal.setText("0");
            txtHargaModal.requestFocus();
        }

        setIdBarang();
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

    private void txtHargaGrosirFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHargaGrosirFocusGained
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_txtHargaGrosirFocusGained

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
        setIdBarang();
    }//GEN-LAST:event_txtHargaGrosirFocusLost

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
            txtHargaEceran.setText(String.valueOf(hargaGrosir+1));
            txtHargaEceran.requestFocus();
        }
    }//GEN-LAST:event_txtHargaEceranActionPerformed

    private void txtHargaEceranFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHargaEceranFocusGained
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_txtHargaEceranFocusGained

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
            txtHargaEceran.setText(String.valueOf(hargaGrosir+1));
            txtHargaEceran.requestFocus();
        }
        setIdBarang();
    }//GEN-LAST:event_txtHargaEceranFocusLost

    private void cmbSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSupplierActionPerformed
        // TODO add your handling code here:
        loadSupplier();
    }//GEN-LAST:event_cmbSupplierActionPerformed

    private void cmbSupplierFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSupplierFocusGained
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_cmbSupplierFocusGained

    private void cmbSupplierFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSupplierFocusLost
        // TODO add your handling code here:
        setIdBarang();
    }//GEN-LAST:event_cmbSupplierFocusLost

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
        int x = (screenSize.width - this.WIDTH) / 4;
        int y = (screenSize.height - this.HEIGHT) /5;
        dialogTambahNamaBarang.setModal(true);
        dialogTambahNamaBarang.setLocation(x, y);
        dialogTambahNamaBarang.setSize(650, 550);
        dialogTambahNamaBarang.show();
    }//GEN-LAST:event_btTambahNamaBarangActionPerformed

    private void btTambahItipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahItipeActionPerformed
        // TODO add your handling code here:
        int x = (screenSize.width - this.WIDTH) / 4;
        int y = (screenSize.height - this.HEIGHT) /5;
        dialogTambahTipe.setModal(true);
        dialogTambahTipe.setLocation(x, y);
        dialogTambahTipe.setSize(320, 400);
        dialogTambahTipe.show();
    }//GEN-LAST:event_btTambahItipeActionPerformed

    private void btTambahMerekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahMerekActionPerformed
        // TODO add your handling code here:
        int x = (screenSize.width - this.WIDTH) / 4;
        int y = (screenSize.height - this.HEIGHT) /5;
        dialogTambahMerek.setLocation(x, y);
        dialogTambahMerek.setSize(320, 400);
        dialogTambahMerek.setModal(true);
        dialogTambahMerek.show();
    }//GEN-LAST:event_btTambahMerekActionPerformed

    private void btTambahSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahSupplierActionPerformed
        // TODO add your handling code here:
        int x = (screenSize.width - this.WIDTH) / 4;
        int y = (screenSize.height - this.HEIGHT) /8;
        dialogTambahSupplier.setLocation(x, y);
        dialogTambahSupplier.setSize(700, 580);
        dialogTambahSupplier.setModal(true);
        dialogTambahSupplier.show();
    }//GEN-LAST:event_btTambahSupplierActionPerformed

    private void btResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetActionPerformed
        // TODO add your handling code here:
        /*controller.resetTambahBarang();
        try {
            resetManual();
        } catch (SQLException ex) {
            Logger.getLogger(tambahBarangView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (barangException ex) {
            Logger.getLogger(tambahBarangView.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_btResetActionPerformed

    private void btTambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambah1ActionPerformed
        updateBarangExec();
    }//GEN-LAST:event_btTambah1ActionPerformed
    Blob gambarRetrieve ;
    ImageIcon iconRetrieve = null;
    public void showUpdateManual(){
        pathGambar = "";
        String idbarang = tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 0).toString();
        String query = "SELECT * from barangbesar where idbarang = '"+idbarang+"'";
        String idbarcode = "";
        String namaBarang = "";
        String tipe = "";
        String merek = "";
        String hargamodal = "";
        String grosir = "";
        String eceran = "";
        String stok = "";
        String stok_min = "";
        String supplier ="";
        String keterangan="";
        String garansi = "";
        String lamaGaransi = "";
        
        byte [] dataGambarRetrieve = null ;
        try{
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            if(rs.next()){
                idbarcode = rs.getString("idbarcode");
                namaBarang = rs.getString("namabarang");
                tipe = rs.getString("tipe");
                merek = rs.getString("merek");
                hargamodal = String.valueOf(rs.getInt("modal"));
                grosir = String.valueOf(rs.getInt("grosir"));
                eceran = String.valueOf(rs.getInt("eceran"));
                stok = String.valueOf(rs.getInt("stok"));
                stok_min = String.valueOf(rs.getInt("stok_minimum"));
                supplier = rs.getString("supplier");
                keterangan = rs.getString("keterangan");
                garansi = rs.getString("garansi");
                lamaGaransi = String.valueOf(rs.getInt("lamagaransi"));
                gambarRetrieve = rs.getBlob("gambar");
                if (gambarRetrieve != null) {
                    dataGambarRetrieve = gambarRetrieve.getBytes(1, (int)gambarRetrieve.length());
                }
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam mengambil ulang data barangkecil yang akan diupdate karena "+exception, "peringatan", JOptionPane.WARNING_MESSAGE);
       }      
        hideUnusableButton();
        txtIdBarang.setText(idbarang);
        txtIdBarcode.setText(idbarcode);
        cmbNamaBarang.setSelectedItem(namaBarang);
        cmbTipe.setSelectedItem(tipe);
        cmbMerek.setSelectedItem(merek);
        txtHargaModal.setText(hargamodal);
        txtHargaGrosir.setText(grosir);
        txtHargaEceran.setText(eceran);        
        JsStok.setValue(Integer.valueOf(stok));
        JsstokMin.setValue(Integer.valueOf(stok_min));
        cmbSupplier.setSelectedItem(supplier);
        txtKet.setText(keterangan);
        
        if(garansi.equals("ya")){
            rbYa.setSelected(true);
        }else{
            rbTidak.setSelected(true);
        }
        
        int lgaransi = Integer.parseInt(lamaGaransi);
        int year = lgaransi / 360;
        int yearRest = lgaransi % 360;
        int month = yearRest % 30;
        int monthRest = yearRest / 30;
        int day = monthRest;

        jsTahun.setValue(year);
        jsBulan.setValue(month);
        jsHari.setValue(day);
        
        if (dataGambarRetrieve != null) {
            if (dataGambarRetrieve.length > 0) {
                iconRetrieve=new ImageIcon(dataGambarRetrieve);
                Image img = iconRetrieve.getImage();
                Image newImg = img.getScaledInstance(150, 150, img.SCALE_FAST);
                iconRetrieve = new ImageIcon(newImg);
                LblImage.setIcon(iconRetrieve);
            }
        }else {
            LblImage.setIcon(null);
        }
        int x = (screenSize.width - this.WIDTH) / 4;
        int y = (screenSize.height - this.HEIGHT) /5;                   
        dialogUpdate.setSize(900, 600);
        dialogUpdate.setLocation(x, y);
        dialogUpdate.setModal(true);
        dialogUpdate.show(true);
        try {
            controller.sort(this);
        } catch (SQLException ex) {
            Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BarangException ex) {
            Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadDatabase();
    }
    
    public void updateManual(){
        if(tabelBarang.getSelectedRowCount()<1){
            JOptionPane.showMessageDialog(null, "Pilih data terlebih dahulu");
        }else{
            showUpdateManual();
        }
    };
    
    
    public String getPath(String id){        
        String pathGambar = "";
        String pathSQL = "SELECT pathgambar from barangbesar WHERE idbarang = '"+id+"'";
        try{
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(pathSQL);
            if(rs.next()){
                pathGambar = rs.getString("pathgambar");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal get path karena = "+e);
        }
        return pathGambar;
    }
    
    public void deleteManual(){        
        if(tabelBarang.getSelectedRowCount()<1){
            JOptionPane.showMessageDialog(null, "Pilih data terlebih dahulu");
            return;
        }else{
            String id=tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 0).toString();            
            String path = getPath(id);
            if(JOptionPane.showConfirmDialog(null, "Anda yakin akan menghapus barangkecil dengan id "+id+" ?") == JOptionPane.YES_OPTION){
            try {
                PreparedStatement ps=conn.prepareStatement("DELETE from barangbesar WHERE idbarang = ?");
                ps.setString(1, id);                
                if(path==null || path.trim().equals("")){
                    
                }else{
                    //hapus image barangkecil
                    new File(path).delete();
                }
                ps.executeUpdate();
                
                //urutkan setelah delete                                
                try {
                    controller.sort(this);
                } catch (BarangException ex) {
                    Logger.getLogger(ReminderView.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ReminderView.class.getName()).log(Level.SEVERE, null, ex);
            }                
            }
        }                
    };
    
    
    Connection conn = null;
	
    public void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+ip.getIpServer()+"/sinarelektronik?;", "root", "P@ssw0rd");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error koneksi pada barangkecil view karena = "+ex);
        }
    }
    
    
    private void preview(){
        byte [] data = null ;
        Blob gambar = null ;
        if(tabelBarang.getSelectedRowCount()!=0){
            try {
                String idBarang=tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 0).toString();
                //koneksi();                
                try{
                    Statement s=conn.createStatement();
                    ResultSet rs=s.executeQuery("select gambar from barangbesar where idbarang='"+idBarang+"'");
                    if(rs.next()){
                        gambar = rs.getBlob("gambar");
                    }
                }
                catch(SQLException exception){
                    JOptionPane.showMessageDialog(null, exception);
                }
                if(gambar != null ){
                    data = gambar.getBytes(1, (int)gambar.length());
                    ImageIcon icon=new ImageIcon(data);
                    Image img = icon.getImage();
                    Image newImg = img.getScaledInstance(400, 400, img.SCALE_FAST);
                    icon = new ImageIcon(newImg);
                    lblPreview.setIcon(icon);
                    dialogPreview.setLocationRelativeTo(null);
                    dialogPreview.setSize(410, 410);
                    dialogPreview.show(true);                        
                }else{
                    JOptionPane.showMessageDialog(null, "gambar tidak tersedia");
                }
                } catch (SQLException ex) {
                    Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        else{
            JOptionPane.showMessageDialog(null, "pilih data terlebih dahulu");
            return;            
        }    
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.components.JSpinField JsStok;
    private com.toedter.components.JSpinField JsstokMin;
    private javax.swing.JLabel LblImage;
    private javax.swing.JPanel PanelLabelKiri;
    private javax.swing.JPanel PanellamaGaransi;
    private javax.swing.JPanel PanewlBottom2;
    private javax.swing.JPanel atas;
    private javax.swing.JPanel bawah;
    private javax.swing.JPanel bgUp;
    private javax.swing.JPanel bottom;
    private javax.swing.JButton btCari;
    private javax.swing.JButton btPreview;
    private javax.swing.JButton btReset;
    private javax.swing.JButton btTambah1;
    private javax.swing.JButton btTambahGambar;
    private javax.swing.JButton btTambahItipe;
    private javax.swing.JButton btTambahMerek;
    private javax.swing.JButton btTambahNamaBarang;
    private javax.swing.JButton btTambahSupplier;
    private javax.swing.JButton btUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbCari;
    private javax.swing.JComboBox cmbMerek;
    private javax.swing.JComboBox cmbNamaBarang;
    private javax.swing.JComboBox cmbSupplier;
    private javax.swing.JComboBox cmbTipe;
    private javax.swing.JComboBox cmbUrut;
    private javax.swing.JDialog dialogPreview;
    private javax.swing.JDialog dialogTambah;
    private javax.swing.JDialog dialogTambahMerek;
    private javax.swing.JDialog dialogTambahNamaBarang;
    private javax.swing.JDialog dialogTambahSatuan;
    private javax.swing.JDialog dialogTambahSupplier;
    private javax.swing.JDialog dialogTambahTipe;
    private javax.swing.JDialog dialogUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.components.JSpinField jsBulan;
    private com.toedter.components.JSpinField jsHari;
    private com.toedter.components.JSpinField jsTahun;
    private javax.swing.JPanel kanan;
    private javax.swing.JPanel kiri;
    private javax.swing.JLabel lblPreview;
    private javax.swing.JPanel middle;
    private Sinarelektronikapp.masterdata.namabarang.view.NamaBarangView namaBarangView1;
    private javax.swing.JPanel panelAtas;
    private javax.swing.JPanel panelImage;
    private javax.swing.JPanel panelInputTengah;
    private javax.swing.JPanel panelMasuk;
    private javax.swing.JPanel panelRB;
    private javax.swing.JPanel panelTambah;
    private javax.swing.JPanel panelTengah;
    private javax.swing.JPanel panelTombol;
    private javax.swing.JRadioButton rbTidak;
    private javax.swing.JRadioButton rbYa;
    private javax.swing.JTable tabelBarang;
    private tambahBarangView tambahBarangView1;
    private javax.swing.JPanel tengah;
    private javax.swing.JTextField txtHargaEceran;
    private javax.swing.JTextField txtHargaGrosir;
    private javax.swing.JTextField txtHargaModal;
    private javax.swing.JTextField txtIdBarang;
    private javax.swing.JTextField txtIdBarcode;
    private javax.swing.JTextField txtKataKunci;
    private javax.swing.JTextArea txtKet;
    private javax.swing.JPanel up;
    // End of variables declaration//GEN-END:variables

  
    @Override
    public void onInsert(barang barang) {
        tabelmodelbarang.add(barang);
    }

    @Override
    public void onUpdate(barang barang) {
        int index = tabelBarang.getSelectedRow();
        tabelmodelbarang.set(index, barang);
    }

    @Override
    public void onDelete() {
        int index = tabelBarang.getSelectedRow();
        tabelmodelbarang.remove(index);
    }

    @Override
    public void onSearch(List list) {
        tabelmodelbarang.setList(list);
    }

    @Override
    public void onSort(List list) {
        tabelmodelbarang.setList(list);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        try{
            barang barang=tabelmodelbarang.get(tabelBarang.getSelectedRow());
            //cmbCari.setSelectedItem(barangkecil.get);
        }catch(IndexOutOfBoundsException ioobe){
            
        }
    }

    @Override
    public void onChange(ReminderModel model) {
        txtKataKunci.setText(model.getCari());
        cmbCari.setSelectedItem(model.getCari());
        cmbUrut.setSelectedItem(model.getCmbsort());
    }
}

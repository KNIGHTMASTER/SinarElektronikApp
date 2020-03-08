package Sinarelektronikapp.view;

import Sinarelektronikapp.AppConstant;
import Sinarelektronikapp.util.loading;
import Sinarelektronikapp.transaction.barangbesar.view.JIFTransaksiPenjualanBB;
import Sinarelektronikapp.transaction.barangkecil.view.JIFManajemenTransaksiPenjualan;
import Sinarelektronikapp.penjualan.barangbesar.view.JIFKasirView2;
import Sinarelektronikapp.retur.barangbesar.view.JIFPRosesReturBB;
import Sinarelektronikapp.retur.barangbesar.view.JIFReturViewBarangBesar;
import Sinarelektronikapp.transfer.barangbesar.view.JIFTransferBB;
import Sinarelektronikapp.chart.view.JIFChartView;
import Sinarelektronikapp.config.ActiveUser;
import Sinarelektronikapp.config.ApplicationMode;
import Sinarelektronikapp.config.InternetProtocol;
import Sinarelektronikapp.config.UserLevel;
import Sinarelektronikapp.inventory.barangkecil.view.JIFInventory;
import Sinarelektronikapp.inventory.barangbesar.view.JIFInventoryBB;
import Sinarelektronikapp.masterdata.barangkecil.stokreminder.view.JIFReminderView;
import Sinarelektronikapp.masterdata.barangkecil.view.JIFBarangView;
import Sinarelektronikapp.masterdata.barangkecil.view.JIFHistoryBarang;
import Sinarelektronikapp.masterdata.barangkecil.view.JIFUpdateGlobalBK;
import Sinarelektronikapp.masterdata.barangbesar.stokreminder.view.JIFReminderView2;
import Sinarelektronikapp.masterdata.barangbesar.view.JIFBarangBesar;
import Sinarelektronikapp.masterdata.barangbesar.view.JIFHistoryBarangBesar;
import Sinarelektronikapp.masterdata.barangbesar.view.JIFUpdateGlobalBB;
import Sinarelektronikapp.masterdata.barangtoko.view.JIFBarangToko;
import Sinarelektronikapp.masterdata.merek.view.JIFMerekView;
import Sinarelektronikapp.masterdata.namabarang.view.JIFNamaBarang;
import Sinarelektronikapp.masterdata.pelanggan.view.JIFPelangganView;
import Sinarelektronikapp.masterdata.itemforbonus.barangbesar.view.JIFBarangBonusKaryawanBBView;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.view.JIFBarangBonusKaryawanBKView;
import Sinarelektronikapp.masterdata.itemforbonus.setupbonus.JIFSetupBonusView;
import Sinarelektronikapp.masterdata.penjual.barangbesar.view.JIFPenjualBarangBesarView;
import Sinarelektronikapp.masterdata.penjual.barangkecil.view.JIFPenjualBKView;
import Sinarelektronikapp.masterdata.report.view.JIFPriceListReport;
import Sinarelektronikapp.masterdata.report.view.JIFREportTransferBB;
import Sinarelektronikapp.masterdata.report.view.JIFReportInsentif;
import Sinarelektronikapp.masterdata.report.view.JIFReportInsentifBarangKecil;
import Sinarelektronikapp.masterdata.report.view.JIFReportInventory;
import Sinarelektronikapp.masterdata.report.view.JIFReportInventoryBarangBesar;
import Sinarelektronikapp.masterdata.report.view.JIFReportMaster;
import Sinarelektronikapp.masterdata.report.view.JIFReportPengembalianBarangBesar;
import Sinarelektronikapp.masterdata.report.view.JIFReportPengembalianBarangKecilSupplier;
import Sinarelektronikapp.masterdata.report.view.JIFReportProfit;
import Sinarelektronikapp.masterdata.report.view.JIFReportProfitBarangBesar;
import Sinarelektronikapp.masterdata.report.view.JIFReportRetur;
import Sinarelektronikapp.masterdata.report.view.JIFReportReturBarangBesar;
import Sinarelektronikapp.masterdata.report.view.JIFReportTransaksi;
import Sinarelektronikapp.masterdata.report.view.JIFReportTransaksiBarangBesar;
import Sinarelektronikapp.masterdata.satuan.view.JIFSatuanVIew;
import Sinarelektronikapp.masterdata.supplier.view.JIFSupplierView;
import Sinarelektronikapp.masterdata.tipe.view.JIFTipeView;
import Sinarelektronikapp.masterdata.user.view.JIFUserView;
import Sinarelektronikapp.penjualan.barangkecil.view.JIFKasirView;
import Sinarelektronikapp.retur.barangkecil.view.JIFPRosesRetur2;
import Sinarelektronikapp.retur.barangkecil.view.JIFReturView2;
import Sinarelektronikapp.util.AES;
//import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
//import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author Fauzi
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    InternetProtocol ip;
    ApplicationMode applicationMode;

    public MainFrame() {
        applicationMode = new ApplicationMode();
        ip = new InternetProtocol();
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        //truncateActiveUser();
        Properties props = new Properties();
        props.put("logoString", "brand new");
        setLoadAwal();
        //activeUser.deleteUserConfig();
        setIconFrame();
        setCursorButton();        
    }

    public void setCursorButton() {
        Cursor c = new Cursor(Cursor.HAND_CURSOR);
        //left panel
        btDashboard.setCursor(c);
        btMaster.setCursor(c);
        btTransaksi.setCursor(c);
        btReport.setCursor(c);
        btMaintenance.setCursor(c);

        //master
        //jpanel1
        btPenjualBB.setCursor(c);
        btBarangBesar.setCursor(c);
        StokUlangBarangBesar.setCursor(c);
        btBarangToko.setCursor(c);
        //jpanel2
        btPenjualBK.setCursor(c);
        btBarang.setCursor(c);
        btStokUlang.setCursor(c);
        btUpdateGlobalBK.setCursor(c);
        btHistoryBarangBK.setCursor(c);
        //jpanel3
        btNamaBarang.setCursor(c);
        btTipe.setCursor(c);
        btMerek.setCursor(c);
        btSatuan.setCursor(c);
        btPelanggan.setCursor(c);
        btSupplier.setCursor(c);
        btUser.setCursor(c);

        //transaksi
        //jpanel4
        btKasir.setCursor(c);
        btRetur.setCursor(c);
        btProsesRetur.setCursor(c);
        btInventory.setCursor(c);
        //jpanel5
        btManajemenTransaksiBarangKecil.setCursor(c);
        btManajemenTransaksiBB.setCursor(c);
        //jpanel6
        btKasirBarangBesar.setCursor(c);
        btReturBarangBesar.setCursor(c);
        btProsesReturBarangBesar.setCursor(c);
        btInventoryBarangBesar.setCursor(c);
        btTransferBarangBesar.setCursor(c);

        //laporan
        //jpanel7
        btChart.setCursor(c);
        btReportMaster.setCursor(c);
        btReportPriceList.setCursor(c);
        btReportInsentif.setCursor(c);
        //jpanel8
        btReportTransaksi.setCursor(c);
        btReportRetur.setCursor(c);
        btReportInventory.setCursor(c);
        btReportProfit.setCursor(c);
        //jpanel9
        btReportTransaksiBB.setCursor(c);
        btReportReturBB.setCursor(c);
        btReportInventoryBB.setCursor(c);
        btReportProfitBB.setCursor(c);
        btReportTransferBB.setCursor(c);

        //maintenance
        btBackUp.setCursor(c);
    }

    public void setIconFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image frameIcon = kit.getImage(getClass().getResource("/Sinarelektronikapp/imageresource/jframeIcon.png"));
        this.setIconImage(frameIcon);
    }
    //JOptionPane.showMessageDialog(null, "Active USer = "+activeUser.getUserName());    
    private Connection connection;

    public void koneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + ip.getIpServer() + "/sinarelektronik?;", "root", "P@ssw0rd");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada jaringan karena = " + ex, "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void setLoadAwal() {
        Main.setVisible(false);
        menuLogout.setVisible(false);
        menuLogin.setVisible(true);
        setting.setVisible(true);
    }

    public void setAfterLoadAwal() {
        Login.setText("Log Out");
        Main.setVisible(true);
        menuLogout.setVisible(true);
        menuLogin.setVisible(false);
    }

    public void loadDialogPassword() {
        int y = 250;
        int x = 500;
        dialogLogin.setModal(true);
        dialogLogin.setLocation(x, y);
        dialogLogin.setSize(300, 150);
        dialogLogin.show();
    }

    final Toolkit toolkit = Toolkit.getDefaultToolkit();
    final Dimension screenSize = toolkit.getScreenSize();

    private JIFKasirView2 fKasirView2 = null;

    private JIFBarangBesar jIFBarangBesar = null;

    private JIFBarangView jIFBarangView = null;

    private JIFMerekView jIFMerekView = null;

    private JIFSatuanVIew jIFSatuanVIew = null;

    private JIFNamaBarang jIFNamaBarang = null;

    private JIFPelangganView jIFPelangganView = null;

    private JIFSupplierView jIFSupplierView = null;

    private JIFTipeView jIFTipeView = null;

    private JIFUserView jIFUserView = null;

    private JIFKasirView jIFKasirView = null;

    private JIFReturView2 jifReturView = null;

    private JIFReturViewBarangBesar jIFReturViewBB = null;

    private JIFInventoryBB jIFInventory = null;

    private JIFInventory jIFInventory1 = null;

    private JIFReminderView jIFReminderView = null;

    private JIFReminderView2 jIFReminderView2 = null;

    private JIFPRosesRetur2 jIFPRosesRetur = null;

    private JIFPRosesReturBB jIFPRosesReturBB = null;

    private JIFPriceListReport jIFPriceListReport = null;

    private JIFReportTransaksiBarangBesar jIFReportTransaksiBarangBesar = null;

    private JIFPenjualBarangBesarView jIFPenjualBBView = null;

    private JIFPenjualBKView jIFPenjualBKView = null;

    private JIFBarangToko jIFBarangToko = null;

    private JIFTransferBB jIFTransferBB = null;

    private JIFREportTransferBB jIFREportTransferBB = null;

    private JIFManajemenTransaksiPenjualan jifmtp = null;

    private JIFTransaksiPenjualanBB jiftpbb = null;

    private JIFChartView jIFChartView = null;

    private JIFReportPengembalianBarangKecilSupplier jIFReportPengembalianBarangKecilSupplier = null;

    private JIFReportPengembalianBarangBesar jIFReportPengembalianBarangBesar = null;

    private JIFUpdateGlobalBK jIFUpdateGlobalBK = null;

    private JIFHistoryBarang jIFHistoryBarang = null;

    private JIFUpdateGlobalBB jIFUpdateGlobalBB = null;

    private JIFHistoryBarangBesar jIFHistoryBarangBesar = null;

    private JIFBarangBonusKaryawanBKView jIFBarangBonusKaryawanBKView = null;

    private JIFReportInsentifBarangKecil jIFReportInsentifBarangKecil = null;

    private JIFBarangBonusKaryawanBBView jIFBarangBonusKaryawanBBView = null;

    private JIFSetupBonusView jIFSetupBonusView = null;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogLogin = new javax.swing.JDialog();
        Up = new javax.swing.JPanel();
        kiri = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        kanan = new javax.swing.JPanel();
        txtUserName = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        Down = new javax.swing.JPanel();
        btLogin = new javax.swing.JButton();
        btReset = new javax.swing.JButton();
        dialogSetNetwork = new javax.swing.JDialog();
        up = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtIP = new javax.swing.JTextField();
        down = new javax.swing.JPanel();
        btTes = new javax.swing.JButton();
        btSambungkan = new javax.swing.JButton();
        dialogSetTema = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        cmbTema = new javax.swing.JComboBox();
        JIFDashBoard = new javax.swing.JInternalFrame();
        Left = new javax.swing.JPanel();
        btDashboard = new Sinarelektronikapp.swinglib.loadBtImage();
        btMaster = new Sinarelektronikapp.swinglib.loadBtImage();
        btTransaksi = new Sinarelektronikapp.swinglib.loadBtImage();
        btReport = new Sinarelektronikapp.swinglib.loadBtImage();
        btMaintenance = new Sinarelektronikapp.swinglib.loadBtImage();
        Center = new javax.swing.JPanel();
        dashboard = new javax.swing.JPanel();
        imageDashboard = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        master = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btPenjualBB = new Sinarelektronikapp.swinglib.loadBtImage();
        btBarangBesar = new Sinarelektronikapp.swinglib.loadBtImage();
        StokUlangBarangBesar = new Sinarelektronikapp.swinglib.loadBtImage();
        btBarangBesarExclude = new Sinarelektronikapp.swinglib.loadBtImage();
        btBarangToko = new Sinarelektronikapp.swinglib.loadBtImage();
        btUpdateGlobalBB = new Sinarelektronikapp.swinglib.loadBtImage();
        btHistoryBarangBB = new Sinarelektronikapp.swinglib.loadBtImage();
        jPanel2 = new javax.swing.JPanel();
        btPenjualBK = new Sinarelektronikapp.swinglib.loadBtImage();
        btBarang = new Sinarelektronikapp.swinglib.loadBtImage();
        btSetupBonus = new Sinarelektronikapp.swinglib.loadBtImage();
        btBarangKecilExclude = new Sinarelektronikapp.swinglib.loadBtImage();
        btStokUlang = new Sinarelektronikapp.swinglib.loadBtImage();
        btUpdateGlobalBK = new Sinarelektronikapp.swinglib.loadBtImage();
        btHistoryBarangBK = new Sinarelektronikapp.swinglib.loadBtImage();
        jPanel3 = new javax.swing.JPanel();
        btNamaBarang = new Sinarelektronikapp.swinglib.loadBtImage();
        btTipe = new Sinarelektronikapp.swinglib.loadBtImage();
        btMerek = new Sinarelektronikapp.swinglib.loadBtImage();
        btSatuan = new Sinarelektronikapp.swinglib.loadBtImage();
        btPelanggan = new Sinarelektronikapp.swinglib.loadBtImage();
        btSupplier = new Sinarelektronikapp.swinglib.loadBtImage();
        btUser = new Sinarelektronikapp.swinglib.loadBtImage();
        transaksi = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btKasir = new Sinarelektronikapp.swinglib.loadBtImage();
        btRetur = new Sinarelektronikapp.swinglib.loadBtImage();
        btProsesRetur = new Sinarelektronikapp.swinglib.loadBtImage();
        btInventory = new Sinarelektronikapp.swinglib.loadBtImage();
        jPanel5 = new javax.swing.JPanel();
        btManajemenTransaksiBarangKecil = new Sinarelektronikapp.swinglib.loadBtImage();
        btManajemenTransaksiBB = new Sinarelektronikapp.swinglib.loadBtImage();
        jPanel6 = new javax.swing.JPanel();
        btKasirBarangBesar = new Sinarelektronikapp.swinglib.loadBtImage();
        btReturBarangBesar = new Sinarelektronikapp.swinglib.loadBtImage();
        btProsesReturBarangBesar = new Sinarelektronikapp.swinglib.loadBtImage();
        btInventoryBarangBesar = new Sinarelektronikapp.swinglib.loadBtImage();
        btTransferBarangBesar = new Sinarelektronikapp.swinglib.loadBtImage();
        laporan = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btChart = new Sinarelektronikapp.swinglib.loadBtImage();
        btReportMaster = new Sinarelektronikapp.swinglib.loadBtImage();
        btReportPriceList = new Sinarelektronikapp.swinglib.loadBtImage();
        jPanel8 = new javax.swing.JPanel();
        btReportTransaksi = new Sinarelektronikapp.swinglib.loadBtImage();
        btReportRetur = new Sinarelektronikapp.swinglib.loadBtImage();
        btReportInventory = new Sinarelektronikapp.swinglib.loadBtImage();
        btReportProfit = new Sinarelektronikapp.swinglib.loadBtImage();
        btInsentifBK = new Sinarelektronikapp.swinglib.loadBtImage();
        jPanel9 = new javax.swing.JPanel();
        btReportTransaksiBB = new Sinarelektronikapp.swinglib.loadBtImage();
        btReportReturBB = new Sinarelektronikapp.swinglib.loadBtImage();
        btReportInventoryBB = new Sinarelektronikapp.swinglib.loadBtImage();
        btReportProfitBB = new Sinarelektronikapp.swinglib.loadBtImage();
        btReportTransferBB = new Sinarelektronikapp.swinglib.loadBtImage();
        btReportInsentif = new Sinarelektronikapp.swinglib.loadBtImage();
        maintenance = new javax.swing.JPanel();
        btBackUp = new Sinarelektronikapp.swinglib.loadBtImage();
        DialogMaintenance = new javax.swing.JDialog();
        maintenanceView1 = new Sinarelektronikapp.maintenance.maintenanceView();
        DialogAbout = new javax.swing.JDialog();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        DialogLisensi = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        DialogReturBK = new javax.swing.JDialog();
        btReturCustomer = new javax.swing.JButton();
        btReturSupplier = new javax.swing.JButton();
        DialogReturBB = new javax.swing.JDialog();
        btReturCustomer1 = new javax.swing.JButton();
        btReturSupplier1 = new javax.swing.JButton();
        JSPutama = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        Main = new javax.swing.JMenu();
        menuDashboard = new javax.swing.JMenuItem();
        Login = new javax.swing.JMenu();
        menuLogin = new javax.swing.JMenuItem();
        menuLogout = new javax.swing.JMenuItem();
        setting = new javax.swing.JMenu();
        SetTema = new javax.swing.JMenuItem();
        jaringan = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        dialogLogin.setTitle("Login");

        Up.setPreferredSize(new java.awt.Dimension(200, 200));
        Up.setLayout(new java.awt.BorderLayout());

        kiri.setLayout(new java.awt.GridLayout(2, 0));

        jLabel1.setText("User Name :");
        kiri.add(jLabel1);

        jLabel2.setText("Password :");
        kiri.add(jLabel2);

        Up.add(kiri, java.awt.BorderLayout.WEST);

        kanan.setPreferredSize(new java.awt.Dimension(100, 100));
        kanan.setLayout(new java.awt.GridLayout(2, 0));

        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });
        kanan.add(txtUserName);

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        kanan.add(txtPassword);

        Up.add(kanan, java.awt.BorderLayout.CENTER);

        dialogLogin.getContentPane().add(Up, java.awt.BorderLayout.CENTER);

        Down.setPreferredSize(new java.awt.Dimension(100, 30));
        Down.setLayout(new java.awt.GridLayout(1, 0));

        btLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/login2.png"))); // NOI18N
        btLogin.setMnemonic('L');
        btLogin.setFocusable(false);
        btLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btLogin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoginActionPerformed(evt);
            }
        });
        Down.add(btLogin);

        btReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/reset.png"))); // NOI18N
        btReset.setMnemonic('R');
        btReset.setFocusable(false);
        btReset.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btReset.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetActionPerformed(evt);
            }
        });
        Down.add(btReset);

        dialogLogin.getContentPane().add(Down, java.awt.BorderLayout.PAGE_END);

        dialogSetNetwork.setTitle("Konfigurasi Jaringan");

        up.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Input IP"));
        up.setLayout(new java.awt.GridLayout(1, 2));

        jLabel4.setText("IP Server :");
        up.add(jLabel4);

        txtIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIPActionPerformed(evt);
            }
        });
        up.add(txtIP);

        dialogSetNetwork.getContentPane().add(up, java.awt.BorderLayout.CENTER);

        down.setLayout(new java.awt.GridLayout(1, 2));

        btTes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/tes.png"))); // NOI18N
        btTes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTesActionPerformed(evt);
            }
        });
        down.add(btTes);

        btSambungkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/sambung.png"))); // NOI18N
        btSambungkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSambungkanActionPerformed(evt);
            }
        });
        down.add(btSambungkan);

        dialogSetNetwork.getContentPane().add(down, java.awt.BorderLayout.PAGE_END);

        dialogSetTema.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogSetTema.setTitle("Konfigurasi Tema");
        dialogSetTema.getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jLabel3.setText("Opsi tema :");
        dialogSetTema.getContentPane().add(jLabel3);

        cmbTema.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nimbus", "Windows Classic", "Windows", "Acryl", "Aero", "Aluminium", "Bernstein", "Fast", "Graphite", "HiFi", "Luna", "Metal", "McWin", "Mint", "Noire", "Smart", "Texture" }));
        cmbTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTemaActionPerformed(evt);
            }
        });
        dialogSetTema.getContentPane().add(cmbTema);

        JIFDashBoard.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Dashboard"));
        JIFDashBoard.setClosable(true);
        JIFDashBoard.setIconifiable(true);
        JIFDashBoard.setTitle("Dashboard");
        JIFDashBoard.setVisible(true);

        Left.setMinimumSize(new java.awt.Dimension(64, 90));
        Left.setOpaque(false);

        btDashboard.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/BlackDashboardButton.png"))); // NOI18N
        btDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDashboardActionPerformed(evt);
            }
        });
        Left.add(btDashboard);

        btMaster.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btMaster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/BlackMaster.png"))); // NOI18N
        btMaster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMasterActionPerformed(evt);
            }
        });
        Left.add(btMaster);

        btTransaksi.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/Blacktransaksi.png"))); // NOI18N
        btTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTransaksiActionPerformed(evt);
            }
        });
        Left.add(btTransaksi);

        btReport.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/Blackreport.png"))); // NOI18N
        btReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportActionPerformed(evt);
            }
        });
        Left.add(btReport);

        btMaintenance.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btMaintenance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/Blackmaintenance.png"))); // NOI18N
        btMaintenance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMaintenanceActionPerformed(evt);
            }
        });
        Left.add(btMaintenance);

        JIFDashBoard.getContentPane().add(Left, java.awt.BorderLayout.PAGE_END);

        Center.setOpaque(false);
        Center.setLayout(new java.awt.CardLayout());

        dashboard.setOpaque(false);
        dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/dashboard.png"))); // NOI18N
        dashboard.add(imageDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        jLabel6.setText("SISTEM INFORMASI MANAJEMEN");
        dashboard.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 600, 80));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        jLabel9.setText("SINAR ELEKTRONIK");
        dashboard.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 380, 80));

        Center.add(dashboard, "dashboard");

        master.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btPenjualBB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/penjualbb.png"))); // NOI18N
        btPenjualBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPenjualBBActionPerformed(evt);
            }
        });
        jPanel1.add(btPenjualBB);

        btBarangBesar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btBarangBesar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/barangbesar.png"))); // NOI18N
        btBarangBesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBarangBesarActionPerformed(evt);
            }
        });
        jPanel1.add(btBarangBesar);

        StokUlangBarangBesar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        StokUlangBarangBesar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/stokulangbb.png"))); // NOI18N
        StokUlangBarangBesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StokUlangBarangBesarActionPerformed(evt);
            }
        });
        jPanel1.add(StokUlangBarangBesar);

        btBarangBesarExclude.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/BonusBB.png"))); // NOI18N
        btBarangBesarExclude.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBarangBesarExcludeActionPerformed(evt);
            }
        });
        jPanel1.add(btBarangBesarExclude);

        btBarangToko.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/barangToko2.png"))); // NOI18N
        btBarangToko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBarangTokoActionPerformed(evt);
            }
        });
        jPanel1.add(btBarangToko);

        btUpdateGlobalBB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/updateGlobal.png"))); // NOI18N
        btUpdateGlobalBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateGlobalBBActionPerformed(evt);
            }
        });
        jPanel1.add(btUpdateGlobalBB);

        btHistoryBarangBB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/historybarang.png"))); // NOI18N
        btHistoryBarangBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHistoryBarangBBActionPerformed(evt);
            }
        });
        jPanel1.add(btHistoryBarangBB);

        master.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 820, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btPenjualBK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/penjualbk.png"))); // NOI18N
        btPenjualBK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPenjualBKActionPerformed(evt);
            }
        });
        jPanel2.add(btPenjualBK);

        btBarang.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/barang.png"))); // NOI18N
        btBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBarangActionPerformed(evt);
            }
        });
        jPanel2.add(btBarang);

        btSetupBonus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/setupBonus.png"))); // NOI18N
        btSetupBonus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSetupBonusActionPerformed(evt);
            }
        });
        jPanel2.add(btSetupBonus);

        btBarangKecilExclude.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/BonusBK.png"))); // NOI18N
        btBarangKecilExclude.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBarangKecilExcludeActionPerformed(evt);
            }
        });
        jPanel2.add(btBarangKecilExclude);

        btStokUlang.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btStokUlang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/StokUlang.png"))); // NOI18N
        btStokUlang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStokUlangActionPerformed(evt);
            }
        });
        jPanel2.add(btStokUlang);

        btUpdateGlobalBK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/updateGlobal.png"))); // NOI18N
        btUpdateGlobalBK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateGlobalBKActionPerformed(evt);
            }
        });
        jPanel2.add(btUpdateGlobalBK);

        btHistoryBarangBK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/historybarang.png"))); // NOI18N
        btHistoryBarangBK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHistoryBarangBKActionPerformed(evt);
            }
        });
        jPanel2.add(btHistoryBarangBK);

        master.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 710, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btNamaBarang.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btNamaBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/Nama Barang.png"))); // NOI18N
        btNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNamaBarangActionPerformed(evt);
            }
        });
        jPanel3.add(btNamaBarang);

        btTipe.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btTipe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/tipe.png"))); // NOI18N
        btTipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTipeActionPerformed(evt);
            }
        });
        jPanel3.add(btTipe);

        btMerek.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btMerek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/merek.png"))); // NOI18N
        btMerek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMerekActionPerformed(evt);
            }
        });
        jPanel3.add(btMerek);

        btSatuan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btSatuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/satuan.png"))); // NOI18N
        btSatuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSatuanActionPerformed(evt);
            }
        });
        jPanel3.add(btSatuan);

        btPelanggan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/pelanggan.png"))); // NOI18N
        btPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPelangganActionPerformed(evt);
            }
        });
        jPanel3.add(btPelanggan);

        btSupplier.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/supplier.png"))); // NOI18N
        btSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSupplierActionPerformed(evt);
            }
        });
        jPanel3.add(btSupplier);

        btUser.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/user.png"))); // NOI18N
        btUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUserActionPerformed(evt);
            }
        });
        jPanel3.add(btUser);

        master.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 670, -1));

        Center.add(master, "master");

        transaksi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btKasir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btKasir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/kasir.png"))); // NOI18N
        btKasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btKasirActionPerformed(evt);
            }
        });
        jPanel4.add(btKasir);

        btRetur.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btRetur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/Retur.png"))); // NOI18N
        btRetur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReturActionPerformed(evt);
            }
        });
        jPanel4.add(btRetur);

        btProsesRetur.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btProsesRetur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/prosesRetur.png"))); // NOI18N
        btProsesRetur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProsesReturActionPerformed(evt);
            }
        });
        jPanel4.add(btProsesRetur);

        btInventory.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btInventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/Inventory2.png"))); // NOI18N
        btInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInventoryActionPerformed(evt);
            }
        });
        jPanel4.add(btInventory);

        transaksi.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, -1, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btManajemenTransaksiBarangKecil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/ManajemenPenjualanBK.png"))); // NOI18N
        btManajemenTransaksiBarangKecil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btManajemenTransaksiBarangKecilActionPerformed(evt);
            }
        });
        jPanel5.add(btManajemenTransaksiBarangKecil);

        btManajemenTransaksiBB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/manajemenPenjualanBB.png"))); // NOI18N
        btManajemenTransaksiBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btManajemenTransaksiBBActionPerformed(evt);
            }
        });
        jPanel5.add(btManajemenTransaksiBB);

        transaksi.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 380, 150));

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btKasirBarangBesar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btKasirBarangBesar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/kasirBarangBesar.png"))); // NOI18N
        btKasirBarangBesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btKasirBarangBesarActionPerformed(evt);
            }
        });
        jPanel6.add(btKasirBarangBesar);

        btReturBarangBesar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btReturBarangBesar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/ReturBB.png"))); // NOI18N
        btReturBarangBesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReturBarangBesarActionPerformed(evt);
            }
        });
        jPanel6.add(btReturBarangBesar);

        btProsesReturBarangBesar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btProsesReturBarangBesar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/prosesreturbb.png"))); // NOI18N
        btProsesReturBarangBesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProsesReturBarangBesarActionPerformed(evt);
            }
        });
        jPanel6.add(btProsesReturBarangBesar);

        btInventoryBarangBesar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btInventoryBarangBesar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/InventoryBarangBesar.png"))); // NOI18N
        btInventoryBarangBesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInventoryBarangBesarActionPerformed(evt);
            }
        });
        jPanel6.add(btInventoryBarangBesar);

        btTransferBarangBesar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/transferbb.png"))); // NOI18N
        btTransferBarangBesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTransferBarangBesarActionPerformed(evt);
            }
        });
        jPanel6.add(btTransferBarangBesar);

        transaksi.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, -1, 140));

        Center.add(transaksi, "transaksi");

        laporan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btChart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/grafik.png"))); // NOI18N
        btChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btChartActionPerformed(evt);
            }
        });
        jPanel7.add(btChart);

        btReportMaster.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btReportMaster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/BlackMaster.png"))); // NOI18N
        btReportMaster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportMasterActionPerformed(evt);
            }
        });
        jPanel7.add(btReportMaster);

        btReportPriceList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btReportPriceList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/PriceList.png"))); // NOI18N
        btReportPriceList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportPriceListActionPerformed(evt);
            }
        });
        jPanel7.add(btReportPriceList);

        laporan.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btReportTransaksi.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btReportTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/Blacktransaksi.png"))); // NOI18N
        btReportTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportTransaksiActionPerformed(evt);
            }
        });
        jPanel8.add(btReportTransaksi);

        btReportRetur.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btReportRetur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/Retur.png"))); // NOI18N
        btReportRetur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportReturActionPerformed(evt);
            }
        });
        jPanel8.add(btReportRetur);

        btReportInventory.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btReportInventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/Inventory2.png"))); // NOI18N
        btReportInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportInventoryActionPerformed(evt);
            }
        });
        jPanel8.add(btReportInventory);

        btReportProfit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btReportProfit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/Profit.png"))); // NOI18N
        btReportProfit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportProfitActionPerformed(evt);
            }
        });
        jPanel8.add(btReportProfit);

        btInsentifBK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/insentifbk.png"))); // NOI18N
        btInsentifBK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInsentifBKActionPerformed(evt);
            }
        });
        jPanel8.add(btInsentifBK);

        laporan.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 520, -1));

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btReportTransaksiBB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btReportTransaksiBB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/transaksiBB.png"))); // NOI18N
        btReportTransaksiBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportTransaksiBBActionPerformed(evt);
            }
        });
        jPanel9.add(btReportTransaksiBB);

        btReportReturBB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btReportReturBB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/ReturBB.png"))); // NOI18N
        btReportReturBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportReturBBActionPerformed(evt);
            }
        });
        jPanel9.add(btReportReturBB);

        btReportInventoryBB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btReportInventoryBB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/InventoryBarangBesar.png"))); // NOI18N
        btReportInventoryBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportInventoryBBActionPerformed(evt);
            }
        });
        jPanel9.add(btReportInventoryBB);

        btReportProfitBB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btReportProfitBB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/ProfitBB.png"))); // NOI18N
        btReportProfitBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportProfitBBActionPerformed(evt);
            }
        });
        jPanel9.add(btReportProfitBB);

        btReportTransferBB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btReportTransferBB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/transferbb.png"))); // NOI18N
        btReportTransferBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportTransferBBActionPerformed(evt);
            }
        });
        jPanel9.add(btReportTransferBB);

        btReportInsentif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/insentifbb.png"))); // NOI18N
        btReportInsentif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportInsentifActionPerformed(evt);
            }
        });
        jPanel9.add(btReportInsentif);

        laporan.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        Center.add(laporan, "laporan");

        maintenance.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btBackUp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btBackUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/backUp.png"))); // NOI18N
        btBackUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBackUpActionPerformed(evt);
            }
        });
        maintenance.add(btBackUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, -1, -1));

        Center.add(maintenance, "maintenance");

        JIFDashBoard.getContentPane().add(Center, java.awt.BorderLayout.CENTER);

        DialogMaintenance.getContentPane().add(maintenanceView1, java.awt.BorderLayout.CENTER);

        DialogAbout.setTitle("Informasi Aplikasi");
        DialogAbout.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/dashboard.png"))); // NOI18N
        DialogAbout.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("SIM_Sinar Elektronik V.1.0");
        DialogAbout.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jButton2.setText("Lisensi");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        DialogAbout.getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, -1, -1));

        DialogLisensi.setTitle("Lisensi Aplikasi");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("SIM_Sinar ELektronik\n\nCopyright (C) 2013 Achmad Fauzi\nprogram ini adalah program yang dibuat khusus \nuntuk toko Sinar Elektronik\nberorientasi pada proses bisnis dalam toko \nSinar Elektronik\n\nUntuk informasi lebih lanjut, silahkan hubungi :\nAchmad Fauzi\nemail \t:fauzi.knightmaster.achmad@gmail.com\nno hp \t:08563084294\nweblog \t:xaxioza.wordpress.com");
        jScrollPane1.setViewportView(jTextArea1);

        DialogLisensi.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        DialogReturBK.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DialogReturBK.setTitle("Retur Barang Kecil");
        DialogReturBK.getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        btReturCustomer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btReturCustomer.setText("Customer - Toko");
        btReturCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReturCustomerActionPerformed(evt);
            }
        });
        DialogReturBK.getContentPane().add(btReturCustomer);

        btReturSupplier.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btReturSupplier.setText("Supplier - Toko");
        btReturSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReturSupplierActionPerformed(evt);
            }
        });
        DialogReturBK.getContentPane().add(btReturSupplier);

        DialogReturBB.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DialogReturBB.setTitle("Retur Barang Besar");
        DialogReturBB.getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        btReturCustomer1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btReturCustomer1.setText("Customer - Toko");
        btReturCustomer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReturCustomer1ActionPerformed(evt);
            }
        });
        DialogReturBB.getContentPane().add(btReturCustomer1);

        btReturSupplier1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btReturSupplier1.setText("Supplier - Toko");
        btReturSupplier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReturSupplier1ActionPerformed(evt);
            }
        });
        DialogReturBB.getContentPane().add(btReturSupplier1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sinar Elektronik App");
        setResizable(true);
        getContentPane().add(JSPutama, java.awt.BorderLayout.CENTER);

        Main.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/main.png"))); // NOI18N
        Main.setText("Main");

        menuDashboard.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        menuDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/dashboard-small.png"))); // NOI18N
        menuDashboard.setText("Dashboard");
        menuDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDashboardActionPerformed(evt);
            }
        });
        Main.add(menuDashboard);

        jMenuBar1.add(Main);

        Login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/main-login.png"))); // NOI18N
        Login.setText("Login");

        menuLogin.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        menuLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/login.png"))); // NOI18N
        menuLogin.setText("Login");
        menuLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLoginActionPerformed(evt);
            }
        });
        Login.add(menuLogin);

        menuLogout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        menuLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/logout.png"))); // NOI18N
        menuLogout.setText("Logout");
        menuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLogoutActionPerformed(evt);
            }
        });
        Login.add(menuLogout);

        jMenuBar1.add(Login);

        setting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/setting.png"))); // NOI18N
        setting.setText("setting");

        SetTema.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        SetTema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/tema.png"))); // NOI18N
        SetTema.setText("Tema");
        SetTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SetTemaActionPerformed(evt);
            }
        });
        setting.add(SetTema);

        jaringan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.CTRL_MASK));
        jaringan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/jaringan.png"))); // NOI18N
        jaringan.setText("Jaringan");
        jaringan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jaringanActionPerformed(evt);
            }
        });
        setting.add(jaringan);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/about.png"))); // NOI18N
        jMenuItem1.setText("About");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        setting.add(jMenuItem1);

        jMenuBar1.add(setting);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void callMenuBarang() {
        jIFBarangView = null;
        try {
            jIFBarangView = new JIFBarangView();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFBarangView);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 5;
        jIFBarangView.setLocation(x, y);
        jIFBarangView.setVisible(true);
    }

    public void callMenuBarangBesar() {
        jIFBarangBesar = null;
        try {
            jIFBarangBesar = new JIFBarangBesar();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFBarangBesar);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 5;
        jIFBarangBesar.setLocation(x, y);
        jIFBarangBesar.setVisible(true);
    }

    private void menuLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLoginActionPerformed
        // TODO add your handling code here:               
        resetLogin();
        loadDialogPassword();
    }//GEN-LAST:event_menuLoginActionPerformed

    public void inOut() {
        menuLogin.setVisible(false);
        menuLogout.setVisible(true);
    }

    public String getUsername() {
        return txtUserName.getText();
    }

    public ActiveUser activeUser = new ActiveUser();

    public ActiveUser getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(ActiveUser activeUser) {
        this.activeUser = activeUser;
    }

    public UserLevel userLevel = new UserLevel();

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public void setBtUser(Boolean b) {
        btUser.setEnabled(b);
    }

    public void setBtUpdateGlobal(Boolean b) {
        btUpdateGlobalBK.setEnabled(b);
    }

    public void setBesar(Boolean b) {
        btBarangBesar.setEnabled(b);
        StokUlangBarangBesar.setEnabled(b);
        btBarangToko.setEnabled(b);
        btPenjualBB.setEnabled(b);
        btUpdateGlobalBB.setEnabled(b);
        btUpdateGlobalBK.setEnabled(b);
        btPenjualBK.setEnabled(b);
        btBarangBesarExclude.setEnabled(b);
        btBarangKecilExclude.setEnabled(b);
        btReportMaster.setEnabled(b);
        btInsentifBK.setEnabled(b);
        btSetupBonus.setEnabled(b);
        btReportMaster.setEnabled(b);
    }

    public void setTransaksiBk(Boolean b) {
        btTransaksi.setEnabled(b);
        btKasir.setEnabled(b);
        btRetur.setEnabled(b);
        btProsesRetur.setEnabled(b);
        btInventory.setEnabled(b);
        btManajemenTransaksiBarangKecil.setEnabled(b);
    }

    public void setTransaksiBb(Boolean b) {
        btKasirBarangBesar.setEnabled(b);
        btReturBarangBesar.setEnabled(b);
        btProsesReturBarangBesar.setEnabled(b);
        btInventoryBarangBesar.setEnabled(b);
        btTransferBarangBesar.setEnabled(b);
        btManajemenTransaksiBB.setEnabled(b);
    }

    public void setReportBk(Boolean b) {
        btReport.setEnabled(b);
        btReportPriceList.setEnabled(b);
        btReportTransaksi.setEnabled(b);
        btReportRetur.setEnabled(b);
        btReportTransaksi.setEnabled(b);
        btReportInventory.setEnabled(b);
    }

    public void setReportBb(Boolean b) {
        btReportInsentif.setEnabled(b);
        btReportProfit.setEnabled(b);
        btReportProfitBB.setEnabled(b);
        btReportTransaksiBB.setEnabled(b);
        btReportInventoryBB.setEnabled(b);
        btReportReturBB.setEnabled(b);
        btReportTransferBB.setEnabled(b);
    }

    public void setMaintenance(Boolean b) {
        btMaintenance.setEnabled(b);
        btBackUp.setEnabled(b);
    }

    public void doLogin() {
        koneksi();
        String userNameF = txtUserName.getText().trim();
        String passwordF = txtPassword.getText().trim();      

        activeUser.setUserName(userNameF);
        activeUser.Filling();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT nama, password, level FROM user WHERE nama='" + userNameF + "'");           
            String decryptedPassword = null;

            if (rs.next()) {
                try {
                    decryptedPassword = AES.decrypt(rs.getString("password"), AppConstant.CONFIG_PASSWORD);
                } catch (IllegalBlockSizeException | BadPaddingException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (decryptedPassword.equals(passwordF)) {
                    Login.setText("Logout");
                    String levelLogin = "";
                    levelLogin = rs.getString("level");
                    userLevel.setUserLevel(levelLogin);
                    userLevel.Filling();
                    setAfterLoadAwal();
                    if (applicationMode.getApplicationMode().equals("3")) {
                        callMenuBarang();
                    } else {
                        switch (levelLogin) {
                            case "Pemilik Toko":
                                setBtUser(true);
                                setBesar(true);
                                setTransaksiBk(true);
                                setTransaksiBb(true);
                                setReportBk(true);
                                setReportBb(true);
                                setMaintenance(true);
                                break;
                            case "Administrator":
                                setBtUser(false);
                                setBesar(false);
                                setTransaksiBk(true);
                                setTransaksiBb(false);
                                setReportBk(true);
                                setReportBb(false);
                                setMaintenance(true);
                                break;
                            case "Karyawan":
                                setBtUpdateGlobal(false);
                                setBtUser(false);
                                setBesar(false);
                                setTransaksiBk(false);
                                setTransaksiBb(false);
                                setReportBk(false);
                                setReportBb(false);
                                setMaintenance(false);
                                break;
                            default:;
                        }                        
                    }    
                    dialogLogin.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "salah password");
                    resetLogin();
                }
            } else {
                JOptionPane.showMessageDialog(null, "salah username");
                resetLogin();
            }
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "terjadi kesalahan pada " + exception);
        }
    }
    private void btLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoginActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                doLogin();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btLoginActionPerformed

    public void resetLogin() {
        txtUserName.setText("");
        txtPassword.setText("");
        txtUserName.requestFocus();
    }

    private void btResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetActionPerformed
        // TODO add your handling code here:
        resetLogin();
    }//GEN-LAST:event_btResetActionPerformed

    public void callMenuMerek() {
        jIFMerekView = null;
        try {
            jIFMerekView = new JIFMerekView();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error di menu merek action performed karena " + ex);
        }
        JSPutama.add(jIFMerekView);
        int x = (screenSize.width - MainFrame.WIDTH) / 3;
        int y = (screenSize.height - MainFrame.HEIGHT) / 3;
        jIFMerekView.setLocation(x, y);
        jIFMerekView.setVisible(true);
    }

    public void callMenuNamaBarang() {
        jIFNamaBarang = null;
        try {
            jIFNamaBarang = new JIFNamaBarang();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error di menu nama barangkecil action performed karena " + ex);
        }
        JSPutama.add(jIFNamaBarang);
        int x = (screenSize.width - MainFrame.WIDTH) / 3;
        int y = (screenSize.height - MainFrame.HEIGHT) / 3;
        jIFNamaBarang.setLocation(x, y);
        jIFNamaBarang.setVisible(true);
    }

    public void callMenuTipe() {
        int x = (screenSize.width - MainFrame.WIDTH) / 3;
        int y = (screenSize.height - MainFrame.HEIGHT) / 3;
        jIFTipeView = null;
        try {
            jIFTipeView = new JIFTipeView();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFTipeView);
        jIFTipeView.setLocation(x, y);
        jIFTipeView.setVisible(true);
    }

    public void callMenuSatuan() {
        int x = (screenSize.width - MainFrame.WIDTH) / 3;
        int y = (screenSize.height - MainFrame.HEIGHT) / 3;
        jIFSatuanVIew = null;
        try {
            jIFSatuanVIew = new JIFSatuanVIew();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFSatuanVIew);
        jIFSatuanVIew.setLocation(x, y);
        jIFSatuanVIew.setVisible(true);
    }

    public void callMenuPelanggan() {
        jIFPelangganView = null;
        try {
            jIFPelangganView = new JIFPelangganView();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFPelangganView);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 6;
        jIFPelangganView.setLocation(x, y);
        jIFPelangganView.setVisible(true);
    }

    public void callMenuSupplier() {
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 5;
        jIFSupplierView = null;
        try {
            jIFSupplierView = new JIFSupplierView();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFSupplierView);
        jIFSupplierView.setLocation(x, y);
        jIFSupplierView.setVisible(true);
    }

    public void callMenuStokUlang() {
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 5;
        try {
            jIFReminderView = new JIFReminderView();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFReminderView);
        jIFReminderView.setLocation(x, y);
        jIFReminderView.setVisible(true);
    }

    public void callMenuStokUlangBB() {
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 5;
        try {
            jIFReminderView2 = new JIFReminderView2();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFReminderView2);
        jIFReminderView2.setLocation(x, y);
        jIFReminderView2.setVisible(true);
    }

    public void callMenuUser() {
        jIFUserView = null;
        try {
            jIFUserView = new JIFUserView();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFUserView);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 5;
        jIFUserView.setLocation(x, y);
        jIFUserView.setVisible(true);
    }

    public void callMenuKasir() {
        jIFKasirView = null;
        try {
            jIFKasirView = new JIFKasirView();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFKasirView);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 6;
        jIFKasirView.setLocation(x, y);
        jIFKasirView.setVisible(true);
    }

    public void callMenuKasir2() {
        JIFKasirView2 fKasirView2 = null;
        try {
            fKasirView2 = new JIFKasirView2();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(fKasirView2);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 6;
        fKasirView2.setLocation(x, y);
        fKasirView2.setVisible(true);
    }


    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        // TODO add your handling code here:
        txtPassword.requestFocus();
    }//GEN-LAST:event_txtUserNameActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
        btLogin.doClick();
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void menuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLogoutActionPerformed
        // TODO add your handling code here:
        /*setLoadAwal();
         MenuBarLogin.setText("Login");*/
        if (JOptionPane.showConfirmDialog(null, "Apakah anda yakin logout ?") == JOptionPane.YES_OPTION) {
            //activeUser.deleteUserConfig();
            this.dispose();
            new ReLogin().setVisible(true);
        } else {
            return;
        }
    }//GEN-LAST:event_menuLogoutActionPerformed


    private void SetTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SetTemaActionPerformed
        // TODO add your handling code here:
        int y = 250;
        int x = 500;
        dialogSetTema.setModal(true);
        dialogSetTema.setLocation(x, y);
        dialogSetTema.setSize(300, 80);
        dialogSetTema.show();
    }//GEN-LAST:event_SetTemaActionPerformed

    private void jaringanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jaringanActionPerformed
        // TODO add your handling code here:         
        int y = 250;
        int x = 500;
        dialogSetNetwork.setModal(true);
        dialogSetNetwork.setLocation(x, y);
        dialogSetNetwork.setSize(300, 150);
        dialogSetNetwork.show();
    }//GEN-LAST:event_jaringanActionPerformed

    String hasilTes = "";

    public void tes() {
        ip.setIp(txtIP.getText());
        hasilTes = ip.tes();
        if (hasilTes.equals("Sukses")) {
            JOptionPane.showMessageDialog(null, hasilTes, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, hasilTes, "Informasi", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void btTesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTesActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                tes();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btTesActionPerformed

    private void btSambungkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSambungkanActionPerformed
        // TODO add your handling code here:
        if (hasilTes.equals("Sukses")) {
            JOptionPane.showMessageDialog(null, "Terkoneksi Dengan Server pada IP " + ip.getIpServer(), "Informasi", JOptionPane.INFORMATION_MESSAGE);
            dialogSetNetwork.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal Koneksi Dengan Server, silahkan cek konfigurasi dan lakukan tes jaringan", "Informasi", JOptionPane.WARNING_MESSAGE);
            txtIP.setText("");
            txtIP.requestFocus();
        }
    }//GEN-LAST:event_btSambungkanActionPerformed

    private void cmbTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTemaActionPerformed
        // TODO add your handling code here:
        String theme = "Default";
        try {
            Properties props = new Properties();
            props.put("logoString", "Sinar Elektronik");
            switch (cmbTema.getSelectedIndex()) {
                case 0:
                    UIManager.setLookAndFeel(new NimbusLookAndFeel());
                    break;
//                case 1:
//                    UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
//                    break;
//                case 2:
//                    UIManager.setLookAndFeel(new WindowsLookAndFeel());
//                    break;
                case 3:
                    com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme(theme);
                    com.jtattoo.plaf.acryl.AcrylLookAndFeel.setCurrentTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
                    break;
                case 4:
                    com.jtattoo.plaf.aero.AeroLookAndFeel.setTheme(theme);
                    com.jtattoo.plaf.aero.AeroLookAndFeel.setCurrentTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
                    break;
                case 5:
                    com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.setTheme(theme);
                    com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.setCurrentTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
                    break;
                case 6:
                    com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.setTheme(theme);
                    com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.setCurrentTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
                    break;
                case 7:
                    com.jtattoo.plaf.fast.FastLookAndFeel.setTheme(theme);
                    com.jtattoo.plaf.fast.FastLookAndFeel.setCurrentTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
                    break;
                case 8:
                    com.jtattoo.plaf.graphite.GraphiteLookAndFeel.setTheme(theme);
                    com.jtattoo.plaf.graphite.GraphiteLookAndFeel.setCurrentTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
                    break;
                case 9:
                    com.jtattoo.plaf.hifi.HiFiLookAndFeel.setTheme(theme);
                    com.jtattoo.plaf.hifi.HiFiLookAndFeel.setCurrentTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
                    break;
                case 10:
                    com.jtattoo.plaf.luna.LunaLookAndFeel.setTheme(theme);
                    com.jtattoo.plaf.luna.LunaLookAndFeel.setCurrentTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
                    break;
                case 11:
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                    break;
                case 12:
                    com.jtattoo.plaf.mcwin.McWinLookAndFeel.setTheme(theme);
                    com.jtattoo.plaf.mcwin.McWinLookAndFeel.setCurrentTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
                    break;
                case 13:
                    com.jtattoo.plaf.mint.MintLookAndFeel.setTheme(theme);
                    com.jtattoo.plaf.mint.MintLookAndFeel.setCurrentTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
                    break;
                case 14:
                    com.jtattoo.plaf.noire.NoireLookAndFeel.setTheme(theme);
                    com.jtattoo.plaf.noire.NoireLookAndFeel.setCurrentTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
                    break;
                case 15:
                    com.jtattoo.plaf.smart.SmartLookAndFeel.setTheme(theme);
                    com.jtattoo.plaf.smart.SmartLookAndFeel.setCurrentTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
                    break;
                case 16:
                    com.jtattoo.plaf.texture.TextureLookAndFeel.setTheme(theme);
                    com.jtattoo.plaf.texture.TextureLookAndFeel.setCurrentTheme(props);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
                    break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error dalam mengganti tema karena " + e);
        } finally {
            javax.swing.SwingUtilities.updateComponentTreeUI(this);
        }
    }//GEN-LAST:event_cmbTemaActionPerformed

    private void txtIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIPActionPerformed
        // TODO add your handling code here:
        btTes.doClick();
    }//GEN-LAST:event_txtIPActionPerformed

    private void menuDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDashboardActionPerformed
        // TODO add your handling code here:
        JSPutama.add(JIFDashBoard);
        int x = (screenSize.width - MainFrame.WIDTH) / 6;
        int y = (screenSize.height - MainFrame.HEIGHT) / 10;
        JIFDashBoard.setSize(900, 550);
        JIFDashBoard.setLocation(x, y);
        JIFDashBoard.setVisible(true);
    }//GEN-LAST:event_menuDashboardActionPerformed

    private void btDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDashboardActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) Center.getLayout();
        cl.show(Center, "dashboard");
    }//GEN-LAST:event_btDashboardActionPerformed

    private void btMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMasterActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) Center.getLayout();
        cl.show(Center, "master");
    }//GEN-LAST:event_btMasterActionPerformed

    private void btTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTransaksiActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) Center.getLayout();
        cl.show(Center, "transaksi");
    }//GEN-LAST:event_btTransaksiActionPerformed

    private void btReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) Center.getLayout();
        cl.show(Center, "laporan");
    }//GEN-LAST:event_btReportActionPerformed

    private void btMaintenanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMaintenanceActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) Center.getLayout();
        cl.show(Center, "maintenance");
    }//GEN-LAST:event_btMaintenanceActionPerformed

    private void btBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBarangActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuBarang();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btBarangActionPerformed

    private void btMerekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMerekActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuMerek();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btMerekActionPerformed

    private void btPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPelangganActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuPelanggan();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btPelangganActionPerformed

    private void btStokUlangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStokUlangActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuStokUlang();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btStokUlangActionPerformed

    private void btSatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSatuanActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuSatuan();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btSatuanActionPerformed

    private void btTipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTipeActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuTipe();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btTipeActionPerformed

    private void btKasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKasirActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuKasir();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btKasirActionPerformed
    /**/
    private void btBackUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBackUpActionPerformed
        // TODO add your handling code here:
        int y = 250;
        int x = 500;
        DialogMaintenance.setModal(true);
        DialogMaintenance.setLocation(x, y);
        DialogMaintenance.setSize(400, 150);
        DialogMaintenance.setTitle("Back Up Database");
        DialogMaintenance.show();
    }//GEN-LAST:event_btBackUpActionPerformed

    private void btSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSupplierActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuSupplier();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btSupplierActionPerformed

    public void callMenuProfit() {
        JIFReportProfit fReportProfit = null;
        try {
            fReportProfit = new JIFReportProfit();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(fReportProfit);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 6;
        fReportProfit.setLocation(x, y);
        fReportProfit.setSize(400, 200);
        fReportProfit.setVisible(true);
    }

    public void callMenuProfitBarangBesar() {
        JIFReportProfitBarangBesar fReportProfit = null;
        try {
            fReportProfit = new JIFReportProfitBarangBesar();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(fReportProfit);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 6;
        fReportProfit.setLocation(x, y);
        fReportProfit.setSize(400, 200);
        fReportProfit.setVisible(true);
    }

    private void btReportProfitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportProfitActionPerformed
        // TODO add your handling code here:
        callMenuProfit();
    }//GEN-LAST:event_btReportProfitActionPerformed

    private void btUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUserActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuUser();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btUserActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        int y = 250;
        int x = 500;
        DialogAbout.setModal(true);
        DialogAbout.setLocation(x, y);
        DialogAbout.setSize(300, 300);
        DialogAbout.show();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int y = 250;
        int x = 500;
        DialogLisensi.setModal(true);
        DialogLisensi.setLocation(x, y);
        DialogLisensi.setSize(400, 380);
        DialogLisensi.show();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNamaBarangActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuNamaBarang();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btNamaBarangActionPerformed

    public void callMenuTransaksiReturBB() {
        jIFReturViewBB = null;
        try {
            jIFReturViewBB = new JIFReturViewBarangBesar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error di menu retur barangkecil besar view action performed karena " + ex, "peringatan", JOptionPane.ERROR_MESSAGE);
        }
        JSPutama.add(jIFReturViewBB);
        int x = (screenSize.width - MainFrame.WIDTH) / 3;
        int y = (screenSize.height - MainFrame.HEIGHT) / 8;
        jIFReturViewBB.setLocation(x, y);
        jIFReturViewBB.setVisible(true);
    }

    public void callMenuTransaksiRetur() {
        jifReturView = null;
        try {
            jifReturView = new JIFReturView2();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error di menu inventory view action performed karena " + ex);
        }
        JSPutama.add(jifReturView);
        int x = (screenSize.width - MainFrame.WIDTH) / 3;
        int y = (screenSize.height - MainFrame.HEIGHT) / 8;
        jifReturView.setLocation(x, y);
        jifReturView.setVisible(true);
    }

    public void callMenuTransaksiInventory() {
        jIFInventory1 = null;
        try {
            jIFInventory1 = new JIFInventory();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error di menu inventory view action performed karena " + ex);
        }
        JSPutama.add(jIFInventory1);
        int x = (screenSize.width - MainFrame.WIDTH) / 3;
        int y = (screenSize.height - MainFrame.HEIGHT) / 8;
        jIFInventory1.setLocation(x, y);
        jIFInventory1.setVisible(true);
    }

    public void callMenuTransaksiInventoryBB() {
        jIFInventory = null;
        try {
            jIFInventory = new JIFInventoryBB();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error di menu inventory view action performed karena " + ex);
        }
        JSPutama.add(jIFInventory);
        int x = (screenSize.width - MainFrame.WIDTH) / 3;
        int y = (screenSize.height - MainFrame.HEIGHT) / 8;
        jIFInventory.setLocation(x, y);
        jIFInventory.setVisible(true);
    }

    public void callMenuProsesRetur() {
        jIFPRosesRetur = null;
        try {
            jIFPRosesRetur = new JIFPRosesRetur2();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error di menu proses retur action performed karena " + ex);
        }
        JSPutama.add(jIFPRosesRetur);
        int x = (screenSize.width - MainFrame.WIDTH) / 3;
        int y = (screenSize.height - MainFrame.HEIGHT) / 7;
        jIFPRosesRetur.setLocation(x, y);
        jIFPRosesRetur.setVisible(true);
    }

    public void callMenuProsesReturBB() {
        jIFPRosesReturBB = null;
        try {
            jIFPRosesReturBB = new JIFPRosesReturBB();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error di menu proses retur action performed karena " + ex);
        }
        JSPutama.add(jIFPRosesReturBB);
        int x = (screenSize.width - MainFrame.WIDTH) / 3;
        int y = (screenSize.height - MainFrame.HEIGHT) / 7;
        jIFPRosesReturBB.setLocation(x, y);
        jIFPRosesReturBB.setVisible(true);
    }

    private void btProsesReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProsesReturActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuProsesRetur();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btProsesReturActionPerformed

    private void btReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReturActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuTransaksiRetur();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btReturActionPerformed

    private void btInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInventoryActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuTransaksiInventory();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btInventoryActionPerformed
    /**/

    public void callMenuReportRetur() {
        JIFReportRetur fReportretur = null;
        try {
            fReportretur = new JIFReportRetur();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(fReportretur);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 6;
        fReportretur.setLocation(x, y);
        fReportretur.setSize(440, 270);
        fReportretur.setVisible(true);
    }

    public void callMenuReportReturPengembalianBK() {
        JIFReportPengembalianBarangKecilSupplier jIFReportPengembalianBarangKecilSupplier = null;
        try {
            jIFReportPengembalianBarangKecilSupplier = new JIFReportPengembalianBarangKecilSupplier();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFReportPengembalianBarangKecilSupplier);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 6;
        jIFReportPengembalianBarangKecilSupplier.setLocation(x, y);
        jIFReportPengembalianBarangKecilSupplier.setSize(400, 200);
        jIFReportPengembalianBarangKecilSupplier.setVisible(true);
    }

    public void callMenuReportReturPengembalianBB() {
        JIFReportPengembalianBarangBesar jIFReportPengembalianBarangBesar = null;
        try {
            jIFReportPengembalianBarangBesar = new JIFReportPengembalianBarangBesar();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFReportPengembalianBarangBesar);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 6;
        jIFReportPengembalianBarangBesar.setLocation(x, y);
        jIFReportPengembalianBarangBesar.setSize(400, 200);
        jIFReportPengembalianBarangBesar.setVisible(true);
    }

    public void callDialogReturBK() {
        int y = 250;
        int x = 500;
        DialogReturBK.setModal(true);
        DialogReturBK.setLocation(x, y);
        DialogReturBK.setSize(400, 250);
        DialogReturBK.show();
    }

    public void callDialogReturBB() {
        int y = 250;
        int x = 500;
        DialogReturBB.setModal(true);
        DialogReturBB.setLocation(x, y);
        DialogReturBB.setSize(400, 250);
        DialogReturBB.show();
    }

    private void btReportReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportReturActionPerformed
        // TODO add your handling code here:
        callDialogReturBK();
    }//GEN-LAST:event_btReportReturActionPerformed

    public void callMenuPriceList() {
        JIFPriceListReport jIFPriceListReport = null;
        try {
            jIFPriceListReport = new JIFPriceListReport();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFPriceListReport);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 6;
        jIFPriceListReport.setLocation(x, y);
        jIFPriceListReport.setSize(400, 200);
        jIFPriceListReport.setVisible(true);
    }

    private void btPriceListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPriceListActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuPriceList();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btPriceListActionPerformed

    private void btBarangBesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBarangBesarActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuBarangBesar();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btBarangBesarActionPerformed
    /*
    private void btStokUlangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StokUlangActionPerformed
        // TODO add your handling code here:
     callMenuStokUlangBB();
    }//GEN-LAST:event_StokUlangActionPerformed

    private void btMerekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTipe1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btTipe1ActionPerformed

    private void btSatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMerek1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btMerek1ActionPerformed

    private void btUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSatuan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btSatuan1ActionPerformed

    private void btSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUser1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btUser1ActionPerformed

    private void btBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBarangKecilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btBarangKecilActionPerformed

    private void btPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBarangKecil1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btBarangKecil1ActionPerformed
*/
    private void StokUlangBarangBesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StokUlangBarangBesarActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuStokUlangBB();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_StokUlangBarangBesarActionPerformed
    /*
    private void btKasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StokUlangBarangKecil1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StokUlangBarangKecil1ActionPerformed

    private void btProsesReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKasir1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btKasir1ActionPerformed

    private void btReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProsesRetur1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btProsesRetur1ActionPerformed

    private void btInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRetur1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btRetur1ActionPerformed

    private void btReportMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRetur2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btRetur2ActionPerformed

    private void btReportMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportMaster1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btReportMaster1ActionPerformed

    private void btDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportPriceList2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btReportPriceList2ActionPerformed

    private void btMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDashboard1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btDashboard1ActionPerformed

    private void btTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDashboard2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btDashboard2ActionPerformed

    private void btReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDashboard3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btDashboard3ActionPerformed

    private void btMaintenanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDashboard4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btDashboard4ActionPerformed

    private void btNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNamaBarang1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btNamaBarang1ActionPerformed

    private void btReportTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportTransaksi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btReportTransaksi1ActionPerformed
*/

    public void callMenuInventory() {
        JIFReportInventory fReportInvent = null;
        try {
            fReportInvent = new JIFReportInventory();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(fReportInvent);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 6;
        fReportInvent.setLocation(x, y);
        fReportInvent.setSize(400, 200);
        fReportInvent.setVisible(true);
    }

    private void btReportInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportInventoryActionPerformed
        // TODO add your handling code here:        
        callMenuInventory();

    }//GEN-LAST:event_btReportInventoryActionPerformed

    public void callMenuReportMaster() {
        JIFReportMaster fReportmaster = null;
        try {
            fReportmaster = new JIFReportMaster();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(fReportmaster);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 6;
        fReportmaster.setLocation(x, y);
        fReportmaster.setVisible(true);
    }

    private void btReportMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportMasterActionPerformed
        // TODO add your handling code here:
        callMenuReportMaster();
    }//GEN-LAST:event_btReportMasterActionPerformed

    public void callMenuReportTransaksi() {
        JIFReportTransaksi fReporttrans = null;
        try {
            fReporttrans = new JIFReportTransaksi();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(fReporttrans);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 6;
        fReporttrans.setLocation(x, y);
        fReporttrans.setSize(400, 200);
        fReporttrans.setVisible(true);

    }

    public void callMenuReportTransaksiBarangBesar() {
        JIFReportTransaksiBarangBesar fReporttrans = null;
        try {
            fReporttrans = new JIFReportTransaksiBarangBesar();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(fReporttrans);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 6;
        fReporttrans.setLocation(x, y);
        fReporttrans.setSize(400, 200);
        fReporttrans.setVisible(true);

    }

    private void btReportTransaksiBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportTransaksiBBActionPerformed
        // TODO add your handling code here:
        callMenuReportTransaksiBarangBesar();
    }//GEN-LAST:event_btReportTransaksiBBActionPerformed

    private void btKasirBarangBesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKasirBarangBesarActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuKasir2();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btKasirBarangBesarActionPerformed

    public void callMenuReportPriceList() {
        JIFPriceListReport fReporttrans = null;
        try {
            fReporttrans = new JIFPriceListReport();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(fReporttrans);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = 50;
        fReporttrans.setLocation(x, y);
        fReporttrans.setVisible(true);
    }
    private void btReportPriceListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportPriceListActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuReportPriceList();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btReportPriceListActionPerformed

    private void btProsesReturBarangBesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProsesReturBarangBesarActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuProsesReturBB();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btProsesReturBarangBesarActionPerformed

    private void btReturBarangBesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReturBarangBesarActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuTransaksiReturBB();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btReturBarangBesarActionPerformed

    private void btInventoryBarangBesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInventoryBarangBesarActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuTransaksiInventoryBB();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btInventoryBarangBesarActionPerformed

    private void btReportTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportTransaksiActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuReportTransaksi();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btReportTransaksiActionPerformed

    public void callMenuReportReturBarangBesar() {
        JIFReportReturBarangBesar fReportretur = null;
        try {
            fReportretur = new JIFReportReturBarangBesar();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(fReportretur);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 6;
        fReportretur.setLocation(x, y);
        fReportretur.setSize(440, 270);
        fReportretur.setVisible(true);
    }

    private void btReportReturBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportReturBBActionPerformed
        // TODO add your handling code here:
        callDialogReturBB();
    }//GEN-LAST:event_btReportReturBBActionPerformed

    public void callMenuInventoryBarangBesar() {
        JIFReportInventoryBarangBesar fReportInvent = null;
        try {
            fReportInvent = new JIFReportInventoryBarangBesar();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(fReportInvent);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 6;
        fReportInvent.setLocation(x, y);
        fReportInvent.setSize(400, 200);
        fReportInvent.setVisible(true);
    }

    private void btReportInventoryBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportInventoryBBActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuInventoryBarangBesar();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btReportInventoryBBActionPerformed

    public void callMenuTransferBarangBesar() {
        JIFTransferBB fReportProfit = null;
        try {
            fReportProfit = new JIFTransferBB();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(fReportProfit);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 6;
        fReportProfit.setLocation(x, y);
        fReportProfit.setVisible(true);
    }


    private void btReportTransferBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportTransferBBActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuReportTransferBarangBesar();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btReportTransferBBActionPerformed

    public void callMenuInsentifBK() {
        JIFReportInsentifBarangKecil jIFReportInsentifBarangKecil = null;
        try {
            jIFReportInsentifBarangKecil = new JIFReportInsentifBarangKecil();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFReportInsentifBarangKecil);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 6;
        jIFReportInsentifBarangKecil.setLocation(x, y);
        jIFReportInsentifBarangKecil.setSize(400, 220);
        jIFReportInsentifBarangKecil.setVisible(true);
    }

    public void callMenuInsentif() {
        JIFReportInsentif fReportProfit = null;
        try {
            fReportProfit = new JIFReportInsentif();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(fReportProfit);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 6;
        fReportProfit.setLocation(x, y);
        fReportProfit.setSize(400, 220);
        fReportProfit.setVisible(true);
    }
    private void btReportInsentifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportInsentifActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuInsentif();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btReportInsentifActionPerformed

    public void callMenuPenjualBB() {
        jIFPenjualBBView = null;
        try {
            jIFPenjualBBView = new JIFPenjualBarangBesarView();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error di menu merek action performed karena " + ex);
        }
        JSPutama.add(jIFPenjualBBView);
        int x = (screenSize.width - MainFrame.WIDTH) / 3;
        int y = (screenSize.height - MainFrame.HEIGHT) / 3;
        jIFPenjualBBView.setLocation(x, y);
        jIFPenjualBBView.setVisible(true);
    }

    public void callMenuBarangBonusKaryawan() {
        jIFBarangBonusKaryawanBKView = null;
        try {
            jIFBarangBonusKaryawanBKView = new JIFBarangBonusKaryawanBKView();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error di menu Barang Bonus Karyawan action performed karena " + ex);
        }
        JSPutama.add(jIFBarangBonusKaryawanBKView);
        int x = (screenSize.width - MainFrame.WIDTH) / 2;
        int y = (screenSize.height - MainFrame.HEIGHT) / 2;
        jIFBarangBonusKaryawanBKView.setLocation(x, y);
        jIFBarangBonusKaryawanBKView.setVisible(true);
    }

    public void callMenuSetupBonus() {
        jIFSetupBonusView = null;
        try {
            jIFSetupBonusView = new JIFSetupBonusView();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error di menu Barang Bonus Karyawan action performed karena " + ex);
        }
        JSPutama.add(jIFSetupBonusView);
        int x = (screenSize.width - MainFrame.WIDTH) / 3;
        int y = (screenSize.height - MainFrame.HEIGHT) / 3;
        jIFSetupBonusView.setLocation(x, y);
        jIFSetupBonusView.setVisible(true);
    }

    public void callMenuBarangBonusKaryawanBB() {
        jIFBarangBonusKaryawanBBView = null;
        try {
            jIFBarangBonusKaryawanBBView = new JIFBarangBonusKaryawanBBView();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error di menu Barang Bonus Karyawan action performed karena " + ex);
        }
        JSPutama.add(jIFBarangBonusKaryawanBBView);
        int x = (screenSize.width - MainFrame.WIDTH) / 2;
        int y = (screenSize.height - MainFrame.HEIGHT) / 2;
        jIFBarangBonusKaryawanBBView.setLocation(x, y);
        jIFBarangBonusKaryawanBBView.setVisible(true);
    }

    public void callMenuPenjualBK() {
        jIFPenjualBKView = null;
        try {
            jIFPenjualBKView = new JIFPenjualBKView();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error di menu merek action performed karena " + ex);
        }
        JSPutama.add(jIFPenjualBKView);
        int x = (screenSize.width - MainFrame.WIDTH) / 3;
        int y = (screenSize.height - MainFrame.HEIGHT) / 3;
        jIFPenjualBKView.setLocation(x, y);
        jIFPenjualBKView.setVisible(true);
    }

    private void btPenjualBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPenjualBBActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuPenjualBB();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btPenjualBBActionPerformed

    public void callMenuBarangToko() {
        jIFBarangToko = null;
        try {
            jIFBarangToko = new JIFBarangToko();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFBarangToko);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 5;
        jIFBarangToko.setLocation(x, y);
        jIFBarangToko.setVisible(true);
    }

    private void btBarangTokoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBarangTokoActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuBarangToko();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btBarangTokoActionPerformed

    public void callMenuReportTransferBarangBesar() {
        jIFREportTransferBB = null;
        try {
            jIFREportTransferBB = new JIFREportTransferBB();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFREportTransferBB);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 5;
        jIFREportTransferBB.setLocation(x, y);
        jIFREportTransferBB.setSize(400, 200);
        jIFREportTransferBB.setVisible(true);
    }

    private void btTransferBarangBesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTransferBarangBesarActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuTransferBarangBesar();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btTransferBarangBesarActionPerformed

    private void btReportProfitBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportProfitBBActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuProfitBarangBesar();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btReportProfitBBActionPerformed

    private void btManajemenTransaksiBarangKecilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btManajemenTransaksiBarangKecilActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuManajemenTransaksiBk();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btManajemenTransaksiBarangKecilActionPerformed

    private void btManajemenTransaksiBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btManajemenTransaksiBBActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuManajemenTransaksiBB();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btManajemenTransaksiBBActionPerformed

    private void btPenjualBKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPenjualBKActionPerformed
        // TODO add your handling code here:
        callMenuPenjualBK();
    }//GEN-LAST:event_btPenjualBKActionPerformed

    public void callMenuChart() {
        jIFChartView = null;
        try {
            jIFChartView = new JIFChartView();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error di menu chart view action performed karena " + ex);
        }
        JSPutama.add(jIFChartView);
        int x = (screenSize.width - MainFrame.WIDTH) / 3;
        int y = (screenSize.height - MainFrame.HEIGHT) / 3;
        jIFChartView.setSize(300, 150);
        jIFChartView.setLocation(x, y);
        jIFChartView.setVisible(true);
    }

    private void btChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btChartActionPerformed
        // TODO add your handling code here:
        callMenuChart();
    }//GEN-LAST:event_btChartActionPerformed

    private void btReturCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReturCustomerActionPerformed
        // TODO add your handling code here:
        DialogReturBK.dispose();
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuReportRetur();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btReturCustomerActionPerformed

    private void btReturSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReturSupplierActionPerformed
        // TODO add your handling code here:
        DialogReturBK.dispose();
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuReportReturPengembalianBK();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btReturSupplierActionPerformed

    private void btReturCustomer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReturCustomer1ActionPerformed
        // TODO add your handling code here:
        DialogReturBB.dispose();
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuReportReturBarangBesar();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btReturCustomer1ActionPerformed

    private void btReturSupplier1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReturSupplier1ActionPerformed
        // TODO add your handling code here:
        DialogReturBB.dispose();
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuReportReturPengembalianBB();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btReturSupplier1ActionPerformed

    private void callMenuUpdateGlobal() {
        jIFUpdateGlobalBK = null;
        try {
            jIFUpdateGlobalBK = new JIFUpdateGlobalBK();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFUpdateGlobalBK);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 5;
        jIFUpdateGlobalBK.setLocation(x, y);
        jIFUpdateGlobalBK.setVisible(true);
    }

    private void callMenuUpdateGlobalBB() {
        jIFUpdateGlobalBB = null;
        try {
            jIFUpdateGlobalBB = new JIFUpdateGlobalBB();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFUpdateGlobalBB);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 5;
        jIFUpdateGlobalBB.setLocation(x, y);
        jIFUpdateGlobalBB.setVisible(true);
    }

    private void callMenuHistoryBarang() {
        jIFHistoryBarang = null;
        try {
            jIFHistoryBarang = new JIFHistoryBarang();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFHistoryBarang);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 5;
        jIFHistoryBarang.setLocation(x, y);
        jIFHistoryBarang.setVisible(true);
    }

    private void callMenuHistoryBarangBesar() {
        jIFHistoryBarangBesar = null;
        try {
            jIFHistoryBarangBesar = new JIFHistoryBarangBesar();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jIFHistoryBarangBesar);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 5;
        jIFHistoryBarangBesar.setLocation(x, y);
        jIFHistoryBarangBesar.setVisible(true);
    }

    private void btUpdateGlobalBKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateGlobalBKActionPerformed
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuUpdateGlobal();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btUpdateGlobalBKActionPerformed

    private void btHistoryBarangBKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHistoryBarangBKActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuHistoryBarang();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btHistoryBarangBKActionPerformed

    private void btUpdateGlobalBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateGlobalBBActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuUpdateGlobalBB();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btUpdateGlobalBBActionPerformed

    private void btHistoryBarangBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHistoryBarangBBActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuHistoryBarangBesar();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btHistoryBarangBBActionPerformed

    private void btBarangKecilExcludeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBarangKecilExcludeActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuBarangBonusKaryawan();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btBarangKecilExcludeActionPerformed

    private void btInsentifBKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInsentifBKActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuInsentifBK();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btInsentifBKActionPerformed

    private void btBarangBesarExcludeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBarangBesarExcludeActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuBarangBonusKaryawanBB();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btBarangBesarExcludeActionPerformed

    private void btSetupBonusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSetupBonusActionPerformed
        // TODO add your handling code here:
        final loading d = new loading(this, true);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                callMenuSetupBonus();
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
            }

            @Override
            protected void done() {
                d.dispose();
            }
        };
        worker.execute();
        d.setVisible(true);
    }//GEN-LAST:event_btSetupBonusActionPerformed

    private void callMenuManajemenTransaksiBk() {
        jifmtp = null;
        try {
            jifmtp = new JIFManajemenTransaksiPenjualan();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jifmtp);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 5;
        jifmtp.setLocation(x, y);
        jifmtp.setVisible(true);
    }

    private void callMenuManajemenTransaksiBB() {
        jiftpbb = null;
        try {
            jiftpbb = new JIFTransaksiPenjualanBB();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSPutama.add(jiftpbb);
        int x = (screenSize.width - MainFrame.WIDTH) / 4;
        int y = (screenSize.height - MainFrame.HEIGHT) / 5;
        jiftpbb.setLocation(x, y);
        jiftpbb.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    /*    public static void main(String args[]) {
     /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    /*        try {
     for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
     if ("Nimbus".equals(info.getName())) {
     javax.swing.UIManager.setLookAndFeel(info.getClassName());
     break;
     }
     }
     } catch (ClassNotFoundException ex) {
     java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
     } catch (InstantiationException ex) {
     java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
     } catch (IllegalAccessException ex) {
     java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
     } catch (javax.swing.UnsupportedLookAndFeelException ex) {
     java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
     }
     //</editor-fold>

     /* Create and display the form */
    /*java.awt.EventQueue.invokeLater(new Runnable() {
     public void run() {
     MainFrame x = new MainFrame();                
     x.setVisible(true);
     }
     });
     }*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Center;
    private javax.swing.JDialog DialogAbout;
    private javax.swing.JDialog DialogLisensi;
    private javax.swing.JDialog DialogMaintenance;
    private javax.swing.JDialog DialogReturBB;
    private javax.swing.JDialog DialogReturBK;
    private javax.swing.JPanel Down;
    private javax.swing.JInternalFrame JIFDashBoard;
    private javax.swing.JDesktopPane JSPutama;
    private javax.swing.JPanel Left;
    private javax.swing.JMenu Login;
    private javax.swing.JMenu Main;
    private javax.swing.JMenuItem SetTema;
    private Sinarelektronikapp.swinglib.loadBtImage StokUlangBarangBesar;
    private javax.swing.JPanel Up;
    private Sinarelektronikapp.swinglib.loadBtImage btBackUp;
    private Sinarelektronikapp.swinglib.loadBtImage btBarang;
    private Sinarelektronikapp.swinglib.loadBtImage btBarangBesar;
    private Sinarelektronikapp.swinglib.loadBtImage btBarangBesarExclude;
    private Sinarelektronikapp.swinglib.loadBtImage btBarangKecilExclude;
    private Sinarelektronikapp.swinglib.loadBtImage btBarangToko;
    private Sinarelektronikapp.swinglib.loadBtImage btChart;
    private Sinarelektronikapp.swinglib.loadBtImage btDashboard;
    private Sinarelektronikapp.swinglib.loadBtImage btHistoryBarangBB;
    private Sinarelektronikapp.swinglib.loadBtImage btHistoryBarangBK;
    private Sinarelektronikapp.swinglib.loadBtImage btInsentifBK;
    private Sinarelektronikapp.swinglib.loadBtImage btInventory;
    private Sinarelektronikapp.swinglib.loadBtImage btInventoryBarangBesar;
    private Sinarelektronikapp.swinglib.loadBtImage btKasir;
    private Sinarelektronikapp.swinglib.loadBtImage btKasirBarangBesar;
    private javax.swing.JButton btLogin;
    private Sinarelektronikapp.swinglib.loadBtImage btMaintenance;
    private Sinarelektronikapp.swinglib.loadBtImage btManajemenTransaksiBB;
    private Sinarelektronikapp.swinglib.loadBtImage btManajemenTransaksiBarangKecil;
    private Sinarelektronikapp.swinglib.loadBtImage btMaster;
    private Sinarelektronikapp.swinglib.loadBtImage btMerek;
    private Sinarelektronikapp.swinglib.loadBtImage btNamaBarang;
    private Sinarelektronikapp.swinglib.loadBtImage btPelanggan;
    private Sinarelektronikapp.swinglib.loadBtImage btPenjualBB;
    private Sinarelektronikapp.swinglib.loadBtImage btPenjualBK;
    private Sinarelektronikapp.swinglib.loadBtImage btProsesRetur;
    private Sinarelektronikapp.swinglib.loadBtImage btProsesReturBarangBesar;
    private Sinarelektronikapp.swinglib.loadBtImage btReport;
    private Sinarelektronikapp.swinglib.loadBtImage btReportInsentif;
    private Sinarelektronikapp.swinglib.loadBtImage btReportInventory;
    private Sinarelektronikapp.swinglib.loadBtImage btReportInventoryBB;
    private Sinarelektronikapp.swinglib.loadBtImage btReportMaster;
    private Sinarelektronikapp.swinglib.loadBtImage btReportPriceList;
    private Sinarelektronikapp.swinglib.loadBtImage btReportProfit;
    private Sinarelektronikapp.swinglib.loadBtImage btReportProfitBB;
    private Sinarelektronikapp.swinglib.loadBtImage btReportRetur;
    private Sinarelektronikapp.swinglib.loadBtImage btReportReturBB;
    private Sinarelektronikapp.swinglib.loadBtImage btReportTransaksi;
    private Sinarelektronikapp.swinglib.loadBtImage btReportTransaksiBB;
    private Sinarelektronikapp.swinglib.loadBtImage btReportTransferBB;
    private javax.swing.JButton btReset;
    private Sinarelektronikapp.swinglib.loadBtImage btRetur;
    private Sinarelektronikapp.swinglib.loadBtImage btReturBarangBesar;
    private javax.swing.JButton btReturCustomer;
    private javax.swing.JButton btReturCustomer1;
    private javax.swing.JButton btReturSupplier;
    private javax.swing.JButton btReturSupplier1;
    private javax.swing.JButton btSambungkan;
    private Sinarelektronikapp.swinglib.loadBtImage btSatuan;
    private Sinarelektronikapp.swinglib.loadBtImage btSetupBonus;
    private Sinarelektronikapp.swinglib.loadBtImage btStokUlang;
    private Sinarelektronikapp.swinglib.loadBtImage btSupplier;
    private javax.swing.JButton btTes;
    private Sinarelektronikapp.swinglib.loadBtImage btTipe;
    private Sinarelektronikapp.swinglib.loadBtImage btTransaksi;
    private Sinarelektronikapp.swinglib.loadBtImage btTransferBarangBesar;
    private Sinarelektronikapp.swinglib.loadBtImage btUpdateGlobalBB;
    private Sinarelektronikapp.swinglib.loadBtImage btUpdateGlobalBK;
    private Sinarelektronikapp.swinglib.loadBtImage btUser;
    private javax.swing.JComboBox cmbTema;
    private javax.swing.JPanel dashboard;
    private javax.swing.JDialog dialogLogin;
    private javax.swing.JDialog dialogSetNetwork;
    private javax.swing.JDialog dialogSetTema;
    private javax.swing.JPanel down;
    private javax.swing.JLabel imageDashboard;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuItem jaringan;
    private javax.swing.JPanel kanan;
    private javax.swing.JPanel kiri;
    private javax.swing.JPanel laporan;
    private javax.swing.JPanel maintenance;
    private Sinarelektronikapp.maintenance.maintenanceView maintenanceView1;
    private javax.swing.JPanel master;
    private javax.swing.JMenuItem menuDashboard;
    private javax.swing.JMenuItem menuLogin;
    private javax.swing.JMenuItem menuLogout;
    private javax.swing.JMenu setting;
    private javax.swing.JPanel transaksi;
    private javax.swing.JTextField txtIP;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JPanel up;
    // End of variables declaration//GEN-END:variables

}

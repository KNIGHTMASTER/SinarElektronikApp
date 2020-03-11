/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.retur.barangkecil.view;

import com.wissensalt.sinarelektronik.util.FormatRupiah;
import com.wissensalt.sinarelektronik.util.InputHarusAngka;
import com.wissensalt.sinarelektronik.util.Waktu;
import com.wissensalt.sinarelektronik.penjualan.barangbesar.view.JIFKasirView2;
import com.wissensalt.sinarelektronik.config.ActiveUser;
import com.wissensalt.sinarelektronik.config.HostName;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.controller.BarangController;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.database.barangDatabase;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.entity.BarangKecilDTO;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.error.BarangException;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.model.BarangKecilModel;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.model.event.BarangKecilListener;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.model.TabelModelBarangKecil;
import com.wissensalt.sinarelektronik.dao.BarangKecilDAO;
import com.wissensalt.sinarelektronik.retur.barangkecil.controller.ReturController;
import com.wissensalt.sinarelektronik.retur.barangkecil.entity.Retur;
import com.wissensalt.sinarelektronik.retur.barangkecil.model.Event.ReturListener;
import com.wissensalt.sinarelektronik.retur.barangkecil.model.ReturModel;
import com.wissensalt.sinarelektronik.retur.barangkecil.model.TabelModelRetur;
import com.wissensalt.sinarelektronik.swinglib.AutoComplete.DefaultModelAutoComplete;
import com.wissensalt.sinarelektronik.swinglib.AutoComplete.TextFieldAutoComplete;
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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;

/**
 *
 * @author Fauzi
 */
public class JIFReturView2 extends javax.swing.JInternalFrame implements ReturListener, BarangKecilListener {

    /**
     * Creates new form JIFReturView2
     */
    
    int totalMain = 0;
    
    InputHarusAngka iha = new InputHarusAngka();
    
    FormatRupiah fr = new FormatRupiah();
    
    ReturController controller;
    
    ReturModel model;
    
    TabelModelRetur tabelModelRetur;        
    
    Waktu w = new Waktu();
    
    private TabelModelBarangKecil tabelmodelBarangKecil;
    
    private BarangController controllerBarang;
    
    private BarangKecilModel modelBarang;
    
    
    public JIFReturView2() {        
        tabelmodelBarangKecil = new TabelModelBarangKecil();
        
        modelBarang=  new BarangKecilModel();
        modelBarang.setListener(this);        
        
        controllerBarang = new BarangController();
        controllerBarang.setModel(modelBarang);
        
        model = new ReturModel();
        model.setListener(this);

        controller =  new ReturController();
        controller.setModel(model);
        initComponents();
        
        tabelRetur.setRowSelectionAllowed(true);
        tabelRetur.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        tabelModelRetur = new TabelModelRetur();
        tabelRetur.setModel(tabelModelRetur);
        
        tabelBarang1.setModel(tabelmodelBarangKecil);
        try {
            loadDatabaseCariBarang();
        } catch (SQLException ex) {
            Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BarangException ex) {
            Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       setTxtkodeAuto(); 
        
    ActionListener taskPerformer = new ActionListener() {
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
        txtjam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
        
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
              @Override
              public boolean dispatchKeyEvent(KeyEvent e) {
                  if(e.getID() == KeyEvent.KEY_PRESSED){
                      if(e.getKeyCode() == KeyEvent.VK_F5){
                          //inputPembayaran();
                      }
                  }
                return false;
              }
          });
      }
    };
    // Timer
    new Timer(1000, taskPerformer).start();                            

    inisiasiDataAwal();
    }
    
    public void loadView(){        
        ActiveUser activeUser = new ActiveUser();
        txttanggal.setText(loadTanggal());
        txtuser.setText(activeUser.getUserActive());
    }     
    public void setEnableTransaksi(boolean enable){        
        txtkodeitem.setEnabled(enable);
        txtjumlahretur.setEnabled(enable);        
    }
    
    Date date=new Date();        
    
    SimpleDateFormat formatTanggal=new SimpleDateFormat("dd:MM:yyyy");
    SimpleDateFormat formatTanggal2=new SimpleDateFormat("yyyy-MM-dd");
    
    private String loadTanggal(){        
        return formatTanggal.format(date);
    }
    
    public ActiveUser getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(ActiveUser activeUser) {
        this.activeUser = activeUser;
    }

    public JComboBox getCmbPenukarang() {
        return cmbPenukarang;
    }

    public void setCmbPenukarang(JComboBox cmbPenukarang) {
        this.cmbPenukarang = cmbPenukarang;
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

    public JTable getTabelRetur() {
        return tabelRetur;
    }

    public void setTabelRetur(JTable tabelRetur) {
        this.tabelRetur = tabelRetur;
    }

    public JTextField getTxtKataKunci1() {
        return txtKataKunci1;
    }

    public void setTxtKataKunci1(JTextField txtKataKunci1) {
        this.txtKataKunci1 = txtKataKunci1;
    }

    public JTextField getTxtSubHarga() {
        return txtSubHarga;
    }

    public void setTxtSubHarga(JTextField txtSubHarga) {
        this.txtSubHarga = txtSubHarga;
    }

    public JTextField getTxthargaperitem() {
        return txthargaperitem;
    }

    public void setTxthargaperitem(JTextField txthargaperitem) {
        this.txthargaperitem = txthargaperitem;
    }

    public JTextField getTxtjam() {
        return txtjam;
    }

    public void setTxtjam(JTextField txtjam) {
        this.txtjam = txtjam;
    }

    public JTextField getTxtjumlahretur() {
        return txtjumlahretur;
    }

    public void setTxtjumlahretur(JTextField txtjumlahretur) {
        this.txtjumlahretur = txtjumlahretur;
    }

    public TextFieldAutoComplete getTxtkodeitem() {
        return txtkodeitem;
    }

    public void setTxtkodeitem(TextFieldAutoComplete txtkodeitem) {
        this.txtkodeitem = txtkodeitem;
    }

    public JTextField getTxtnamabarang() {
        return txtnamabarang;
    }

    public void setTxtnamabarang(JTextField txtnamabarang) {
        this.txtnamabarang = txtnamabarang;
    }

    public JTextField getTxtnoretur() {
        return txtnoretur;
    }

    public void setTxtnoretur(JTextField txtnoretur) {
        this.txtnoretur = txtnoretur;
    }

    public JTextField getTxttanggal() {
        return txttanggal;
    }

    public void setTxttanggal(JTextField txttanggal) {
        this.txttanggal = txttanggal;
    }

    public JTextField getTxttotal() {
        return txttotal;
    }

    public void setTxttotal(JTextField txttotal) {
        this.txttotal = txttotal;
    }

    public JTextField getTxtuser() {
        return txtuser;
    }

    public void setTxtuser(JTextField txtuser) {
        this.txtuser = txtuser;
    }

    
    
    public void inisiasiDataAwal(){
        txtnoretur.setText(String.valueOf(getIdTransaksi()));
        txtuser.setText(getUser());
        txttanggal.setText(getTanggal());
        txtkodeitem.setEditable(false);
        btOpenCari.setEnabled(false);
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
        txtSubHarga = new javax.swing.JTextField();
        palingAtas = new javax.swing.JPanel();
        atas = new javax.swing.JPanel();
        kiri = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        kanan = new javax.swing.JPanel();
        kanansatu = new javax.swing.JPanel();
        txtnoretur = new javax.swing.JTextField();
        txtuser = new javax.swing.JTextField();
        txttanggal = new javax.swing.JTextField();
        txtjam = new javax.swing.JTextField();
        kanandua = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        tengah = new javax.swing.JPanel();
        satu = new javax.swing.JPanel();
        satukiri = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        satukanan = new javax.swing.JPanel();
        panelKode = new javax.swing.JPanel();
        btOpenCari = new javax.swing.JButton();
        txtkodeitem = new com.wissensalt.sinarelektronik.swinglib.AutoComplete.TextFieldAutoComplete();
        txtnamabarang = new javax.swing.JTextField();
        dua = new javax.swing.JPanel();
        duakiri = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        duakanan = new javax.swing.JPanel();
        txthargaperitem = new javax.swing.JTextField();
        txtjumlahretur = new javax.swing.JTextField();
        tiga = new javax.swing.JPanel();
        tigakiri = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cmbPenukarang = new javax.swing.JComboBox();
        tigakanan = new javax.swing.JPanel();
        btGo = new javax.swing.JButton();
        bawah = new javax.swing.JPanel();
        PanelTabel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelRetur = new javax.swing.JTable();
        PanelTombol = new javax.swing.JPanel();
        btTambah = new javax.swing.JButton();
        btSimpan = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();
        btReset = new javax.swing.JButton();

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

        javax.swing.GroupLayout atas2Layout = new javax.swing.GroupLayout(atas2);
        atas2.setLayout(atas2Layout);
        atas2Layout.setHorizontalGroup(
            atas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(atas2Layout.createSequentialGroup()
                .addComponent(txtKataKunci1, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCari1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        atas2Layout.setVerticalGroup(
            atas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtKataKunci1)
            .addComponent(btCari1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

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

        txtSubHarga.setEditable(false);
        txtSubHarga.setFont(new java.awt.Font("Tahoma", 0, 45)); // NOI18N
        txtSubHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubHargaActionPerformed(evt);
            }
        });
        txtSubHarga.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSubHargaFocusGained(evt);
            }
        });

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Transaksi Retur Barang Kecil");

        palingAtas.setPreferredSize(new java.awt.Dimension(500, 300));
        palingAtas.setLayout(new java.awt.BorderLayout());

        atas.setLayout(new java.awt.BorderLayout());

        kiri.setLayout(new java.awt.GridLayout(4, 0));

        jLabel1.setText("No. Retur :");
        kiri.add(jLabel1);

        jLabel2.setText("User :");
        kiri.add(jLabel2);

        jLabel3.setText("Tanggal :");
        kiri.add(jLabel3);

        jLabel4.setText("Jam :");
        kiri.add(jLabel4);

        atas.add(kiri, java.awt.BorderLayout.WEST);

        kanan.setLayout(new java.awt.BorderLayout());

        kanansatu.setPreferredSize(new java.awt.Dimension(150, 80));
        kanansatu.setLayout(new java.awt.GridLayout(4, 0));

        txtnoretur.setEditable(false);
        txtnoretur.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        kanansatu.add(txtnoretur);

        txtuser.setEditable(false);
        txtuser.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        kanansatu.add(txtuser);

        txttanggal.setEditable(false);
        txttanggal.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        kanansatu.add(txttanggal);

        txtjam.setEditable(false);
        txtjam.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        kanansatu.add(txtjam);

        kanan.add(kanansatu, java.awt.BorderLayout.WEST);

        kanandua.setLayout(new java.awt.BorderLayout());

        jLabel10.setText("Total :");
        kanandua.add(jLabel10, java.awt.BorderLayout.WEST);

        txttotal.setEditable(false);
        txttotal.setFont(new java.awt.Font("Tahoma", 1, 90)); // NOI18N
        txttotal.setForeground(new java.awt.Color(255, 0, 0));
        kanandua.add(txttotal, java.awt.BorderLayout.CENTER);

        kanan.add(kanandua, java.awt.BorderLayout.CENTER);

        atas.add(kanan, java.awt.BorderLayout.CENTER);

        palingAtas.add(atas, java.awt.BorderLayout.CENTER);

        tengah.setMaximumSize(new java.awt.Dimension(32767, 80));
        tengah.setPreferredSize(new java.awt.Dimension(400, 80));
        tengah.setLayout(new java.awt.GridLayout(1, 3));

        satu.setLayout(new java.awt.BorderLayout());

        satukiri.setLayout(new java.awt.GridLayout(2, 0));

        jLabel5.setText("Kode Item :");
        satukiri.add(jLabel5);

        jLabel6.setText("Nama Barang :");
        satukiri.add(jLabel6);

        satu.add(satukiri, java.awt.BorderLayout.WEST);

        satukanan.setLayout(new java.awt.GridLayout(2, 0));

        panelKode.setLayout(new java.awt.BorderLayout());

        btOpenCari.setMnemonic('F');
        btOpenCari.setText("...");
        btOpenCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOpenCariActionPerformed(evt);
            }
        });
        btOpenCari.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                btOpenCariFocusLost(evt);
            }
        });
        panelKode.add(btOpenCari, java.awt.BorderLayout.EAST);

        txtkodeitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkodeitemActionPerformed(evt);
            }
        });
        txtkodeitem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtkodeitemFocusLost(evt);
            }
        });
        panelKode.add(txtkodeitem, java.awt.BorderLayout.CENTER);

        satukanan.add(panelKode);

        txtnamabarang.setEditable(false);
        txtnamabarang.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        txtnamabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamabarangActionPerformed(evt);
            }
        });
        txtnamabarang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtnamabarangFocusGained(evt);
            }
        });
        satukanan.add(txtnamabarang);

        satu.add(satukanan, java.awt.BorderLayout.CENTER);

        tengah.add(satu);

        dua.setLayout(new java.awt.BorderLayout());

        duakiri.setLayout(new java.awt.GridLayout(2, 0));

        jLabel7.setText("Harga/ item :");
        duakiri.add(jLabel7);

        jLabel8.setText("Jumlah :");
        duakiri.add(jLabel8);

        dua.add(duakiri, java.awt.BorderLayout.WEST);

        duakanan.setLayout(new java.awt.GridLayout(2, 0));

        txthargaperitem.setEditable(false);
        txthargaperitem.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        duakanan.add(txthargaperitem);

        txtjumlahretur.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        txtjumlahretur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjumlahreturActionPerformed(evt);
            }
        });
        txtjumlahretur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtjumlahreturFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtjumlahreturFocusLost(evt);
            }
        });
        duakanan.add(txtjumlahretur);

        dua.add(duakanan, java.awt.BorderLayout.CENTER);

        tengah.add(dua);

        tiga.setLayout(new java.awt.BorderLayout());

        tigakiri.setLayout(new java.awt.BorderLayout());

        jLabel9.setText("Penukaran :");
        tigakiri.add(jLabel9, java.awt.BorderLayout.WEST);

        cmbPenukarang.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cmbPenukarang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Barang", "Uang" }));
        cmbPenukarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPenukarangActionPerformed(evt);
            }
        });
        cmbPenukarang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbPenukarangFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbPenukarangFocusLost(evt);
            }
        });
        tigakiri.add(cmbPenukarang, java.awt.BorderLayout.CENTER);

        tiga.add(tigakiri, java.awt.BorderLayout.CENTER);

        tigakanan.setLayout(new java.awt.BorderLayout());

        btGo.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        btGo.setText("Go");
        btGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGoActionPerformed(evt);
            }
        });
        tigakanan.add(btGo, java.awt.BorderLayout.CENTER);

        tiga.add(tigakanan, java.awt.BorderLayout.EAST);

        tengah.add(tiga);

        palingAtas.add(tengah, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(palingAtas, java.awt.BorderLayout.CENTER);

        bawah.setPreferredSize(new java.awt.Dimension(500, 350));
        bawah.setLayout(new java.awt.BorderLayout());

        PanelTabel.setPreferredSize(new java.awt.Dimension(452, 300));
        PanelTabel.setLayout(new java.awt.BorderLayout());

        tabelRetur.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelRetur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabelReturMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelReturMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelRetur);

        PanelTabel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        bawah.add(PanelTabel, java.awt.BorderLayout.CENTER);

        PanelTombol.setLayout(new java.awt.GridLayout(1, 4));

        btTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/tambah.png"))); // NOI18N
        btTambah.setMnemonic('T');
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });
        PanelTombol.add(btTambah);

        btSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/simpan.png"))); // NOI18N
        btSimpan.setMnemonic('S');
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });
        PanelTombol.add(btSimpan);

        btHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/hapus.png"))); // NOI18N
        btHapus.setMnemonic('H');
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });
        PanelTombol.add(btHapus);

        btReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/reset.png"))); // NOI18N
        btReset.setMnemonic('R');
        btReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetActionPerformed(evt);
            }
        });
        PanelTombol.add(btReset);

        bawah.add(PanelTombol, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(bawah, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setTxtkodeAuto(){
        DefaultModelAutoComplete model=new DefaultModelAutoComplete(getDataList());
        txtkodeitem.setAutoComplete(model);
    }
    
    public String [] getDataList(){                        
        String [] data = new String[WIDTH];
        koneksi();
        Statement statement = null;
        try {
            statement = c.createStatement();
            ResultSet rs1=statement.executeQuery("select count(idbarang) total from barang");
            if(rs1.next()){
                data=new String[rs1.getInt("total")];
            }
            ResultSet rs=statement.executeQuery("select idbarang from barang");
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
                    Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
        return data;
    }         
    
    public void resetinputan(){
        txtkodeitem.setText("");
        txtnamabarang.setText("");
        txthargaperitem.setText("");
        txtSubHarga.setText("");
        txtkodeitem.requestFocus();        
        txtjumlahretur.setText("1");
        txtjumlahretur.selectAll();

    }
        
        
    public void cariBarang(){
        DialogCariBarang.setSize(800, 700);        
        DialogCariBarang.show();
        DialogCariBarang.setModal(true);
        DialogCariBarang.setTitle("Pencarian Barang");
        DialogCariBarang.setLocationRelativeTo(null);            
    }
    
    private void btOpenCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOpenCariActionPerformed
        // TODO add your handling code here:
        resetinputan();
        cariBarang();
    }//GEN-LAST:event_btOpenCariActionPerformed

    private void btOpenCariFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btOpenCariFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_btOpenCariFocusLost

    ActiveUser activeUser = new ActiveUser();
    
    public String getUser(){
        String user ="";            
        user = activeUser.getUserActive();
        return  user;
    }              

    Connection c;
    HostName ip = new HostName();
    public void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JIFKasirView2.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            c = DriverManager.getConnection("jdbc:mysql://"+ip.getIpServer()+"/sinarelektronik?;", "root", "P@ssw0rd");
        } catch (SQLException ex) {
            Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public String getHarga(){
        koneksi();
        int harga=0;
        String queryGrosir = "SELECT grosir as harga FROM barang WHERE idbarang='"+txtkodeitem.getText()+"'";
        ResultSet rs = null;
        Statement statement = null;
        try{
            statement=c.createStatement();
            rs = statement.executeQuery(queryGrosir);
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
                    Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return String.valueOf(harga);
    }    
    
    public String getNama(){
        koneksi();
        String hasil="";
        Statement s = null;
        try {
            s=c.createStatement();
            ResultSet rs=  s.executeQuery("SELECT namabarang from barang where idbarang = '"+txtkodeitem.getText()+"'");
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
                    Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return hasil;        
    }
    
    public int getSubHarga(int harga, int jumlah){
        return harga*jumlah;
    }
    
    public int getStokBarang(String text){
        koneksi();
        int stok=0;
        Statement s = null;
        try{
            s=c.createStatement();
            ResultSet rs=s.executeQuery("SELECT stok FROM barang WHERE idbarang='"+text+"'");
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
                    Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return stok;
    }
    private void executeUpdate(int angkaUpdate, String kodeProses) {
        koneksi();
        Statement statement = null;
        try{
            statement=c.createStatement();
            statement.execute("UPDATE barang SET stok='"+angkaUpdate+"' WHERE idbarang='"+kodeProses+"'");
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error update barangkecil karena "+exception);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }    
    
    public void updateStokSetelahSimpanRetur(){
        koneksi();
        Statement statement = null;
        try{
            statement=c.createStatement();
            ResultSet rs=statement.executeQuery("SELECT kode, penukaran, jumlah FROM detailretur");
            while(rs.next()){
                String kodeProses=rs.getString("kode");
                int jumlah=rs.getInt("jumlah");
                String penukaran = rs.getString("penukaran");
                if(penukaran.equals("Barang")){
                    int stok=getStokBarang(kodeProses);
                    int angkaUpdate=stok-jumlah;
                    executeUpdate(angkaUpdate, kodeProses);
                }else if(penukaran.equals("Uang")){
                    //do nothing
                }
            }            
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error update stok karena = "+exception);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
    }
    
    public void setTxtTotalMain(int total){
        /*int total = 0;
        tabelRetur.selectAll();        
        int row = tabelRetur.getSelectedRowCount();
        for(int a=0; a<row; a++){
            total += Integer.valueOf(String.valueOf(tabelRetur.getValueAt(a, 4)));
        }*/        
        txttotal.setText(String.valueOf(total));
    }

    private int getJumlahBarang(String text) {
        koneksi();
        int jumlahBarang = 0;
        Statement s = null;
        try{
            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT stok FROM barang WHERE idbarang='"+text+"'");
            if(rs.next()){
                jumlahBarang = rs.getInt(1);
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam mendapatkan jumlah barangkecil karena = "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return jumlahBarang;
    }
    
    
    private void txtkodeitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkodeitemActionPerformed
        // TODO add your handling code here:
        txtnamabarang.setText(getNama());
        if(txtkodeitem.getText().trim().equals("") || txtnamabarang.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Kode barangkecil masih kosong atau tidak sesuai");
            resetinputan();
            return;
        }else{
            txthargaperitem.setText(getHarga());
            txtjumlahretur.requestFocus();
        }
    }//GEN-LAST:event_txtkodeitemActionPerformed

    private void txtkodeitemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtkodeitemFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_txtkodeitemFocusLost

    private void txtnamabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamabarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamabarangActionPerformed

    private void txtnamabarangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnamabarangFocusGained
        // TODO add your handling code here:
        txtnamabarang.setText(getNama());
        if(txtkodeitem.getText().trim().equals("") || txtnamabarang.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Kode barangkecil masih kosong atau tidak sesuai");
            resetinputan();
            txtkodeitem.requestFocus();
            return;
        }else{
            txthargaperitem.setText(getHarga());
            txtjumlahretur.requestFocus();
        }
    }//GEN-LAST:event_txtnamabarangFocusGained

    public void jumlahReturGo(){
        int jumlahRetur = Integer.parseInt(txtjumlahretur.getText());
        int jumlahBarang = getJumlahBarang(txtkodeitem.getText());
        int jumlahTemp = jumlahBarang - getJumlahTemp(txtkodeitem.getText());
        //!txtJumlah.getText().matches("[0-9]*")
        if(!txtjumlahretur.getText().matches("[0-9]*")){
            JOptionPane.showMessageDialog(null, "Jumlah return harus angka");
            return;                        
        }else if(jumlahRetur>jumlahTemp){            
            JOptionPane.showMessageDialog(null, "jumlah "+txtnamabarang.getText()+" tidak mencukupi. jumlah barangkecil sekarang = "+jumlahTemp);
            txtjumlahretur.requestFocus();
            txtjumlahretur.setText("0");
            txtjumlahretur.selectAll();                        
            return;
        }else if(jumlahRetur<=0){
            JOptionPane.showMessageDialog(null, "jumlah retur pergantian barangkecil tidak boleh 0");
            txtjumlahretur.requestFocus();
            txtjumlahretur.selectAll();
            return;
        }else{
            txtSubHarga.setText(String.valueOf(getSubHarga(Integer.parseInt(txthargaperitem.getText()), Integer.parseInt(txtjumlahretur.getText()))));
            btGo.doClick();
        }        
    }
    
    public String getSupplier(String kode){
        String hasil="";
        Statement  s = null;
        try{
            String Query = "SELECT supplier FROM barang WHERE idbarang = '"+kode+"'";
            s = c.createStatement();
            ResultSet rs = s.executeQuery(Query);
            if(rs.next()){
                hasil = rs.getString("supplier");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam mendapatkan supplier barangkecil karena = "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return hasil;
    }
    
    public int getJumlahTemp(String kode){
        int jumlahTemp = 0;
        Statement  s = null;
        try{
            String Query = "SELECT jumlah FROM detailretur WHERE kode = '"+kode+"'";
            s = c.createStatement();
            ResultSet rs = s.executeQuery(Query);
            while (rs.next()) {                
                jumlahTemp+=rs.getInt(1);
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam mendapatkan jumlah temp karena = "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return jumlahTemp;
    }
    
    private void txtjumlahreturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjumlahreturActionPerformed
        // TODO add your handling code here:
        if(txtjumlahretur.getText().equals("0") || txtjumlahretur.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Jumlah return tidak boleh kosong atau nol");
            return;                                    
        }else if(!txtjumlahretur.getText().matches("[0-9]*")){
            JOptionPane.showMessageDialog(null, "Jumlah return harus angka");
            return;                        
        }else{
            cmbPenukarang.requestFocus();
        }
    }//GEN-LAST:event_txtjumlahreturActionPerformed

    private void txtjumlahreturFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtjumlahreturFocusGained
        // TODO add your handling code here:        
        txtnamabarang.setText(getNama());
        if(txtkodeitem.getText().trim().equals("") || txtnamabarang.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Kode barangkecil masih kosong atau tidak sesuai");
            resetinputan();
            return;
        }else{
            txthargaperitem.setText(getHarga());
            txtjumlahretur.requestFocus();
        }
    }//GEN-LAST:event_txtjumlahreturFocusGained

    private void txtjumlahreturFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtjumlahreturFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjumlahreturFocusLost

    private void txtSubHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubHargaActionPerformed

    private void txtSubHargaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSubHargaFocusGained
        // TODO add your handling code here:
        jumlahReturGo();
    }//GEN-LAST:event_txtSubHargaFocusGained

    private void btGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGoActionPerformed
        // TODO add your handling code here:
        txtnamabarang.setText(getNama());
        if(txtkodeitem.getText().trim().equals("") || txtnamabarang.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Kode barangkecil masih kosong atau tidak sesuai");
            resetinputan();
            return;
        }else{
            controller.insertRetur(this);
            resetinputan();
            txtjumlahretur.setText("");
        }
        //setTxtTotalMain();
    }//GEN-LAST:event_btGoActionPerformed

    public int getIdTransaksi(){
        int id = 0;
        koneksi();
        Statement s= null;
        try {        
            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT count(id) FROM retur");
            if(rs.next()){
                id = rs.getInt(1)+1;
            }else{
                id += 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return  id;
    }
    
    public int getJumlahBarangDalamTabel(){
        int jumlah = 0;
        tabelRetur.selectAll();
        int row = tabelRetur.getSelectedRowCount();
        for(int a=0; a<row; a++){
            jumlah+=Integer.parseInt(tabelRetur.getValueAt(tabelRetur.getSelectedRow(), 3).toString());
        }
        return jumlah;
    }
    
    public int getTotal(){
        int total = 0;
        tabelRetur.selectAll();
        int row = tabelRetur.getSelectedRowCount();
        for(int a=0; a<row; a++){
            total+=Integer.parseInt(tabelRetur.getValueAt(tabelRetur.getSelectedRow(), 4).toString());
        }
        return total;        
    }
    
    public String getTanggal(){
        Date date=new Date();            
        SimpleDateFormat formatTanggal=new SimpleDateFormat("dd:MM:yyyy");        
        return formatTanggal.format(date);
    }
    
    public String getTanggalForDatabase(){
        Date date=new Date();            
        return formatTanggal2.format(date);        
    }
    
    public void tambahTransaksiBaru(){        
        controller.tambahTransaksi(this);
        txtnoretur.setText(String.valueOf(getIdTransaksi()));
        btOpenCari.setEnabled(true);
        txtkodeitem.setEditable(true);
        txtkodeitem.setText("");
        txtnamabarang.setText("");
        txthargaperitem.setText("");
        txtjumlahretur.setText("");
        txtSubHarga.setText("");
        
        txtkodeitem.requestFocus();
    }

    public void loadDatabaseCariBarang() throws SQLException, BarangException{
        BarangKecilDAO dao = barangDatabase.getBarangDao();
        tabelmodelBarangKecil.setList(dao.selectAllBarang());
    }        
    
    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        // TODO add your handling code here:
        btHapus.setEnabled(true);
        tambahTransaksiBaru();
        totalMain = 0;
        setTxtTotalMain(totalMain);
        txtjumlahretur.setText("");
    }//GEN-LAST:event_btTambahActionPerformed

    public void insertIntoDetailReturFinal(){
        Statement s = null;
        PreparedStatement ps = null;
        try{
            s= c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM detailretur");
            while (rs.next()) {                
                String user = rs.getString("user");
                String tanggal = rs.getString("tanggal");
                String jam = rs.getString("jam");
                String kode = rs.getString("kode");
                String nama = rs.getString("nama");
                String jumlah = rs.getString("jumlah");
                String harga = rs.getString("harga");
                String penukaran = rs.getString("penukaran");
                String subharga = rs.getString("subharga");
                String supplier  = rs.getString("supplier");
                
                c.setAutoCommit(false);
                ps = c.prepareStatement("INSERT INTO detailreturfinal (no_retur, user, tanggal, jam, kode, nama, jumlah, harga, penukaran, subharga, supplier, status) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, txtnoretur.getText());        
                ps.setString(2, user);
                ps.setString(3, tanggal);
                ps.setString(4, jam);
                ps.setString(5, kode);
                ps.setString(6, nama);
                ps.setString(7, jumlah);
                ps.setString(8, harga);
                ps.setString(9, penukaran);
                ps.setString(10, subharga);
                ps.setString(11, supplier);
                ps.setString(12, "belum diproses");
                ps.executeUpdate();
                c.commit();         
            }
        }catch(SQLException exception){
            try {
                c.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "error dalam insert table detail retur karena = "+exception);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
    }    

    public void insertIntoTableRetur(){
        PreparedStatement ps = null;
        try{
            c.setAutoCommit(false);
            ps = c.prepareStatement("INSERT INTO retur (id, user, tanggal, jam, jumlah, total) values(?, ?, ?, ?, ?, ?)");            
            ps.setString(1, txtnoretur.getText());        
            ps.setString(2, txtuser.getText());
            ps.setString(3, getTanggalForDatabase());
            ps.setString(4, txtjam.getText());
            ps.setString(5, String.valueOf(getJumlahBarangDalamTabel()));
            ps.setString(6, txttotal.getText());
            ps.executeUpdate();
            c.commit();
        }catch(SQLException exception){
            try {
                c.rollback();
            //JOptionPane.showMessageDialog(null, "error dalam insert table retur karena = "+exception);
            } catch (SQLException ex) {
                Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
    private void btSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(null, "Apakah transaksi ini akan disimpan dan diproses ?") == JOptionPane.YES_OPTION){
            insertIntoTableRetur();
            insertIntoDetailReturFinal();
            updateStokSetelahSimpanRetur();
            //resetinputan();
            txtkodeitem.setEditable(false);
            btOpenCari.setEnabled(false);
            btHapus.setEnabled(false);
            //setTxtTotalMain();
        }
    }//GEN-LAST:event_btSimpanActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        // TODO add your handling code here:
        controller.deleteRetur(this);
        resetinputan();
        //setTxtTotalMain();
    }//GEN-LAST:event_btHapusActionPerformed

    private void btResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetActionPerformed
        // TODO add your handling code here:
        resetinputan();
    }//GEN-LAST:event_btResetActionPerformed

    private void txtKataKunci1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKataKunci1ActionPerformed
        // TODO add your handling code here:
        btCari1.doClick();
    }//GEN-LAST:event_txtKataKunci1ActionPerformed

    private void btCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCari1ActionPerformed
        try {
            controllerBarang.cari6(this, this);
        this.setSize(getWidth(), getHeight());
        } catch (SQLException ex) {
            Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BarangException ex) {
            Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btCari1ActionPerformed

    private void cmbCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCari1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCari1ActionPerformed

    private void cmbUrut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUrut1ActionPerformed
        try {
            controllerBarang.sort5(this);
        } catch (SQLException ex) {
            Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BarangException ex) {
            Logger.getLogger(JIFReturView2.class.getName()).log(Level.SEVERE, null, ex);
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

    private void pilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihActionPerformed
        // TODO add your handling code here:
        if(tabelBarang1.getSelectedRowCount()==1){
            String data=tabelBarang1.getValueAt(tabelBarang1.getSelectedRow(), 0).toString();
            txtkodeitem.setText(data);
            txtnamabarang.requestFocus();
            DialogCariBarang.dispose();                        
        }
    }//GEN-LAST:event_pilihActionPerformed

    private void tabelReturMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelReturMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tabelReturMousePressed

    private void tabelReturMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelReturMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelReturMouseReleased

    private void cmbPenukarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPenukarangActionPerformed
        // TODO add your handling code here:                
    }//GEN-LAST:event_cmbPenukarangActionPerformed

    private void cmbPenukarangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbPenukarangFocusLost
        // TODO add your handling code here:
        switch(cmbPenukarang.getSelectedItem().toString()){
            case "Barang" : jumlahReturGo();break;
            case "Uang" : txtSubHarga.setText(String.valueOf(getSubHarga(Integer.parseInt(txthargaperitem.getText()), Integer.parseInt(txtjumlahretur.getText()))));
                          btGo.doClick();
                          break;
            default:;
        }
    }//GEN-LAST:event_cmbPenukarangFocusLost

    private void cmbPenukarangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbPenukarangFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPenukarangFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogCariBarang;
    private javax.swing.JDialog DialogPreview;
    private javax.swing.JPanel PanelTabel;
    private javax.swing.JPanel PanelTombol;
    private javax.swing.JPanel atas;
    private javax.swing.JPanel atas2;
    private javax.swing.JPanel bawah;
    private javax.swing.JPanel bawah2;
    private javax.swing.JButton btCari1;
    private javax.swing.JButton btGo;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btOpenCari;
    private javax.swing.JButton btReset;
    private javax.swing.JButton btSimpan;
    private javax.swing.JButton btTambah;
    private javax.swing.JComboBox cmbCari1;
    private javax.swing.JComboBox cmbPenukarang;
    private javax.swing.JComboBox cmbUrut1;
    private javax.swing.JPanel dua;
    private javax.swing.JPanel duakanan;
    private javax.swing.JPanel duakiri;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel kanan;
    private javax.swing.JPanel kanan1;
    private javax.swing.JPanel kanandua;
    private javax.swing.JPanel kanansatu;
    private javax.swing.JPanel kiri;
    private javax.swing.JPanel kiri2;
    private com.wissensalt.sinarelektronik.swinglib.labeling labeling7;
    private com.wissensalt.sinarelektronik.swinglib.labeling labeling8;
    private com.wissensalt.sinarelektronik.swinglib.labeling labeling9;
    private javax.swing.JLabel lblPreview;
    private javax.swing.JPanel middle2;
    private javax.swing.JPanel palingAtas;
    private com.wissensalt.sinarelektronik.swinglib.panelGradUser panelBackgroundUp1;
    private com.wissensalt.sinarelektronik.swinglib.panelGradUser panelGradUser4;
    private javax.swing.JPanel panelKode;
    private javax.swing.JMenuItem pilih;
    private javax.swing.JPopupMenu popUpTabel;
    private javax.swing.JPanel satu;
    private javax.swing.JPanel satukanan;
    private javax.swing.JPanel satukiri;
    private javax.swing.JTable tabelBarang1;
    private javax.swing.JTable tabelRetur;
    private javax.swing.JPanel tengah;
    private javax.swing.JPanel tengah2;
    private javax.swing.JPanel tiga;
    private javax.swing.JPanel tigakanan;
    private javax.swing.JPanel tigakiri;
    private javax.swing.JTextField txtKataKunci1;
    private javax.swing.JTextField txtSubHarga;
    private javax.swing.JTextField txthargaperitem;
    private javax.swing.JTextField txtjam;
    private javax.swing.JTextField txtjumlahretur;
    private com.wissensalt.sinarelektronik.swinglib.AutoComplete.TextFieldAutoComplete txtkodeitem;
    private javax.swing.JTextField txtnamabarang;
    private javax.swing.JTextField txtnoretur;
    private javax.swing.JTextField txttanggal;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txtuser;
    private javax.swing.JPanel up2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onChange(ReturModel retur) {
        txtnoretur.setText(String.valueOf(retur.getId()));
        txtuser.setText(retur.getUser());
        txtjam.setText(retur.getJam());
        txtkodeitem.setText(retur.getKode());
        txtnamabarang.setText(retur.getNama());
        txtjumlahretur.setText(String.valueOf(retur.getJumlah()));
        txtSubHarga.setText(String.valueOf(retur.getSubharga()));
        
        
    }

    @Override
    public void onInsert(Retur retur) {
        tabelModelRetur.add(retur);
        
        totalMain+=retur.getSubharga();
        setTxtTotalMain(totalMain);
    }

    @Override
    public void onUpdate(Retur retur) {
        int index = tabelRetur.getSelectedRow();
        tabelModelRetur.set(index, retur);
    }

    @Override
    public void onDelete() {
        int index = tabelRetur.getSelectedRow();
        
        totalMain-=Integer.parseInt(String.valueOf(tabelRetur.getValueAt(index, 4)));
        setTxtTotalMain(totalMain);
        
        tabelModelRetur.remove(index);        
        
    }    

    
    @Override
    public void onTruncate() {
        tabelRetur.selectAll();
        int b= tabelRetur.getSelectedRowCount();
        int a=0;
        while (b>0) {            
            tabelModelRetur.remove(a);
            tabelRetur.selectAll();
            b= tabelRetur.getSelectedRowCount();
        }
    }
    
    @Override
    public void onChange(BarangKecilModel model) {
        txtKataKunci1.setText(modelBarang.getCari());
    }

    @Override
    public void onInsert(BarangKecilDTO BarangKecilDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onUpdate(BarangKecilDTO BarangKecilDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onSearch(List list) {
        tabelmodelBarangKecil.setList(list);
    }

    @Override
    public void onSort(List list) {
        tabelmodelBarangKecil.setList(list);
    }
}

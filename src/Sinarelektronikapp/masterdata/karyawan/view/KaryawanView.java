/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.karyawan.view;

import Sinarelektronikapp.AppConstant;
import Sinarelektronikapp.config.InternetProtocol;
import Sinarelektronikapp.masterdata.karyawan.Error.KaryawanException;
import Sinarelektronikapp.masterdata.karyawan.controller.KaryawanController;
import Sinarelektronikapp.masterdata.karyawan.database.KaryawanDatabase;
import Sinarelektronikapp.masterdata.karyawan.entity.Karyawan;
import Sinarelektronikapp.masterdata.karyawan.model.KaryawanModel;
import Sinarelektronikapp.masterdata.karyawan.model.TabelModelKaryawan;
import Sinarelektronikapp.masterdata.karyawan.model.event.KaryawanListener;
import Sinarelektronikapp.masterdata.karyawan.service.KaryawanDao;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Fauzi
 */
public class KaryawanView extends javax.swing.JPanel implements KaryawanListener, ListSelectionListener{

    /**
     * Creates new form KaryawanView
     */    
    TabelModelKaryawan tabelModelKaryawan;
    
    KaryawanModel karyawanModel;
    
    KaryawanController karyawanController;
    
    SimpleDateFormat formatTanggal=new SimpleDateFormat("dd:MM:yyyy");
    
    public KaryawanView() {
        karyawanModel = new KaryawanModel();
        karyawanModel.setKaryawanListener(this);
        
        karyawanController = new KaryawanController();
        karyawanController.setKaryawanModel(karyawanModel);
        
        initComponents();
        
        tabelModelKaryawan = new TabelModelKaryawan();
        tabelKaryawan.setModel(tabelModelKaryawan);
        tabelKaryawan.getSelectionModel().addListSelectionListener(this);

        loadId();
        loadDatabase();
    }

    InternetProtocol ip = new InternetProtocol();
    Connection connection =null;
    public void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+ip.getIpServer()+"/sinarelektronik?;", "root", AppConstant.DB_PASSWORD);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error koneksi pada barangkecil view karena = "+ex);
        }
    }        
    
    public JLabel getFoto() {
        return foto;
    }

    public void setFoto(JLabel foto) {
        this.foto = foto;
    }

    public JRadioButton getRbDuda() {
        return rbDuda;
    }

    public void setRbDuda(JRadioButton rbDuda) {
        this.rbDuda = rbDuda;
    }

    public JRadioButton getRbJanda() {
        return rbJanda;
    }

    public void setRbJanda(JRadioButton rbJanda) {
        this.rbJanda = rbJanda;
    }

    public JRadioButton getRbMenikah() {
        return rbMenikah;
    }

    public void setRbMenikah(JRadioButton rbMenikah) {
        this.rbMenikah = rbMenikah;
    }

    public JRadioButton getRbSingle() {
        return rbSingle;
    }

    public void setRbSingle(JRadioButton rbSingle) {
        this.rbSingle = rbSingle;
    }

    public JTable getTabelKaryawan() {
        return tabelKaryawan;
    }

    public void setTabelKaryawan(JTable tabelKaryawan) {
        this.tabelKaryawan = tabelKaryawan;
    }

    public JTextField getTxtAgama() {
        return txtTelepon;
    }

    public void setTxtAgama(JTextField txtAgama) {
        this.txtTelepon = txtAgama;
    }

    public JTextField getTxtAlamat() {
        return txtAlamat;
    }

    public void setTxtAlamat(JTextField txtAlamat) {
        this.txtAlamat = txtAlamat;
    }

    public JTextField getTxtGaji() {
        return txtGaji;
    }

    public void setTxtGaji(JTextField txtGaji) {
        this.txtGaji = txtGaji;
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public void setTxtId(JTextField txtId) {
        this.txtId = txtId;
    }

    public JTextField getTxtNama() {
        return txtNama;
    }

    public void setTxtNama(JTextField txtNama) {
        this.txtNama = txtNama;
    }

    public JDateChooser getTxtTanggalLahir() {
        return txtTanggalLahir;
    }

    public void setTxtTanggalLahir(JDateChooser txtTanggalLahir) {
        this.txtTanggalLahir = txtTanggalLahir;
    }

    public JTextField getTxtTelepon() {
        return txtTelepon;
    }

    public void setTxtTelepon(JTextField txtTelepon) {
        this.txtTelepon = txtTelepon;
    }

    public JTextField getTxtTempatLahir() {
        return txtTempatLahir;
    }

    public void setTxtTempatLahir(JTextField txtTempatLahir) {
        this.txtTempatLahir = txtTempatLahir;        
    }

    File gambar;

    public File getGambar() {
        return gambar;
    }

    public void setGambar(File gambar) {
        this.gambar = gambar;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        middle = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelKaryawan = new javax.swing.JTable();
        up = new javax.swing.JPanel();
        kiri = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tengah = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtTanggalLahir = new com.toedter.calendar.JDateChooser();
        txtTempatLahir = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        txtTelepon = new javax.swing.JTextField();
        txtAgama = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        rbSingle = new javax.swing.JRadioButton();
        rbMenikah = new javax.swing.JRadioButton();
        rbDuda = new javax.swing.JRadioButton();
        rbJanda = new javax.swing.JRadioButton();
        txtGaji = new javax.swing.JTextField();
        kanan = new javax.swing.JPanel();
        foto = new javax.swing.JLabel();
        btBrowse = new javax.swing.JButton();
        bottom = new javax.swing.JPanel();
        btTambah = new javax.swing.JButton();
        btUpdate = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        btReset = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        middle.setLayout(new java.awt.BorderLayout());

        tabelKaryawan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelKaryawan);

        middle.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(middle, java.awt.BorderLayout.CENTER);

        up.setLayout(new java.awt.BorderLayout());

        kiri.setLayout(new java.awt.GridLayout(9, 0));

        jLabel1.setText("Id :");
        kiri.add(jLabel1);

        jLabel2.setText("Nama :");
        kiri.add(jLabel2);

        jLabel3.setText("Tanggal Lahir :");
        kiri.add(jLabel3);

        jLabel4.setText("Tempat Lahir :");
        kiri.add(jLabel4);

        jLabel5.setText("Alamat :");
        kiri.add(jLabel5);

        jLabel6.setText("Telepon :");
        kiri.add(jLabel6);

        jLabel7.setText("Agama :");
        kiri.add(jLabel7);

        jLabel8.setText("Status :");
        kiri.add(jLabel8);

        jLabel9.setText("Gaji :");
        kiri.add(jLabel9);

        up.add(kiri, java.awt.BorderLayout.WEST);

        tengah.setLayout(new java.awt.GridLayout(9, 0));

        txtId.setEditable(false);
        tengah.add(txtId);

        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });
        tengah.add(txtNama);
        tengah.add(txtTanggalLahir);
        tengah.add(txtTempatLahir);
        tengah.add(txtAlamat);
        tengah.add(txtTelepon);

        txtAgama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Islam", "Kristen", "Katolik", "Hindu", "Budha", "Kong Hu Chu" }));
        tengah.add(txtAgama);

        jPanel1.setLayout(new java.awt.GridLayout(1, 4));

        buttonGroup1.add(rbSingle);
        rbSingle.setText("Single");
        jPanel1.add(rbSingle);

        buttonGroup1.add(rbMenikah);
        rbMenikah.setText("Menikah");
        jPanel1.add(rbMenikah);

        buttonGroup1.add(rbDuda);
        rbDuda.setText("Duda");
        jPanel1.add(rbDuda);

        buttonGroup1.add(rbJanda);
        rbJanda.setText("Janda");
        jPanel1.add(rbJanda);

        tengah.add(jPanel1);
        tengah.add(txtGaji);

        up.add(tengah, java.awt.BorderLayout.CENTER);

        kanan.setLayout(new java.awt.BorderLayout());
        kanan.add(foto, java.awt.BorderLayout.CENTER);

        btBrowse.setText("Browse");
        btBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBrowseActionPerformed(evt);
            }
        });
        kanan.add(btBrowse, java.awt.BorderLayout.PAGE_END);

        up.add(kanan, java.awt.BorderLayout.EAST);

        add(up, java.awt.BorderLayout.PAGE_START);

        bottom.setLayout(new java.awt.GridLayout(1, 4));

        btTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/tambah2.png"))); // NOI18N
        btTambah.setMnemonic('T');
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });
        bottom.add(btTambah);

        btUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/update2.png"))); // NOI18N
        btUpdate.setMnemonic('U');
        btUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateActionPerformed(evt);
            }
        });
        bottom.add(btUpdate);

        btDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/hapus.png"))); // NOI18N
        btDelete.setMnemonic('H');
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });
        bottom.add(btDelete);

        btReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/reset.png"))); // NOI18N
        btReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetActionPerformed(evt);
            }
        });
        bottom.add(btReset);

        add(bottom, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        // TODO add your handling code here:
        if(pathGambar!=null || pathGambar!=""){
            gambar = new File(pathGambar);
        }else{
            JOptionPane.showMessageDialog(null, "gambar masih kosong");
        }
        karyawanController.insertKaryawan(this);        
    }//GEN-LAST:event_btTambahActionPerformed

    private void btUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateActionPerformed
        // TODO add your handling code here:
        karyawanController.updateKaryawan(this);
    }//GEN-LAST:event_btUpdateActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        // TODO add your handling code here:
        karyawanController.deleteKaryawan(this);
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetActionPerformed
        // TODO add your handling code here:
        karyawanController.reset(this);
    }//GEN-LAST:event_btResetActionPerformed
    
    String pathGambar = "";
            
    private void btBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBrowseActionPerformed
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
                Logger.getLogger(KaryawanView.class.getName()).log(Level.SEVERE, null, ex);
            }
            foto.setSize(new Dimension(150, 150));
            ImageIcon gambar=new ImageIcon(thumb);
            foto.setIcon(gambar);
        }
    }//GEN-LAST:event_btBrowseActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottom;
    private javax.swing.JButton btBrowse;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btReset;
    private javax.swing.JButton btTambah;
    private javax.swing.JButton btUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel foto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel kanan;
    private javax.swing.JPanel kiri;
    private javax.swing.JPanel middle;
    private javax.swing.JRadioButton rbDuda;
    private javax.swing.JRadioButton rbJanda;
    private javax.swing.JRadioButton rbMenikah;
    private javax.swing.JRadioButton rbSingle;
    private javax.swing.JTable tabelKaryawan;
    private javax.swing.JPanel tengah;
    private javax.swing.JComboBox txtAgama;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtGaji;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNama;
    private com.toedter.calendar.JDateChooser txtTanggalLahir;
    private javax.swing.JTextField txtTelepon;
    private javax.swing.JTextField txtTempatLahir;
    private javax.swing.JPanel up;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onChange(KaryawanModel model) {
        txtId.setText(String.valueOf(model.getId()));
        txtNama.setText(model.getNama());
        txtTanggalLahir.setDate(new Date(model.getTanggal_lahir()));
        txtTempatLahir.setText(model.getTempat_lahir());
        txtAlamat.setText(model.getAlamat());
        txtTelepon.setText(model.getTelepon());
        txtAgama.setSelectedItem(model.getAgama());
        switch(model.getStatus()){
            case "single":rbSingle.isSelected();break;
            case "menikah":rbMenikah.isSelected();break;
            case "janda":rbJanda.isSelected();break;
            case "duda":rbDuda.isSelected();break;
            default:;
        }
        txtGaji.setText(String.valueOf(model.getGaji()));
        Blob gambarRetrive = (Blob) model.getFoto();
        byte [] dataGambarRetrieve = null;
        try {
            dataGambarRetrieve = gambarRetrive.getBytes(1, (int)gambarRetrive.length());
            
            
            
            //foto.setIcon(new ImageIcon(model.getFoto()));
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanView.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon iconRetrieve = new ImageIcon(dataGambarRetrieve);
        Image img = iconRetrieve.getImage();
        Image newImg = img.getScaledInstance(150, 150, img.SCALE_FAST);
        iconRetrieve = new ImageIcon(newImg);        
        foto.setIcon(iconRetrieve);
    }

    @Override
    public void onInsert(Karyawan karyawan) {
        tabelModelKaryawan.add(karyawan);
    }

    @Override
    public void onUpdate(Karyawan karyawan) {
        int index = tabelKaryawan.getSelectedRow();
        tabelModelKaryawan.set(index, karyawan);
    }

    @Override
    public void onDelete() {
        int index = tabelKaryawan.getSelectedRow();
        tabelModelKaryawan.remove(index);
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        koneksi();
        try{
            Karyawan model = tabelModelKaryawan.get(tabelKaryawan.getSelectedRow());
            txtId.setText(String.valueOf(model.getId()));
            txtNama.setText(model.getNama());
            java.util.Date date = new SimpleDateFormat("dd:MM:yyyy").parse(model.getTanggal_lahir());
            txtTanggalLahir.setDate(date);
            //txttanggal lahir harus diinitiate (jdate chooser)
            //txtTanggalLahir.actionPerformed(null);            
            txtTempatLahir.setText(model.getTempat_lahir());
            txtTanggalLahir.actionPerformed(null);
            txtAlamat.setText(model.getAlamat());
            txtTelepon.setText(model.getTelepon());
            txtAgama.setSelectedItem(model.getAgama());
            switch(model.getStatus()){
                case "single":rbSingle.isSelected();break;
                case "menikah":rbMenikah.isSelected();break;
                case "janda":rbJanda.isSelected();break;
                case "duda":rbDuda.isSelected();break;
                default:;
            }
            Blob gambar = null;
            byte [] data = null;
            Statement s = null;
            try{
                s = connection.createStatement();
                ResultSet rs = s.executeQuery("select foto from karyawan where id ="+model.getId());
                if(rs.next()){
                    gambar = rs.getBlob("foto");
                }
            }catch(SQLException exception){
                JOptionPane.showMessageDialog(null, "Error pada execute ambil gambar karena = "+e);
            }finally{
                if(s!=null){
                    try {
                        s.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(KaryawanView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            txtGaji.setText(String.valueOf(model.getGaji()));            
            if(gambar!=null){
                try {
                    data = gambar.getBytes(1, (int)gambar.length());
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanView.class.getName()).log(Level.SEVERE, null, ex);
                }
                ImageIcon icon=new ImageIcon(data);
                Image img = icon.getImage();
                Image newImg = img.getScaledInstance(200, 200, img.SCALE_FAST);
                icon = new ImageIcon(newImg);
                foto.setIcon(icon);
            }
            } catch (ParseException ex) {            
            Logger.getLogger(KaryawanView.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IndexOutOfBoundsException boundsException){            
                
            }
    }
    
    public void loadId(){
        KaryawanDao karyawanDao = KaryawanDatabase.getKaryawanDao();        
        int id = 0;
        try {
            id = karyawanDao.getLastIdKaryawan()+1;
        } catch (KaryawanException ex) {
            Logger.getLogger(KaryawanView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        txtId.setText(String.valueOf(id));
        txtNama.requestFocus();
    }
    
    public void loadDatabase(){
        KaryawanDao karyawanDao = KaryawanDatabase.getKaryawanDao();
        try {
            tabelModelKaryawan.setList(karyawanDao.selectAllKaryawan());
        } catch (KaryawanException ex) {
            Logger.getLogger(KaryawanView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

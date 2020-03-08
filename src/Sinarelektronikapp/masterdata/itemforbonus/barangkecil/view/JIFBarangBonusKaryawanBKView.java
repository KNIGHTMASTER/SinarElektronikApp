/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.itemforbonus.barangkecil.view;
import Sinarelektronikapp.config.HostName;
import Sinarelektronikapp.config.UserLevel;
import Sinarelektronikapp.masterdata.barangkecil.controller.BarangController;
import Sinarelektronikapp.masterdata.barangkecil.database.barangDatabase;
import Sinarelektronikapp.masterdata.barangkecil.entity.barang;
import Sinarelektronikapp.masterdata.barangkecil.error.BarangException;
import Sinarelektronikapp.masterdata.barangkecil.model.barangModel;
import Sinarelektronikapp.masterdata.barangkecil.model.event.barangListener;
import Sinarelektronikapp.masterdata.barangkecil.model.tabelModelBarang;
import Sinarelektronikapp.masterdata.barangkecil.service.BarangDao;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.controller.BarangBonusKaryawanBKController;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.database.BarangBonusKaryawanBKDatabase;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.entity.BarangBonusKaryawanBk;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.error.BarangBonusKaryawanBKException;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.model.event.BarangBonusKaryawanBKListener;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.model.tabelModelBarangBonusKaryawanBK;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.model.BarangBonusKaryawanBKModel;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.service.BarangBonusKaryawanDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author Fauzi
 */
public class JIFBarangBonusKaryawanBKView extends javax.swing.JInternalFrame implements BarangBonusKaryawanBKListener, ListSelectionListener, barangListener{

    /**
     * Creates new form JIFTipeView
     */
    
    private BarangBonusKaryawanBKController controller;
    
    private tabelModelBarangBonusKaryawanBK tabelMbbkbk;
    
    private BarangBonusKaryawanBKModel model;
    
    private  tabelModelBarang tabelmodelBarang ;
    
    private BarangController controllerBarang;
    
    private  barangModel modelBarang;
    
    public JIFBarangBonusKaryawanBKView() {
        tabelmodelBarang = new tabelModelBarang();
        try {
            try {
                initComponents();

                tabelMbbkbk = new tabelModelBarangBonusKaryawanBK();
                
                model = new BarangBonusKaryawanBKModel();
                model.setListener(this);
                
                controller = new BarangBonusKaryawanBKController(model);
                
                modelBarang=  new barangModel();
                modelBarang.setListener(this);        
                controllerBarang = new BarangController();
                controllerBarang.setModel(modelBarang);
                
                initComponents();
                
                tabelTipe.getSelectionModel().addListSelectionListener(this);
                
                tabelTipe.setModel(tabelMbbkbk);
                setLevel();        
                LoadDatabase();
            } catch (SQLException ex) {
                Logger.getLogger(JIFBarangBonusKaryawanBKView.class.getName()).log(Level.SEVERE, null, ex);       
            } catch (BarangBonusKaryawanBKException ex) {
                Logger.getLogger(JIFBarangBonusKaryawanBKView.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            tabelBarang1.setModel(tabelmodelBarang);
            loadDatabaseCariBarang();
        } catch (SQLException ex) {
            Logger.getLogger(JIFBarangBonusKaryawanBKView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BarangException ex) {
            Logger.getLogger(JIFBarangBonusKaryawanBKView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JTextField getTxtKataKunci1() {
        return txtKataKunci1;
    }

    public void setTxtKataKunci1(JTextField txtKataKunci1) {
        this.txtKataKunci1 = txtKataKunci1;
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

    
    public void setLevel(){
        UserLevel userLevel = new UserLevel();
        String levelAktif = userLevel.getUserLevelActive();
        switch(levelAktif){
            case "Pemilik Toko":
                    btTambah.setEnabled(true);
                    btDelete.setEnabled(true);
                    btReset.setEnabled(true);
                break;
            case "Administrator":
                    btTambah.setEnabled(true);
                    btDelete.setEnabled(true);
                    btReset.setEnabled(true);
                break;
            case "Karyawan":
                    btTambah.setEnabled(false);
                    btDelete.setEnabled(false);
                    btReset.setEnabled(false);
                break;                        
            default:;
        }
    }    
    
    public JTable getTabelTipe() {
        return tabelTipe;
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
        panelBackgroundUp1 = new Sinarelektronikapp.swinglib.panelGradUser();
        kiri2 = new javax.swing.JPanel();
        labeling7 = new Sinarelektronikapp.swinglib.labeling();
        labeling8 = new Sinarelektronikapp.swinglib.labeling();
        labeling9 = new Sinarelektronikapp.swinglib.labeling();
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
        panelGradUser4 = new Sinarelektronikapp.swinglib.panelGradUser();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelBarang1 = new javax.swing.JTable();
        popUpTabel = new javax.swing.JPopupMenu();
        pilih = new javax.swing.JMenuItem();
        atas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelTipe = new javax.swing.JTable();
        bawah = new javax.swing.JPanel();
        btTambah = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        btReset = new javax.swing.JButton();
        palingAtas = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtKodeBarang = new javax.swing.JTextField();
        btCari = new javax.swing.JButton();
        txtNamaBarang = new javax.swing.JTextField();
        txtTipeBarang = new javax.swing.JTextField();
        txtMerekBarang = new javax.swing.JTextField();

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

        cmbCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id barangkecil", "id barcode", "nama barangkecil", "tipe", "merek", "harga", "satuan", "stok", "stok minimum", "supplier", "keterangan" }));
        cmbCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCari1ActionPerformed(evt);
            }
        });
        tengah2.add(cmbCari1, java.awt.BorderLayout.CENTER);

        kanan1.add(tengah2);

        bawah2.setOpaque(false);
        bawah2.setLayout(new java.awt.BorderLayout());

        cmbUrut1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id barangkecil", "id barcode", "nama barangkecil", "tipe", "merek", "harga", "satuan", "stok", "stok minimum", "supplier", "keterangan" }));
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

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Manajemen Barang Bonus");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(400, 300));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        atas.setLayout(new java.awt.BorderLayout());

        tabelTipe.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelTipe);

        atas.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(atas, java.awt.BorderLayout.CENTER);

        bawah.setLayout(new java.awt.GridLayout(1, 4));

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
        bawah.add(btTambah);

        btDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/hapus.png"))); // NOI18N
        btDelete.setMnemonic('H');
        btDelete.setFocusable(false);
        btDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });
        bawah.add(btDelete);

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
        bawah.add(btReset);

        getContentPane().add(bawah, java.awt.BorderLayout.PAGE_END);

        palingAtas.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(4, 0));

        jLabel2.setText("Kode Barang :");
        jPanel1.add(jLabel2);

        jLabel1.setText("Nama :");
        jPanel1.add(jLabel1);

        jLabel3.setText("Tipe :");
        jPanel1.add(jLabel3);

        jLabel4.setText("Merek :");
        jPanel1.add(jLabel4);

        palingAtas.add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setLayout(new java.awt.GridLayout(4, 0));

        jPanel3.setLayout(new java.awt.BorderLayout());

        txtKodeBarang.setEditable(false);
        txtKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeBarangActionPerformed(evt);
            }
        });
        txtKodeBarang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtKodeBarangFocusGained(evt);
            }
        });
        jPanel3.add(txtKodeBarang, java.awt.BorderLayout.CENTER);

        btCari.setText("...");
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });
        jPanel3.add(btCari, java.awt.BorderLayout.LINE_END);

        jPanel2.add(jPanel3);

        txtNamaBarang.setEditable(false);
        txtNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaBarangActionPerformed(evt);
            }
        });
        jPanel2.add(txtNamaBarang);

        txtTipeBarang.setEditable(false);
        jPanel2.add(txtTipeBarang);

        txtMerekBarang.setEditable(false);
        jPanel2.add(txtMerekBarang);

        palingAtas.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(palingAtas, java.awt.BorderLayout.PAGE_START);

        getAccessibleContext().setAccessibleName("Manajemen  Barang Bonus");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JTextField getTxtKodeBarang() {
        return txtKodeBarang;
    }

    public void setTxtKodeBarang(JTextField txtKodeBarang) {
        this.txtKodeBarang = txtKodeBarang;
    }

    public JTextField getTxtMerekBarang() {
        return txtMerekBarang;
    }

    public void setTxtMerekBarang(JTextField txtMerekBarang) {
        this.txtMerekBarang = txtMerekBarang;
    }

    public JTextField getTxtNamaBarang() {
        return txtNamaBarang;
    }

    public void setTxtNamaBarang(JTextField txtNamaBarang) {
        this.txtNamaBarang = txtNamaBarang;
    }

    public JTextField getTxtTipeBarang() {
        return txtTipeBarang;
    }

    public void setTxtTipeBarang(JTextField txtTipeBarang) {
        this.txtTipeBarang = txtTipeBarang;
    }

    
    private void delete(){
        controller.deleteTipe(this);
        controller.resetTipe(this);
    }

    
    public void tambah(){
        controller.insertTipe(this);
    }
    
    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        // TODO add your handling code here:
        tambah();
    }//GEN-LAST:event_btTambahActionPerformed

    static HostName ip1 = new HostName();
    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        if(tabelTipe.getSelectedRowCount()<=0){
            JOptionPane.showMessageDialog(null, "Pilih data terlebih dahulu", "peringatan", JOptionPane.WARNING_MESSAGE);
        }else if(JOptionPane.showConfirmDialog(null, "Apakah anda yakin akan menghapus ?") == JOptionPane.YES_OPTION){
        try {
                // TODO add your handling code here:
                //delete();
                Class.forName("com.mysql.jdbc.Driver");
                String queryDelete = "Delete from barangbonuskaryawanbk where kodebarang=?";
                try {
                    Connection c = DriverManager.getConnection("jdbc:mysql://"+ip1.getIpServer()+":3306/sinarelektronik?;", "root", "P@ssw0rd");
                    PreparedStatement ps = c.prepareStatement(queryDelete);
                    ps.setString(1, txtKodeBarang.getText());
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFBarangBonusKaryawanBKView.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JIFBarangBonusKaryawanBKView.class.getName()).log(Level.SEVERE, null, ex);
            }            
        
                try {            
                    LoadDatabase();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFBarangBonusKaryawanBKView.class.getName()).log(Level.SEVERE, null, ex);                
                } catch (BarangBonusKaryawanBKException ex) {
                Logger.getLogger(JIFBarangBonusKaryawanBKView.class.getName()).log(Level.SEVERE, null, ex);        
                }
                txtKodeBarang.setText("");
                txtMerekBarang.setText("");
                txtNamaBarang.setText("");
                txtTipeBarang.setText("");
                this.setSize(this.getWidth(), this.getHeight());
        }
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetActionPerformed
        // TODO add your handling code here:
        controller.resetTipe(this);
    }//GEN-LAST:event_btResetActionPerformed

    private void cariBarang(){
        txtKodeBarang.setText("");
        txtNamaBarang.setText("");
        txtMerekBarang.setText("");
        txtTipeBarang.setText("");
        DialogCariBarang.setSize(800, 900);
        DialogCariBarang.show();
        DialogCariBarang.setModal(true);
        DialogCariBarang.setTitle("Pencarian Barang");
        DialogCariBarang.setLocationRelativeTo(null);        
    }
    
    private void txtNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaBarangActionPerformed
        // TODO add your handling code here:
        tambah();
    }//GEN-LAST:event_txtNamaBarangActionPerformed

    private void txtKataKunci1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKataKunci1ActionPerformed
        // TODO add your handling code here:
        btCari1.doClick();
    }//GEN-LAST:event_txtKataKunci1ActionPerformed

    private void btCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCari1ActionPerformed
        try {
            // TODO add your handling code here:
            controllerBarang.cariBarangBonusKaryawan(this, this);

            this.setSize(getWidth(), getHeight());
            tabelBarang1.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(BarangBonusKaryawanBKView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BarangException ex) {
            Logger.getLogger(BarangBonusKaryawanBKView.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            controllerBarang.sortBarangBonusKaryawan(this);
        } catch (SQLException ex) {
            Logger.getLogger(BarangBonusKaryawanBKView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BarangException ex) {
            Logger.getLogger(BarangBonusKaryawanBKView.class.getName()).log(Level.SEVERE, null, ex);
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
            txtKodeBarang.setText(data);
            txtNamaBarang.setText(tabelBarang1.getValueAt(tabelBarang1.getSelectedRow(), 1).toString());
            txtTipeBarang.setText(tabelBarang1.getValueAt(tabelBarang1.getSelectedRow(), 2).toString());
            txtMerekBarang.setText(tabelBarang1.getValueAt(tabelBarang1.getSelectedRow(), 3).toString());
            DialogCariBarang.dispose();
        }
    }//GEN-LAST:event_pilihActionPerformed

    private void txtKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeBarangActionPerformed

    private void txtKodeBarangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtKodeBarangFocusGained
       
    }//GEN-LAST:event_txtKodeBarangFocusGained

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        // TODO add your handling code here:
        cariBarang();
    }//GEN-LAST:event_btCariActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogCariBarang;
    private javax.swing.JPanel atas;
    private javax.swing.JPanel atas2;
    private javax.swing.JPanel bawah;
    private javax.swing.JPanel bawah2;
    private javax.swing.JButton btCari;
    private javax.swing.JButton btCari1;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btReset;
    private javax.swing.JButton btSetCari;
    private javax.swing.JButton btTambah;
    private javax.swing.JComboBox cmbCari1;
    private javax.swing.JComboBox cmbUrut1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel kanan1;
    private javax.swing.JPanel kiri2;
    private Sinarelektronikapp.swinglib.labeling labeling7;
    private Sinarelektronikapp.swinglib.labeling labeling8;
    private Sinarelektronikapp.swinglib.labeling labeling9;
    private javax.swing.JPanel middle2;
    private javax.swing.JPanel palingAtas;
    private Sinarelektronikapp.swinglib.panelGradUser panelBackgroundUp1;
    private Sinarelektronikapp.swinglib.panelGradUser panelGradUser4;
    private javax.swing.JMenuItem pilih;
    private javax.swing.JPopupMenu popUpTabel;
    private javax.swing.JTable tabelBarang1;
    private javax.swing.JTable tabelTipe;
    private javax.swing.JPanel tengah2;
    private javax.swing.JTextField txtKataKunci1;
    private javax.swing.JTextField txtKodeBarang;
    private javax.swing.JTextField txtMerekBarang;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtTipeBarang;
    private javax.swing.JPanel up2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onChange(BarangBonusKaryawanBKModel model) {        
        txtKodeBarang.setText(model.getKodeBarang());
        txtMerekBarang.setText(model.getMerek());
        txtNamaBarang.setText(model.getNama());
        txtTipeBarang.setText(model.getTipe());
    }

    @Override
    public void onInsert(BarangBonusKaryawanBk tipe) {
        tabelMbbkbk.add(tipe);
    }

    @Override
    public void onDelete() {
        int index = tabelTipe.getSelectedRow();
        JOptionPane.showMessageDialog(null, "index = "+index);
        tabelMbbkbk.remove(index);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        BarangBonusKaryawanBk model = tabelMbbkbk.get(tabelTipe.getSelectedRow());
        txtKodeBarang.setText(model.getKodeBarang());
        txtNamaBarang.setText(model.getNama());
        txtTipeBarang.setText(model.getTipe());
        txtMerekBarang.setText(model.getMerek());
    }
    
    public void LoadDatabase() throws SQLException, SQLException, BarangBonusKaryawanBKException {
        BarangBonusKaryawanDao  dao = BarangBonusKaryawanBKDatabase.getTipeDao();
        tabelMbbkbk.setList(dao.selectAllTipe());
    }        
    
    public void loadDatabaseCariBarang() throws SQLException, BarangException{
        BarangDao dao = barangDatabase.getBarangDao();
        tabelmodelBarang.setList(dao.selectAllBarang());
    }        

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
    public void onSearch(List list) {
        tabelmodelBarang.setList(list);
    }

    @Override
    public void onSort(List list) {
        tabelmodelBarang.setList(list);
    }
}

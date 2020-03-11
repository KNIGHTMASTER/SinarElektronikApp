/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.pelanggan.view;

import com.wissensalt.sinarelektronik.config.UserLevel;
import com.wissensalt.sinarelektronik.masterdata.pelanggan.controller.pelangganController;
import com.wissensalt.sinarelektronik.masterdata.pelanggan.database.pelangganDatabase;
import com.wissensalt.sinarelektronik.masterdata.pelanggan.entity.pelanggan;
import com.wissensalt.sinarelektronik.masterdata.pelanggan.error.pelangganException;
import com.wissensalt.sinarelektronik.masterdata.pelanggan.model.event.pelangganListener;
import com.wissensalt.sinarelektronik.masterdata.pelanggan.model.pelangganModel;
import com.wissensalt.sinarelektronik.masterdata.pelanggan.model.tabelModelPelanggan;
import com.wissensalt.sinarelektronik.masterdata.pelanggan.service.pelangganDao;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Fauzi
 */
public class pelangganView extends javax.swing.JPanel implements pelangganListener, ListSelectionListener{

    /**
     * Creates new form pelangganView
     */
    
    tabelModelPelanggan modelPelanggan;
    
    pelangganController controller;
    
    pelangganModel model;       
    
    public pelangganView() {
        modelPelanggan=new tabelModelPelanggan();
        model=new pelangganModel();
        model.setListener(this);
        
        controller=new pelangganController();
        controller.setModel(model);
        
        initComponents();
        
        tabelPelanggan.getSelectionModel().addListSelectionListener(this);
        tabelPelanggan.setModel(modelPelanggan);
        
        try {
            loadDatabase();
            loadId();
        } catch (SQLException ex) {
            Logger.getLogger(pelangganView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (pelangganException ex) {
            Logger.getLogger(pelangganView.class.getName()).log(Level.SEVERE, null, ex);
        }                        
        

/*    ActionListener taskPerformer = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {        
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
              @Override
              public boolean dispatchKeyEvent(KeyEvent e) {
                  if(e.getID() == KeyEvent.KEY_PRESSED){
                      if(e.getKeyCode() == KeyEvent.VK_F1){
                          tambah();
                      }else if(e.getKeyCode() == KeyEvent.VK_F2){
                          update();
                      }else if(e.getKeyCode() == KeyEvent.VK_F3){
                          delete();
                      }else if(e.getKeyCode() == KeyEvent.VK_F4){
                          reset();
                      }
                  }
                return false;
              }
          });
      }
    };
    // Timer
    new Timer(1000, taskPerformer).start();*/
    setLevel();
    }

    public void setLevel(){
        UserLevel userLevel = new UserLevel();
        String levelAktif = userLevel.getUserLevelActive();
        switch(levelAktif){
            case "Pemilik Toko":
                    btTambah.setEnabled(true);
                    btDelete.setEnabled(true);
                    btReset.setEnabled(true);
                    btUpdate.setEnabled(true);
                break;
            case "Administrator":
                    btTambah.setEnabled(true);
                    btDelete.setEnabled(true);
                    btReset.setEnabled(true);
                    btUpdate.setEnabled(true);
                break;
            case "Karyawan":
                    btTambah.setEnabled(false);
                    btDelete.setEnabled(false);
                    btReset.setEnabled(false);
                    btUpdate.setEnabled(false);                
                break;                        
            default:;
        }
    }    
    
    public JTable getTabelPelanggan() {
        return tabelPelanggan;
    }
    
    public JComboBox getCmbCari() {
        return cmbCari;
    }

    public JComboBox getCmbUrut() {
        return cmbUrut;
    }

    public JTable getjTable1() {
        return tabelPelanggan;
    }

    public JTextField getTxtALamat() {
        return txtALamat;
    }

    public JTextArea getTxtCatatan() {
        return txtCatatan;
    }

    public JTextField getTxtFax() {
        return txtFax;
    }

    public JTextField getTxtIdPelanggan() {
        return txtIdPelanggan;
    }

    public JTextField getTxtKataKunci() {
        return txtKataKunci;
    }

    public JTextField getTxtKodePost() {
        return txtKodePost;
    }

    public JTextField getTxtKontakPerson() {
        return txtKontakPerson;
    }

    public JTextField getTxtKota() {
        return txtKota;
    }

    public JTextField getTxtNama() {
        return txtNama;
    }

    public JTextField getTxtPropinsi() {
        return txtPropinsi;
    }

    public JTextField getTxtTelepon() {
        return txtTelepon;
    }
        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAtas = new javax.swing.JPanel();
        upCombo = new javax.swing.JPanel();
        kiri = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        kanan = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btCari = new javax.swing.JButton();
        txtKataKunci = new javax.swing.JTextField();
        cmbCari = new javax.swing.JComboBox();
        cmbUrut = new javax.swing.JComboBox();
        downTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPelanggan = new javax.swing.JTable();
        panelTengah = new javax.swing.JPanel();
        panelKiri = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtIdPelanggan = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtALamat = new javax.swing.JTextField();
        txtKota = new javax.swing.JTextField();
        txtPropinsi = new javax.swing.JTextField();
        panelKanan = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtKodePost = new javax.swing.JTextField();
        txtTelepon = new javax.swing.JTextField();
        txtFax = new javax.swing.JTextField();
        txtKontakPerson = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCatatan = new javax.swing.JTextArea();
        panelBawah = new javax.swing.JPanel();
        btTambah = new javax.swing.JButton();
        btUpdate = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        btReset = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(700, 500));
        setLayout(new java.awt.BorderLayout());

        panelAtas.setPreferredSize(new java.awt.Dimension(400, 270));
        panelAtas.setLayout(new java.awt.BorderLayout());

        upCombo.setPreferredSize(new java.awt.Dimension(400, 100));
        upCombo.setLayout(new java.awt.BorderLayout());

        kiri.setLayout(new java.awt.GridLayout(3, 0));

        jLabel1.setText("Masukkan Kata Kunci :");
        kiri.add(jLabel1);

        jLabel2.setText("Cari Data Berdasarkan :");
        kiri.add(jLabel2);

        jLabel3.setText("Urut Data Berdasarkan :");
        kiri.add(jLabel3);

        upCombo.add(kiri, java.awt.BorderLayout.WEST);

        kanan.setLayout(new java.awt.GridLayout(3, 0));

        jPanel1.setLayout(new java.awt.BorderLayout());

        btCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/cari.png"))); // NOI18N
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });
        jPanel1.add(btCari, java.awt.BorderLayout.EAST);

        txtKataKunci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKataKunciActionPerformed(evt);
            }
        });
        txtKataKunci.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtKataKunciFocusGained(evt);
            }
        });
        jPanel1.add(txtKataKunci, java.awt.BorderLayout.CENTER);

        kanan.add(jPanel1);

        cmbCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id pelanggan", "nama", "alamat", "kota", "propinsi", "kode post", "telepon", "fax", "kontak person", "catatan" }));
        kanan.add(cmbCari);

        cmbUrut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "id pelanggan", "nama", "alamat", "kota", "propinsi", "kode post", "telepon", "fax", "kontak person", "catatan" }));
        cmbUrut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUrutActionPerformed(evt);
            }
        });
        kanan.add(cmbUrut);

        upCombo.add(kanan, java.awt.BorderLayout.CENTER);

        panelAtas.add(upCombo, java.awt.BorderLayout.PAGE_START);

        downTable.setLayout(new java.awt.BorderLayout());

        tabelPelanggan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelPelanggan);

        downTable.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panelAtas.add(downTable, java.awt.BorderLayout.CENTER);

        add(panelAtas, java.awt.BorderLayout.PAGE_START);

        panelTengah.setPreferredSize(new java.awt.Dimension(487, 200));
        panelTengah.setLayout(new java.awt.GridLayout(1, 2));

        panelKiri.setLayout(new java.awt.GridLayout(1, 2));

        jPanel2.setLayout(new java.awt.GridLayout(5, 0));

        jLabel4.setText("Id Pelanggan :");
        jPanel2.add(jLabel4);

        jLabel6.setText("Nama :");
        jPanel2.add(jLabel6);

        jLabel7.setText("Alamat :");
        jPanel2.add(jLabel7);

        jLabel8.setText("Kota :");
        jPanel2.add(jLabel8);

        jLabel9.setText("Propinsi :");
        jPanel2.add(jLabel9);

        panelKiri.add(jPanel2);

        jPanel3.setLayout(new java.awt.GridLayout(5, 0));

        txtIdPelanggan.setEditable(false);
        txtIdPelanggan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIdPelangganFocusGained(evt);
            }
        });
        jPanel3.add(txtIdPelanggan);
        jPanel3.add(txtNama);
        jPanel3.add(txtALamat);
        jPanel3.add(txtKota);
        jPanel3.add(txtPropinsi);

        panelKiri.add(jPanel3);

        panelTengah.add(panelKiri);

        panelKanan.setLayout(new java.awt.GridLayout(1, 2));

        jPanel4.setLayout(new java.awt.GridLayout(5, 0));

        jLabel5.setText("Kode Post :");
        jPanel4.add(jLabel5);

        jLabel10.setText("Telepon :");
        jPanel4.add(jLabel10);

        jLabel11.setText("Fax :");
        jPanel4.add(jLabel11);

        jLabel12.setText("Kontak Person :");
        jPanel4.add(jLabel12);

        jLabel13.setText("Catatan :");
        jPanel4.add(jLabel13);

        panelKanan.add(jPanel4);

        jPanel5.setLayout(new java.awt.GridLayout(5, 0));
        jPanel5.add(txtKodePost);
        jPanel5.add(txtTelepon);
        jPanel5.add(txtFax);
        jPanel5.add(txtKontakPerson);

        txtCatatan.setColumns(20);
        txtCatatan.setRows(5);
        jScrollPane2.setViewportView(txtCatatan);

        jPanel5.add(jScrollPane2);

        panelKanan.add(jPanel5);

        panelTengah.add(panelKanan);

        add(panelTengah, java.awt.BorderLayout.CENTER);

        panelBawah.setLayout(new java.awt.GridLayout(1, 4));

        btTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/tambah2.png"))); // NOI18N
        btTambah.setMnemonic('T');
        btTambah.setFocusable(false);
        btTambah.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btTambah.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });
        panelBawah.add(btTambah);

        btUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/update2.png"))); // NOI18N
        btUpdate.setMnemonic('U');
        btUpdate.setFocusable(false);
        btUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btUpdate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateActionPerformed(evt);
            }
        });
        panelBawah.add(btUpdate);

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
        panelBawah.add(btDelete);

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
        panelBawah.add(btReset);

        add(panelBawah, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void txtKataKunciFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtKataKunciFocusGained
        try {
            // TODO add your handling code here:
            loadId();
        } catch (SQLException ex) {
            Logger.getLogger(pelangganView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (pelangganException ex) {
            Logger.getLogger(pelangganView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtKataKunciFocusGained

    
    private void tambah(){
        controller.insertPelanggan(this);
        try {
            loadId();
        } catch (SQLException ex) {
            Logger.getLogger(pelangganView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (pelangganException ex) {
            Logger.getLogger(pelangganView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        // TODO add your handling code here:
        tambah();
    }//GEN-LAST:event_btTambahActionPerformed

    private void update(){
        controller.updatePelanggan(this);
    }
    
    private void btUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btUpdateActionPerformed

    private void delete(){
        controller.deletePelanggan(this);
        controller.resetPelanggan();
        try {
            loadId();
        } catch (SQLException ex) {
            Logger.getLogger(pelangganView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (pelangganException ex) {
            Logger.getLogger(pelangganView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btDeleteActionPerformed

    private void reset(){
        controller.resetPelanggan();
        try {
            loadId();
        } catch (SQLException ex) {
            Logger.getLogger(pelangganView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (pelangganException ex) {
            Logger.getLogger(pelangganView.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtNama.requestFocus();
    }
    
    private void btResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btResetActionPerformed

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        // TODO add your handling code here:
        try{
            controller.searchPelanggan(this, this);
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_btCariActionPerformed

    private void cmbUrutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUrutActionPerformed
        // TODO add your handling code here:
        try{
            controller.sortPelanggan(this);
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_cmbUrutActionPerformed

    private void txtIdPelangganFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdPelangganFocusGained
      // TODO add your handling code here:
        try {
            loadId();
        } catch (SQLException ex) {
            Logger.getLogger(pelangganView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (pelangganException ex) {
            Logger.getLogger(pelangganView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtIdPelangganFocusGained

    private void txtKataKunciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKataKunciActionPerformed
        // TODO add your handling code here:
        btCari.doClick();
    }//GEN-LAST:event_txtKataKunciActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCari;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btReset;
    private javax.swing.JButton btTambah;
    private javax.swing.JButton btUpdate;
    private javax.swing.JComboBox cmbCari;
    private javax.swing.JComboBox cmbUrut;
    private javax.swing.JPanel downTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel kanan;
    private javax.swing.JPanel kiri;
    private javax.swing.JPanel panelAtas;
    private javax.swing.JPanel panelBawah;
    private javax.swing.JPanel panelKanan;
    private javax.swing.JPanel panelKiri;
    private javax.swing.JPanel panelTengah;
    private javax.swing.JTable tabelPelanggan;
    private javax.swing.JTextField txtALamat;
    private javax.swing.JTextArea txtCatatan;
    private javax.swing.JTextField txtFax;
    private javax.swing.JTextField txtIdPelanggan;
    private javax.swing.JTextField txtKataKunci;
    private javax.swing.JTextField txtKodePost;
    private javax.swing.JTextField txtKontakPerson;
    private javax.swing.JTextField txtKota;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtPropinsi;
    private javax.swing.JTextField txtTelepon;
    private javax.swing.JPanel upCombo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onChange(pelangganModel model) {
        txtALamat.setText(model.getAlamat());
        txtCatatan.setText(model.getCatatan());
        txtFax.setText(model.getFax());
        txtIdPelanggan.setText(model.getIdpelanggan());
        txtKodePost.setText(model.getKodePost());
        txtKontakPerson.setText(model.getKontakPerson());
        txtKota.setText(model.getKota());
        txtNama.setText(model.getNama());
        txtPropinsi.setText(model.getPropinsi());
        txtTelepon.setText(model.getTelepon());        
    }

    @Override
    public void onInsert(pelanggan pelanggan) {
        modelPelanggan.add(pelanggan);
    }

    @Override
    public void onUpdate(pelanggan pelanggan) {
        int index= tabelPelanggan.getSelectedRow();
        modelPelanggan.set(index, pelanggan);
    }

    @Override
    public void onDelete() {
        int index = tabelPelanggan.getSelectedRow();
        modelPelanggan.remove(index);
    }

    @Override
    public void onSearch(List list) {
        modelPelanggan.setList(list);
    }

    @Override
    public void onSort(List list) {
        modelPelanggan.setList(list);
    }
    
    public void loadDatabase() throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        modelPelanggan.setList(dao.selectAllPelanggan()) ;
    }
    
    public void loadId() throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        int id=dao.getLastId();
        txtIdPelanggan.setText(String.valueOf(id));
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        try{
            pelanggan model=modelPelanggan.get(tabelPelanggan.getSelectedRow());
            txtALamat.setText(model.getAlamat());
            txtCatatan.setText(model.getCatatan());
            txtFax.setText(model.getFax());
            txtIdPelanggan.setText(model.getIdpelanggan());
            txtKodePost.setText(model.getKodePost());
            txtKontakPerson.setText(model.getKontakPerson());
            txtKota.setText(model.getKota());
            txtNama.setText(model.getNama());
            txtPropinsi.setText(model.getPropinsi());
            txtTelepon.setText(model.getTelepon());                    
        }catch(IndexOutOfBoundsException exception){
            
        }
    }
}

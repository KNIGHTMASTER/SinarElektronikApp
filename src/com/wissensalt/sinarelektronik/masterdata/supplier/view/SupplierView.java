package com.wissensalt.sinarelektronik.masterdata.supplier.view;

import com.wissensalt.sinarelektronik.config.UserLevel;
import com.wissensalt.sinarelektronik.dao.SupplierDAO;
import com.wissensalt.sinarelektronik.dao.impl.SupplierDAOImpl;
import com.wissensalt.sinarelektronik.masterdata.supplier.controller.supplierController;
import com.wissensalt.sinarelektronik.masterdata.supplier.entity.SupplierDTO;
import com.wissensalt.sinarelektronik.masterdata.supplier.model.SupplierModel;
import com.wissensalt.sinarelektronik.masterdata.supplier.model.TabelModelSupplier;
import com.wissensalt.sinarelektronik.masterdata.supplier.model.event.supplierListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public class SupplierView extends javax.swing.JPanel implements supplierListener,ListSelectionListener{

    private TabelModelSupplier modelSupplier;
    private supplierController controller;
    private final SupplierDAO supplierDAO;
            
    public SupplierView() {
        supplierDAO = new SupplierDAOImpl();
        modelSupplier = new TabelModelSupplier();

        SupplierModel model = new SupplierModel();
        model.setListener(this);        
        
        controller = new supplierController();
        controller.setModel(model);
        
        initComponents();        
        
        tabelSupplier.getSelectionModel().addListSelectionListener(this);
        tabelSupplier.setModel(modelSupplier);
        loadDatabase();
        setLevel();
    }
    
    public void setLevel(){
        UserLevel userLevel = new UserLevel();
        String levelAktif = userLevel.getUserLevelActive();
        switch(levelAktif){
            case "Pemilik Toko":                    
                    btTambah.setEnabled(true);
                    btHapus.setEnabled(true);
                    btUpdate.setEnabled(true);
                break;
            case "Administrator":
                    btTambah.setEnabled(true);
                    btHapus.setEnabled(true);
                    btUpdate.setEnabled(true);
                break;
            case "Karyawan":
                    btTambah.setEnabled(false);
                    btHapus.setEnabled(false);
                    btUpdate.setEnabled(false);                
                break;                        
            default:;
        }
    }        
    
    final Toolkit toolkit = Toolkit.getDefaultToolkit();
    final Dimension screenSize = toolkit.getScreenSize();
    final int x = (screenSize.width - this.getWidth()) / 4;
    int y = (screenSize.height - this.getHeight()) /4;    

    public JComboBox getCmbCari() {
        return cmbCari;
    }

    public JComboBox getCmbUrut() {
        return cmbUrut;
    }

    public JTextField getTxtKataKunci() {
        return txtKataKunci;
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogTambah = new javax.swing.JDialog();
        tambahSupplierView1 = new com.wissensalt.sinarelektronik.masterdata.supplier.view.tambahSupplierView();
        dialogUpdate = new javax.swing.JDialog();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtBank = new javax.swing.JTextField();
        txtKontakPerson = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNorek = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAtasNama = new javax.swing.JTextField();
        btUpdateTambahSupplier = new javax.swing.JButton();
        btResetTambahSupplier = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtKeterangan = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        txtPropinsi = new javax.swing.JTextField();
        txtKota = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtFax = new javax.swing.JTextField();
        txtTelepon = new javax.swing.JTextField();
        txtKodePost = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtIdsupplier = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        up = new javax.swing.JPanel();
        kanan = new javax.swing.JPanel();
        atas = new javax.swing.JPanel();
        txtKataKunci = new javax.swing.JTextField();
        btCari = new javax.swing.JButton();
        tengah = new javax.swing.JPanel();
        cmbCari = new javax.swing.JComboBox();
        bawah = new javax.swing.JPanel();
        cmbUrut = new javax.swing.JComboBox();
        kiri = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        middle = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelSupplier = new javax.swing.JTable();
        bottom = new javax.swing.JPanel();
        btTambah = new javax.swing.JButton();
        btUpdate = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();

        dialogTambah.setTitle("Tambah Supplier");
        dialogTambah.getContentPane().add(tambahSupplierView1, java.awt.BorderLayout.CENTER);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Nomor Rekening :");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Bank :");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Kontak person :");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Atas Nama :");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Kode Post :");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Propinsi :");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Fax :");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Telepon :");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Alamat :");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Kota :");

        btUpdateTambahSupplier.setMnemonic('U');
        btUpdateTambahSupplier.setText("Update");
        btUpdateTambahSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateTambahSupplierActionPerformed(evt);
            }
        });

        btResetTambahSupplier.setMnemonic('R');
        btResetTambahSupplier.setText("Reset");
        btResetTambahSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetTambahSupplierActionPerformed(evt);
            }
        });

        txtKeterangan.setColumns(20);
        txtKeterangan.setRows(5);
        jScrollPane2.setViewportView(txtKeterangan);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Id Supplier :");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nama :");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Keterangan :");

        txtIdsupplier.setEditable(false);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Email :");

        javax.swing.GroupLayout dialogUpdateLayout = new javax.swing.GroupLayout(dialogUpdate.getContentPane());
        dialogUpdate.getContentPane().setLayout(dialogUpdateLayout);
        dialogUpdateLayout.setHorizontalGroup(
            dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogUpdateLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKontakPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(dialogUpdateLayout.createSequentialGroup()
                            .addComponent(btUpdateTambahSupplier)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btResetTambahSupplier))
                        .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtIdsupplier, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNama, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(txtPropinsi, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKota, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKodePost, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNorek, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(txtAtasNama, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(txtBank, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFax, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelepon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)))))
        );
        dialogUpdateLayout.setVerticalGroup(
            dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogUpdateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPropinsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtKodePost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtBank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtNorek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtAtasNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtKontakPerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dialogUpdateLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dialogUpdateLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btUpdateTambahSupplier)
                    .addComponent(btResetTambahSupplier))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setLayout(new java.awt.BorderLayout());

        up.setLayout(new java.awt.BorderLayout());

        kanan.setOpaque(false);
        kanan.setLayout(new java.awt.GridLayout(3, 0));

        atas.setOpaque(false);

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
                .addComponent(txtKataKunci, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCari, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        atasLayout.setVerticalGroup(
            atasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtKataKunci)
            .addComponent(btCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        kanan.add(atas);

        tengah.setOpaque(false);
        tengah.setLayout(new java.awt.BorderLayout());

        cmbCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "idsupplier", "nama", "alamat", "kota", "propinsi", "kodePost", "telepon", "fax", "bank", "nomorRek", "atasNama", "kontakPerson", "email", "note" }));
        tengah.add(cmbCari, java.awt.BorderLayout.CENTER);

        kanan.add(tengah);

        bawah.setOpaque(false);
        bawah.setLayout(new java.awt.BorderLayout());

        cmbUrut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "idsupplier", "nama", "alamat", "kota", "propinsi", "kodePost", "telepon", "fax", "bank", "nomorRek", "atasNama", "kontakPerson", "email", "note" }));
        cmbUrut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUrutActionPerformed(evt);
            }
        });
        bawah.add(cmbUrut, java.awt.BorderLayout.CENTER);

        kanan.add(bawah);

        up.add(kanan, java.awt.BorderLayout.CENTER);

        kiri.setOpaque(false);
        kiri.setLayout(new java.awt.GridLayout(3, 1));

        jLabel18.setText("Masukkan Kata Kunci :");
        kiri.add(jLabel18);

        jLabel19.setText("Cari Data Berdasarkan :");
        kiri.add(jLabel19);

        jLabel20.setText("Urutkan Data Berdasarkan :");
        kiri.add(jLabel20);

        up.add(kiri, java.awt.BorderLayout.WEST);

        add(up, java.awt.BorderLayout.PAGE_START);

        middle.setLayout(new java.awt.BorderLayout());

        tabelSupplier.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelSupplier);

        middle.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(middle, java.awt.BorderLayout.CENTER);

        bottom.setLayout(new java.awt.GridLayout(1, 3));

        btTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/tambah.png"))); // NOI18N
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

        btHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/hapus.png"))); // NOI18N
        btHapus.setMnemonic('H');
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });
        bottom.add(btHapus);

        add(bottom, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents
    
    private void updateEXEC(){
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setIdsupplier(txtIdsupplier.getText());
        supplierDTO.setNama(txtNama.getText());
        supplierDTO.setAlamat(txtAlamat.getText());
        supplierDTO.setKota(txtKota.getText());
        supplierDTO.setPropinsi(txtPropinsi.getText());
        supplierDTO.setKodePost(txtKodePost.getText());
        supplierDTO.setTelepon(txtTelepon.getText());
        supplierDTO.setFax(txtFax.getText());
        supplierDTO.setBank(txtBank.getText());
        supplierDTO.setNomorRek(txtNorek.getText());
        supplierDTO.setAtasNama(txtAtasNama.getText());
        supplierDTO.setKontakPerson(txtKontakPerson.getText());
        supplierDTO.setEmail(txtEmail.getText());
        supplierDTO.setNote(txtKeterangan.getText());

        supplierDAO.update(supplierDTO);
    }
    private void btUpdateTambahSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateTambahSupplierActionPerformed
        updateEXEC();
        JOptionPane.showMessageDialog(null, "data berhasil diupdate");
        controller.sort(this);
    }//GEN-LAST:event_btUpdateTambahSupplierActionPerformed

    public void resetManual(){
    txtAlamat.setText("");
        txtAtasNama.setText("");
        txtBank.setText("");
        txtEmail.setText("");
        txtFax.setText("");
        txtKataKunci.setText("");
        txtKeterangan.setText("");
        txtKodePost.setText("");
        txtKontakPerson.setText("");
        txtKota.setText("");
        txtNama.setText("");
        txtNorek.setText("");
        txtPropinsi.setText("");
        txtTelepon.setText("");        
    }
    private void btResetTambahSupplierActionPerformed(java.awt.event.ActionEvent evt) {
        resetManual();
    }

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {
        deleteManual();
    }

    private void btUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        updateManual();
        controller.sort(this);
    }

    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        // TODO add your handling code here:
        y = (screenSize.height - this.getHeight()) /6;
        dialogTambah.setLocation(x, y);
        dialogTambah.setSize(400, 650);
        dialogTambah.setModal(true);
        dialogTambah.show();
        controller.sort(this);
    }

    private void cmbUrutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUrutActionPerformed
        controller.sort(this);
    }

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {
        controller.search(this, this);
    }

    private void deleteManual(){
        String id=tabelSupplier.getValueAt(tabelSupplier.getSelectedRow(), 0).toString();
        if(id.trim().equals("")){
            JOptionPane.showMessageDialog(null, "silahkan pilih data terlebih dahulu");
        }else {
            if (JOptionPane.showConfirmDialog(null, "apakah data supplierdengan id = " + id + " akan dihapus?") == JOptionPane.YES_OPTION) {
                supplierDAO.deleteByString(id);
            }
            controller.sort(this);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel atas;
    private javax.swing.JPanel bawah;
    private javax.swing.JPanel bottom;
    private javax.swing.JButton btCari;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btResetTambahSupplier;
    private javax.swing.JButton btTambah;
    private javax.swing.JButton btUpdate;
    private javax.swing.JButton btUpdateTambahSupplier;
    private javax.swing.JComboBox cmbCari;
    private javax.swing.JComboBox cmbUrut;
    private javax.swing.JDialog dialogTambah;
    private javax.swing.JDialog dialogUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel kanan;
    private javax.swing.JPanel kiri;
    private javax.swing.JPanel middle;
    private javax.swing.JTable tabelSupplier;
    private com.wissensalt.sinarelektronik.masterdata.supplier.view.tambahSupplierView tambahSupplierView1;
    private javax.swing.JPanel tengah;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtAtasNama;
    private javax.swing.JTextField txtBank;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFax;
    private javax.swing.JTextField txtIdsupplier;
    private javax.swing.JTextField txtKataKunci;
    private javax.swing.JTextArea txtKeterangan;
    private javax.swing.JTextField txtKodePost;
    private javax.swing.JTextField txtKontakPerson;
    private javax.swing.JTextField txtKota;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNorek;
    private javax.swing.JTextField txtPropinsi;
    private javax.swing.JTextField txtTelepon;
    private javax.swing.JPanel up;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onChange(SupplierModel model) {
        txtKataKunci.setText(model.getIdsupplier());
    }

    @Override
    public void onInsert(SupplierDTO SupplierDTO) {
        modelSupplier.add(SupplierDTO);
    }

    @Override
    public void onUpdate(SupplierDTO SupplierDTO) {
        int index = tabelSupplier.getSelectedRow();
        modelSupplier.set(index, SupplierDTO);
    }

    @Override
    public void onDelete() {
        int index = tabelSupplier.getSelectedRow();
        modelSupplier.remove(index);
    }

    @Override
    public void onSearch(List list) {
        modelSupplier.setList(list);
    }

    @Override
    public void onSort(List list) {
        modelSupplier.setList(list);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        SupplierDTO supplierDTOEntity = modelSupplier.get(tabelSupplier.getSelectedRow());
    }
    
    public void loadDatabase() {
        modelSupplier.setList(supplierDAO.selectAllSupplier());
    }

    private void updateManual(){
        y = (screenSize.height - this.getHeight()) /6;
        try {
            String id=tabelSupplier.getValueAt(tabelSupplier.getSelectedRow(),0).toString();            
            SupplierDTO supplierDTO = supplierDAO.findSingleByField(id, new SupplierDAOImpl().getSearchByIdEqual());
            txtIdsupplier.setText(supplierDTO.getIdsupplier());
            txtNama.setText(supplierDTO.getNama());
            txtAlamat.setText(supplierDTO.getAlamat());
            txtKota.setText(supplierDTO.getKota());
            txtPropinsi.setText(supplierDTO.getPropinsi());
            txtKodePost.setText(supplierDTO.getKodePost());
            txtTelepon.setText(supplierDTO.getTelepon());
            txtFax.setText(supplierDTO.getFax());
            txtBank.setText(supplierDTO.getBank());
            txtNorek.setText(supplierDTO.getNomorRek());
            txtAtasNama.setText(supplierDTO.getAtasNama());
            txtKontakPerson.setText(supplierDTO.getKontakPerson());
            txtEmail.setText(supplierDTO.getEmail());
            txtKeterangan.setText(supplierDTO.getNote());

            dialogUpdate.setLocation(x, y);
            dialogUpdate.setSize(400, 630);
            dialogUpdate.setModal(true);
            dialogUpdate.setTitle("Update Supplier");
            dialogUpdate.show();           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan diupdate");
        }              
    }    
}

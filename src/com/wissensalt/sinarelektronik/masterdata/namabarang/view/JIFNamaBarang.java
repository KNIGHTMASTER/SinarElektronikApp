package com.wissensalt.sinarelektronik.masterdata.namabarang.view;

/**
 *
 * @author Fauzi
 */
public class JIFNamaBarang extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFNamaBarang
     */
    public JIFNamaBarang() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        namaBarangView1 = new com.wissensalt.sinarelektronik.masterdata.namabarang.view.NamaBarangView();

        setClosable(true);
        setTitle("Nama Barang");
        getContentPane().add(namaBarangView1, java.awt.BorderLayout.CENTER);

        pack();
    }

    private com.wissensalt.sinarelektronik.masterdata.namabarang.view.NamaBarangView namaBarangView1;
}

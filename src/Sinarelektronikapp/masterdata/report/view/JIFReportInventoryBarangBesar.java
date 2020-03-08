/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.report.view;

import Sinarelektronikapp.masterdata.report.core.ReportParam;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class JIFReportInventoryBarangBesar extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFReportInventoryBarangBesar
     */
    public JIFReportInventoryBarangBesar() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTransaksi = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTanggalAwal = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtTanggalAkhir = new com.toedter.calendar.JDateChooser();
        btRetur = new javax.swing.JButton();
        btDetailRetur = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        panelTransaksi.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Laporan Inventory Barang Besar"));
        panelTransaksi.setLayout(new java.awt.GridLayout(3, 2));

        jLabel1.setText("Tanggal Awal :");
        panelTransaksi.add(jLabel1);
        panelTransaksi.add(txtTanggalAwal);

        jLabel2.setText("Tanggal Akhir :");
        panelTransaksi.add(jLabel2);
        panelTransaksi.add(txtTanggalAkhir);

        btRetur.setText("Inventory");
        btRetur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReturActionPerformed(evt);
            }
        });
        panelTransaksi.add(btRetur);

        btDetailRetur.setText("Detail Inventory");
        btDetailRetur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDetailReturActionPerformed(evt);
            }
        });
        panelTransaksi.add(btDetailRetur);

        getContentPane().add(panelTransaksi, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReturActionPerformed
        // TODO add your handling code here:
        if(txtTanggalAwal.getDate()==null){
            txtTanggalAwal.requestFocus();
            JOptionPane.showMessageDialog(null, "Tanggal Awal masih kosong");
            return;
        }else if (txtTanggalAkhir.getDate() == null){
            txtTanggalAkhir.requestFocus();
            JOptionPane.showMessageDialog(null, "Tanggal Akhir masih kosong");
            return;
        }
        else{
            ReportParam rp=new ReportParam();
            rp.buildReportDoubleParam("inventorybarangbesar", txtTanggalAwal.getDate(), txtTanggalAkhir.getDate());
        }
    }//GEN-LAST:event_btReturActionPerformed

    private void btDetailReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDetailReturActionPerformed
        // TODO add your handling code here:
        if(txtTanggalAwal.getDate()==null){
            txtTanggalAwal.requestFocus();
            JOptionPane.showMessageDialog(null, "Tanggal Awal masih kosong");
            return;
        }else if (txtTanggalAkhir.getDate() == null){
            txtTanggalAkhir.requestFocus();
            JOptionPane.showMessageDialog(null, "Tanggal Akhir masih kosong");
            return;
        }
        else{
            ReportParam rp=new ReportParam();
            rp.buildReportDoubleParam("detailinventorybarangbesar", txtTanggalAwal.getDate(), txtTanggalAkhir.getDate());
        }
    }//GEN-LAST:event_btDetailReturActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDetailRetur;
    private javax.swing.JButton btRetur;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelTransaksi;
    private com.toedter.calendar.JDateChooser txtTanggalAkhir;
    private com.toedter.calendar.JDateChooser txtTanggalAwal;
    // End of variables declaration//GEN-END:variables
}
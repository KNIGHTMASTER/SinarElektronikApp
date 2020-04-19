package com.wissensalt.sinarelektronik.masterdata.tipe.view;

/**
 *
 * @author Fauzi
 */
public class JIFTipeView extends javax.swing.JInternalFrame {

    public JIFTipeView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipeView2 = new com.wissensalt.sinarelektronik.masterdata.tipe.view.TipeView();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Manajemen Tipe Barang");
        setPreferredSize(new java.awt.Dimension(400, 300));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        getContentPane().add(tipeView2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.wissensalt.sinarelektronik.masterdata.tipe.view.TipeView tipeView2;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.user.view;

/**
 *
 * @author Fauzi
 */
public class JIFUserView extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFUserView
     */
    public JIFUserView() {
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

        try {
            userView1 = new Sinarelektronikapp.masterData.user.view.userView();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Manajemen User");
        setPreferredSize(new java.awt.Dimension(800, 500));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        getContentPane().add(userView1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Sinarelektronikapp.masterData.user.view.userView userView1;
    // End of variables declaration//GEN-END:variables
}

package com.wissensalt.sinarelektronik.masterdata.barangtoko.reminderbarangtoko.view;

/**
 *
 * @author Fauzi
 */
public class JIFReminderViewToko extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFReminderViewToko
     */
    public JIFReminderViewToko() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {

        reminderView1 = new com.wissensalt.sinarelektronik.masterdata.barangbesar.reminderbarangbesar.view.ReminderView();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Stok Ulang Barang Toko");
        getContentPane().add(reminderView1, java.awt.BorderLayout.CENTER);

        pack();
    }
    private com.wissensalt.sinarelektronik.masterdata.barangbesar.reminderbarangbesar.view.ReminderView reminderView1;
}
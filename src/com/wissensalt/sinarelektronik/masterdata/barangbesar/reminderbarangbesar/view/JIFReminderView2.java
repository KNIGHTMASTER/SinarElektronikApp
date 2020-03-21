/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.barangbesar.reminderbarangbesar.view;

/**
 *
 * @author Fauzi
 */
public class JIFReminderView2 extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFReminderView2
     */
    public JIFReminderView2() {
        initComponents();
    }

    private void initComponents() {

        reminderView1 = new com.wissensalt.sinarelektronik.masterdata.barangbesar.reminderbarangbesar.view.ReminderView();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Stok Ulang Barang Besar");
        getContentPane().add(reminderView1, java.awt.BorderLayout.CENTER);

        pack();
    }
    private com.wissensalt.sinarelektronik.masterdata.barangbesar.reminderbarangbesar.view.ReminderView reminderView1;
}

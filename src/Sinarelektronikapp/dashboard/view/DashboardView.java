/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.dashboard.view;

import Sinarelektronikapp.view.MainFrame;
import java.awt.CardLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class DashboardView extends javax.swing.JPanel {

    /**
     * Creates new form DashboardView
     */
    public DashboardView() {
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

        Left = new javax.swing.JPanel();
        btDashboard = new Sinarelektronikapp.swingResource.loadBtImage();
        btMaster = new Sinarelektronikapp.swingResource.loadBtImage();
        btTransaksi = new Sinarelektronikapp.swingResource.loadBtImage();
        btReport = new Sinarelektronikapp.swingResource.loadBtImage();
        btMaintenance = new Sinarelektronikapp.swingResource.loadBtImage();
        Center = new javax.swing.JPanel();
        dashboard = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        master = new javax.swing.JPanel();
        btBarang = new Sinarelektronikapp.swingResource.loadBtImage();
        btMerek = new Sinarelektronikapp.swingResource.loadBtImage();
        btPelanggan = new Sinarelektronikapp.swingResource.loadBtImage();
        btSupplier = new Sinarelektronikapp.swingResource.loadBtImage();
        btSatuan = new Sinarelektronikapp.swingResource.loadBtImage();
        btTipe = new Sinarelektronikapp.swingResource.loadBtImage();
        transaksi = new javax.swing.JPanel();
        btKasir = new Sinarelektronikapp.swingResource.loadBtImage();
        laporan = new javax.swing.JPanel();
        loadBtImage11 = new Sinarelektronikapp.swingResource.loadBtImage();
        loadBtImage13 = new Sinarelektronikapp.swingResource.loadBtImage();
        maintenance = new javax.swing.JPanel();
        loadBtImage12 = new Sinarelektronikapp.swingResource.loadBtImage();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        Left.setMinimumSize(new java.awt.Dimension(64, 90));
        Left.setOpaque(false);
        Left.setLayout(new java.awt.GridLayout(1, 5));

        btDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/BlackDashboardButton.png"))); // NOI18N
        btDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDashboardActionPerformed(evt);
            }
        });
        Left.add(btDashboard);

        btMaster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/BlackMaster.png"))); // NOI18N
        btMaster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMasterActionPerformed(evt);
            }
        });
        Left.add(btMaster);

        btTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/Blacktransaksi.png"))); // NOI18N
        btTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTransaksiActionPerformed(evt);
            }
        });
        Left.add(btTransaksi);

        btReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/Blackreport.png"))); // NOI18N
        btReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportActionPerformed(evt);
            }
        });
        Left.add(btReport);

        btMaintenance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/Blackmaintenance.png"))); // NOI18N
        btMaintenance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMaintenanceActionPerformed(evt);
            }
        });
        Left.add(btMaintenance);

        add(Left, java.awt.BorderLayout.PAGE_END);

        Center.setOpaque(false);
        Center.setLayout(new java.awt.CardLayout());

        dashboard.setOpaque(false);
        dashboard.setLayout(new java.awt.BorderLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/Verde.jpg"))); // NOI18N
        dashboard.add(jLabel1, java.awt.BorderLayout.CENTER);

        Center.add(dashboard, "dashboard");

        master.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/barang.png"))); // NOI18N
        btBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBarangActionPerformed(evt);
            }
        });
        master.add(btBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 130, 120));

        btMerek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/merek.png"))); // NOI18N
        btMerek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMerekActionPerformed(evt);
            }
        });
        master.add(btMerek, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 140, 120));

        btPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/pelanggan.png"))); // NOI18N
        btPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPelangganActionPerformed(evt);
            }
        });
        master.add(btPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 150, 130));

        btSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/supplier.png"))); // NOI18N
        btSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSupplierActionPerformed(evt);
            }
        });
        master.add(btSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 140, 120));

        btSatuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/satuan.png"))); // NOI18N
        btSatuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSatuanActionPerformed(evt);
            }
        });
        master.add(btSatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 160, 110));

        btTipe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/tipe.png"))); // NOI18N
        btTipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTipeActionPerformed(evt);
            }
        });
        master.add(btTipe, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 230, 160, 110));

        Center.add(master, "master");

        transaksi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btKasir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/kasir.png"))); // NOI18N
        btKasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btKasirActionPerformed(evt);
            }
        });
        transaksi.add(btKasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, -1, -1));

        Center.add(transaksi, "transaksi");

        loadBtImage11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/BlackMaster.png"))); // NOI18N
        loadBtImage11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadBtImage11ActionPerformed(evt);
            }
        });

        loadBtImage13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/Blacktransaksi.png"))); // NOI18N
        loadBtImage13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadBtImage13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout laporanLayout = new javax.swing.GroupLayout(laporan);
        laporan.setLayout(laporanLayout);
        laporanLayout.setHorizontalGroup(
            laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(laporanLayout.createSequentialGroup()
                .addGap(312, 312, 312)
                .addComponent(loadBtImage11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(loadBtImage13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(388, Short.MAX_VALUE))
        );
        laporanLayout.setVerticalGroup(
            laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(laporanLayout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addGroup(laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(loadBtImage13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadBtImage11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(493, Short.MAX_VALUE))
        );

        Center.add(laporan, "laporan");

        loadBtImage12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageResource/backUp.png"))); // NOI18N
        loadBtImage12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadBtImage12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout maintenanceLayout = new javax.swing.GroupLayout(maintenance);
        maintenance.setLayout(maintenanceLayout);
        maintenanceLayout.setHorizontalGroup(
            maintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
            .addGroup(maintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(maintenanceLayout.createSequentialGroup()
                    .addGap(0, 453, Short.MAX_VALUE)
                    .addComponent(loadBtImage12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 454, Short.MAX_VALUE)))
        );
        maintenanceLayout.setVerticalGroup(
            maintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 765, Short.MAX_VALUE)
            .addGroup(maintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(maintenanceLayout.createSequentialGroup()
                    .addGap(0, 336, Short.MAX_VALUE)
                    .addComponent(loadBtImage12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 336, Short.MAX_VALUE)))
        );

        Center.add(maintenance, "maintenance");

        add(Center, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDashboardActionPerformed
        // TODO add your handling code here:
        CardLayout cl=(CardLayout) Center.getLayout();
        cl.show(Center, "dashboard");
    }//GEN-LAST:event_btDashboardActionPerformed

    private void btMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMasterActionPerformed
        // TODO add your handling code here:
        CardLayout cl=(CardLayout) Center.getLayout();
        cl.show(Center, "master");        
    }//GEN-LAST:event_btMasterActionPerformed

    private void btTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTransaksiActionPerformed
        // TODO add your handling code here:
        CardLayout cl=(CardLayout) Center.getLayout();
        cl.show(Center, "transaksi");        
    }//GEN-LAST:event_btTransaksiActionPerformed

    private void btReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportActionPerformed
        // TODO add your handling code here:
        CardLayout cl=(CardLayout) Center.getLayout();
        cl.show(Center, "laporan");        
    }//GEN-LAST:event_btReportActionPerformed

    private void btMaintenanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMaintenanceActionPerformed
        // TODO add your handling code here:
        CardLayout cl=(CardLayout) Center.getLayout();
        cl.show(Center, "maintenance");        
    }//GEN-LAST:event_btMaintenanceActionPerformed

    private void btKasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKasirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btKasirActionPerformed

    private void loadBtImage11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadBtImage11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loadBtImage11ActionPerformed

    private void loadBtImage12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadBtImage12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loadBtImage12ActionPerformed

    private void btBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBarangActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "1");        
        MainFrame x = new MainFrame();
        JOptionPane.showMessageDialog(null, "2");
        x.callMenuBarang();
        JOptionPane.showMessageDialog(null, "3");
    }//GEN-LAST:event_btBarangActionPerformed

    private void btMerekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMerekActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btMerekActionPerformed

    private void btPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btPelangganActionPerformed

    private void btSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btSupplierActionPerformed

    private void btSatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSatuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btSatuanActionPerformed

    private void btTipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTipeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btTipeActionPerformed

    private void loadBtImage13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadBtImage13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loadBtImage13ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Center;
    private javax.swing.JPanel Left;
    private Sinarelektronikapp.swingResource.loadBtImage btBarang;
    private Sinarelektronikapp.swingResource.loadBtImage btDashboard;
    private Sinarelektronikapp.swingResource.loadBtImage btKasir;
    private Sinarelektronikapp.swingResource.loadBtImage btMaintenance;
    private Sinarelektronikapp.swingResource.loadBtImage btMaster;
    private Sinarelektronikapp.swingResource.loadBtImage btMerek;
    private Sinarelektronikapp.swingResource.loadBtImage btPelanggan;
    private Sinarelektronikapp.swingResource.loadBtImage btReport;
    private Sinarelektronikapp.swingResource.loadBtImage btSatuan;
    private Sinarelektronikapp.swingResource.loadBtImage btSupplier;
    private Sinarelektronikapp.swingResource.loadBtImage btTipe;
    private Sinarelektronikapp.swingResource.loadBtImage btTransaksi;
    private javax.swing.JPanel dashboard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel laporan;
    private Sinarelektronikapp.swingResource.loadBtImage loadBtImage11;
    private Sinarelektronikapp.swingResource.loadBtImage loadBtImage12;
    private Sinarelektronikapp.swingResource.loadBtImage loadBtImage13;
    private javax.swing.JPanel maintenance;
    private javax.swing.JPanel master;
    private javax.swing.JPanel transaksi;
    // End of variables declaration//GEN-END:variables
}

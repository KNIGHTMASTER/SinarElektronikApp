package Sinarelektronikapp.chart.view;

import Sinarelektronikapp.chart.core.Core;
import Sinarelektronikapp.config.HostName;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartUtilities;

/**
 *
 * @author Administrator
 */
public class JIFChartView extends javax.swing.JInternalFrame {

    /**
     * Creates new form PenjualBK
     */
    public JIFChartView() {
        initComponents();
        koneksi();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogOpsiGrafikKaryawanBB = new javax.swing.JDialog();
        panelTransaksi = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTanggalAwal = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtTanggalAkhir = new com.toedter.calendar.JDateChooser();
        btPieChartBB = new javax.swing.JButton();
        btBarChartBB = new javax.swing.JButton();
        dialogOpsiGrafikKaryawanBK = new javax.swing.JDialog();
        panelTransaksi1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTanggalAwalBK = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtTanggalAkhirBK = new com.toedter.calendar.JDateChooser();
        btPieChartBK = new javax.swing.JButton();
        btBarChartBK = new javax.swing.JButton();
        btGrafikKaryawanBB = new javax.swing.JButton();
        btGrafikKaryawanBK = new javax.swing.JButton();

        dialogOpsiGrafikKaryawanBB.setMinimumSize(new java.awt.Dimension(300, 120));
        dialogOpsiGrafikKaryawanBB.setPreferredSize(new java.awt.Dimension(300, 120));

        panelTransaksi.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Grafik Karyawan Barang Besar"));
        panelTransaksi.setLayout(new java.awt.GridLayout(3, 2));

        jLabel1.setText("Tanggal Awal :");
        panelTransaksi.add(jLabel1);
        panelTransaksi.add(txtTanggalAwal);

        jLabel2.setText("Tanggal Akhir :");
        panelTransaksi.add(jLabel2);
        panelTransaksi.add(txtTanggalAkhir);

        btPieChartBB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/pieChart.png"))); // NOI18N
        btPieChartBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPieChartBBActionPerformed(evt);
            }
        });
        panelTransaksi.add(btPieChartBB);

        btBarChartBB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/barChart.png"))); // NOI18N
        btBarChartBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBarChartBBActionPerformed(evt);
            }
        });
        panelTransaksi.add(btBarChartBB);

        dialogOpsiGrafikKaryawanBB.getContentPane().add(panelTransaksi, java.awt.BorderLayout.CENTER);

        dialogOpsiGrafikKaryawanBK.setMinimumSize(new java.awt.Dimension(300, 120));
        dialogOpsiGrafikKaryawanBK.setPreferredSize(new java.awt.Dimension(300, 120));

        panelTransaksi1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Grafik Karyawan Barang Kecil"));
        panelTransaksi1.setLayout(new java.awt.GridLayout(3, 2));

        jLabel3.setText("Tanggal Awal :");
        panelTransaksi1.add(jLabel3);
        panelTransaksi1.add(txtTanggalAwalBK);

        jLabel4.setText("Tanggal Akhir :");
        panelTransaksi1.add(jLabel4);
        panelTransaksi1.add(txtTanggalAkhirBK);

        btPieChartBK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/pieChart.png"))); // NOI18N
        btPieChartBK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPieChartBKActionPerformed(evt);
            }
        });
        panelTransaksi1.add(btPieChartBK);

        btBarChartBK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/barChart.png"))); // NOI18N
        btBarChartBK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBarChartBKActionPerformed(evt);
            }
        });
        panelTransaksi1.add(btBarChartBK);

        dialogOpsiGrafikKaryawanBK.getContentPane().add(panelTransaksi1, java.awt.BorderLayout.CENTER);

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Grafik Performa Karyawan");
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        btGrafikKaryawanBB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/barangbesar.png"))); // NOI18N
        btGrafikKaryawanBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGrafikKaryawanBBActionPerformed(evt);
            }
        });
        getContentPane().add(btGrafikKaryawanBB);

        btGrafikKaryawanBK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sinarelektronikapp/imageresource/barang.png"))); // NOI18N
        btGrafikKaryawanBK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGrafikKaryawanBKActionPerformed(evt);
            }
        });
        getContentPane().add(btGrafikKaryawanBK);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public Connection c = null;
    
    Core core = new Core();
    HostName ip1 = new HostName();
    
    public void koneksi(){
        String url = "jdbc:mysql://"+ip1.getIpServer()+"/sinarelektronik?;";
        String pass = "P@ssw0rd";
        String user = "root";
        try {
            Class.forName("com.mysql.jdbc.Driver");            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JIFChartView.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            c = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(JIFChartView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    SimpleDateFormat formatTanggal = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formatTanggal2 = new SimpleDateFormat("dd:MM:YYYY");
    
    public void buildPieChartKaryawanBB(Date tanggal1, Date tanggal2){        
        String tanggalAwal = formatTanggal.format(tanggal1);
        String tanggalAkhir = formatTanggal.format(tanggal2);
        String [] names = null;
        Integer [] values = null;
        Statement s = null;
        try{    
            int panjangNama = 0;
            s = c.createStatement();
            ResultSet rs = s.executeQuery("select count(nama) as hasil from penjualbb");
            if(rs.next()){
                panjangNama = rs.getInt("hasil");
            }
            names = new String[panjangNama];
            values = new Integer[panjangNama];
            int a=0;
            Statement s1 = c.createStatement();
            ResultSet rs1 = s1.executeQuery("select nama from penjualbb");
            while (rs1.next()) {
                names[a] = (rs1.getString("nama"));
                Statement s2 = c.createStatement();
                ResultSet rs2 = s2.executeQuery("SELECT count(iddetailtransaksi) as hasil FROM `detailtransaksipenjualanbarangbesar` WHERE `tanggal` >= '"+tanggalAwal+"' and `tanggal` <= '"+tanggalAkhir+"' AND `penjual` LIKE '"+names[a]+"'");
                if(rs2.next()){                    
                    values[a] = rs2.getInt("hasil");
                }
                a++;
            }
        }catch(SQLException exception){
            JOptionPane.showConfirmDialog(null, "error karena = "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TestChart.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //core.writeChartToPDF(core.generatePieChart("grafik penjual Barang Besar", names, values), 400, 500, "D://grafikPenjualBB.pdf");
        try {
        ChartUtilities.saveChartAsJPEG(new File("D:\\grafikPenjualBB.jpg"), core.generatePieChart("grafik Penjual Barang Besar\ntanggal :"+formatTanggal2.format(tanggal1)+" - "+formatTanggal2.format(tanggal2), names, values), 500, 300);
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Problem occurred creating chart because "+e);
        }                
        core.openFile("D://grafikPenjualBB.jpg");        
    }
    
    public void buildPieChartKaryawanBK(Date tanggal1, Date tanggal2){        
        String tanggalAwal = formatTanggal.format(tanggal1);
        String tanggalAkhir = formatTanggal.format(tanggal2);
        String [] names = null;
        Integer [] values = null;
        Statement s = null;
        try{    
            int panjangNama = 0;
            s = c.createStatement();
            ResultSet rs = s.executeQuery("select count(nama) as hasil from penjualbk");
            if(rs.next()){
                panjangNama = rs.getInt("hasil");
            }
            names = new String[panjangNama];
            values = new Integer[panjangNama];
            int a=0;
            Statement s1 = c.createStatement();
            ResultSet rs1 = s1.executeQuery("select nama from penjualbk");
            while (rs1.next()) {
                names[a] = (rs1.getString("nama"));
                Statement s2 = c.createStatement();
                ResultSet rs2 = s2.executeQuery("SELECT count(iddetailtransaksi) as hasil FROM `detailtransaksipenjualan` WHERE `tanggal` >= '"+tanggalAwal+"' and `tanggal` <= '"+tanggalAkhir+"' AND `penjual` LIKE '"+names[a]+"'");
                if(rs2.next()){                    
                    values[a] = rs2.getInt("hasil");
                }
                a++;
            }
        }catch(SQLException exception){
            JOptionPane.showConfirmDialog(null, "error karena = "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TestChart.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //core.writeChartToPDF(core.generatePieChart("grafik penjual Barang Besar", names, values), 400, 500, "D://grafikPenjualBB.pdf");
        try {
        ChartUtilities.saveChartAsJPEG(new File("D:\\grafikPenjualBK.jpg"), core.generatePieChart("grafik Penjual Barang Kecil\ntanggal : "+formatTanggal2.format(tanggal1)+" - "+formatTanggal2.format(tanggal2), names, values), 500, 300);
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Problem occurred creating chart because "+e);
        }                
        core.openFile("D://grafikPenjualBK.jpg");
    }    
    
    public void buildBarChartKaryawanBB(Date tanggal1, Date tanggal2){        
        String tanggalAwal = formatTanggal.format(tanggal1);
        String tanggalAkhir = formatTanggal.format(tanggal2);
        String [] names = null;
        Integer [] values = null;
        Statement s = null;
        try{    
            int panjangNama = 0;
            s = c.createStatement();
            ResultSet rs = s.executeQuery("select count(nama) as hasil from penjualbb");
            if(rs.next()){
                panjangNama = rs.getInt("hasil");
            }
            names = new String[panjangNama];
            values = new Integer[panjangNama];
            int a=0;
            Statement s1 = c.createStatement();
            ResultSet rs1 = s1.executeQuery("select nama from penjualbb");
            while (rs1.next()) {
                names[a] = (rs1.getString("nama"));
                Statement s2 = c.createStatement();
                ResultSet rs2 = s2.executeQuery("SELECT count(iddetailtransaksi) as hasil FROM `detailtransaksipenjualanbarangbesar` WHERE `tanggal` >= '"+tanggalAwal+"' and `tanggal` <= '"+tanggalAkhir+"' AND `penjual` LIKE '"+names[a]+"'");
                if(rs2.next()){                    
                    values[a] = rs2.getInt("hasil");
                }
                a++;
            }
        }catch(SQLException exception){
            JOptionPane.showConfirmDialog(null, "error karena = "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TestChart.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //core.writeChartToPDF(core.generatePieChart("grafik penjual Barang Besar", names, values), 400, 500, "D://grafikPenjualBB.pdf");
        try {
        ChartUtilities.saveChartAsJPEG(new File("D:\\grafikPenjualBB.jpg"), core.generateBarChart("grafik Penjual Barang Besar\ntanggal "+formatTanggal2.format(tanggal1)+" - "+formatTanggal2.format(tanggal2), names, values), 500, 300);
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Problem occurred creating chart because "+e);
        }                
        core.openFile("D://grafikPenjualBB.jpg");        
    }    
    
    public void buildBarChartKaryawanBK(Date tanggal1, Date tanggal2){        
        String tanggalAwal = formatTanggal.format(tanggal1);
        String tanggalAkhir = formatTanggal.format(tanggal2);
        String [] names = null;
        Integer [] values = null;
        Statement s = null;
        try{    
            int panjangNama = 0;
            s = c.createStatement();
            ResultSet rs = s.executeQuery("select count(nama) as hasil from penjualbk");
            if(rs.next()){
                panjangNama = rs.getInt("hasil");
            }
            names = new String[panjangNama];
            values = new Integer[panjangNama];
            int a=0;
            Statement s1 = c.createStatement();
            ResultSet rs1 = s1.executeQuery("select nama from penjualbk");
            while (rs1.next()) {
                names[a] = (rs1.getString("nama"));
                Statement s2 = c.createStatement();
                ResultSet rs2 = s2.executeQuery("SELECT count(iddetailtransaksi) as hasil FROM `detailtransaksipenjualan` WHERE `tanggal` >= '"+tanggalAwal+"' and `tanggal` <= '"+tanggalAkhir+"' AND `penjual` LIKE '"+names[a]+"'");
                if(rs2.next()){                    
                    values[a] = rs2.getInt("hasil");
                }
                a++;
            }
        }catch(SQLException exception){
            JOptionPane.showConfirmDialog(null, "error karena = "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TestChart.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //core.writeChartToPDF(core.generatePieChart("grafik penjual Barang Besar", names, values), 400, 500, "D://grafikPenjualBB.pdf");
        try {
        ChartUtilities.saveChartAsJPEG(new File("D:\\grafikBarPenjualBK.jpg"), core.generateBarChart("grafik Barang Penjual Barang Besar\ntanggal : "+formatTanggal2.format(tanggal1)+" - "+formatTanggal2.format(tanggal2), names, values), 500, 300);
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Problem occurred creating chart because "+e);
        }                
        core.openFile("D://grafikBarPenjualBK.jpg");        
    }        
    
    private void btGrafikKaryawanBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGrafikKaryawanBBActionPerformed
        // TODO add your handling code here:
        int y = 200;
        int x = 400;
        dialogOpsiGrafikKaryawanBB.setModal(true);
        dialogOpsiGrafikKaryawanBB.setLocation(x, y);
        dialogOpsiGrafikKaryawanBB.setSize(500, 400);
        dialogOpsiGrafikKaryawanBB.show();        
    }//GEN-LAST:event_btGrafikKaryawanBBActionPerformed

    private void btGrafikKaryawanBKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGrafikKaryawanBKActionPerformed
        // TODO add your handling code here:
        int y = 200;
        int x = 400;
        dialogOpsiGrafikKaryawanBK.setModal(true);
        dialogOpsiGrafikKaryawanBK.setLocation(x, y);
        dialogOpsiGrafikKaryawanBK.setSize(500, 400);
        dialogOpsiGrafikKaryawanBK.show();                
    }//GEN-LAST:event_btGrafikKaryawanBKActionPerformed

    private void btPieChartBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPieChartBBActionPerformed
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
            buildPieChartKaryawanBB(txtTanggalAwal.getDate(), txtTanggalAkhir.getDate());
        }
    }//GEN-LAST:event_btPieChartBBActionPerformed

    private void btBarChartBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBarChartBBActionPerformed
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
            buildBarChartKaryawanBB(txtTanggalAwal.getDate(), txtTanggalAkhir.getDate());
        }
    }//GEN-LAST:event_btBarChartBBActionPerformed

    private void btPieChartBKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPieChartBKActionPerformed
        // TODO add your handling code here:
        buildPieChartKaryawanBK(txtTanggalAwalBK.getDate(), txtTanggalAkhirBK.getDate());
    }//GEN-LAST:event_btPieChartBKActionPerformed

    private void btBarChartBKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBarChartBKActionPerformed
        // TODO add your handling code here:
        buildBarChartKaryawanBK(txtTanggalAwalBK.getDate(), txtTanggalAkhirBK.getDate());
    }//GEN-LAST:event_btBarChartBKActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBarChartBB;
    private javax.swing.JButton btBarChartBK;
    private javax.swing.JButton btGrafikKaryawanBB;
    private javax.swing.JButton btGrafikKaryawanBK;
    private javax.swing.JButton btPieChartBB;
    private javax.swing.JButton btPieChartBK;
    private javax.swing.JDialog dialogOpsiGrafikKaryawanBB;
    private javax.swing.JDialog dialogOpsiGrafikKaryawanBK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel panelTransaksi;
    private javax.swing.JPanel panelTransaksi1;
    private com.toedter.calendar.JDateChooser txtTanggalAkhir;
    private com.toedter.calendar.JDateChooser txtTanggalAkhirBK;
    private com.toedter.calendar.JDateChooser txtTanggalAwal;
    private com.toedter.calendar.JDateChooser txtTanggalAwalBK;
    // End of variables declaration//GEN-END:variables
}

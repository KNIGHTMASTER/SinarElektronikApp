package com.wissensalt.sinarelektronik.transaction.barangbesar.controller;

import com.wissensalt.sinarelektronik.transaction.barangbesar.model.TransaksiPenjualanBBModel;
import com.wissensalt.sinarelektronik.transaction.barangbesar.view.JIFTransaksiPenjualanBB;
import com.wissensalt.sinarelektronik.config.HostName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class TransaksiPenjualanBBController {
    TransaksiPenjualanBBModel model;

    public TransaksiPenjualanBBModel getModel() {
        return model;
    }

    public void setModel(TransaksiPenjualanBBModel model) {
        this.model = model;
    }

    public TransaksiPenjualanBBController() {
    }

    private Connection connection;
            
	
    static HostName ip1 = new HostName();    
    public void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "error karena = "+ex);
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://"+ip1.getIpServer()+"/sinarelektronik?;", "root", "P@ssw0rd");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error karena = "+ex);
        }    
    }
    
    public void deleteTransaksi(JIFTransaksiPenjualanBB jiftpbb){
        if(jiftpbb.getTabelTransaksiPenjualan().getSelectedRowCount()==0){
            JOptionPane.showMessageDialog(jiftpbb, "pilih data yang akan dihapus", "perhatian", JOptionPane.WARNING_MESSAGE);
            return;
        }else if(JOptionPane.showConfirmDialog(jiftpbb, "Apakah anda yakin akan menghapus data ?") == JOptionPane.YES_OPTION){
            int id = (int) jiftpbb.getTabelTransaksiPenjualan().getValueAt(jiftpbb.getTabelTransaksiPenjualan().getSelectedRow(), 0);
            model.setIdtransaksi(id);

            //proses pengembalian data yang telah dicancel transaksinya 
            koneksi();
            String query = "select kode, jumlah from detailtransaksipenjualanbarangbesar where iddetailtransaksi = "+id;
            Statement s = null;
            int stokLama = 0;
            String posisi = "";
            try{
                connection.setAutoCommit(false);
                s = connection.createStatement();
                ResultSet rs = s.executeQuery(query);
                String kode = "";
                int jumlah = 0;
                while (rs.next()) {                                        
                    kode = rs.getString(1);
                    jumlah = rs.getInt(2);
                    Object [] options = {"toko", "gudang", "cancel (tidak keduanya)"};
                    int n = JOptionPane.showOptionDialog(jiftpbb, "Silahkan pilih posisi barangkecil yang akan dikembalikan", "Posisi barangkecil pengembalian", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    String queryposisi = "";
                    String queryupdate = "";
                    if(n == JOptionPane.YES_OPTION){
                        queryposisi = "select stok from barangtoko where idbarang = '"+kode+"'";
                        queryupdate = "update barangtoko set stok = ? where idbarang = ?";
                    }else if(n == JOptionPane.NO_OPTION){
                        queryposisi = "select stok from barangbesar where idbarang = '"+kode+"'";
                        queryupdate = "update barangbesar set stok = ? where idbarang = ?";
                    }else{
                        queryposisi = null;
                        queryupdate = null;
                    }                    
                    Statement s2 = connection.createStatement();
                    ResultSet rs2 = s2.executeQuery(queryposisi);
                    if(rs2.next()){
                        stokLama = rs2.getInt(1);
                    }                
                    int stokBaru = jumlah+stokLama;                    
                    PreparedStatement ps = connection.prepareStatement(queryupdate);
                    ps.setInt(1, stokBaru);
                    ps.setString(2, kode);
                    ps.executeUpdate();
                }
                connection.commit();
            }catch(Exception exception){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiPenjualanBBController.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(jiftpbb, "error karena = "+exception);
            }finally{    
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiPenjualanBBController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(s!=null){
                    try {
                        s.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(TransaksiPenjualanBBController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
//                if (connection != null) {
//                    try {
//                        connection.close();
//                    } catch (SQLException ex) {
//                        Logger.getLogger(TransaksiPenjualanBBController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
            }
        }                        
            model.deleteTransaksiPenjualanBB();
        }    
}

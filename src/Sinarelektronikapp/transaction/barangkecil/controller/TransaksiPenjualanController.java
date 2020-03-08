package Sinarelektronikapp.transaction.barangkecil.controller;

import Sinarelektronikapp.transaction.barangkecil.model.TransaksiPenjualanModel;
import Sinarelektronikapp.transaction.barangkecil.view.JIFManajemenTransaksiPenjualan;
import Sinarelektronikapp.config.HostName;
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
public class TransaksiPenjualanController {
    TransaksiPenjualanModel transaksiPenjualanModel;

    public TransaksiPenjualanController() {
    }

    public TransaksiPenjualanModel getTransaksiPenjualanModel() {
        return transaksiPenjualanModel;
    }

    public void setTransaksiPenjualanModel(TransaksiPenjualanModel transaksiPenjualanModel) {
        this.transaksiPenjualanModel = transaksiPenjualanModel;
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
    
    public void deleteTransaksiPenjualan(JIFManajemenTransaksiPenjualan jifmtp){
        if(jifmtp.getTabelTransaksiPenjualan().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(jifmtp, "pilih data yang akan dihapus");
            return;
        }else if(JOptionPane.showConfirmDialog(jifmtp, "yakin data akan dihapus ?") == JOptionPane.YES_OPTION){
            String no = jifmtp.getTabelTransaksiPenjualan().getValueAt(jifmtp.getTabelTransaksiPenjualan().getSelectedRow(), 0).toString();
            transaksiPenjualanModel.setIdTransaksi(Integer.parseInt(no));            
            
            //proses pengembalian data yang telah dicancel transaksinya 
            koneksi();
            String query = "select kode, jumlah from detailtransaksipenjualan where iddetailtransaksi = "+no;
            Statement s = null;
            int stokLama = 0;
            try{
                connection.setAutoCommit(false);
                s = connection.createStatement();
                ResultSet rs = s.executeQuery(query);
                String kode = "";
                int jumlah = 0;
                while (rs.next()) {                    
                    kode = rs.getString(1);
                    jumlah = rs.getInt(2);
                    String query2 = "select stok from barang where idbarang = '"+kode+"'";
                    Statement s2 = connection.createStatement();
                    ResultSet rs2 = s2.executeQuery(query2);
                    if(rs2.next()){
                        stokLama = rs2.getInt(1);
                    }                
                    int stokBaru = jumlah+stokLama;                    
                    String query3 = "update barang set stok = ? where idbarang = ?";
                    PreparedStatement ps = connection.prepareStatement(query3);
                    ps.setInt(1, stokBaru);
                    ps.setString(2, kode);
                    ps.executeUpdate();
                }
                connection.commit();
            }catch(Exception exception){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiPenjualanController.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(jifmtp, "error karena = "+exception);
            }finally{    
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiPenjualanController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(s!=null){
                    try {
                        s.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(TransaksiPenjualanController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        transaksiPenjualanModel.deleteTransaksiPenjualan();
    }
       
    public void cari(JIFManajemenTransaksiPenjualan aThis) {
        String kataKunci = aThis.getTxtKataKunci().getText();
        String berdasarkan = aThis.getCmbCari().getSelectedItem().toString();
        JOptionPane.showMessageDialog(null, "kata kunci = "+kataKunci);
        JOptionPane.showMessageDialog(null, "berdasarkan= "+berdasarkan);
        JOptionPane.showMessageDialog(null, "oke contorller");
        transaksiPenjualanModel.cari(kataKunci, berdasarkan);
        JOptionPane.showMessageDialog(null, "oke contorller 2");
    }

    public void sort(JIFManajemenTransaksiPenjualan aThis) {
        String berdasarkan = aThis.getCmbUrut().getSelectedItem().toString();        
        transaksiPenjualanModel.sort(berdasarkan);
    }    
}

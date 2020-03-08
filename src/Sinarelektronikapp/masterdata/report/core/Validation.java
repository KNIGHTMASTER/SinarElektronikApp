/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.report.core;

import Sinarelektronikapp.config.HostName;
import java.net.URL;
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
public class Validation {
    
    public Connection conn = null;

    public Validation() {
    }    
     
    HostName ip = new HostName();
    
    public void koneksi(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+ip.getIpServer()+"/sinarelektronik?;", "root", "P@ssw0rd");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error pada koneksi report karena = "+e);
        }
    }
       
   private URL reportPath = getClass().getResource("\\masterdata\\report\\");
   public void validasiReport() {
        koneksi();
        String [] namaReport = {
            "barangkecil",
            "detailtransaksipenjualan", 
            "merek", 
            "pelanggan", 
            "satuan", 
            "transaksipenjualan", 
            "tipe", 
            "supplier", 
            "transaksipenjualanDoubleParam", 
            "detailtransaksipenjualanDoubleParam", 
            "struk2", 
            "Profit", 
            "profitdetail", 
            "retur", 
            "detailretur", 
            "inventory", 
            "detailinventory", 
            "kasirpricelist", 
            "grosirpricelist", 
            "retailpricelist", 
            "barangbesar", 
            "transaksibarangbesar", 
            "detailtransaksibarangbesar", 
            "returbarangbesar", 
            "detailreturbarangbesar", 
            "inventorybarangbesar", 
            "detailinventorybarangbesar",
            "profitbarangbesar", 
            "detailprofitbarangbesar", 
            "barangperkategori", 
            "baranggrosirperkategori", 
            "barangeceranperkategori", 
            "insentif", 
            "pricelistmerekha", 
            "pricelistmerekal", 
            "pricelistmereksp", 
            "transferbb", 
            "struktransfer", 
            "strukbb", 
            "barangbesarwithoutstok", 
            "pengembalianbarangkecil",
            "pengembalianbarangbesar",
            "insentifbk",
            "barangtokomaster",
            "barangbesarpricelist"
        };
        String nama[] = new String[namaReport.length];
        Statement s = null;
        try {
            //ambil isi database
            s= conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT nama FROM report");
            int a=0;
            while (rs.next()) {                                
                nama[a] = rs.getString("nama");                
                a++;
            }
                        
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "error pada "+ex);           
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Validation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if(nama[0] == null && nama[3] == null){
            JOptionPane.showMessageDialog(null, "nama 0 = "+nama[0]);
            for(int a=0; a<namaReport.length; a++){
		PreparedStatement ps = null;
                try {
                //save address to database            
                ps = conn.prepareStatement("INSERT INTO report (nama, pathsource, pathdest) VALUES (?, ?, ?)");
                ps.setString(1, namaReport[a]);
                String pathSource = reportPath+"templates\\"+namaReport[a]+".jrxml";
                String pathDest = reportPath+"results\\"+namaReport[a]+".html";
                ps.setString(2, pathSource);
                ps.setString(3, pathDest);
                ps.executeUpdate();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "error terjadi pada penyimpanan data "+ex);
                }finally{
                    if(ps!=null){
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }
                }                          
            }
        }else{
            
        }                        
    }
}

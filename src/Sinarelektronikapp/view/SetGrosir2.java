/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author FAUZI
 */
public class SetGrosir2 {

    public SetGrosir2() {
    }
    
    private static Connection c = null;
    
    public static void update(String id, int hasil){        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/sinarelektronik?;", "root", "P@ssw0rd");
            PreparedStatement ps = c.prepareStatement("update barang set grosir2=? where idbarang=?");
            ps.setInt(1, hasil);
            ps.setString(2, id);
            ps.executeUpdate();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error Update karena = "+e);
        }    
    }
    
    public static void main(String [] args){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/sinarelektronik?;", "root", "P@ssw0rd");
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select idbarang, grosir, eceran from barang");
            int hasil = 0;
            String idbarang = "";
            while (rs.next()) {
                hasil = (rs.getInt("grosir")+rs.getInt("eceran"))/2;
                idbarang = rs.getString("idbarang");
                update(idbarang, hasil);
                System.out.println("id "+idbarang+" sukses terupdate");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error Results Set karena = "+e);
        }
    }
        
}

package com.wissensalt.sinarelektronik.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author FAUZI
 */
public class SetGrosir2BarangKecil {

    public SetGrosir2BarangKecil() {
    }   

    public static void update(String id, int hasil, Connection c) throws SQLException, ClassNotFoundException {                
        PreparedStatement ps = c.prepareStatement("update barang set grosir2=? where idbarang=?");
        ps.setInt(1, hasil);
        ps.setString(2, id);
        ps.executeUpdate();   
        
        ps.close();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/sinarelektronik?;", "root", "P@ssw0rd");
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("select idbarang, grosir, eceran from barang");
        int hasil = 0;
        String idbarang;
        while (rs.next()) {
            hasil = (rs.getInt("grosir") + rs.getInt("eceran")) / 2;
            idbarang = rs.getString("idbarang");
            update(idbarang, hasil, c);
            System.out.println("id " + idbarang + " sukses terupdate");
        }

        rs.close();
        s.close();
        c.close();
    }

}

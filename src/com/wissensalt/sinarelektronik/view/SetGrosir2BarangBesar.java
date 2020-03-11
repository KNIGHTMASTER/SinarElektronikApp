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
public class SetGrosir2BarangBesar {

    public SetGrosir2BarangBesar() {
    }   
    
    private static final int GROSIR_2_ADDTIONAL = 100000;
    private static final int ECERAN_ADDITIONAL = 200000;

    public static void update(String idBarang, int grosir2, int eceran, Connection c) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = c.prepareStatement("update barangbesar set grosir2=?, eceran=? where idbarang=?;");
        ps.setInt(1, grosir2);
        ps.setInt(2, eceran);
        ps.setString(3, idBarang);
       
        ps.executeUpdate();   
        
        ps.close();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/sinarelektronik?;", "root", "P@ssw0rd");
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("select idbarang, modal from barangbesar");
        int grosir2 = 0;
        int eceran = 0;
        String idbarang;
        while (rs.next()) {            
            grosir2 = rs.getInt("modal") + GROSIR_2_ADDTIONAL;
            eceran = rs.getInt("modal") + ECERAN_ADDITIONAL;
            idbarang = rs.getString("idbarang");          
            update(idbarang, grosir2, eceran, c);
            System.out.println("id " + idbarang + " sukses terupdate");
        }

        rs.close();
        s.close();
        c.close();
    }

}

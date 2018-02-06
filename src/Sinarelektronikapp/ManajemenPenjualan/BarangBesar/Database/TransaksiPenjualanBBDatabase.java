/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Database;

import Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Service.TransaksiPenjualanBBDao;
import Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Service.TransaksiPenjualanBBDaoImpl;
import Sinarelektronikapp.config.InternetProtocol;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class TransaksiPenjualanBBDatabase {
    private static Connection connection;
    
    private static TransaksiPenjualanBBDao dao;
    
    private static InternetProtocol ip = new InternetProtocol();

    public static Connection getConnection() {
        if(connection == null){
            MysqlDataSource mds = new MysqlDataSource();
            mds.setURL("jdbc:mysql://"+ip.getIpServer()+":3306/sinarelektronik");
            mds.setUser("root");
            mds.setPassword("5430trisin9");
            try {
                connection = mds.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenjualanBBDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        return connection;
    }
    
    public static TransaksiPenjualanBBDao getTransaksiPenjualanBBDao(){
        if(dao == null){
            dao =  new TransaksiPenjualanBBDaoImpl(getConnection());
        }
        return dao;
    }
        
}

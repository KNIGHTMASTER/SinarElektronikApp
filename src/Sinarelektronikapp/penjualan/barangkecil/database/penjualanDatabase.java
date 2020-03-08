/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.penjualan.barangkecil.database;

import Sinarelektronikapp.config.HostName;
import Sinarelektronikapp.penjualan.barangkecil.service.impl.penjualanDaoImpl;
import Sinarelektronikapp.penjualan.barangkecil.service.penjualanDao;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class penjualanDatabase {
    private static Connection connection;
    
    private static penjualanDao dao;    
    
    static HostName ip1 = new HostName();
    
    public static Connection getConnection() {
        if(connection==null){
            MysqlDataSource dataSource=new MysqlDataSource();
            dataSource.setUrl("jdbc:mysql://"+ip1.getIpServer()+":3306/sinarelektronik");
            dataSource.setUser("root");
            dataSource.setPassword("P@ssw0rd");
            try {
                connection=dataSource.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(penjualanDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
    
    public static penjualanDao getPenjualanDao(){
        if(dao == null){
            dao = new penjualanDaoImpl(getConnection());
        }
        return dao;
    }
}

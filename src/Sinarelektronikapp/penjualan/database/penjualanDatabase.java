/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.penjualan.database;

import Sinarelektronikapp.config.InternetProtocol;
import Sinarelektronikapp.penjualan.service.impl.penjualanDaoImpl;
import Sinarelektronikapp.penjualan.service.penjualanDao;
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
    
    static InternetProtocol ip1 = new InternetProtocol();
    
    public static Connection getConnection() {
        if(connection==null){
            MysqlDataSource dataSource=new MysqlDataSource();
            dataSource.setUrl("jdbc:mysql://"+ip1.getIpServer()+":3306/sinarelektronik");
            dataSource.setUser("root");
            dataSource.setPassword("5430trisin9");
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.penjualan.garansi.database;

import Sinarelektronikapp.config.InternetProtocol;
import Sinarelektronikapp.penjualan.garansi.service.GaransiDao;
import Sinarelektronikapp.penjualan.garansi.service.impl.GaransiDaoImpl;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class GaransiDatabase {
    public static Connection connection;
    
    public static GaransiDao dao;

    private static String ip;
    
    static InternetProtocol ip1 = new InternetProtocol();
    
    public static Connection getConnection(){
        if(connection == null){
            MysqlDataSource  dataSource = new MysqlDataSource();
            dataSource.setUrl("jdbc:mysql://"+ip1.getIpServer()+":3306/sinarelektronik");
            dataSource.setUser("root");
            dataSource.setPassword("5430trisin9");
            try {
                connection = dataSource.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;        
    }
    
    public static GaransiDao getGaransiDao(){
        if(dao == null){
            dao = new GaransiDaoImpl(getConnection());
        }
        return  dao;
    }
}

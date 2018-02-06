/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterData.tipe.database;

import Sinarelektronikapp.config.InternetProtocol;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import Sinarelektronikapp.masterData.tipe.service.impl.TipeDaoImpl;
import Sinarelektronikapp.masterData.tipe.service.tipeDao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class tipeDatabase {
    private static Connection connection;
    
    private static tipeDao dao;

    private static String ip;
    
    
    static InternetProtocol ip1 = new InternetProtocol();
    
    public static Connection getConnection() throws SQLException {
        if(connection == null){
            MysqlDataSource dataSource = new MysqlDataSource();            
            dataSource.setUrl("jdbc:mysql://"+ip1.getIpServer()+":3306/sinarelektronik");
            dataSource.setUser("root");
            dataSource.setPassword("5430trisin9");
            
            connection = dataSource.getConnection();
        }
        return connection;
    }
        
    public static tipeDao getTipeDao() throws SQLException {
        if(dao == null){
            dao = new TipeDaoImpl(getConnection());
        }
        return dao;
    }
}

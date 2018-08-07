/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterData.satuan.database;

import Sinarelektronikapp.config.InternetProtocol;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import Sinarelektronikapp.masterData.satuan.service.impl.satuanDaoImpl;
import Sinarelektronikapp.masterData.satuan.service.satuanDao;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class satuanDatabase {
    private static Connection connection;
    
    private static satuanDao dao;
    
    static InternetProtocol ip1 = new InternetProtocol();
    
    public static Connection getConnection() throws SQLException {
        
        if(connection == null){
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUrl("jdbc:mysql://"+ip1.getIpServer()+":3306/sinarelektronik");
            dataSource.setUser("root");
            dataSource.setPassword("P@ssw0rd");
            
            connection = dataSource.getConnection();
        }
        return connection;
    }
    
    public static satuanDao getSatuanDao() throws SQLException{
        if(dao == null){
            dao = new satuanDaoImpl(getConnection());
        }
        return dao;
    }
    
}

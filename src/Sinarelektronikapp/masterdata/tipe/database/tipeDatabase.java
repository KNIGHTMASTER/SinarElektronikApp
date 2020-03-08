/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterdata.tipe.database;

import Sinarelektronikapp.config.HostName;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import Sinarelektronikapp.masterdata.tipe.service.impl.TipeDaoImpl;
import Sinarelektronikapp.masterdata.tipe.service.tipeDao;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class tipeDatabase {
    private static Connection connection;
    
    private static tipeDao dao;

    private static String ip;
    
    
    static HostName ip1 = new HostName();
    
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
        
    public static tipeDao getTipeDao() throws SQLException {
        if(dao == null){
            dao = new TipeDaoImpl(getConnection());
        }
        return dao;
    }
}

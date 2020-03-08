/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.user.database;

import Sinarelektronikapp.config.InternetProtocol;
import Sinarelektronikapp.masterdata.user.service.impl.userDaoImpl;
import Sinarelektronikapp.masterdata.user.service.userDao;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class userdatabase {
    
    private static Connection connection;
    
    private static userDao dao;

    private static String ip;
    
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
    
    public static userDao getUserDao() throws SQLException{
        if(dao == null){
            dao=new userDaoImpl(getConnection());
        }
        return dao;
    }
    
}

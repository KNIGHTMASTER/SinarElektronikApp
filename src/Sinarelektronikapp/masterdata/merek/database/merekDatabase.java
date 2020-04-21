/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterdata.merek.database;

import Sinarelektronikapp.AppConstant;
import Sinarelektronikapp.config.InternetProtocol;
import Sinarelektronikapp.masterdata.merek.service.impl.merekDaoImpl;
import Sinarelektronikapp.masterdata.merek.service.merekDao;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class merekDatabase {
    private static Connection connection;
    
    private static merekDao dao;    
    
    static InternetProtocol ip1 = new InternetProtocol();
    
    public static Connection getConnection() throws SQLException {
        if(connection == null){
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUrl("jdbc:mysql://"+ip1.getIpServer()+":3306/sinarelektronik");
            dataSource.setUser("root");
            dataSource.setPassword(AppConstant.DB_PASSWORD);
            
            connection = dataSource.getConnection();
        }
        return connection;
    }
    
    public static merekDao getMerekDao() throws SQLException{
        if(dao == null){
            dao = new merekDaoImpl(getConnection());
        }
        return dao;
    }
}

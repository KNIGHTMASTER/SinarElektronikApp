/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.supplier.database;

import Sinarelektronikapp.config.InternetProtocol;
import Sinarelektronikapp.masterdata.supplier.service.impl.supplierDaoImpl;
import Sinarelektronikapp.masterdata.supplier.service.supplierDao;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class supplierDatabase {
    
    private static Connection connection;
    
    private static  supplierDao dao;
    
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
    
    public static supplierDao getSupplierDao() throws SQLException{
        if(dao==null){
            dao = new supplierDaoImpl(getConnection());
        }return dao;
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.ReturBarangBesar.transaksi.database;

import Sinarelektronikapp.config.InternetProtocol;
import Sinarelektronikapp.ReturBarangBesar.transaksi.service.ReturDao;
import Sinarelektronikapp.ReturBarangBesar.transaksi.service.ReturDaoImpl;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class ReturDatabase {

    private static Connection connection;
    
    private static ReturDao dao;    
    
    static InternetProtocol ip1 = new InternetProtocol();
    
    public static Connection getConnection() {
        if(connection==null){
            MysqlDataSource dataSource=new MysqlDataSource();
            dataSource.setUrl("jdbc:mysql://"+ip1.getIpServer()+":3306/sinarelektronik");
            dataSource.setUser("root");
            dataSource.setPassword("P@ssw0rd");
            try {
                connection=dataSource.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(ReturDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
    
    public static ReturDao getReturDao(){
        if(dao == null){
            dao = (ReturDao) new ReturDaoImpl(getConnection());
        }
        return dao;
    }    
}

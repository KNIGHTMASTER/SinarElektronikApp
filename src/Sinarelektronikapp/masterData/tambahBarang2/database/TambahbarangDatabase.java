/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterData.tambahBarang2.database;

import Sinarelektronikapp.config.InternetProtocol;
import Sinarelektronikapp.masterData.tambahBarang2.service.TambahBarangDao;
import Sinarelektronikapp.masterData.tambahBarang2.service.impl.TambahBarangDaoImpl;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class TambahbarangDatabase {
    private static Connection connection;
    
    private static TambahBarangDao dao;
        
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

    public static TambahBarangDao getBarangDao() throws SQLException{
        if(dao == null){
            dao = new TambahBarangDaoImpl(getConnection());
        }
        return dao;
    }
    
}

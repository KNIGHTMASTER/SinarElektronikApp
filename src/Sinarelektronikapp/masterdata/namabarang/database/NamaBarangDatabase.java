/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.namabarang.database;

import Sinarelektronikapp.config.HostName;
import Sinarelektronikapp.masterdata.namabarang.service.NamaBarangDao;
import Sinarelektronikapp.masterdata.namabarang.service.NamaBarangDaoImpl;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class NamaBarangDatabase {
    private static Connection connection;
    
    private static NamaBarangDao dao;    
    
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
    
    public static NamaBarangDao getNamaBarangDao() throws SQLException{
        if(dao == null){
            dao = new NamaBarangDaoImpl(getConnection());
        }
        return dao;
    }
    
}

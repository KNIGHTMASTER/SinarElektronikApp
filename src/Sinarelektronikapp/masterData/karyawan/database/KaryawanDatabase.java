/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.karyawan.database;

import Sinarelektronikapp.config.InternetProtocol;
import Sinarelektronikapp.masterData.karyawan.service.KaryawanDao;
import Sinarelektronikapp.masterData.karyawan.service.KaryawanDaoImpl;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class KaryawanDatabase {

    static Connection connection;
    
    static KaryawanDao dao;
    
    public KaryawanDatabase() {
    }

    static InternetProtocol ip1 = new InternetProtocol();
    
    public static Connection getConnection(){
        if(connection == null){
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUrl("jdbc:mysql://"+ip1.getIpServer()+":3306/sinarelektronik");
            dataSource.setUser("root");
            dataSource.setPassword("P@ssw0rd");
            try {
                connection = dataSource.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(KaryawanDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }   
    
    public static KaryawanDao getKaryawanDao(){
        if(dao == null){
            dao = new KaryawanDaoImpl(getConnection());
        }
        
        return dao;
        
    }
    
}

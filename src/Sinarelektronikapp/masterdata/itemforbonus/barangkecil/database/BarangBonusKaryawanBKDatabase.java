/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterdata.itemforbonus.barangkecil.database;

import Sinarelektronikapp.config.InternetProtocol;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.service.impl.BarangBonusKaryawanBKDaoImpl;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.service.BarangBonusKaryawanDao;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class BarangBonusKaryawanBKDatabase {
    private static Connection connection;
    
    private static BarangBonusKaryawanDao dao;

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
        
    public static BarangBonusKaryawanDao getTipeDao() throws SQLException {
        if(dao == null){
            dao = new BarangBonusKaryawanBKDaoImpl(getConnection());
        }
        return dao;
    }
}

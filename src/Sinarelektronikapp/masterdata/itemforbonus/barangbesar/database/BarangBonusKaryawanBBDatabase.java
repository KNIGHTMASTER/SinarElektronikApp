/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterdata.itemforbonus.barangbesar.database;

import Sinarelektronikapp.config.InternetProtocol;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import Sinarelektronikapp.masterdata.itemforbonus.barangbesar.service.impl.BarangBonusKaryawanBBDaoImpl;
import Sinarelektronikapp.masterdata.itemforbonus.barangbesar.service.BarangBonusKaryawanBBDao;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class BarangBonusKaryawanBBDatabase {
    private static Connection connection;
    
    private static BarangBonusKaryawanBBDao dao;

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
        
    public static BarangBonusKaryawanBBDao getTipeDao() throws SQLException {
        if(dao == null){
            dao = new BarangBonusKaryawanBBDaoImpl(getConnection());
        }
        return dao;
    }
}

package com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangbesar.database;

import com.wissensalt.sinarelektronik.config.HostName;
import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangbesar.service.impl.BarangBonusKaryawanBBDaoImpl;
import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangbesar.service.BarangBonusKaryawanBBDao;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
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
    
    
    public static Connection getConnection() throws SQLException {
        if(connection == null){
            MysqlDataSource dataSource = new MysqlDataSource();            
            dataSource.setUrl("jdbc:mysql://"+new HostName().getIpServer()+":3306/sinarelektronik");
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

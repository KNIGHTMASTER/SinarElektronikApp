package com.wissensalt.sinarelektronik.transaction.barangkecil.database;

import com.wissensalt.sinarelektronik.config.HostName;
import com.wissensalt.sinarelektronik.transaction.barangkecil.service.TransaksiPenjualanDao;
import com.wissensalt.sinarelektronik.transaction.barangkecil.service.TransaksiPenjualanDaoImpl;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class TransaksiPenjualanDatabase {


    private static Connection connection;
    
    private static TransaksiPenjualanDao dao;    
    
    static Connection getConnection() {
        if(connection==null){
            MysqlDataSource dataSource=new MysqlDataSource();
            dataSource.setUrl("jdbc:mysql://"+new HostName().getIpServer()+":3306/sinarelektronik");
            dataSource.setUser("root");
            dataSource.setPassword("P@ssw0rd");
            try {
                connection=dataSource.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenjualanDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
    
    public static TransaksiPenjualanDao getTransaksiPenjualanDao(){
        if(dao == null){
            dao = (TransaksiPenjualanDao) new TransaksiPenjualanDaoImpl(getConnection());
        }
        return dao;
    }    
    
}

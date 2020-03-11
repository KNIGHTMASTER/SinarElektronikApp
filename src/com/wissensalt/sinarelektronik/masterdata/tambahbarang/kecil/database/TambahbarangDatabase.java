/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wissensalt.sinarelektronik.masterdata.tambahbarang.kecil.database;

import com.wissensalt.sinarelektronik.config.HostName;
import com.wissensalt.sinarelektronik.masterdata.tambahbarang.kecil.service.TambahBarangDao;
import com.wissensalt.sinarelektronik.masterdata.tambahbarang.kecil.service.impl.TambahBarangDaoImpl;
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

    public static TambahBarangDao getBarangDao() throws SQLException{
        if(dao == null){
            dao = new TambahBarangDaoImpl(getConnection());
        }
        return dao;
    }
    
}

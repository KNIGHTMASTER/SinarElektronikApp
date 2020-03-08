package Sinarelektronikapp.dao;

import Sinarelektronikapp.config.HostName;
import Sinarelektronikapp.inventory.barangbesar.database.InventoryDatabase;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class GenericConnection {
    private static Connection connection;
    
    public static Connection getConnection() {
        if(connection==null){
            MysqlDataSource dataSource=new MysqlDataSource();
            dataSource.setUrl("jdbc:mysql://"+ new HostName().getIpServer()+":3306/sinarelektronik");
            dataSource.setUser("root");
            dataSource.setPassword("P@ssw0rd");
            try {
                connection=dataSource.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(InventoryDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
}

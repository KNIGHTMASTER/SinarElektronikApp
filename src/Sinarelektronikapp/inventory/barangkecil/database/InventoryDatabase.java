package Sinarelektronikapp.inventory.barangkecil.database;

import Sinarelektronikapp.config.InternetProtocol;
import Sinarelektronikapp.inventory.barangkecil.service.InventoryDao;
import Sinarelektronikapp.inventory.barangkecil.service.InventoryDaoImpl;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class InventoryDatabase {

    private static Connection connection;
    
    private static InventoryDao dao;    
    
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
                Logger.getLogger(InventoryDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
    
    public static InventoryDao getInventoryDao(){
        if(dao == null){
            dao = new InventoryDaoImpl(getConnection());
        }
        return dao;
    }        
}

package Sinarelektronikapp.masterdata.barangtoko.stokreminder.database;

import Sinarelektronikapp.config.HostName;
import Sinarelektronikapp.masterdata.barangtoko.stokreminder.service.ReminderDao;
import Sinarelektronikapp.masterdata.barangtoko.stokreminder.service.impl.ReminderDaoImpl;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class ReminderDatabase {
    private static Connection connection;
    
    private static ReminderDao dao;    
    
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

    public static ReminderDao getReminderDao() throws SQLException{
        if(dao == null){
            dao = new ReminderDaoImpl(getConnection());
        }
        return dao;
    }
    
}

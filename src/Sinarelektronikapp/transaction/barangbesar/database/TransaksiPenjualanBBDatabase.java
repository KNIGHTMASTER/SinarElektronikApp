package Sinarelektronikapp.transaction.barangbesar.database;

import Sinarelektronikapp.AppConstant;
import Sinarelektronikapp.transaction.barangbesar.service.TransaksiPenjualanBBDao;
import Sinarelektronikapp.transaction.barangbesar.service.TransaksiPenjualanBBDaoImpl;
import Sinarelektronikapp.config.InternetProtocol;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class TransaksiPenjualanBBDatabase {
    private static Connection connection;
    
    private static TransaksiPenjualanBBDao dao;
    
    private static InternetProtocol ip = new InternetProtocol();

    public static Connection getConnection() {
        if(connection == null){
            MysqlDataSource mds = new MysqlDataSource();
            mds.setURL("jdbc:mysql://"+ip.getIpServer()+":3306/sinarelektronik");
            mds.setUser("root");
            mds.setPassword(AppConstant.DB_PASSWORD);
            try {
                connection = mds.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenjualanBBDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        return connection;
    }
    
    public static TransaksiPenjualanBBDao getTransaksiPenjualanBBDao(){
        if(dao == null){
            dao =  new TransaksiPenjualanBBDaoImpl(getConnection());
        }
        return dao;
    }
        
}

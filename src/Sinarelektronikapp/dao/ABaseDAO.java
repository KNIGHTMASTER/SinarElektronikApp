package Sinarelektronikapp.dao;

import Sinarelektronikapp.dto.BaseDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 * @param <DTO>
 */
 public abstract class ABaseDAO<DTO extends BaseDTO> implements IBaseDAO<DTO> {

    protected Connection connection;
    
    public void initConnection() {
        try {
            connection = GenericConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ABaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void insert(DTO dto) {
        initConnection();
        PreparedStatement ps = null;
        try{
            connection.setAutoCommit(false);
            insertDetail(dto, ps);
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(ABaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Error dalam proses insert karena = "+exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ABaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteByInt(int id) {
        initConnection();
        PreparedStatement ps = null;
        try{
            connection.setAutoCommit(false);          
            ps = connection.prepareStatement(getDeleteQueryById());
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(ABaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Error dalam proses delete karena = "+exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
                if (connection != null) connection.close();
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ABaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void truncate() {
        initConnection();
        PreparedStatement ps = null;
        try{
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(getTruncateQuery());
            ps.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(ABaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Error dalam proses truncate karena = "+exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
                if(ps != null)ps.close();
                if(connection != null)connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ABaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    }

    @Override
    public void deleteByString(String id) {
        initConnection();
        PreparedStatement ps = null;
        try{
            connection.setAutoCommit(false);          
            ps = connection.prepareStatement(getDeleteQueryByString());
            ps.setString(1, id);
            ps.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(ABaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Error dalam proses delete karena = "+exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
                if (connection != null) connection.close();
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ABaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

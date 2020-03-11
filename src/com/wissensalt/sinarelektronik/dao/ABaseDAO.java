package com.wissensalt.sinarelektronik.dao;

import com.wissensalt.sinarelektronik.dto.BaseDTO;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 * @param <DTO>
 */
 public abstract class ABaseDAO<DTO extends BaseDTO> implements IBaseDAO<DTO> {

    protected Connection connection;
    
    private void initConnection() {
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
    public void update(DTO dto) {
        initConnection();
        PreparedStatement ps = null;
        try{
            connection.setAutoCommit(false);
            ps = updateDetail(dto, ps);
            ps.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(ABaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Update barangkecil besar gagal karena "+exception, "Perhatian", JOptionPane.WARNING_MESSAGE);
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
    public List<DTO> findByFieldLike(String id, String query) {
        initConnection();
        PreparedStatement ps = null;
        List<DTO> list = new ArrayList<>();
        try {            
            ps = connection.prepareStatement(query);
            ps.setString(1, "%"+id+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(getDTOFromResultSet(rs));
            }            
        } catch (SQLException ex) {
            Logger.getLogger(ABaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
                if(ps != null)ps.close();
                if(connection != null)connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ABaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
        
        return list;
    }

    @Override
    public List<DTO> findByField(String field, String query) {
        initConnection();
        PreparedStatement ps = null;
        List<DTO> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, field);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(getDTOFromResultSet(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ABaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
                if(ps != null)ps.close();
                if(connection != null)connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ABaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }

    @Override
    public List<DTO> findAndSortByField(String query) {
        initConnection();
        Statement statement = null;
        List<DTO> list = new ArrayList<>();
        try {            
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(getDTOFromResultSet(rs));
            }            
        } catch (SQLException ex) {
            Logger.getLogger(ABaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
                if(statement != null)statement.close();
                if(connection != null)connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ABaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        
        return list;
    }

    @Override
    public int findLastIdByField(String field, String query) {
        initConnection();
        Statement statement = null;
        int total = 0;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                total = rs.getInt(field);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ABaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
                if(statement != null)statement.close();
                if(connection != null)connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ABaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        return total;
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
    
    @Override
    public void insertDetail(DTO arg0, PreparedStatement arg1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDeleteQueryById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTruncateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

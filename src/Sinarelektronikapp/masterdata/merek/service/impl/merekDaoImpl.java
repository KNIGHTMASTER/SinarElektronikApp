/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterdata.merek.service.impl;

import Sinarelektronikapp.masterdata.merek.entity.merek;
import Sinarelektronikapp.masterdata.merek.error.merekException;
import Sinarelektronikapp.masterdata.merek.service.merekDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class merekDaoImpl implements merekDao{
    private Connection connection;
    
    private final String insertMerek = "INSERT INTO merek (namamerek) VALUES (?)";
    
    private final String updateMerek = "UPDATE merek SET idmerek=?, namamerek=? WHERE idmerek=?";
    
    private final String deleteMerek = "DELETE FROM merek WHERE idmerek= ? ";
    
    private final String getMerekById = "SELECT * FROM merek WHERE idmerek = ?";
    
    private final String selectAll = "SELECT * FROM merek ORDER BY namamerek";
    
    private final String selectLastId="SELECT count(idmerek) total from merek";

    public merekDaoImpl(Connection connection) {
        this.connection = connection;
    }


    
    @Override
    public void insertMerek(merek merek) throws merekException{
        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(insertMerek, statement.RETURN_GENERATED_KEYS);
            //statement.setString(1, merek.getIdmerek());
            statement.setString(1, merek.getNamamerek());
            statement.executeUpdate();
            connection.commit();
            ResultSet rs=statement.getGeneratedKeys();
            if(rs.next()){
                merek.setIdmerek(String.valueOf(rs.getInt(1)));
            }            
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //JOptionPane.showMessageDialog(null, "Insert merek gagal karena = "+exception);
            throw new merekException("Error karena = "+exception.getMessage());
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    
                }
            }
        }
    }

    @Override
    public void updateMerek(merek merek) throws merekException {

        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(updateMerek);
            statement.setString(1, merek.getIdmerek());
            statement.setString(2, merek.getNamamerek());
            statement.setString(3, merek.getIdmerek());
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new merekException(exception.getMessage());
            //JOptionPane.showMessageDialog(null, "Update merek gagal karena = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    
                }
            }
        }
    }

    @Override
    public void deleteMerek(String idMerek) throws merekException {
        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(deleteMerek);
            statement.setString(1, idMerek);
            statement.executeUpdate();            
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new merekException(exception.getMessage());
            //JOptionPane.showMessageDialog(null, "Terjadi error dengan pesan "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    
                }
            }            
        }
    }

    @Override
    public List<merek> getMerekById(String idMerek) throws merekException {
        PreparedStatement statement = null;
        merek merek = null;
        List<merek> list=new ArrayList<merek>();
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getMerekById);
            statement.setString(1, idMerek);            
            ResultSet rs = statement.executeQuery();            
            if(rs.next()){
                merek = new merek();
                merek.setIdmerek(rs.getString("idmerek"));
                merek.setNamamerek(rs.getString("namamerek"));
                list.add(merek);
            }else{
                throw new merekException("merek dengan id = "+idMerek+" tidak dapat ditemukan");
            }
            connection.commit();
            //return merek;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "error karene = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
        }
        return list;
    }

    @Override
    public List<merek> selectAllMerek() throws merekException {
        List<merek> list = new ArrayList<merek>();
        Statement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAll);
            while (resultSet.next()) {                
                merek merek  = new merek();
                merek.setIdmerek(resultSet.getString("idmerek"));
                merek.setNamamerek(resultSet.getString("namamerek"));
                list.add(merek);
            }
            connection.commit();
            //return list;            
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Select merek all gagal karena "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }            
        }        
        return list;
    }

    @Override
    public int getLastId() throws merekException {        
        Statement statement = null;
        int hasil=0;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectLastId);
            while (resultSet.next()) {                
                hasil=resultSet.getInt("total");
            }
            connection.commit();

        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Select merek all gagal karena "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }            
        }        
        return hasil;
    }
}

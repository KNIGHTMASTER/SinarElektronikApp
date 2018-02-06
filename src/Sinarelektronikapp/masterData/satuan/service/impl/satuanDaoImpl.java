/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterData.satuan.service.impl;

import Sinarelektronikapp.masterData.satuan.entity.satuan;
import Sinarelektronikapp.masterData.satuan.error.SatuanException;
import Sinarelektronikapp.masterData.satuan.service.satuanDao;
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
public class satuanDaoImpl implements satuanDao{
    
    private Connection connection;
    
    private final String insertSatuan = "INSERT INTO satuan (namasatuan) VALUES (?)";
    
    private final String updateSatuan = "UPDATE satuan SET namasatuan=? WHERE idsatuan=?";
    
    private final String deleteSatuan = "DELETE FROM satuan WHERE idsatuan = ?";
    
    private final String getSatuanById = "SELECT * FROM satuan WHERE idsatuan = ?";
    
    private final String selectAll = "SELECT * FROM satuan ORDER BY namasatuan";
    
    private final String getLastId = "SELECT count(idsatuan)+1 total from satuan ";

    public satuanDaoImpl(Connection connection) {
        this.connection = connection;
    }
    
    
    @Override
    public void insertSatuan(satuan satuan) throws SatuanException{
        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(insertSatuan, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, satuan.getNamasatuan());
            statement.executeUpdate();
            connection.commit();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                satuan.setIdsatuan(String.valueOf(rs.getInt(1)));
            }
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Insert satuan tidak bisa dilakukan karena = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(null, "statement tidak bisa ditutup karena = "+ex);
                }
            }
        }
    }

    @Override
    public void updateSatuan(satuan satuan) throws SatuanException {
        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(updateSatuan, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, satuan.getNamasatuan());
            statement.setString(2, satuan.getIdsatuan());            
            statement.executeUpdate();
            connection.commit();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                satuan.setIdsatuan(String.valueOf(rs.getInt(1)));
            }
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Update satuan tidak bisa dilakukan karena = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(null, "statement tidak bisa ditutup karena = "+ex);
                }
            }
        }
    }

    @Override
    public void deleteSatuan(String idSatuan) throws SatuanException {

        PreparedStatement statement = null;
        try{
            JOptionPane.showMessageDialog(null, "sudah mulai masuk satuandaoimpl");
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(deleteSatuan);
            statement.setString(1, idSatuan);
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Delete satuan tidak bisa dilakukan karena = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(null, "statement tidak bisa ditutup karena = "+ex);
                }
            }
        }
    }

    @Override
    public satuan getTipeById(String idSatuan) throws SatuanException {

        PreparedStatement statement = null;
        satuan satuan = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getSatuanById);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                satuan = new satuan();
                satuan.setIdsatuan(rs.getString("idsatuan"));
                satuan.setNamasatuan(rs.getString("namasatuan"));
            }
            else{
                throw new SatuanException("satuan dengan id "+idSatuan+" tidak ditemukan");
            }
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Update satuan tidak bisa dilakukan karena = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(null, "statement tidak bisa ditutup karena = "+ex);
                }
            }
        }
        return satuan;
    }

    @Override
    public List<satuan> selectAllSatuan() throws SatuanException{
        List<satuan> list = new ArrayList<satuan>();
        Statement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();                        
            
            ResultSet rs = statement.executeQuery(selectAll);
            
            while(rs.next()){
                satuan satuan = new satuan();
                satuan.setIdsatuan(rs.getString("idsatuan"));
                satuan.setNamasatuan(rs.getString("namasatuan"));
                list.add(satuan);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new SatuanException(exception.getMessage());            
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                }
            }            
        }
    }

    @Override
    public int getLastId() throws SatuanException {
        int hasil=0;
        Statement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();                        
            
            ResultSet rs = statement.executeQuery(getLastId);
            
            while(rs.next()){
                hasil=rs.getInt("total");
            }
            connection.commit();
            return hasil;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new SatuanException(exception.getMessage());            
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                }
            }            
        }
    }

}

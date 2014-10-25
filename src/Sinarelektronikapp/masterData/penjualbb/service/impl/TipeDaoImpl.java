/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterData.penjualbb.service.impl;

import Sinarelektronikapp.masterData.penjualbb.entity.tipe;
import Sinarelektronikapp.masterData.penjualbb.error.TipeException;
import Sinarelektronikapp.masterData.penjualbb.service.tipeDao;
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
public class TipeDaoImpl implements tipeDao{

    private Connection connection;

    public TipeDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public TipeDaoImpl() {
    }
    
    
    private final String insertTipe = "INSERT INTO penjualbb (nama) VALUES (?);";

    private final String updateTipe = "UPDATE penjualbb SET nama=? WHERE id=?;";

    private final String deleteTipe = "DELETE FROM penjualbb WHERE id=?;";    
    
    private final String getById = "SELECT * FROM penjualbb WHERE id=?;";    
    
    private final String selectAll = "SELECT * FROM penjualbb ORDER BY nama;";
    
    private final String getLastData = "SELECT count(id) total FROM penjualbb;";
        
    
    @Override
    public void insertTipe(tipe tipe) throws TipeException {
        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(insertTipe, statement.RETURN_GENERATED_KEYS);            
            statement.setString(1, tipe.getNamaTipe());
            statement.executeUpdate();    
            connection.commit();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                tipe.setIdtipe(String.valueOf(rs.getInt(1)));
            }
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Insert tipe gagal karena = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "statement tidak bisa ditutup karena "+ex);
                }
            }
        }
    }

    @Override
    public void updateTipe(tipe tipe) throws TipeException {
        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(updateTipe);
            statement.setString(1, tipe.getNamaTipe());
            statement.setString(2, tipe.getIdtipe());
            statement.executeUpdate();    
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Update tipe gagal karena = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "statement tidak bisa ditutup karena "+ex);
                }
            }
        }
    }

    @Override
    public void deleteTipe(String idTipe) throws TipeException {
        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(deleteTipe);
            statement.setString(1, idTipe);
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Delete Tipe gagal karena = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Statement tidak bisa di tutup karena = "+ex);
                }
            }
            
        }
    }

    @Override
    public tipe getTipeByid(String idTipe) throws TipeException {
        PreparedStatement statement = null;
        tipe tipe = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getById);
            statement.setString(1, idTipe);
            
            ResultSet rs = statement.executeQuery();            
            if(rs.next()){
                tipe = new tipe();
                tipe.setIdtipe(rs.getString("id"));
                tipe.setNamaTipe(rs.getString("nama"));
            }
            else{
                throw new TipeException("barang dengan id "+idTipe +" tidak ditemukan");
            }
            connection.commit();
            return tipe;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new TipeException(exception.getMessage());
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
    }

    @Override
    public List<tipe> selectAllTipe() throws TipeException {
        List<tipe> list = new ArrayList<tipe>();
        Statement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();                        
            
            ResultSet rs = statement.executeQuery(selectAll);
            
            while(rs.next()){
                tipe tipe = new tipe();
                tipe.setIdtipe(rs.getString("id"));
                tipe.setNamaTipe(rs.getString("nama"));
                list.add(tipe);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new TipeException(exception.getMessage());            
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
    public Integer getLastIdata() throws TipeException {
        Statement statement = null;
        int hasil=0;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();                        
            
            ResultSet rs = statement.executeQuery(getLastData);
            
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
            throw new TipeException(exception.getMessage());            
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

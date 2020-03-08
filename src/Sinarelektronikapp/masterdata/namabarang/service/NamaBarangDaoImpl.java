/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.namabarang.service;

import Sinarelektronikapp.masterdata.namabarang.Entity.NamaBarang;
import Sinarelektronikapp.masterdata.namabarang.error.NamaBarangException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class NamaBarangDaoImpl implements NamaBarangDao {

    Connection connection;

    public NamaBarangDaoImpl(Connection connection) {
        this.connection = connection;
    }
    
    private final String insertNamaBarang = "INSERT INTO namabarang (namabarang) VALUES (?)";
    
    private final String updateNamaBarang = "UPDATE namabarang SET namabarang=? WHERE id=?";
    
    private final String deleteNamaBarang = "DELETE FROM namabarang WHERE id= ? ";
    
    private final String getNamaBarangById = "SELECT * FROM namabarang WHERE id= ?";
    
    private final String selectAll = "SELECT * FROM namabarang ORDER BY namabarang";
    
    private final String selectLastId="SELECT count(id) total from namabarang";
    
    @Override
    public void insertNamaBarang(NamaBarang namaBarang) throws NamaBarangException {
        
        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(insertNamaBarang, statement.RETURN_GENERATED_KEYS);
            //statement.setString(1, merek.getIdmerek());
            statement.setString(1, namaBarang.getNamabarang());
            statement.executeUpdate();
            connection.commit();
            ResultSet rs=statement.getGeneratedKeys();
            if(rs.next()){
                namaBarang.setId(rs.getInt(1));
            }            
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //JOptionPane.showMessageDialog(null, "Insert merek gagal karena = "+exception);
            throw new NamaBarangException("Error karena = "+exception.getMessage());
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
    public void updateNamaBarang(NamaBarang namaBarang) throws NamaBarangException {
        

        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(updateNamaBarang);
            statement.setString(1, namaBarang.getNamabarang());
            statement.setInt(2, namaBarang.getId());
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new NamaBarangException(exception.getMessage());
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
    public void deleteNamaBarang(int id) throws NamaBarangException {
        
        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(deleteNamaBarang);
            statement.setInt(1, id);
            statement.executeUpdate();            
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new NamaBarangException(exception.getMessage());
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
    public List<NamaBarang> getNamaBarangById(int id) throws NamaBarangException {
        
        PreparedStatement statement = null;
        NamaBarang namaBarang = null;
        List<NamaBarang> list=new ArrayList<NamaBarang>();
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getNamaBarangById);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();            
            if(rs.next()){
                namaBarang = new NamaBarang();
                namaBarang.setId(rs.getInt("id"));
                namaBarang.setNamabarang(rs.getString("namabarang"));
                list.add(namaBarang);
            }else{
                throw new NamaBarangException("namabarang dengan id = "+id+" tidak dapat ditemukan");
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
    public List<NamaBarang> selectAllNamaBarang() throws NamaBarangException {
        
        List<NamaBarang> list = new ArrayList<NamaBarang>();
        Statement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAll);
            while (resultSet.next()) {                
                NamaBarang namaBarang  = new NamaBarang();
                namaBarang.setId(resultSet.getInt("id"));
                namaBarang.setNamabarang(resultSet.getString("namabarang"));
                list.add(namaBarang);
            }
            connection.commit();
            //return list;            
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Select nama barangkecil all gagal karena "+exception);
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
    public int getLastId() throws NamaBarangException {        
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
            JOptionPane.showMessageDialog(null, "Select nama barangkecil last id all gagal karena "+exception);
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterdata.itemforbonus.barangkecil.service.impl;

import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.entity.BarangBonusKaryawanBk;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.error.BarangBonusKaryawanBKException;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.service.BarangBonusKaryawanDao;
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
public class BarangBonusKaryawanBKDaoImpl implements BarangBonusKaryawanDao{

    private Connection connection;

    public BarangBonusKaryawanBKDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public BarangBonusKaryawanBKDaoImpl() {
    }

    
    
    private final String insertTipe = "INSERT INTO barangbonuskaryawanbk (kodebarang, nama, tipe, merek) VALUES (?, ?, ?, ?);";

    private final String updateTipe = "UPDATE tipe SET kodebarang=?, nama=?, tipe=?, merek=? WHERE kodebarang=?;";

    private final String deleteTipe = "DELETE FROM barangbonuskaryawanbk WHERE kodebarang=?;";    
       
    private final String selectAll = "SELECT * FROM barangbonuskaryawanbk  ORDER BY kodebarang;";    

    @Override
    public void insertTipe(BarangBonusKaryawanBk bbbbk) throws BarangBonusKaryawanBKException {
        

        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(insertTipe);            
            statement.setString(1, bbbbk.getKodeBarang());
            statement.setString(2, bbbbk.getNama());
            statement.setString(3, bbbbk.getTipe());
            statement.setString(4, bbbbk.getMerek());
            statement.executeUpdate();    
            connection.commit();           
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Insert barangkecil bonus karyawan gagal karena = "+exception);
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
    public void updateTipe(BarangBonusKaryawanBk tipe) throws BarangBonusKaryawanBKException {


        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(updateTipe);
            statement.setString(1, tipe.getKodeBarang());
            statement.setString(2, tipe.getNama());
            statement.setString(3, tipe.getTipe());
            statement.setString(4, tipe.getMerek());
            statement.executeUpdate();    
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Update barangkecil bonus karyawan  gagal karena = "+exception);
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
    public void deleteTipe(String idBarangBonusKaryawanBK) throws BarangBonusKaryawanBKException {

        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(deleteTipe);
            statement.setString(1, idBarangBonusKaryawanBK);
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
    public List<BarangBonusKaryawanBk> selectAllTipe() throws BarangBonusKaryawanBKException {


        List<BarangBonusKaryawanBk> list = new ArrayList<BarangBonusKaryawanBk>();
        Statement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();                        
            
            ResultSet rs = statement.executeQuery(selectAll);
            
            while(rs.next()){
                BarangBonusKaryawanBk tipe = new BarangBonusKaryawanBk();
                tipe.setKodeBarang(rs.getString("kodebarang"));
                tipe.setNama(rs.getString("nama"));
                tipe.setTipe(rs.getString("tipe"));
                tipe.setMerek(rs.getString("merek"));
                list.add(tipe);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new BarangBonusKaryawanBKException(exception.getMessage());            
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

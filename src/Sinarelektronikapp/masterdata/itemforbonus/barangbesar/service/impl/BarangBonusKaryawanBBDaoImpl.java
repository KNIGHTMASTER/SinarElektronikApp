/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterdata.itemforbonus.barangbesar.service.impl;

import Sinarelektronikapp.masterdata.itemforbonus.barangbesar.entity.BarangBonusKaryawanBB;
import Sinarelektronikapp.masterdata.itemforbonus.barangbesar.error.BarangBonusKaryawanBBException;
import Sinarelektronikapp.masterdata.itemforbonus.barangbesar.service.BarangBonusKaryawanBBDao;
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
public class BarangBonusKaryawanBBDaoImpl implements BarangBonusKaryawanBBDao{

    private Connection connection;

    public BarangBonusKaryawanBBDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public BarangBonusKaryawanBBDaoImpl() {
    }

    
    
    private final String insertTipe = "INSERT INTO barangbonuskaryawanbb (kodebarang, nama, tipe, merek) VALUES (?, ?, ?, ?);";

    private final String updateTipe = "UPDATE tipe SET kodebarang=?, nama=?, tipe=?, merek=? WHERE kodebarang=?;";

    private final String deleteTipe = "DELETE FROM barangbonuskaryawanbb WHERE kodebarang=?;";    
       
    private final String selectAll = "SELECT * FROM barangbonuskaryawanbb  ORDER BY kodebarang;";    

    @Override
    public void insertTipe(BarangBonusKaryawanBB bbbbk) throws BarangBonusKaryawanBBException {
        

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
    public void updateTipe(BarangBonusKaryawanBB tipe) throws BarangBonusKaryawanBBException {


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
    public void deleteTipe(String idBarangBonusKaryawanBK) throws BarangBonusKaryawanBBException {

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
    public List<BarangBonusKaryawanBB> selectAllTipe() throws BarangBonusKaryawanBBException {


        List<BarangBonusKaryawanBB> list = new ArrayList<BarangBonusKaryawanBB>();
        Statement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();                        
            
            ResultSet rs = statement.executeQuery(selectAll);
            
            while(rs.next()){
                BarangBonusKaryawanBB tipe = new BarangBonusKaryawanBB();
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
            throw new BarangBonusKaryawanBBException(exception.getMessage());            
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.karyawan.service;

import com.wissensalt.sinarelektronik.masterdata.karyawan.Error.KaryawanException;
import com.wissensalt.sinarelektronik.masterdata.karyawan.entity.Karyawan;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class KaryawanDaoImpl implements KaryawanDao {

    private Connection connection;

    public KaryawanDaoImpl(Connection connection) {
        this.connection = connection;
    }
    
    final String INSERTKARYAWAN = "INSERT INTO karyawan (nama, tanggal_lahir, tempat_lahir, alamat, telepon, agama, status, gaji, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    final String UPDATEKARYAWAN = "UPDATE karyawan set nama = ?, tanggal_lahir = ?, tempat_lahir = ?, alamt = ?, telepon = ?, agama = ?, status = ?, gaji = ?, foto = ? WHERE id = ?";
    
    final String DELETEKARYAWAN = "DELETE FROM karyawan WHERE id = ?";
    
    final String SELECTALLKARYAWAN = "SELECT * FROM karyawan";
    
    final String GETLASTID = "SELECT count(id) as total FROM karyawan";
        
    @Override
    public void insertKaryawan(Karyawan karyawan) throws KaryawanException {
        PreparedStatement ps = null;
        try{            
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(INSERTKARYAWAN, ps.RETURN_GENERATED_KEYS);
            ps.setString(1, karyawan.getNama());
            ps.setString(2, karyawan.getTanggal_lahir().toString());
            ps.setString(3, karyawan.getTempat_lahir());
            ps.setString(4, karyawan.getAlamat());
            ps.setString(5, karyawan.getTelepon());
            ps.setString(6, karyawan.getAgama());
            ps.setString(7, karyawan.getStatus());
            ps.setInt(8, karyawan.getGaji());
            ps.setBlob(9, new FileInputStream(karyawan.getFoto()));
            ps.executeUpdate();            
            connection.commit();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                karyawan.setId(rs.getInt(1));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KaryawanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam insert karyawan karena = "+exception);
            try {
                connection.rollback();                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error dalam insert karyawan karena = "+ex);
                Logger.getLogger(KaryawanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(KaryawanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void updateKaryawan(Karyawan karyawan) throws KaryawanException {

        PreparedStatement ps = null;
        try{            
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(UPDATEKARYAWAN);
            ps.setString(1, karyawan.getNama());
            ps.setString(2, karyawan.getTanggal_lahir().toString());
            ps.setString(3, karyawan.getTempat_lahir());
            ps.setString(4, karyawan.getAlamat());
            ps.setString(5, karyawan.getTelepon());
            ps.setString(6, karyawan.getAgama());
            ps.setString(7, karyawan.getStatus());
            ps.setInt(8, karyawan.getGaji());
            ps.setBlob(9, new FileInputStream(karyawan.getFoto()));
            ps.setInt(1, karyawan.getId());
            ps.executeUpdate();            
            connection.commit();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KaryawanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException exception){
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null, "Error dalam update karyawan karena = "+exception);
            } catch (SQLException ex) {
                Logger.getLogger(KaryawanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(KaryawanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }

    @Override
    public void deleteKaryawan(int id) throws KaryawanException {

        PreparedStatement ps = null;
        try{            
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(DELETEKARYAWAN);
            ps.setInt(1, id);
            ps.executeUpdate();            
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null, "Error dalam delete karyawan karena = "+exception);
            } catch (SQLException ex) {
                Logger.getLogger(KaryawanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(KaryawanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Karyawan> selectAllKaryawan() throws KaryawanException {

        List<Karyawan> list = new ArrayList<>();
        Statement statement = null;
        try{            
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECTALLKARYAWAN);
            while (rs.next()) {                
                Karyawan karyawan = new Karyawan();
                karyawan.setId(rs.getInt("id"));
                karyawan.setNama(rs.getString("nama"));
                karyawan.setTanggal_lahir(rs.getString("tanggal_lahir"));
                karyawan.setTempat_lahir(rs.getString("tempat_lahir"));
                karyawan.setAlamat(rs.getString("alamat"));
                karyawan.setTelepon(rs.getString("telepon"));
                karyawan.setAgama(rs.getString("agama"));
                karyawan.setStatus(rs.getString("status"));
                karyawan.setGaji(rs.getInt("gaji"));
                karyawan.setGambarHasil(rs.getBlob("foto"));
                list.add(karyawan);
            }
            
        }catch(SQLException exception){
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null, "Error dalam select karyawan karena = "+exception);
            } catch (SQLException ex) {
                Logger.getLogger(KaryawanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(KaryawanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KaryawanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public int getLastIdKaryawan() throws KaryawanException {
        Statement statement = null;
        int hasil=0;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GETLASTID);
            while (resultSet.next()) {                
                hasil=resultSet.getInt("total");
            }
            connection.commit();

        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Select karyawan last id all gagal karena "+exception);
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

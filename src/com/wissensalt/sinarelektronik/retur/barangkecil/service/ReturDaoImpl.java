/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.retur.barangkecil.service;

import com.wissensalt.sinarelektronik.retur.barangkecil.entity.Retur;
import com.wissensalt.sinarelektronik.retur.barangkecil.error.ReturException;
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


public class ReturDaoImpl implements ReturDao {

    final String INSERTRETUR = "INSERT INTO detailretur (user, tanggal, jam, kode, nama, jumlah, harga, penukaran, subharga, supplier) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    final String UPDATERETUR = "UPDATE detailretur set user=?, tanggal=?, jam=?, kode=?, nama=?, jumlah=?, harga = ?, penukaran = ?, subharga=? , supplier = ? WHERE id=?";
    
    final String DELETERETUR = "DELETE FROM detailretur WHERE id=?";
    
    final String SELECTALLRETUR = "SELECT * FROM detailretur";
    
    final String TRUNCATERETUR = "TRUNCATE table detailretur";
    
    public Connection c;

    public ReturDaoImpl(Connection c) {
        this.c = c;
    }
        
    @Override
    public void insertRetur(Retur retur) throws ReturException {
        PreparedStatement ps = null;
        try{
            c.setAutoCommit(false);
            ps = c.prepareStatement(INSERTRETUR, ps.RETURN_GENERATED_KEYS);
            ps.setString(1, retur.getUser());
            ps.setString(2, retur.getTanggal());
            ps.setString(3, retur.getJam());
            ps.setString(4, retur.getKode());
            ps.setString(5, retur.getNama());
            ps.setInt(6, retur.getJumlah());
            ps.setInt(7, retur.getHarga());
            ps.setString(8, retur.getPenukaran());
            ps.setInt(9, retur.getSubharga());            
            ps.setString(10, retur.getSupplier());            
            ps.executeUpdate();
            c.commit();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                retur.setId(rs.getInt(1));
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam insert retur karena "+exception);
            try {
                c.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(ReturDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "error dalam insert retur karena "+exception);
            }
        }finally{
            try {
                c.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ReturDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void updateRetur(Retur retur) throws ReturException {
        PreparedStatement ps = null;
            try{
                c.setAutoCommit(false);
                ps = c.prepareStatement(UPDATERETUR);
                ps.setString(1, retur.getUser());
                ps.setString(2, retur.getTanggal());
                ps.setString(3, retur.getJam());
                ps.setString(4, retur.getKode());
                ps.setString(5, retur.getNama());
                ps.setInt(6, retur.getJumlah());
                ps.setInt(7, retur.getHarga());
                ps.setString(8, retur.getPenukaran());
                ps.setInt(9, retur.getSubharga());                
                ps.setInt(10, retur.getId());
                ps.setString(11, retur.getSupplier());
                ps.executeUpdate();
                c.commit();
            }catch(SQLException exception){
                try {
                    c.rollback();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "error dalam update retur karena "+exception);
                }
            }finally{
                try {
                    c.setAutoCommit(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ReturDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ReturDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
            }
        }

    @Override
    public void deleteRetur(int id) throws ReturException {
    PreparedStatement ps = null;
        try{
            c.setAutoCommit(false);
            ps = c.prepareStatement(DELETERETUR);
            ps.setInt(1, id);
            ps.executeUpdate();
            c.commit();
        }catch(SQLException exception){
            try {
                c.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(ReturDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "error dalam deleteretur karena "+exception);
            }
        }finally{
            try {
                c.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ReturDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Retur> showAllRetur() throws ReturException {
        List<Retur> list = new ArrayList<>();
        Statement s = null;
        try{
            c.setAutoCommit(false);
            s=c.createStatement();
            ResultSet rs = s.executeQuery(SELECTALLRETUR);
            while (rs.next()) {
                Retur r = new Retur();
                r.setId(rs.getInt("id"));
                r.setUser(rs.getString("user"));
                r.setTanggal(rs.getString("tanggal"));
                r.setJam(rs.getString("jam"));
                r.setKode(rs.getString("kode"));
                r.setNama(rs.getString("nama"));
                r.setJumlah(rs.getInt("jumlah"));
                r.setHarga(rs.getInt("harga"));
                r.setSubharga(rs.getInt("subharga"));
                
                list.add(r);
            }
            c.commit();
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam show all retur karena = "+exception);
            try {
                c.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(ReturDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                c.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ReturDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;        
    }

    @Override
    public void truncateRetur() throws ReturException {
        PreparedStatement ps = null;
        try{
            c.setAutoCommit(false);
            ps = c.prepareStatement(TRUNCATERETUR);
            ps.executeUpdate();
            c.commit();
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "error dalam truncate data retur karena = "+exception);
            try {
                c.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(ReturDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                c.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ReturDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}

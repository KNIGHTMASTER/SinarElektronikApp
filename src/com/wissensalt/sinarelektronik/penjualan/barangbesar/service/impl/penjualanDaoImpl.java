/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.penjualan.barangbesar.service.impl;

import com.wissensalt.sinarelektronik.penjualan.barangbesar.entity.ProsesKasir;
import com.wissensalt.sinarelektronik.penjualan.barangbesar.error.penjualanException;
import com.wissensalt.sinarelektronik.penjualan.barangbesar.service.penjualanDao;
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

/**
 *
 * @author Fauzi
 */
public class penjualanDaoImpl implements penjualanDao{
    
    private Connection connection;

    public penjualanDaoImpl(Connection connection) {
        this.connection = connection;
    }
    
    final String insertProsesKasir="INSERT INTO proseskasir2 (kode, nama, jml, harga, potongan, tambahan, total, profit, penjual, bonuslangsung, bonuskumulatif, asalbarang) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    final String deleteProsesKasir="DELETE FROM proseskasir2 WHERE no=?";
    
    final String selectAllProsesKasir="SELECT * FROM proseskasir2";

    @Override
    public void insertPenjualan(ProsesKasir prosesKasir) throws penjualanException {
        PreparedStatement ps=null;        
        try{
            connection.setAutoCommit(false);
            ps=connection.prepareStatement(insertProsesKasir, ps.RETURN_GENERATED_KEYS);
            ps.setString(1, prosesKasir.getKode());
            ps.setString(2, prosesKasir.getNama());
            ps.setInt(3, prosesKasir.getJml());
            ps.setInt(4, prosesKasir.getHarga());
            ps.setInt(5, prosesKasir.getPotongan());
            ps.setInt(6, prosesKasir.getTambahan());
            ps.setInt(7, prosesKasir.getTotal());
            ps.setInt(8, prosesKasir.getProfit());
            ps.setString(9, prosesKasir.getPenjual());
            ps.setInt(10, prosesKasir.getBonuslangsung());
            ps.setInt(11, prosesKasir.getBonuskumulatif());
            ps.setString(12, prosesKasir.getAsalBarang());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                prosesKasir.setNo(rs.getInt(1));
            }
            
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error dalam insert data penjualan karena = "+ex, "perhatian", JOptionPane.ERROR_MESSAGE);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    
    @Override
    public void deletePenjualan(int no) throws penjualanException {
        PreparedStatement ps=null;
        try{
            connection.setAutoCommit(false);
            ps=connection.prepareStatement(deleteProsesKasir);
            ps.setString(1, String.valueOf(no));
            ps.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    @Override
    public List<ProsesKasir> selectAllPenjualan() throws penjualanException {
        List<ProsesKasir> list=new ArrayList<ProsesKasir>();
        Statement ps=null;
        try{
            connection.setAutoCommit(false);
            ProsesKasir prosesKasir=new ProsesKasir();            
            ps=connection.createStatement();
            ResultSet rs=ps.executeQuery(selectAllProsesKasir);
            while (rs.next()) {
                prosesKasir.setNo(Integer.valueOf(rs.getString("no")));
                prosesKasir.setKode(rs.getString("kode"));
                prosesKasir.setNama(rs.getString("nama"));
                prosesKasir.setJml(Integer.valueOf(rs.getString("jml")));
                prosesKasir.setHarga(Integer.valueOf(rs.getString("harga")));
                prosesKasir.setPotongan(Integer.valueOf(rs.getString("potongan")));
                prosesKasir.setTambahan(Integer.valueOf(rs.getString("tambahan")));
                prosesKasir.setTotal(Integer.valueOf(rs.getString("total")));
                prosesKasir.setProfit(Integer.valueOf(rs.getString("profit")));
                prosesKasir.setPenjual(rs.getString("penjual"));
                prosesKasir.setBonuslangsung(rs.getInt("bonuslangsung"));
                prosesKasir.setBonuskumulatif(rs.getInt("bonuskumulatif"));
                list.add(prosesKasir);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public void truncatePenjualan() throws penjualanException {
        Statement s = null;
        try{
            connection.setAutoCommit(false);
            s=connection.createStatement();
            s.executeQuery("TRUNCATE TABLE proseskasir2");            
            connection.commit();
        }catch(SQLException e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "tidak bisa men- truncate tabel proses kasir karena = "+e);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }
    
    
}

package com.wissensalt.sinarelektronik.transaction.barangkecil.service;

import com.wissensalt.sinarelektronik.transaction.barangkecil.entity.TransaksiPenjualan;
import com.wissensalt.sinarelektronik.transaction.barangkecil.error.TransaksiPenjualanException;
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


public class TransaksiPenjualanDaoImpl implements TransaksiPenjualanDao {
   
    final String DELETE_TRANSAKSI_PENJUALAN_BK="DELETE FROM transaksipenjualan WHERE idtransaksi= ?";

    final String SELECT_ALL_TRANSAKSI_PENJUALAN_BK = "SELECT * FROM transaksipenjualan order by idtransaksi";
    
    final String DELETE_DETAIL_TRANSAKSI_PENJUALAN_BK = "DELETE FROM detailtransaksipenjualan WHERE iddetailtransaksi=?";   
    
    public Connection c;
    
    public TransaksiPenjualanDaoImpl(Connection connection) {
        this.c = connection;
    }

    @Override
    public void deleteTransaksiPenjualan(int id) throws TransaksiPenjualanException {
        PreparedStatement ps = null;
        try{
            c.setAutoCommit(false);
            ps = c.prepareStatement(DELETE_TRANSAKSI_PENJUALAN_BK);
            ps.setInt(1, id);
            ps.executeUpdate();
            deleteDetailTransaksiPenjualan(id);
            c.commit();
        }catch(SQLException ex){
            try {
                c.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(TransaksiPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                c.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<TransaksiPenjualan> showAllTransaksiPenjualan() throws TransaksiPenjualanException {
        List<TransaksiPenjualan> list = new ArrayList<TransaksiPenjualan>();
        Statement s = null;
        try{
            c.setAutoCommit(false);
            s = c.createStatement();
            ResultSet rs = s.executeQuery(SELECT_ALL_TRANSAKSI_PENJUALAN_BK);
            while (rs.next()) {                
                TransaksiPenjualan tp = new TransaksiPenjualan();
                tp.setIdTransaksi(rs.getInt("idtransaksi"));
                tp.setUser(rs.getString("user"));
                tp.setTanggal(rs.getDate("Tanggal"));
                tp.setJam(rs.getString("jam"));
                tp.setTotal(rs.getInt("total"));
                tp.setPembayaran(rs.getInt("pembayaran"));
                tp.setSisa(rs.getInt("sisa"));
                tp.setProfit(rs.getInt("profit"));                
                list.add(tp);
            }
            c.commit();
        }catch(SQLException exception){
            try {
                c.rollback();
                JOptionPane.showMessageDialog(null, "Error dalam show all transaksi penjualan karena = "+exception, "perhatian", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                c.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    private void deleteDetailTransaksiPenjualan(int id) {
        PreparedStatement ps = null;
        try{
            ps = c.prepareStatement(DELETE_DETAIL_TRANSAKSI_PENJUALAN_BK);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "error dalam menghapus detail transaksi penjualan karena = "+ex, "Perhatian", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }    

    @Override
    public List<TransaksiPenjualan> getTransaksiPenjualan(String kataKunci, String berdasarkan) throws TransaksiPenjualanException {
        String dasar = berdasarkan;
        String query = "";
        switch(dasar){
            case "id":query="select * from transaksipenjualan where idtransaksi like ? order by idtransaksi";break;
            case "user":query="select * from transaksipenjualan where user like ? order by idtransaksi";break;
            case "tanggal":query="select * from transaksipenjualan where tanggal like ? order by idtransaksi";break;
            case "jam":query="select * from transaksipenjualan where jam like ? order by idtransaksi";break;        
            case "total":query="select * from transaksipenjualan where total like ? order by idtransaksi";break;
            default:query=null;
        }
        List<TransaksiPenjualan> list = new ArrayList<TransaksiPenjualan>();
        PreparedStatement statement = null;
        try{
            c.setAutoCommit(false);
            statement = c.prepareStatement(query);
            statement.setString(1, "%"+kataKunci+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                TransaksiPenjualan tp = new TransaksiPenjualan();
                tp.setIdTransaksi(rs.getInt("idTransaksi"));
                tp.setJam(rs.getString("jam"));                
                tp.setPembayaran(rs.getInt("pembayaran"));
                tp.setProfit(rs.getInt("profit"));
                tp.setSisa(rs.getInt("sisa"));
                tp.setTanggal(rs.getDate("tanggal"));
                tp.setTotal(rs.getInt("total"));
                tp.setUser(rs.getString("user"));
                list.add(tp);
            }
            c.commit();
        }catch(SQLException exception){
            try {
                c.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }           
            JOptionPane.showMessageDialog(null, "Error dalam get transaksi penjualan karena = "+exception, "perhatian", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                c.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<TransaksiPenjualan> sortTransaksiPenjualan(String berdasarkan) throws TransaksiPenjualanException {        List<TransaksiPenjualan> list = new ArrayList<TransaksiPenjualan>();
        String dasar = berdasarkan;
        String query = "";
        switch(dasar){
            case "id":query="select * from transaksipenjualan order by idtransaksi";break;
            case "user":query="select * from transaksipenjualan order by user";break;
            case "tanggal":query="select * from transaksipenjualan order by tanggal";break;                
            case "jam":query="select * from transaksipenjualan order by jam";break;                
            case "total":query="select * from transaksipenjualan order by total";break;                
            default:query=null;
        }    
        Statement s = null;
        try{
            c.setAutoCommit(false);
            s = c.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {                
                TransaksiPenjualan tp = new TransaksiPenjualan();
                tp.setIdTransaksi(rs.getInt("idtransaksi"));
                tp.setUser(rs.getString("user"));
                tp.setTanggal(rs.getDate("Tanggal"));
                tp.setJam(rs.getString("jam"));
                tp.setTotal(rs.getInt("total"));
                tp.setPembayaran(rs.getInt("pembayaran"));
                tp.setSisa(rs.getInt("sisa"));
                tp.setProfit(rs.getInt("profit"));                
                list.add(tp);
            }
            c.commit();
        }catch(SQLException exception){
            try {
                c.rollback();
                JOptionPane.showMessageDialog(null, "Error dalam show all transaksi penjualan karena = "+exception, "perhatian", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Error dalam get transaksi penjualan karena = "+exception, "perhatian", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                c.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Service;

import Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Entity.TransaksiPenjualanBB;
import Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Error.TransaksiPenjualanBBException;
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


public class TransaksiPenjualanBBDaoImpl implements TransaksiPenjualanBBDao {

    public Connection connection;

    final String DELETE_TRANSAKSI = "DELETE FROM transaksipenjualanbarangbesar where idtransaksi= ?";

    final String SELECT_ALL_TRANSAKSI = "SELECT * FROM transaksipenjualanbarangbesar";

    final String DELETE_DETAIL_TRANSAKSI_PENJUALAN_BB = "DELETE FROM detailtransaksipenjualanbarangbesar WHERE iddetailtransaksi=?";   
    
    public TransaksiPenjualanBBDaoImpl(Connection connection) {
        this.connection = connection;
    }
    
    private void deleteDetailTransaksiPenjualan(int id) {
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(DELETE_DETAIL_TRANSAKSI_PENJUALAN_BB);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error delete detail karena = "+ex, "perhatian", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiPenjualanBBDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }    
    
    @Override
    public void deleteTransaksi(int id) throws TransaksiPenjualanBBException {
        PreparedStatement ps = null;
        try{
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(DELETE_TRANSAKSI);
            ps.setInt(1, id);
            ps.executeUpdate();
            deleteDetailTransaksiPenjualan(id);
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenjualanBBDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Error karena = "+exception, "perhatian", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenjualanBBDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiPenjualanBBDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<TransaksiPenjualanBB> selectAllTransaksi() throws TransaksiPenjualanBBException {
        List<TransaksiPenjualanBB> list = new ArrayList<>();
        Statement s = null;
        try{
            connection.setAutoCommit(false);
            s = connection.createStatement();
            ResultSet rs = s.executeQuery(SELECT_ALL_TRANSAKSI);
            while (rs.next()) {                
                TransaksiPenjualanBB tpbb = new TransaksiPenjualanBB();
                tpbb.setIdtransaksi(rs.getInt("idtransaksi"));
                tpbb.setUser(rs.getString("user"));
                tpbb.setTanggal(rs.getDate("tanggal"));
                tpbb.setJam(rs.getString("jam"));
                tpbb.setTotal(rs.getInt("total"));
                tpbb.setPembayaran(rs.getInt("pembayaran"));
                tpbb.setSisa(rs.getInt("sisa"));
                tpbb.setProfit(rs.getInt("profit"));
                tpbb.setPenjual(rs.getString("penjual"));
                tpbb.setBonusLangsung(rs.getInt("bonuslangsung"));
                tpbb.setBonusKumuatif(rs.getInt("bonuskumulatif"));
                list.add(tpbb);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenjualanBBDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenjualanBBDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiPenjualanBBDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<TransaksiPenjualanBB> searchTransaksi(String kataKunci, String berdasarkan) throws TransaksiPenjualanBBException {
        String dasar = berdasarkan;
        String query = "";
        switch(dasar){
            case "id":query="select * from transaksipenjualanbarangbesar where idtransaksi like '%"+kataKunci+"%' order by idtransaksi";break;
            case "user":query="select * from transaksipenjualanbarangbesar where user like '%"+kataKunci+"%' order by idtransaksi";break;
            case "tanggal":query="select * from transaksipenjualanbarangbesar where tanggal like '%"+kataKunci+"%' order by idtransaksi";break;                
            case "jam":query="select * from transaksipenjualanbarangbesar where jam like '%"+kataKunci+"%' order by idtransaksi";break;                
            case "total":query="select * from transaksipenjualanbarangbesar where total like '%"+kataKunci+"%' order by idtransaksi";break;                
            case "penjual":query="select * from transaksipenjualanbarangbesar where penjual like '%"+kataKunci+"%' order by idtransaksi";break;
            default:query=null;
        }
        List<TransaksiPenjualanBB> list = new ArrayList<TransaksiPenjualanBB>();
        Statement s = null;
        try{
            connection.setAutoCommit(false);
            s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {                
                TransaksiPenjualanBB tpbb = new TransaksiPenjualanBB();
                tpbb.setIdtransaksi(rs.getInt("idtransaksi"));
                tpbb.setUser(rs.getString("user"));
                tpbb.setTanggal(rs.getDate("tanggal"));
                tpbb.setJam(rs.getString("jam"));
                tpbb.setTotal(rs.getInt("total"));
                tpbb.setPembayaran(rs.getInt("pembayaran"));
                tpbb.setSisa(rs.getInt("sisa"));
                tpbb.setProfit(rs.getInt("profit"));
                tpbb.setPenjual(rs.getString("penjual"));
                tpbb.setBonusLangsung(rs.getInt("bonuslangsung"));
                tpbb.setBonusKumuatif(rs.getInt("bonuskumulatif"));
                list.add(tpbb);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenjualanBBDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "error karena = "+exception, "peringatan", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenjualanBBDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiPenjualanBBDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<TransaksiPenjualanBB> sortTransaksi(String berdasarkan) throws TransaksiPenjualanBBException {
        String query = "";
        switch(berdasarkan){
            case "id":query="select * from transaksipenjualanbarangbesar order by idtransaksi";break;
            case "user":query="select * from transaksipenjualanbarangbesar order by user";break;
            case "tanggal":query="select * from transaksipenjualanbarangbesar order by tanggal";break;                
            case "jam":query="select * from transaksipenjualanbarangbesar order by jam";break;                
            case "total":query="select * from transaksipenjualanbarangbesar order by total";break;                
            case "penjual":query="select * from transaksipenjualanbarangbesar order by penjual";break;
            default:query=null;
        }
        List<TransaksiPenjualanBB> list = new ArrayList<TransaksiPenjualanBB>();
        Statement s = null;
        try{
            connection.setAutoCommit(false);
            s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {                
                TransaksiPenjualanBB tpbb = new TransaksiPenjualanBB();
                tpbb.setIdtransaksi(rs.getInt("idtransaksi"));
                tpbb.setUser(rs.getString("user"));
                tpbb.setTanggal(rs.getDate("tanggal"));
                tpbb.setJam(rs.getString("jam"));
                tpbb.setTotal(rs.getInt("total"));
                tpbb.setPembayaran(rs.getInt("pembayaran"));
                tpbb.setSisa(rs.getInt("sisa"));
                tpbb.setProfit(rs.getInt("profit"));
                tpbb.setPenjual(rs.getString("penjual"));
                tpbb.setBonusLangsung(rs.getInt("bonuslangsung"));
                tpbb.setBonusKumuatif(rs.getInt("bonuskumulatif"));
                list.add(tpbb);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenjualanBBDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Error karena = "+exception, "peringatan", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TransaksiPenjualanBBDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiPenjualanBBDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
}

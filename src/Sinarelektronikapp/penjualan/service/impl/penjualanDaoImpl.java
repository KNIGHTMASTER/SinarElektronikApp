/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.penjualan.service.impl;

import Sinarelektronikapp.penjualan.entity.ProsesKasir;
import Sinarelektronikapp.penjualan.error.penjualanException;
import Sinarelektronikapp.penjualan.model.TabelModelPenjualan;
import Sinarelektronikapp.penjualan.service.penjualanDao;
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
import javax.swing.JTable;

/**
 *
 * @author Fauzi
 */
public class penjualanDaoImpl implements penjualanDao{
    
    private Connection connection;

    public penjualanDaoImpl(Connection connection) {
        this.connection = connection;
    }
    
    final String insertProsesKasir="INSERT INTO proseskasir (kode, nama, jml, harga, potongan, tambahan, total, profit, penjual, bonuslangsung, bonuskumulatif) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    final String deleteProsesKasir="DELETE FROM proseskasir WHERE no=?";
    
    final String selectAllProsesKasir="SELECT * FROM proseskasir";

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
                JOptionPane.showMessageDialog(null, "Error dalam insert data penjualan karena = "+ex);
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
        }
        return list;
    }

    @Override
    public void truncatePenjualan() throws penjualanException {
        Statement s = null;
        try{
            connection.setAutoCommit(false);
            s=connection.createStatement();
            s.executeQuery("TRUNCATE TABLE proseskasir");            
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

    @Override
    public void createTable() throws penjualanException {        
        Statement s = null;
        try{
            connection.setAutoCommit(false);
            s=connection.createStatement();
            s.executeQuery("TRUNCATE TABLE proseskasir");            
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

    @Override
    public int getJumlahBarangBeli() throws penjualanException {
        int hasil=0;
        Statement s = null;
        try{
            s=connection.createStatement();
            ResultSet resultSet= s.executeQuery("SELECT jml FROM proseskasir");
            while (resultSet.next()) {
                hasil+=resultSet.getInt("jml");
            }                        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "tidak bisa getjumlah barang beli proses kasir karena = "+e);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        return hasil;
    }    

    @Override
    public void createTempTable() throws penjualanException {
        Statement s = null;
        try{
            connection.setAutoCommit(false);
            s=connection.createStatement();
            s.executeUpdate("CREATE TEMPORARY TABLE IF NOT EXISTS proseskasir(no int(3) NOT NULL AUTO_INCREMENT, kode varchar(45) NOT NULL, nama varchar(45) NOT NULL,jml int(5) NOT NULL,harga bigint(10) NOT NULL,potongan bigint(10) NOT NULL,tambahan bigint(20) NOT NULL,total bigint(15) NOT NULL,profit bigint(20) NOT NULL, penjual varchar(100) NOT NULL, bonuslangsung bigint(10) NOT NULL, bonuskumulatif bigint(10) NOT NULL, PRIMARY KEY (no))");
            connection.commit();
        }catch(SQLException e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "tidak bisa create temp tabel proses kasir karena = "+e);
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

    @Override
    public int getStokBarang(String kode) throws penjualanException {
        int hasil = 0;
        Statement s = null;
        try{
            s=connection.createStatement();
            ResultSet rs=s.executeQuery("SELECT stok FROM barang WHERE idbarang='"+kode+"'");
            if(rs.next()){
                hasil=rs.getInt("stok");
            }            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam mengambil stok barang karena = "+e);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
        return hasil;
    }

    @Override
    public int getJumlahProsesKasir(String text) throws penjualanException {
        int hasil = 0;
        Statement s = null;
        try{
            s=connection.createStatement();
            ResultSet rs=s.executeQuery("SELECT jml from proseskasir where kode='"+text+"'");
            while(rs.next()){
                hasil += rs.getInt("jml");
            }            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam getjumlahproseskasir karena = "+e);
        }finally{          
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
        return hasil;
    }

    @Override
    public int getSubTotalBarangBeli() throws penjualanException {
        int hasil = 0;
        Statement s = null;
        try{
            s=connection.createStatement();
            ResultSet resultSet=s.executeQuery("SELECT total FROM proseskasir");            
            while (resultSet.next()) {
                hasil+=resultSet.getInt("total");
            }            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam getSubTotalBarangBeli karena = "+e);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
        return hasil;
    }

    @Override
    public int getAllTotal() throws penjualanException {
        int hasil = 0;
        Statement s = null;
        try{
            s=connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT total FROM proseskasir");
            while (rs.next()) {                
                hasil+=rs.getInt("total");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Tidak bisa melakukan getALlTotal karena = "+e);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
        return hasil;
    }

    @Override
    public void updateStokSetelahPembayaran() throws penjualanException {
        Statement s = null;
        try{
            s=connection.createStatement();
            ResultSet rs=s.executeQuery("SELECT kode, jml FROM proseskasir");			
            while(rs.next()){
                String kodeProses=rs.getString("kode");				
                int jumlah=rs.getInt("jml");
                int stok=getStokBarang(kodeProses);
                int angkaUpdate=stok-jumlah;
                executeUpdate(angkaUpdate, kodeProses);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Tidak bisa melakukan getALlTotal karena = "+e);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
    }

    private void executeUpdate(int angkaUpdate, String kodeProses) {
        Statement s = null;
        try{            
            s=connection.createStatement();
            s.execute("UPDATE barang SET stok='"+angkaUpdate+"' WHERE idbarang='"+kodeProses+"'");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Tidak bisa melakukan getALL Total karena = "+e);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
    }
    
    @Override
    public int getProfitPerBarisTransaksi() throws penjualanException {
        int hasil = 0;
        Statement s = null;
        try{
            s=connection.createStatement();
            ResultSet resultSet=s.executeQuery("SELECT profit FROM proseskasir");            
            while (resultSet.next()) {
                hasil+=resultSet.getInt("profit");
            }            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Tidak bisa mendapatkan profit perbaris transaksi karena = "+e, "perhatian", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
        return hasil;
    }

    @Override
    public void isiDataTransaksi(String user, String tanggal, String jam, int subTotalBarangBeli, String pembayaran, int sisa, int profitPerBarisTransaksi, String penjual, double bonusLangsung, double bonusKumulatif) throws penjualanException {
        PreparedStatement ps=null;        
        try{
            ps =connection.prepareStatement("INSERT INTO transaksipenjualan (user, Tanggal, jam, total, pembayaran, sisa, profit, penjual, bonuslangsung, bonuskumulatif) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", ps.RETURN_GENERATED_KEYS);
            ps.setString(1, user);
            ps.setString(2, tanggal);
            ps.setString(3, jam);
            ps.setInt(4, getSubTotalBarangBeli());
            ps.setInt(5, Integer.valueOf(pembayaran));
            ps.setInt(6, sisa);
            ps.setDouble(7, getProfitPerBarisTransaksi());
            ps.setString(8, penjual);
            ps.setDouble(9, bonusLangsung);
            ps.setDouble(10, bonusKumulatif);
            ps.executeUpdate();                        
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam insert data penjualan karena = "+exception);
        }finally{
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void isiDataDetailTransaksi(TabelModelPenjualan modelPenjualan, JTable tabelTransaksi, int row, String noTransaksi, String user, String jam, String tanggal, String penjual) throws penjualanException {
        PreparedStatement ps=null;        
        int id = 0;
        try{
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("select max(idtransaksi) from transaksipenjualan");            
            if(rs.next()){
                id = rs.getInt(1);
            }            
        }catch(SQLException exception)     {
            JOptionPane.showMessageDialog(null, "Error dalam mendapatkan max idtransaksi dari tabel transaksipenjualan karena "+exception, "peringatan", JOptionPane.ERROR_MESSAGE);
        }
        for(int a=0; a<row; a++){            
            try{                
                ps =connection.prepareStatement("INSERT INTO detailtransaksipenjualan (iddetailtransaksi, user, Tanggal, jam, kode, namaBarang, jumlah, harga, modal, potongan, tambahan, total, profit, penjual, bonuslangsung, bonuskumulatif) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ProsesKasir pk = modelPenjualan.get(a);
                ps.setString(1, String.valueOf(id));
                ps.setString(2, user);
                ps.setString(3, tanggal);
                ps.setString(4, jam);
                ps.setString(5, pk.getKode());
                ps.setString(6, pk.getNama());
                ps.setString(7, String.valueOf(pk.getJml()));
                ps.setString(8, String.valueOf(pk.getHarga()));
                ps.setInt(9, getModal(pk.getKode()));
                ps.setString(10, String.valueOf(pk.getPotongan()));
                ps.setString(11, String.valueOf(pk.getTambahan()));
                ps.setString(12, String.valueOf(pk.getTotal()));
                ps.setString(13, String.valueOf(pk.getProfit()));
                ps.setString(14, String.valueOf(pk.getPenjual()));
                ps.setString(15, String.valueOf(pk.getBonuslangsung()));
                ps.setString(16, String.valueOf(pk.getBonuskumulatif()));
                ps.executeUpdate();                        
            }catch(SQLException exception){
                JOptionPane.showMessageDialog(null, "Error dalam insert data detail transaksi penjualan karena = "+exception);
            }finally{
               if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    public int getModal(String kode) throws penjualanException {
        int hasil = 0;
        Statement s = null;
        try{
            s=connection.createStatement();
            ResultSet rs = s.executeQuery("select hargamodal from barang where idbarang = '"+kode+"'");
            if(rs.next()){
                hasil = rs.getInt("hargamodal");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Tidak bisa mendapatkan modal barang karena = "+e, "perhatian", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(penjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
        return hasil;
    }   

    @Override
    public int getTotalBonusLangsung() {
        int totalBonusLangsung = 0;
        try{
            Statement s= connection.createStatement();
            ResultSet rs=s.executeQuery("select bonuslangsung from proseskasir");
            while (rs.next()) {                
                totalBonusLangsung+=rs.getInt("bonuslangsung");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Penjualan Controller Error dalam mendapatkan bonus langsung total karena = "+e);
        }
        return totalBonusLangsung;
    }

    @Override
    public int getTotalBonusKumulatif() {
        
        int totalBonusKumulatif = 0;
        try{
            Statement s= connection.createStatement();
            ResultSet rs=s.executeQuery("select bonuskumulatif from proseskasir");
            while (rs.next()) {                
                totalBonusKumulatif+=rs.getInt("bonuskumulatif");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Penjualan Controller Error dalam mendapatkan bonus kumulatif total karena "+e);
        }
        return totalBonusKumulatif;
    }
}
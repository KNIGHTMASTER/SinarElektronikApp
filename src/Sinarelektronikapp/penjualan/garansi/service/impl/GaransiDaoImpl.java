/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.penjualan.garansi.service.impl;

import Sinarelektronikapp.penjualan.garansi.entity.Garansi;
import Sinarelektronikapp.penjualan.garansi.error.GaransiException;
import Sinarelektronikapp.penjualan.garansi.service.GaransiDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class GaransiDaoImpl implements GaransiDao{
    
    private Connection connection;

    public GaransiDaoImpl(Connection connection) {
        this.connection = connection;
    }
        

    private final String INSERTGARANSI = "INSERT INTO garansi (idtransaksi, namabarang, jumlah ,masaakhir, status) values (?, ?, ?, ?, ?)";
    
    private final String UPDATEGARANSI = "UPDATE garansi SET namabarang = ?, jumlah = ?, masaakhir = ?, status = ?, WHERE idtransaksi = ?";
    
    private final String DELETEGARANSI = "DELETE FROM garansi WHERE idtransaksi = ?";
            
    private final String SEARCHBYID = "SELECT * FROM garansi WHERE idtransaksi = ?";
    
    private final String SEARCHBYNAMA = "SELECT * FROM garansi WHERE namabarang = ?";
    
    private final String SEARCHBYJUMLAH = "SELECT * FROM garansi WHERE jumlah = ?";
    
    private final String SEARCHBYMASAAKHIR = "SELECT * FROM garansi WHERE masaakhir = ?";
    
    private final String SEARCHBYSTATUS = "SELECT * FROM garansi WHERE status = ?";
    
    private final String SORTBYID = "SELECT * FROM garansi ORDER BY idtransaksi";
    
    private final String SORTBYNAMA = "SELECT * FROM garansi ORDER BY namabarang";
    
    private final String SORTBYJUMLAH = "SELECT * FROM garansi ORDER BY jumlah";
    
    private final String SORTBYMASAAKHIR = "SELECT * FROM garansi ORDER BY masaakhir";
    
    private final String SORTBYSTATUS = "SELECT * FROM garansi ORDER BY status";    
    
    private final String SELECTALLGARANSI = "SELECT * FROM garansi ORDER BY idtransaksi";
    
    
    @Override
    public void insertGaransi(Garansi garansi) throws GaransiException {

        PreparedStatement statement  = null;
        try{
            connection.setAutoCommit(false);
            statement =connection.prepareStatement(INSERTGARANSI);            
            statement.setString(1, garansi.getIdTransaksi());
            statement.setString(2, garansi.getNamaBarang());
            statement.setString(3, String.valueOf(garansi.getJumlah()));
            statement.setString(4, garansi.getMasaakhir());
            statement.setString(5, garansi.getStatus());
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
    }

    @Override
    public void updateGaransi(Garansi garansi) throws GaransiException {
        PreparedStatement statement  = null;
        try{
            connection.setAutoCommit(false);
            statement =connection.prepareStatement(UPDATEGARANSI);    
            statement.setString(1, garansi.getNamaBarang());
            statement.setString(2, String.valueOf(garansi.getJumlah()));
            statement.setString(3, garansi.getMasaakhir());
            statement.setString(4, garansi.getStatus());
            statement.setString(5, garansi.getIdTransaksi());
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
    }

    @Override
    public void deleteGaransi(String idGaransi) throws GaransiException {
        PreparedStatement statement  = null;
        try{
            connection.setAutoCommit(false);
            statement =connection.prepareStatement(DELETEGARANSI);    
            statement.setString(1, idGaransi);
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }           
    }

    @Override
    public List<Garansi> getAllGaransi() throws GaransiException {        
        List<Garansi> list=new ArrayList<Garansi>();        
        Statement statement  = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECTALLGARANSI);
            while (rs.next()) {
                Garansi garansi = new Garansi();
                garansi.setIdTransaksi(rs.getString("idtransaksi"));
                garansi.setNamaBarang(rs.getString("namabarang"));
                garansi.setJumlah(rs.getInt("jumlah"));
                garansi.setMasaakhir(rs.getString("masaakhir"));
                garansi.setStatus(rs.getString("status"));
                list.add(garansi);
            }            
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
        return list;
    }

    @Override
    public List<Garansi> searchGaransiByid(String idGaransi) throws GaransiException {
        List<Garansi> list=new ArrayList<Garansi>();        
        PreparedStatement statement  = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SEARCHBYID);
            statement.setString(1, "%"+idGaransi+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Garansi garansi = new Garansi();
                garansi.setIdTransaksi(rs.getString("idtransaksi"));
                garansi.setNamaBarang(rs.getString("namabarang"));
                garansi.setJumlah(rs.getInt("jumlah"));
                garansi.setMasaakhir(rs.getString("masaakhir"));
                garansi.setStatus(rs.getString("status"));
                list.add(garansi);
            }            
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
        return list;
    }

    @Override
    public List<Garansi> searchGaransiByNama(String namaBarang) throws GaransiException {

        List<Garansi> list=new ArrayList<Garansi>();        
        PreparedStatement statement  = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SEARCHBYNAMA);
            statement.setString(1, "%"+namaBarang+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Garansi garansi = new Garansi();
                garansi.setIdTransaksi(rs.getString("idtransaksi"));
                garansi.setNamaBarang(rs.getString("namabarang"));
                garansi.setJumlah(rs.getInt("jumlah"));
                garansi.setMasaakhir(rs.getString("masaakhir"));
                garansi.setStatus(rs.getString("status"));
                list.add(garansi);
            }            
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
        return list;  
    }

    @Override
    public List<Garansi> searchGaransiByJumlah(int jumlah) throws GaransiException {

        List<Garansi> list=new ArrayList<Garansi>();        
        PreparedStatement statement  = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SEARCHBYJUMLAH);
            statement.setString(1, "%"+String.valueOf(jumlah)+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Garansi garansi = new Garansi();
                garansi.setIdTransaksi(rs.getString("idtransaksi"));
                garansi.setNamaBarang(rs.getString("namabarang"));
                garansi.setJumlah(rs.getInt("jumlah"));
                garansi.setMasaakhir(rs.getString("masaakhir"));
                garansi.setStatus(rs.getString("status"));
                list.add(garansi);
            }            
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
        return list;
    }

    @Override
    public List<Garansi> searchGaransiByMasaAkhir(String masaAkhir) throws GaransiException {

        List<Garansi> list=new ArrayList<Garansi>();        
        PreparedStatement statement  = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SEARCHBYMASAAKHIR);
            statement.setString(1, "%"+masaAkhir+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Garansi garansi = new Garansi();
                garansi.setIdTransaksi(rs.getString("idtransaksi"));
                garansi.setNamaBarang(rs.getString("namabarang"));
                garansi.setJumlah(rs.getInt("jumlah"));
                garansi.setMasaakhir(rs.getString("masaakhir"));
                garansi.setStatus(rs.getString("status"));
                list.add(garansi);
            }            
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
        return list;
    }

    @Override
    public List<Garansi> searchGaransiByStatus(String status) throws GaransiException {

        List<Garansi> list=new ArrayList<Garansi>();        
        PreparedStatement statement  = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SEARCHBYSTATUS);
            statement.setString(1, "%"+status+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Garansi garansi = new Garansi();
                garansi.setIdTransaksi(rs.getString("idtransaksi"));
                garansi.setNamaBarang(rs.getString("namabarang"));
                garansi.setJumlah(rs.getInt("jumlah"));
                garansi.setMasaakhir(rs.getString("masaakhir"));
                garansi.setStatus(rs.getString("status"));
                list.add(garansi);
            }            
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
        return list;
    }

    @Override
    public List<Garansi> sortGaransiByid() throws GaransiException {
        List<Garansi> list=new ArrayList<Garansi>();        
        PreparedStatement statement  = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SORTBYID);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Garansi garansi = new Garansi();
                garansi.setIdTransaksi(rs.getString("idtransaksi"));
                garansi.setNamaBarang(rs.getString("namabarang"));
                garansi.setJumlah(rs.getInt("jumlah"));
                garansi.setMasaakhir(rs.getString("masaakhir"));
                garansi.setStatus(rs.getString("status"));
                list.add(garansi);
            }            
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
        return list;  
    }

    @Override
    public List<Garansi> sortGaransiByNama() throws GaransiException {
        List<Garansi> list=new ArrayList<Garansi>();        
        PreparedStatement statement  = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SORTBYNAMA);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Garansi garansi = new Garansi();
                garansi.setIdTransaksi(rs.getString("idtransaksi"));
                garansi.setNamaBarang(rs.getString("namabarang"));
                garansi.setJumlah(rs.getInt("jumlah"));
                garansi.setMasaakhir(rs.getString("masaakhir"));
                garansi.setStatus(rs.getString("status"));
                list.add(garansi);
            }            
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
        return list;  
    }

    @Override
    public List<Garansi> sortGaransiByJumlah() throws GaransiException {
        List<Garansi> list=new ArrayList<Garansi>();        
        PreparedStatement statement  = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SORTBYJUMLAH);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Garansi garansi = new Garansi();
                garansi.setIdTransaksi(rs.getString("idtransaksi"));
                garansi.setNamaBarang(rs.getString("namabarang"));
                garansi.setJumlah(rs.getInt("jumlah"));
                garansi.setMasaakhir(rs.getString("masaakhir"));
                garansi.setStatus(rs.getString("status"));
                list.add(garansi);
            }            
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
        return list;  

    }

    @Override
    public List<Garansi> sortGaransiByMasaAkhir() throws GaransiException {
        List<Garansi> list=new ArrayList<Garansi>();        
        PreparedStatement statement  = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SORTBYMASAAKHIR);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Garansi garansi = new Garansi();
                garansi.setIdTransaksi(rs.getString("idtransaksi"));
                garansi.setNamaBarang(rs.getString("namabarang"));
                garansi.setJumlah(rs.getInt("jumlah"));
                garansi.setMasaakhir(rs.getString("masaakhir"));
                garansi.setStatus(rs.getString("status"));
                list.add(garansi);
            }            
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
        return list;  

    }

    @Override
    public List<Garansi> sortGaransiByStatus() throws GaransiException {
        List<Garansi> list=new ArrayList<Garansi>();        
        PreparedStatement statement  = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SORTBYSTATUS);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Garansi garansi = new Garansi();
                garansi.setIdTransaksi(rs.getString("idtransaksi"));
                garansi.setNamaBarang(rs.getString("namabarang"));
                garansi.setJumlah(rs.getInt("jumlah"));
                garansi.setMasaakhir(rs.getString("masaakhir"));
                garansi.setStatus(rs.getString("status"));
                list.add(garansi);
            }            
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GaransiDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
        return list;  

    }
    
}

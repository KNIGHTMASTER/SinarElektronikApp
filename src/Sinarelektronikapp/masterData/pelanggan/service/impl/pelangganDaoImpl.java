/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.pelanggan.service.impl;

import Sinarelektronikapp.masterData.pelanggan.entity.pelanggan;
import Sinarelektronikapp.masterData.pelanggan.error.pelangganException;
import Sinarelektronikapp.masterData.pelanggan.service.pelangganDao;
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
public class pelangganDaoImpl implements pelangganDao{
    final String insertPelanggan="INSERT INTO pelanggan (nama, alamat, kota, propinsi, kodePost, telepon, fax, kontakPerson, Catatan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    final String updatePelanggan="UPDATE pelanggan set , nama=?, alamat?, kota=?, propinsi=?, kodePost=?, telepon=?, fax=?, kontakPerson=?, Catatan=?) WHERE idpelanggan=?";
    
    final String deletePelanggan="DELETE FROM pelanggan WHERE idpelanggan=?";
    
    final String selectAllPelanggan="SELECT * FROM pelanggan";
    
    final String getById="SELECT * FROM pelanggan WHERE idpelanggan LIKE ?";
    
    final String getByNama="SELECT * FROM pelanggan WHERE nama LIKE ?";
    
    final String getByAlamat="SELECT * FROM pelanggan WHERE alamat LIKE ?";
    
    final String getByKota="SELECT * FROM pelanggan WHERE kota LIKE ?";
    
    final String getByPropinsi="SELECT * FROM pelanggan WHERE propinsi LIKE ?";    
    
    final String getByKodePost="SELECT * FROM pelanggan WHERE kodePost LIKE ?";    
    
    final String getByTelepon="SELECT * FROM pelanggan WHERE telepon LIKE ?";    
    
    final String getByFax="SELECT * FROM pelanggan WHERE fax LIKE ?";    
    
    final String getByKontakPerson="SELECT * FROM pelanggan WHERE kontakPerson LIKE ?";    
    
    final String getByCatatan="SELECT * FROM pelanggan WHERE Catatan LIKE ?";    
    
    final String sortById="SELECT * FROM pelanggan ORDER BY idpelanggan";
    
    final String sortByNama="SELECT * FROM pelanggan ORDER BY nama";
    
    final String sortByAlamat="SELECT * FROM pelanggan ORDER BY alamat";
    
    final String sortByKota="SELECT * FROM pelanggan ORDER BY kota";
    
    final String sortByPropinsi="SELECT * FROM pelanggan ORDER BY propinsi";    
    
    final String sortByKodePost="SELECT * FROM pelanggan ORDER BY kodePost";
    
    final String sortByTelepon="SELECT * FROM pelanggan ORDER BY telepon";    
    
    final String sortByFax="SELECT * FROM pelanggan ORDER BY fax";    
    
    final String sortByKontakPerson="SELECT * FROM pelanggan ORDER BY kontakPerson";    
    
    final String sortByCatatan="SELECT * FROM pelanggan ORDER BY Catatan";    
    
    final String getLastId="SELECT COUNT(idpelanggan)+1 TOTAL FROM pelanggan";
    
    private Connection connection;

    public pelangganDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void insertPelanggan(pelanggan pelanggan) throws pelangganException {
        PreparedStatement ps=null;
        try{
            connection.setAutoCommit(false);
            ps=connection.prepareStatement(insertPelanggan, ps.RETURN_GENERATED_KEYS);
            ps.setString(1, pelanggan.getIdpelanggan());
            ps.setString(2, pelanggan.getNama());
            ps.setString(3, pelanggan.getAlamat());
            ps.setString(4, pelanggan.getKota());
            ps.setString(5, pelanggan.getPropinsi());
            ps.setString(6, pelanggan.getKodePost());
            ps.setString(7, pelanggan.getTelepon());
            ps.setString(8, pelanggan.getFax());
            ps.setString(9, pelanggan.getKontakPerson());
            ps.setString(10, pelanggan.getCatatan());
            ps.executeUpdate();
            connection.commit();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                pelanggan.setIdpelanggan(String.valueOf(rs.getInt(1)));
            }
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void updatePelanggan(pelanggan pelanggan) throws pelangganException {
        PreparedStatement ps=null;
        try{
            connection.setAutoCommit(false);
            ps=connection.prepareStatement(updatePelanggan);            
            ps.setString(1, pelanggan.getNama());
            ps.setString(2, pelanggan.getAlamat());
            ps.setString(3, pelanggan.getKota());
            ps.setString(4, pelanggan.getPropinsi());
            ps.setString(5, pelanggan.getKodePost());
            ps.setString(6, pelanggan.getTelepon());
            ps.setString(7, pelanggan.getFax());
            ps.setString(8, pelanggan.getKontakPerson());
            ps.setString(9, pelanggan.getCatatan());
            ps.setString(10, pelanggan.getIdpelanggan());
            ps.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void deletePelanggan(String idPelanggan) throws pelangganException {
    PreparedStatement ps=null;
        try{
            connection.setAutoCommit(false);
            ps=connection.prepareStatement(deletePelanggan);
            ps.setString(1, idPelanggan);
            ps.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<pelanggan> selectAllPelanggan() throws pelangganException {
     Statement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet rs=statement.executeQuery(selectAllPelanggan);
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> getById(String idpelanggan) throws pelangganException {
    PreparedStatement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(getById);
            statement.setString(1, "%"+idpelanggan+"%");
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> getByNama(String nama) throws pelangganException {
  PreparedStatement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(getByNama);
            statement.setString(1, "%"+nama+"%");
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> getByALamat(String alamat) throws pelangganException {
  PreparedStatement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(getByAlamat);
            statement.setString(1, "%"+alamat+"%");
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> getByKota(String kota) throws pelangganException {
  PreparedStatement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(getByKota);
            statement.setString(1, "%"+kota+"%");
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> getByPropinsi(String propinsi) throws pelangganException {
  PreparedStatement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(getByPropinsi);
            statement.setString(1, "%"+propinsi+"%");
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> getByKodePost(String kodePost) throws pelangganException {
  PreparedStatement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(getByKodePost);
            statement.setString(1, "%"+kodePost+"%");
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> getByTelepon(String telepon) throws pelangganException {
  PreparedStatement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(getByTelepon);
            statement.setString(1, "%"+telepon+"%");
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> getByFax(String fax) throws pelangganException {
  PreparedStatement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(getByFax);
            statement.setString(1, "%"+fax+"%");
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> getByKontakPerson(String kontakPerson) throws pelangganException {
  PreparedStatement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(getByKontakPerson);
            statement.setString(1, "%"+kontakPerson+"%");
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> getByCatatan(String catatan) throws pelangganException {
  PreparedStatement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(getByCatatan);
            statement.setString(1, "%"+catatan+"%");
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> sortById() throws pelangganException {
    Statement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            ResultSet rs=statement.executeQuery(sortById);
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> sortByNama() throws pelangganException {
    Statement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            ResultSet rs=statement.executeQuery(sortByNama);
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> sortByALamat() throws pelangganException {
    Statement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            ResultSet rs=statement.executeQuery(sortByAlamat);
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> sortByKota() throws pelangganException {
    Statement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            ResultSet rs=statement.executeQuery(sortByKota);
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> sortByPropinsi() throws pelangganException {
    Statement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            ResultSet rs=statement.executeQuery(sortByPropinsi);
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> sortByKodePost() throws pelangganException {
    Statement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            ResultSet rs=statement.executeQuery(sortByKodePost);
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> sortByTelepon() throws pelangganException {
    Statement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            ResultSet rs=statement.executeQuery(sortByTelepon);
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> sortByFax() throws pelangganException {
    Statement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            ResultSet rs=statement.executeQuery(sortByFax);
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> sortByKontakPerson() throws pelangganException {
    Statement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            ResultSet rs=statement.executeQuery(sortByKontakPerson);
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<pelanggan> sortByCatatan() throws pelangganException {
    Statement statement=null;
     List<pelanggan> list=new ArrayList<pelanggan>();
        try{
            connection.setAutoCommit(false);
            ResultSet rs=statement.executeQuery(sortByCatatan);
            while(rs.next()){
                pelanggan pelanggan=new pelanggan();
                pelanggan.setIdpelanggan(rs.getString("idpelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setKota(rs.getString("kota"));
                pelanggan.setPropinsi(rs.getString("propinsi"));
                pelanggan.setKodePost(rs.getString("kodePost"));
                pelanggan.setTelepon(rs.getString("telepon"));
                pelanggan.setFax(rs.getString("fax"));
                pelanggan.setKontakPerson(rs.getString("kontakPerson"));
                pelanggan.setCatatan(rs.getString("Catatan"));
                
                list.add(pelanggan);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public int getLastId() throws pelangganException {
        Statement ps=null;
        int hasil=0;
        try{
            connection.setAutoCommit(false);
            ps=connection.createStatement();
            ResultSet rs=ps.executeQuery(getLastId);
            if(rs.next()){
                hasil=rs.getInt("TOTAL");
            }
            //JOptionPane.showMessageDialog(null, "hasil = "+hasil);
            connection.commit();
        }catch(SQLException e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return hasil;
    }
    
    
    
}

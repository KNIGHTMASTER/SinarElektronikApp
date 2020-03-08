/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.supplier.service.impl;

import Sinarelektronikapp.masterdata.supplier.entity.supplier;
import Sinarelektronikapp.masterdata.supplier.service.supplierDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Sinarelektronikapp.masterdata.supplier.error.supplierException;

/**
 *
 * @author Fauzi
 */
public class supplierDaoImpl implements supplierDao{
    
    private Connection connection;

    public supplierDaoImpl(Connection connection) {
        this.connection = connection;
    }    
    
    private final String insertSupplier = "INSERT INTO supplier (nama, alamat, kota, propinsi, kodePost, telepon, fax, bank, nomorRek, atasNama, kontakPerson, email, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private final String updateSupplier = "UPDATE supplier SET nama = ?, alamat = ?, kota = ?, propinsi = ?, kodePost = ?, telepon = ?, fax = ?, nomorRek = ?, atasNama = ?, kontakPerson = ?, email = ?, note = ? WHERE idsupplier = ?";
    
    private final String deleteSupplier = "DELETE FROM supplier WHERE idsupplier = ?";
    
    private final String searchByid = "SELECT * FROM supplier WHERE idsupplier LIKE ?";
    
    private final String searchByNama = "SELECT * FROM supplier WHERE nama LIKE ?";
    
    private final String searchByAlamat = "SELECT * FROM supplier WHERE alamat LIKE ?";
    
    private final String searchByKota = "SELECT * FROM supplier WHERE kota LIKE ?";
    
    private final String searchByPropinsi = "SELECT * FROM supplier WHERE propinsi LIKE ?";    
    
    private final String searchByKodePost = "SELECT * FROM supplier WHERE kodePost LIKE ?";
    
    private final String searchByTelepon = "SELECT * FROM supplier WHERE telepon LIKE ?";
    
    private final String searchByFax = "SELECT * FROM supplier WHERE fax LIKE ?";
    
    private final String searchByBank = "SELECT * FROM supplier WHERE bank LIKE ?";
    
    private final String searchByNomorRek = "SELECT * FROM supplier WHERE nomorRek LIKE ?";
    
    private final String searchByAtasNama = "SELECT * FROM supplier WHERE atasNama LIKE ?";
    
    private final String searchByKontakPerson = "SELECT * FROM supplier WHERE kontakPerson LIKE ?";
    
    private final String searchByEmail  = "SELECT * FROM supplier WHERE email LIKE ?";
    
    private final String searchByNote = "SELECT * FROM supplier WHERE note LIKE ?";
    
    private final String sortByid = "SELECT * FROM supplier ORDER BY idsupplier";
    
    private final String sortByNama = "SELECT * FROM supplier ORDER BY nama";
    
    private final String sortByAlamat = "SELECT * FROM supplier ORDER BY alamat";
    
    private final String sortByKota = "SELECT * FROM supplier ORDER BY kota";
    
    private final String sortByPropinsi = "SELECT * FROM supplier ORDER BY propinsi";    
    
    private final String sortByKodePost = "SELECT * FROM supplier ORDER BY kodePost";
    
    private final String sortByTelepon = "SELECT * FROM supplier ORDER BY telepon";
    
    private final String sortByFax = "SELECT * FROM supplier ORDER BY fax";
    
    private final String sortByBank = "SELECT * FROM supplier ORDER BY bank";
    
    private final String sortByNomorRek = "SELECT * FROM supplier ORDER BY nomorRek";
    
    private final String sortByAtasNama = "SELECT * FROM supplier ORDER BY atasNama";
    
    private final String sortByKontakPerson = "SELECT * FROM supplier ORDER BY kontakPerson";
    
    private final String sortByEmail  = "SELECT * FROM supplier ORDER BY email";
    
    private final String sortByNote = "SELECT * FROM supplier ORDER BY note";    
    
    private final String selectAllSupplier = "SELECT * FROM supplier";
    
    public final String getLastId = "SELECT COUNT(idsupplier)+1 TOTAL FROM supplier";

    @Override
    public void insertSupplier(supplier supplier) throws supplierException {
        PreparedStatement statement  = null;
        try{
            connection.setAutoCommit(false);
            statement =connection.prepareStatement(insertSupplier, statement.RETURN_GENERATED_KEYS);            
            statement.setString(1, supplier.getNama());
            statement.setString(2, supplier.getAlamat());
            statement.setString(3, supplier.getKota());
            statement.setString(4, supplier.getPropinsi());
            statement.setString(5, supplier.getKodePost());
            statement.setString(6, supplier.getTelepon());
            statement.setString(7, supplier.getFax());
            statement.setString(8, supplier.getBank());
            statement.setString(9, supplier.getNomorRek());
            statement.setString(10, supplier.getAtasNama());
            statement.setString(11, supplier.getKontakPerson());
            statement.setString(12, supplier.getEmail());
            statement.setString(13, supplier.getNote());
            statement.executeUpdate();
            connection.commit();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                supplier.setIdsupplier(String.valueOf(rs.getInt(1)));
            }
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void updateSupplier(supplier supplier) throws supplierException {
        PreparedStatement statement  = null;
        try{
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(updateSupplier);
            statement.setString(1, supplier.getNama());
            statement.setString(2, supplier.getAlamat());
            statement.setString(3, supplier.getKota());
            statement.setString(4, supplier.getPropinsi());
            statement.setString(5, supplier.getKodePost());
            statement.setString(6, supplier.getTelepon());
            statement.setString(7, supplier.getFax());
            statement.setString(8, supplier.getBank());
            statement.setString(9, supplier.getNomorRek());
            statement.setString(10, supplier.getAtasNama());
            statement.setString(11, supplier.getKontakPerson());
            statement.setString(12, supplier.getEmail());
            statement.setString(13, supplier.getNote());
            statement.setString(14, supplier.getIdsupplier());
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void deleteSupplier(String idSupplier) throws supplierException {
        PreparedStatement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(deleteSupplier);
            statement.setString(1, idSupplier);
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<supplier> searchById(String idSupplier) throws supplierException {
        List<supplier> list = new ArrayList<supplier>();
        PreparedStatement statement= null;
        supplier supplier= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(searchByid);
            statement.setString(1, "%"+idSupplier+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new supplierException(exception.getMessage());
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
    }

    @Override
    public List<supplier> searchByNama(String namaSupplier) throws supplierException {
 List<supplier> list = new ArrayList<supplier>();
        PreparedStatement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(searchByid);
            statement.setString(1, "%"+namaSupplier+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> searchByAlamat(String alamatSupplier) throws supplierException {
 List<supplier> list = new ArrayList<supplier>();
        PreparedStatement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(searchByid);
            statement.setString(1, "%"+alamatSupplier+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> searchByKota(String kotaSupplier) throws supplierException {
 List<supplier> list = new ArrayList<supplier>();
        PreparedStatement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(searchByid);
            statement.setString(1, "%"+kotaSupplier+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> searchByPropinsi(String propinsiSupplier) throws supplierException {
 List<supplier> list = new ArrayList<supplier>();
        PreparedStatement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(searchByid);
            statement.setString(1, "%"+propinsiSupplier+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> searchByKodePost(String kodePostSupplier) throws supplierException {
 List<supplier> list = new ArrayList<supplier>();
        PreparedStatement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(searchByid);
            statement.setString(1, "%"+kodePostSupplier+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> searchByTelepon(String teleponSupplier) throws supplierException {
 List<supplier> list = new ArrayList<supplier>();
        PreparedStatement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(searchByid);
            statement.setString(1, "%"+teleponSupplier+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> searchByFax(String faxSupplier) throws supplierException {
 List<supplier> list = new ArrayList<supplier>();
        PreparedStatement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(searchByid);
            statement.setString(1, "%"+faxSupplier+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> searchByBank(String BankSupplier) throws supplierException {
 List<supplier> list = new ArrayList<supplier>();
        PreparedStatement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(searchByid);
            statement.setString(1, "%"+BankSupplier+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> searchByNomorRek(String nomorRekSupplier) throws supplierException {
 List<supplier> list = new ArrayList<supplier>();
        PreparedStatement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(searchByid);
            statement.setString(1, "%"+nomorRekSupplier+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> searchByAtasNama(String atasNamaSupplier) throws supplierException {
    List<supplier> list = new ArrayList<supplier>();
        PreparedStatement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(searchByid);
            statement.setString(1, "%"+atasNamaSupplier+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> searchByKontakPerson(String kontakPersonSupplier) throws supplierException {
 List<supplier> list = new ArrayList<supplier>();
        PreparedStatement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(searchByid);
            statement.setString(1, "%"+kontakPersonSupplier+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> searchByEmail(String emailSupplier) throws supplierException {
 List<supplier> list = new ArrayList<supplier>();
        PreparedStatement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(searchByid);
            statement.setString(1, "%"+emailSupplier+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> searchByNote(String noteSupplier) throws supplierException {
    List<supplier> list = new ArrayList<supplier>();
        PreparedStatement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(searchByid);
            statement.setString(1, "%"+noteSupplier+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> sortById(String idSupplier) throws supplierException {
    List<supplier> list = new ArrayList<supplier>();
        Statement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            ResultSet rs = statement.executeQuery(sortByid);
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> sortByNama(String namaSupplier) throws supplierException {
    List<supplier> list = new ArrayList<supplier>();
        Statement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            ResultSet rs = statement.executeQuery(sortByNama);
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> sortByAlamat(String alamatSupplier) throws supplierException {
    List<supplier> list = new ArrayList<supplier>();
        Statement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            ResultSet rs = statement.executeQuery(sortByAlamat);
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> sortByKota(String kotaSupplier) throws supplierException {
    List<supplier> list = new ArrayList<supplier>();
        Statement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            ResultSet rs = statement.executeQuery(sortByKota);
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> sortByPropinsi(String propinsiSupplier) throws supplierException {
    List<supplier> list = new ArrayList<supplier>();
        Statement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            ResultSet rs = statement.executeQuery(sortByPropinsi);
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> sortByKodePost(String kodePostSupplier) throws supplierException {
    List<supplier> list = new ArrayList<supplier>();
        Statement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            ResultSet rs = statement.executeQuery(sortByKodePost);
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> sortByTelepon(String teleponSupplier) throws supplierException {
    List<supplier> list = new ArrayList<supplier>();
        Statement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            ResultSet rs = statement.executeQuery(sortByTelepon);
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> sortByFax(String faxSupplier) throws supplierException {
    List<supplier> list = new ArrayList<supplier>();
        Statement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            ResultSet rs = statement.executeQuery(sortByFax);
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> sortByBank(String BankSupplier) throws supplierException {
    List<supplier> list = new ArrayList<supplier>();
        Statement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            ResultSet rs = statement.executeQuery(sortByBank);
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> sortByNomorRek(String nomorRekSupplier) throws supplierException {
    List<supplier> list = new ArrayList<supplier>();
        Statement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            ResultSet rs = statement.executeQuery(sortByNomorRek);
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> sortByAtasNama(String atasNamaSupplier) throws supplierException {
    List<supplier> list = new ArrayList<supplier>();
        Statement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            ResultSet rs = statement.executeQuery(sortByAtasNama);
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> sortByKontakPerson(String kontakPersonSupplier) throws supplierException {
    List<supplier> list = new ArrayList<supplier>();
        Statement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            ResultSet rs = statement.executeQuery(sortByKontakPerson);
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> sortByEmail(String emailSupplier) throws supplierException {
    List<supplier> list = new ArrayList<supplier>();
        Statement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            ResultSet rs = statement.executeQuery(sortByEmail);
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> sortByNote(String noteSupplier) throws supplierException {
    List<supplier> list = new ArrayList<supplier>();
        Statement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            ResultSet rs = statement.executeQuery(sortByNote);
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<supplier> selectAllSupplier() throws supplierException {
    List<supplier> list = new ArrayList<supplier>();
        Statement statement= null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            ResultSet rs = statement.executeQuery(selectAllSupplier);
            while (rs.next()) {                
                supplier supplier = new supplier();
                supplier.setIdsupplier(rs.getString("idsupplier"));
                supplier.setNama(rs.getString("nama"));
                supplier.setAlamat(rs.getString("alamat"));
                supplier.setKota(rs.getString("kota"));
                supplier.setPropinsi(rs.getString("propinsi"));
                supplier.setKodePost(rs.getString("kodePost"));
                supplier.setTelepon(rs.getString("telepon"));
                supplier.setFax(rs.getString("fax"));
                supplier.setBank(rs.getString("bank"));
                supplier.setNomorRek(rs.getString("nomorRek"));
                supplier.setAtasNama(rs.getString("atasNama"));
                supplier.setKontakPerson(rs.getString("kontakPerson"));
                supplier.setEmail(rs.getString("email"));
                supplier.setNote(rs.getString("note"));
                list.add(supplier);
            }
            connection.commit();
        }        
        catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(supplierDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public int getLastId() throws supplierException {
        Statement statement = null;
        int total = 0;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(getLastId);
            while (rs.next()) {                
                total = rs.getInt("TOTAL");
            }
            connection.commit();
            return total;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new supplierException(exception.getMessage());
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
    }
    
}

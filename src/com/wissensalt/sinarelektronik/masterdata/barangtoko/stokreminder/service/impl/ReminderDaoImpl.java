/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.barangtoko.stokreminder.service.impl;

import com.wissensalt.sinarelektronik.masterdata.barangtoko.entity.barang;
import com.wissensalt.sinarelektronik.masterdata.barangtoko.error.BarangException;
import com.wissensalt.sinarelektronik.dao.impl.BarangTokoDAOImpl;
import com.wissensalt.sinarelektronik.masterdata.barangtoko.stokreminder.service.ReminderDao;
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


public class ReminderDaoImpl implements ReminderDao {

    private Connection connection;    

    final String updateBarang = "UPDATE barang SET idbarang = ?, idbarcode = ?, namabarang = ?, tipe = ?, merek = ?, hargamodal = ?, grosir, eceran, satuan = ?, stok = ?, stok_minimum = ?, supplier = ?, keterangan = ?, gambar=? kategori = ? WHERE idbarang = ?";

    final String deleteBarang = "DELETE from barangbesar where idbarang = ?";

    final String getById = "SELECT * from barangbesar where idbarang like ?";

    final String getByBarcode = "SELECT * from barangbesar where idbarcode like ?";
    
    final String getByNama = "SELECT * from barangbesar where namabarang like ?";
    
    final String getBytipe = "SELECT * from barangbesar where tipe like ?";
    
    final String getBymerek = "SELECT * from barangbesar where merek like ?";
    
    final String getByharga = "SELECT * from barangbesar where harga like ?";
    
    final String getBysatuan = "SELECT * from barangbesar where satuan like ?";
    
    final String getBystok = "SELECT * from barangbesar where stok like ?";
    
    final String getBystok_min = "SELECT * from barangbesar where stok_minimum like ?";
    
    final String getBysupplier = "SELECT * from barangbesar where supplier like ?";
    
    final String getByket = "SELECT * from barangbesar where keterangan like ?";
    
    final String sortById = "SELECT * from barangbesar order by idbarang WHERE stok<stok_minimum";

    final String sortByBarcode = "SELECT * from barangbesar order by idbarcode WHERE stok<stok_minimum";
    
    final String sortByNama = "SELECT * from barangbesar order by namabarang WHERE stok<stok_minimum";
    
    final String sortBytipe = "SELECT * from barangbesar order by tipe WHERE stok<stok_minimum";
    
    final String sortBymerek = "SELECT * from barangbesar order by merek WHERE stok<stok_minimum";
    
    final String sortByharga = "SELECT * from barangbesar order by harga WHERE stok<stok_minimum";
    
    final String sortBysatuan = "SELECT * from barangbesar order by satuan WHERE stok<stok_minimum";
    
    final String sortBystok = "SELECT * from barangbesar order by stok WHERE stok<stok_minimum";
    
    final String sortBystok_min = "SELECT * from barangbesar order by stok_minimum WHERE stok<stok_minimum";
    
    final String sortBysupplier = "SELECT * from barangbesar order by supplier WHERE stok<stok_minimum";
    
    final String sortByket = "SELECT * from barangbesar order by keterangan WHERE stok<stok_minimum";

    final String selectAll = "SELECT * from barangbesar WHERE stok<stok_minimum";
    
    final String getLastId = "SELECT COUNT(idbarang) total from barang";

    public ReminderDaoImpl(Connection connection) {
        this.connection = connection;
    }       

    @Override
    public void updateBarang(barang barang) throws BarangException {
        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(updateBarang);
            statement.setString(1, barang.getIdBarang());
            statement.setString(2, barang.getIdBarcode());
            statement.setString(3, barang.getNamaBarang());
            statement.setString(4, barang.getTipe());
            statement.setString(5, barang.getMerek());
            statement.setInt(6, barang.getModal());
            statement.setInt(7, barang.getGrosir());
            statement.setInt(8, barang.getEceran());
            statement.setInt(9, barang.getStok());
            statement.setInt(10, barang.getStok_min());
            statement.setString(11, barang.getSupplier());
            statement.setString(12, barang.getKeterangan());            
            try {
                statement.setBlob(13, new FileInputStream(barang.getGambar()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BarangTokoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            statement.setString(13, barang.getGaransi());
            statement.setString(14, String.valueOf(barang.getLamaGaransi()));
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Update barangkecil besar gagal karena "+exception, "Perhatian", JOptionPane.WARNING_MESSAGE);
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
    public List<barang> getBarangbyId(String idBarang) throws BarangException {
        PreparedStatement statement = null;
        List<barang> list = new ArrayList<barang>();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getById);
            statement.setString(1, "%"+idBarang+"%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                barang barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            return list;
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new BarangException(exception.getMessage());
        } finally {
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
    

    @Override
    public List<barang> getBarangbyBarcode(String idBarcode) throws BarangException {
        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getByBarcode);            
            statement.setString(1, "%"+idBarcode+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
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
    public List<barang> selectAllBarang() throws BarangException {
        Statement statement = null;
        List<barang> list = new ArrayList<barang>();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(selectAll);

            while (rs.next()) {
                barang barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new barangException(exception.getMessage());
        } finally {
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
        return list;

    }

    @Override
    public List<barang>  getBarangbyNama(String nama) throws BarangException {

        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getByNama);
            statement.setString(1, "%"+nama+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
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
        return list;
    }

    @Override
    public List<barang>  getBarangbyTipe(String tipe) throws BarangException {

        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBytipe);
            statement.setString(1, "%"+tipe+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));               
                list.add(barang);
            }
            connection.commit();            
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
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
        return list;
    }

    @Override
    public List<barang> getBarangbyMerek(String merek) throws BarangException {

        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBymerek);
            statement.setString(1, "%"+merek+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));                
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            //return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
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
        return list;
    }

    @Override
    public List<barang> getBarangbyHarga(String Harga) throws BarangException {

        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getByharga);
            statement.setString(1, "%"+Harga+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
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
        return list;
    }

    @Override
    public List<barang> getBarangbySatuan(String satuan) throws BarangException {

        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBysatuan);
            statement.setString(1, "%"+satuan+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            //return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
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
        return list;
    }

    @Override
    public List<barang> getBarangbyStok(String stok) throws BarangException {

        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBystok);
            statement.setString(1, "%"+stok+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            //return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
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
        return list;
    }

    @Override
    public List<barang> getBarangbyStok_min(String stok_min) throws BarangException {

        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBystok_min);
            statement.setString(1, "%"+stok_min+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            //return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
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
        return list;
    }

    @Override
    public List<barang> getBarangbySupplier(String supplier) throws BarangException {

        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBysupplier);
            statement.setString(1, "%"+supplier+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            //return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
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
        return list;
    }

    @Override
    public List<barang> getBarangbyKeterangan(String keterangan) throws BarangException {

        List<barang> list = new ArrayList<barang>();
        PreparedStatement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getByket);
            statement.setString(1, "%"+keterangan+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            //return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
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
        return list;
    }

    @Override
    public List<barang> sortBarangbyId() throws BarangException {
        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortById);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            //return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
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
        return list;
    }

    @Override
    public List<barang> sortBarangbyBarcode() throws BarangException {
        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortByBarcode);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            //return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            //throw new barangException(exception.getMessage());
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
        return list;
    }

    @Override
    public List<barang> sortBarangbyNama() throws BarangException {

        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortByNama);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
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
    public List<barang> sortBarangbyTipe() throws BarangException {

        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortBytipe);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
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
    public List<barang> sortBarangbyMerek() throws BarangException {

        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortBymerek);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
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
    public List<barang> sortBarangbyHarga() throws BarangException {

        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortByharga);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
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
    public List<barang> sortBarangbySatuan() throws BarangException {

        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortBysatuan);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
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
    public List<barang> sortBarangbyStok() throws BarangException {

        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortBystok);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
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
    public List<barang> sortBarangbyStok_min() throws BarangException {

        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortBystok_min);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
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
    public List<barang> sortBarangbySupplier() throws BarangException {

        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortBysupplier);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
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
    public List<barang> sortBarangbyKeterangan() throws BarangException {

        List<barang> list = new ArrayList<barang>();
        Statement statement = null;
        barang barang = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortByket);
            while (rs.next()) {                
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("MerekDTO"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));                            
                barang.setGambarHasil(rs.getBlob("gambar"));
                list.add(barang);
            }
            connection.commit();
            return list;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
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
    public int getBarangLastId() throws BarangException {

        Statement statement = null;
        int total = 0;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(getLastId);
            while (rs.next()) {                
                total = rs.getInt("total");
            }
            connection.commit();
            return total;
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            throw new BarangException(exception.getMessage());
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

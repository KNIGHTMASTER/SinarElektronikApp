/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.stokreminder.service.impl;

import com.wissensalt.sinarelektronik.masterdata.barangkecil.entity.BarangKecilDTO;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.error.BarangException;
import com.wissensalt.sinarelektronik.masterdata.stokreminder.service.ReminderDao;
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

    final String deleteBarang = "DELETE FROM barangkecil where idbarang = ?";

    final String getById = "SELECT * FROM barang where idbarang like ?";

    final String getByBarcode = "SELECT * FROM barang where idbarcode like ?";
    
    final String getByNama = "SELECT * FROM barang where namabarang like ?";
    
    final String getBytipe = "SELECT * FROM barang where tipe like ?";
    
    final String getBymerek = "SELECT * FROM barang where merek like ?";
    
    final String getByharga = "SELECT * FROM barang where harga like ?";
    
    final String getBysatuan = "SELECT * FROM barang where satuan like ?";
    
    final String getBystok = "SELECT * FROM barang where stok like ?";
    
    final String getBystok_min = "SELECT * FROM barang where stok_minimum like ?";
    
    final String getBysupplier = "SELECT * FROM barang where supplier like ?";
    
    final String getByket = "SELECT * FROM barang where keterangan like ?";
    
    final String sortById = "SELECT * FROM barang order by idbarang WHERE stok<stok_minimum";

    final String sortByBarcode = "SELECT * FROM barang order by idbarcode WHERE stok<stok_minimum";
    
    final String sortByNama = "SELECT * FROM barang order by namabarang WHERE stok<stok_minimum";
    
    final String sortBytipe = "SELECT * FROM barang order by tipe WHERE stok<stok_minimum";
    
    final String sortBymerek = "SELECT * FROM barang order by merek WHERE stok<stok_minimum";
    
    final String sortByharga = "SELECT * FROM barang order by harga WHERE stok<stok_minimum";
    
    final String sortBysatuan = "SELECT * FROM barang order by satuan WHERE stok<stok_minimum";
    
    final String sortBystok = "SELECT * FROM barang order by stok WHERE stok<stok_minimum";
    
    final String sortBystok_min = "SELECT * FROM barang order by stok_minimum WHERE stok<stok_minimum";
    
    final String sortBysupplier = "SELECT * FROM barang order by supplier WHERE stok<stok_minimum";
    
    final String sortByket = "SELECT * FROM barang order by keterangan WHERE stok<stok_minimum";

    final String selectAll = "SELECT * FROM barang WHERE stok<stok_minimum";
    
    final String getLastId = "SELECT COUNT(idbarang) total from barang";

    public ReminderDaoImpl(Connection connection) {
        this.connection = connection;
    }       

    @Override
    public void updateBarang(BarangKecilDTO BarangKecilDTO) throws BarangException {
        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(updateBarang);
            statement.setString(1, BarangKecilDTO.getIdBarang());
            statement.setString(2, BarangKecilDTO.getIdBarcode());
            statement.setString(3, BarangKecilDTO.getNamaBarang());
            statement.setString(4, BarangKecilDTO.getTipe());
            statement.setString(5, BarangKecilDTO.getMerek());
            statement.setInt(6, BarangKecilDTO.getHargamodal());
            statement.setInt(7, BarangKecilDTO.getEceran());
            statement.setInt(8, BarangKecilDTO.getGrosir());
            statement.setString(9, BarangKecilDTO.getSatuan());
            statement.setInt(10, BarangKecilDTO.getStok());
            statement.setInt(11, BarangKecilDTO.getStokMinimum());
            statement.setString(12, BarangKecilDTO.getSupplier());
            statement.setString(13, BarangKecilDTO.getKeterangan());
            try {
                statement.setBlob(14, new FileInputStream(BarangKecilDTO.getGambar()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            statement.setString(14, BarangKecilDTO.getKategori());
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Insert barangkecil gagal karena "+exception);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }


    @Override
    public List<BarangKecilDTO> getBarangbyId(String idBarang) throws BarangException {
        PreparedStatement statement = null;
        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getById);
            statement.setString(1, "%"+idBarang+"%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                BarangKecilDTO BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
        }
    }
    

    @Override
    public List<BarangKecilDTO> getBarangbyBarcode(String idBarcode) throws BarangException {
        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        PreparedStatement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getByBarcode);            
            statement.setString(1, "%"+idBarcode+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }    
    }

    @Override
    public List<BarangKecilDTO> selectAllBarang() throws BarangException {
        Statement statement = null;
        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(selectAll);

            while (rs.next()) {
                BarangKecilDTO BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;

    }

    @Override
    public List<BarangKecilDTO>  getBarangbyNama(String nama) throws BarangException {

        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        PreparedStatement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getByNama);
            statement.setString(1, "%"+nama+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<BarangKecilDTO>  getBarangbyTipe(String tipe) throws BarangException {

        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        PreparedStatement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBytipe);
            statement.setString(1, "%"+tipe+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<BarangKecilDTO> getBarangbyMerek(String merek) throws BarangException {

        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        PreparedStatement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBymerek);
            statement.setString(1, "%"+merek+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<BarangKecilDTO> getBarangbyHarga(String Harga) throws BarangException {

        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        PreparedStatement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getByharga);
            statement.setString(1, "%"+Harga+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<BarangKecilDTO> getBarangbySatuan(String satuan) throws BarangException {

        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        PreparedStatement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBysatuan);
            statement.setString(1, "%"+satuan+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<BarangKecilDTO> getBarangbyStok(String stok) throws BarangException {

        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        PreparedStatement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBystok);
            statement.setString(1, "%"+stok+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<BarangKecilDTO> getBarangbyStok_min(String stok_min) throws BarangException {

        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        PreparedStatement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBystok_min);
            statement.setString(1, "%"+stok_min+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<BarangKecilDTO> getBarangbySupplier(String supplier) throws BarangException {

        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        PreparedStatement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBysupplier);
            statement.setString(1, "%"+supplier+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<BarangKecilDTO> getBarangbyKeterangan(String keterangan) throws BarangException {

        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        PreparedStatement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getByket);
            statement.setString(1, "%"+keterangan+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<BarangKecilDTO> sortBarangbyId() throws BarangException {
        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        Statement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortById);
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<BarangKecilDTO> sortBarangbyBarcode() throws BarangException {
        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        Statement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortByBarcode);
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        return list;
    }

    @Override
    public List<BarangKecilDTO> sortBarangbyNama() throws BarangException {

        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        Statement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortByNama);
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    @Override
    public List<BarangKecilDTO> sortBarangbyTipe() throws BarangException {

        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        Statement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortBytipe);
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    @Override
    public List<BarangKecilDTO> sortBarangbyMerek() throws BarangException {

        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        Statement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortBymerek);
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    @Override
    public List<BarangKecilDTO> sortBarangbyHarga() throws BarangException {

        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        Statement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortByharga);
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    @Override
    public List<BarangKecilDTO> sortBarangbySatuan() throws BarangException {

        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        Statement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortBysatuan);
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    @Override
    public List<BarangKecilDTO> sortBarangbyStok() throws BarangException {

        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        Statement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortBystok);
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    @Override
    public List<BarangKecilDTO> sortBarangbyStok_min() throws BarangException {

        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        Statement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortBystok_min);
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    @Override
    public List<BarangKecilDTO> sortBarangbySupplier() throws BarangException {

        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        Statement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortBysupplier);
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }

    @Override
    public List<BarangKecilDTO> sortBarangbyKeterangan() throws BarangException {

        List<BarangKecilDTO> list = new ArrayList<BarangKecilDTO>();
        Statement statement = null;
        BarangKecilDTO BarangKecilDTO = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();            
            
            ResultSet rs = statement.executeQuery(sortByket);
            while (rs.next()) {                
                BarangKecilDTO = new BarangKecilDTO();
                BarangKecilDTO.setIdBarang(rs.getString("idbarang"));
                BarangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
                BarangKecilDTO.setNamaBarang(rs.getString("namabarang"));
                BarangKecilDTO.setTipe(rs.getString("tipe"));
                BarangKecilDTO.setMerek(rs.getString("MerekDTO"));
                BarangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
                BarangKecilDTO.setEceran(rs.getInt("eceran"));
                BarangKecilDTO.setGrosir(rs.getInt("grosir"));
                BarangKecilDTO.setSatuan(rs.getString("satuan"));
                BarangKecilDTO.setStok(rs.getInt("stok"));
                BarangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
                BarangKecilDTO.setSupplier(rs.getString("supplier"));
                BarangKecilDTO.setKeterangan(rs.getString("keterangan"));
                BarangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
                BarangKecilDTO.setKategori(rs.getString("kategori"));
                list.add(BarangKecilDTO);
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
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
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ReminderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
    }
    
}

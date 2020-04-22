/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterdata.barangbesar.service.impl;

import Sinarelektronikapp.masterdata.barangbesar.entity.barang;
import Sinarelektronikapp.masterdata.barangbesar.error.BarangException;
import Sinarelektronikapp.masterdata.barangbesar.service.BarangDao;
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

/**
 *
 * @author Fauzi
 */
public class BarangDaoImpl implements BarangDao{

    public BarangDaoImpl(Connection connection) {
        this.connection = connection;
    }
        
    private Connection connection;    

    final String updateBarang = "UPDATE barangbesar SET idbarang = ?, idbarcode = ?, namabarang = ?, tipe = ?, merek = ?, modal = ?, grosir=?, grosir2=?, eceran=?, stok = ?, stok_minimum = ?, supplier = ?, keterangan = ?, gambar=? garansi=?, lamagaransi=? WHERE idbarang = ?";

    final String deleteBarang = "DELETE FROM barangbesar where idbarang = ?";

    final String getById = "SELECT * FROM barangbesar where idbarang like ?";

    final String getByBarcode = "SELECT * FROM barangbesar where idbarcode like ?";
    
    final String getByNama = "SELECT * FROM barangbesar where namabarang like ?";
    
    final String getBytipe = "SELECT * FROM barangbesar where tipe like ?";
    
    final String getBymerek = "SELECT * FROM barangbesar where merek like ?";
    
    final String getByeceran = "SELECT * FROM barangbesar where eceran like ?";
    
    final String getBystok = "SELECT * FROM barangbesar where stok<=?";
    
    final String getBystok_min = "SELECT * FROM barangbesar where stok_minimum like = ?";
    
    final String getBysupplier = "SELECT * FROM barangbesar where supplier like ?";
    
    final String getByket = "SELECT * FROM barangbesar where keterangan like ?";
    
    final String sortById = "SELECT * FROM barangbesar order by idbarang";

    final String sortByBarcode = "SELECT * FROM barangbesar order by idbarcode";
    
    final String sortByNama = "SELECT * FROM barangbesar order by namabarang";
    
    final String sortBytipe = "SELECT * FROM barangbesar order by tipe";
    
    final String sortBymerek = "SELECT * FROM barangbesar order by merek";
    
    final String sortByeceran = "SELECT * FROM barangbesar order by eceran";        
    
    final String sortBystok = "SELECT * FROM barangbesar order by stok";
    
    final String sortBystok_min = "SELECT * FROM barangbesar order by stok_minimum";
    
    final String sortBysupplier = "SELECT * FROM barangbesar order by supplier";
    
    final String sortByket = "SELECT * FROM barangbesar order by keterangan";

    final String selectAll = "SELECT * FROM barangbesar order by namabarang limit 100";
    
    final String getLastId = "SELECT COUNT(idbarang) total from barangbesar";
    
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
            statement.setInt(8, barang.getGrosir2());
            statement.setInt(9, barang.getEceran());
            statement.setInt(10, barang.getStok());
            statement.setInt(11, barang.getStok_min());
            statement.setString(12, barang.getSupplier());
            statement.setString(13, barang.getKeterangan());            
            try {
                statement.setBlob(14, new FileInputStream(barang.getGambar()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            statement.setString(15, barang.getGaransi());
            statement.setString(16, String.valueOf(barang.getLamaGaransi()));
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Update barang besar besar gagal karena "+exception, "Perhatian", JOptionPane.WARNING_MESSAGE);
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
    public void deleteBarang(String idBarang) throws BarangException {
        PreparedStatement statement = null;
        try{
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(deleteBarang);
            statement.setString(1, idBarang);
            statement.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Delete barangkecil tidak berhasil karena = "+exception, "perhatian", JOptionPane.WARNING_MESSAGE);
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
        List<barang> list = new ArrayList<>();
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
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setEceran(rs.getInt("eceran"));                
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));                    
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));                
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
    public List<barang> getBarangbyNama(String nama) throws BarangException {
        PreparedStatement statement = null;        
        List<barang> list = new ArrayList<>();
        barang barang = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getBytipe);
            statement.setString(1, "%"+nama+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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
    public List<barang> getBarangbyTipe(String tipe) throws BarangException {
        PreparedStatement statement = null;        
        List<barang> list = new ArrayList<>();
        barang barang = null;
        try {
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
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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
    public List<barang> getBarangbyMerek(String merek) throws BarangException {
        PreparedStatement statement = null;        
        List<barang> list = new ArrayList<>();
        barang barang = null;
        try {
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
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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
    public List<barang> getBarangbyHarga(String Harga) throws BarangException {
        PreparedStatement statement = null;        
        List<barang> list = new ArrayList<>();
        barang barang = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getByeceran);
            statement.setString(1, "%"+Harga+"%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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
    public List<barang> getBarangbyStok(String stok) throws BarangException {
        PreparedStatement statement = null;        
        List<barang> list = new ArrayList<>();
        barang barang = null;
        try {
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
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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
    public List<barang> getBarangbyStok_min(String stok_min) throws BarangException {
        PreparedStatement statement = null;        
        List<barang> list = new ArrayList<>();
        barang barang = null;
        try {
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
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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
    public List<barang> getBarangbySupplier(String supplier) throws BarangException {
        PreparedStatement statement = null;        
        List<barang> list = new ArrayList<>();
        barang barang = null;
        try {
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
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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
    public List<barang> getBarangbyKeterangan(String keterangan) throws BarangException {
        PreparedStatement statement = null;        
        List<barang> list = new ArrayList<>();
        barang barang = null;
        try {
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
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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
    public List<barang> sortBarangbyId() throws BarangException {
        Statement statement = null;
        List<barang> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sortById);

            while (rs.next()) {
                barang barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));                
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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
    public List<barang> sortBarangbyBarcode() throws BarangException {
        Statement statement = null;
        List<barang> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sortByBarcode);

            while (rs.next()) {
                barang barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));                
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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
    public List<barang> sortBarangbyNama() throws BarangException {
        Statement statement = null;
        List<barang> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sortByNama);

            while (rs.next()) {
                barang barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));                
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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
    public List<barang> sortBarangbyTipe() throws BarangException {
        Statement statement = null;
        List<barang> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sortBytipe);

            while (rs.next()) {
                barang barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));                
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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
    public List<barang> sortBarangbyMerek() throws BarangException {
        Statement statement = null;
        List<barang> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sortBymerek);

            while (rs.next()) {
                barang barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));                
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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
    public List<barang> sortBarangbyHarga() throws BarangException {
        Statement statement = null;
        List<barang> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sortByeceran);

            while (rs.next()) {
                barang barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));                
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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
    public List<barang> sortBarangbyStok() throws BarangException {
        Statement statement = null;
        List<barang> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sortBystok);

            while (rs.next()) {
                barang barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));                
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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
    public List<barang> sortBarangbyStok_min() throws BarangException {
        Statement statement = null;
        List<barang> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sortBystok_min);

            while (rs.next()) {
                barang barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));                
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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
    public List<barang> sortBarangbySupplier() throws BarangException {
        Statement statement = null;
        List<barang> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sortBysupplier);

            while (rs.next()) {
                barang barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));                
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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
    public List<barang> sortBarangbyKeterangan() throws BarangException {
        Statement statement = null;
        List<barang> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sortByket);

            while (rs.next()) {
                barang barang = new barang();
                barang.setIdBarang(rs.getString("idbarang"));
                barang.setIdBarcode(rs.getString("idbarcode"));
                barang.setNamaBarang(rs.getString("namabarang"));
                barang.setTipe(rs.getString("tipe"));
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));                
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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
    public List<barang> selectAllBarang() throws BarangException {
        Statement statement = null;
        List<barang> list = new ArrayList<>();
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
                barang.setMerek(rs.getString("merek"));
                barang.setModal(rs.getInt("modal"));
                barang.setEceran(rs.getInt("eceran"));
                barang.setGrosir(rs.getInt("grosir"));
                barang.setGrosir2(rs.getInt("grosir2"));
                barang.setStok(rs.getInt("stok"));
                barang.setStok_min(rs.getInt("stok_minimum"));
                barang.setSupplier(rs.getString("supplier"));
                barang.setKeterangan(rs.getString("keterangan"));
                barang.setGambarHasil(rs.getBlob("gambar"));
                barang.setGaransi(rs.getString("garansi"));
                barang.setLamaGaransi(rs.getInt("lamagaransi"));                
                list.add(barang);
            }
            connection.commit();
            
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            //throw new BarangException(exception.getMessage());
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

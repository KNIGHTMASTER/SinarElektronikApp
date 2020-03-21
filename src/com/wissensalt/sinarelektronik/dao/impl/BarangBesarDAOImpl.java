package com.wissensalt.sinarelektronik.dao.impl;

import com.wissensalt.sinarelektronik.dao.ABaseDAO;
import com.wissensalt.sinarelektronik.dao.BarangBesarDAO;
import com.wissensalt.sinarelektronik.dto.BarangBesarDTO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class BarangBesarDAOImpl extends ABaseDAO<BarangBesarDTO> implements BarangBesarDAO {

    public BarangBesarDAOImpl() {        
    }
    
    private final String UPDATE_BARANG = "UPDATE barangbesar SET idbarang = ?, idbarcode = ?, namabarang = ?, tipe = ?, merek = ?, modal = ?, grosir=?, eceran=?, stok = ?, stok_minimum = ?, supplier = ?, keterangan = ?, gambar=? garansi=?, lamagaransi=? WHERE idbarang = ?";
    private final String DELETE_BARANG = "DELETE FROM barangbesar where idbarang = ?";
    private final String GET_BY_ID = "SELECT * FROM barangbesar where idbarang like ?";
    private final String GET_BY_BARCODE = "SELECT * FROM barangbesar where idbarcode like ?";
    private final String GET_BY_NAMA = "SELECT * FROM barangbesar where namabarang like ?";
    private final String GET_BY_TIPE = "SELECT * FROM barangbesar where tipe like ?";
    private final String GET_BY_MEREK = "SELECT * FROM barangbesar where MerekDTO like ?";
    private final String GET_BY_ECERAN = "SELECT * FROM barangbesar where eceran like ?";
    private final String GET_BY_STOK = "SELECT * FROM barangbesar where stok<=?";
    private final String GET_BY_STOK_MIN = "SELECT * FROM barangbesar where stok_minimum like = ?";
    private final String GET_BY_SUPPLIER = "SELECT * FROM barangbesar where supplier like ?";
    private final String GET_BY_KETERANGAN = "SELECT * FROM barangbesar where keterangan like ?";
    private final String SORT_BY_ID = "SELECT * FROM barangbesar order by idbarang";
    private final String SORT_BY_BARCODE = "SELECT * FROM barangbesar order by idbarcode";
    private final String SORT_BY_NAMA = "SELECT * FROM barangbesar order by namabarang";
    private final String SORT_BY_TIPE = "SELECT * FROM barangbesar order by tipe";
    private final String SORT_BY_MEREK = "SELECT * FROM barangbesar order by MerekDTO";
    private final String SORT_BY_ECERAN = "SELECT * FROM barangbesar order by eceran";
    private final String SORT_BY_STOK = "SELECT * FROM barangbesar order by stok";
    private final String SORT_BY_STOK_MIN = "SELECT * FROM barangbesar order by stok_minimum";
    private final String SORT_BY_SUPPLIER = "SELECT * FROM barangbesar order by supplier";
    private final String SORT_BY_KETERANGAN = "SELECT * FROM barangbesar order by keterangan";
    private final String SELECT_ALL = "SELECT * FROM barangbesar order by namabarang";
    private final String GET_LAST_ID = "SELECT COUNT(idbarang) total from barangbesar";    

    @Override
    public PreparedStatement updateDetail(BarangBesarDTO dto, PreparedStatement ps) {
        try {
            ps = connection.prepareStatement(UPDATE_BARANG);
            ps.setString(1, dto.getIdBarang());
            ps.setString(2, dto.getIdBarcode());
            ps.setString(3, dto.getNamaBarang());
            ps.setString(4, dto.getTipe());
            ps.setString(5, dto.getMerek());
            ps.setInt(6, dto.getModal());
            ps.setInt(7, dto.getGrosir());
            ps.setInt(8, dto.getEceran());
            ps.setInt(9, dto.getStok());
            ps.setInt(10, dto.getStok_min());
            ps.setString(11, dto.getSupplier());
            ps.setString(12, dto.getKeterangan());
            try {
                ps.setBlob(13, new FileInputStream(dto.getGambar()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BarangBesarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            ps.setString(13, dto.getGaransi());
            ps.setString(14, String.valueOf(dto.getLamaGaransi()));
        } catch (SQLException ex) {
            Logger.getLogger(BarangBesarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }      
        
        return ps;
    }           

    @Override
    public String getDeleteQueryByString() {
        return DELETE_BARANG;
    }

    @Override
    public List<BarangBesarDTO> getBarangbyId(String id)  {
        return findByFieldLike(id, GET_BY_ID);
    }
    
    @Override
    public BarangBesarDTO getDTOFromResultSet(ResultSet rs) {
        try {
            BarangBesarDTO barangBesarDTO = new BarangBesarDTO();
            barangBesarDTO.setIdBarang(rs.getString("idbarang"));
            barangBesarDTO.setIdBarcode(rs.getString("idbarcode"));
            barangBesarDTO.setNamaBarang(rs.getString("namabarang"));
            barangBesarDTO.setTipe(rs.getString("tipe"));
            barangBesarDTO.setMerek(rs.getString("MerekDTO"));
            barangBesarDTO.setModal(rs.getInt("modal"));
            barangBesarDTO.setGrosir(rs.getInt("grosir"));
            barangBesarDTO.setEceran(rs.getInt("eceran"));
            barangBesarDTO.setStok(rs.getInt("stok"));
            barangBesarDTO.setStok_min(rs.getInt("stok_minimum"));
            barangBesarDTO.setSupplier(rs.getString("supplier"));
            barangBesarDTO.setKeterangan(rs.getString("keterangan"));
            barangBesarDTO.setGambarHasil(rs.getBlob("gambar"));
            barangBesarDTO.setGaransi(rs.getString("garansi"));
            barangBesarDTO.setLamaGaransi(rs.getInt("lamagaransi"));
            return barangBesarDTO;
        } catch (SQLException ex) {
            Logger.getLogger(BarangBesarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }   

    @Override
    public List<BarangBesarDTO> getBarangbyNama(String nama)  {
        return findByFieldLike(nama, GET_BY_NAMA);
    }

    @Override
    public List<BarangBesarDTO> getBarangbyTipe(String tipe)  {
        return findByFieldLike(tipe, GET_BY_TIPE);
    }

    @Override
    public List<BarangBesarDTO> getBarangbyMerek(String merek)  {
        return findByFieldLike(merek, GET_BY_MEREK);
    }

    @Override
    public List<BarangBesarDTO> getBarangbyHarga(String harga)  {
        return findByFieldLike(harga, GET_BY_ECERAN);
    }

    @Override
    public List<BarangBesarDTO> getBarangbyStok(String stok)  {
        return findByFieldLike(stok, GET_BY_STOK);
    }

    @Override
    public List<BarangBesarDTO> getBarangbyStokMin(String stokMin)  {
        return findByFieldLike(stokMin, GET_BY_STOK_MIN);
    }

    @Override
    public List<BarangBesarDTO> getBarangbySupplier(String supplier)  {
        return findByFieldLike(supplier, GET_BY_SUPPLIER);
    }

    @Override
    public List<BarangBesarDTO> getBarangbyKeterangan(String keterangan)  {
        return findByFieldLike(keterangan, GET_BY_KETERANGAN);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbyId()  {
        return findAndSortByField(SORT_BY_ID);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbyBarcode()  {
        return findAndSortByField(SORT_BY_BARCODE);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbyNama()  {
        return findAndSortByField(SORT_BY_NAMA);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbyTipe()  {
        return findAndSortByField(SORT_BY_TIPE);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbyMerek()  {
        return findAndSortByField(SORT_BY_MEREK);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbyHarga()  {
        return findAndSortByField(SORT_BY_ECERAN);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbyStok()  {
        return findAndSortByField(SORT_BY_STOK);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbyStokMin()  {
        return findAndSortByField(SORT_BY_STOK_MIN);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbySupplier()  {
        return findAndSortByField(SORT_BY_SUPPLIER);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbyKeterangan()  {
        return findAndSortByField(SORT_BY_KETERANGAN);
    }

    @Override
    public List<BarangBesarDTO> selectAllBarang()  {
        return findAndSortByField(SELECT_ALL);
    }

    @Override
    public int getBarangLastId()  {
        return findLastIdByField("total", GET_LAST_ID);
    }    
}

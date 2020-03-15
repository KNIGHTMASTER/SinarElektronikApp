package com.wissensalt.sinarelektronik.dao.impl;

import com.wissensalt.sinarelektronik.dao.ABaseDAO;
import com.wissensalt.sinarelektronik.dao.BarangTokoDAO;
import com.wissensalt.sinarelektronik.masterdata.barangtoko.entity.BarangTokoDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class BarangTokoDAOImpl extends ABaseDAO<BarangTokoDTO>implements BarangTokoDAO {

    private final  String UPDATE_BARANG = "UPDATE barangtoko SET idbarang = ?, idbarcode = ?, namabarang = ?, tipe = ?, merek = ?, modal = ?, grosir=?, eceran=?, stok = ?, stok_minimum = ?, supplier = ?, keterangan = ?, gambar=? garansi=?, lamagaransi=? WHERE idbarang = ?";
    private final  String DELETE_BARANG = "DELETE FROM barangtoko where idbarang = ?";
    private final  String GET_BY_ID = "SELECT * FROM barangtoko where idbarang like ?";
    private final  String GET_BY_BARCODE = "SELECT * FROM barangtoko where idbarcode like ?";
    private final  String GET_BY_NAMA = "SELECT * FROM barangtoko where namabarang like ?";
    private final  String GET_BY_TYPE = "SELECT * FROM barangtoko where tipe like ?";
    private final  String GET_BY_MEREK = "SELECT * FROM barangtoko where MerekDTO like ?";
    private final  String GET_BY_ECERAN = "SELECT * FROM barangtoko where eceran like ?";
    private final  String GET_BY_STOK = "SELECT * FROM barangtoko where stok<=?";
    private final  String GET_BY_STOK_MIN = "SELECT * FROM barangtoko where stok_minimum like = ?";
    private final  String GET_BY_SUPPLIER = "SELECT * FROM barangtoko where supplier like ?";
    private final  String GET_BY_KET = "SELECT * FROM barangtoko where keterangan like ?";
    private final  String SORT_BY_ID = "SELECT * FROM barangtoko order by idbarang";
    private final  String SORT_BY_BARCODE = "SELECT * FROM barangtoko order by idbarcode";
    private final  String SORT_BY_NAMA = "SELECT * FROM barangtoko order by namabarang";
    private final  String SORT_BY_TIPE = "SELECT * FROM barangtoko order by tipe";
    private final  String SORT_BY_MEREK = "SELECT * FROM barangtoko order by merek";
    private final  String SORT_BY_ECERAN = "SELECT * FROM barangtoko order by eceran";
    private final  String SORT_BY_STOK = "SELECT * FROM barangtoko order by stok";
    private final  String SORT_BY_STOK_MIN = "SELECT * FROM barangtoko order by stok_minimum";
    private final  String SORT_BY_SUPPLIER = "SELECT * FROM barangtoko order by supplier";
    private final  String SORT_BY_KET = "SELECT * FROM barangtoko order by keterangan";
    private final  String SELECT_ALL = "SELECT * FROM barangtoko order by namabarang";
    private final  String GET_LAST_ID = "SELECT COUNT(idbarang) total from barangtoko";

    @Override
    public PreparedStatement updateDetail(BarangTokoDTO dto, PreparedStatement ps) {
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
                Logger.getLogger(BarangTokoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            ps.setString(13, dto.getGaransi());
            ps.setString(14, String.valueOf(dto.getLamaGaransi()));            
        } catch (SQLException ex) {
            Logger.getLogger(BarangTokoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ps;
    }

    @Override
    public String getDeleteQueryByString() {
        return DELETE_BARANG;
    }

    @Override
    public BarangTokoDTO getDTOFromResultSet(ResultSet rs) {
        BarangTokoDTO barang = null;
        try {
            barang = new BarangTokoDTO();
            barang.setIdBarang(rs.getString("idbarang"));
            barang.setIdBarcode(rs.getString("idbarcode"));
            barang.setNamaBarang(rs.getString("namabarang"));
            barang.setTipe(rs.getString("tipe"));
            barang.setMerek(rs.getString("MerekDTO"));
            barang.setModal(rs.getInt("modal"));
            barang.setGrosir(rs.getInt("grosir"));
            barang.setEceran(rs.getInt("eceran"));
            barang.setStok(rs.getInt("stok"));
            barang.setStok_min(rs.getInt("stok_minimum"));
            barang.setSupplier(rs.getString("supplier"));
            barang.setKeterangan(rs.getString("keterangan"));
            barang.setGambarHasil(rs.getBlob("gambar"));
            barang.setGaransi(rs.getString("garansi"));  
            barang.setLamaGaransi(rs.getInt("lamagaransi"));
        } catch (SQLException ex) {
            Logger.getLogger(BarangTokoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return barang;
    }
    
    

    @Override
    public List<BarangTokoDTO> getBarangbyId(String idBarang) {
        return findByFieldLike(idBarang, GET_BY_ID);
    }

    @Override
    public List<BarangTokoDTO> getBarangbyNama(String nama) {
        return findByFieldLike(nama, GET_BY_NAMA);
    }

    @Override
    public List<BarangTokoDTO> getBarangbyTipe(String tipe) {
        return findByFieldLike(tipe, GET_BY_TYPE);
    }

    @Override
    public List<BarangTokoDTO> getBarangbyMerek(String merek) {
        return findByFieldLike(merek, GET_BY_MEREK);
    }

    @Override
    public List<BarangTokoDTO> getBarangbyHarga(String harga) {
        return findByFieldLike(harga, GET_BY_ECERAN);
    }

    @Override
    public List<BarangTokoDTO> getBarangbyStok(String stok) {
        return findByFieldLike(stok, GET_BY_STOK);
    }

    @Override
    public List<BarangTokoDTO> getBarangbyStokMin(String stokMin) {
        return findByFieldLike(stokMin, GET_BY_STOK_MIN);
    }

    @Override
    public List<BarangTokoDTO> getBarangbySupplier(String supplier) {
        return findByFieldLike(supplier, GET_BY_SUPPLIER);
    }

    @Override
    public List<BarangTokoDTO> getBarangbyKeterangan(String keterangan) {
        return findByFieldLike(keterangan, GET_BY_KET);
    }

    @Override
    public List<BarangTokoDTO> sortBarangbyId() {
        return findAndSortByField(SORT_BY_ID);
    }

    @Override
    public List<BarangTokoDTO> sortBarangbyBarcode() {
        return findAndSortByField(SORT_BY_BARCODE);
    }

    @Override
    public List<BarangTokoDTO> sortBarangbyNama() {
        return findAndSortByField(SORT_BY_NAMA);
    }

    @Override
    public List<BarangTokoDTO> sortBarangbyTipe() {
        return findAndSortByField(SORT_BY_TIPE);
    }

    @Override
    public List<BarangTokoDTO> sortBarangbyMerek() {
        return findAndSortByField(SORT_BY_MEREK);
    }

    @Override
    public List<BarangTokoDTO> sortBarangbyHarga() {
        return findAndSortByField(SORT_BY_ECERAN);
    }

    @Override
    public List<BarangTokoDTO> sortBarangbyStok() {
        return findAndSortByField(SORT_BY_STOK);
    }

    @Override
    public List<BarangTokoDTO> sortBarangbyStokMin() {
        return findAndSortByField(SORT_BY_STOK_MIN);
    }

    @Override
    public List<BarangTokoDTO> sortBarangbySupplier() {
        return findAndSortByField(SORT_BY_SUPPLIER);
    }

    @Override
    public List<BarangTokoDTO> sortBarangbyKeterangan() {
        return findAndSortByField(SORT_BY_KET);
    }

    @Override
    public List<BarangTokoDTO> selectAllBarang() {
        return findAndSortByField(SELECT_ALL);
    }

    @Override
    public int getBarangLastId() {
        return findLastIdByField("total", GET_LAST_ID);
    }
}

package com.wissensalt.sinarelektronik.dao.impl;

import com.wissensalt.sinarelektronik.dao.ABaseDAO;
import com.wissensalt.sinarelektronik.dao.BarangKecilDAO;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.entity.BarangKecilDTO;

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
public class BarangKecilDAOImpl extends ABaseDAO<BarangKecilDTO> implements BarangKecilDAO {

    private final static String UPDATE_BARANG = "UPDATE barang SET idbarang = ?, idbarcode = ?, namabarang = ?, tipe = ?, merek = ?, hargamodal = ?, eceran=?, grosir=?, grosir2, satuan = ?, stok = ?, stok_minimum = ?, supplier = ?, keterangan = ?, gambar=? garansi=?, lamagaransi=? kategori = ? WHERE idbarang = ?";
    private final static String DELETE_BARANG = "DELETE FROM barang where idbarang = ?";
    private final static String GET_BY_ID = "SELECT * FROM barang where idbarang like ?";
    private final static String GET_BY_BARCODE = "SELECT * FROM barang where idbarcode like ?";
    private final static String GET_BY_NAMA = "SELECT * FROM barang where namabarang like ?";
    private final static String GET_BYTIPE = "SELECT * FROM barang where tipe like ?";
    private final static String GET_BYMEREK = "SELECT * FROM barang where merek like ?";
    private final static String GET_BYHARGA = "SELECT * FROM barang where harga like ?";
    private final static String GET_BYSATUAN = "SELECT * FROM barang where satuan like ?";
    private final static String GET_BYSTOK = "SELECT * FROM barang where stok<=?";
    private final static String GET_BYSTOK_MIN = "SELECT * FROM barang where stok_minimum = ?";
    private final static String GET_BYSUPPLIER = "SELECT * FROM barang where supplier like ?";
    private final static String GET_BYKET = "SELECT * FROM barang where keterangan like ?";
    private final static String SORT_BY_ID = "SELECT * FROM barang order by idbarang";
    private final static String SORT_BY_BARCODE = "SELECT * FROM barang order by idbarcode";
    private final static String SORT_BY_NAMA = "SELECT * FROM barang order by namabarang";
    private final static String SORT_BYTIPE = "SELECT * FROM barang order by tipe";
    private final static String SORT_BYMEREK = "SELECT * FROM barang order by merek";
    private final static String SORT_BYHARGA = "SELECT * FROM barang order by harga";
    private final static String SORT_BYSATUAN = "SELECT * FROM barang order by satuan";
    private final static String SORT_BYSTOK = "SELECT * FROM barang order by stok";
    private final static String SORT_BYSTOK_MIN = "SELECT * FROM barang order by stok_minimum";
    private final static String SORT_BYSUPPLIER = "SELECT * FROM barang order by supplier";
    private final static String SORT_BYKET = "SELECT * FROM barang order by keterangan";
    private final static String SELECT_ALL = "SELECT * FROM barang order by namabarang";
    private final static String GET_LAST_ID = "SELECT COUNT(idbarang) total from barang";

    @Override
    public PreparedStatement updateDetail(BarangKecilDTO dto, PreparedStatement ps) {
        try {
            ps = connection.prepareStatement(UPDATE_BARANG);
            ps.setString(1, dto.getIdBarang());
            ps.setString(2, dto.getIdBarcode());
            ps.setString(3, dto.getNamaBarang());
            ps.setString(4, dto.getTipe());
            ps.setString(5, dto.getMerek());
            dto.setHargamodal(dto.getHargamodal());
            dto.setEceran(dto.getEceran());
            dto.setGrosir(dto.getGrosir());
            dto.setGrosir2(dto.getGrosir2());
            ps.setString(7, dto.getSatuan());
            ps.setInt(8, dto.getStok());
            ps.setInt(9, dto.getStokMinimum());
            ps.setString(10, dto.getSupplier());
            ps.setString(11, dto.getKeterangan());
            ps.setString(12, dto.getKategori());
            try {
                ps.setBlob(13, new FileInputStream(dto.getGambar()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BarangKecilDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            ps.setString(14, dto.getGaransi());
            ps.setString(15, String.valueOf(dto.getLamaGaransi()));
        } catch (SQLException e) {
            Logger.getLogger(BarangKecilDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return ps;
    }

    @Override
    public String getDeleteQueryByString() {
        return DELETE_BARANG;
    }

    @Override
    public BarangKecilDTO getDTOFromResultSet(ResultSet rs) {
        BarangKecilDTO barangKecilDTO = new BarangKecilDTO();
        try {
            barangKecilDTO.setIdBarang(rs.getString("idbarang"));
            barangKecilDTO.setIdBarcode(rs.getString("idbarcode"));
            barangKecilDTO.setNamaBarang(rs.getString("namabarang"));
            barangKecilDTO.setTipe(rs.getString("tipe"));
            barangKecilDTO.setMerek(rs.getString("MerekDTO"));
            barangKecilDTO.setHargamodal(rs.getInt("hargamodal"));
            barangKecilDTO.setEceran(rs.getInt("eceran"));
            barangKecilDTO.setGrosir(rs.getInt("grosir"));
            barangKecilDTO.setGrosir2(rs.getInt("grosir2"));
            barangKecilDTO.setSatuan(rs.getString("satuan"));
            barangKecilDTO.setStok(rs.getInt("stok"));
            barangKecilDTO.setStokMinimum(rs.getInt("stok_minimum"));
            barangKecilDTO.setSupplier(rs.getString("supplier"));
            barangKecilDTO.setKeterangan(rs.getString("keterangan"));
            barangKecilDTO.setGambarHasil(rs.getBlob("gambar"));
            barangKecilDTO.setKategori(rs.getString("kategori"));
        } catch (SQLException e) {
            Logger.getLogger(BarangKecilDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return barangKecilDTO;
    }

    @Override
    public List<BarangKecilDTO> getBarangbyId(String idBarang) {
        return findByFieldLike(idBarang, GET_BY_ID);
    }
    

    @Override
    public List<BarangKecilDTO> getBarangbyBarcode(String idBarcode) {
        return findByFieldLike(idBarcode, GET_BY_BARCODE);
    }

    @Override
    public List<BarangKecilDTO> selectAllBarang() {
        return findAndSortByField(SELECT_ALL);

    }

    @Override
    public List<BarangKecilDTO>  getBarangbyNama(String nama) {
        return findByFieldLike(nama, GET_BY_NAMA);
    }

    @Override
    public List<BarangKecilDTO>  getBarangbyTipe(String tipe) {
        return findByFieldLike(tipe, GET_BYTIPE);
    }

    @Override
    public List<BarangKecilDTO> getBarangbyMerek(String merek) {
        return findByFieldLike(merek, GET_BYMEREK);
    }

    @Override
    public List<BarangKecilDTO> getBarangbyHarga(String harga){
        return findByFieldLike(harga, GET_BYHARGA);
    }

    @Override
    public List<BarangKecilDTO> getBarangbySatuan(String satuan) {
        return findByFieldLike(satuan, GET_BYSATUAN);
    }

    @Override
    public List<BarangKecilDTO> getBarangbyStok(String stok) {
        return findByFieldLike(stok, GET_BYSTOK);
    }

    @Override
    public List<BarangKecilDTO> getBarangbyStokMin(String stokMin) {
        return findByFieldLike(stokMin, GET_BYSTOK_MIN);
    }

    @Override
    public List<BarangKecilDTO> getBarangbySupplier(String supplier) {
        return findByFieldLike(supplier, GET_BYSUPPLIER);
    }

    @Override
    public List<BarangKecilDTO> getBarangbyKeterangan(String keterangan) {
        return findByFieldLike(keterangan, GET_BYKET);
    }

    @Override
    public List<BarangKecilDTO> sortBarangbyId() {
        return findAndSortByField(SORT_BY_ID);
    }

    @Override
    public List<BarangKecilDTO> sortBarangbyBarcode() {
        return findAndSortByField(SORT_BY_BARCODE);
    }

    @Override
    public List<BarangKecilDTO> sortBarangbyNama() {
        return findAndSortByField(SORT_BY_NAMA);
    }

    @Override
    public List<BarangKecilDTO> sortBarangbyTipe() {
        return findAndSortByField(SORT_BYTIPE);
    }

    @Override
    public List<BarangKecilDTO> sortBarangbyMerek() {
        return findAndSortByField(SORT_BYMEREK);
    }

    @Override
    public List<BarangKecilDTO> sortBarangbyHarga() {
        return findAndSortByField(SORT_BYHARGA);
    }

    @Override
    public List<BarangKecilDTO> sortBarangbySatuan() {
        return findAndSortByField(SORT_BYSATUAN);
    }

    @Override
    public List<BarangKecilDTO> sortBarangbyStok() {
        return findAndSortByField(SORT_BYSTOK);
    }

    @Override
    public List<BarangKecilDTO> sortBarangbyStokMin() {
        return findAndSortByField(SORT_BYSTOK_MIN);
    }

    @Override
    public List<BarangKecilDTO> sortBarangbySupplier() {
        return findAndSortByField(SORT_BYSUPPLIER);
    }

    @Override
    public List<BarangKecilDTO> sortBarangbyKeterangan() {
        return findAndSortByField(SORT_BYKET);
    }

    @Override
    public int getBarangLastId() {
        return findLastIdByField("total", GET_LAST_ID);
    }
}

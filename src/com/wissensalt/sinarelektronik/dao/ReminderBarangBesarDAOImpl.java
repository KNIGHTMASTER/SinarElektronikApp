package com.wissensalt.sinarelektronik.dao;

import com.wissensalt.sinarelektronik.dto.BarangBesarDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReminderBarangBesarDAOImpl extends ABaseDAO<BarangBesarDTO> implements ReminderBarangBesarDAO {

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


    @Override
    public BarangBesarDTO getDTOFromResultSet(ResultSet rs) {
        BarangBesarDTO barangBesarDTO = new BarangBesarDTO();
        
        try {            
            barangBesarDTO.setIdBarang(rs.getString("idbarang"));
            barangBesarDTO.setIdBarcode(rs.getString("idbarcode"));
            barangBesarDTO.setNamaBarang(rs.getString("namabarang"));
            barangBesarDTO.setTipe(rs.getString("tipe"));
            barangBesarDTO.setMerek(rs.getString("merek"));
            barangBesarDTO.setModal(rs.getInt("modal"));
            barangBesarDTO.setEceran(rs.getInt("eceran"));
            barangBesarDTO.setGrosir(rs.getInt("grosir"));
            barangBesarDTO.setStok(rs.getInt("stok"));
            barangBesarDTO.setStok_min(rs.getInt("stok_minimum"));
            barangBesarDTO.setSupplier(rs.getString("supplier"));
            barangBesarDTO.setKeterangan(rs.getString("keterangan"));
            barangBesarDTO.setGambarHasil(rs.getBlob("gambar"));
            
        } catch (SQLException ex) {
            Logger.getLogger(ReminderBarangBesarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return barangBesarDTO;
    }

    @Override
    public List<BarangBesarDTO> getBarangbyId(String idBarang) {
        return findByFieldLike(idBarang, getById);
    }
    

    @Override
    public List<BarangBesarDTO> getBarangbyBarcode(String idBarcode)  {
        return findByFieldLike(idBarcode, getByBarcode);
    }

    @Override
    public List<BarangBesarDTO> selectAllBarang()  {
        return findAndSortByField(selectAll);
    }

    @Override
    public List<BarangBesarDTO>  getBarangbyNama(String nama)  {
        return findByFieldLike(nama, getByNama);
    }

    @Override
    public List<BarangBesarDTO>  getBarangbyTipe(String tipe)  {
        return findByFieldLike(tipe, getBytipe);
    }

    @Override
    public List<BarangBesarDTO> getBarangbyMerek(String merek)  {
        return findByFieldLike(merek, getBymerek);
    }

    @Override
    public List<BarangBesarDTO> getBarangbyHarga(String harga)  {
        return findByFieldLike(harga, getByharga);
    }

    @Override
    public List<BarangBesarDTO> getBarangbySatuan(String satuan)  {
        return findByFieldLike(satuan, getBysatuan);
    }

    @Override
    public List<BarangBesarDTO> getBarangbyStok(String stok)  {
        return findByFieldLike(stok, getBystok);
    }

    @Override
    public List<BarangBesarDTO> getBarangbyStokMin(String stokMin)  {
        return findByFieldLike(stokMin, getBystok_min);
    }

    @Override
    public List<BarangBesarDTO> getBarangbySupplier(String supplier)  {
        return findByFieldLike(supplier, getBysupplier);
    }

    @Override
    public List<BarangBesarDTO> getBarangbyKeterangan(String keterangan)  {
        return findByFieldLike(keterangan, getByket);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbyId()  {
        return findAndSortByField(sortById);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbyBarcode()  {
        return findAndSortByField(sortByBarcode);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbyNama()  {
        return findAndSortByField(sortByNama);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbyTipe()  {
        return findAndSortByField(sortBytipe);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbyMerek()  {
        return findAndSortByField(sortBymerek);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbyHarga()  {
        return findAndSortByField(sortByharga);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbySatuan()  {
        return findAndSortByField(sortBysatuan);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbyStok()  {
        return findAndSortByField(sortBystok);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbyStok_min()  {
        return findAndSortByField(sortBystok_min);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbySupplier()  {
        return findAndSortByField(sortBysupplier);
    }

    @Override
    public List<BarangBesarDTO> sortBarangbyKeterangan()  {
        return findAndSortByField(sortByket);
    }  
}

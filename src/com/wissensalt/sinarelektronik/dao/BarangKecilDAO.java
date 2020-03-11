package com.wissensalt.sinarelektronik.dao;

import com.wissensalt.sinarelektronik.masterdata.barangkecil.entity.BarangKecilDTO;

import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface BarangKecilDAO extends IBaseDAO<BarangKecilDTO> {

    List<BarangKecilDTO> getBarangbyId(String idBarang);
    
    List<BarangKecilDTO> getBarangbyBarcode(String idBarcode);
    
    List<BarangKecilDTO> getBarangbyNama(String nama);
    
    List<BarangKecilDTO> getBarangbyTipe(String tipe);
    
    List<BarangKecilDTO> getBarangbyMerek(String merek);
    
    List<BarangKecilDTO> getBarangbyHarga(String harga);
    
    List<BarangKecilDTO> getBarangbySatuan(String satuan);
    
    List<BarangKecilDTO> getBarangbyStok(String stok);
    
    List<BarangKecilDTO> getBarangbyStokMin(String stokMin);
    
    List<BarangKecilDTO> getBarangbySupplier(String supplier);
    
    List<BarangKecilDTO> getBarangbyKeterangan(String keterangan);
    
    List<BarangKecilDTO> sortBarangbyId();
    
    List<BarangKecilDTO> sortBarangbyBarcode();
    
    List<BarangKecilDTO> sortBarangbyNama();
    
    List<BarangKecilDTO> sortBarangbyTipe();
    
    List<BarangKecilDTO> sortBarangbyMerek();
    
    List<BarangKecilDTO> sortBarangbyHarga();
    
    List<BarangKecilDTO> sortBarangbySatuan();
    
    List<BarangKecilDTO> sortBarangbyStok();
    
    List<BarangKecilDTO> sortBarangbyStokMin();
    
    List<BarangKecilDTO> sortBarangbySupplier();
    
    List<BarangKecilDTO> sortBarangbyKeterangan();
    
    List<BarangKecilDTO> selectAllBarang();
    
    int getBarangLastId();    
}

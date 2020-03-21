package com.wissensalt.sinarelektronik.dao;

import com.wissensalt.sinarelektronik.dto.BarangBesarDTO;

import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface ReminderBarangBesarDAO extends IBaseDAO<BarangBesarDTO> {
    
    List<BarangBesarDTO> getBarangbyId(String idBarang);
    
    List<BarangBesarDTO> getBarangbyBarcode(String idBarcode);
    
    List<BarangBesarDTO> getBarangbyNama(String nama);
    
    List<BarangBesarDTO> getBarangbyTipe(String tipe);
    
    List<BarangBesarDTO> getBarangbyMerek(String merek);
    
    List<BarangBesarDTO> getBarangbyHarga(String harga);
    
    List<BarangBesarDTO> getBarangbySatuan(String satuan);
    
    List<BarangBesarDTO> getBarangbyStok(String stok);
    
    List<BarangBesarDTO> getBarangbyStokMin(String stokMin);
    
    List<BarangBesarDTO> getBarangbySupplier(String supplier);
    
    List<BarangBesarDTO> getBarangbyKeterangan(String keterangan);
    
    List<BarangBesarDTO> sortBarangbyId();
    
    List<BarangBesarDTO> sortBarangbyBarcode();
    
    List<BarangBesarDTO> sortBarangbyNama();
    
    List<BarangBesarDTO> sortBarangbyTipe();
    
    List<BarangBesarDTO> sortBarangbyMerek();
    
    List<BarangBesarDTO> sortBarangbyHarga();
    
    List<BarangBesarDTO> sortBarangbySatuan();
    
    List<BarangBesarDTO> sortBarangbyStok();
    
    List<BarangBesarDTO> sortBarangbyStok_min();
    
    List<BarangBesarDTO> sortBarangbySupplier();
    
    List<BarangBesarDTO> sortBarangbyKeterangan();
    
    List<BarangBesarDTO> selectAllBarang();
}

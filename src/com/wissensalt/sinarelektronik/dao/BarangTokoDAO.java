package com.wissensalt.sinarelektronik.dao;

import com.wissensalt.sinarelektronik.dto.BarangTokoDTO;

import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface BarangTokoDAO extends IBaseDAO<BarangTokoDTO> {
    
    List<BarangTokoDTO> getBarangbyId(String idBarang);
    
    List<BarangTokoDTO> getBarangbyNama(String nama);
    
    List<BarangTokoDTO> getBarangbyTipe(String tipe);
    
    List<BarangTokoDTO> getBarangbyMerek(String merek);
    
    List<BarangTokoDTO> getBarangbyHarga(String harga);
    
    List<BarangTokoDTO> getBarangbyStok(String stok);
    
    List<BarangTokoDTO> getBarangbyStokMin(String stokMin);
    
    List<BarangTokoDTO> getBarangbySupplier(String supplier);
    
    List<BarangTokoDTO> getBarangbyKeterangan(String keterangan);
    
    List<BarangTokoDTO> sortBarangbyId();
    
    List<BarangTokoDTO> sortBarangbyBarcode();
    
    List<BarangTokoDTO> sortBarangbyNama();
    
    List<BarangTokoDTO> sortBarangbyTipe();
    
    List<BarangTokoDTO> sortBarangbyMerek();
    
    List<BarangTokoDTO> sortBarangbyHarga();
    
    List<BarangTokoDTO> sortBarangbyStok();
    
    List<BarangTokoDTO> sortBarangbyStokMin();
    
    List<BarangTokoDTO> sortBarangbySupplier();
    
    List<BarangTokoDTO> sortBarangbyKeterangan();
    
    List<BarangTokoDTO> selectAllBarang();
    
    int getBarangLastId();

    void updateBarangTokoWithoutImage(BarangTokoDTO barangTokoDTO);
    
    void updateBarangTokoWithmage(BarangTokoDTO barangTokoDTO, String pathGambar); 
}

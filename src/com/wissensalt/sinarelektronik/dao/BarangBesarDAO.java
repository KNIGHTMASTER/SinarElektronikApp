package com.wissensalt.sinarelektronik.dao;

import com.wissensalt.sinarelektronik.dto.BarangBesarDTO;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.error.BarangException;

import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface BarangBesarDAO extends IBaseDAO<BarangBesarDTO> {   
    
    List<BarangBesarDTO> getBarangbyId(String idBarang) throws BarangException;
    
    List<BarangBesarDTO> getBarangbyNama(String nama) throws BarangException;
    
    List<BarangBesarDTO> getBarangbyTipe(String tipe) throws BarangException;
    
    List<BarangBesarDTO> getBarangbyMerek(String merek) throws BarangException;
    
    List<BarangBesarDTO> getBarangbyHarga(String harga) throws BarangException;
    
    List<BarangBesarDTO> getBarangbyStok(String stok) throws BarangException;
    
    List<BarangBesarDTO> getBarangbyStokMin(String stokMin) throws BarangException;
    
    List<BarangBesarDTO> getBarangbySupplier(String supplier) throws BarangException;
    
    List<BarangBesarDTO> getBarangbyKeterangan(String keterangan) throws BarangException;
    
    List<BarangBesarDTO> sortBarangbyId() throws BarangException;
    
    List<BarangBesarDTO> sortBarangbyBarcode() throws BarangException;
    
    List<BarangBesarDTO> sortBarangbyNama() throws BarangException;
    
    List<BarangBesarDTO> sortBarangbyTipe() throws BarangException;
    
    List<BarangBesarDTO> sortBarangbyMerek() throws BarangException;
    
    List<BarangBesarDTO> sortBarangbyHarga() throws BarangException;
    
    List<BarangBesarDTO> sortBarangbyStok() throws BarangException;
    
    List<BarangBesarDTO> sortBarangbyStokMin() throws BarangException;
    
    List<BarangBesarDTO> sortBarangbySupplier() throws BarangException;
    
    List<BarangBesarDTO> sortBarangbyKeterangan() throws BarangException;
    
    List<BarangBesarDTO> selectAllBarang()throws BarangException;
    
    int getBarangLastId() throws BarangException;    

}

package com.wissensalt.sinarelektronik.dao;

import com.wissensalt.sinarelektronik.masterdata.namabarang.entity.NamaBarangDTO;

import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface NamaBarangDAO extends IBaseDAO<NamaBarangDTO> {
    
    List<NamaBarangDTO> getNamaBarangById(int id);
    
    List<NamaBarangDTO> selectAllNamaBarang();
    
    int getLastId();    
}

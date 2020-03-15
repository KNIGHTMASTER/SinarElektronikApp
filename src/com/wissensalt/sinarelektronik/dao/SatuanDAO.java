package com.wissensalt.sinarelektronik.dao;

import com.wissensalt.sinarelektronik.masterdata.satuan.entity.SatuanDTO;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface SatuanDAO extends IBaseDAO<SatuanDTO> {
    
    SatuanDTO getTipeById(String idSatuan);
    
    List<SatuanDTO> selectAllSatuan();
    
    int getLastId();    
    
}

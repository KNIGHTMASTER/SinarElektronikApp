package com.wissensalt.sinarelektronik.dao;

import com.wissensalt.sinarelektronik.masterdata.tipe.entity.TipeDTO;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface TipeDAO extends IBaseDAO<TipeDTO> {
    
    TipeDTO getTipeByid(String idTipe);
    
    List<TipeDTO> selectAllTipe();
    
    Integer getLastIdata();
    
}

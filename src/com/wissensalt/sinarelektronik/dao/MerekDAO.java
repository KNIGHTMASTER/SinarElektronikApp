package com.wissensalt.sinarelektronik.dao;

import com.wissensalt.sinarelektronik.dto.MerekDTO;

import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface MerekDAO extends IBaseDAO<MerekDTO> {
    List<MerekDTO> getMerekById(String idMerek);
    List<MerekDTO> selectAllMerek();
    int getLastId();
}

package com.wissensalt.sinarelektronik.dao;

import com.wissensalt.sinarelektronik.masterdata.user.entity.UserDTO;

import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface UserDAO extends IBaseDAO<UserDTO> {
    List<UserDTO> selectAllUser();
    
    List<UserDTO> searchById(String id);
    
    List<UserDTO> searchByname(String name);
    
    List<UserDTO> searchByLevel(String level);
    
    List<UserDTO> searchByKet(String ket);
    
    List<UserDTO> sortById();
    
    List<UserDTO> sortByname();
    
    List<UserDTO> sortByLevel();
    
    List<UserDTO> sortByKet();
    
    int getLastId();
}

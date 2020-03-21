package com.wissensalt.sinarelektronik.dao;

import com.wissensalt.sinarelektronik.dto.BaseDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Fauzi
 * @param <DTO>
 */
public interface IBaseDAO<DTO extends BaseDTO>{
    
    /**
     *
     * @param dto
     */
    void insert(DTO dto);    
    void insertDetail(DTO dto, PreparedStatement ps);
    
    void update(DTO dto);
    PreparedStatement updateDetail(DTO dto, PreparedStatement ps);
    
    List<DTO> findByFieldLike(String field, String query);    
    DTO getDTOFromResultSet(ResultSet rs);

    /**
     * 
     * @param field Field/ Keyword to Search
     * @param query Query for Searching
     * @return 
     */
    DTO findSingleByField(String field, String query);
    
    List<DTO> findByField(String field, String query);
    
    List<DTO> findAndSortByField(String query);    
    
    int findLastIdByField(String field, String query);
    
    void deleteByInt(int id);    
    String getDeleteQueryById();
    
    void deleteByString(String id);    
    String getDeleteQueryByString();
    
    void truncate();    
    String getTruncateQuery();
    
    ResultSet selectSingleField(String query);
    
    Connection getConnection();     
}

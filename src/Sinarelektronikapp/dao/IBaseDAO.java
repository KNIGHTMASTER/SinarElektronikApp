package Sinarelektronikapp.dao;

import Sinarelektronikapp.dto.BaseDTO;
import java.sql.PreparedStatement;

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
    
    void deleteByInt(int id);    
    String getDeleteQueryById();
    
    void deleteByString(String id);    
    String getDeleteQueryByString();
    
    void truncate();    
    String getTruncateQuery();
}

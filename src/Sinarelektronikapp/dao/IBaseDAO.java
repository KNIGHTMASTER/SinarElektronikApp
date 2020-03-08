package Sinarelektronikapp.dao;

import Sinarelektronikapp.dto.BaseDTO;

/**
 *
 * @author moka
 * @param <DTO>
 */
public interface IBaseDAO<DTO extends BaseDTO>{
    
    /**
     *
     * @param dto
     */
    void insert(DTO dto);
    
    void deleteByInt(int id);
    
    void deleteByString(String id);
    
    void truncate();
    
}

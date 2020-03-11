package com.wissensalt.sinarelektronik.listener;

import com.wissensalt.sinarelektronik.dto.BaseDTO;
import com.wissensalt.sinarelektronik.model.BaseModel;

/**
 *
 * @author Fauzi
 * @param <MODEL>
 * @param <DTO>
 */
public interface BaseListener <MODEL extends BaseModel, DTO extends BaseDTO> {
    public void onChange(MODEL model);

    public void onInsert(DTO dto);
    
    public void onDelete();
    
    public void onTruncate();
}

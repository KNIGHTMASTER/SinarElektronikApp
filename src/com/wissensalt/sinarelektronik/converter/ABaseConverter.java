package com.wissensalt.sinarelektronik.converter;

import com.wissensalt.sinarelektronik.dto.BaseDTO;
import com.wissensalt.sinarelektronik.model.BaseModel;

/**
 *
 * @author Fauzi
 */
public abstract class ABaseConverter<DTO extends BaseDTO, MODEL extends BaseModel>{
    public abstract DTO toDTO(MODEL model);
    public abstract MODEL toModel(DTO dto);
}

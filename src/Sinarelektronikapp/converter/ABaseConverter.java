package Sinarelektronikapp.converter;

import Sinarelektronikapp.dto.BaseDTO;
import Sinarelektronikapp.model.BaseModel;

/**
 *
 * @author Fauzi
 */
public abstract class ABaseConverter<DTO extends BaseDTO, MODEL extends BaseModel>{
    public abstract DTO toDTO(MODEL model);
    public abstract MODEL toModel(DTO dto);
}

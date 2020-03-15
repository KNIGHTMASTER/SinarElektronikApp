package com.wissensalt.sinarelektronik.masterdata.tipe.model.event;

import com.wissensalt.sinarelektronik.masterdata.tipe.entity.TipeDTO;
import com.wissensalt.sinarelektronik.masterdata.tipe.model.TipeModel;

/**
 *
 * @author Fauzi
 */
public interface TipeListener {
    void onChange(TipeModel model);
    
    void onInsert(TipeDTO dto);
    
    void onUpdate(TipeDTO dto);
    
    void onDelete();
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.merek.model.event;

import com.wissensalt.sinarelektronik.dto.MerekDTO;
import com.wissensalt.sinarelektronik.model.MerekModel;

/**
 *
 * @author Fauzi
 */
public interface merekListener {
    public void onChange(MerekModel model);
    
    public void onInsert(MerekDTO MerekDTO);
    
    public void onUpdate(MerekDTO MerekDTO);
    
    public void onDelete();
}

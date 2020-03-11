/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.barangbesar.stokreminder.model.event;

import com.wissensalt.sinarelektronik.dto.BarangBesarDTO;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.stokreminder.model.ReminderModel;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface ReminderListener {
    public void onChange(ReminderModel model);
    
    public void onInsert(BarangBesarDTO BarangBesarDTO);
    
    public void onUpdate(BarangBesarDTO BarangBesarDTO);
    
    public void onDelete();
    
    public void onSearch(List list);
    
    public void onSort(List list);
    
}

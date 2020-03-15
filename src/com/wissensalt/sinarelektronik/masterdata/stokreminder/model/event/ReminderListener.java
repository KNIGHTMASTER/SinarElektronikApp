/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.stokreminder.model.event;

import com.wissensalt.sinarelektronik.masterdata.barangkecil.entity.BarangKecilDTO;
import com.wissensalt.sinarelektronik.masterdata.stokreminder.model.ReminderModel;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface ReminderListener {
    public void onChange(ReminderModel model);
    
    public void onInsert(BarangKecilDTO BarangKecilDTO);
    
    public void onUpdate(BarangKecilDTO BarangKecilDTO);
    
    public void onDelete();
    
    public void onSearch(List list);
    
    public void onSort(List list);
    
}

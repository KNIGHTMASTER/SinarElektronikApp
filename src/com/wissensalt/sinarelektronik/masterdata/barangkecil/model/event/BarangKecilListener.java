/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.barangkecil.model.event;

import com.wissensalt.sinarelektronik.masterdata.barangkecil.entity.BarangKecilDTO;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.model.BarangKecilModel;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface BarangKecilListener {
    public void onChange(BarangKecilModel model);
    
    public void onInsert(BarangKecilDTO BarangKecilDTO);
    
    public void onUpdate(BarangKecilDTO BarangKecilDTO);
    
    public void onDelete();
    
    public void onSearch(List list);
    
    public void onSort(List list);
}

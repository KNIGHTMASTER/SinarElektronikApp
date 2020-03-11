/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.penjualan.barangkecil.garansi.event;

import com.wissensalt.sinarelektronik.penjualan.barangkecil.garansi.entity.Garansi;
import com.wissensalt.sinarelektronik.penjualan.barangkecil.garansi.model.GaransiModel;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface GaransiListener {
    public void onChange(GaransiModel model);
    
    public void onInsert(Garansi garansi);
    
    public void onUpdate(Garansi garansi);
    
    public void onDelete();
    
    public void onSearch(List list);
    
    public void onSort(List list);    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.pelanggan.model.event;

import com.wissensalt.sinarelektronik.masterdata.pelanggan.entity.pelanggan;
import com.wissensalt.sinarelektronik.masterdata.pelanggan.model.pelangganModel;

/**
 *
 * @author Fauzi
 */
public interface pelangganListener {
    public void onChange(pelangganModel model);
    
    public void onInsert(pelanggan pelanggan);
    
    public void onUpdate(pelanggan pelanggan);
    
    public void onDelete();
    
    public void onSearch(java.util.List list);
    
    public void onSort(java.util.List list);
}

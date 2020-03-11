/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.barangtoko.model.event;

import com.wissensalt.sinarelektronik.masterdata.barangtoko.entity.barang;
import com.wissensalt.sinarelektronik.masterdata.barangtoko.model.barangModel;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface barangListener {
    public void onChange(barangModel model);
    
    public void onInsert(barang barang);
    
    public void onUpdate(barang barang);
    
    public void onDelete();
    
    public void onSearchToko(List list);
    
    public void onSortToko(List list);
}

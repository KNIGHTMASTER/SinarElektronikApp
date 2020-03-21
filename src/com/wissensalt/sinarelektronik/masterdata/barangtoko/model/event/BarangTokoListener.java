package com.wissensalt.sinarelektronik.masterdata.barangtoko.model.event;

import com.wissensalt.sinarelektronik.dto.BarangTokoDTO;
import com.wissensalt.sinarelektronik.model.BarangTokoModel;

import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface BarangTokoListener {
    void onChange(BarangTokoModel model);
    
    void onInsert(BarangTokoDTO barang);
    
    void onUpdate(BarangTokoDTO barang);
    
    void onDelete();
    
    void onSearchToko(List list);
    
    void onSortToko(List list);
}

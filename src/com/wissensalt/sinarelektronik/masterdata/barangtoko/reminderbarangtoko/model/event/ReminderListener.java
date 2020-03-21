package com.wissensalt.sinarelektronik.masterdata.barangtoko.reminderbarangtoko.model.event;

import com.wissensalt.sinarelektronik.dto.BarangTokoDTO;
import com.wissensalt.sinarelektronik.model.BarangTokoReminderModel;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface ReminderListener {
    void onChange(BarangTokoReminderModel model);
    
    void onInsert(BarangTokoDTO barang);
    
    void onUpdate(BarangTokoDTO barang);
    
    void onDelete();
    
    void onSearch(List list);
    
    void onSort(List list);
    
}

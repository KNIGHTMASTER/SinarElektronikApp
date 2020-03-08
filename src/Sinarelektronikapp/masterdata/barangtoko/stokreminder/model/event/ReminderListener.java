/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.barangtoko.stokreminder.model.event;

import Sinarelektronikapp.masterdata.barangtoko.entity.barang;
import Sinarelektronikapp.masterdata.barangtoko.stokreminder.model.ReminderModel;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface ReminderListener {
    public void onChange(ReminderModel model);
    
    public void onInsert(barang barang);
    
    public void onUpdate(barang barang);
    
    public void onDelete();
    
    public void onSearch(List list);
    
    public void onSort(List list);
    
}

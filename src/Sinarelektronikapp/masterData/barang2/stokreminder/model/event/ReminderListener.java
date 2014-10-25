/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.barang2.stokreminder.model.event;

import Sinarelektronikapp.masterData.barang2.entity.barang;
import Sinarelektronikapp.masterData.barang2.stokreminder.model.ReminderModel;
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

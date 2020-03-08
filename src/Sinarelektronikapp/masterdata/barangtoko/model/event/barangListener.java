/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.barangtoko.model.event;

import Sinarelektronikapp.masterdata.barangtoko.entity.barang;
import Sinarelektronikapp.masterdata.barangtoko.model.barangModel;
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

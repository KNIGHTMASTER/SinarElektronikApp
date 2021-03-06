/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.barangToko.model.event;

import Sinarelektronikapp.masterData.barangToko.entity.barang;
import Sinarelektronikapp.masterData.barangToko.model.barangModel;
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

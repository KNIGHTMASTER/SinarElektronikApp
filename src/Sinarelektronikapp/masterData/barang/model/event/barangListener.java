/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.barang.model.event;

import Sinarelektronikapp.masterData.barang.entity.barang;
import Sinarelektronikapp.masterData.barang.model.barangModel;
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
    
    public void onSearch(List list);
    
    public void onSort(List list);
}

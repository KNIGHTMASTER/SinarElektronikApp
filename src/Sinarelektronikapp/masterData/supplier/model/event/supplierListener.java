/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.supplier.model.event;

import Sinarelektronikapp.masterData.supplier.entity.supplier;
import Sinarelektronikapp.masterData.supplier.model.supplierModel;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface supplierListener {
    public void onChange(supplierModel model);
    
    public void onInsert(supplier supplier);
    
    public void onUpdate(supplier supplier);
    
    public void onDelete();
    
    public void onSearch(List list);
    
    public void onSort(List list);
}

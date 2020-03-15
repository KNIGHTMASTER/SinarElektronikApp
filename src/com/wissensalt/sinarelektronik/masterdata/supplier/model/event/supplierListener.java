/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.supplier.model.event;

import com.wissensalt.sinarelektronik.masterdata.supplier.entity.SupplierDTO;
import com.wissensalt.sinarelektronik.masterdata.supplier.model.SupplierModel;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface supplierListener {
    public void onChange(SupplierModel model);
    
    public void onInsert(SupplierDTO SupplierDTO);
    
    public void onUpdate(SupplierDTO SupplierDTO);
    
    public void onDelete();
    
    public void onSearch(List list);
    
    public void onSort(List list);
}

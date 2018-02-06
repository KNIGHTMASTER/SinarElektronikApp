/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.inventoryBarangBesar.model.Event;

import Sinarelektronikapp.inventoryBarangBesar.entity.Inventory;
import Sinarelektronikapp.inventoryBarangBesar.model.InventoryModel;

/**
 *
 * @author Fauzi
 */
public interface InventoryListener {
    public void onChange(InventoryModel model);

    public void onInsert(Inventory inventory);
    
    public void onDelete();
    
    public void onTruncate();
}

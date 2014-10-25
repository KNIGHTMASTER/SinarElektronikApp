/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.inventory.model.Event;

import Sinarelektronikapp.inventory.entity.Inventory;
import Sinarelektronikapp.inventory.model.InventoryModel;

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

package Sinarelektronikapp.inventory.barangbesar.model.Event;

import Sinarelektronikapp.inventory.barangbesar.entity.Inventory;
import Sinarelektronikapp.inventory.barangbesar.model.InventoryModel;

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

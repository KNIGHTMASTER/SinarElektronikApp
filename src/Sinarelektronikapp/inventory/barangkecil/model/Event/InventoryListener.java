package Sinarelektronikapp.inventory.barangkecil.model.Event;

import Sinarelektronikapp.inventory.barangkecil.entity.Inventory;
import Sinarelektronikapp.inventory.barangkecil.model.InventoryModel;

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

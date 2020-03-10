package Sinarelektronikapp.inventory.barangkecil.model.Event;

import Sinarelektronikapp.dto.InventoryBarangKecilDTO;
import Sinarelektronikapp.inventory.barangkecil.model.InventoryBarangKecilModel;

/**
 *
 * @author Fauzi
 */
public interface InventoryBarangKecilListener {
    public void onChange(InventoryBarangKecilModel model);

    public void onInsert(InventoryBarangKecilDTO inventoryBarangKecilDTO);
    
    public void onDelete();
    
    public void onTruncate();
}

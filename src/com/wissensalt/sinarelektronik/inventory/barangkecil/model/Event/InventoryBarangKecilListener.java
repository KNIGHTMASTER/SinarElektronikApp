package com.wissensalt.sinarelektronik.inventory.barangkecil.model.Event;

import com.wissensalt.sinarelektronik.dto.InventoryBarangKecilDTO;
import com.wissensalt.sinarelektronik.model.InventoryBarangKecilModel;

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

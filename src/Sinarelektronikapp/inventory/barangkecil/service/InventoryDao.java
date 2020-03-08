package Sinarelektronikapp.inventory.barangkecil.service;

import Sinarelektronikapp.inventory.barangkecil.entity.Inventory;
import Sinarelektronikapp.inventory.barangkecil.error.InventoryException;

/**
 *
 * @author Fauzi
 */
public interface InventoryDao {
    
    public void insertProsesInventory(Inventory inventory) throws InventoryException;
    
    public void deleteProsesInventory(int id) throws InventoryException;
    
    public void truncateProsesInventory() throws InventoryException;
    
}

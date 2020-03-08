package Sinarelektronikapp.inventory.barangbesar.service;

import Sinarelektronikapp.inventory.barangbesar.entity.InventoryDTO;
import Sinarelektronikapp.inventory.barangbesar.error.InventoryException;

/**
 *
 * @author Fauzi
 */
public interface InventoryDao {
    
    public void insertProsesInventory(InventoryDTO inventory) throws InventoryException;
    
    public void deleteProsesInventory(int id) throws InventoryException;
    
    public void truncateProsesInventory() throws InventoryException;
    
}

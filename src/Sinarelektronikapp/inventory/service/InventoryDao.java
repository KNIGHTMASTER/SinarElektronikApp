/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.inventory.service;

import Sinarelektronikapp.inventory.entity.Inventory;
import Sinarelektronikapp.inventory.error.InventoryException;

/**
 *
 * @author Fauzi
 */
public interface InventoryDao {
    
    public void insertProsesInventory(Inventory inventory) throws InventoryException;
    
    public void deleteProsesInventory(int id) throws InventoryException;
    
    public void truncateProsesInventory() throws InventoryException;
    
}

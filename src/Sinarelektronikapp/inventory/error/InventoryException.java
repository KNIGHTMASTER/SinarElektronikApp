/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.inventory.error;

/**
 *
 * @author Fauzi
 */
public class InventoryException extends Exception {

    /**
     * Creates a new instance of
     * <code>InventoryException</code> without detail message.
     */
    public InventoryException() {
    }

    /**
     * Constructs an instance of
     * <code>InventoryException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public InventoryException(String msg) {
        super(msg);
    }
}

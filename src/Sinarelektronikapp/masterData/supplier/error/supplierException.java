/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.supplier.error;

/**
 *
 * @author Fauzi
 */
public class supplierException extends Exception {

    /**
     * Creates a new instance of
     * <code>supplierException</code> without detail message.
     */
    public supplierException() {
    }

    /**
     * Constructs an instance of
     * <code>supplierException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public supplierException(String msg) {
        super(msg);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.retur.transaksi.error;

/**
 *
 * @author Fauzi
 */
public class ReturException extends Exception {

    /**
     * Creates a new instance of
     * <code>ReturException</code> without detail message.
     */
    public ReturException() {
    }

    /**
     * Constructs an instance of
     * <code>ReturException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ReturException(String msg) {
        super(msg);
    }
}

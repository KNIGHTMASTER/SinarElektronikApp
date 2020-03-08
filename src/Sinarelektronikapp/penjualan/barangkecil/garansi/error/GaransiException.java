/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.penjualan.barangkecil.garansi.error;

/**
 *
 * @author Fauzi
 */
public class GaransiException extends Exception {

    /**
     * Creates a new instance of
     * <code>GaransiException</code> without detail message.
     */
    public GaransiException() {
    }

    /**
     * Constructs an instance of
     * <code>GaransiException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public GaransiException(String msg) {
        super(msg);
    }
}

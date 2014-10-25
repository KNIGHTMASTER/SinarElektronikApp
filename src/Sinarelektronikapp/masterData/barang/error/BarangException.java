/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.barang.error;

/**
 *
 * @author Fauzi
 */
public class BarangException extends Exception {

    /**
     * Creates a new instance of
     * <code>BarangException</code> without detail message.
     */
    public BarangException() {
    }

    /**
     * Constructs an instance of
     * <code>BarangException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public BarangException(String msg) {
        super(msg);
    }
}

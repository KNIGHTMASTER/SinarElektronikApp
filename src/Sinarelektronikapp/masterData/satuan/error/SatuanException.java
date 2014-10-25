/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.satuan.error;

/**
 *
 * @author Fauzi
 */
public class SatuanException extends Exception {

    /**
     * Creates a new instance of
     * <code>SatuanException</code> without detail message.
     */
    public SatuanException() {
    }

    /**
     * Constructs an instance of
     * <code>SatuanException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public SatuanException(String msg) {
        super(msg);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.ManajemenPenjualan.BarangKecil.Error;

/**
 *
 * @author Fauzi
 */
public class TransaksiPenjualanException extends Exception {

    /**
     * Creates a new instance of
     * <code>TransaksiPenjualanException</code> without detail message.
     */
    public TransaksiPenjualanException() {
    }

    /**
     * Constructs an instance of
     * <code>TransaksiPenjualanException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public TransaksiPenjualanException(String msg) {
        super(msg);
    }
}

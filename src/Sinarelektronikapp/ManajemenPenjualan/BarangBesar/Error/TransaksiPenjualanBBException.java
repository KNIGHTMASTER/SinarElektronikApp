/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Error;

/**
 *
 * @author Fauzi
 */
public class TransaksiPenjualanBBException extends Exception {

    /**
     * Creates a new instance of
     * <code>TransaksiPenjualanBBException</code> without detail message.
     */
    public TransaksiPenjualanBBException() {
    }

    /**
     * Constructs an instance of
     * <code>TransaksiPenjualanBBException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public TransaksiPenjualanBBException(String msg) {
        super(msg);
    }
}

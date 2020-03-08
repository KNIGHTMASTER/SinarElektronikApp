/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.merek.error;

/**
 *
 * @author Fauzi
 */
public class merekException extends Exception {

    /**
     * Creates a new instance of
     * <code>merekException</code> without detail message.
     */
    public merekException() {
    }

    /**
     * Constructs an instance of
     * <code>merekException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public merekException(String msg) {
        super(msg);
    }
}

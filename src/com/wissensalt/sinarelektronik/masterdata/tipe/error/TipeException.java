/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.tipe.error;

/**
 *
 * @author Fauzi
 */
public class TipeException extends Exception {

    /**
     * Creates a new instance of
     * <code>TipeException</code> without detail message.
     */
    public TipeException() {
    }

    /**
     * Constructs an instance of
     * <code>TipeException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public TipeException(String msg) {
        super(msg);
    }
}

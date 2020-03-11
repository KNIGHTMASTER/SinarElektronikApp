/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.user.error;

/**
 *
 * @author Fauzi
 */
public class userException extends Exception {

    /**
     * Creates a new instance of
     * <code>userException</code> without detail message.
     */
    public userException() {
    }

    /**
     * Constructs an instance of
     * <code>userException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public userException(String msg) {
        super(msg);
    }
}

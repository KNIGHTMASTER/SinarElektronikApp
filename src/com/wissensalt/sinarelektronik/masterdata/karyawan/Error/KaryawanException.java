/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.karyawan.Error;

/**
 *
 * @author Fauzi
 */
public class KaryawanException extends Exception {

    /**
     * Creates a new instance of
     * <code>KaryawanException</code> without detail message.
     */
    public KaryawanException() {
    }

    /**
     * Constructs an instance of
     * <code>KaryawanException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public KaryawanException(String msg) {
        super(msg);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.tambahBarang.service;

import Sinarelektronikapp.masterData.barang.entity.barang;
import Sinarelektronikapp.masterData.tambahBarang.error.TambahBarangException;

/**
 *
 * @author Fauzi
 */
public interface TambahBarangDao {
        public void insertBarang(barang barang) throws TambahBarangException;
}

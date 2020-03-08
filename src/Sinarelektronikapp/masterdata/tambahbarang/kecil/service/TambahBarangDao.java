/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.tambahbarang.kecil.service;

import Sinarelektronikapp.masterdata.barangkecil.entity.barang;
import Sinarelektronikapp.masterdata.tambahbarang.kecil.error.TambahBarangException;

/**
 *
 * @author Fauzi
 */
public interface TambahBarangDao {
        public void insertBarang(barang barang) throws TambahBarangException;
}

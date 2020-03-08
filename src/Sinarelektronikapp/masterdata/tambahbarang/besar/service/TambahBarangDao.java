/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.tambahbarang.besar.service;

import Sinarelektronikapp.masterdata.barangbesar.entity.barang;
import Sinarelektronikapp.masterdata.tambahbarang.besar.error.TambahBarangException;

/**
 *
 * @author Fauzi
 */
public interface TambahBarangDao {
        public void insertBarang(barang barang) throws TambahBarangException;
}

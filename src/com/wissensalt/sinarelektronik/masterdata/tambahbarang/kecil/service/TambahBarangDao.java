/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.tambahbarang.kecil.service;

import com.wissensalt.sinarelektronik.masterdata.barangkecil.entity.BarangKecilDTO;
import com.wissensalt.sinarelektronik.masterdata.tambahbarang.kecil.error.TambahBarangException;

/**
 *
 * @author Fauzi
 */
public interface TambahBarangDao {
        public void insertBarang(BarangKecilDTO BarangKecilDTO) throws TambahBarangException;
}

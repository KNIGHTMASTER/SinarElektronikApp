/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.dao;

import com.wissensalt.sinarelektronik.masterdata.barangkecil.entity.BarangKecilDTO;
import com.wissensalt.sinarelektronik.masterdata.tambahbarang.kecil.error.TambahBarangException;

/**
 *
 * @author Fauzi
 */
public interface TambahBarangKecilDAO {
        public void insertBarang(BarangKecilDTO BarangKecilDTO) throws TambahBarangException;
}

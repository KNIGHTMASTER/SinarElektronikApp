/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.tambahbarang.besar.service;

import com.wissensalt.sinarelektronik.dto.BarangBesarDTO;
import com.wissensalt.sinarelektronik.masterdata.tambahbarang.besar.error.TambahBarangException;

/**
 *
 * @author Fauzi
 */
public interface TambahBarangDao {
        public void insertBarang(BarangBesarDTO BarangBesarDTO) throws TambahBarangException;
}

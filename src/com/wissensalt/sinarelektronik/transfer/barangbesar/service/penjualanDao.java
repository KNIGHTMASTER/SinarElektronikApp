/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.transfer.barangbesar.service;

import com.wissensalt.sinarelektronik.transfer.barangbesar.entity.ProsesKasir;
import com.wissensalt.sinarelektronik.transfer.barangbesar.error.penjualanException;

import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface penjualanDao {
    
    public void insertPenjualan(ProsesKasir prosesKasir) throws penjualanException;
    
    public List<ProsesKasir> selectAllPenjualan() throws penjualanException;

    public void deletePenjualan(int no) throws penjualanException;
    
    public void truncatePenjualan()throws penjualanException;
    
}

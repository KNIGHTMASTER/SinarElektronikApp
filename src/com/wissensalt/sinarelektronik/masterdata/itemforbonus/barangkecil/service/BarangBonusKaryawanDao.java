/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangkecil.service;

import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangkecil.entity.BarangBonusKaryawanBk;
import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangkecil.error.BarangBonusKaryawanBKException;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface BarangBonusKaryawanDao {
    
    public void insertTipe(BarangBonusKaryawanBk bbbbk)throws BarangBonusKaryawanBKException;
    
    public void updateTipe(BarangBonusKaryawanBk tipe) throws BarangBonusKaryawanBKException;
    
    public void deleteTipe(String idBarangBonusKaryawanBK) throws BarangBonusKaryawanBKException;    
    
    public List<BarangBonusKaryawanBk> selectAllTipe() throws BarangBonusKaryawanBKException;    
    
}

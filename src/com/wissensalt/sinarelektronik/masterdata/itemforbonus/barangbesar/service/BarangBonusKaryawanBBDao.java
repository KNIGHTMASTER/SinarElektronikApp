/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangbesar.service;

import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangbesar.entity.BarangBonusKaryawanBB;
import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangbesar.error.BarangBonusKaryawanBBException;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface BarangBonusKaryawanBBDao {
    
    public void insertTipe(BarangBonusKaryawanBB bbbbk)throws BarangBonusKaryawanBBException;
    
    public void updateTipe(BarangBonusKaryawanBB tipe) throws BarangBonusKaryawanBBException;
    
    public void deleteTipe(String idBarangBonusKaryawanBK) throws BarangBonusKaryawanBBException;    
    
    public List<BarangBonusKaryawanBB> selectAllTipe() throws BarangBonusKaryawanBBException;    
    
}

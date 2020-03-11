/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangkecil.model.event;

import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangkecil.entity.BarangBonusKaryawanBk;
import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangkecil.model.BarangBonusKaryawanBKModel;

/**
 *
 * @author Fauzi
 */
public interface BarangBonusKaryawanBKListener {
    public void onChange(BarangBonusKaryawanBKModel model);
    
    public void onInsert(BarangBonusKaryawanBk tipe);
        
    public void onDelete();
}

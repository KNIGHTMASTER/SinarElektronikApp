/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.itemforbonus.barangkecil.model.event;

import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.entity.BarangBonusKaryawanBk;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.model.BarangBonusKaryawanBKModel;

/**
 *
 * @author Fauzi
 */
public interface BarangBonusKaryawanBKListener {
    public void onChange(BarangBonusKaryawanBKModel model);
    
    public void onInsert(BarangBonusKaryawanBk tipe);
        
    public void onDelete();
}

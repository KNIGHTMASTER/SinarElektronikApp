package com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangbesar.model.event;

import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangbesar.entity.BarangBonusKaryawanBB;
import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangbesar.model.BarangBonusKaryawanBBModel;

/**
 *
 * @author Fauzi
 */
public interface BarangBonusKaryawanBBListener {
    public void onChange(BarangBonusKaryawanBBModel model);
    
    public void onInsert(BarangBonusKaryawanBB tipe);
        
    public void onDelete();
}

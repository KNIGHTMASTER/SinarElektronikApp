package Sinarelektronikapp.masterdata.itemforbonus.barangbesar.model.event;

import Sinarelektronikapp.masterdata.itemforbonus.barangbesar.entity.BarangBonusKaryawanBB;
import Sinarelektronikapp.masterdata.itemforbonus.barangbesar.model.BarangBonusKaryawanBBModel;

/**
 *
 * @author Fauzi
 */
public interface BarangBonusKaryawanBBListener {
    public void onChange(BarangBonusKaryawanBBModel model);
    
    public void onInsert(BarangBonusKaryawanBB tipe);
        
    public void onDelete();
}

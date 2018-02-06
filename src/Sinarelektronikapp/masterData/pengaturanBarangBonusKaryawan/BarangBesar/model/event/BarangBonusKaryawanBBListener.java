/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.pengaturanBarangBonusKaryawan.BarangBesar.model.event;

import Sinarelektronikapp.masterData.pengaturanBarangBonusKaryawan.BarangBesar.entity.BarangBonusKaryawanBB;
import Sinarelektronikapp.masterData.pengaturanBarangBonusKaryawan.BarangBesar.model.BarangBonusKaryawanBBModel;

/**
 *
 * @author Fauzi
 */
public interface BarangBonusKaryawanBBListener {
    public void onChange(BarangBonusKaryawanBBModel model);
    
    public void onInsert(BarangBonusKaryawanBB tipe);
        
    public void onDelete();
}

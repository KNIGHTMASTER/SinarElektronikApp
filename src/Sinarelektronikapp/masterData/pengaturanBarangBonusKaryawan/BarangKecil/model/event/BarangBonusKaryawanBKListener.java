/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.pengaturanBarangBonusKaryawan.BarangKecil.model.event;

import Sinarelektronikapp.masterData.pengaturanBarangBonusKaryawan.BarangKecil.entity.BarangBonusKaryawanBk;
import Sinarelektronikapp.masterData.pengaturanBarangBonusKaryawan.BarangKecil.model.BarangBonusKaryawanBKModel;

/**
 *
 * @author Fauzi
 */
public interface BarangBonusKaryawanBKListener {
    public void onChange(BarangBonusKaryawanBKModel model);
    
    public void onInsert(BarangBonusKaryawanBk tipe);
        
    public void onDelete();
}

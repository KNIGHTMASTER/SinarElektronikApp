/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.karyawan.model.event;

import Sinarelektronikapp.masterData.karyawan.entity.Karyawan;
import Sinarelektronikapp.masterData.karyawan.model.KaryawanModel;

/**
 *
 * @author Fauzi
 */
public interface KaryawanListener {
    
    public void onChange(KaryawanModel model);
    
    public void onInsert(Karyawan karyawan);
    
    public void onUpdate(Karyawan karyawan);
    
    public void onDelete();
    
}

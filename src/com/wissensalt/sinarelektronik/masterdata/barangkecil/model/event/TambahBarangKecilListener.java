/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.barangkecil.model.event;

import com.wissensalt.sinarelektronik.masterdata.barangkecil.entity.BarangKecilDTO;

import com.wissensalt.sinarelektronik.masterdata.tambahbarang.kecil.model.tambahBarangModel;

/**
 *
 * @author Fauzi
 */
public interface TambahBarangKecilListener {
    
    public void onChange(tambahBarangModel model);
    
    public void onInsert(BarangKecilDTO BarangKecilDTO);
    
    public void onUpdate(BarangKecilDTO BarangKecilDTO);
    
}

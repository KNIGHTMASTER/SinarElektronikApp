/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.namabarang.model.event;

import com.wissensalt.sinarelektronik.masterdata.namabarang.entity.NamaBarangDTO;
import com.wissensalt.sinarelektronik.masterdata.namabarang.model.NamaBarangModel;

/**
 *
 * @author Fauzi
 */
public interface NamaBarangListener {
    public void onChange(NamaBarangModel model);
    
    public void onInsert(NamaBarangDTO namaBarangDTO);
    
    public void onUpdate(NamaBarangDTO namaBarangDTO);
    
    public void onDelete();    
}

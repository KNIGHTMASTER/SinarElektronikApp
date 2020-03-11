/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.barangbesar.model.event;

import com.wissensalt.sinarelektronik.dto.BarangBesarDTO;

import com.wissensalt.sinarelektronik.masterdata.tambahbarang.besar.model.tambahBarangModel;

/**
 *
 * @author Fauzi
 */
public interface tambahBarangListener {
    
    public void onChange(tambahBarangModel model);
    
    public void onInsert(BarangBesarDTO BarangBesarDTO);
    
    public void onUpdate(BarangBesarDTO BarangBesarDTO);
    
}

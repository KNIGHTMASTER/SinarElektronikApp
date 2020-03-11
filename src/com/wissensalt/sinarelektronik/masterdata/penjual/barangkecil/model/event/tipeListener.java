/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.penjual.barangkecil.model.event;

import com.wissensalt.sinarelektronik.masterdata.penjual.barangkecil.entity.tipe;
import com.wissensalt.sinarelektronik.masterdata.penjual.barangkecil.model.tipeModel;

/**
 *
 * @author Fauzi
 */
public interface tipeListener {
    public void onChange(tipeModel model);
    
    public void onInsert(tipe tipe);
    
    public void onUpdate(tipe tipe);
    
    public void onDelete();
}

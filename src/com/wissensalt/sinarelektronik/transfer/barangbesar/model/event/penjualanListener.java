/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.transfer.barangbesar.model.event;

import com.wissensalt.sinarelektronik.transfer.barangbesar.entity.ProsesKasir;
import com.wissensalt.sinarelektronik.transfer.barangbesar.model.PenjualanModel;

/**
 *
 * @author Fauzi
 */
public interface penjualanListener {
    public void onChange(PenjualanModel penjualan);
    
    public void onInsert(ProsesKasir prosesKasir);
    
    public void onDelete();
    
    public void onTruncate();
}

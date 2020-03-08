/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.penjualan.barangbesar.model.event;

import Sinarelektronikapp.penjualan.barangbesar.entity.ProsesKasir;
import Sinarelektronikapp.penjualan.barangbesar.model.PenjualanModel;

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

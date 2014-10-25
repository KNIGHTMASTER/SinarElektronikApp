/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.PenjualanBarangBesar.model.event;

import Sinarelektronikapp.PenjualanBarangBesar.entity.ProsesKasir;
import Sinarelektronikapp.PenjualanBarangBesar.model.PenjualanModel;

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

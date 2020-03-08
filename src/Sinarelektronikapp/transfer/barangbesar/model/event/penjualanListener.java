/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.transfer.barangbesar.model.event;

import Sinarelektronikapp.transfer.barangbesar.entity.ProsesKasir;
import Sinarelektronikapp.transfer.barangbesar.model.PenjualanModel;

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

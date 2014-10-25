/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.ReturBarangBesar.transaksi.model.Event;

import Sinarelektronikapp.ReturBarangBesar.transaksi.entity.Retur;
import Sinarelektronikapp.ReturBarangBesar.transaksi.model.ReturModel;

/**
 *
 * @author Fauzi
 */
public interface ReturListener {
    public void onChange(ReturModel retur);
    
    public void onInsert(Retur retur);
    
    public void onUpdate(Retur retur);
    
    public void onDelete();   
    
    public void onTruncate();
}

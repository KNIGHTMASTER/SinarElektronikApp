/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.barangtoko.model.event;

import Sinarelektronikapp.masterdata.barangbesar.entity.barang;

import Sinarelektronikapp.masterdata.tambahbarang.besar.model.tambahBarangModel;

/**
 *
 * @author Fauzi
 */
public interface tambahBarangListener {
    
    public void onChange(tambahBarangModel model);
    
    public void onInsert(barang barang);
    
    public void onUpdate(barang barang);
    
}

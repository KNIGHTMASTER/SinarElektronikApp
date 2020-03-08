/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.barangkecil.model.event;

import Sinarelektronikapp.masterdata.barangkecil.entity.barang;

import Sinarelektronikapp.masterdata.tambahbarang.kecil.model.tambahBarangModel;

/**
 *
 * @author Fauzi
 */
public interface tambahBarangListener {
    
    public void onChange(tambahBarangModel model);
    
    public void onInsert(barang barang);
    
    public void onUpdate(barang barang);
    
}

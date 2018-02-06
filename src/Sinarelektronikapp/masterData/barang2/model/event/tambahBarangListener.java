/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.barang2.model.event;

import Sinarelektronikapp.masterData.barang2.entity.barang;

import Sinarelektronikapp.masterData.tambahBarang2.model.tambahBarangModel;

/**
 *
 * @author Fauzi
 */
public interface tambahBarangListener {
    
    public void onChange(tambahBarangModel model);
    
    public void onInsert(barang barang);
    
    public void onUpdate(barang barang);
    
}

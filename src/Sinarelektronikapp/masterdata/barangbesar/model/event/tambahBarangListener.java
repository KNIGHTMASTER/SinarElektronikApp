/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.barangbesar.model.event;

import Sinarelektronikapp.masterdata.barangbesar.entity.barang;

import Sinarelektronikapp.masterdata.tambahbarang.besar.model.TambahBarangBesarModel;

/**
 *
 * @author Fauzi
 */
public interface tambahBarangListener {
    
    public void onChange(TambahBarangBesarModel model);
    
    public void onInsert(barang barang);
    
    public void onUpdate(barang barang);
    
}

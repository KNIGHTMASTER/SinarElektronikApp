/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.namabarang.model.event;

import Sinarelektronikapp.masterdata.namabarang.Entity.NamaBarang;
import Sinarelektronikapp.masterdata.namabarang.model.NamaBarangModel;

/**
 *
 * @author Fauzi
 */
public interface NamaBarangListener {
    public void onChange(NamaBarangModel model);
    
    public void onInsert(NamaBarang namaBarang);
    
    public void onUpdate(NamaBarang namaBarang);
    
    public void onDelete();    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.satuan.model.event;

import Sinarelektronikapp.masterData.satuan.entity.satuan;
import Sinarelektronikapp.masterData.satuan.model.satuanModel;

/**
 *
 * @author Fauzi
 */
public interface satuanListener {
    public void onChange(satuanModel model);
    
    public void onInsert(satuan satuan);
    
    public void onUpdate(satuan satuan);
    
    public void onDelete();
}

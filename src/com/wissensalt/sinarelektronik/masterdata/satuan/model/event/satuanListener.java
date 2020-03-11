/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.satuan.model.event;

import com.wissensalt.sinarelektronik.masterdata.satuan.entity.satuan;
import com.wissensalt.sinarelektronik.masterdata.satuan.model.satuanModel;

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

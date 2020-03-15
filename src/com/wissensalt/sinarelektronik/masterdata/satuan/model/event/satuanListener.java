/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.satuan.model.event;

import com.wissensalt.sinarelektronik.masterdata.satuan.entity.SatuanDTO;
import com.wissensalt.sinarelektronik.masterdata.satuan.model.SatuanModel;

/**
 *
 * @author Fauzi
 */
public interface satuanListener {
    public void onChange(SatuanModel model);
    
    public void onInsert(SatuanDTO SatuanDTO);
    
    public void onUpdate(SatuanDTO SatuanDTO);
    
    public void onDelete();
}

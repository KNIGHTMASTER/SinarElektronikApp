/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.retur.barangbesar.model.Event;

import com.wissensalt.sinarelektronik.retur.barangbesar.entity.Retur;
import com.wissensalt.sinarelektronik.retur.barangbesar.model.ReturModel;

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

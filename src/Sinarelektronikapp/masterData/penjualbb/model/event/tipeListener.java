/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.penjualbb.model.event;

import Sinarelektronikapp.masterData.penjualbb.entity.tipe;
import Sinarelektronikapp.masterData.penjualbb.model.tipeModel;

/**
 *
 * @author Fauzi
 */
public interface tipeListener {
    public void onChange(tipeModel model);
    
    public void onInsert(tipe tipe);
    
    public void onUpdate(tipe tipe);
    
    public void onDelete();
}

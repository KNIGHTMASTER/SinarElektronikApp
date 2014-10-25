/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.tipe.model.event;

import Sinarelektronikapp.masterData.tipe.entity.tipe;
import Sinarelektronikapp.masterData.tipe.model.tipeModel;

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

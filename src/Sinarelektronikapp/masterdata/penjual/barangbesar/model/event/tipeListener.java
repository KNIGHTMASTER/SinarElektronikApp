/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.penjual.barangbesar.model.event;

import Sinarelektronikapp.masterdata.penjual.barangbesar.entity.tipe;
import Sinarelektronikapp.masterdata.penjual.barangbesar.model.tipeModel;

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

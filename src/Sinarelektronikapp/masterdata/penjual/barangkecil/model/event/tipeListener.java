/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.penjual.barangkecil.model.event;

import Sinarelektronikapp.masterdata.penjual.barangkecil.entity.tipe;
import Sinarelektronikapp.masterdata.penjual.barangkecil.model.tipeModel;

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

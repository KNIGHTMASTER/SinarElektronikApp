/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.retur.barangkecil.model.Event;

import Sinarelektronikapp.retur.barangkecil.entity.Retur;
import Sinarelektronikapp.retur.barangkecil.model.ReturModel;

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

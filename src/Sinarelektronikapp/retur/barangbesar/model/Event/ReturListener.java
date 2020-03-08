/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.retur.barangbesar.model.Event;

import Sinarelektronikapp.retur.barangbesar.entity.Retur;
import Sinarelektronikapp.retur.barangbesar.model.ReturModel;

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

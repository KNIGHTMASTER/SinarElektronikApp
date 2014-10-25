/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.merek.model.event;

import Sinarelektronikapp.masterData.merek.entity.merek;
import Sinarelektronikapp.masterData.merek.model.merekModel;

/**
 *
 * @author Fauzi
 */
public interface merekListener {
    public void onChange(merekModel model);
    
    public void onInsert(merek merek);
    
    public void onUpdate(merek merek);
    
    public void onDelete();
}

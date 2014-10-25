/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.penjualan.garansi.event;

import Sinarelektronikapp.penjualan.garansi.entity.Garansi;
import Sinarelektronikapp.penjualan.garansi.model.GaransiModel;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface GaransiListener {
    public void onChange(GaransiModel model);
    
    public void onInsert(Garansi garansi);
    
    public void onUpdate(Garansi garansi);
    
    public void onDelete();
    
    public void onSearch(List list);
    
    public void onSort(List list);    
}

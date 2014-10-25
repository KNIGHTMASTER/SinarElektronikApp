/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Model.Event;

import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface TransaksiPenjualanBBListener {
    public void onDelete();
    
    public void onSearch(List list);
    
    public void onSort(List list);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.ManajemenPenjualan.BarangKecil.Model.Event;

import Sinarelektronikapp.ManajemenPenjualan.BarangKecil.Model.TransaksiPenjualanModel;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface TransaksiPenjualanListener {       
    public void onDelete();
    
    public void onSearch(List list);
    
    public void onSort(List list);
}

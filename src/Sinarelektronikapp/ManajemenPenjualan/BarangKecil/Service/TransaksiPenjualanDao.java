/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.ManajemenPenjualan.BarangKecil.Service;

import Sinarelektronikapp.ManajemenPenjualan.BarangKecil.Entity.TransaksiPenjualan;
import Sinarelektronikapp.ManajemenPenjualan.BarangKecil.Error.TransaksiPenjualanException;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface TransaksiPenjualanDao {  
    public void deleteTransaksiPenjualan(int id)throws TransaksiPenjualanException;
    
    public List<TransaksiPenjualan> showAllTransaksiPenjualan()throws TransaksiPenjualanException;
    
    public List<TransaksiPenjualan> getTransaksiPenjualan(String kataKunci, String berdasarkan)throws TransaksiPenjualanException;
    
    public List<TransaksiPenjualan> sortTransaksiPenjualan(String berdasarkan)throws TransaksiPenjualanException;
}

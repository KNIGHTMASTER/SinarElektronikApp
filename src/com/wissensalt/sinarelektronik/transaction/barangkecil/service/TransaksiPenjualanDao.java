package com.wissensalt.sinarelektronik.transaction.barangkecil.service;

import com.wissensalt.sinarelektronik.transaction.barangkecil.entity.TransaksiPenjualan;
import com.wissensalt.sinarelektronik.transaction.barangkecil.error.TransaksiPenjualanException;
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

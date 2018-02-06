/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Service;

import Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Entity.TransaksiPenjualanBB;
import Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Error.TransaksiPenjualanBBException;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface TransaksiPenjualanBBDao {
    
    public void deleteTransaksi(int id)throws TransaksiPenjualanBBException;
    
    public List<TransaksiPenjualanBB> selectAllTransaksi() throws TransaksiPenjualanBBException;
    
    public List<TransaksiPenjualanBB> searchTransaksi(String kataKunci, String berdasarkan) throws TransaksiPenjualanBBException;
    
    public List<TransaksiPenjualanBB> sortTransaksi(String berdasarkan) throws TransaksiPenjualanBBException;
    
}

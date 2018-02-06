/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.TransferBarangBesar.service;

import Sinarelektronikapp.TransferBarangBesar.entity.ProsesKasir;
import Sinarelektronikapp.TransferBarangBesar.error.penjualanException;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface penjualanDao {
    
    public void insertPenjualan(ProsesKasir prosesKasir) throws penjualanException;            
    
    public List<ProsesKasir> selectAllPenjualan() throws penjualanException;

    public void deletePenjualan(int no) throws penjualanException;
    
    public void truncatePenjualan()throws penjualanException;
    
}

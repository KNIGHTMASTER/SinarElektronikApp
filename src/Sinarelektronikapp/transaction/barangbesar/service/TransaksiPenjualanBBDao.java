package Sinarelektronikapp.transaction.barangbesar.service;

import Sinarelektronikapp.transaction.barangbesar.entity.TransaksiPenjualanBB;
import Sinarelektronikapp.transaction.barangbesar.error.TransaksiPenjualanBBException;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.ReturBarangBesar.transaksi.service;

import Sinarelektronikapp.ReturBarangBesar.transaksi.entity.Retur;
import Sinarelektronikapp.ReturBarangBesar.transaksi.error.ReturException;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface ReturDao {
    public  void insertRetur(Retur retur) throws ReturException;
    
    public void updateRetur(Retur retur) throws ReturException;
    
    public void deleteRetur(int id) throws ReturException;
    
    public List<Retur> showAllRetur() throws ReturException;
    
    public void truncateRetur()throws ReturException;
}

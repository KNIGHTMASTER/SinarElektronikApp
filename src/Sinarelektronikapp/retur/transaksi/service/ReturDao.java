/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.retur.transaksi.service;

import Sinarelektronikapp.retur.transaksi.entity.Retur;
import Sinarelektronikapp.retur.transaksi.error.ReturException;
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

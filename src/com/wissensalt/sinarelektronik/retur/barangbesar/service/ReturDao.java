/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.retur.barangbesar.service;

import com.wissensalt.sinarelektronik.retur.barangbesar.entity.Retur;
import com.wissensalt.sinarelektronik.retur.barangbesar.error.ReturException;
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

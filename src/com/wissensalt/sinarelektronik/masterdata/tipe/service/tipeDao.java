/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.tipe.service;

import com.wissensalt.sinarelektronik.masterdata.tipe.entity.tipe;
import com.wissensalt.sinarelektronik.masterdata.tipe.error.TipeException;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface tipeDao {
    
    public void insertTipe(tipe tipe) throws TipeException;
    
    public void updateTipe(tipe tipe) throws TipeException;
    
    public void deleteTipe(String idTipe) throws TipeException;
    
    public tipe getTipeByid(String idTipe) throws TipeException;
    
    public List<tipe> selectAllTipe() throws TipeException;
    
    public Integer getLastIdata() throws TipeException;
    
}

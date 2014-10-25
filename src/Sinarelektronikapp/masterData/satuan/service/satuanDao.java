/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.satuan.service;

import Sinarelektronikapp.masterData.satuan.entity.satuan;
import Sinarelektronikapp.masterData.satuan.error.SatuanException;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface satuanDao {
    
    public void insertSatuan(satuan satuan) throws SatuanException;
    
    public void updateSatuan(satuan satuan) throws SatuanException;
    
    public void deleteSatuan(String idSatuan) throws SatuanException;
    
    public satuan getTipeById(String idSatuan) throws SatuanException;
    
    public List<satuan> selectAllSatuan() throws SatuanException;
    
    public int getLastId() throws SatuanException;    
    
}

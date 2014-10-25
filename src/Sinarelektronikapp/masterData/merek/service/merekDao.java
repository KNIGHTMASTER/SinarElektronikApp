/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.merek.service;

import Sinarelektronikapp.masterData.merek.entity.merek;
import Sinarelektronikapp.masterData.merek.error.merekException;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface merekDao {
    
    public void insertMerek(merek merek) throws merekException;
    
    public void updateMerek(merek merek) throws merekException;
    
    public void deleteMerek(String idMerek) throws merekException;
    
    public List<merek> getMerekById(String idMerek) throws merekException;
    
    public List<merek> selectAllMerek() throws merekException;
    
    public int getLastId()throws  merekException;
       
}

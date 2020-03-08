/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterdata.tipe.model;

import Sinarelektronikapp.masterdata.tipe.database.tipeDatabase;
import Sinarelektronikapp.masterdata.tipe.entity.tipe;
import Sinarelektronikapp.masterdata.tipe.error.TipeException;
import Sinarelektronikapp.masterdata.tipe.model.event.tipeListener;
import Sinarelektronikapp.masterdata.tipe.service.tipeDao;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class tipeModel {
    private String idtipe, namaTipe;

    tipeListener listener;

    public tipeListener getListener() {
        return listener;
    }

    public void setListener(tipeListener listener) {
        this.listener = listener;
    }
        
    public String getIdtipe() {
        return idtipe;
    }

    public void setIdtipe(String idtipe) {
        this.idtipe = idtipe;
    }

    public String getNamaTipe() {
        return namaTipe;
    }

    public void setNamaTipe(String namaTipe) {
        this.namaTipe = namaTipe;
    }    
    
    protected void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }
    
     protected void fireOnInsert(tipe tipe){
        if(listener!=null){
            listener.onInsert(tipe);
        }
    } 
    
    protected void fireOnUpdate(tipe tipe){
        if(listener!=null){
            listener.onUpdate(tipe);
        }
    }    
    
    protected void fireOnDelete(){
        if(listener!=null){
            listener.onDelete();
        }
    }    
    
    public void resetTipe(){
        setIdtipe("");
        setNamaTipe("");
    }
    
    public void insertTipe() throws SQLException, TipeException{
        tipeDao dao = tipeDatabase.getTipeDao();
        tipe tipe = new tipe();        
        tipe.setIdtipe(idtipe);
        tipe.setNamaTipe(namaTipe);
        dao.insertTipe(tipe);
        fireOnInsert(tipe);
    }
    
    public void updateTipe() throws SQLException, TipeException{
        tipeDao dao = tipeDatabase.getTipeDao();
        tipe tipe = new tipe();        
        tipe.setIdtipe(idtipe);
        tipe.setNamaTipe(namaTipe);
        dao.updateTipe(tipe);
        fireOnUpdate(tipe);     
    }
    
    public void deleteTipe() throws SQLException, TipeException{
        tipeDao dao = tipeDatabase.getTipeDao();
        dao.deleteTipe(idtipe);
        fireOnDelete();
    }
}

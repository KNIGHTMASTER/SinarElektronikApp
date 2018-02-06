/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterData.penjualbk.model;

import Sinarelektronikapp.masterData.penjualbk.database.tipeDatabase;
import Sinarelektronikapp.masterData.penjualbk.entity.tipe;
import Sinarelektronikapp.masterData.penjualbk.error.TipeException;
import Sinarelektronikapp.masterData.penjualbk.model.event.tipeListener;
import Sinarelektronikapp.masterData.penjualbk.service.tipeDao;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class tipeModel {
    private String idtipe, namaTipe;

    private int insentif;
    
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

    public int getInsentif() {
        return insentif;
    }

    public void setInsentif(int insentif) {
        this.insentif = insentif;
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
        setInsentif(0);
    }
    
    public void insertTipe() throws SQLException, TipeException{
        tipeDao dao = tipeDatabase.getTipeDao();
        tipe tipe = new tipe();        
        tipe.setIdtipe(idtipe);
        tipe.setNamaTipe(namaTipe);
        tipe.setInsentif(insentif);
        dao.insertTipe(tipe);
        fireOnInsert(tipe);
    }
    
    public void updateTipe() throws SQLException, TipeException{
        tipeDao dao = tipeDatabase.getTipeDao();
        tipe tipe = new tipe();        
        tipe.setIdtipe(idtipe);
        tipe.setNamaTipe(namaTipe);
        tipe.setInsentif(insentif);
        dao.updateTipe(tipe);
        fireOnUpdate(tipe);     
    }
    
    public void deleteTipe() throws SQLException, TipeException{
        tipeDao dao = tipeDatabase.getTipeDao();
        dao.deleteTipe(idtipe);
        fireOnDelete();
    }
}

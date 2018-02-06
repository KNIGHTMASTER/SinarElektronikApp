/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterData.penjualbb.model;

import Sinarelektronikapp.masterData.penjualbb.database.tipeDatabase;
import Sinarelektronikapp.masterData.penjualbb.entity.tipe;
import Sinarelektronikapp.masterData.penjualbb.error.TipeException;
import Sinarelektronikapp.masterData.penjualbb.model.event.tipeListener;
import Sinarelektronikapp.masterData.penjualbb.service.tipeDao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public int getInsentif() {
        return insentif;
    }

    public void setInsentif(int insentif) {
        this.insentif = insentif;
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
        setInsentif(0);
    }
    
    public void insertTipe(){
        tipeDao dao = null;
        try {
            dao = tipeDatabase.getTipeDao();
        } catch (SQLException ex) {
            Logger.getLogger(tipeModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        tipe tipe = new tipe();        
        tipe.setIdtipe(idtipe);
        tipe.setNamaTipe(namaTipe);
        tipe.setInsentif(insentif);
        try {
            dao.insertTipe(tipe);
        } catch (TipeException ex) {
            Logger.getLogger(tipeModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireOnInsert(tipe);
    }
    
    public void updateTipe(){
        tipeDao dao = null;
        try {
            dao = tipeDatabase.getTipeDao();
        } catch (SQLException ex) {
            Logger.getLogger(tipeModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        tipe tipe = new tipe();        
        tipe.setIdtipe(idtipe);
        tipe.setNamaTipe(namaTipe);
        tipe.setInsentif(insentif);
        try {
            dao.updateTipe(tipe);
        } catch (TipeException ex) {
            Logger.getLogger(tipeModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireOnUpdate(tipe);     
    }
    
    public void deleteTipe(){
        tipeDao dao = null;
        try {
            dao = tipeDatabase.getTipeDao();
        } catch (SQLException ex) {
            Logger.getLogger(tipeModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dao.deleteTipe(idtipe);
        } catch (TipeException ex) {
            Logger.getLogger(tipeModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireOnDelete();
    }
}

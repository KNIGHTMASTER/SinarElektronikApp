package com.wissensalt.sinarelektronik.masterdata.tipe.model;

import com.wissensalt.sinarelektronik.masterdata.tipe.entity.TipeDTO;
import com.wissensalt.sinarelektronik.masterdata.tipe.model.event.TipeListener;
import com.wissensalt.sinarelektronik.model.BaseModel;

/**
 *
 * @author Fauzi
 */
public class TipeModel extends BaseModel {
    private String idTipe, namaTipe;

    private TipeListener listener;

    public TipeListener getListener() {
        return listener;
    }

    public void setListener(TipeListener listener) {
        this.listener = listener;
    }
        
    public String getIdTipe() {
        return idTipe;
    }

    public void setIdTipe(String idTipe) {
        this.idTipe = idTipe;
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
    
     protected void fireOnInsert(TipeDTO TipeDTO){
        if(listener!=null){
            listener.onInsert(TipeDTO);
        }
    } 
    
    protected void fireOnUpdate(TipeDTO TipeDTO){
        if(listener!=null){
            listener.onUpdate(TipeDTO);
        }
    }    
    
    protected void fireOnDelete(){
        if(listener!=null){
            listener.onDelete();
        }
    }    
    
    public void resetTipe(){
        setIdTipe("");
        setNamaTipe("");
    }
    
    public void insertTipe(TipeDTO tipeDTO) {
        fireOnInsert(tipeDTO);
    }
    
    public void updateTipe(TipeDTO tipeDTO) {
        fireOnUpdate(tipeDTO);
    }
    
    public void deleteTipe() {
        fireOnDelete();
    }
}

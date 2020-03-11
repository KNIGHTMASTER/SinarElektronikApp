package com.wissensalt.sinarelektronik.model;

import com.wissensalt.sinarelektronik.dto.MerekDTO;
import com.wissensalt.sinarelektronik.masterdata.merek.model.event.merekListener;
import com.wissensalt.sinarelektronik.model.BaseModel;

/**
 *
 * @author Fauzi
 */
public class MerekModel extends BaseModel {
    private String idMerek, namaMerek;
    private merekListener listener;

    public String getIdMerek() {
        return idMerek;
    }

    public void setIdMerek(String idMerek) {
        this.idMerek = idMerek;
        fireOnChange();
    }

    public String getNamaMerek() {
        return namaMerek;
    }

    public void setNamaMerek(String namaMerek) {
        this.namaMerek = namaMerek;
        fireOnChange();
    }

    public merekListener getListener() {
        return listener;
    }

    public void setListener(merekListener listener) {
        this.listener = listener;
    }
    
    protected void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }
    
    protected void fireOnInsert(MerekDTO merekDTO){
        if(listener!=null){
            listener.onInsert(merekDTO);
        }
    }
    
     protected void fireOnUpdate(MerekDTO merekDTO){
        if(listener!=null){
            listener.onUpdate(merekDTO);
        }
    }
     
    protected void fireOnDelete(){
        if(listener!=null){
            listener.onDelete();
        }
    }
    
    public void resetMerek(){
        setIdMerek("");
        setNamaMerek("");
    }
    
    public void insertMerek(MerekDTO merekDTO) {
        fireOnInsert(merekDTO);
    }
    
    public void updateMerek(MerekDTO merekDTO) {
        fireOnUpdate(merekDTO);
    }    
    
    public void deleteMerek() {
        fireOnDelete();
    }
}

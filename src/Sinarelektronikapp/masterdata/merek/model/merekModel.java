/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterdata.merek.model;

import Sinarelektronikapp.masterdata.merek.database.merekDatabase;
import Sinarelektronikapp.masterdata.merek.entity.merek;
import Sinarelektronikapp.masterdata.merek.error.merekException;
import Sinarelektronikapp.masterdata.merek.model.event.merekListener;
import Sinarelektronikapp.masterdata.merek.service.merekDao;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class merekModel {
    private String idmerek, namamerek;
    
    merekListener listener;

    public String getIdmerek() {
        return idmerek;
    }

    public void setIdmerek(String idmerek) {
        this.idmerek = idmerek;
        fireOnChange();
    }

    public String getNamamerek() {
        return namamerek;
    }

    public void setNamamerek(String namamerek) {
        this.namamerek = namamerek;
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
    
    protected void fireOnInsert(merek merek){
        if(listener!=null){
            listener.onInsert(merek);
        }
    }
    
     protected void fireOnUpdate(merek merek){
        if(listener!=null){
            listener.onUpdate(merek);
        }
    }
     
    protected void fireOnDelete(){
        if(listener!=null){
            listener.onDelete();
        }
    }
    
    public void resetMerek(){
        setIdmerek("");
        setNamamerek("");
    }
    
    public void insertMerek() throws SQLException, merekException{
        merekDao dao = merekDatabase.getMerekDao();
        merek merek = new merek();
        merek.setIdmerek(idmerek);
        merek.setNamamerek(namamerek);
        dao.insertMerek(merek);
        
        fireOnInsert(merek);
    }
    
    public void updateMerek() throws SQLException, merekException{
        merekDao dao = merekDatabase.getMerekDao();
        merek merek = new merek();
        merek.setIdmerek(idmerek);
        merek.setNamamerek(namamerek);
        dao.updateMerek(merek);
        
        fireOnUpdate(merek);
    }    
    
    public void deleteMerek() throws SQLException, merekException{
        merekDao dao = merekDatabase.getMerekDao();
        dao.deleteMerek(idmerek);
        fireOnDelete();
    }
    
    
}

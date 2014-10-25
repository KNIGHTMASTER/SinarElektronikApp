/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterData.satuan.model;

import Sinarelektronikapp.masterData.satuan.database.satuanDatabase;
import Sinarelektronikapp.masterData.satuan.entity.satuan;
import Sinarelektronikapp.masterData.satuan.error.SatuanException;
import Sinarelektronikapp.masterData.satuan.model.event.satuanListener;
import Sinarelektronikapp.masterData.satuan.service.satuanDao;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class satuanModel {
    private String idsatuan, namasatuan;

    satuanListener listener;

    public satuanListener getListener() {
        return listener;
    }

    public void setListener(satuanListener listener) {
        this.listener = listener;
    }

    public String getIdsatuan() {
        return idsatuan;
    }

    public void setIdsatuan(String idsatuan) {
        this.idsatuan = idsatuan;
    }

    public String getNamasatuan() {
        return namasatuan;
    }

    public void setNamasatuan(String namasatuan) {
        this.namasatuan = namasatuan;
    }
        
    
    protected void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }
    
     protected void fireOnInsert(satuan satuan){
        if(listener!=null){
            listener.onInsert(satuan);
        }
    } 
    
    protected void fireOnUpdate(satuan satuan){
        if(listener!=null){
            listener.onUpdate(satuan);
        }
    }    
    
    protected void fireOnDelete(){
        if(listener!=null){
            listener.onDelete();
        }
    }    
    
    public void resetSatuan(){
        setIdsatuan("");
        setNamasatuan("");
    }
    
    public void insertSatuan() throws SQLException, SatuanException{
        satuanDao dao = satuanDatabase.getSatuanDao();
        satuan satuan = new satuan();        
        satuan.setIdsatuan(idsatuan);
        satuan.setNamasatuan(namasatuan);
        dao.insertSatuan(satuan);
        fireOnInsert(satuan);
    }
    
    public void updateSatuan() throws SQLException, SatuanException{
        satuanDao dao = satuanDatabase.getSatuanDao();
        satuan satuan = new satuan();        
        satuan.setIdsatuan(idsatuan);
        satuan.setNamasatuan(namasatuan);
        dao.updateSatuan(satuan);
        fireOnUpdate(satuan);     
    }
    
    public void deleteSatuan() throws SQLException, SatuanException{
        satuanDao dao = satuanDatabase.getSatuanDao();
        dao.deleteSatuan(idsatuan);
        fireOnDelete();
    }
}

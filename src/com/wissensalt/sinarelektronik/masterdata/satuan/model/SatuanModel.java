package com.wissensalt.sinarelektronik.masterdata.satuan.model;

import com.wissensalt.sinarelektronik.dao.SatuanDAO;
import com.wissensalt.sinarelektronik.dao.impl.SatuanDAOImpl;
import com.wissensalt.sinarelektronik.masterdata.satuan.entity.SatuanDTO;
import com.wissensalt.sinarelektronik.masterdata.satuan.model.event.satuanListener;

/**
 *
 * @author Fauzi
 */
public class SatuanModel {
    private String idsatuan, namaSatuan;
    private SatuanDAO satuanDAO;
    private satuanListener listener;

    public SatuanModel() {
        this.satuanDAO = new SatuanDAOImpl();
    }

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

    public String getNamaSatuan() {
        return namaSatuan;
    }

    public void setNamaSatuan(String namaSatuan) {
        this.namaSatuan = namaSatuan;
    }
        
    
    protected void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }
    
     protected void fireOnInsert(SatuanDTO SatuanDTO){
        if(listener!=null){
            listener.onInsert(SatuanDTO);
        }
    } 
    
    protected void fireOnUpdate(SatuanDTO SatuanDTO){
        if(listener!=null){
            listener.onUpdate(SatuanDTO);
        }
    }    
    
    protected void fireOnDelete(){
        if(listener!=null){
            listener.onDelete();
        }
    }    
    
    public void resetSatuan(){
        setIdsatuan("");
        setNamaSatuan("");
    }
    
    public void insertSatuan() {
        SatuanDTO satuanDTO = new SatuanDTO();
        satuanDTO.setIdsatuan(idsatuan);
        satuanDTO.setNamaSatuan(namaSatuan);
        satuanDAO.insert(satuanDTO);
        fireOnInsert(satuanDTO);
    }
    
    public void updateSatuan() {
        SatuanDTO satuanDTO = new SatuanDTO();
        satuanDTO.setIdsatuan(idsatuan);
        satuanDTO.setNamaSatuan(namaSatuan);
        satuanDAO.insert(satuanDTO);
        fireOnUpdate(satuanDTO);
    }
    
    public void deleteSatuan() {
        satuanDAO.deleteByString(idsatuan);
        fireOnDelete();
    }
}

package com.wissensalt.sinarelektronik.masterdata.namabarang.model;

import com.wissensalt.sinarelektronik.masterdata.namabarang.entity.NamaBarangDTO;
import com.wissensalt.sinarelektronik.masterdata.namabarang.model.event.NamaBarangListener;

/**
 *
 * @author Fauzi
 */
public class NamaBarangModel {

    private int id;
    private String namabarang;
    private NamaBarangListener listener;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaBarang() {
        return namabarang;
    }

    public void setNamaBarang(String namabarang) {
        this.namabarang = namabarang;
        fireOnChange();
    }

    public NamaBarangListener getListener() {
        return listener;
    }

    public void setListener(NamaBarangListener listener) {
        this.listener = listener;
    }
    
    protected void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }
    
    protected void fireOnInsert(NamaBarangDTO namabarang){
        if(listener!=null){
            listener.onInsert(namabarang);
        }
    }
    
     protected void fireOnUpdate(NamaBarangDTO namabarang){
        if(listener!=null){
            listener.onUpdate(namabarang);
        }
    }
     
    protected void fireOnDelete(){
        if(listener!=null){
            listener.onDelete();
        }
    }
    
    public void resetNamaBarang(){
        setId(0);
        setNamaBarang(namabarang);
    }
    
    public void insertNamaBarang(NamaBarangDTO namaBarangDTO) {
        fireOnInsert(namaBarangDTO);
    }
    
    public void updateNamaBarang(NamaBarangDTO namaBarangDTO){
        fireOnUpdate(namaBarangDTO);
    }    
    
    public void deleteNamaBarang() {
        fireOnDelete();
    }
    
}

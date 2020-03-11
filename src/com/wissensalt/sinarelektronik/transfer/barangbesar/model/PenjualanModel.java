/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.transfer.barangbesar.model;

import com.wissensalt.sinarelektronik.transfer.barangbesar.database.penjualanDatabase;
import com.wissensalt.sinarelektronik.transfer.barangbesar.entity.ProsesKasir;
import com.wissensalt.sinarelektronik.transfer.barangbesar.error.penjualanException;
import com.wissensalt.sinarelektronik.transfer.barangbesar.model.event.penjualanListener;
import com.wissensalt.sinarelektronik.transfer.barangbesar.service.penjualanDao;

/**
 *
 * @author Fauzi
 */
public class PenjualanModel {
       private String kode, nama;
       
       private int jml, no;
    
    private penjualanListener listener;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
        fireOnChange();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
        fireOnChange();
    }

    public int getJml() {
        return jml;
    }

    public void setJml(int jml) {
        this.jml = jml;
        fireOnChange();
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public penjualanListener getListener() {
        return listener;
    }

    public void setListener(penjualanListener listener) {
        this.listener = listener;
    }

       
    protected void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }
    
    protected void fireOnInsert(ProsesKasir prosesKasir){
        if(listener!=null){
            listener.onInsert(prosesKasir);
        }
    }
    
    protected void fireOnDelete(){
        if(listener!=null){
            listener.onDelete();
        }
    }
    
    protected void fireOnTruncate(){
        if(listener!=null){
            listener.onTruncate();
        }
    }
    
    public void insertPenjualan() throws penjualanException {
        penjualanDao dao=penjualanDatabase.getPenjualanDao();
        
        ProsesKasir prosesKasir=new ProsesKasir();        
        prosesKasir.setKode(kode);
        prosesKasir.setNama(nama);
        prosesKasir.setJml(jml);        
        dao.insertPenjualan(prosesKasir);
        
        fireOnInsert(prosesKasir);
    }
    
    public void deletePenjualan() throws penjualanException{
        penjualanDao dao=penjualanDatabase.getPenjualanDao();
        dao.deletePenjualan(no);
        fireOnDelete();
    }
    
    public void TruncatePenjualan() throws penjualanException{
        penjualanDao dao=penjualanDatabase.getPenjualanDao();
        dao.truncatePenjualan();
        fireOnTruncate();
    }
    
}

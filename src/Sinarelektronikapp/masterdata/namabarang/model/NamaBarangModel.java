/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.namabarang.model;

import Sinarelektronikapp.masterdata.namabarang.Entity.NamaBarang;
import Sinarelektronikapp.masterdata.namabarang.database.NamaBarangDatabase;
import Sinarelektronikapp.masterdata.namabarang.error.NamaBarangException;
import Sinarelektronikapp.masterdata.namabarang.model.event.NamaBarangListener;
import Sinarelektronikapp.masterdata.namabarang.service.NamaBarangDao;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class NamaBarangModel {

    public int id;
    public String namabarang;
    
    NamaBarangListener listener;

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
    
    protected void fireOnInsert(NamaBarang namabarang){
        if(listener!=null){
            listener.onInsert(namabarang);
        }
    }
    
     protected void fireOnUpdate(NamaBarang namabarang){
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
    
    public void insertNamaBarang() throws SQLException, NamaBarangException{
        NamaBarangDao dao = NamaBarangDatabase.getNamaBarangDao();
        NamaBarang nbarang = new NamaBarang();
        nbarang.setId(id);
        nbarang.setNamabarang(namabarang);
        dao.insertNamaBarang(nbarang);
        
        fireOnInsert(nbarang);
    }
    
    public void updateNamaBarang() throws SQLException, NamaBarangException{
        NamaBarangDao dao = NamaBarangDatabase.getNamaBarangDao();
        NamaBarang nbarang = new NamaBarang();
        nbarang.setId(id);
        nbarang.setNamabarang(namabarang);
        dao.updateNamaBarang(nbarang);
        
        fireOnUpdate(nbarang);
    }    
    
    public void deleteNamaBarang() throws SQLException, NamaBarangException{
        NamaBarangDao dao = NamaBarangDatabase.getNamaBarangDao();
        dao.deleteNamaBarang(id);
        fireOnDelete();
    }
    
}

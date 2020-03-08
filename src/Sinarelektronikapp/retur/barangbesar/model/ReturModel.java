/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.retur.barangbesar.model;

import Sinarelektronikapp.retur.barangbesar.database.ReturDatabase;
import Sinarelektronikapp.retur.barangbesar.entity.Retur;
import Sinarelektronikapp.retur.barangbesar.error.ReturException;
import Sinarelektronikapp.retur.barangbesar.model.Event.ReturListener;
import Sinarelektronikapp.retur.barangbesar.service.ReturDao;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class ReturModel {
    int id, jumlah, subharga;
    String user, jam, kode, nama, tanggal, asalBarang, penukaran;

    ReturListener listener;
    
    public ReturModel() {
    }

    public String getAsalBarang() {
        return asalBarang;
    }

    public void setAsalBarang(String asalBarang) {
        this.asalBarang = asalBarang;
    }

    public String getPenukaran() {
        return penukaran;
    }

    public void setPenukaran(String penukaran) {
        this.penukaran = penukaran;
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getSubharga() {
        return subharga;
    }

    public void setSubharga(int subharga) {
        this.subharga = subharga;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public ReturListener getListener() {
        return listener;
    }

    public void setListener(ReturListener listener) {
        this.listener = listener;
    }

    public void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }
    
    public void fireOnInsert(Retur retur){
        if(listener!=null){
            listener.onInsert(retur);
        }
    }

     public void fireOnUpdate(Retur retur){
        if(listener!=null){
            listener.onUpdate(retur);
        }
    }
     
     public void fireOnDelete(){
         if(listener!=null){
             listener.onDelete();
         }
     }     
     
     public void insertRetur(){
         Retur r = new Retur();
         ReturDao dao = ReturDatabase.getReturDao();
         r.setId(id);
         r.setUser(user);
         r.setTanggal(tanggal);
         r.setJam(jam);
         r.setKode(kode);
         r.setNama(nama);
         r.setJumlah(jumlah);
         r.setSubharga(subharga);
         r.setAsalBarang(asalBarang);
         r.setPenukaran(penukaran);         
        try {
            dao.insertRetur(r);
        } catch (ReturException ex) {
            Logger.getLogger(ReturModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        fireOnInsert(r);
     }
     
     
     public void updateRetur(){
         Retur r = new Retur();
         ReturDao dao = ReturDatabase.getReturDao();
         r.setId(id);
         r.setUser(user);
         r.setTanggal(tanggal);
         r.setJam(jam);
         r.setKode(kode);
         r.setNama(nama);
         r.setJumlah(jumlah);
         r.setSubharga(subharga);
         r.setAsalBarang(asalBarang);
         r.setPenukaran(penukaran);
        try {
            dao.updateRetur(r);
        } catch (ReturException ex) {
            Logger.getLogger(ReturModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        fireOnUpdate(r);         
     }
     
     public void deleteRetur(){
         ReturDao dao = ReturDatabase.getReturDao();
        try {
            dao.deleteRetur(id);
        } catch (ReturException ex) {
            Logger.getLogger(ReturModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        fireOnDelete();
     }

     public void truncateRetur(){
         ReturDao dao = ReturDatabase.getReturDao();
        try {
            dao.truncateRetur();
        } catch (ReturException ex) {
            Logger.getLogger(ReturModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireOnTruncate();
     }
     
     public void fireOnTruncate(){
         if(listener!=null){
             listener.onTruncate();
         }
     }
}

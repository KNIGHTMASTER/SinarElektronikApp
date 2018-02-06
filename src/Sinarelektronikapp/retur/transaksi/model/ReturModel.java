/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.retur.transaksi.model;

import Sinarelektronikapp.retur.transaksi.database.ReturDatabase;
import Sinarelektronikapp.retur.transaksi.entity.Retur;
import Sinarelektronikapp.retur.transaksi.error.ReturException;
import Sinarelektronikapp.retur.transaksi.model.Event.ReturListener;
import Sinarelektronikapp.retur.transaksi.service.ReturDao;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class ReturModel {
    int id, jumlah, subharga, harga;
    String user, jam, kode, nama, tanggal, penukarang, supplier;

    
    ReturListener listener;
    
    public ReturModel() {
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

        
    public String getPenukarang() {
        return penukarang;
    }

    public void setPenukarang(String penukarang) {
        this.penukarang = penukarang;
    }

    
    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
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
         r.setHarga(harga);
         r.setPenukaran(penukarang);
         r.setSubharga(subharga);
         r.setSupplier(supplier);
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
         r.setHarga(harga);
         r.setPenukaran(penukarang);
         r.setSubharga(subharga);
         r.setSupplier(supplier);
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

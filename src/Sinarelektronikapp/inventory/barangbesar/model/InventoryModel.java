package Sinarelektronikapp.inventory.barangbesar.model;

import Sinarelektronikapp.inventory.barangbesar.database.InventoryDatabase;
import Sinarelektronikapp.inventory.barangbesar.entity.InventoryDTO;
import Sinarelektronikapp.inventory.barangbesar.error.InventoryException;
import Sinarelektronikapp.inventory.barangbesar.model.Event.InventoryListener;
import Sinarelektronikapp.inventory.barangbesar.service.InventoryDao;
import Sinarelektronikapp.model.BaseModel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class InventoryModel extends BaseModel {
    int id, jumlah, harga, ekspedisi, subharga;
    
    String user, tanggal, jam, kode, nama;
    
    InventoryListener listener;

    public InventoryModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        //fireOnChange();
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
        //fireOnChange();
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
        //fireOnChange();
    }

    public int getEkspedisi() {
        return ekspedisi;
    }

    public void setEkspedisi(int ekspedisi) {
        this.ekspedisi = ekspedisi;
        //fireOnChange();
    }

    public int getSubharga() {
        return subharga;
    }

    public void setSubharga(int subharga) {
        this.subharga = subharga;
        //fireOnChange();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
        //fireOnChange();
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
        //fireOnChange();
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
        //fireOnChange();
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
        //fireOnChange();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
        //fireOnChange();
    }

    public InventoryListener getListener() {
        return listener;
    }

    public void setListener(InventoryListener listener) {
        this.listener = listener;
        //fireOnChange();
    }
    
    public void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }
    
    public void fireOnInsert(InventoryDTO inventory){
        if(listener!=null){
            listener.onInsert(inventory);
        }
    }
    
    public void fireOnDelete(){
        if(listener!=null){
            listener.onDelete();
        }
    }
    
    public void fireOnTruncate(){
        if(listener!=null){
            listener.onTruncate();
        }
    }
    
    public void insertProsesInventory(){
        InventoryDao dao = InventoryDatabase.getInventoryDao();
        
        InventoryDTO inventory = new InventoryDTO();        
        inventory.setId(id);
        inventory.setUser(user);
        inventory.setTanggal(tanggal);
        inventory.setJam(jam);
        inventory.setKode(kode);
        inventory.setNama(nama);
        inventory.setHarga(harga);
        inventory.setEkspedisi(ekspedisi);
        inventory.setJumlah(jumlah);
        inventory.setSubharga(subharga);
        
        try {
            dao.insertProsesInventory(inventory);
        } catch (InventoryException ex) {
            Logger.getLogger(InventoryModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fireOnInsert(inventory);
    }
    
    public void deleteProsesInventory(){
        InventoryDao dao = InventoryDatabase.getInventoryDao();
        try {
            dao.deleteProsesInventory(id);
        } catch (InventoryException ex) {
            Logger.getLogger(InventoryModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fireOnDelete();
    }
    
    public void truncateProsesInventory(){
        InventoryDao dao = InventoryDatabase.getInventoryDao();
        try {
            dao.truncateProsesInventory();
        } catch (InventoryException ex) {
            Logger.getLogger(InventoryModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fireOnTruncate();
    }
    
    
}

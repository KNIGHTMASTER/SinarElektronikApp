/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.inventory.model;

import Sinarelektronikapp.inventory.database.InventoryDatabase;
import Sinarelektronikapp.inventory.entity.Inventory;
import Sinarelektronikapp.inventory.error.InventoryException;
import Sinarelektronikapp.inventory.model.Event.InventoryListener;
import Sinarelektronikapp.inventory.service.InventoryDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class InventoryModel {
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
    
    public void fireOnInsert(Inventory inventory){
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
        
        Inventory inventory = new Inventory();        
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

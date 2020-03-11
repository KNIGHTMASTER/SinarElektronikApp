package com.wissensalt.sinarelektronik.model;

import com.wissensalt.sinarelektronik.dto.InventoryBarangKecilDTO;
import com.wissensalt.sinarelektronik.inventory.barangkecil.model.Event.InventoryBarangKecilListener;
import com.wissensalt.sinarelektronik.model.BaseModel;

/**
 *
 * @author Fauzi
 */
public class InventoryBarangKecilModel extends BaseModel {
    private int id, jumlah, harga, ekspedisi, subharga;    
    private String user, tanggal, jam, kode, nama;
    
    private InventoryBarangKecilListener listener;

    public InventoryBarangKecilModel() {
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

    public InventoryBarangKecilListener getListener() {
        return listener;
    }

    public void setListener(InventoryBarangKecilListener listener) {
        this.listener = listener;
        //fireOnChange();
    }
    
    public void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }
    
    public void fireOnInsert(InventoryBarangKecilDTO inventoryBarangKecilDTO){
        if(listener!=null){
            listener.onInsert(inventoryBarangKecilDTO);
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
}

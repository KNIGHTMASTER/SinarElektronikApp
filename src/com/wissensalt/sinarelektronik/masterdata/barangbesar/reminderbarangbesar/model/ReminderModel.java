package com.wissensalt.sinarelektronik.masterdata.barangbesar.reminderbarangbesar.model;

import com.wissensalt.sinarelektronik.dto.BarangBesarDTO;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.reminderbarangbesar.model.event.ReminderListener;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public class ReminderModel {
    
    private String cari, cmbcari, cmbsort;

    public ReminderModel() {
    }


    private String idBarang, idBarcode, namaBarang, tipe, merek, satuan, supplier, keterangan, kategori;

    private int stok, stokMinimum, harga;
    

    private ReminderListener listener ;

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
        fireOnChange();
    }
    
    public ReminderListener getListener() {
        return listener;
    }

    public void setListener(ReminderListener listener) {
        this.listener = listener;
    }

        
    public String getCari() {
        return cari;
    }

    public void setCari(String cari) {
        this.cari = cari;
        fireOnChange();
    }

    public String getCmbcari() {
        return cmbcari;
    }

    public void setCmbcari(String cmbcari) {
        this.cmbcari = cmbcari;
        fireOnChange();
    }

    public String getCmbsort() {
        return cmbsort;
    }

    public void setCmbsort(String cmbsort) {
        this.cmbsort = cmbsort;
    }

    ///////////////////////////////

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
        fireOnChange();
    }

    public String getIdBarcode() {
        return idBarcode;
    }

    public void setIdBarcode(String idBarcode) {
        this.idBarcode = idBarcode;
        fireOnChange();
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
        fireOnChange();
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
        fireOnChange();
    }

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
        fireOnChange();
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
        fireOnChange();
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
        fireOnChange();
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
        fireOnChange();
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
        fireOnChange();
    }

    public int getStokMinimum() {
        return stokMinimum;
    }

    public void setStokMinimum(int stokMinimum) {
        this.stokMinimum = stokMinimum;
        fireOnChange();
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
        fireOnChange();
    }

    protected void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }

    protected void fireOnInsert(BarangBesarDTO barangBesarDTO){
        if(listener!=null){
            listener.onInsert(barangBesarDTO);
        }
    }

    protected void fireOnUpdate(BarangBesarDTO barangBesarDTO){
        if(listener!=null){
            listener.onUpdate(barangBesarDTO);
        }
    }

    protected void fireOnDelete(){
        if(listener!=null){
            listener.onDelete();
        }
    }

    protected void fireOnSearch(List list){
        if(listener!=null){
            listener.onSearch(list);
        }
    }

    protected void fireOnSort(List list){
        if(listener!=null){
            listener.onSort(list);
        }
    }

    public void resetBarang(){
       setCari("");
       setCmbcari(cmbcari);
       setCmbsort(cmbsort);
    }

    public void search(List list) {
        fireOnSearch(list);
    }

    public void sort(List list) {
        fireOnSort(list);
    }
}

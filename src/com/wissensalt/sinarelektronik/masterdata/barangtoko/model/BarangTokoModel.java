package com.wissensalt.sinarelektronik.masterdata.barangtoko.model;

import com.wissensalt.sinarelektronik.masterdata.barangtoko.entity.BarangTokoDTO;
import com.wissensalt.sinarelektronik.masterdata.barangtoko.model.event.BarangTokoListener;
import com.wissensalt.sinarelektronik.model.BaseModel;

import java.util.List;

/**
 *
 * @author Fauzi
 */
public class BarangTokoModel extends BaseModel {

    private String cari, cmbcari, cmbsort;
    private String idBarang, idBarcode, namaBarang, tipe, merek, satuan, supplier, keterangan, kategori;
    private int stok, stokMinimum, harga;
    private BarangTokoListener listener ;

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
        fireOnChange();
    }
    

    public BarangTokoListener getListener() {
        return listener;
    }

    public void setListener(BarangTokoListener listener) {
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

    protected void fireOnInsert(BarangTokoDTO barang){
        if(listener!=null){
            listener.onInsert(barang);
        }
    }

    protected void fireOnUpdate(BarangTokoDTO barang){
        if(listener!=null){
            listener.onUpdate(barang);
        }
    }

    protected void fireOnDelete(){
        if(listener!=null){
            listener.onDelete();
        }
    }

    protected void fireOnSearch(List list){
        if(listener!=null){
            listener.onSearchToko(list);
        }
    }

    protected void fireOnSort(List list){
        if(listener!=null){
            listener.onSortToko(list);
        }
    }

    public void resetBarang(){
       setCari("");
       setCmbcari(cmbcari);
       setCmbsort(cmbsort);
    }

    public void deleteBarang() {
        fireOnDelete();
    }

    public void findByField(List list) {
        fireOnSearch(list);
    }

    public void sortByField(List list) {
        fireOnSort(list);
    }
}

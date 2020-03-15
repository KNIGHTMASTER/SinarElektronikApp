package com.wissensalt.sinarelektronik.masterdata.barangkecil.model;

import com.wissensalt.sinarelektronik.masterdata.barangkecil.entity.BarangKecilDTO;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.model.event.BarangKecilListener;
import com.wissensalt.sinarelektronik.model.BaseModel;

import java.util.List;

/**
 *
 * @author Fauzi
 */
public class BarangKecilModel extends BaseModel {

    private String cari, cmbCari, cmbSort;
    private String idBarang, idBarcode, namaBarang, tipe, merek, satuan, supplier, keterangan, kategori;
    private int stok, stokMinimum, harga, grosir2;
    private BarangKecilListener listener ;

    public BarangKecilModel() {
    }

    public int getGrosir2() {
        return grosir2;
    }

    public void setGrosir2(int grosir2) {
        this.grosir2 = grosir2;
    }

    
    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
        fireOnChange();
    }
    

    public BarangKecilListener getListener() {
        return listener;
    }

    public void setListener(BarangKecilListener listener) {
        this.listener = listener;
    }
        
    public String getCari() {
        return cari;
    }

    public void setCari(String cari) {
        this.cari = cari;
        fireOnChange();
    }

    public String getCmbCari() {
        return cmbCari;
    }

    public void setCmbCari(String cmbCari) {
        this.cmbCari = cmbCari;
        fireOnChange();
    }

    public String getCmbSort() {
        return cmbSort;
    }

    public void setCmbSort(String cmbSort) {
        this.cmbSort = cmbSort;
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

    protected void fireOnInsert(BarangKecilDTO BarangKecilDTO){
        if(listener!=null){
            listener.onInsert(BarangKecilDTO);
        }
    }

    protected void fireOnUpdate(BarangKecilDTO BarangKecilDTO){
        if(listener!=null){
            listener.onUpdate(BarangKecilDTO);
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
       setCmbCari(cmbCari);
       setCmbSort(cmbSort);
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

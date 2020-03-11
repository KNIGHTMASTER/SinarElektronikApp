/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.tambahbarang.besar.model;

import com.wissensalt.sinarelektronik.dto.BarangBesarDTO;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.model.event.tambahBarangListener;
import com.wissensalt.sinarelektronik.masterdata.tambahbarang.besar.database.TambahbarangDatabase;
import com.wissensalt.sinarelektronik.masterdata.tambahbarang.besar.error.TambahBarangException;
import com.wissensalt.sinarelektronik.masterdata.tambahbarang.besar.service.TambahBarangDao;
import java.io.File;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class tambahBarangModel {

    private String idBarang, idBarcode, namaBarang, tipe, merek, satuan, supplier, keterangan, garansi, kategori;
    
    private File gambar;

    private int stok, stokMinimum, hargaModal, eceran, grosir, lamaGaransi;

    public File getGambar() {
        return gambar;
    }

    public void setGambar(File gambar) {
        this.gambar = gambar;
    }

    
    private tambahBarangListener listener;

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
        fireOnChange();
    }

    
    public int getLamaGaransi() {
        return lamaGaransi;
    }

    public void setLamaGaransi(int lamaGaransi) {
        this.lamaGaransi = lamaGaransi;
        fireOnChange();
    }
    
    public tambahBarangListener getListener() {
        return listener;
    }

    public void setListener(tambahBarangListener listener) {
        this.listener = listener;
    }

    public String getGaransi() {
        return garansi;
    }

    public void setGaransi(String garansi) {
        this.garansi = garansi;
        fireOnChange();
    }

    
    public int getHargaModal() {
        return hargaModal;
    }

    public void setHargaModal(int hargaModal) {
        this.hargaModal = hargaModal;
        fireOnChange();
    }

    public int getEceran() {
        return eceran;
    }

    public void setEceran(int eceran) {
        this.eceran = eceran;
        fireOnChange();
    }
   

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getIdBarcode() {
        return idBarcode;
    }

    public void setIdBarcode(String idBarcode) {
        this.idBarcode = idBarcode;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getStokMinimum() {
        return stokMinimum;
    }

    public void setStokMinimum(int stokMinimum) {
        this.stokMinimum = stokMinimum;
    }

    public int getGrosir() {
        return grosir;
    }

    public void setGrosir(int grosir) {
        this.grosir = grosir;
    }
    
    public void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }
    
    public void fireOnInsert(BarangBesarDTO BarangBesarDTO){
        if(listener!=null){
            listener.onInsert(BarangBesarDTO);
        }
    }
    
    public void fireOnUpdate(BarangBesarDTO BarangBesarDTO){
        if(listener!=null){
            listener.onUpdate(BarangBesarDTO);
        }
    }
    
    public void resetTambahBarang(){
        setIdBarang("");
        setIdBarcode("");
        setNamaBarang("");
        setTipe("");
        setMerek("");
        setGrosir(0);
        setSatuan("");
        setStok(0);
        setStokMinimum(0);
        setSupplier("");
        setKeterangan("");        
    }
    public void insertBarang() throws SQLException, TambahBarangException{
        TambahBarangDao dao = TambahbarangDatabase.getBarangDao();

        BarangBesarDTO BarangBesarDTO = new BarangBesarDTO();
        BarangBesarDTO.setIdBarang(idBarang);
        BarangBesarDTO.setIdBarcode(idBarcode);
        BarangBesarDTO.setNamaBarang(namaBarang);
        BarangBesarDTO.setTipe(tipe);
        BarangBesarDTO.setMerek(merek);
        BarangBesarDTO.setModal(hargaModal);
        BarangBesarDTO.setGrosir(grosir);
        BarangBesarDTO.setEceran(eceran);
        BarangBesarDTO.setStok(stok);
        BarangBesarDTO.setStok_min(stokMinimum);
        BarangBesarDTO.setSupplier(supplier);
        BarangBesarDTO.setKeterangan(keterangan);
        if (gambar != null) {
            BarangBesarDTO.setGambar(gambar);
        }
        BarangBesarDTO.setGaransi(garansi);
        BarangBesarDTO.setLamaGaransi(lamaGaransi);
        dao.insertBarang(BarangBesarDTO);
        fireOnInsert(BarangBesarDTO);
    }
}

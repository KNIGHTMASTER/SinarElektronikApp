package com.wissensalt.sinarelektronik.masterdata.tambahbarang.besar.model;

import com.wissensalt.sinarelektronik.dao.BarangBesarDAO;
import com.wissensalt.sinarelektronik.dao.impl.BarangBesarDAOImpl;
import com.wissensalt.sinarelektronik.dto.BarangBesarDTO;
import com.wissensalt.sinarelektronik.dto.BaseDTO;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.model.event.tambahBarangListener;

import java.io.File;

/**
 *
 * @author Fauzi
 */
public class TambahBarangBesarModel extends BaseDTO {

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
    
    public void fireOnUpdate(BarangBesarDTO barangBesarDTO){
        if(listener!=null){
            listener.onUpdate(barangBesarDTO);
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

    public void insertBarang() {
        BarangBesarDAO barangBesarDAO = new BarangBesarDAOImpl();
        BarangBesarDTO barangBesarDTO = new BarangBesarDTO();
        barangBesarDTO.setIdBarang(idBarang);
        barangBesarDTO.setIdBarcode(idBarcode);
        barangBesarDTO.setNamaBarang(namaBarang);
        barangBesarDTO.setTipe(tipe);
        barangBesarDTO.setMerek(merek);
        barangBesarDTO.setModal(hargaModal);
        barangBesarDTO.setGrosir(grosir);
        barangBesarDTO.setEceran(eceran);
        barangBesarDTO.setStok(stok);
        barangBesarDTO.setStok_min(stokMinimum);
        barangBesarDTO.setSupplier(supplier);
        barangBesarDTO.setKeterangan(keterangan);
        if (gambar != null) {
            barangBesarDTO.setGambar(gambar);
        }

        barangBesarDTO.setGaransi(garansi);
        barangBesarDTO.setLamaGaransi(lamaGaransi);
        barangBesarDAO.insert(barangBesarDTO);
        fireOnInsert(barangBesarDTO);
    }
}

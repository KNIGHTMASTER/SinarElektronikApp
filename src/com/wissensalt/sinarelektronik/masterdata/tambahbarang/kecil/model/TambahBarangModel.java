package com.wissensalt.sinarelektronik.masterdata.tambahbarang.kecil.model;

import com.wissensalt.sinarelektronik.dao.BarangKecilDAO;
import com.wissensalt.sinarelektronik.dao.impl.BarangKecilDAOImpl;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.entity.BarangKecilDTO;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.model.event.TambahBarangKecilListener;

import java.io.File;

/**
 *
 * @author Fauzi
 */
public class TambahBarangModel {

    private String idBarang, idBarcode, namaBarang, tipe, merek, satuan, supplier, keterangan, garansi, kategori;
    
    private File gambar;

    private int stok, stokMinimum, hargaModal, eceran, grosir, lamaGaransi, grosir2;

    public int getGrosir2() {
        return grosir2;
    }

    public void setGrosir2(int grosir2) {
        this.grosir2 = grosir2;
    }
    
    public File getGambar() {
        return gambar;
    }

    public void setGambar(File gambar) {
        this.gambar = gambar;
    }

    
    private TambahBarangKecilListener listener;

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
    
    public TambahBarangKecilListener getListener() {
        return listener;
    }

    public void setListener(TambahBarangKecilListener listener) {
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
    
    public void fireOnInsert(BarangKecilDTO BarangKecilDTO){
        if(listener!=null){
            listener.onInsert(BarangKecilDTO);
        }
    }
    
    public void fireOnUpdate(BarangKecilDTO BarangKecilDTO){
        if(listener!=null){
            listener.onUpdate(BarangKecilDTO);
        }
    }
    
    public void resetTambahBarang(){
        setIdBarang("");
        setIdBarcode("");
        setNamaBarang("");
        setTipe("");
        setMerek("");
        setGrosir(0);
        setGrosir2(0);
        setSatuan("");
        setStok(0);
        setStokMinimum(0);
        setSupplier("");
        setKeterangan("");        
    }
    public void insertBarang() {
        BarangKecilDAO dao = new BarangKecilDAOImpl();

        BarangKecilDTO barangKecilDTO = new BarangKecilDTO();
        barangKecilDTO.setIdBarang(idBarang);
        barangKecilDTO.setIdBarcode(idBarcode);
        barangKecilDTO.setNamaBarang(namaBarang);
        barangKecilDTO.setTipe(tipe);
        barangKecilDTO.setMerek(merek);
        barangKecilDTO.setHargamodal(hargaModal);
        barangKecilDTO.setGrosir(grosir);
        barangKecilDTO.setGrosir2(grosir2);
        barangKecilDTO.setEceran(eceran);
        barangKecilDTO.setSatuan(satuan);
        barangKecilDTO.setStok(stok);
        barangKecilDTO.setStokMinimum(stokMinimum);
        barangKecilDTO.setSupplier(supplier);
        barangKecilDTO.setKeterangan(keterangan);
        if (gambar != null) {
            barangKecilDTO.setGambar(gambar);
        }     
        barangKecilDTO.setGaransi(garansi);
        barangKecilDTO.setLamaGaransi(lamaGaransi);
        barangKecilDTO.setKategori(kategori);
        dao.insert(barangKecilDTO);
        fireOnInsert(barangKecilDTO);
    }
}

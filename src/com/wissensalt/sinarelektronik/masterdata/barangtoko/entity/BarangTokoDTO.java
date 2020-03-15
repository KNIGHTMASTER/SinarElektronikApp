package com.wissensalt.sinarelektronik.masterdata.barangtoko.entity;

import com.wissensalt.sinarelektronik.dto.BaseDTO;

import java.io.File;
import java.sql.Blob;
import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class BarangTokoDTO extends BaseDTO {

    private String idBarang, idBarcode, namaBarang, tipe, merek, supplier, keterangan, garansi;
    private int modal, grosir, eceran, stok, stok_min, lamaGaransi;
    private File gambar;
    private Blob gambarHasil;

    public BarangTokoDTO() {
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

    public String getGaransi() {
        return garansi;
    }

    public void setGaransi(String garansi) {
        this.garansi = garansi;
    }

    public int getModal() {
        return modal;
    }

    public void setModal(int modal) {
        this.modal = modal;
    }

    public int getGrosir() {
        return grosir;
    }

    public void setGrosir(int grosir) {
        this.grosir = grosir;
    }

    public int getEceran() {
        return eceran;
    }

    public void setEceran(int eceran) {
        this.eceran = eceran;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getStok_min() {
        return stok_min;
    }

    public void setStok_min(int stok_min) {
        this.stok_min = stok_min;
    }

    public int getLamaGaransi() {
        return lamaGaransi;
    }

    public void setLamaGaransi(int lamaGaransi) {
        this.lamaGaransi = lamaGaransi;
    }

    public File getGambar() {
        return gambar;
    }

    public void setGambar(File gambar) {
        this.gambar = gambar;
    }

    public Blob getGambarHasil() {
        return gambarHasil;
    }

    public void setGambarHasil(Blob gambarHasil) {
        this.gambarHasil = gambarHasil;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.idBarang);
        hash = 29 * hash + Objects.hashCode(this.idBarcode);
        hash = 29 * hash + Objects.hashCode(this.namaBarang);
        hash = 29 * hash + Objects.hashCode(this.tipe);
        hash = 29 * hash + Objects.hashCode(this.merek);
        hash = 29 * hash + Objects.hashCode(this.supplier);
        hash = 29 * hash + Objects.hashCode(this.keterangan);
        hash = 29 * hash + Objects.hashCode(this.garansi);
        hash = 29 * hash + this.modal;
        hash = 29 * hash + this.grosir;
        hash = 29 * hash + this.eceran;
        hash = 29 * hash + this.stok;
        hash = 29 * hash + this.stok_min;
        hash = 29 * hash + this.lamaGaransi;
        hash = 29 * hash + Objects.hashCode(this.gambar);
        hash = 29 * hash + Objects.hashCode(this.gambarHasil);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BarangTokoDTO that = (BarangTokoDTO) o;
        return modal == that.modal &&
                grosir == that.grosir &&
                eceran == that.eceran &&
                stok == that.stok &&
                stok_min == that.stok_min &&
                lamaGaransi == that.lamaGaransi &&
                idBarang.equals(that.idBarang) &&
                idBarcode.equals(that.idBarcode) &&
                namaBarang.equals(that.namaBarang) &&
                tipe.equals(that.tipe) &&
                merek.equals(that.merek) &&
                supplier.equals(that.supplier) &&
                keterangan.equals(that.keterangan) &&
                garansi.equals(that.garansi) &&
                gambar.equals(that.gambar) &&
                gambarHasil.equals(that.gambarHasil);
    }
}

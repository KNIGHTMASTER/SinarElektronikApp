/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterdata.barangkecil.entity;

import java.io.File;
import java.sql.Blob;
import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class barang {

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    
    public String idBarang, idBarcode, namaBarang, tipe, merek, satuan, supplier,keterangan, garansi, kategori;
    
    public int stok, stokMinimum,  hargamodal, eceran, grosir, lamaGaransi, grosir2;
    
    File gambar;        
    
    Blob gambarHasil;

    public int getGrosir2() {
        return grosir2;
    }

    public void setGrosir2(int grosir2) {
        this.grosir2 = grosir2;
    }

        
    public Blob getGambarHasil() {
        return gambarHasil;
    }

    public void setGambarHasil(Blob gambarHasil) {
        this.gambarHasil = gambarHasil;
    }
    
    
    public barang() {
    }

    public File getGambar() {
        return gambar;
    }

    public void setGambar(File gambar) {
        this.gambar = gambar;
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

    public String getGaransi() {
        return garansi;
    }

    public void setGaransi(String garansi) {
        this.garansi = garansi;
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

    public int getHargamodal() {
        return hargamodal;
    }

    public void setHargamodal(int hargamodal) {
        this.hargamodal = hargamodal;
    }

    public int getEceran() {
        return eceran;
    }

    public void setEceran(int eceran) {
        this.eceran = eceran;
    }

    public int getGrosir() {
        return grosir;
    }

    public void setGrosir(int grosir) {
        this.grosir = grosir;
    }

    public int getLamaGaransi() {
        return lamaGaransi;
    }

    public void setLamaGaransi(int lamaGaransi) {
        this.lamaGaransi = lamaGaransi;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.idBarang);
        hash = 73 * hash + Objects.hashCode(this.idBarcode);
        hash = 73 * hash + Objects.hashCode(this.namaBarang);
        hash = 73 * hash + Objects.hashCode(this.tipe);
        hash = 73 * hash + Objects.hashCode(this.merek);
        hash = 73 * hash + Objects.hashCode(this.satuan);
        hash = 73 * hash + Objects.hashCode(this.supplier);
        hash = 73 * hash + Objects.hashCode(this.keterangan);
        hash = 73 * hash + Objects.hashCode(this.garansi);
        hash = 73 * hash + Objects.hashCode(this.kategori);
        hash = 73 * hash + this.stok;
        hash = 73 * hash + this.stokMinimum;
        hash = 73 * hash + this.hargamodal;
        hash = 73 * hash + this.eceran;
        hash = 73 * hash + this.grosir;
        hash = 73 * hash + this.lamaGaransi;
        hash = 73 * hash + Objects.hashCode(this.gambar);
        hash = 73 * hash + Objects.hashCode(this.gambarHasil);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final barang other = (barang) obj;
        if (!Objects.equals(this.idBarang, other.idBarang)) {
            return false;
        }
        if (!Objects.equals(this.idBarcode, other.idBarcode)) {
            return false;
        }
        if (!Objects.equals(this.namaBarang, other.namaBarang)) {
            return false;
        }
        if (!Objects.equals(this.tipe, other.tipe)) {
            return false;
        }
        if (!Objects.equals(this.merek, other.merek)) {
            return false;
        }
        if (!Objects.equals(this.satuan, other.satuan)) {
            return false;
        }
        if (!Objects.equals(this.supplier, other.supplier)) {
            return false;
        }
        if (!Objects.equals(this.keterangan, other.keterangan)) {
            return false;
        }
        if (!Objects.equals(this.garansi, other.garansi)) {
            return false;
        }
        if (!Objects.equals(this.kategori, other.kategori)) {
            return false;
        }
        if (this.stok != other.stok) {
            return false;
        }
        if (this.stokMinimum != other.stokMinimum) {
            return false;
        }
        if (this.hargamodal != other.hargamodal) {
            return false;
        }
        if (this.eceran != other.eceran) {
            return false;
        }
        if (this.grosir != other.grosir) {
            return false;
        }
        if (this.lamaGaransi != other.lamaGaransi) {
            return false;
        }
        if (!Objects.equals(this.gambar, other.gambar)) {
            return false;
        }
        if (!Objects.equals(this.gambarHasil, other.gambarHasil)) {
            return false;
        }
        return true;
    }    
}

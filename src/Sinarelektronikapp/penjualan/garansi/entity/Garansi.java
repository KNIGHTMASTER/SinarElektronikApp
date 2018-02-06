/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.penjualan.garansi.entity;

import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class Garansi {
    String idTransaksi, namaBarang, status, masaakhir;
    int jumlah;

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMasaakhir() {
        return masaakhir;
    }

    public void setMasaakhir(String masaakhir) {
        this.masaakhir = masaakhir;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idTransaksi);
        hash = 97 * hash + Objects.hashCode(this.namaBarang);
        hash = 97 * hash + Objects.hashCode(this.status);
        hash = 97 * hash + Objects.hashCode(this.masaakhir);
        hash = 97 * hash + this.jumlah;
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
        final Garansi other = (Garansi) obj;
        if (!Objects.equals(this.idTransaksi, other.idTransaksi)) {
            return false;
        }
        if (!Objects.equals(this.namaBarang, other.namaBarang)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.masaakhir, other.masaakhir)) {
            return false;
        }
        if (this.jumlah != other.jumlah) {
            return false;
        }
        return true;
    }
        
}

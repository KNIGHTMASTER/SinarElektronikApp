/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.itemforbonus.barangkecil.entity;

import java.util.Objects;

/**
 *
 * @author FAUZI
 */
public class BarangBonusKaryawanBk {

    public BarangBonusKaryawanBk() {
    }
    
    private String kodeBarang, nama, tipe, merek;

    public BarangBonusKaryawanBk(String kodeBarang, String nama, String tipe, String merek) {
        this.kodeBarang = kodeBarang;
        this.nama = nama;
        this.tipe = tipe;
        this.merek = merek;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.kodeBarang);
        hash = 29 * hash + Objects.hashCode(this.nama);
        hash = 29 * hash + Objects.hashCode(this.tipe);
        hash = 29 * hash + Objects.hashCode(this.merek);
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
        final BarangBonusKaryawanBk other = (BarangBonusKaryawanBk) obj;
        if (!Objects.equals(this.kodeBarang, other.kodeBarang)) {
            return false;
        }
        if (!Objects.equals(this.nama, other.nama)) {
            return false;
        }
        if (!Objects.equals(this.tipe, other.tipe)) {
            return false;
        }
        if (!Objects.equals(this.merek, other.merek)) {
            return false;
        }
        return true;
    }        
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.retur.barangbesar.entity;

import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class Retur {

    public Retur() {
    }

    int id, jumlah, subharga;
    String user, jam, kode, nama, tanggal, asalBarang, penukaran;

    public String getAsalBarang() {
        return asalBarang;
    }

    public void setAsalBarang(String asalBarang) {
        this.asalBarang = asalBarang;
    }

    public String getPenukaran() {
        return penukaran;
    }

    public void setPenukaran(String penukaran) {
        this.penukaran = penukaran;
    }

        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getSubharga() {
        return subharga;
    }

    public void setSubharga(int subharga) {
        this.subharga = subharga;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }


    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.jumlah;
        hash = 79 * hash + this.subharga;
        hash = 79 * hash + Objects.hashCode(this.user);
        hash = 79 * hash + Objects.hashCode(this.jam);
        hash = 79 * hash + Objects.hashCode(this.kode);
        hash = 79 * hash + Objects.hashCode(this.nama);
        hash = 79 * hash + Objects.hashCode(this.tanggal);
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final Retur other = (Retur) obj;
        if (this.jumlah != other.jumlah) {
            return false;
        }
        if (this.subharga != other.subharga) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.jam, other.jam)) {
            return false;
        }
        if (!Objects.equals(this.kode, other.kode)) {
            return false;
        }
        if (!Objects.equals(this.nama, other.nama)) {
            return false;
        }
        if (!Objects.equals(this.tanggal, other.tanggal)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
           
}

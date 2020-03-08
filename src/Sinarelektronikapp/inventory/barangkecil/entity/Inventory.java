package Sinarelektronikapp.inventory.barangkecil.entity;

import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class Inventory {

    public Inventory() {
    }
    
    int id, jumlah, harga, ekspedisi, subharga;
    
    String user, tanggal, jam, kode, nama;

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

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getEkspedisi() {
        return ekspedisi;
    }

    public void setEkspedisi(int ekspedisi) {
        this.ekspedisi = ekspedisi;
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

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + this.jumlah;
        hash = 29 * hash + this.harga;
        hash = 29 * hash + this.ekspedisi;
        hash = 29 * hash + this.subharga;
        hash = 29 * hash + Objects.hashCode(this.user);
        hash = 29 * hash + Objects.hashCode(this.tanggal);
        hash = 29 * hash + Objects.hashCode(this.jam);
        hash = 29 * hash + Objects.hashCode(this.kode);
        hash = 29 * hash + Objects.hashCode(this.nama);
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
        final Inventory other = (Inventory) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.jumlah != other.jumlah) {
            return false;
        }
        if (this.harga != other.harga) {
            return false;
        }
        if (this.ekspedisi != other.ekspedisi) {
            return false;
        }
        if (this.subharga != other.subharga) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.tanggal, other.tanggal)) {
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
        return true;
    }
    
}

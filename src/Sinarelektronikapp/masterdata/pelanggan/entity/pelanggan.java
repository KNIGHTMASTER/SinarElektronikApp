/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.pelanggan.entity;

import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class pelanggan {
    private String idpelanggan, nama, alamat, kota, propinsi, kodePost, telepon, fax, kontakPerson, catatan;

    public pelanggan(String idpelanggan, String nama, String alamat, String kota, String propinsi, String kodePost, String telepon, String fax, String kontakPerson, String catatan) {
        this.idpelanggan = idpelanggan;
        this.nama = nama;
        this.alamat = alamat;
        this.kota = kota;
        this.propinsi = propinsi;
        this.kodePost = kodePost;
        this.telepon = telepon;
        this.fax = fax;
        this.kontakPerson = kontakPerson;
        this.catatan = catatan;
    }

    public pelanggan() {
    }

    public String getIdpelanggan() {
        return idpelanggan;
    }

    public void setIdpelanggan(String idpelanggan) {
        this.idpelanggan = idpelanggan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getPropinsi() {
        return propinsi;
    }

    public void setPropinsi(String propinsi) {
        this.propinsi = propinsi;
    }

    public String getKodePost() {
        return kodePost;
    }

    public void setKodePost(String kodePost) {
        this.kodePost = kodePost;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getKontakPerson() {
        return kontakPerson;
    }

    public void setKontakPerson(String kontakPerson) {
        this.kontakPerson = kontakPerson;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idpelanggan);
        hash = 97 * hash + Objects.hashCode(this.nama);
        hash = 97 * hash + Objects.hashCode(this.alamat);
        hash = 97 * hash + Objects.hashCode(this.kota);
        hash = 97 * hash + Objects.hashCode(this.propinsi);
        hash = 97 * hash + Objects.hashCode(this.kodePost);
        hash = 97 * hash + Objects.hashCode(this.telepon);
        hash = 97 * hash + Objects.hashCode(this.fax);
        hash = 97 * hash + Objects.hashCode(this.kontakPerson);
        hash = 97 * hash + Objects.hashCode(this.catatan);
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
        final pelanggan other = (pelanggan) obj;
        if (!Objects.equals(this.idpelanggan, other.idpelanggan)) {
            return false;
        }
        if (!Objects.equals(this.nama, other.nama)) {
            return false;
        }
        if (!Objects.equals(this.alamat, other.alamat)) {
            return false;
        }
        if (!Objects.equals(this.kota, other.kota)) {
            return false;
        }
        if (!Objects.equals(this.propinsi, other.propinsi)) {
            return false;
        }
        if (!Objects.equals(this.kodePost, other.kodePost)) {
            return false;
        }
        if (!Objects.equals(this.telepon, other.telepon)) {
            return false;
        }
        if (!Objects.equals(this.fax, other.fax)) {
            return false;
        }
        if (!Objects.equals(this.kontakPerson, other.kontakPerson)) {
            return false;
        }
        if (!Objects.equals(this.catatan, other.catatan)) {
            return false;
        }
        return true;
    }
        
}

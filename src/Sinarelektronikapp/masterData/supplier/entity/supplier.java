/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.supplier.entity;

import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class supplier {
    private String idsupplier, nama, alamat, kota, propinsi, kodePost, telepon, fax, bank, nomorRek, atasNama, kontakPerson, email, note;

    public String getIdsupplier() {
        return idsupplier;
    }

    public void setIdsupplier(String idsupplier) {
        this.idsupplier = idsupplier;
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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getNomorRek() {
        return nomorRek;
    }

    public void setNomorRek(String nomorRek) {
        this.nomorRek = nomorRek;
    }

    public String getAtasNama() {
        return atasNama;
    }

    public void setAtasNama(String atasNama) {
        this.atasNama = atasNama;
    }

    public String getKontakPerson() {
        return kontakPerson;
    }

    public void setKontakPerson(String kontakPerson) {
        this.kontakPerson = kontakPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idsupplier);
        hash = 89 * hash + Objects.hashCode(this.nama);
        hash = 89 * hash + Objects.hashCode(this.alamat);
        hash = 89 * hash + Objects.hashCode(this.kota);
        hash = 89 * hash + Objects.hashCode(this.propinsi);
        hash = 89 * hash + Objects.hashCode(this.kodePost);
        hash = 89 * hash + Objects.hashCode(this.telepon);
        hash = 89 * hash + Objects.hashCode(this.fax);
        hash = 89 * hash + Objects.hashCode(this.bank);
        hash = 89 * hash + Objects.hashCode(this.nomorRek);
        hash = 89 * hash + Objects.hashCode(this.atasNama);
        hash = 89 * hash + Objects.hashCode(this.kontakPerson);
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Objects.hashCode(this.note);
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
        final supplier other = (supplier) obj;
        if (!Objects.equals(this.idsupplier, other.idsupplier)) {
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
        if (!Objects.equals(this.bank, other.bank)) {
            return false;
        }
        if (!Objects.equals(this.nomorRek, other.nomorRek)) {
            return false;
        }
        if (!Objects.equals(this.atasNama, other.atasNama)) {
            return false;
        }
        if (!Objects.equals(this.kontakPerson, other.kontakPerson)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        return true;
    }
        
}

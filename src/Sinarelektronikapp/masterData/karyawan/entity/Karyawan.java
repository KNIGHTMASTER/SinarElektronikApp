/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.karyawan.entity;

import java.io.File;
import java.sql.Blob;
import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class Karyawan {
    int id, gaji;
    String nama, tempat_lahir, alamat, telepon, agama, status, tanggal_lahir;
    File foto;  
    Blob gambarHasil;

    public Blob getGambarHasil() {
        return gambarHasil;
    }

    public void setGambarHasil(Blob gambarHasil) {
        this.gambarHasil = gambarHasil;
    }
    

    public Karyawan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGaji() {
        return gaji;
    }

    public void setGaji(int gaji) {
        this.gaji = gaji;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + this.gaji;
        hash = 67 * hash + Objects.hashCode(this.nama);
        hash = 67 * hash + Objects.hashCode(this.tempat_lahir);
        hash = 67 * hash + Objects.hashCode(this.alamat);
        hash = 67 * hash + Objects.hashCode(this.telepon);
        hash = 67 * hash + Objects.hashCode(this.agama);
        hash = 67 * hash + Objects.hashCode(this.status);
        hash = 67 * hash + Objects.hashCode(this.tanggal_lahir);
        hash = 67 * hash + Objects.hashCode(this.foto);
        hash = 67 * hash + Objects.hashCode(this.gambarHasil);
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
        final Karyawan other = (Karyawan) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.gaji != other.gaji) {
            return false;
        }
        if (!Objects.equals(this.nama, other.nama)) {
            return false;
        }
        if (!Objects.equals(this.tempat_lahir, other.tempat_lahir)) {
            return false;
        }
        if (!Objects.equals(this.alamat, other.alamat)) {
            return false;
        }
        if (!Objects.equals(this.telepon, other.telepon)) {
            return false;
        }
        if (!Objects.equals(this.agama, other.agama)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.tanggal_lahir, other.tanggal_lahir)) {
            return false;
        }
        if (!Objects.equals(this.foto, other.foto)) {
            return false;
        }
        if (!Objects.equals(this.gambarHasil, other.gambarHasil)) {
            return false;
        }
        return true;
    }

    
}

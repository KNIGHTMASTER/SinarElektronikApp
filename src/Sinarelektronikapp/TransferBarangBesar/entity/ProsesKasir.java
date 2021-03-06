/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.TransferBarangBesar.entity;

import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class ProsesKasir {
       private String kode, nama;
       
       private int jml, no;

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

    public int getJml() {
        return jml;
    }

    public void setJml(int jml) {
        this.jml = jml;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.kode);
        hash = 61 * hash + Objects.hashCode(this.nama);
        hash = 61 * hash + this.jml;
        hash = 61 * hash + this.no;
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
        final ProsesKasir other = (ProsesKasir) obj;
        if (!Objects.equals(this.kode, other.kode)) {
            return false;
        }
        if (!Objects.equals(this.nama, other.nama)) {
            return false;
        }
        if (this.jml != other.jml) {
            return false;
        }
        if (this.no != other.no) {
            return false;
        }
        return true;
    }
      
}

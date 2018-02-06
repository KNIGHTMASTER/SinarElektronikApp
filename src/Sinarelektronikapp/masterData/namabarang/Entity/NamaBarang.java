/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.namabarang.Entity;

import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class NamaBarang {

    public NamaBarang() {
    }

    
    int id;
    String namabarang;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamabarang() {
        return namabarang;
    }

    public void setNamabarang(String namabarang) {
        this.namabarang = namabarang;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.namabarang);
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
        final NamaBarang other = (NamaBarang) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.namabarang, other.namabarang)) {
            return false;
        }
        return true;
    }       
    
}

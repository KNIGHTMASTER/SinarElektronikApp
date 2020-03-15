package com.wissensalt.sinarelektronik.masterdata.namabarang.entity;

import com.wissensalt.sinarelektronik.dto.BaseDTO;

import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class NamaBarangDTO extends BaseDTO {

    private int id;
    private String namabarang;

    public NamaBarangDTO() {
    }

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
        final NamaBarangDTO other = (NamaBarangDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.namabarang, other.namabarang)) {
            return false;
        }
        return true;
    }       
    
}

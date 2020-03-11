/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wissensalt.sinarelektronik.masterdata.satuan.entity;

import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class satuan {
    private String idsatuan, namasatuan;

    public satuan(String idsatuan, String namasatuan) {
        this.idsatuan = idsatuan;
        this.namasatuan = namasatuan;
    }

    public satuan() {
        
    }

    public String getIdsatuan() {
        return idsatuan;
    }

    public void setIdsatuan(String idsatuan) {
        this.idsatuan = idsatuan;
    }

    public String getNamasatuan() {
        return namasatuan;
    }

    public void setNamasatuan(String namasatuan) {
        this.namasatuan = namasatuan;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idsatuan);
        hash = 97 * hash + Objects.hashCode(this.namasatuan);
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
        final satuan other = (satuan) obj;
        if (!Objects.equals(this.idsatuan, other.idsatuan)) {
            return false;
        }
        if (!Objects.equals(this.namasatuan, other.namasatuan)) {
            return false;
        }
        return true;
    }
        
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wissensalt.sinarelektronik.masterdata.satuan.entity;

import com.wissensalt.sinarelektronik.dto.BaseDTO;

import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class SatuanDTO extends BaseDTO {
    private String idsatuan, namaSatuan;

    public SatuanDTO(String idsatuan, String namaSatuan) {
        this.idsatuan = idsatuan;
        this.namaSatuan = namaSatuan;
    }

    public SatuanDTO() {
        
    }

    public String getIdsatuan() {
        return idsatuan;
    }

    public void setIdsatuan(String idsatuan) {
        this.idsatuan = idsatuan;
    }

    public String getNamaSatuan() {
        return namaSatuan;
    }

    public void setNamaSatuan(String namaSatuan) {
        this.namaSatuan = namaSatuan;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idsatuan);
        hash = 97 * hash + Objects.hashCode(this.namaSatuan);
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
        final SatuanDTO other = (SatuanDTO) obj;
        if (!Objects.equals(this.idsatuan, other.idsatuan)) {
            return false;
        }
        if (!Objects.equals(this.namaSatuan, other.namaSatuan)) {
            return false;
        }
        return true;
    }
        
}

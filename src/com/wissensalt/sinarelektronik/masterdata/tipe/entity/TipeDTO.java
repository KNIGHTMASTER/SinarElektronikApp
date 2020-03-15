/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wissensalt.sinarelektronik.masterdata.tipe.entity;

import com.wissensalt.sinarelektronik.dto.BaseDTO;

import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class TipeDTO extends BaseDTO {
    private String idTipe, namaTipe;

    public TipeDTO() {
    }

    
    public TipeDTO(String idTipe, String namaTipe) {
        this.idTipe = idTipe;
        this.namaTipe = namaTipe;
    }
    
    public String getIdTipe() {
        return idTipe;
    }

    public void setIdTipe(String idTipe) {
        this.idTipe = idTipe;
    }

    public String getNamaTipe() {
        return namaTipe;
    }

    public void setNamaTipe(String namaTipe) {
        this.namaTipe = namaTipe;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.idTipe);
        hash = 41 * hash + Objects.hashCode(this.namaTipe);
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
        final TipeDTO other = (TipeDTO) obj;
        if (!Objects.equals(this.idTipe, other.idTipe)) {
            return false;
        }
        if (!Objects.equals(this.namaTipe, other.namaTipe)) {
            return false;
        }
        return true;
    }
    
}

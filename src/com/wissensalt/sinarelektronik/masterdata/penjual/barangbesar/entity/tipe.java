/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wissensalt.sinarelektronik.masterdata.penjual.barangbesar.entity;

import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class tipe {
    private String idtipe, namaTipe;

    private int insentif;
    public tipe(){
        
    }
    public tipe(String idtipe, String namaTipe, int insentif) {
        this.idtipe = idtipe;
        this.namaTipe = namaTipe;
        this.insentif = insentif;
    }

    public String getIdtipe() {
        return idtipe;
    }

    public void setIdtipe(String idtipe) {
        this.idtipe = idtipe;
    }

    public String getNamaTipe() {
        return namaTipe;
    }

    public void setNamaTipe(String namaTipe) {
        this.namaTipe = namaTipe;
    }

    public int getInsentif() {
        return insentif;
    }

    public void setInsentif(int insentif) {
        this.insentif = insentif;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.idtipe);
        hash = 59 * hash + Objects.hashCode(this.namaTipe);
        hash = 59 * hash + this.insentif;
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
        final tipe other = (tipe) obj;
        if (!Objects.equals(this.idtipe, other.idtipe)) {
            return false;
        }
        if (!Objects.equals(this.namaTipe, other.namaTipe)) {
            return false;
        }
        if (this.insentif != other.insentif) {
            return false;
        }
        return true;
    }
    
    
}

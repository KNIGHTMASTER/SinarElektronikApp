/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterdata.tipe.entity;

import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class tipe {
    private String idtipe, namaTipe;

    public tipe() {
    }

    
    public tipe(String idtipe, String namaTipe) {
        this.idtipe = idtipe;
        this.namaTipe = namaTipe;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.idtipe);
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
        final tipe other = (tipe) obj;
        if (!Objects.equals(this.idtipe, other.idtipe)) {
            return false;
        }
        if (!Objects.equals(this.namaTipe, other.namaTipe)) {
            return false;
        }
        return true;
    }
    
}

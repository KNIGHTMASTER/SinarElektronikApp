/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterData.merek.entity;

import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class merek {
    private String idmerek, namamerek;

    public merek(String idmerek, String namamerek) {
        this.idmerek = idmerek;
        this.namamerek = namamerek;
    }

    public merek() {
    }

    public String getIdmerek() {
        return idmerek;
    }

    public void setIdmerek(String idmerek) {
        this.idmerek = idmerek;
    }

    public String getNamamerek() {
        return namamerek;
    }

    public void setNamamerek(String namamerek) {
        this.namamerek = namamerek;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idmerek);
        hash = 37 * hash + Objects.hashCode(this.namamerek);
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
        final merek other = (merek) obj;
        if (!Objects.equals(this.idmerek, other.idmerek)) {
            return false;
        }
        if (!Objects.equals(this.namamerek, other.namamerek)) {
            return false;
        }
        return true;
    }
        
}

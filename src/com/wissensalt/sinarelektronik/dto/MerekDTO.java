package com.wissensalt.sinarelektronik.dto;

import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class MerekDTO extends BaseDTO {
    private String idMerek, namaMerek;

    public MerekDTO(String idMerek, String namaMerek) {
        this.idMerek = idMerek;
        this.namaMerek = namaMerek;
    }

    public MerekDTO() {
    }

    public String getIdMerek() {
        return idMerek;
    }

    public void setIdMerek(String idMerek) {
        this.idMerek = idMerek;
    }

    public String getNamaMerek() {
        return namaMerek;
    }

    public void setNamaMerek(String namaMerek) {
        this.namaMerek = namaMerek;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idMerek);
        hash = 37 * hash + Objects.hashCode(this.namaMerek);
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
        final MerekDTO other = (MerekDTO) obj;
        if (!Objects.equals(this.idMerek, other.idMerek)) {
            return false;
        }
        if (!Objects.equals(this.namaMerek, other.namaMerek)) {
            return false;
        }
        return true;
    }
        
}

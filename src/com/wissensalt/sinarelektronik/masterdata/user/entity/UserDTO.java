package com.wissensalt.sinarelektronik.masterdata.user.entity;

import com.wissensalt.sinarelektronik.dto.BaseDTO;

import java.util.Objects;

/**
 *
 * @author Fauzi
 */
public class UserDTO extends BaseDTO {
    private String idUser, nama, password, level, keterangan;

    public UserDTO() {
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return idUser.equals(userDTO.idUser) &&
                nama.equals(userDTO.nama) &&
                password.equals(userDTO.password) &&
                level.equals(userDTO.level) &&
                keterangan.equals(userDTO.keterangan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, nama, password, level, keterangan);
    }
}

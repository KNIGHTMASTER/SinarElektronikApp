package com.wissensalt.sinarelektronik.dao.impl;

import com.wissensalt.sinarelektronik.dao.ABaseDAO;
import com.wissensalt.sinarelektronik.dao.UserDAO;
import com.wissensalt.sinarelektronik.masterdata.user.entity.UserDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class UserDAOImpl extends ABaseDAO<UserDTO> implements UserDAO {
    private final String insertUser = "INSERT INTO user (nama, password, level, keterangan) VALUES (?, ?, ?, ?) ";
    
    private final String updateUser = "UPDATE user SET nama=?, password=?, level=?, keterangan=? WHERE iduser=?";                                        
    
    private final String deleteUser = "DELETE FROM user WHERE iduser=?";
    
    private final String selectAllUser = "SELECT * FROM user";
    
    private final String searchById = "SELECT * FROM user WHERE iduser LIKE ?";
    
    private final String searchByName = "SELECT * FROM user WHERE nama LIKE ?";
    
    private final String searchByLevel = "SELECT * FROM user WHERE level LIKE ?";
    
    private final String searchByKet= "SELECT * FROM user WHERE keterangan LIKE ?";
    
    private final String sortById= "SELECT * FROM user ORDER BY iduser";
    
    private final String sortByNama= "SELECT * FROM user ORDER BY nama";
    
    private final String sortByLevel= "SELECT * FROM user ORDER BY level";
    
    private final String sortByKet= "SELECT * FROM user ORDER BY keterangan";
    
    private final String getLastId = "SELECT COUNT(iduser)+1 total FROM user";

    @Override
    public void insertDetail(UserDTO userDTO, PreparedStatement ps) {
        try {
            ps=connection.prepareStatement(insertUser, ps.RETURN_GENERATED_KEYS);
            ps.setString(1, userDTO.getNama());
            ps.setString(2, userDTO.getPassword());
            ps.setString(3, userDTO.getLevel());
            ps.setString(4, userDTO.getKeterangan());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public PreparedStatement updateDetail(UserDTO userDTO, PreparedStatement ps) {
        try {
            ps=connection.prepareStatement(updateUser);
            ps.setString(1, userDTO.getNama());
            ps.setString(2, userDTO.getPassword());
            ps.setString(3, userDTO.getLevel());
            ps.setString(4, userDTO.getKeterangan());
            ps.setString(5, userDTO.getIdUser());
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ps;
    }

    @Override
    public String getDeleteQueryByString() {
        return deleteUser;
    }

    @Override
    public List<UserDTO> selectAllUser() {
        return findAndSortByField(selectAllUser);
    }

    @Override
    public List<UserDTO> searchById(String id) {
        return findByFieldLike(id, searchById);
    }

    @Override
    public List<UserDTO> searchByname(String name) {
        return findByFieldLike(name, searchByName);
    }

    @Override
    public List<UserDTO> searchByLevel(String level){
        return findByFieldLike(level, searchByLevel);
    }

    @Override
    public List<UserDTO> searchByKet(String ket) {
        return findByFieldLike(ket, searchByKet);
    }

    @Override
    public List<UserDTO> sortById() {
        return findAndSortByField(sortById);
    }

    @Override
    public List<UserDTO> sortByname() {
        return findAndSortByField(sortByNama);
    }

    @Override
    public List<UserDTO> sortByLevel() {
        return findAndSortByField(sortByLevel);
    }

    @Override
    public List<UserDTO> sortByKet() {
        return findAndSortByField(sortByKet);
    }

    @Override
    public int getLastId() {
        return findLastIdByField("total", getLastId);
    }
    
}
    
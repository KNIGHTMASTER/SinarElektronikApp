package com.wissensalt.sinarelektronik.dao.impl;

import com.wissensalt.sinarelektronik.dao.ABaseDAO;
import com.wissensalt.sinarelektronik.dao.NamaBarangDAO;
import com.wissensalt.sinarelektronik.masterdata.namabarang.entity.NamaBarangDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class NamaBarangDAOImpl extends ABaseDAO<NamaBarangDTO> implements NamaBarangDAO {
    
    private final String INSERT_NAMA_BARANG = "INSERT INTO namabarang (namabarang) VALUES (?)";
    private final String UPDATE_NAMA_BARANG = "UPDATE namabarang SET namabarang=? WHERE id=?";
    private final String DELETE_NAMA_BARANG = "DELETE FROM namabarang WHERE id= ? ";
    private final String GET_NAMA_BARANG_BY_ID = "SELECT * FROM namabarang WHERE id= ?";
    private final String SELECT_ALL = "SELECT * FROM namabarang ORDER BY namabarang";
    private final String SELECT_LAST_ID ="SELECT count(id) total from namabarang";


    @Override
    public void insertDetail(NamaBarangDTO dto, PreparedStatement ps) {
        try {
            ps = connection.prepareStatement(INSERT_NAMA_BARANG, ps.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getNamabarang());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NamaBarangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public PreparedStatement updateDetail(NamaBarangDTO dto, PreparedStatement ps) {
        try {
            ps = connection.prepareStatement(UPDATE_NAMA_BARANG);
            ps.setString(1, dto.getNamabarang());
            ps.setInt(2, dto.getId());
        } catch (SQLException ex) {
            Logger.getLogger(NamaBarangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ps;
    }

    @Override
    public String getDeleteQueryById() {
        return DELETE_NAMA_BARANG;
    }

    @Override
    public List<NamaBarangDTO> getNamaBarangById(int id) {
        return findAndSortByField(GET_NAMA_BARANG_BY_ID);
    }

    @Override
    public List<NamaBarangDTO> selectAllNamaBarang() {
        return findAndSortByField(SELECT_ALL);
    }

    @Override
    public int getLastId(){        
        return findLastIdByField("total", SELECT_LAST_ID);
    }
    
}

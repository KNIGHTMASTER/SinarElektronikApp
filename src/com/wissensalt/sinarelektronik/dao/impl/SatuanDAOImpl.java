package com.wissensalt.sinarelektronik.dao.impl;

import com.wissensalt.sinarelektronik.dao.ABaseDAO;
import com.wissensalt.sinarelektronik.dao.SatuanDAO;
import com.wissensalt.sinarelektronik.masterdata.satuan.entity.SatuanDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class SatuanDAOImpl extends ABaseDAO<SatuanDTO> implements SatuanDAO {
    
    private final String INSERT_SATUAN = "INSERT INTO satuan (namasatuan) VALUES (?)";
    private final String UPDATE_SATUAN = "UPDATE satuan SET namasatuan=? WHERE idsatuan=?";
    private final String DELETE_SATUAN = "DELETE FROM satuan WHERE idsatuan = ?";
    private final String GET_SATUAN_BY_ID = "SELECT * FROM satuan WHERE idsatuan = ?";
    private final String SELECT_ALL = "SELECT * FROM satuan ORDER BY namasatuan";
    private final String GET_LAST_ID = "SELECT count(idsatuan)+1 total from satuan ";

    @Override
    public void insertDetail(SatuanDTO satuanDTO, PreparedStatement statement) {
        try {
            statement = connection.prepareStatement(INSERT_SATUAN, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, satuanDTO.getNamaSatuan());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SatuanDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public PreparedStatement updateDetail(SatuanDTO satuanDTO, PreparedStatement statement) {
        try {
            statement = connection.prepareStatement(UPDATE_SATUAN, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, satuanDTO.getNamaSatuan());
            statement.setString(2, satuanDTO.getIdsatuan());
        } catch (SQLException ex) {
            Logger.getLogger(SatuanDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return statement;
    }

    @Override
    public String getDeleteQueryByString() {
        return DELETE_SATUAN;
    }


    @Override
    public SatuanDTO getTipeById(String idSatuan) {
        return findSingleByField(idSatuan, GET_SATUAN_BY_ID);
    }

    @Override
    public List<SatuanDTO> selectAllSatuan() {
        return findAndSortByField(SELECT_ALL);
    }

    @Override
    public int getLastId() {
        return findLastIdByField("total", GET_LAST_ID);
    }

}

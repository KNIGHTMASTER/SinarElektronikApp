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
    
    private final String insertSatuan = "INSERT INTO satuan (namasatuan) VALUES (?)";
    
    private final String updateSatuan = "UPDATE satuan SET namasatuan=? WHERE idsatuan=?";
    
    private final String deleteSatuan = "DELETE FROM satuan WHERE idsatuan = ?";
    
    private final String getSatuanById = "SELECT * FROM satuan WHERE idsatuan = ?";
    
    private final String selectAll = "SELECT * FROM satuan ORDER BY namasatuan";
    
    private final String getLastId = "SELECT count(idsatuan)+1 total from satuan ";

    @Override
    public void insertDetail(SatuanDTO satuanDTO, PreparedStatement statement) {
        try {
            statement = connection.prepareStatement(insertSatuan, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, satuanDTO.getNamaSatuan());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SatuanDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public PreparedStatement updateDetail(SatuanDTO satuanDTO, PreparedStatement statement) {
        try {
            statement = connection.prepareStatement(updateSatuan, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, satuanDTO.getNamaSatuan());
            statement.setString(2, satuanDTO.getIdsatuan());
        } catch (SQLException ex) {
            Logger.getLogger(SatuanDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return statement;
    }

    @Override
    public String getDeleteQueryByString() {
        return deleteSatuan;
    }


    @Override
    public SatuanDTO getTipeById(String idSatuan) {
        return findSingleByField(idSatuan, getSatuanById);
    }

    @Override
    public List<SatuanDTO> selectAllSatuan() {
        return findAndSortByField(selectAll);
    }

    @Override
    public int getLastId() {
        return findLastIdByField("total", getLastId);
    }

}

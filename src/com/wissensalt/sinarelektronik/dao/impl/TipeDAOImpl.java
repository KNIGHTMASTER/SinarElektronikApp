package com.wissensalt.sinarelektronik.dao.impl;

import com.wissensalt.sinarelektronik.dao.ABaseDAO;
import com.wissensalt.sinarelektronik.dao.TipeDAO;
import com.wissensalt.sinarelektronik.masterdata.tipe.entity.TipeDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class TipeDAOImpl extends ABaseDAO<TipeDTO> implements TipeDAO {
    
    private final String insertTipe = "INSERT INTO tipe (namaTipe) VALUES (?);";

    private final String updateTipe = "UPDATE tipe SET namaTipe=? WHERE idtipe=?;";

    private final String deleteTipe = "DELETE FROM tipe WHERE idtipe=?;";    
    
    private final String getById = "SELECT * FROM tipe WHERE idtipe=?;";    
    
    private final String selectAll = "SELECT * FROM tipe ORDER BY namaTipe;";    
    
    private final String getLastData = "SELECT count(idtipe) total FROM tipe;";

    @Override
    public void insertDetail(TipeDTO dto, PreparedStatement ps) {
        try {
            ps = connection.prepareStatement(insertTipe, ps.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getNamaTipe());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TipeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public PreparedStatement updateDetail(TipeDTO dto, PreparedStatement ps) {
        try {
            ps = connection.prepareStatement(updateTipe);
            ps.setString(1, dto.getNamaTipe());
            ps.setString(2, dto.getIdTipe());
        } catch (SQLException ex) {
            Logger.getLogger(TipeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ps;
    }

    @Override
    public String getDeleteQueryByString() {
        return deleteTipe;
    }

    @Override
    public TipeDTO getDTOFromResultSet(ResultSet rs) {
        TipeDTO tipeDTO = null;
        try {
            tipeDTO = new TipeDTO();
            tipeDTO.setIdTipe(rs.getString("idtipe"));
            tipeDTO.setNamaTipe(rs.getString("namaTipe"));
        } catch (SQLException ex) {
            Logger.getLogger(TipeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tipeDTO;
    }

    @Override
    public TipeDTO getTipeByid(String idTipe) {
        return findSingleByField(idTipe, getById);
    }

    @Override
    public List<TipeDTO> selectAllTipe() {
        return findAndSortByField(selectAll);
    }

    @Override
    public Integer getLastIdata() {
        return findLastIdByField("total", getLastData);
    }

}

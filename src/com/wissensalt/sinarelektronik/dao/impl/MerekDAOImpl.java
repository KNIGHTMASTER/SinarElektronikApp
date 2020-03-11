package com.wissensalt.sinarelektronik.dao.impl;

import com.wissensalt.sinarelektronik.dao.ABaseDAO;
import com.wissensalt.sinarelektronik.dao.MerekDAO;
import com.wissensalt.sinarelektronik.dto.MerekDTO;

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
public class MerekDAOImpl extends ABaseDAO<MerekDTO> implements MerekDAO {

    private final static String INSERT_MEREK = "INSERT INTO merek (namamerek) VALUES (?)";
        private final static String UPDATE_MEREK = "UPDATE merek SET idmerek=?, namamerek=? WHERE idmerek=?";
    private final static String DELETE_MEREK = "DELETE FROM merek WHERE idmerek= ? ";
    private final static String FIND_BY_ID = "SELECT * FROM merek WHERE idmerek = ?";
    private final static String FIND_ALL = "SELECT * FROM merek ORDER BY namamerek";
    private final static String FIND_LAST_ID = "SELECT count(idmerek) total from merek";

    @Override
    public void insertDetail(MerekDTO dto, PreparedStatement ps) {
        try {
            ps = connection.prepareStatement(INSERT_MEREK, ps.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getNamaMerek());
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(MerekDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public PreparedStatement updateDetail(MerekDTO dto, PreparedStatement ps) {
        try {
            ps = connection.prepareStatement(UPDATE_MEREK);
            ps.setString(1, dto.getIdMerek());
            ps.setString(2, dto.getNamaMerek());
            ps.setString(3, dto.getIdMerek());
        } catch (SQLException e) {
            Logger.getLogger(MerekDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return ps;
    }

    @Override
    public String getDeleteQueryByString() {
        return DELETE_MEREK;
    }

    @Override
    public MerekDTO getDTOFromResultSet(ResultSet rs) {
        MerekDTO merekDTO = new MerekDTO();
        try {
            merekDTO.setIdMerek(rs.getString("idmerek"));
            merekDTO.setNamaMerek(rs.getString("namamerek"));
        } catch (SQLException e) {
            Logger.getLogger(MerekDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return merekDTO;
    }

    @Override
    public List<MerekDTO> getMerekById(String idMerek) {
        return findByField(idMerek, FIND_BY_ID);
    }


    @Override
    public List<MerekDTO> selectAllMerek() {
        return findAndSortByField(FIND_ALL);
    }

    @Override
    public int getLastId() {
        return findLastIdByField("total", FIND_LAST_ID);
    }
}

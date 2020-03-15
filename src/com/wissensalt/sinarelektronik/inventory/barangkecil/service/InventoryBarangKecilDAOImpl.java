package com.wissensalt.sinarelektronik.inventory.barangkecil.service;

import com.wissensalt.sinarelektronik.dao.ABaseDAO;
import com.wissensalt.sinarelektronik.dao.InventoryBarangKecilDAO;
import com.wissensalt.sinarelektronik.dto.InventoryBarangKecilDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InventoryBarangKecilDAOImpl extends ABaseDAO<InventoryBarangKecilDTO> implements InventoryBarangKecilDAO {

    private final String INSERTPROSESINVENTORY = "INSERT INTO prosesinventory(user, tanggal, jam, kode, nama, harga, ekspedisi, jumlah, subharga) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public void insertDetail(InventoryBarangKecilDTO inventoryBarangKecilDTO, PreparedStatement ps) {
        try {           
            ps = connection.prepareStatement(INSERTPROSESINVENTORY, ps.RETURN_GENERATED_KEYS);
            ps.setString(1, inventoryBarangKecilDTO.getUser());
            ps.setString(2, inventoryBarangKecilDTO.getTanggal());
            ps.setString(3, inventoryBarangKecilDTO.getJam());
            ps.setString(4, inventoryBarangKecilDTO.getKode());
            ps.setString(5, inventoryBarangKecilDTO.getNama());
            ps.setInt(6, inventoryBarangKecilDTO.getHarga());
            ps.setInt(7, inventoryBarangKecilDTO.getEkspedisi());
            ps.setInt(8, inventoryBarangKecilDTO.getJumlah());
            ps.setInt(9, inventoryBarangKecilDTO.getSubharga());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                inventoryBarangKecilDTO.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InventoryBarangKecilDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getDeleteQueryById() {
        return "DELETE FROM prosesinventory WHERE id = ?";
    }

    @Override
    public String getTruncateQuery() {
        return "TRUNCATE TABLE prosesinventory";
    }

    @Override
    public String getDeleteQueryByString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

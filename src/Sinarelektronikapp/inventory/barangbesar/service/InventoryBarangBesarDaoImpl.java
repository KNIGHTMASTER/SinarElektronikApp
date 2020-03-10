package Sinarelektronikapp.inventory.barangbesar.service;

import Sinarelektronikapp.dao.ABaseDAO;
import Sinarelektronikapp.dto.InventoryBarangBesarDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InventoryBarangBesarDaoImpl extends ABaseDAO<InventoryBarangBesarDTO> implements InventoryBarangBesarDAO {

    private final String INSERT_PROSES_INVENTORY = "INSERT INTO prosesinventorybarangbesar(user, tanggal, jam, kode, nama, harga, ekspedisi, jumlah, subharga) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";       

    @Override
    public void insertDetail(InventoryBarangBesarDTO inventory, PreparedStatement ps) {
        try {
            ps = connection.prepareStatement(INSERT_PROSES_INVENTORY, ps.RETURN_GENERATED_KEYS);
            ps.setString(1, inventory.getUser());
            ps.setString(2, inventory.getTanggal());
            ps.setString(3, inventory.getJam());
            ps.setString(4, inventory.getKode());
            ps.setString(5, inventory.getNama());
            ps.setInt(6, inventory.getHarga());
            ps.setInt(7, inventory.getEkspedisi());
            ps.setInt(8, inventory.getJumlah());
            ps.setInt(9, inventory.getSubharga());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                inventory.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InventoryBarangBesarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getDeleteQueryById() {
        return "DELETE FROM prosesinventorybarangbesar WHERE id = ?";
    }

    @Override
    public String getTruncateQuery() {
        return "TRUNCATE TABLE prosesinventorybarangbesar";
    }

    @Override
    public String getDeleteQueryByString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

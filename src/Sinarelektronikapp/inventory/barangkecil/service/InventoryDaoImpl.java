/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.inventory.barangkecil.service;

import Sinarelektronikapp.inventory.barangkecil.entity.Inventory;
import Sinarelektronikapp.inventory.barangkecil.error.InventoryException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class InventoryDaoImpl implements InventoryDao {

    private final String INSERTPROSESINVENTORY = "INSERT INTO prosesinventory(user, tanggal, jam, kode, nama, harga, ekspedisi, jumlah, subharga) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private final String DELETEPROSESINVENTORY = "DELETE FROM prosesinventory WHERE id = ?";
    
    private final String TRUNCATEPROSESINVENTORY = "TRUNCATE TABLE prosesinventory";
    
    Connection c;

    public InventoryDaoImpl(Connection c) {
        this.c = c;
    }
        
    @Override
    public void insertProsesInventory(Inventory inventory) throws InventoryException {
        PreparedStatement ps = null;
        try{
            c.setAutoCommit(false);
            ps = c.prepareStatement(INSERTPROSESINVENTORY, ps.RETURN_GENERATED_KEYS);
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
            c.commit();
        }catch(SQLException exception){
            try {
                c.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(InventoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Error dalam insert proses inventory karena = "+exception);
        }finally{
            try {
                c.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(InventoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InventoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void deleteProsesInventory(int id) throws InventoryException {
        PreparedStatement ps = null;
        try{
            c.setAutoCommit(false);
            ps = c.prepareStatement(DELETEPROSESINVENTORY);
            ps.setInt(1, id);
            ps.executeUpdate();
            
            c.commit();
        }catch(SQLException exception){
            try {
                c.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(InventoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Error dalam delete proses inventory karena = "+exception);
        }finally{
            try {
                c.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(InventoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InventoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void truncateProsesInventory() throws InventoryException {
        PreparedStatement ps = null;
        try{
            c.setAutoCommit(false);
            ps = c.prepareStatement(TRUNCATEPROSESINVENTORY);
            ps.executeUpdate();
            c.commit();
        }catch(SQLException exception){
            try {
                c.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(InventoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Error dalam truncate proses inventory karena = "+exception);
        }finally{
            try {
                c.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(InventoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InventoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}

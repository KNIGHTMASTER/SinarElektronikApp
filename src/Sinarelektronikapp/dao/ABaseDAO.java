package Sinarelektronikapp.dao;

import Sinarelektronikapp.dto.BaseDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Fauzi
 * @param <DTO>
 */
public class ABaseDAO<DTO extends BaseDTO> implements IBaseDAO<DTO> {

    private Connection c;
    
    void init() {
        c = GenericConnection.getConnection();
    }
    
    @Override
    public void insert(DTO dto) {
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
    public void deleteByInt(int arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteByString(String arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void truncate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

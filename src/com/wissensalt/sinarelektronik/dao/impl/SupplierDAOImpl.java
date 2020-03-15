package com.wissensalt.sinarelektronik.dao.impl;

import com.wissensalt.sinarelektronik.dao.ABaseDAO;
import com.wissensalt.sinarelektronik.masterdata.supplier.entity.SupplierDTO;
import com.wissensalt.sinarelektronik.dao.SupplierDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class SupplierDAOImpl extends ABaseDAO<SupplierDTO> implements SupplierDAO {
    
    private final String insertSupplier = "INSERT INTO supplier (nama, alamat, kota, propinsi, kodePost, telepon, fax, bank, nomorRek, atasNama, kontakPerson, email, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private final String updateSupplier = "UPDATE supplier SET nama = ?, alamat = ?, kota = ?, propinsi = ?, kodePost = ?, telepon = ?, fax = ?, nomorRek = ?, atasNama = ?, kontakPerson = ?, email = ?, note = ? WHERE idsupplier = ?";
    
    private final String deleteSupplier = "DELETE FROM supplier WHERE idsupplier = ?";
    
    private final String searchByid = "SELECT * FROM supplier WHERE idsupplier LIKE ?";

    private final String searchByIdEqual = "SELECT * FROM supplier WHERE idsupplier = ?";
    
    private final String searchByNama = "SELECT * FROM supplier WHERE nama LIKE ?";
    
    private final String searchByAlamat = "SELECT * FROM supplier WHERE alamat LIKE ?";
    
    private final String searchByKota = "SELECT * FROM supplier WHERE kota LIKE ?";
    
    private final String searchByPropinsi = "SELECT * FROM supplier WHERE propinsi LIKE ?";    
    
    private final String searchByKodePost = "SELECT * FROM supplier WHERE kodePost LIKE ?";
    
    private final String searchByTelepon = "SELECT * FROM supplier WHERE telepon LIKE ?";
    
    private final String searchByFax = "SELECT * FROM supplier WHERE fax LIKE ?";
    
    private final String searchByBank = "SELECT * FROM supplier WHERE bank LIKE ?";
    
    private final String searchByNomorRek = "SELECT * FROM supplier WHERE nomorRek LIKE ?";
    
    private final String searchByAtasNama = "SELECT * FROM supplier WHERE atasNama LIKE ?";
    
    private final String searchByKontakPerson = "SELECT * FROM supplier WHERE kontakPerson LIKE ?";
    
    private final String searchByEmail  = "SELECT * FROM supplier WHERE email LIKE ?";
    
    private final String searchByNote = "SELECT * FROM supplier WHERE note LIKE ?";
    
    private final String sortByid = "SELECT * FROM supplier ORDER BY idsupplier";
    
    private final String sortByNama = "SELECT * FROM supplier ORDER BY nama";
    
    private final String sortByAlamat = "SELECT * FROM supplier ORDER BY alamat";
    
    private final String sortByKota = "SELECT * FROM supplier ORDER BY kota";
    
    private final String sortByPropinsi = "SELECT * FROM supplier ORDER BY propinsi";    
    
    private final String sortByKodePost = "SELECT * FROM supplier ORDER BY kodePost";
    
    private final String sortByTelepon = "SELECT * FROM supplier ORDER BY telepon";
    
    private final String sortByFax = "SELECT * FROM supplier ORDER BY fax";
    
    private final String sortByBank = "SELECT * FROM supplier ORDER BY bank";
    
    private final String sortByNomorRek = "SELECT * FROM supplier ORDER BY nomorRek";
    
    private final String sortByAtasNama = "SELECT * FROM supplier ORDER BY atasNama";
    
    private final String sortByKontakPerson = "SELECT * FROM supplier ORDER BY kontakPerson";
    
    private final String sortByEmail  = "SELECT * FROM supplier ORDER BY email";
    
    private final String sortByNote = "SELECT * FROM supplier ORDER BY note";    
    
    private final String selectAllSupplier = "SELECT * FROM supplier";
    
    public final String getLastId = "SELECT COUNT(idsupplier)+1 TOTAL FROM supplier";

    @Override
    public void insertDetail(SupplierDTO supplierDTO, PreparedStatement ps) {
        try {
            ps =connection.prepareStatement(insertSupplier, ps.RETURN_GENERATED_KEYS);
            ps.setString(1, supplierDTO.getNama());
            ps.setString(2, supplierDTO.getAlamat());
            ps.setString(3, supplierDTO.getKota());
            ps.setString(4, supplierDTO.getPropinsi());
            ps.setString(5, supplierDTO.getKodePost());
            ps.setString(6, supplierDTO.getTelepon());
            ps.setString(7, supplierDTO.getFax());
            ps.setString(8, supplierDTO.getBank());
            ps.setString(9, supplierDTO.getNomorRek());
            ps.setString(10, supplierDTO.getAtasNama());
            ps.setString(11, supplierDTO.getKontakPerson());
            ps.setString(12, supplierDTO.getEmail());
            ps.setString(13, supplierDTO.getNote());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public PreparedStatement updateDetail(SupplierDTO supplierDTO, PreparedStatement ps) {
        try {
            ps = connection.prepareStatement(updateSupplier);
            ps.setString(1, supplierDTO.getNama());
            ps.setString(2, supplierDTO.getAlamat());
            ps.setString(3, supplierDTO.getKota());
            ps.setString(4, supplierDTO.getPropinsi());
            ps.setString(5, supplierDTO.getKodePost());
            ps.setString(6, supplierDTO.getTelepon());
            ps.setString(7, supplierDTO.getFax());
            ps.setString(8, supplierDTO.getBank());
            ps.setString(9, supplierDTO.getNomorRek());
            ps.setString(10, supplierDTO.getAtasNama());
            ps.setString(11, supplierDTO.getKontakPerson());
            ps.setString(12, supplierDTO.getEmail());
            ps.setString(13, supplierDTO.getNote());
            ps.setString(14, supplierDTO.getIdsupplier());
                        
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ps;
    }

    @Override
    public List<SupplierDTO> searchById(String idSupplier) {
        return findByFieldLike(idSupplier, searchByid);
    }

    @Override
    public List<SupplierDTO> searchByNama(String namaSupplier) {
        return findByFieldLike(namaSupplier, searchByNama);
    }

    @Override
    public List<SupplierDTO> searchByAlamat(String alamatSupplier) {
        return findByFieldLike(alamatSupplier, searchByAlamat);
    }

    @Override
    public List<SupplierDTO> searchByKota(String kotaSupplier) {
        return findByFieldLike(kotaSupplier, searchByKota);
    }

    @Override
    public List<SupplierDTO> searchByPropinsi(String propinsiSupplier) {
        return findByFieldLike(propinsiSupplier, searchByPropinsi);
    }

    @Override
    public List<SupplierDTO> searchByKodePost(String kodePostSupplier) {
        return findByFieldLike(kodePostSupplier, searchByKodePost);
    }

    @Override
    public List<SupplierDTO> searchByTelepon(String teleponSupplier) {
        return findByFieldLike(teleponSupplier, searchByTelepon);
    }

    @Override
    public List<SupplierDTO> searchByFax(String faxSupplier) {
        return findByFieldLike(faxSupplier, searchByFax);
    }

    @Override
    public List<SupplierDTO> searchByBank(String bankSupplier) {
        return findByFieldLike(bankSupplier, searchByBank);
    }

    @Override
    public List<SupplierDTO> searchByNomorRek(String nomorRekSupplier) {
        return findByFieldLike(nomorRekSupplier, searchByNomorRek);
    }

    @Override
    public List<SupplierDTO> searchByAtasNama(String atasNamaSupplier) {
        return findByFieldLike(atasNamaSupplier, searchByAtasNama);
    }

    @Override
    public List<SupplierDTO> searchByKontakPerson(String kontakPersonSupplier) {
        return findByFieldLike(kontakPersonSupplier, searchByKontakPerson);
    }

    @Override
    public List<SupplierDTO> searchByEmail(String emailSupplier) {
        return findByFieldLike(emailSupplier, searchByEmail);
    }

    @Override
    public List<SupplierDTO> searchByNote(String noteSupplier) {
        return findByFieldLike(noteSupplier, searchByNote);
    }

    @Override
    public List<SupplierDTO> sortById() {
        return findAndSortByField(sortByid);
    }

    @Override
    public List<SupplierDTO> sortByNama() {
        return findAndSortByField(sortByNama);
    }

    @Override
    public List<SupplierDTO> sortByAlamat() {
        return findAndSortByField(sortByAlamat);
    }

    @Override
    public List<SupplierDTO> sortByKota() {
        return findAndSortByField(sortByKota);
    }

    @Override
    public List<SupplierDTO> sortByPropinsi() {
        return findAndSortByField(sortByPropinsi);
    }

    @Override
    public List<SupplierDTO> sortByKodePost() {
        return findAndSortByField(sortByKodePost);
    }

    @Override
    public List<SupplierDTO> sortByTelepon() {
        return findAndSortByField(sortByTelepon);
    }

    @Override
    public List<SupplierDTO> sortByFax() {
        return findAndSortByField(sortByFax);
    }

    @Override
    public List<SupplierDTO> sortByBank() {
        return findAndSortByField(sortByBank);
    }

    @Override
    public List<SupplierDTO> sortByNomorRek() {
        return findAndSortByField(sortByNomorRek);
    }

    @Override
    public List<SupplierDTO> sortByAtasNama() {
        return findAndSortByField(sortByAtasNama);
    }

    @Override
    public List<SupplierDTO> sortByKontakPerson() {
        return findAndSortByField(sortByKontakPerson);
    }

    @Override
    public List<SupplierDTO> sortByEmail() {
        return findAndSortByField(sortByEmail);
    }

    @Override
    public List<SupplierDTO> sortByNote() {
        return findAndSortByField(sortByNote);
    }

    @Override
    public List<SupplierDTO> selectAllSupplier() {
        return findAndSortByField(selectAllSupplier);
    }

    @Override
    public int getLastId() {
        return findLastIdByField("TOTAL", getLastId);
    }

    public String getSearchByIdEqual() {
        return searchByIdEqual;
    }
}

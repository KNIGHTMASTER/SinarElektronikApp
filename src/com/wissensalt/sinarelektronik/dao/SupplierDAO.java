package com.wissensalt.sinarelektronik.dao;

import com.wissensalt.sinarelektronik.masterdata.supplier.entity.SupplierDTO;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface SupplierDAO extends IBaseDAO<SupplierDTO> {   
   
    List<SupplierDTO> searchById(String idSupplier);
    
    List<SupplierDTO> searchByNama(String namaSupplier);
    
    List<SupplierDTO> searchByAlamat(String alamatSupplier);
    
    List<SupplierDTO> searchByKota(String kotaSupplier);
    
    List<SupplierDTO> searchByPropinsi(String propinsiSupplier);
    
    List<SupplierDTO> searchByKodePost(String kodePostSupplier);
    
    List<SupplierDTO> searchByTelepon(String teleponSupplier);
    
    List<SupplierDTO> searchByFax(String faxSupplier);
    
    List<SupplierDTO> searchByBank(String bankSupplier);
    
    List<SupplierDTO> searchByNomorRek(String nomorRekSupplier);
    
    List<SupplierDTO> searchByAtasNama(String atasNamaSupplier);
    
    List<SupplierDTO> searchByKontakPerson(String kontakPersonSupplier);
    
    List<SupplierDTO> searchByEmail(String emailSupplier);
    
    List<SupplierDTO> searchByNote(String noteSupplier);
    
    List<SupplierDTO> sortById(String idSupplier);
    
    List<SupplierDTO> sortByNama(String namaSupplier);
    
    List<SupplierDTO> sortByAlamat(String alamatSupplier);
    
    List<SupplierDTO> sortByKota(String kotaSupplier);
    
    List<SupplierDTO> sortByPropinsi(String propinsiSupplier);
    
    List<SupplierDTO> sortByKodePost(String kodePostSupplier);
    
    List<SupplierDTO> sortByTelepon(String teleponSupplier);
    
    List<SupplierDTO> sortByFax(String faxSupplier);
    
    List<SupplierDTO> sortByBank(String bankSupplier);
    
    List<SupplierDTO> sortByNomorRek(String nomorRekSupplier);
    
    List<SupplierDTO> sortByAtasNama(String atasNamaSupplier);
    
    List<SupplierDTO> sortByKontakPerson(String kontakPersonSupplier);
    
    List<SupplierDTO> sortByEmail(String emailSupplier);
    
    List<SupplierDTO> sortByNote(String noteSupplier);
    
    List<SupplierDTO> selectAllSupplier() ;
    
    int getLastId();
    
}

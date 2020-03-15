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
    
    List<SupplierDTO> sortById();
    
    List<SupplierDTO> sortByNama();
    
    List<SupplierDTO> sortByAlamat();
    
    List<SupplierDTO> sortByKota();
    
    List<SupplierDTO> sortByPropinsi();
    
    List<SupplierDTO> sortByKodePost();
    
    List<SupplierDTO> sortByTelepon();
    
    List<SupplierDTO> sortByFax();
    
    List<SupplierDTO> sortByBank();
    
    List<SupplierDTO> sortByNomorRek();
    
    List<SupplierDTO> sortByAtasNama();
    
    List<SupplierDTO> sortByKontakPerson();
    
    List<SupplierDTO> sortByEmail();
    
    List<SupplierDTO> sortByNote();
    
    List<SupplierDTO> selectAllSupplier() ;
    
    int getLastId();
    
}

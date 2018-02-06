/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.supplier.service;

import Sinarelektronikapp.masterData.supplier.entity.supplier;
import Sinarelektronikapp.masterData.supplier.error.supplierException;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface supplierDao {
    public void insertSupplier(supplier supplier)throws supplierException;
    
    public void updateSupplier(supplier supplier)throws supplierException;
    
    public void deleteSupplier(String idSupplier)throws supplierException;
    
    public List<supplier> searchById(String idSupplier)throws supplierException;
    
    public List<supplier> searchByNama(String namaSupplier)throws supplierException;
    
    public List<supplier> searchByAlamat(String alamatSupplier)throws supplierException;
    
    public List<supplier> searchByKota(String kotaSupplier)throws supplierException;
    
    public List<supplier> searchByPropinsi(String propinsiSupplier)throws supplierException;
    
    public List<supplier> searchByKodePost(String kodePostSupplier)throws supplierException;
    
    public List<supplier> searchByTelepon(String teleponSupplier)throws supplierException;
    
    public List<supplier> searchByFax(String faxSupplier)throws supplierException;
    
    public List<supplier> searchByBank(String BankSupplier)throws supplierException;
    
    public List<supplier> searchByNomorRek(String nomorRekSupplier)throws supplierException;
    
    public List<supplier> searchByAtasNama(String atasNamaSupplier)throws supplierException;
    
    public List<supplier> searchByKontakPerson(String kontakPersonSupplier)throws supplierException;
    
    public List<supplier> searchByEmail(String emailSupplier)throws supplierException;
    
    public List<supplier> searchByNote(String noteSupplier)throws supplierException;
    
    public List<supplier> sortById(String idSupplier)throws supplierException;
    
    public List<supplier> sortByNama(String namaSupplier)throws supplierException;
    
    public List<supplier> sortByAlamat(String alamatSupplier)throws supplierException;
    
    public List<supplier> sortByKota(String kotaSupplier)throws supplierException;
    
    public List<supplier> sortByPropinsi(String propinsiSupplier)throws supplierException;
    
    public List<supplier> sortByKodePost(String kodePostSupplier)throws supplierException;
    
    public List<supplier> sortByTelepon(String teleponSupplier)throws supplierException;
    
    public List<supplier> sortByFax(String faxSupplier)throws supplierException;
    
    public List<supplier> sortByBank(String BankSupplier)throws supplierException;
    
    public List<supplier> sortByNomorRek(String nomorRekSupplier)throws supplierException;
    
    public List<supplier> sortByAtasNama(String atasNamaSupplier)throws supplierException;
    
    public List<supplier> sortByKontakPerson(String kontakPersonSupplier)throws supplierException;
    
    public List<supplier> sortByEmail(String emailSupplier)throws supplierException;
    
    public List<supplier> sortByNote(String noteSupplier)throws supplierException;
    
    public List<supplier> selectAllSupplier() throws supplierException;    
    
    public int getLastId()throws supplierException;
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.pelanggan.service;

import Sinarelektronikapp.masterdata.pelanggan.entity.pelanggan;
import Sinarelektronikapp.masterdata.pelanggan.error.pelangganException;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface pelangganDao {
    public void insertPelanggan(pelanggan pelanggan) throws pelangganException;
    
    public void updatePelanggan(pelanggan pelanggan) throws pelangganException;
    
    public void deletePelanggan(String idPelanggan) throws pelangganException;
    
    public List<pelanggan> selectAllPelanggan() throws pelangganException;
    
    public List<pelanggan> getById(String id) throws pelangganException;
    
    public List<pelanggan> getByNama(String nama) throws pelangganException;
    
    public List<pelanggan> getByALamat(String alamat) throws pelangganException;
    
    public List<pelanggan> getByKota(String kota) throws pelangganException;
    
    public List<pelanggan> getByPropinsi(String propinsi) throws pelangganException;
    
    public List<pelanggan> getByKodePost(String kodePost) throws pelangganException;
    
    public List<pelanggan> getByTelepon(String telepon) throws pelangganException;
    
    public List<pelanggan> getByFax(String fax) throws pelangganException;
    
    public List<pelanggan> getByKontakPerson(String kontakPerson) throws pelangganException;
    
    public List<pelanggan> getByCatatan(String catatan) throws pelangganException;
    
    public List<pelanggan> sortById() throws pelangganException;
    
    public List<pelanggan> sortByNama() throws pelangganException;
    
    public List<pelanggan> sortByALamat() throws pelangganException;
    
    public List<pelanggan> sortByKota() throws pelangganException;
    
    public List<pelanggan> sortByPropinsi() throws pelangganException;
    
    public List<pelanggan> sortByKodePost() throws pelangganException;
    
    public List<pelanggan> sortByTelepon() throws pelangganException;
    
    public List<pelanggan> sortByFax() throws pelangganException;
    
    public List<pelanggan> sortByKontakPerson() throws pelangganException;
    
    public List<pelanggan> sortByCatatan() throws pelangganException;
    
    public int getLastId() throws pelangganException;
    
}

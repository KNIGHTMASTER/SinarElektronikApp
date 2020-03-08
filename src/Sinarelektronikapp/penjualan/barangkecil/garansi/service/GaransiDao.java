/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.penjualan.barangkecil.garansi.service;

import Sinarelektronikapp.penjualan.barangkecil.garansi.entity.Garansi;
import Sinarelektronikapp.penjualan.barangkecil.garansi.error.GaransiException;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface GaransiDao {
    
    public void insertGaransi(Garansi garansi) throws GaransiException;
    
    public void updateGaransi(Garansi garansi) throws GaransiException;
    
    public void deleteGaransi(String idGaransi) throws GaransiException;
    
    //public Garansi getGaransiById() throws penjualanException;
    
    public List<Garansi> getAllGaransi() throws GaransiException;
    
    public List<Garansi> searchGaransiByid(String idGaransi) throws GaransiException;
    
    public List<Garansi> searchGaransiByNama(String namaBarang) throws GaransiException;
    
    public List<Garansi> searchGaransiByJumlah(int jumlah) throws GaransiException;
    
    public List<Garansi> searchGaransiByMasaAkhir(String masaAkhir) throws GaransiException;
    
    public List<Garansi> searchGaransiByStatus(String status) throws GaransiException;
    
    public List<Garansi> sortGaransiByid() throws GaransiException;
    
    public List<Garansi> sortGaransiByNama() throws GaransiException;
    
    public List<Garansi> sortGaransiByJumlah() throws GaransiException;
    
    public List<Garansi> sortGaransiByMasaAkhir() throws GaransiException;
    
    public List<Garansi> sortGaransiByStatus() throws GaransiException;    
            
}

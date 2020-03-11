/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.barangkecil.stokreminder.service;

import com.wissensalt.sinarelektronik.masterdata.barangkecil.entity.BarangKecilDTO;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.error.BarangException;

/**
 *
 * @author Fauzi
 */
public interface ReminderDao {
    
    public void updateBarang(BarangKecilDTO BarangKecilDTO) throws BarangException;
    
    public java.util.List<BarangKecilDTO> getBarangbyId(String idBarang) throws BarangException;
    
    public java.util.List<BarangKecilDTO> getBarangbyBarcode(String idBarcode) throws BarangException;
    
    public java.util.List<BarangKecilDTO> getBarangbyNama(String nama) throws BarangException;
    
    public java.util.List<BarangKecilDTO> getBarangbyTipe(String tipe) throws BarangException;
    
    public java.util.List<BarangKecilDTO> getBarangbyMerek(String merek) throws BarangException;
    
    public java.util.List<BarangKecilDTO> getBarangbyHarga(String Harga) throws BarangException;
    
    public java.util.List<BarangKecilDTO> getBarangbySatuan(String satuan) throws BarangException;
    
    public java.util.List<BarangKecilDTO> getBarangbyStok(String stok) throws BarangException;
    
    public java.util.List<BarangKecilDTO> getBarangbyStok_min(String stok_min) throws BarangException;
    
    public java.util.List<BarangKecilDTO> getBarangbySupplier(String supplier) throws BarangException;
    
    public java.util.List<BarangKecilDTO> getBarangbyKeterangan(String keterangan) throws BarangException;
    
    public java.util.List<BarangKecilDTO> sortBarangbyId() throws BarangException;
    
    public java.util.List<BarangKecilDTO> sortBarangbyBarcode() throws BarangException;
    
    public java.util.List<BarangKecilDTO> sortBarangbyNama() throws BarangException;
    
    public java.util.List<BarangKecilDTO> sortBarangbyTipe() throws BarangException;
    
    public java.util.List<BarangKecilDTO> sortBarangbyMerek() throws BarangException;
    
    public java.util.List<BarangKecilDTO> sortBarangbyHarga() throws BarangException;
    
    public java.util.List<BarangKecilDTO> sortBarangbySatuan() throws BarangException;
    
    public java.util.List<BarangKecilDTO> sortBarangbyStok() throws BarangException;
    
    public java.util.List<BarangKecilDTO> sortBarangbyStok_min() throws BarangException;
    
    public java.util.List<BarangKecilDTO> sortBarangbySupplier() throws BarangException;
    
    public java.util.List<BarangKecilDTO> sortBarangbyKeterangan() throws BarangException;
    
    public java.util.List<BarangKecilDTO> selectAllBarang()throws BarangException;
    
    public int getBarangLastId() throws BarangException;        
}

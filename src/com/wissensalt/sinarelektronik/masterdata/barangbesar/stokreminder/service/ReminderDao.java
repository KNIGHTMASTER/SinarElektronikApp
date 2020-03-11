/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.barangbesar.stokreminder.service;

import com.wissensalt.sinarelektronik.dto.BarangBesarDTO;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.error.BarangException;

/**
 *
 * @author Fauzi
 */
public interface ReminderDao {
    
    public void updateBarang(BarangBesarDTO BarangBesarDTO) throws BarangException;
    
    public java.util.List<BarangBesarDTO> getBarangbyId(String idBarang) throws BarangException;
    
    public java.util.List<BarangBesarDTO> getBarangbyBarcode(String idBarcode) throws BarangException;
    
    public java.util.List<BarangBesarDTO> getBarangbyNama(String nama) throws BarangException;
    
    public java.util.List<BarangBesarDTO> getBarangbyTipe(String tipe) throws BarangException;
    
    public java.util.List<BarangBesarDTO> getBarangbyMerek(String merek) throws BarangException;
    
    public java.util.List<BarangBesarDTO> getBarangbyHarga(String Harga) throws BarangException;
    
    public java.util.List<BarangBesarDTO> getBarangbySatuan(String satuan) throws BarangException;
    
    public java.util.List<BarangBesarDTO> getBarangbyStok(String stok) throws BarangException;
    
    public java.util.List<BarangBesarDTO> getBarangbyStok_min(String stok_min) throws BarangException;
    
    public java.util.List<BarangBesarDTO> getBarangbySupplier(String supplier) throws BarangException;
    
    public java.util.List<BarangBesarDTO> getBarangbyKeterangan(String keterangan) throws BarangException;
    
    public java.util.List<BarangBesarDTO> sortBarangbyId() throws BarangException;
    
    public java.util.List<BarangBesarDTO> sortBarangbyBarcode() throws BarangException;
    
    public java.util.List<BarangBesarDTO> sortBarangbyNama() throws BarangException;
    
    public java.util.List<BarangBesarDTO> sortBarangbyTipe() throws BarangException;
    
    public java.util.List<BarangBesarDTO> sortBarangbyMerek() throws BarangException;
    
    public java.util.List<BarangBesarDTO> sortBarangbyHarga() throws BarangException;
    
    public java.util.List<BarangBesarDTO> sortBarangbySatuan() throws BarangException;
    
    public java.util.List<BarangBesarDTO> sortBarangbyStok() throws BarangException;
    
    public java.util.List<BarangBesarDTO> sortBarangbyStok_min() throws BarangException;
    
    public java.util.List<BarangBesarDTO> sortBarangbySupplier() throws BarangException;
    
    public java.util.List<BarangBesarDTO> sortBarangbyKeterangan() throws BarangException;
    
    public java.util.List<BarangBesarDTO> selectAllBarang()throws BarangException;
    
    public int getBarangLastId() throws BarangException;        
}

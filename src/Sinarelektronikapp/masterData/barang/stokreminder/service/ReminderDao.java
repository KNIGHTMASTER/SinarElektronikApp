/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.barang.stokreminder.service;

import Sinarelektronikapp.masterData.barang.entity.barang;
import Sinarelektronikapp.masterData.barang.error.BarangException;

/**
 *
 * @author Fauzi
 */
public interface ReminderDao {
    
    public void updateBarang(barang barang) throws BarangException;    
    
    public java.util.List<barang> getBarangbyId(String idBarang) throws BarangException;
    
    public java.util.List<barang> getBarangbyBarcode(String idBarcode) throws BarangException;        
    
    public java.util.List<barang> getBarangbyNama(String nama) throws BarangException;
    
    public java.util.List<barang> getBarangbyTipe(String tipe) throws BarangException;
    
    public java.util.List<barang> getBarangbyMerek(String merek) throws BarangException;
    
    public java.util.List<barang> getBarangbyHarga(String Harga) throws BarangException;
    
    public java.util.List<barang> getBarangbySatuan(String satuan) throws BarangException;
    
    public java.util.List<barang> getBarangbyStok(String stok) throws BarangException;
    
    public java.util.List<barang> getBarangbyStok_min(String stok_min) throws BarangException;
    
    public java.util.List<barang> getBarangbySupplier(String supplier) throws BarangException;
    
    public java.util.List<barang> getBarangbyKeterangan(String keterangan) throws BarangException;
    
    public java.util.List<barang> sortBarangbyId() throws BarangException;
    
    public java.util.List<barang> sortBarangbyBarcode() throws BarangException;        
    
    public java.util.List<barang> sortBarangbyNama() throws BarangException;
    
    public java.util.List<barang> sortBarangbyTipe() throws BarangException;
    
    public java.util.List<barang> sortBarangbyMerek() throws BarangException;
    
    public java.util.List<barang> sortBarangbyHarga() throws BarangException;
    
    public java.util.List<barang> sortBarangbySatuan() throws BarangException;
    
    public java.util.List<barang> sortBarangbyStok() throws BarangException;
    
    public java.util.List<barang> sortBarangbyStok_min() throws BarangException;
    
    public java.util.List<barang> sortBarangbySupplier() throws BarangException;
    
    public java.util.List<barang> sortBarangbyKeterangan() throws BarangException;    
    
    public java.util.List<barang> selectAllBarang()throws BarangException;    
    
    public int getBarangLastId() throws BarangException;        
}

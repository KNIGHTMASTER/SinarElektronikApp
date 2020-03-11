/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.penjualan.barangkecil.garansi.model;

import com.wissensalt.sinarelektronik.penjualan.barangkecil.garansi.database.GaransiDatabase;
import com.wissensalt.sinarelektronik.penjualan.barangkecil.garansi.entity.Garansi;
import com.wissensalt.sinarelektronik.penjualan.barangkecil.garansi.error.GaransiException;
import com.wissensalt.sinarelektronik.penjualan.barangkecil.garansi.event.GaransiListener;
import com.wissensalt.sinarelektronik.penjualan.barangkecil.garansi.service.GaransiDao;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class GaransiModel {
    String idTransaksi, namaBarang, status, masaakhir;
    int jumlah;
    
    private GaransiListener listener;

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
        fireOnChange();
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
        fireOnChange();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        fireOnChange();
    }

    public String getMasaakhir() {
        return masaakhir;
    }

    public void setMasaakhir(String masaakhir) {
        this.masaakhir = masaakhir;
        fireOnChange();
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
        fireOnChange();
    }

    public GaransiListener getListener() {
        return listener;
    }

    public void setListener(GaransiListener listener) {
        this.listener = listener;
    }
    
    public void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }
    
    public void fireOnInsert(Garansi garansi){
        if(listener!=null){
            listener.onInsert(garansi);
        }
    }
    
    public void fireOnUpdate(Garansi garansi){
        if(listener!=null){
            listener.onUpdate(garansi);
        }
    }
    
    public void fireOnDelete(){
        if(listener!=null){
            listener.onDelete();
        }
    }
    
    public void fireOnSearch(List list){
        if(listener!=null){
            listener.onSearch(list);
        }
    }
    
    public void fireOnSort(List list){
        if(listener!=null){
            listener.onSort(list);
        }
    }
    
    public void resetGaransi(){
        setIdTransaksi("");
        setJumlah(0);
        setMasaakhir(null);
        setNamaBarang("");
        setStatus("");
    }    
    
    public void insertGaransi(){
        Garansi garansi = new Garansi();
        garansi.setIdTransaksi(idTransaksi);
        garansi.setJumlah(jumlah);
        garansi.setMasaakhir(masaakhir);
        garansi.setNamaBarang(namaBarang);
        garansi.setStatus(status);
        
        GaransiDao dao = GaransiDatabase.getGaransiDao();
        try {
            dao.insertGaransi(garansi);
        } catch (GaransiException ex) {
            Logger.getLogger(GaransiModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireOnInsert(garansi);
    }
    
    public void updateGaransi(){
        Garansi garansi = new Garansi();
        garansi.setIdTransaksi(idTransaksi);
        garansi.setJumlah(jumlah);
        garansi.setMasaakhir(masaakhir);
        garansi.setNamaBarang(namaBarang);
        garansi.setStatus(status);
        
        GaransiDao dao = GaransiDatabase.getGaransiDao();
        try {
            dao.updateGaransi(garansi);
        } catch (GaransiException ex) {
            Logger.getLogger(GaransiModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireOnUpdate(garansi);
    }
    
    public void deleteGaransi(){
        GaransiDao dao = GaransiDatabase.getGaransiDao();
        try {
            dao.deleteGaransi(idTransaksi);
        } catch (GaransiException ex) {
            Logger.getLogger(GaransiModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireOnDelete();        
    }
    
    public void searchById(){
        GaransiDao dao = GaransiDatabase.getGaransiDao();
        List<Garansi> list = new ArrayList<Garansi>();
        try {
            dao.searchGaransiByid(idTransaksi);
        } catch (GaransiException ex) {
            Logger.getLogger(GaransiModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void searchByNama(){
        GaransiDao dao = GaransiDatabase.getGaransiDao();
        List<Garansi> list = new ArrayList<Garansi>();
        try {
            dao.searchGaransiByNama(namaBarang);
        } catch (GaransiException ex) {
            Logger.getLogger(GaransiModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void searchByJumlah(){
        GaransiDao dao = GaransiDatabase.getGaransiDao();
        List<Garansi> list = new ArrayList<Garansi>();
        try {
            dao.searchGaransiByJumlah(jumlah);
        } catch (GaransiException ex) {
            Logger.getLogger(GaransiModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void searchByMasaAkhir(){
        GaransiDao dao = GaransiDatabase.getGaransiDao();
        List<Garansi> list = new ArrayList<Garansi>();
        try {
            dao.searchGaransiByMasaAkhir(masaakhir);
        } catch (GaransiException ex) {
            Logger.getLogger(GaransiModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void searchByStatus(){
        GaransiDao dao = GaransiDatabase.getGaransiDao();
        List<Garansi> list = new ArrayList<Garansi>();
        try {
            dao.searchGaransiByStatus(status);
        } catch (GaransiException ex) {
            Logger.getLogger(GaransiModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sortById(){
        GaransiDao dao = GaransiDatabase.getGaransiDao();
        List<Garansi> list = new ArrayList<Garansi>();
        try {
            dao.sortGaransiByid();
        } catch (GaransiException ex) {
            Logger.getLogger(GaransiModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sortByNama(){
        GaransiDao dao = GaransiDatabase.getGaransiDao();
        List<Garansi> list = new ArrayList<Garansi>();
        try {
            dao.sortGaransiByNama();
        } catch (GaransiException ex) {
            Logger.getLogger(GaransiModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void sortByJumlah(){
        GaransiDao dao = GaransiDatabase.getGaransiDao();
        List<Garansi> list = new ArrayList<Garansi>();
        try {
            dao.sortGaransiByJumlah();
        } catch (GaransiException ex) {
            Logger.getLogger(GaransiModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void sortByMasaAkhir(){
        GaransiDao dao = GaransiDatabase.getGaransiDao();
        List<Garansi> list = new ArrayList<Garansi>();
        try {
            dao.sortGaransiByMasaAkhir();
        } catch (GaransiException ex) {
            Logger.getLogger(GaransiModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void sortByStatus(){
        GaransiDao dao = GaransiDatabase.getGaransiDao();
        List<Garansi> list = new ArrayList<Garansi>();
        try {
            dao.sortGaransiByStatus();
        } catch (GaransiException ex) {
            Logger.getLogger(GaransiModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}

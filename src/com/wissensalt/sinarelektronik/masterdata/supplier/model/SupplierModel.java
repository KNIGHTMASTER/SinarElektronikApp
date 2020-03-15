package com.wissensalt.sinarelektronik.masterdata.supplier.model;

import com.wissensalt.sinarelektronik.dao.SupplierDAO;
import com.wissensalt.sinarelektronik.dao.impl.SupplierDAOImpl;
import com.wissensalt.sinarelektronik.masterdata.supplier.entity.SupplierDTO;
import com.wissensalt.sinarelektronik.masterdata.supplier.model.event.supplierListener;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public class SupplierModel {
    private String cari, cmbcari, cmbsort;
    private String idsupplier, nama, alamat, kota, propinsi, kodePost, 
            telepon, fax, bank, nomorRek, atasNama, kontakPerson, email, note;
    private supplierListener listener;
    private final SupplierDAO supplierDAO;

    public SupplierModel() {
        supplierDAO = new SupplierDAOImpl();
    }

    public String getCari() {
        return cari;
    }

    public void setCari(String cari) {
        this.cari = cari;
    }

    public String getCmbcari() {
        return cmbcari;
    }

    public void setCmbcari(String cmbcari) {
        this.cmbcari = cmbcari;
    }

    public String getCmbsort() {
        return cmbsort;
    }

    public void setCmbsort(String cmbsort) {
        this.cmbsort = cmbsort;
    }

    public String getIdsupplier() {
        return idsupplier;
    }

    public void setIdsupplier(String idsupplier) {
        this.idsupplier = idsupplier;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getPropinsi() {
        return propinsi;
    }

    public void setPropinsi(String propinsi) {
        this.propinsi = propinsi;
    }

    public String getKodePost() {
        return kodePost;
    }

    public void setKodePost(String kodePost) {
        this.kodePost = kodePost;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getNomorRek() {
        return nomorRek;
    }

    public void setNomorRek(String nomorRek) {
        this.nomorRek = nomorRek;
    }

    public String getAtasNama() {
        return atasNama;
    }

    public void setAtasNama(String atasNama) {
        this.atasNama = atasNama;
    }

    public String getKontakPerson() {
        return kontakPerson;
    }

    public void setKontakPerson(String kontakPerson) {
        this.kontakPerson = kontakPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public supplierListener getListener() {
        return listener;
    }

    public void setListener(supplierListener listener) {
        this.listener = listener;
    }
    
    public void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }
    
    public void fireOnInsert(SupplierDTO supllier){
        if(listener!=null){
            listener.onInsert(supllier);
        }
    }
    
    public void fireOnUpdate(SupplierDTO SupplierDTO){
        if(listener!=null){
            listener.onUpdate(SupplierDTO);
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
    
    public void resetSupplier(){
        setCari("");
        setCmbcari(cmbcari);
        setCmbsort(cmbsort);        
        setIdsupplier("");
        setNama("");
        setAlamat("");
        setKodePost("");
        setKota("");
        setPropinsi("");
        setTelepon("");
        setFax("");
        setBank("");
        setNomorRek("");
        setAtasNama("");
        setKontakPerson("");
        setEmail("");
        setNote("");
    }
    
    public void resetTambahSupplier(){        
        setIdsupplier("");
        setNama("");
        setAlamat("");
        setKodePost("");
        setKota("");
        setPropinsi("");
        setTelepon("");
        setFax("");
        setBank("");
        setNomorRek("");
        setAtasNama("");
        setKontakPerson("");
        setEmail("");
        setNote("");
    }
    
    public void insertSupplier() {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setIdsupplier(idsupplier);
        supplierDTO.setNama(nama);
        supplierDTO.setAlamat(alamat);
        supplierDTO.setKota(kota);
        supplierDTO.setPropinsi(propinsi);
        supplierDTO.setKodePost(kodePost);
        supplierDTO.setTelepon(telepon);
        supplierDTO.setFax(fax);
        supplierDTO.setBank(bank);
        supplierDTO.setNomorRek(nomorRek);
        supplierDTO.setAtasNama(atasNama);
        supplierDTO.setKontakPerson(kontakPerson);
        supplierDTO.setEmail(email);
        supplierDTO.setNote(note);
        supplierDAO.insert(supplierDTO);
        fireOnInsert(supplierDTO);
    }
    
    public void deleteSupplier() {
        supplierDAO.deleteByString(idsupplier);
        fireOnDelete();
    }
    
    public void findByField(String kataKunci, String berdasarkan) {
        List list = new ArrayList();
        switch(berdasarkan){
            case "idsupplier" : list = supplierDAO.searchById(kataKunci);break;
            case "nama" : list = supplierDAO.searchByNama(kataKunci);break;
            case "alamat" : list = supplierDAO.searchByAlamat(kataKunci);break;
            case "kota" : list = supplierDAO.searchByKota(kataKunci);break;
            case "propinsi" : list = supplierDAO.searchByPropinsi(kataKunci);break;
            case "kodePost" : list = supplierDAO.searchByKodePost(kataKunci);break;
            case "telepon" : list = supplierDAO.searchByTelepon(kataKunci);break;
            case "fax" : list = supplierDAO.searchByFax(kataKunci);break;
            case "bank" : list = supplierDAO.searchByBank(kataKunci);break;
            case "nomorRek" : list = supplierDAO.searchByNomorRek(kataKunci);break;
            case "atasNama" : list = supplierDAO.searchByAtasNama(kataKunci);break;
            case "kontakPerson" : list = supplierDAO.searchByKontakPerson(kataKunci);break;
            case "email" : list = supplierDAO.searchByEmail(kataKunci);break;
            case "note" : list = supplierDAO.searchByNote(kataKunci);break;
            default:
        }

        fireOnSearch(list);
    }

    public void sortByField(String berdasarkan) {
        List list = new ArrayList();
        switch(berdasarkan){
            case "idsupplier" : list = supplierDAO.sortById();break;
            case "nama" : list = supplierDAO.sortByNama();break;
            case "alamat" : list = supplierDAO.sortByAlamat();break;
            case "kota" : list = supplierDAO.sortByKota();break;
            case "propinsi" : list = supplierDAO.sortByPropinsi();break;
            case "kodePost" : list = supplierDAO.sortByKodePost();break;
            case "telepon" : list = supplierDAO.sortByTelepon();break;
            case "fax" : list = supplierDAO.sortByFax();break;
            case "bank" : list = supplierDAO.sortByBank();break;
            case "nomorRek" : list = supplierDAO.sortByNomorRek();break;
            case "atasNama" : list = supplierDAO.sortByAtasNama();break;
            case "kontakPerson" : list = supplierDAO.sortByKontakPerson();break;
            case "email" : list = supplierDAO.sortByEmail();break;
            case "note" : list = supplierDAO.sortByNote();break;
            default:
        }

        fireOnSearch(list);
    }
}

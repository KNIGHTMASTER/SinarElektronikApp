/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.supplier.model;

import com.wissensalt.sinarelektronik.masterdata.supplier.entity.SupplierDTO;
import com.wissensalt.sinarelektronik.masterdata.supplier.database.supplierDatabase;
import com.wissensalt.sinarelektronik.masterdata.supplier.model.event.supplierListener;
import com.wissensalt.sinarelektronik.dao.SupplierDAO;
import java.sql.SQLException;
import java.util.List;
import com.wissensalt.sinarelektronik.masterdata.supplier.error.supplierException;

/**
 *
 * @author Fauzi
 */
public class SupplierModel {
    private String cari, cmbcari, cmbsort;
    
    private String idsupplier, nama, alamat, kota, propinsi, kodePost, 
            telepon, fax, bank, nomorRek, atasNama, kontakPerson, email, note;

    public SupplierModel() {
    }
        
    
    supplierListener listener;

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
    
    public void insertSupplier() throws SQLException, supplierException{
        SupplierDAO dao = supplierDatabase.getSupplierDao();
        SupplierDTO SupplierDTO = new SupplierDTO();
        SupplierDTO.setIdsupplier(idsupplier);
        SupplierDTO.setNama(nama);
        SupplierDTO.setAlamat(alamat);
        SupplierDTO.setKota(kota);
        SupplierDTO.setPropinsi(propinsi);
        SupplierDTO.setKodePost(kodePost);
        SupplierDTO.setTelepon(telepon);
        SupplierDTO.setFax(fax);
        SupplierDTO.setBank(bank);
        SupplierDTO.setNomorRek(nomorRek);
        SupplierDTO.setAtasNama(atasNama);
        SupplierDTO.setKontakPerson(kontakPerson);
        SupplierDTO.setEmail(email);
        SupplierDTO.setNote(note);
        dao.insertSupplier(SupplierDTO);
        fireOnInsert(SupplierDTO);
    }
    
    public void updateSupplier() throws SQLException, supplierException{
        SupplierDAO dao = supplierDatabase.getSupplierDao();
        SupplierDTO SupplierDTO = new SupplierDTO();
        SupplierDTO.setIdsupplier(idsupplier);
        SupplierDTO.setNama(nama);
        SupplierDTO.setAlamat(alamat);
        SupplierDTO.setKota(kota);
        SupplierDTO.setPropinsi(propinsi);
        SupplierDTO.setKodePost(kodePost);
        SupplierDTO.setTelepon(telepon);
        SupplierDTO.setFax(fax);
        SupplierDTO.setBank(bank);
        SupplierDTO.setNomorRek(nomorRek);
        SupplierDTO.setAtasNama(atasNama);
        SupplierDTO.setKontakPerson(kontakPerson);
        SupplierDTO.setEmail(email);
        SupplierDTO.setNote(note);
        dao.updateSupplier(SupplierDTO);
        fireOnUpdate(SupplierDTO);
    }
    
    public void deleteSupplier() throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        dao.deleteSupplier(idsupplier);
        fireOnDelete();
    }
    
    public void searchById(String kataKunci) throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.searchById(kataKunci);
        fireOnSearch(list);
    }

     public void searchByNama(String kataKunci) throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.searchByNama(kataKunci);
        fireOnSearch(list);
    }
     
    public void searchByAlamat(String kataKunci) throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.searchByAlamat(kataKunci);
        fireOnSearch(list);
    }
    
    public void searchByKota(String kataKunci) throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.searchByKota(kataKunci);
        fireOnSearch(list);
    }
    
    public void searchByPropinsi(String kataKunci) throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.searchByPropinsi(kataKunci);
        fireOnSearch(list);
    }
    
    public void searchByKodePost(String kataKunci) throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.searchByKodePost(kataKunci);
        fireOnSearch(list);
    }
    
    public void searchByTelepon(String kataKunci) throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.searchByTelepon(kataKunci);
        fireOnSearch(list);
    }
    
    public void searchByFax(String kataKunci) throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.searchByFax(kataKunci);
        fireOnSearch(list);
    }
    
    public void searchByBank(String kataKunci) throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.searchByBank(kataKunci);
        fireOnSearch(list);
    }
    
    public void searchByNomorRek(String kataKunci) throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.searchByNomorRek(kataKunci);
        fireOnSearch(list);
    }
    
    public void searchByAtasNama(String kataKunci) throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.searchByAtasNama(kataKunci);
        fireOnSearch(list);
    }
    
    public void searchByKontakPerson(String kataKunci) throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.searchByKontakPerson(kataKunci);
        fireOnSearch(list);
    }
    
    public void searchByEmail(String kataKunci) throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.searchByEmail(kataKunci);
        fireOnSearch(list);
    }
    
    public void searchByNote(String kataKunci) throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.searchByNote(kataKunci);
        fireOnSearch(list);
    }
    
    public void sortById() throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.sortById(idsupplier);
        fireOnSearch(list);
    }

     public void sortByNama() throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.sortByNama(nama);
        fireOnSearch(list);
    }
     
    public void sortByAlamat() throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.sortByAlamat(alamat);
        fireOnSearch(list);
    }
    
    public void sortByKota() throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.sortByKota(kota);
        fireOnSearch(list);
    }
    
    public void sortByPropinsi() throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.sortByPropinsi(propinsi);
        fireOnSearch(list);
    }
    
    public void sortByKodePost() throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.sortByKodePost(kodePost);
        fireOnSearch(list);
    }
    
    public void sortByTelepon() throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.sortByTelepon(telepon);
        fireOnSearch(list);
    }
    
    public void sortByFax() throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.sortByFax(fax);
        fireOnSearch(list);
    }
    
    public void sortByBank() throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.sortByBank(bank);
        fireOnSearch(list);
    }
    
    public void sortByNomorRek() throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.sortByNomorRek(nomorRek);
        fireOnSearch(list);
    }
    
    public void sortByAtasNama() throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.sortByAtasNama(atasNama);
        fireOnSearch(list);
    }
    
    public void sortByKontakPerson() throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.sortByKontakPerson(kontakPerson);
        fireOnSearch(list);
    }
    
    public void sortByEmail() throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.sortByEmail(email);
        fireOnSearch(list);
    }
    
    public void sortByNote() throws SQLException, supplierException{
        SupplierDAO dao= supplierDatabase.getSupplierDao();
        List<SupplierDTO> list = dao.sortByNote(note);
        fireOnSearch(list);
    }        
}

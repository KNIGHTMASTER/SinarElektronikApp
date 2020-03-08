/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.pelanggan.model;

import Sinarelektronikapp.masterdata.pelanggan.database.pelangganDatabase;
import Sinarelektronikapp.masterdata.pelanggan.entity.pelanggan;
import Sinarelektronikapp.masterdata.pelanggan.error.pelangganException;
import Sinarelektronikapp.masterdata.pelanggan.model.event.pelangganListener;
import Sinarelektronikapp.masterdata.pelanggan.service.pelangganDao;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class pelangganModel {
    private String idpelanggan, nama, alamat, kota, propinsi, kodePost, telepon, fax, kontakPerson, catatan;
    
    private pelangganListener listener;

    public pelangganListener getListener() {
        return listener;
    }

    public void setListener(pelangganListener listener) {
        this.listener = listener;
    }        

    public String getIdpelanggan() {
        return idpelanggan;
    }

    public void setIdpelanggan(String idpelanggan) {
        this.idpelanggan = idpelanggan;
        fireOnChange();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
        fireOnChange();
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
        fireOnChange();
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
        fireOnChange();
    }

    public String getPropinsi() {
        return propinsi;
    }

    public void setPropinsi(String propinsi) {
        this.propinsi = propinsi;
        fireOnChange();
    }

    public String getKodePost() {
        return kodePost;
    }

    public void setKodePost(String kodePost) {
        this.kodePost = kodePost;
        fireOnChange();
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
        fireOnChange();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
        fireOnChange();
    }

    public String getKontakPerson() {
        return kontakPerson;
    }

    public void setKontakPerson(String kontakPerson) {
        this.kontakPerson = kontakPerson;
        fireOnChange();
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
        fireOnChange();
    }
    
    public void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }
    
    public void fireOnInsert(pelanggan pelanggan){
        if(listener!=null){
            listener.onInsert(pelanggan);
        }
    }
    
    public void fireOnUpdate(pelanggan pelanggan){
        if(listener!=null){
            listener.onUpdate(pelanggan);
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
    
    public void resetPelanggan(){
        setAlamat("");
        setCatatan("");
        setFax("");
        setIdpelanggan("");
        setKodePost("");
        setKontakPerson("");
        setKota("");
        setNama("");
        setPropinsi("");
        setTelepon("");
    }
    
    public void insertPelanggan() throws SQLException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        pelanggan pelanggan=new pelanggan();
        pelanggan.setIdpelanggan(idpelanggan);
        pelanggan.setNama(nama);
        pelanggan.setAlamat(alamat);
        pelanggan.setKota(kota);
        pelanggan.setPropinsi(propinsi);
        pelanggan.setKodePost(kodePost);
        pelanggan.setTelepon(telepon);
        pelanggan.setFax(fax);
        pelanggan.setKontakPerson(kontakPerson);
        pelanggan.setCatatan(catatan);
        try {
            dao.insertPelanggan(pelanggan);
        } catch (pelangganException ex) {
            Logger.getLogger(pelangganModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireOnInsert(pelanggan);
    }
    
    public void updatePelanggan() throws SQLException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        pelanggan pelanggan=new pelanggan();
        pelanggan.setIdpelanggan(idpelanggan);
        pelanggan.setNama(nama);
        pelanggan.setAlamat(alamat);
        pelanggan.setKota(kota);
        pelanggan.setPropinsi(propinsi);
        pelanggan.setKodePost(kodePost);
        pelanggan.setTelepon(telepon);
        pelanggan.setFax(fax);
        pelanggan.setKontakPerson(kontakPerson);
        pelanggan.setCatatan(catatan);
        try {
            dao.updatePelanggan(pelanggan);
        } catch (pelangganException ex) {
            Logger.getLogger(pelangganModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireOnUpdate(pelanggan);
    }    
    
    public void deletePelanggan() throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        dao.deletePelanggan(idpelanggan);
        fireOnDelete();
    }
    
    public void getById(String keyword) throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.getById(keyword);
        fireOnSearch(list);
    }
    
    public void getByNama(String keyword) throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.getByNama(keyword);
        fireOnSearch(list);
    }
    
    public void getByAlamat(String keyword) throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.getByALamat(keyword);
        fireOnSearch(list);
    }
    
    public void getByKota(String keyword) throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.getByKota(keyword);
        fireOnSearch(list);
    }    
    
    public void getByPropinsi(String keyword) throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.getByPropinsi(keyword);
        fireOnSearch(list);
    }
    
    public void getByKodePost(String keyword) throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.getByKodePost(keyword);
        fireOnSearch(list);
    }
    
    public void getByTelepon(String keyword) throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.getByTelepon(keyword);
        fireOnSearch(list);
    }
    
    public void getByFax(String keyword) throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.getByFax(keyword);
        fireOnSearch(list);
    }    
    
    public void getByKontakPerson(String keyword) throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.getByKontakPerson(keyword);
        fireOnSearch(list);
    }    
    
    public void getByCatatan(String keyword) throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.getByCatatan(keyword);
        fireOnSearch(list);
    }    
    
  public void sortById() throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.sortById();
        fireOnSort(list);
    }
    
    public void sortByNama() throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.sortByNama();
        fireOnSort(list);
    }
    
    public void sortByAlamat() throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.sortByALamat();
        fireOnSort(list);
    }
    
    public void sortByKota() throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.sortByKota();
        fireOnSort(list);
    }    
    
    public void sortByPropinsi() throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.sortByPropinsi();
        fireOnSort(list);
    }
    
    public void sortByKodePost() throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.sortByKodePost();
        fireOnSort(list);
    }
    
    public void sortByTelepon() throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.sortByTelepon();
        fireOnSort(list);
    }
    
    public void sortByFax() throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.sortByFax();
        fireOnSort(list);
    }    
    
    public void sortByKontakPerson() throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.sortByKontakPerson();
        fireOnSort(list);
    }    
    
    public void sortByCatatan() throws SQLException, pelangganException{
        pelangganDao dao=pelangganDatabase.getPelangganDao();
        List<pelanggan> list=dao.sortByCatatan();
        fireOnSort(list);
    }        
}

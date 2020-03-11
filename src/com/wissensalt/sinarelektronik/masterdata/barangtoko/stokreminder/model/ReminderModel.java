/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.barangtoko.stokreminder.model;

import com.wissensalt.sinarelektronik.masterdata.barangtoko.entity.barang;
import com.wissensalt.sinarelektronik.masterdata.barangtoko.error.BarangException;
import com.wissensalt.sinarelektronik.masterdata.barangtoko.stokreminder.database.ReminderDatabase;
import com.wissensalt.sinarelektronik.masterdata.barangtoko.stokreminder.model.event.ReminderListener;
import com.wissensalt.sinarelektronik.masterdata.barangtoko.stokreminder.service.ReminderDao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public class ReminderModel {
    
    private String cari, cmbcari, cmbsort;

    public ReminderModel() {
    }


    private String idBarang, idBarcode, namaBarang, tipe, merek, satuan, supplier, keterangan, kategori;

    private int stok, stokMinimum, harga;
    

    private ReminderListener listener ;

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
        fireOnChange();
    }
    
    public ReminderListener getListener() {
        return listener;
    }

    public void setListener(ReminderListener listener) {
        this.listener = listener;
    }
    
    

    ////////////////////

        
    public String getCari() {
        return cari;
    }

    public void setCari(String cari) {
        this.cari = cari;
        fireOnChange();
    }

    public String getCmbcari() {
        return cmbcari;
    }

    public void setCmbcari(String cmbcari) {
        this.cmbcari = cmbcari;
        fireOnChange();
    }

    public String getCmbsort() {
        return cmbsort;
    }

    public void setCmbsort(String cmbsort) {
        this.cmbsort = cmbsort;
    }

    ///////////////////////////////

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
        fireOnChange();
    }

    public String getIdBarcode() {
        return idBarcode;
    }

    public void setIdBarcode(String idBarcode) {
        this.idBarcode = idBarcode;
        fireOnChange();
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
        fireOnChange();
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
        fireOnChange();
    }

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
        fireOnChange();
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
        fireOnChange();
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
        fireOnChange();
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
        fireOnChange();
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
        fireOnChange();
    }

    public int getStokMinimum() {
        return stokMinimum;
    }

    public void setStokMinimum(int stokMinimum) {
        this.stokMinimum = stokMinimum;
        fireOnChange();
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
        fireOnChange();
    }

    protected void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }

    protected void fireOnInsert(barang barang){
        if(listener!=null){
            listener.onInsert(barang);
        }
    }

    protected void fireOnUpdate(barang barang){
        if(listener!=null){
            listener.onUpdate(barang);
        }
    }

    protected void fireOnDelete(){
        if(listener!=null){
            listener.onDelete();
        }
    }

    protected void fireOnSearch(List list){
        if(listener!=null){
            listener.onSearch(list);
        }
    }

    protected void fireOnSort(List list){
        if(listener!=null){
            listener.onSort(list);
        }
    }

    public void resetBarang(){
       setCari("");
       setCmbcari(cmbcari);
       setCmbsort(cmbsort);
    }
     

    public void searchBarangbyId(String kataKunci) throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list = dao.getBarangbyId(kataKunci);
        fireOnSearch(list);
    }

     public void searchBarangbyBarcode(String kataKunci) throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.getBarangbyBarcode(kataKunci);
        fireOnSearch(list);
    }

     public void searchBarangbyNama(String kataKunci) throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.getBarangbyNama(kataKunci);
        fireOnSearch(list);
    }

     public void searchBarangbyTipe(String kataKunci) throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.getBarangbyTipe(kataKunci);
        fireOnSearch(list);
    }

    public void searchBarangbyMerek(String kataKunci) throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.getBarangbyMerek(kataKunci);
        fireOnSearch(list);
    }

    public void searchBarangbyHarga(String kataKunci) throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.getBarangbyHarga(kataKunci);
        fireOnSearch(list);
    }

    public void searchBarangbySatuan(String kataKunci) throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.getBarangbySatuan(kataKunci);
        fireOnSearch(list);
    }

    public void searchBarangbyStok(String kataKunci) throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.getBarangbyStok(kataKunci);
        fireOnSearch(list);
    }

    public void searchBarangbyStokMin(String kataKunci) throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.getBarangbyStok_min(kataKunci);
        fireOnSearch(list);
    }

    public void searchBarangbySupplier(String kataKunci) throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.getBarangbySupplier(kataKunci);
        fireOnSearch(list);
    }

    public void searchBarangbyKeterangan(String kataKunci) throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.getBarangbyKeterangan(kataKunci);
        fireOnSort(list);
    }
    public void sortBarangbyId() throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list = dao.sortBarangbyId();
        fireOnSort(list);
    }

     public void sortBarangbyBarcode() throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.sortBarangbyBarcode();
        fireOnSort(list);
    }

     public void sortBarangbyNama() throws SQLException, BarangException{         
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.sortBarangbyNama();
        fireOnSort(list);
    }

     public void sortBarangbyTipe() throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.sortBarangbyTipe();
        fireOnSort(list);
    }

    public void sortBarangbyMerek() throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.sortBarangbyMerek();
        fireOnSort(list);
    }

    public void sortBarangbyHarga() throws SQLException, BarangException{      
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.sortBarangbyHarga();
        fireOnSort(list);
    }

    public void sortBarangbySatuan() throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.sortBarangbySatuan();
        fireOnSort(list);
    }

    public void sortBarangbyStok() throws SQLException, BarangException{        
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.sortBarangbyStok();
        fireOnSort(list);
    }

    public void sortBarangbyStokMin() throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.sortBarangbyStok_min();
        fireOnSort(list);
    }

    public void sortBarangbySupplier() throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.sortBarangbySupplier();
        fireOnSort(list);
    }

    public void sortBarangbyKeterangan() throws SQLException, BarangException{
        ReminderDao dao = ReminderDatabase.getReminderDao();
        List<barang> list =dao.sortBarangbyKeterangan();
        fireOnSort(list);
    }

    
}

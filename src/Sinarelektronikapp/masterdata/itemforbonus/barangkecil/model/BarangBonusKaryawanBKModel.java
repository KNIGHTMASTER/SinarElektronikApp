/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterdata.itemforbonus.barangkecil.model;

import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.database.BarangBonusKaryawanBKDatabase;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.entity.BarangBonusKaryawanBk;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.error.BarangBonusKaryawanBKException;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.model.event.BarangBonusKaryawanBKListener;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.service.BarangBonusKaryawanDao;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class BarangBonusKaryawanBKModel {    

    BarangBonusKaryawanBKListener listener;

    private String kodeBarang, nama, tipe, merek;

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }
    
    
    public BarangBonusKaryawanBKListener getListener() {
        return listener;
    }

    public void setListener(BarangBonusKaryawanBKListener listener) {
        this.listener = listener;
    }
        
    
    protected void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }
    
     protected void fireOnInsert(BarangBonusKaryawanBk tipe){
        if(listener!=null){
            listener.onInsert(tipe);
        }
    }     
    
    protected void fireOnDelete(){
        if(listener!=null){
            listener.onDelete();
        }
    }    
    
    public void resetTipe(){
        setKodeBarang("");
        setMerek("");
        setNama("");
        setTipe("");
    }
    
    public void insertTipe() throws SQLException, BarangBonusKaryawanBKException{
        BarangBonusKaryawanDao dao = BarangBonusKaryawanBKDatabase.getTipeDao();
        BarangBonusKaryawanBk bbkb = new BarangBonusKaryawanBk();
        bbkb.setKodeBarang(kodeBarang);
        bbkb.setNama(nama);
        bbkb.setMerek(merek);
        bbkb.setTipe(tipe);
        dao.insertTipe(bbkb);
        fireOnInsert(bbkb);
    }
    
    
    public void deleteTipe() throws SQLException, BarangBonusKaryawanBKException{
        BarangBonusKaryawanDao dao = BarangBonusKaryawanBKDatabase.getTipeDao();
        JOptionPane.showMessageDialog(null, "kode barangkecil ="+kodeBarang);
        dao.deleteTipe(kodeBarang);
        fireOnDelete();
    }
}

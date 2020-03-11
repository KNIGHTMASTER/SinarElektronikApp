package com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangbesar.model;

import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangbesar.database.BarangBonusKaryawanBBDatabase;
import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangbesar.entity.BarangBonusKaryawanBB;
import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangbesar.error.BarangBonusKaryawanBBException;
import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangbesar.model.event.BarangBonusKaryawanBBListener;
import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangbesar.service.BarangBonusKaryawanBBDao;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class BarangBonusKaryawanBBModel {    

    BarangBonusKaryawanBBListener listener;

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
    
    
    public BarangBonusKaryawanBBListener getListener() {
        return listener;
    }

    public void setListener(BarangBonusKaryawanBBListener listener) {
        this.listener = listener;
    }
        
    
    protected void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }
    
     protected void fireOnInsert(BarangBonusKaryawanBB tipe){
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
    
    public void insertTipe() throws SQLException, BarangBonusKaryawanBBException{
        BarangBonusKaryawanBBDao dao = BarangBonusKaryawanBBDatabase.getTipeDao();
        BarangBonusKaryawanBB bbkb = new BarangBonusKaryawanBB();
        bbkb.setKodeBarang(kodeBarang);
        bbkb.setNama(nama);
        bbkb.setMerek(merek);
        bbkb.setTipe(tipe);
        dao.insertTipe(bbkb);
        fireOnInsert(bbkb);
    }
    
    
    public void deleteTipe() throws SQLException, BarangBonusKaryawanBBException{
        BarangBonusKaryawanBBDao dao = BarangBonusKaryawanBBDatabase.getTipeDao();
        JOptionPane.showMessageDialog(null, "kode barangkecil ="+kodeBarang);
        dao.deleteTipe(kodeBarang);
        fireOnDelete();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterData.pengaturanBarangBonusKaryawan.BarangBesar.model;

import Sinarelektronikapp.masterData.pengaturanBarangBonusKaryawan.BarangBesar.database.BarangBonusKaryawanBBDatabase;
import Sinarelektronikapp.masterData.pengaturanBarangBonusKaryawan.BarangBesar.entity.BarangBonusKaryawanBB;
import Sinarelektronikapp.masterData.pengaturanBarangBonusKaryawan.BarangBesar.error.BarangBonusKaryawanBBException;
import Sinarelektronikapp.masterData.pengaturanBarangBonusKaryawan.BarangBesar.model.event.BarangBonusKaryawanBBListener;
import Sinarelektronikapp.masterData.pengaturanBarangBonusKaryawan.BarangBesar.service.BarangBonusKaryawanBBDao;
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
        JOptionPane.showMessageDialog(null, "kode barang ="+kodeBarang);
        dao.deleteTipe(kodeBarang);
        fireOnDelete();
    }
}

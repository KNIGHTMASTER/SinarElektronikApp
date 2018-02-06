/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.karyawan.model;

import Sinarelektronikapp.masterData.karyawan.Error.KaryawanException;
import Sinarelektronikapp.masterData.karyawan.database.KaryawanDatabase;
import Sinarelektronikapp.masterData.karyawan.entity.Karyawan;
import Sinarelektronikapp.masterData.karyawan.model.event.KaryawanListener;
import Sinarelektronikapp.masterData.karyawan.service.KaryawanDao;
import java.io.File;
import java.sql.Blob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class KaryawanModel {
    
    int id, gaji;
    String nama, tempat_lahir, alamat, telepon, agama, status, tanggal_lahir;
    File foto;  
    Blob gambarHasil;
    KaryawanListener karyawanListener;
    
    public KaryawanModel() {
    }

    public KaryawanListener getKaryawanListener() {
        return karyawanListener;
    }

    public void setKaryawanListener(KaryawanListener karyawanListener) {
        this.karyawanListener = karyawanListener;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGaji() {
        return gaji;
    }

    public void setGaji(int gaji) {
        this.gaji = gaji;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public Blob getGambarHasil() {
        return gambarHasil;
    }

    public void setGambarHasil(Blob gambarHasil) {
        this.gambarHasil = gambarHasil;
    }
    
    public void fireOnChange(){
        if(karyawanListener!=null){
            karyawanListener.onChange(this);
        }
    }
    
    public void fireOnInsert(Karyawan karyawan){
        if(karyawanListener!=null){
            karyawanListener.onInsert(karyawan);
        }
    }
    
    public void fireOnUpdate(Karyawan karyawan){
        if(karyawanListener!=null){
            karyawanListener.onUpdate(karyawan);
        }
    }
    
    public void fireOnDelete(){
        if(karyawanListener!=null){
            karyawanListener.onDelete();
        }
    }
    
    public void insertKaryawan(){
        KaryawanDao dao = KaryawanDatabase.getKaryawanDao();
        Karyawan karyawan = new Karyawan();
        karyawan.setId(id);
        karyawan.setNama(nama);
        karyawan.setTanggal_lahir(tanggal_lahir);
        karyawan.setTempat_lahir(tempat_lahir);
        karyawan.setAlamat(alamat);
        karyawan.setTelepon(telepon);        
        karyawan.setAgama(agama);
        karyawan.setStatus(status);
        karyawan.setGaji(gaji);
        karyawan.setFoto(foto);
        try {
            dao.insertKaryawan(karyawan);
        } catch (KaryawanException ex) {
            JOptionPane.showMessageDialog(null, "error karena = "+ex);
            Logger.getLogger(KaryawanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fireOnInsert(karyawan);
    }
    
    public void updateKaryawan(){
        KaryawanDao dao = KaryawanDatabase.getKaryawanDao();
        Karyawan karyawan = new Karyawan();
        karyawan.setId(id);
        karyawan.setTanggal_lahir(tanggal_lahir);
        karyawan.setTempat_lahir(tempat_lahir);
        karyawan.setAlamat(alamat);
        karyawan.setTelepon(telepon);
        karyawan.setAgama(agama);
        karyawan.setStatus(status);
        karyawan.setGaji(gaji);
        karyawan.setFoto(foto);
        try {
            dao.updateKaryawan(karyawan);
        } catch (KaryawanException ex) {
            Logger.getLogger(KaryawanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fireOnUpdate(karyawan);
    }
    
    public void deleteKaryawan(){
        KaryawanDao dao = KaryawanDatabase.getKaryawanDao();
        try {
            dao.deleteKaryawan(id);
        } catch (KaryawanException ex) {
            Logger.getLogger(KaryawanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void resetKaryawan(){
        setId(0);
        setAgama("");
        setAlamat("");
        setFoto(null);
        setGaji(0);
        setGambarHasil(null);
        setNama("");
        setStatus("");
        setTanggal_lahir("");
        setTelepon("");
        setTempat_lahir("");
    }
}

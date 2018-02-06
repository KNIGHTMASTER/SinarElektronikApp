/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterData.pengaturanBarangBonusKaryawan.BarangBesar.controller;

import Sinarelektronikapp.masterData.pengaturanBarangBonusKaryawan.BarangBesar.error.BarangBonusKaryawanBBException;
import Sinarelektronikapp.masterData.pengaturanBarangBonusKaryawan.BarangBesar.model.BarangBonusKaryawanBBModel;
import Sinarelektronikapp.masterData.pengaturanBarangBonusKaryawan.BarangBesar.view.BarangBonusKaryawanBBView;
import Sinarelektronikapp.masterData.pengaturanBarangBonusKaryawan.BarangBesar.view.JIFBarangBonusKaryawanBBView;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class BarangBonusKaryawanBBController {
    private BarangBonusKaryawanBBModel model;

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
    
    
    public BarangBonusKaryawanBBController(BarangBonusKaryawanBBModel model) {
        this.model = model;
    }

    public BarangBonusKaryawanBBModel getModel() {
        return model;
    }

    public void setModel(BarangBonusKaryawanBBModel model) {
        this.model = model;
    }

   
    public void resetTipe(JIFBarangBonusKaryawanBBView view){
        model.resetTipe();
    }
    
    public void insertTipe(JIFBarangBonusKaryawanBBView view){
        String kodeBarang = view.getTxtKodeBarang().getText();
        String namaBarang = view.getTxtNamaBarang().getText();        
        String tipeBarang = view.getTxtTipeBarang().getText();
        String merekBarang = view.getTxtMerekBarang().getText();
        if(kodeBarang.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Kode Barang Masih Kosong");
            return;
        }else if(namaBarang.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Nama Barang Masih Kosong");
            return;
        }else if(tipeBarang.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Tipe Barang Masih Kosong");
            return;
        }else if(merekBarang.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Merek Barang Masih Kosong");
            return;
        }else{
            model.setKodeBarang(kodeBarang);
            model.setNama(namaBarang);
            model.setMerek(merekBarang);
            model.setTipe(tipeBarang);
            try {
                try {
                    model.insertTipe();
                } catch (BarangBonusKaryawanBBException ex) {
                    Logger.getLogger(BarangBonusKaryawanBBController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Insert tipe gagal");
            }
        }    
    }
    
    public void deleteTipe(JIFBarangBonusKaryawanBBView view){
        if(view.getTabelTipe().getSelectedRowCount()==0){
            JOptionPane.showMessageDialog(view, "pilih baris yang akan dihapus");
            return;
        }
        if(JOptionPane.showConfirmDialog(view, "Anda yakin akan menghapus ? ") == JOptionPane.YES_OPTION){
            kodeBarang = view.getTabelTipe().getValueAt(view.getTabelTipe().getSelectedRow(), 0).toString();
            model.setKodeBarang(kodeBarang);
            try {
                try {
                    model.deleteTipe();
                } catch (BarangBonusKaryawanBBException ex) {
                    Logger.getLogger(BarangBonusKaryawanBBController.class.getName()).log(Level.SEVERE, null, ex);
                }
                model.resetTipe();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi kesalahan dengan pesan = "+ex);
            } 
        }else{
            
        }
    }
}

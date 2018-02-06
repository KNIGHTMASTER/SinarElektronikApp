/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.namabarang.controller;

import Sinarelektronikapp.masterData.namabarang.error.NamaBarangException;
import Sinarelektronikapp.masterData.namabarang.model.NamaBarangModel;
import Sinarelektronikapp.masterData.namabarang.view.NamaBarangView;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class NamaBarangController {
    private NamaBarangModel model;

    public NamaBarangController() {
    }

    public NamaBarangController(NamaBarangModel model) {
        this.model = model;
    }

    public NamaBarangModel getModel() {
        return model;
    }

    public void setModel(NamaBarangModel model) {
        this.model = model;
    }


    public void resetNamaBarang(NamaBarangView view){
        model.resetNamaBarang();
    }
    
    public void insertNamaBarang(NamaBarangView view){
        String id = view.getTxtId().getText();
        String namaBarang = view.getTxtNamaBarang().getText();
        if(namaBarang.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Nama Barang Masih Kosong");
        }else{
            model.setId(Integer.valueOf(id));
            model.setNamaBarang(namaBarang);
            try {
                model.insertNamaBarang();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Insert NamaBarang gagal");
            } catch (NamaBarangException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi exception pada NamaBarang exception dengan pesan "+ex);
            }
        }
    }
    
    public void updateNamaBarang(NamaBarangView view){
        if(view.getTabelNamaBarang().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "Pilih data terlebih dahulu");
            return;
        }        
        String id = view.getTxtId().getText();
        String namaBarang = view.getTxtNamaBarang().getText();        
        
        if(namaBarang.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Nama barang Masih Kosong");
        }else{
            model.setId(Integer.valueOf(id));
            model.setNamaBarang(namaBarang);
            try {
                model.updateNamaBarang();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Update Nama Barang gagal");
            } catch (NamaBarangException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi exception pada barang exception dengan pesan "+ex);
            }
        }        
    }
    public void deletenamabarang(NamaBarangView view){        
        if(view.getTabelNamaBarang().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "pilih baris yang akan dihapus");
            return;
        }
        if(JOptionPane.showConfirmDialog(view, "Anda yakin akan menghapus ? ") == JOptionPane.YES_OPTION){
            String id = view.getTxtId().getText();
            model.setId(Integer.valueOf(id));
            try {
                model.deleteNamaBarang();
                model.resetNamaBarang();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi kesalahan dengan pesan = "+ex);
            } catch (NamaBarangException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi kesalahan barang exception dengan pesan = "+ex);
            }                            
        }else{
            
        }
    }
 
}

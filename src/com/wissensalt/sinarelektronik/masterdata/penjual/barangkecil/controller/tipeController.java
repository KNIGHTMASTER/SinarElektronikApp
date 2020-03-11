/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wissensalt.sinarelektronik.masterdata.penjual.barangkecil.controller;

import com.wissensalt.sinarelektronik.masterdata.penjual.barangkecil.error.TipeException;
import com.wissensalt.sinarelektronik.masterdata.penjual.barangkecil.model.tipeModel;
import com.wissensalt.sinarelektronik.masterdata.penjual.barangkecil.view.JIFPenjualBKView;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class tipeController {
    private tipeModel model;

    public tipeController(tipeModel model) {
        this.model = model;
    }

    public tipeModel getModel() {
        return model;
    }

    public void setModel(tipeModel model) {
        this.model = model;
    }
    
    public void resetTipe(JIFPenjualBKView view){
        model.resetTipe();
    }
    
    public void insertTipe(JIFPenjualBKView view){
        String idTipe = view.getTxtId().getText();
        String namaTipe = view.getTxtTipe().getText();        
        String insentif = view.getTxtPersentaseInsentif().getText();
        if(idTipe.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Id Tipe Masih Kosong");
            return;
        }else if(namaTipe.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Nama Tipe Masih Kosong");
            return;
        }else if(insentif.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Insentif Masih Kosong");
            return;
        }else{
            model.setIdtipe(idTipe);
            model.setNamaTipe(namaTipe);
            model.setInsentif(Integer.parseInt(insentif));
            try {
                model.insertTipe();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Insert tipe gagal");
            } catch (TipeException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi exception pada barangkecil exception dengan pesan "+ex);
            }
        }
    }
    
    public void updateTipe(JIFPenjualBKView view){
        if(view.getTabelTipe().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "Pilih data terlebih dahulu");
            return;
        }        
        String idTipe = view.getTxtId().getText();
        String namaTipe = view.getTxtTipe().getText();        
        String insentif = view.getTxtPersentaseInsentif().getText();
        
        if(idTipe.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Id Masih Kosong");
            return;
        }else if(namaTipe.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Nama  Masih Kosong");
            return;
        }else if(insentif.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Insentig Masih Kosong");
            return;
        }else{
            model.setIdtipe(idTipe);
            model.setNamaTipe(namaTipe);
            model.setInsentif(Integer.parseInt(insentif));
            try {
                model.updateTipe();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Update tipe gagal");
            } catch (TipeException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi exception pada barangkecil exception dengan pesan "+ex);
            }
        }        
    }
    public void deleteTipe(JIFPenjualBKView view){
        if(view.getTabelTipe().getSelectedRowCount()==0){
            JOptionPane.showMessageDialog(view, "pilih baris yang akan dihapus");
            return;
        }
        if(JOptionPane.showConfirmDialog(view, "Anda yakin akan menghapus ? ") == JOptionPane.YES_OPTION){
            String id = view.getTxtId().getText();
            model.setIdtipe(id);
            try {
                model.deleteTipe();
                model.resetTipe();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi kesalahan dengan pesan = "+ex);
            } catch (TipeException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi kesalahan barangkecil exception dengan pesan = "+ex);
            }                            
        }else{
            
        }
    }
}

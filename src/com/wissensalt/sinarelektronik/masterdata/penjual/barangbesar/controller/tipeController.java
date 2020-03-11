/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wissensalt.sinarelektronik.masterdata.penjual.barangbesar.controller;

import com.wissensalt.sinarelektronik.masterdata.penjual.barangbesar.model.tipeModel;
import com.wissensalt.sinarelektronik.masterdata.penjual.barangbesar.view.JIFPenjualBarangBesarView;
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
    
    public void resetTipe(JIFPenjualBarangBesarView view){
        model.resetTipe();
    }
    
    public void insertTipe(JIFPenjualBarangBesarView view){
        String idTipe = view.getTxtId().getText();
        String namaTipe = view.getTxtTipe().getText();        
        int insentif = Integer.parseInt(view.getTxtPersentaseInsentif().getText());
        if(idTipe.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Id Tipe Masih Kosong");
        }else if(namaTipe.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Nama Tipe Masih Kosong");
        }else if(insentif == 0){
            JOptionPane.showMessageDialog(view, "Insentif Masih Kosong");
        }else{
            model.setIdtipe(idTipe);
            model.setNamaTipe(namaTipe);
            model.setInsentif(insentif);
            model.insertTipe();
        }
    }
    
    public void updateTipe(JIFPenjualBarangBesarView view){
        if(view.getTabelTipe().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "Pilih data terlebih dahulu");
            return;
        }        
        String idTipe = view.getTxtId().getText();
        String namaTipe = view.getTxtTipe().getText();        
        int insentif = Integer.parseInt(view.getTxtPersentaseInsentif().getText());
        
        if(idTipe.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Id Tipe Masih Kosong");
        }else if(namaTipe.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Nama Tipe Masih Kosong");
        }else if(insentif == 0){
            JOptionPane.showMessageDialog(view, "Insentif Masih Kosong");
        }else{
            model.setIdtipe(idTipe);
            model.setNamaTipe(namaTipe);
            model.setInsentif(insentif);
            model.updateTipe();
        }
    }
    public void deleteTipe(JIFPenjualBarangBesarView view){
        if(view.getTabelTipe().getSelectedRowCount()==0){
            JOptionPane.showMessageDialog(view, "pilih baris yang akan dihapus");
            return;
        }
        if(JOptionPane.showConfirmDialog(view, "Anda yakin akan menghapus ? ") == JOptionPane.YES_OPTION){
            String id = view.getTxtId().getText();
            model.setIdtipe(id);
            model.deleteTipe();
            model.resetTipe();
        }else{
            
        }
    }
}

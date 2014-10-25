/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterData.tipe.controller;

import Sinarelektronikapp.masterData.tipe.error.TipeException;
import Sinarelektronikapp.masterData.tipe.model.tipeModel;
import Sinarelektronikapp.masterData.tipe.view.TipeView;
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
    
    public void resetTipe(TipeView view){
        model.resetTipe();
    }
    
    public void insertTipe(TipeView view){
        String idTipe = view.getTxtId().getText();
        String namaTipe = view.getTxtTipe().getText();        
        if(idTipe.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Id Tipe Masih Kosong");
        }else if(namaTipe.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Nama Tipe Masih Kosong");
        }else{
            model.setIdtipe(idTipe);
            model.setNamaTipe(namaTipe);
            try {
                model.insertTipe();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Insert tipe gagal");
            } catch (TipeException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi exception pada barang exception dengan pesan "+ex);
            }
        }
    }
    
    public void updateTipe(TipeView view){
        if(view.getTabelTipe().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "Pilih data terlebih dahulu");
            return;
        }        
        String idTipe = view.getTxtId().getText();
        String namaTipe = view.getTxtTipe().getText();        
        
        if(idTipe.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Id Tipe Masih Kosong");
        }else if(namaTipe.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Nama Tipe Masih Kosong");
        }else{
            model.setIdtipe(idTipe);
            model.setNamaTipe(namaTipe);
            try {
                model.updateTipe();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Update tipe gagal");
            } catch (TipeException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi exception pada barang exception dengan pesan "+ex);
            }
        }        
    }
    public void deleteTipe(TipeView view){
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
                JOptionPane.showMessageDialog(view, "Terjadi kesalahan barang exception dengan pesan = "+ex);
            }                            
        }else{
            
        }
    }
}

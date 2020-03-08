/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterdata.merek.controller;

import Sinarelektronikapp.masterdata.merek.error.merekException;
import Sinarelektronikapp.masterdata.merek.model.merekModel;
import Sinarelektronikapp.masterdata.merek.view.MerekView2;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class MerekController {
    private merekModel model;

    public MerekController(merekModel model) {
        this.model = model;
    }

    public MerekController() {
        
    }

    public merekModel getModel() {
        return model;
    }

    public void setModel(merekModel model) {
        this.model = model;
    }


    public void resetMerek(MerekView2 view){
        model.resetMerek();
    }
    
    public void insertMerek(MerekView2 view){
        String idMerek = view.getTxtId().getText();
        String namaMerek = view.getTxtMerek().getText();
        if(idMerek.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Id Merek Masih Kosong");
        }else if(namaMerek.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Nama Merek Masih Kosong");
        }else{
            model.setIdmerek(idMerek);
            model.setNamamerek(namaMerek);
            try {
                model.insertMerek();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Insert merek gagal");
            } catch (merekException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi exception pada merek exception dengan pesan "+ex);
            }
        }
    }
    
    public void updateMerek(MerekView2 view){
        if(view.getTabelMerek().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "Pilih data terlebih dahulu");
            return;
        }        
        String idMerek = view.getTxtId().getText();
        String namaMerek = view.getTxtMerek().getText();        
        
        if(idMerek.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Id Merek Masih Kosong");
        }else if(namaMerek.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Nama Merek Masih Kosong");
        }else{
            model.setIdmerek(idMerek);
            model.setNamamerek(namaMerek);
            try {
                model.updateMerek();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Update Merek gagal");
            } catch (merekException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi exception pada barangkecil exception dengan pesan "+ex);
            }
        }        
    }
    public void deleteMerek(MerekView2 view){        
        if(view.getTabelMerek().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "pilih baris yang akan dihapus");
            return;
        }
        if(JOptionPane.showConfirmDialog(view, "Anda yakin akan menghapus ? ") == JOptionPane.YES_OPTION){
            String id = view.getTxtId().getText();
            model.setIdmerek(id);
            try {
                model.deleteMerek();
                model.resetMerek();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi kesalahan dengan pesan = "+ex);
            } catch (merekException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi kesalahan barangkecil exception dengan pesan = "+ex);
            }                            
        }else{
            
        }
    }
}

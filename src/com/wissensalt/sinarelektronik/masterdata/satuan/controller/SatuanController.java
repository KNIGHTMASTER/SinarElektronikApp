package com.wissensalt.sinarelektronik.masterdata.satuan.controller;

import com.wissensalt.sinarelektronik.masterdata.satuan.model.SatuanModel;
import com.wissensalt.sinarelektronik.masterdata.satuan.view.SatuanView;

import javax.swing.*;

/**
 *
 * @author Fauzi
 */
public class SatuanController {
    private SatuanModel model;

    public SatuanController(SatuanModel model) {
        this.model = model;
    }

    public SatuanController() {
    }
    
    public SatuanModel getModel() {
        return model;
    }

    public void setModel(SatuanModel model) {
        this.model = model;
    }
    
    public void resetsatuan(SatuanView view){
        model.resetSatuan();
    }
    
    public void insertsatuan(SatuanView view){
        String idsatuan = view.getTxtId().getText();
        String namasatuan = view.getTxtSatuan().getText();        
        if(idsatuan.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Id satuan Masih Kosong");
        }else if(namasatuan.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Nama satuan Masih Kosong");
        }else{
            model.setIdsatuan(idsatuan);
            model.setNamaSatuan(namasatuan);
            model.insertSatuan();
        }
    }
    
    public void updatesatuan(SatuanView view){
        if(view.getTabelSatuan().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "Pilih data terlebih dahulu");
        }        
        String idsatuan = view.getTxtId().getText();
        String namasatuan = view.getTxtSatuan().getText();
        
        if(idsatuan.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Id satuan Masih Kosong");
        }else if(namasatuan.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Nama satuan Masih Kosong");
        }else{
            model.setIdsatuan(idsatuan);
            model.setNamaSatuan(namasatuan);
            model.updateSatuan();
        }        
    }

    public void deletesatuan(SatuanView view){
        if(view.getTabelSatuan().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "pilih baris yang akan dihapus");
            return;
        }
        if(JOptionPane.showConfirmDialog(view, "Anda yakin akan menghapus ? ") == JOptionPane.YES_OPTION){
            String id = view.getTxtId().getText();
            JOptionPane.showMessageDialog(view, "id satuan = "+id);
            model.setIdsatuan(id);
            model.deleteSatuan();
            model.resetSatuan();
        }
    }
}

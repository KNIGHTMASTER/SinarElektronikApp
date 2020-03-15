package com.wissensalt.sinarelektronik.masterdata.tipe.controller;

import com.wissensalt.sinarelektronik.dao.TipeDAO;
import com.wissensalt.sinarelektronik.dao.impl.TipeDAOImpl;
import com.wissensalt.sinarelektronik.masterdata.tipe.entity.TipeDTO;
import com.wissensalt.sinarelektronik.masterdata.tipe.model.TipeModel;
import com.wissensalt.sinarelektronik.masterdata.tipe.view.TipeView;

import javax.swing.*;

/**
 *
 * @author Fauzi
 */
public class TipeController {
    private TipeModel model;
    private final TipeDAO tipeDAO;

    public TipeController() {
        tipeDAO = new TipeDAOImpl();
    }

    public TipeModel getModel() {
        return model;
    }

    public void setModel(TipeModel model) {
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
            TipeDTO tipeDTO = new TipeDTO();
            tipeDTO.setIdTipe(idTipe);
            tipeDTO.setNamaTipe(namaTipe);
            tipeDAO.insert(tipeDTO);
            model.insertTipe(tipeDTO);
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
            TipeDTO tipeDTO = new TipeDTO();
            tipeDTO.setIdTipe(idTipe);
            tipeDTO.setNamaTipe(namaTipe);
            tipeDAO.update(tipeDTO);
            model.updateTipe(tipeDTO);
        }        
    }
    public void deleteTipe(TipeView view){
        if(view.getTabelTipe().getSelectedRowCount()==0){
            JOptionPane.showMessageDialog(view, "pilih baris yang akan dihapus");
            return;
        }
        if(JOptionPane.showConfirmDialog(view, "Anda yakin akan menghapus ? ") == JOptionPane.YES_OPTION){
            String id = view.getTxtId().getText();
            tipeDAO.deleteByString(id);
            model.setIdTipe(id);
            model.deleteTipe();
            model.resetTipe();
        }
    }
}

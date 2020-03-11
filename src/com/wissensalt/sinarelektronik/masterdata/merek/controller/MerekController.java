package com.wissensalt.sinarelektronik.masterdata.merek.controller;

import com.wissensalt.sinarelektronik.dao.MerekDAO;
import com.wissensalt.sinarelektronik.dao.impl.MerekDAOImpl;
import com.wissensalt.sinarelektronik.dto.MerekDTO;
import com.wissensalt.sinarelektronik.model.MerekModel;
import com.wissensalt.sinarelektronik.masterdata.merek.view.MerekView;

import javax.swing.*;

/**
 *
 * @author Fauzi
 */
public class MerekController {
    private MerekModel model;
    private final MerekDAO merekDAO;

    public MerekController() {
        merekDAO = new MerekDAOImpl();
    }

    public MerekModel getModel() {
        return model;
    }

    public void setModel(MerekModel model) {
        this.model = model;
    }

    public void resetMerek(MerekView view){
        model.resetMerek();
    }
    
    public void insertMerek(MerekView view){
        String idMerek = view.getTxtId().getText();
        String namaMerek = view.getTxtMerek().getText();
        if(idMerek.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Id Merek Masih Kosong");
        }else if(namaMerek.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Nama Merek Masih Kosong");
        }else{
            MerekDTO merekDTO = new MerekDTO();
            merekDTO.setIdMerek(idMerek);
            merekDTO.setNamaMerek(namaMerek);
            merekDAO.insert(merekDTO);
            model.insertMerek(merekDTO);
        }
    }
    
    public void updateMerek(MerekView view){
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
            MerekDTO merekDTO = new MerekDTO();
            merekDTO.setIdMerek(idMerek);
            merekDTO.setNamaMerek(namaMerek);
            merekDAO.update(merekDTO);
            model.updateMerek(merekDTO);
        }        
    }

    public void deleteMerek(MerekView view){
        if(view.getTabelMerek().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "pilih baris yang akan dihapus");
        }

        if(JOptionPane.showConfirmDialog(view, "Anda yakin akan menghapus ? ") == JOptionPane.YES_OPTION){
            String id = view.getTxtId().getText();
            merekDAO.deleteByString(id);
            model.resetMerek();
        }
    }
}

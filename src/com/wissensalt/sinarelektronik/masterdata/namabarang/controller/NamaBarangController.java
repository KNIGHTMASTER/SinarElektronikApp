package com.wissensalt.sinarelektronik.masterdata.namabarang.controller;

import com.wissensalt.sinarelektronik.dao.NamaBarangDAO;
import com.wissensalt.sinarelektronik.dao.impl.NamaBarangDAOImpl;
import com.wissensalt.sinarelektronik.masterdata.namabarang.entity.NamaBarangDTO;
import com.wissensalt.sinarelektronik.masterdata.namabarang.model.NamaBarangModel;
import com.wissensalt.sinarelektronik.masterdata.namabarang.view.NamaBarangView;

import javax.swing.*;

/**
 *
 * @author Fauzi
 */
public class NamaBarangController {
    private NamaBarangModel namaBarangModel;
    private final NamaBarangDAO namaBarangDAO;

    public NamaBarangController() {
        namaBarangDAO = new NamaBarangDAOImpl();
    }

    public NamaBarangModel getModel() {
        return namaBarangModel;
    }

    public void setModel(NamaBarangModel model) {
        this.namaBarangModel = model;
    }


    public void resetNamaBarang(NamaBarangView view){
        namaBarangModel.resetNamaBarang();
    }
    
    public void insertNamaBarang(NamaBarangView view){
        String id = view.getTxtId().getText();
        String namaBarang = view.getTxtNamaBarang().getText();
        if(namaBarang.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Nama Barang Masih Kosong");
        }else{
            NamaBarangDTO namaBarangDTO = new NamaBarangDTO();
            namaBarangDTO.setId(Integer.valueOf(id));
            namaBarangDTO.setNamabarang(namaBarang);
            namaBarangDAO.insert(namaBarangDTO);
            namaBarangModel.insertNamaBarang(namaBarangDTO);
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
            JOptionPane.showMessageDialog(view, "Nama barangkecil Masih Kosong");
        }else{
            NamaBarangDTO namaBarangDTO = new NamaBarangDTO();
            namaBarangDTO.setId(Integer.valueOf(id));
            namaBarangDTO.setNamabarang(namaBarang);
            namaBarangDAO.update(namaBarangDTO);
            namaBarangModel.updateNamaBarang(namaBarangDTO);
        }        
    }

    public void deletenamabarang(NamaBarangView view){        
        if(view.getTabelNamaBarang().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "pilih baris yang akan dihapus");
            return;
        }
        if(JOptionPane.showConfirmDialog(view, "Anda yakin akan menghapus ? ") == JOptionPane.YES_OPTION){
            String id = view.getTxtId().getText();
            namaBarangDAO.deleteByString(id);
            namaBarangModel.deleteNamaBarang();
            namaBarangModel.resetNamaBarang();
        }
    }
 
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterdata.satuan.controller;

import Sinarelektronikapp.masterdata.satuan.error.SatuanException;
import Sinarelektronikapp.masterdata.satuan.model.satuanModel;
import Sinarelektronikapp.masterdata.satuan.view.SatuanView;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class satuanController {
    private satuanModel model;

    public satuanController(satuanModel model) {
        this.model = model;
    }

    public satuanController() {
    }
    
    public satuanModel getModel() {
        return model;
    }

    public void setModel(satuanModel model) {
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
            model.setNamasatuan(namasatuan);
            try {
                model.insertSatuan();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Insert satuan gagal");
            } catch (SatuanException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi exception pada barangkecil exception dengan pesan "+ex);
            }
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
            model.setNamasatuan(namasatuan);
            try {
                model.updateSatuan();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Update satuan gagal");
            } catch (SatuanException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi exception pada barangkecil exception dengan pesan "+ex);
            }
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
            try {
                model.deleteSatuan();
                model.resetSatuan();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi kesalahan dengan pesan = "+ex);
            } catch (SatuanException ex) {
                JOptionPane.showMessageDialog(view, "Terjadi kesalahan barangkecil exception dengan pesan = "+ex);
            }                            
        }else{
            
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.transfer.barangbesar.controller;

import Sinarelektronikapp.transfer.barangbesar.error.penjualanException;
import Sinarelektronikapp.transfer.barangbesar.model.PenjualanModel;
import Sinarelektronikapp.transfer.barangbesar.view.JIFTransferBB;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class penjualanController {
    private PenjualanModel model;

    public void setModel(PenjualanModel model) {
        this.model = model;
    }
    
    public void insertPenjualan(JIFTransferBB kasirview){
        String kode=kasirview.getTxtKodeItem().getText();
        String nama=kasirview.getTxtDetailItem().getText();
        String jml=kasirview.getTxtJumlah().getText();   
        if(kode.trim().equals("")){
            JOptionPane.showMessageDialog(kasirview, "Kode barangkecil masih kosong");
        }else if(nama.trim().equals("")){
            JOptionPane.showMessageDialog(kasirview, "Nama barangkecil masih kosong");
        }else if(jml.trim().equals("")){
            JOptionPane.showMessageDialog(kasirview, "jumlah barangkecil masih kosong");
        }else{
            model.setKode(kode);
            model.setNama(nama);
            model.setJml(Integer.parseInt(jml));
            try {
                model.insertPenjualan();
            } catch (penjualanException ex) {
                Logger.getLogger(penjualanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    

    public void deletePenjualan(JIFTransferBB kasirview){        
        
        if(kasirview.getTabelTransaksi().getSelectedRowCount()==0){
            JOptionPane.showMessageDialog(kasirview, "pilih data yang akan dihapus");
            return;
        }
        
        if(JOptionPane.showConfirmDialog(kasirview, "yakin akan dihapus ?")==JOptionPane.YES_OPTION){
            String no=kasirview.getTabelTransaksi().getValueAt(kasirview.getTabelTransaksi().getSelectedRow(), 0).toString();
            model.setNo(Integer.parseInt(no));
            try {
                model.deletePenjualan();
            } catch (penjualanException ex) {
                Logger.getLogger(penjualanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    
    
    public void truncatePenjualan(JIFTransferBB kasirview){
            if(JOptionPane.showConfirmDialog(kasirview, "yakin transaksi akan digagalkan ?")==JOptionPane.YES_OPTION){
            try {
                model.TruncatePenjualan();
            } catch (penjualanException ex) {
                Logger.getLogger(penjualanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    
    public void tambahPenjualan(JIFTransferBB kasirview){
            try {
                model.TruncatePenjualan();
            } catch (penjualanException ex) {
                Logger.getLogger(penjualanController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }        
}

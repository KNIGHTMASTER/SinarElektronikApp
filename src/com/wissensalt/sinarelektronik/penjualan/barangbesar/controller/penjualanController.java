/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.penjualan.barangbesar.controller;

import com.wissensalt.sinarelektronik.penjualan.barangbesar.error.penjualanException;
import com.wissensalt.sinarelektronik.penjualan.barangbesar.model.PenjualanModel;
import com.wissensalt.sinarelektronik.penjualan.barangbesar.view.JIFKasirView2;
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
    
    public void insertPenjualan(JIFKasirView2 kasirview){
        String kode=kasirview.getTxtKodeItem().getText();
        String nama=kasirview.getTxtDetailItem().getText();
        String jml=kasirview.getTxtJumlah().getText();
        String harga=kasirview.getTxtHarga().getText();
        String potongan=kasirview.getTxtPotongan().getText();
        String tambahan = kasirview.getTxtTambahan().getText();
        String penjual = kasirview.getLblPenjual().getText();
        String asalBarang = kasirview.getLblStokBarang().getText();
        int profit = kasirview.getProfit();
        int total=kasirview.getSubTotal();        
        int bonuslangsung = kasirview.getBonuslangsung();
        int bonuskumulatif = kasirview.getBonuskumulatif();
        
        if(kode.trim().equals("")){
            JOptionPane.showMessageDialog(kasirview, "Kode barangkecil masih kosong");
        }else if(nama.trim().equals("")){
            JOptionPane.showMessageDialog(kasirview, "Nama barangkecil masih kosong");
        }else if(jml.trim().equals("")){
            JOptionPane.showMessageDialog(kasirview, "jumlah barangkecil masih kosong");
        }else if(harga.trim().equals("")){
            JOptionPane.showMessageDialog(kasirview, "harga barangkecil masih kosong");
        }else{
            model.setKode(kode);
            model.setNama(nama);
            model.setJml(Integer.parseInt(jml));
            model.setHarga(Integer.parseInt(harga));
            model.setPotongan(Integer.parseInt(potongan));
            model.setTambahan(Integer.parseInt(tambahan));
            model.setTotal(total);            
            model.setProfit(profit);
            model.setPenjual(penjual);
            model.setBonuslangsung(bonuslangsung);
            model.setBonuskumulatif(bonuskumulatif);
            model.setAsalBarang(asalBarang);
            try {
                model.insertPenjualan();
            } catch (penjualanException ex) {
                Logger.getLogger(penjualanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    

    public void deletePenjualan(JIFKasirView2 kasirview){        
        
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
    
    
    public void truncatePenjualan(JIFKasirView2 kasirview){
            if(JOptionPane.showConfirmDialog(kasirview, "yakin transaksi akan digagalkan ?")==JOptionPane.YES_OPTION){
            try {
                model.TruncatePenjualan();
            } catch (penjualanException ex) {
                Logger.getLogger(penjualanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    
    public void tambahPenjualan(JIFKasirView2 kasirview){
            try {
                model.TruncatePenjualan();
            } catch (penjualanException ex) {
                Logger.getLogger(penjualanController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }        
}

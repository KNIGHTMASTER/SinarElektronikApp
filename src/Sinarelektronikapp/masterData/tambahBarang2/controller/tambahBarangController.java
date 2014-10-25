/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.tambahBarang2.controller;

import Sinarelektronikapp.masterData.tambahBarang2.error.TambahBarangException;
import Sinarelektronikapp.masterData.tambahBarang2.model.tambahBarangModel;
import Sinarelektronikapp.masterData.tambahBarang2.view.TambahBarangView2;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class tambahBarangController {
    private tambahBarangModel model;


    public void setModel(tambahBarangModel model) {
        this.model = model;
    }

    public tambahBarangController() {
    }
    
    public void resetTambahBarang(){        
        model.resetTambahBarang();
    }
    public void insertBarang(TambahBarangView2 view){
        String idbarang = view.getTxtIdBarang().getText();
        String idbarcode = view.getTxtIdBarcode().getText();
        String namaBarang = view.getCmbNamaBarang().getSelectedItem().toString();
        String tipe = view.getCmbTipe().getSelectedItem().toString();
        String merek = view.getCmbMerek().getSelectedItem().toString();
        String hargaModal = view.getTxtHargaModal().getText();
        String grosir = view.getTxtHargaGrosir().getText();
        String  eceran = view.getTxtHargaEceran().getText();
        int stok = view.getJsStok().getValue();
        int stokMinimum = view.getJsstokMin().getValue();
        String supplier = view.getCmbSupplier().getSelectedItem().toString();
        String keterangan = view.getTxtKet().getText();        
        File gambar = view.getGambar();
        String garansi = "";
        int hari =  0, bulan = 0, tahun = 0;
        hari = view.getJsHari().getValue();
        bulan = view.getJsBulan().getValue() * 30;
        tahun = view.getJsTahun().getValue() * 360;
        
        int lamaGaransi = (hari+bulan+tahun);        
        if(view.getRbTidak().isSelected()){
            garansi = "tidak";
        }else if(view.getRbYa().isSelected()){
            garansi = "ya";
        }
        if(idbarang.trim().equals("")){
            JOptionPane.showMessageDialog(null, "Id Barang masih kosong");
        }
        if(namaBarang.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Nama barang masih kosong");
        }else if(String.valueOf(eceran).trim().equals("")){
            JOptionPane.showMessageDialog(view, "Harga eceran masih kosong");
        }else if(String.valueOf(grosir).trim().equals("")){
            JOptionPane.showMessageDialog(view, "Harga grosir masih kosong");
        }else if(String.valueOf(stok).trim().equals("")){
            JOptionPane.showMessageDialog(view, "Stok masih kosong");
        }else if(String.valueOf(stokMinimum).equals("")){
            JOptionPane.showMessageDialog(view, "Stok minimum masih kosong");
        }else if(!hargaModal.matches("[0-9]*")){
            JOptionPane.showMessageDialog(view, "Harga modal harus angka");
            view.getTxtHargaModal().setText("0");
            view.getTxtHargaModal().requestFocus();
        }else if(!eceran.matches("[0-9]*")){
            JOptionPane.showMessageDialog(view, "Eceran harus angka");
            view.getTxtHargaEceran().setText("0");
            view.getTxtHargaEceran().requestFocus();
        }else if(!grosir.matches("[0-9]*")){
            JOptionPane.showMessageDialog(view, "Grosir harus angka");
            view.getTxtHargaGrosir().setText("0");
            view.getTxtHargaGrosir().requestFocus();
        }
        else{
            model.setIdBarang(idbarang);
            model.setIdBarcode(idbarcode);
            model.setNamaBarang(namaBarang);
            model.setTipe(tipe);
            model.setMerek(merek);
            model.setHargaModal(Integer.parseInt(hargaModal));            
            model.setEceran(Integer.parseInt(eceran));
            model.setGrosir(Integer.parseInt(grosir));
            model.setStok(stok);
            model.setStokMinimum(stokMinimum);
            model.setSupplier(supplier);
            model.setKeterangan(keterangan);
            model.setGambar(gambar);
            model.setGaransi(garansi);
            model.setLamaGaransi(lamaGaransi);            
            try {
                model.insertBarang();
                JOptionPane.showMessageDialog(null, "Insert Barang Besar Berhasil");
            } catch (SQLException ex) {
                Logger.getLogger(tambahBarangController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TambahBarangException ex) {
                Logger.getLogger(tambahBarangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
}

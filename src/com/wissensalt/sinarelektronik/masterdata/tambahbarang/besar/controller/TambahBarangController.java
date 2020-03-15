package com.wissensalt.sinarelektronik.masterdata.tambahbarang.besar.controller;

import com.wissensalt.sinarelektronik.masterdata.tambahbarang.besar.model.TambahBarangBesarModel;
import com.wissensalt.sinarelektronik.masterdata.tambahbarang.besar.view.TambahBarangView2;

import javax.swing.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class TambahBarangController {
    private TambahBarangBesarModel tambahBarangBesarModel;

    public void setTambahBarangBesarModel(TambahBarangBesarModel tambahBarangBesarModel) {
        this.tambahBarangBesarModel = tambahBarangBesarModel;
    }

    public void resetTambahBarang(){        
        tambahBarangBesarModel.resetTambahBarang();
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
            JOptionPane.showMessageDialog(view, "Nama barangkecil masih kosong");
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
            tambahBarangBesarModel.setIdBarang(idbarang);
            tambahBarangBesarModel.setIdBarcode(idbarcode);
            tambahBarangBesarModel.setNamaBarang(namaBarang);
            tambahBarangBesarModel.setTipe(tipe);
            tambahBarangBesarModel.setMerek(merek);
            tambahBarangBesarModel.setHargaModal(Integer.parseInt(hargaModal));
            tambahBarangBesarModel.setEceran(Integer.parseInt(eceran));
            tambahBarangBesarModel.setGrosir(Integer.parseInt(grosir));
            tambahBarangBesarModel.setStok(stok);
            tambahBarangBesarModel.setStokMinimum(stokMinimum);
            tambahBarangBesarModel.setSupplier(supplier);
            tambahBarangBesarModel.setKeterangan(keterangan);
            if (gambar != null) {
                tambahBarangBesarModel.setGambar(gambar);
            }
            tambahBarangBesarModel.setGaransi(garansi);
            tambahBarangBesarModel.setLamaGaransi(lamaGaransi);

            try {
                tambahBarangBesarModel.insertBarang();
                JOptionPane.showMessageDialog(null, "Insert Barang Besar Berhasil");
            } catch (Exception ex) {
                Logger.getLogger(TambahBarangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
}

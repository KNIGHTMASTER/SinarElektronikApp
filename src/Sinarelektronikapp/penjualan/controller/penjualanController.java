/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.penjualan.controller;

import Sinarelektronikapp.penjualan.error.penjualanException;
import Sinarelektronikapp.penjualan.model.PenjualanModel;
import Sinarelektronikapp.penjualan.model.TabelModelPenjualan;
import Sinarelektronikapp.penjualan.view.JIFKasirView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Fauzi
 */
public class penjualanController {
    private PenjualanModel model;

    public void setModel(PenjualanModel model) {
        this.model = model;
    }
    
    public void insertPenjualan(JIFKasirView JIFKasirView){
        String kode=JIFKasirView.getTxtKodeItem().getText();
        String nama=JIFKasirView.getTxtDetailItem().getText();
        String jml=JIFKasirView.getTxtJumlah().getText();
        String harga=JIFKasirView.getTxtHarga().getText();
        String potongan=JIFKasirView.getTxtPotongan().getText();
        String tambahan = JIFKasirView.getTxtTambahan().getText();
        int profit = JIFKasirView.getProfit();
        int total=JIFKasirView.getSubTotal();        
        
        if(kode.trim().equals("")){
            JOptionPane.showMessageDialog(JIFKasirView, "Kode barang masih kosong");            
        }else if(nama.trim().equals("")){
            JOptionPane.showMessageDialog(JIFKasirView, "Nama barang masih kosong");            
        }else if(jml.trim().equals("")){
            JOptionPane.showMessageDialog(JIFKasirView, "jumlah barang masih kosong");
        }else if(harga.trim().equals("")){
            JOptionPane.showMessageDialog(JIFKasirView, "harga barang masih kosong");
        }else{
            model.setKode(kode);
            model.setNama(nama);
            model.setJml(Integer.parseInt(jml));
            model.setHarga(Integer.parseInt(harga));
            model.setPotongan(Integer.parseInt(potongan));
            model.setTambahan(Integer.parseInt(tambahan));
            model.setTotal(total);            
            model.setProfit(profit);
            try {
                model.insertPenjualan();
            } catch (penjualanException ex) {
                Logger.getLogger(penjualanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deletePenjualan(JIFKasirView JIFKasirView){        
        
        if(JIFKasirView.getTabelTransaksi().getSelectedRowCount()==0){
            JOptionPane.showMessageDialog(JIFKasirView, "pilih data yang akan dihapus");
            return;
        }
        
        if(JOptionPane.showConfirmDialog(JIFKasirView, "yakin akan dihapus ?")==JOptionPane.YES_OPTION){
            String no=JIFKasirView.getTabelTransaksi().getValueAt(JIFKasirView.getTabelTransaksi().getSelectedRow(), 0).toString();
            model.setNo(Integer.parseInt(no));
            try {
                model.deletePenjualan();
            } catch (penjualanException ex) {
                Logger.getLogger(penjualanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void truncatePenjualan(JIFKasirView JIFKasirView){
            if(JOptionPane.showConfirmDialog(JIFKasirView, "yakin transaksi akan digagalkan ?")==JOptionPane.YES_OPTION){
            try {
                model.TruncatePenjualan();
            } catch (penjualanException ex) {
                Logger.getLogger(penjualanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void tambahPenjualan(JIFKasirView JIFKasirView){
            try {
                model.TruncatePenjualan();
            } catch (penjualanException ex) {
                Logger.getLogger(penjualanController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
    
    public int getJumlahBarangBeli(){
        int hasil = 0;
        try {
            hasil = model.getJumlahBarangBeli();
        } catch (penjualanException ex) {
            Logger.getLogger(penjualanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
    
    public int getStokBarang(String kode){
        int hasil = 0;        
        hasil = model.getStokBarang(kode);
        return hasil;
    }    
    
    public void createTempTable(){
        try {
            model.createTempTable();
        } catch (penjualanException ex) {
            Logger.getLogger(penjualanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getJumlahProsesKasir(String text) {
        int hasil = 0;        
        hasil = model.getJumlahProsesKasir(text);
        return hasil;
    }
    
    public int getSubTotalBarangBeli(){
        int hasil = 0;        
        hasil = model.getSubTotalBarangBeli();
        return hasil;        
    }

    public int getAllTotal() {
        int hasil = 0;        
        hasil = model.getAllTotal();
        return hasil;        
    }

    public void updateStokSetelahPembayaran() {
        model.updateStokSetelahPembayaran();
    }

    SimpleDateFormat formatTanggal2=new SimpleDateFormat("yyyy-MM-dd");
    Date date=new Date();        
    
    public int getProfitPerBarisTransaksi(){
        int hasil = 0;        
        hasil = model.getProfitPerBarisTransaksi();
        return hasil;                
    }
    
    public void isiDataTransaksi(JIFKasirView JIFKasirView) {
        String user = JIFKasirView.getTxtUser().getText();
        String tanggal = formatTanggal2.format(date);
        String jam = JIFKasirView.getTxtJam().getText();
        int subTotalBarangBeli = getSubTotalBarangBeli();
        String pembayaran = JIFKasirView.getTxtPembayaran().getText();
        int sisa = JIFKasirView.getSisa();
        int profitPerBarisTransaksi = getProfitPerBarisTransaksi();;
        model.isiDataTransaksi(user, tanggal, jam, subTotalBarangBeli, pembayaran, sisa, profitPerBarisTransaksi);
    }

    public void isiDataDetailTransaksi(JIFKasirView aThis) {
        TabelModelPenjualan modelPenjualan = aThis.getModelPenjualan();        
        JTable tabelTransaksi = aThis.getTabelTransaksi();
        tabelTransaksi.selectAll();
        String tanggal = formatTanggal2.format(date);
        int row=tabelTransaksi.getSelectedRowCount();
        String noTransaksi = aThis.getTxtNoTransaksi().getText();
        String user = aThis.getTxtUser().getText();
        String jam = aThis.getTxtJam().getText();
        model.isiDataDetailTransaksi(modelPenjualan, tabelTransaksi, row, noTransaksi, user, jam, tanggal);
    }
}

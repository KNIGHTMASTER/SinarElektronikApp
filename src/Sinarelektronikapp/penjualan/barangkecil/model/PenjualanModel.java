/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.penjualan.barangkecil.model;

import Sinarelektronikapp.penjualan.barangkecil.database.penjualanDatabase;
import Sinarelektronikapp.penjualan.barangkecil.entity.ProsesKasir;
import Sinarelektronikapp.penjualan.barangkecil.error.penjualanException;
import Sinarelektronikapp.penjualan.barangkecil.model.event.penjualanListener;
import Sinarelektronikapp.penjualan.barangkecil.service.penjualanDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author Fauzi
 */
public class PenjualanModel {
    private String kode, nama, penjual;
       
    private int jml, harga, potongan, tambahan, total, no, profit, bonuslangsung, bonuskumulatif;
    
    private penjualanListener listener;

    public int getBonuslangsung() {
        return bonuslangsung;
    }

    public void setBonuslangsung(int bonuslangsung) {
        this.bonuslangsung = bonuslangsung;
    }

    public int getBonuskumulatif() {
        return bonuskumulatif;
    }

    public void setBonuskumulatif(int bonuskumulatif) {
        this.bonuskumulatif = bonuskumulatif;
    }
    
    public String getPenjual() {
        return penjual;
    }

    public void setPenjual(String penjual) {
        this.penjual = penjual;
    }

    
    public int getTambahan() {
        return tambahan;
    }

    public void setTambahan(int tambahan) {
        this.tambahan = tambahan;
        fireOnChange();
    }
    
    public penjualanListener getListener() {
        return listener;
    }

    public void setListener(penjualanListener listener) {
        this.listener = listener;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
        fireOnChange();
    }
            

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
        fireOnChange();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
        fireOnChange();
    }

    public int getJml() {
        return jml;
    }

    public void setJml(int jml) {
        this.jml = jml;
        fireOnChange();
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
        fireOnChange();
    }

    public int getPotongan() {
        return potongan;
    }

    public void setPotongan(int potongan) {
        this.potongan = potongan;
        fireOnChange();
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        fireOnChange();
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
        fireOnChange();
    }
       
    protected void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }
    
    protected void fireOnInsert(ProsesKasir prosesKasir){
        if(listener!=null){
            listener.onInsert(prosesKasir);
        }
    }
    
    protected void fireOnDelete(){
        if(listener!=null){
            listener.onDelete();
        }
    }
    
    protected void fireOnTruncate(){
        if(listener!=null){
            listener.onTruncate();
        }
    }
    
    public void insertPenjualan() throws penjualanException{
        
        penjualanDao dao=penjualanDatabase.getPenjualanDao();
        
        ProsesKasir prosesKasir=new ProsesKasir();
        
        prosesKasir.setKode(kode);
        prosesKasir.setNama(nama);
        prosesKasir.setJml(jml);
        prosesKasir.setHarga(harga);
        prosesKasir.setPotongan(potongan);
        prosesKasir.setTambahan(tambahan);
        prosesKasir.setTotal(total);        
        prosesKasir.setProfit(profit);
        prosesKasir.setPenjual(penjual);
        prosesKasir.setBonuslangsung(bonuslangsung);
        prosesKasir.setBonuskumulatif(bonuskumulatif);
        
        dao.insertPenjualan(prosesKasir);
        
        //start using file
        
        //end using file
        fireOnInsert(prosesKasir);
    }
    
    public void deletePenjualan() throws penjualanException{
        penjualanDao dao=penjualanDatabase.getPenjualanDao();
        dao.deletePenjualan(no);
        fireOnDelete();
    }
    
    public void TruncatePenjualan() throws penjualanException{
        penjualanDao dao=penjualanDatabase.getPenjualanDao();
        dao.truncatePenjualan();
        fireOnTruncate();
    }
    
    public int getJumlahBarangBeli() throws penjualanException{
        penjualanDao dao = penjualanDatabase.getPenjualanDao();
        int hasil = dao.getJumlahBarangBeli();
        return hasil;
    }
    
    public void createTempTable()throws penjualanException{
        penjualanDao dao=penjualanDatabase.getPenjualanDao();
        dao.createTempTable();   
    }

    public int getStokBarang(String kode) {
        penjualanDao dao=penjualanDatabase.getPenjualanDao();
        int hasil = 0;
        try {
            hasil = dao.getStokBarang(kode);
        } catch (penjualanException ex) {
            Logger.getLogger(PenjualanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }

    public int getJumlahProsesKasir(String text) {
        penjualanDao dao=penjualanDatabase.getPenjualanDao();
        int hasil = 0;
        try {
            hasil = dao.getJumlahProsesKasir(text);
        } catch (penjualanException ex) {
            Logger.getLogger(PenjualanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }

    public int getSubTotalBarangBeli() {
        penjualanDao dao=penjualanDatabase.getPenjualanDao();
        int hasil = 0;
        try {
            hasil = dao.getSubTotalBarangBeli();
        } catch (penjualanException ex) {
            Logger.getLogger(PenjualanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }

    public int getAllTotal() {
        penjualanDao dao=penjualanDatabase.getPenjualanDao();
        int hasil = 0;
        try {
            hasil = dao.getAllTotal();
        } catch (penjualanException ex) {
            Logger.getLogger(PenjualanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }

    public void updateStokSetelahPembayaran() {
        penjualanDao dao=penjualanDatabase.getPenjualanDao();
        try {
            dao.updateStokSetelahPembayaran();
        } catch (penjualanException ex) {
            Logger.getLogger(PenjualanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getProfitPerBarisTransaksi() {
        penjualanDao dao=penjualanDatabase.getPenjualanDao();
        int hasil = 0;
        try {
            hasil = dao.getProfitPerBarisTransaksi();
        } catch (penjualanException ex) {
            Logger.getLogger(PenjualanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }

    public void isiDataTransaksi(String user, String tanggal, String jam, int subTotalBarangBeli, String pembayaran, int sisa, int profitPerBarisTransaksi, String penjual, double bonusLangsung, double bonusKumulatif) {
        penjualanDao dao=penjualanDatabase.getPenjualanDao();
        try {
            dao.isiDataTransaksi(user, tanggal, jam, subTotalBarangBeli, pembayaran, sisa, profitPerBarisTransaksi, penjual, bonusLangsung, bonusKumulatif);
        } catch (penjualanException ex) {
            Logger.getLogger(PenjualanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void isiDataDetailTransaksi(TabelModelPenjualan modelPenjualan, JTable tabelTransaksi, int row, String noTransaksi, String user, String jam, String tanggal, String penjual) {
        penjualanDao dao=penjualanDatabase.getPenjualanDao();
        try {
            dao.isiDataDetailTransaksi(modelPenjualan, tabelTransaksi, row, noTransaksi, user, jam, tanggal, penjual);
        } catch (penjualanException ex) {
            Logger.getLogger(PenjualanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getTotalBonusLangsung(){
        penjualanDao dao = penjualanDatabase.getPenjualanDao();
        int langsung = dao.getTotalBonusLangsung();        
        return langsung;
    }
    
    public int getTotalBonusKumulatif(){
        penjualanDao dao = penjualanDatabase.getPenjualanDao();
        int kumulatif = dao.getTotalBonusKumulatif();
        return kumulatif;        
    }

}

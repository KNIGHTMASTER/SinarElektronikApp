/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.penjualan.barangbesar.model;

import com.wissensalt.sinarelektronik.penjualan.barangbesar.database.penjualanDatabase;
import com.wissensalt.sinarelektronik.penjualan.barangbesar.entity.ProsesKasir;
import com.wissensalt.sinarelektronik.penjualan.barangbesar.error.penjualanException;
import com.wissensalt.sinarelektronik.penjualan.barangbesar.model.event.penjualanListener;
import com.wissensalt.sinarelektronik.penjualan.barangbesar.service.penjualanDao;

/**
 *
 * @author Fauzi
 */
public class PenjualanModel {
    private String kode, nama, penjual, asalBarang;
       
    private int jml, harga, potongan, tambahan, total, no, profit, bonuslangsung, bonuskumulatif;
    
    private penjualanListener listener;

    public String getAsalBarang() {
        return asalBarang;
    }

    public void setAsalBarang(String asalBarang) {
        this.asalBarang = asalBarang;
    }
    
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

    
    public int getTambahan() {
        return tambahan;
    }

    public String getPenjual() {
        return penjual;
    }

    public void setPenjual(String penjual) {
        this.penjual = penjual;
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
        prosesKasir.setAsalBarang(asalBarang);
        
        dao.insertPenjualan(prosesKasir);
        
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
    
}

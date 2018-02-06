/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.ManajemenPenjualan.BarangKecil.Model;

import Sinarelektronikapp.ManajemenPenjualan.BarangKecil.Database.TransaksiPenjualanDatabase;
import Sinarelektronikapp.ManajemenPenjualan.BarangKecil.Entity.TransaksiPenjualan;
import Sinarelektronikapp.ManajemenPenjualan.BarangKecil.Error.TransaksiPenjualanException;
import Sinarelektronikapp.ManajemenPenjualan.BarangKecil.Model.Event.TransaksiPenjualanListener;
import Sinarelektronikapp.ManajemenPenjualan.BarangKecil.Service.TransaksiPenjualanDao;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class TransaksiPenjualanModel {
    int idTransaksi;
    String user;
    Date tanggal;
    String jam;
    int total;
    int pembayaran;
    int sisa;
    int profit;

    private String cari, cmbCari, cmbUrut;

    public String getCari() {
        return cari;
    }

    public void setCari(String cari) {
        this.cari = cari;
        
    }

    public String getCmbCari() {
        return cmbCari;
    }

    public void setCmbCari(String cmbCari) {
        this.cmbCari = cmbCari;
        
    }

    public String getCmbUrut() {
        return cmbUrut;
    }

    public void setCmbUrut(String cmbUrut) {
        this.cmbUrut = cmbUrut;
        
    }
        
    TransaksiPenjualanListener tpl;

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
        
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
        
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
        
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
        
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        
    }

    public int getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(int pembayaran) {
        this.pembayaran = pembayaran;
        
    }

    public int getSisa() {
        return sisa;
    }

    public void setSisa(int sisa) {
        this.sisa = sisa;
        
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
        
    }

    public TransaksiPenjualanListener getTpl() {
        return tpl;
    }

    public void setTpl(TransaksiPenjualanListener tpl) {
        this.tpl = tpl;
        
    }
    
    public void fireonDelete(){
        if(tpl!=null){
            tpl.onDelete();
        }
    }
    
    public void deleteTransaksiPenjualan(){
        TransaksiPenjualanDao dao = TransaksiPenjualanDatabase.getTransaksiPenjualanDao();
        try {
            dao.deleteTransaksiPenjualan(idTransaksi);
        } catch (TransaksiPenjualanException ex) {
            Logger.getLogger(TransaksiPenjualanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireonDelete();
    }

    public void cari(String kataKunci, String berdasarkan) {
        TransaksiPenjualanDao dao = TransaksiPenjualanDatabase.getTransaksiPenjualanDao();
        try {
            List<TransaksiPenjualan> list = dao.getTransaksiPenjualan(kataKunci, berdasarkan);
            JOptionPane.showMessageDialog(null, "oke selesai masuk dari dao");
            fireOnSearch(list);            
            JOptionPane.showMessageDialog(null, "oke model");
        } catch (TransaksiPenjualanException ex) {
            Logger.getLogger(TransaksiPenjualanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sort(String berdasarkan){
        TransaksiPenjualanDao dao = TransaksiPenjualanDatabase.getTransaksiPenjualanDao();       
        try {
            List<TransaksiPenjualan> list = dao.sortTransaksiPenjualan(berdasarkan);
            fireOnSort(list);
        } catch (TransaksiPenjualanException ex) {
            Logger.getLogger(TransaksiPenjualanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fireOnSearch(List list){
        if(tpl!=null){
            tpl.onSearch(list);
        }
    }
    
    public void fireOnSort(List list){
        if(tpl!=null){
            tpl.onSort(list);
        }
    }    
    
    /*public void fireOnChange(){
        if(tpl!=null){
            tpl.onChange(this);
        }
    }*/
}

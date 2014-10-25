/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Model;

import Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Database.TransaksiPenjualanBBDatabase;
import Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Entity.TransaksiPenjualanBB;
import Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Error.TransaksiPenjualanBBException;
import Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Model.Event.TransaksiPenjualanBBListener;
import Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Service.TransaksiPenjualanBBDao;
import Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Service.TransaksiPenjualanBBDaoImpl;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class TransaksiPenjualanBBModel {
    int idtransaksi;
    String user;
    Date tanggal;
    String jam;
    int total;
    int pembayaran;
    int sisa;
    int profit;
    String penjual;
    int bonusLangsung;
    int bonusKumuatif;

    public int getIdtransaksi() {
        return idtransaksi;
    }

    public void setIdtransaksi(int idtransaksi) {
        this.idtransaksi = idtransaksi;
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

    public String getPenjual() {
        return penjual;
    }

    public void setPenjual(String penjual) {
        this.penjual = penjual;
    }

    public int getBonusLangsung() {
        return bonusLangsung;
    }

    public void setBonusLangsung(int bonusLangsung) {
        this.bonusLangsung = bonusLangsung;
    }

    public int getBonusKumuatif() {
        return bonusKumuatif;
    }

    public void setBonusKumuatif(int bonusKumuatif) {
        this.bonusKumuatif = bonusKumuatif;
    }
    
    TransaksiPenjualanBBListener tpbbl;

    public TransaksiPenjualanBBListener getTpbbl() {
        return tpbbl;
    }

    public void setTpbbl(TransaksiPenjualanBBListener tpbbl) {
        this.tpbbl = tpbbl;
    }
        
    public void fireOnDelete(){
        if(tpbbl!=null){
            tpbbl.onDelete();
        }
    }
    
    public void fireOnSearch(List list){
        if(tpbbl!=null){
            tpbbl.onSearch(list);
        }
    }
    
    public void fireOnSort(List list){
        if(tpbbl!=null){
            tpbbl.onSort(list);
        }
    }
    
    public void deleteTransaksiPenjualanBB(){
        TransaksiPenjualanBBDao dao = TransaksiPenjualanBBDatabase.getTransaksiPenjualanBBDao();
        try {
            dao.deleteTransaksi(idtransaksi);
            fireOnDelete();
        } catch (TransaksiPenjualanBBException ex) {
            Logger.getLogger(TransaksiPenjualanBBModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sort(String berdasarkan){
        TransaksiPenjualanBBDao dao = TransaksiPenjualanBBDatabase.getTransaksiPenjualanBBDao();
        try {
            List<TransaksiPenjualanBB> list = dao.sortTransaksi(berdasarkan);
            fireOnSort(list);
        } catch (TransaksiPenjualanBBException ex) {
            Logger.getLogger(TransaksiPenjualanBBModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void search(String kataKunci, String berdasarkan){
        TransaksiPenjualanBBDao dao = TransaksiPenjualanBBDatabase.getTransaksiPenjualanBBDao();
        try {
            List<TransaksiPenjualanBB> list = dao.searchTransaksi(kataKunci, berdasarkan);
            fireOnSearch(list);
        } catch (TransaksiPenjualanBBException ex) {
            Logger.getLogger(TransaksiPenjualanBBModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

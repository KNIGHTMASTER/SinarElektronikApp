/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.penjualan.barangkecil.controller;

import Sinarelektronikapp.config.InternetProtocol;
import Sinarelektronikapp.penjualan.barangkecil.error.penjualanException;
import Sinarelektronikapp.penjualan.barangkecil.model.PenjualanModel;
import Sinarelektronikapp.penjualan.barangkecil.model.TabelModelPenjualan;
import Sinarelektronikapp.penjualan.barangkecil.view.JIFKasirView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    private Connection connection;
    
    InternetProtocol ip1 = new InternetProtocol();    
    
    public void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");    
            try {
                connection = DriverManager.getConnection("jdbc:mysql://"+ip1.getIpServer()+"/sinarelektronik?;", "root", "P@ssw0rd");
            } catch (SQLException ex) {
                Logger.getLogger(penjualanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(penjualanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int getModal(String kode){
        koneksi();
        int modal = 0;
        Statement s = null;
        try{
            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select hargamodal from barang where idbarang = '"+kode+"'");
            if(rs.next()){
                modal = rs.getInt("hargamodal");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam mengambil harga modal karena "+exception);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(penjualanController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return modal;        
    }
    
    public int getSubTotal(JIFKasirView jifkv){
        int harga = Integer.parseInt(jifkv.getTxtHarga().getText());
        int potongan = Integer.parseInt(jifkv.getTxtPotongan().getText())*Integer.parseInt(jifkv.getTxtJumlah().getText());
        int tambahan  = Integer.parseInt(jifkv.getTxtTambahan().getText())*Integer.parseInt(jifkv.getTxtJumlah().getText());
        int subtotal = harga-potongan+tambahan;        
        return subtotal;
    }
    
    public int getHargaModal(JIFKasirView jifkv){
        String grosir = "SELECT hargamodal from barang WHERE idbarang = '"+jifkv.getTxtKodeItem().getText()+"'";
        int hargaModal = 0;
        try{
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(grosir);
            if(rs.next()){
                hargaModal = rs.getInt("hargamodal");
            }
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error dalam mendapatkan biaya ekspedisi karena "+exception);
        }

        return hargaModal;
    }
    
    public int profit = 0;
    
    public int getProfit(JIFKasirView jifkv) {
        profit = getSubTotal(jifkv) - (getHargaModal(jifkv)*Integer.parseInt(jifkv.getTxtJumlah().getText()));
        return profit;
    }
    
    public String [] getBarangBonus(){
        String [] barangBonus = null;
        try{
            koneksi();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("select count(kodebarang) as jumlah from barangbonuskaryawanbk");
            int jumlah = 0;
            if(rs.next()){
                barangBonus = new String[rs.getInt("jumlah")];
                jumlah = rs.getInt("jumlah");
            }
            
            Statement s2 = connection.createStatement();
            ResultSet rs2 = s2.executeQuery("select kodebarang from barangbonuskaryawanbk");
            int a=0;
            while(rs2.next()){
                barangBonus[a] = rs2.getString("kodebarang");
                a++;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error karena");
        }
        return barangBonus;
    }
    
    int bonuslangsung;
    
    int bonuskumulatif;
    
    public int [] getPatokanBonusLangsungBK(){
        koneksi();
        int []hasil=new int[2];
        try{
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("select patokanbk, bonuslangsungbk from setupbonus");
            if(rs.next()){
                hasil[0] = rs.getInt("patokanbk");
                hasil[1] = rs.getInt("bonuslangsungbk");
            }
        }catch(Exception e){
            
        }
        return hasil;
    }
        
    public void insertPenjualan(JIFKasirView JIFKasirView){
        String kode=JIFKasirView.getTxtKodeItem().getText();
        String nama=JIFKasirView.getTxtDetailItem().getText();
        String jml=JIFKasirView.getTxtJumlah().getText();
        String harga=JIFKasirView.getTxtHarga().getText();
        String potongan=JIFKasirView.getTxtPotongan().getText();
        String tambahan = JIFKasirView.getTxtTambahan().getText();
        int [] patokanBonusBK = getPatokanBonusLangsungBK();
        int modal = getModal(kode) * Integer.parseInt(jml);
        int patokan = modal+patokanBonusBK[0];
        int jual = getSubTotal(JIFKasirView);        
        //int profit = JIFKasirView.getProfit();
        int profit = getProfit(JIFKasirView);
        int total=JIFKasirView.getSubTotal();        
        String penjual = JIFKasirView.getCmbPenjual().getSelectedItem().toString();
        String [] barangBonus = getBarangBonus();
        int panjang = barangBonus.length;        
        int a=0;
        int flag=0;
        while(a<panjang){
            if(JIFKasirView.getTxtKodeItem().getText().equals(barangBonus[a])){
                flag=1;
                break;
            }else{
                flag=0;
            }
            a++;
        }
            if(flag==1){
                if(jual == patokan){
                    bonuslangsung=patokanBonusBK[1];
                    bonuskumulatif = 0;
                }else if(jual>patokan){
                    bonuslangsung=patokanBonusBK[1];
                    int tambahanKumulatif = jual - patokan;
                    bonuskumulatif = tambahanKumulatif;
                }else if((jual>=modal) && (jual<patokan)){
                    //masuk kumulatif
                    bonuslangsung=0;
                    bonuskumulatif = profit;
                }
            }else{
                bonuslangsung=0;
                bonuskumulatif = profit;                
            }        
        
        if(kode.trim().equals("")){
            JOptionPane.showMessageDialog(JIFKasirView, "Kode barangkecil masih kosong");
        }else if(nama.trim().equals("")){
            JOptionPane.showMessageDialog(JIFKasirView, "Nama barangkecil masih kosong");
        }else if(jml.trim().equals("")){
            JOptionPane.showMessageDialog(JIFKasirView, "jumlah barangkecil masih kosong");
        }else if(harga.trim().equals("")){
            JOptionPane.showMessageDialog(JIFKasirView, "harga barangkecil masih kosong");
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
        double bonusLangsung = model.getTotalBonusLangsung();
        double bonusKumulatif = model.getTotalBonusKumulatif();
        String penjual = JIFKasirView.getCmbPenjual().getSelectedItem().toString();
        model.isiDataTransaksi(user, tanggal, jam, subTotalBarangBeli, pembayaran, sisa, profitPerBarisTransaksi, penjual, bonusLangsung, bonusKumulatif);
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
        String penjual = aThis.getCmbPenjual().getSelectedItem().toString();
        
        model.isiDataDetailTransaksi(modelPenjualan, tabelTransaksi, row, noTransaksi, user, jam, tanggal, penjual);
    }
}

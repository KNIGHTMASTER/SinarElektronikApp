/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.penjualan.service;

import Sinarelektronikapp.penjualan.entity.ProsesKasir;
import Sinarelektronikapp.penjualan.error.penjualanException;
import Sinarelektronikapp.penjualan.model.TabelModelPenjualan;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Fauzi
 */
public interface penjualanDao {
    
    public void insertPenjualan(ProsesKasir prosesKasir) throws penjualanException;            
    
    public List<ProsesKasir> selectAllPenjualan() throws penjualanException;

    public void deletePenjualan(int no) throws penjualanException;
    
    public void truncatePenjualan()throws penjualanException;
    
    public void createTable() throws penjualanException;
    
    public int getJumlahBarangBeli()throws penjualanException;
    
    public void createTempTable() throws penjualanException;

    public int getStokBarang(String kode) throws penjualanException;

    public int getJumlahProsesKasir(String text)throws penjualanException;

    public int getSubTotalBarangBeli()throws penjualanException;

    public int getAllTotal()throws penjualanException;

    public void updateStokSetelahPembayaran() throws penjualanException;

    public int getProfitPerBarisTransaksi()throws penjualanException;

    public void isiDataTransaksi(String user, String tanggal, String jam, int subTotalBarangBeli, String pembayaran, int sisa, int profitPerBarisTransaksi)throws penjualanException;

    public void isiDataDetailTransaksi(TabelModelPenjualan modelPenjualan, JTable tabelTransaksi, int row, String noTransaksi, String user, String jam, String tanggal)throws penjualanException;
    
}

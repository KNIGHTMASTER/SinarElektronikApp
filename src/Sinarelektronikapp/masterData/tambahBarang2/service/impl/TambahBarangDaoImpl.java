/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.tambahBarang2.service.impl;

import Sinarelektronikapp.masterData.barang2.entity.barang;
import Sinarelektronikapp.masterData.tambahBarang2.error.TambahBarangException;
import Sinarelektronikapp.masterData.tambahBarang2.service.TambahBarangDao;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class TambahBarangDaoImpl implements TambahBarangDao{
    
    private Connection connection;

    public TambahBarangDaoImpl(Connection connection) {
        this.connection = connection;
    }        
    
    final String insertBarang = "INSERT INTO barangbesar (idbarang, idbarcode, namabarang, tipe, merek, modal, grosir, eceran, stok, stok_minimum, supplier, keterangan, gambar, garansi, lamagaransi) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    @Override
    public void insertBarang(barang barang) throws TambahBarangException {
        PreparedStatement statement = null;
        try{
            //connection.setAutoCommit(false);
            statement = connection.prepareStatement(insertBarang);
            statement.setString(1, barang.getIdBarang());
            statement.setString(2, barang.getIdBarcode());
            statement.setString(3, barang.getNamaBarang());
            statement.setString(4, barang.getTipe());
            statement.setString(5, barang.getMerek());
            statement.setInt(6, barang.getModal());
            statement.setInt(7, barang.getGrosir());
            statement.setInt(8, barang.getEceran());
            statement.setInt(9, barang.getStok());
            statement.setInt(10, barang.getStok_min());
            statement.setString(11, barang.getSupplier());
            statement.setString(12, barang.getKeterangan());
            try {
                statement.setBlob(13, new FileInputStream(barang.getGambar()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TambahBarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            statement.setString(14, barang.getGaransi());
            statement.setString(15, String.valueOf(barang.getLamaGaransi()));
            statement.executeUpdate();
            //connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Insert barang gagal karena "+exception, "Peringatan", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {                
            }
            if(statement!=null){
                try{
                    statement.close();
                }catch(SQLException exception){
                    
                }
            }
        }    
    }

        
}

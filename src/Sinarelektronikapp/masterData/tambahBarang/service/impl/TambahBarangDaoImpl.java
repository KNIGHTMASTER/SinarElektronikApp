/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.tambahBarang.service.impl;

import Sinarelektronikapp.masterData.barang.entity.barang;
import Sinarelektronikapp.masterData.tambahBarang.error.TambahBarangException;
import Sinarelektronikapp.masterData.tambahBarang.service.TambahBarangDao;
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
    
    final String insertBarang = "INSERT INTO barang (idbarang, idbarcode, namabarang, tipe, merek, hargamodal, grosir, grosir2, eceran, satuan, stok, stok_minimum, supplier, keterangan, gambar, garansi, lamagaransi, kategori) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
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
            statement.setInt(6, barang.getHargamodal());
            statement.setInt(7, barang.getGrosir());            
            statement.setInt(8, barang.getGrosir2());
            statement.setInt(9, barang.getEceran());
            statement.setString(10, barang.getSatuan());
            statement.setInt(11, barang.getStok());
            statement.setInt(12, barang.getStokMinimum());
            statement.setString(13, barang.getSupplier());
            statement.setString(14, barang.getKeterangan());
            try {
                statement.setBlob(15, new FileInputStream(barang.getGambar()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TambahBarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            statement.setString(16, barang.getGaransi());
            statement.setString(17, String.valueOf(barang.getLamaGaransi()));
            statement.setString(18, barang.getKategori());
            statement.executeUpdate();
            //connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Insert barang gagal karena "+exception);
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

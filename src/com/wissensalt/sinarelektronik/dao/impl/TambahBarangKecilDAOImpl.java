/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.dao.impl;

import com.wissensalt.sinarelektronik.masterdata.barangkecil.entity.BarangKecilDTO;
import com.wissensalt.sinarelektronik.masterdata.tambahbarang.kecil.error.TambahBarangException;
import com.wissensalt.sinarelektronik.dao.TambahBarangKecilDAO;
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
public class TambahBarangKecilDAOImpl implements TambahBarangKecilDAO {
    
    private Connection connection;

    public TambahBarangKecilDAOImpl(Connection connection) {
        this.connection = connection;
    }        
    
    final String insertBarang = "INSERT INTO barang (idbarang, idbarcode, namabarang, tipe, merek, hargamodal, grosir, grosir2, eceran, satuan, stok, stok_minimum, supplier, keterangan, gambar, garansi, lamagaransi, kategori) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    @Override
    public void insertBarang(BarangKecilDTO BarangKecilDTO) throws TambahBarangException {
        PreparedStatement statement = null;
        try{
            //connection.setAutoCommit(false);
            statement = connection.prepareStatement(insertBarang);
            statement.setString(1, BarangKecilDTO.getIdBarang());
            statement.setString(2, BarangKecilDTO.getIdBarcode());
            statement.setString(3, BarangKecilDTO.getNamaBarang());
            statement.setString(4, BarangKecilDTO.getTipe());
            statement.setString(5, BarangKecilDTO.getMerek());
            statement.setInt(6, BarangKecilDTO.getHargamodal());
            statement.setInt(7, BarangKecilDTO.getGrosir());
            statement.setInt(8, BarangKecilDTO.getGrosir2());
            statement.setInt(9, BarangKecilDTO.getEceran());
            statement.setString(10, BarangKecilDTO.getSatuan());
            statement.setInt(11, BarangKecilDTO.getStok());
            statement.setInt(12, BarangKecilDTO.getStokMinimum());
            statement.setString(13, BarangKecilDTO.getSupplier());
            statement.setString(14, BarangKecilDTO.getKeterangan());
            try {
                if (BarangKecilDTO.getGambar() != null) {
                    statement.setBlob(15, new FileInputStream(BarangKecilDTO.getGambar()));
                }else {
                    statement.setNull(15, java.sql.Types.BLOB);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TambahBarangKecilDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            statement.setString(16, BarangKecilDTO.getGaransi());
            statement.setString(17, String.valueOf(BarangKecilDTO.getLamaGaransi()));
            statement.setString(18, BarangKecilDTO.getKategori());
            statement.executeUpdate();
            //connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
            }
            JOptionPane.showMessageDialog(null, "Insert barangkecil gagal karena "+exception);
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

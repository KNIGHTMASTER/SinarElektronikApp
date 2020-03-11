/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.tambahbarang.besar.service.impl;

import com.wissensalt.sinarelektronik.dto.BarangBesarDTO;
import com.wissensalt.sinarelektronik.masterdata.tambahbarang.besar.error.TambahBarangException;
import com.wissensalt.sinarelektronik.masterdata.tambahbarang.besar.service.TambahBarangDao;
import com.mysql.jdbc.Blob;
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
public class TambahBarangDaoImpl implements TambahBarangDao {

    private Connection connection;

    public TambahBarangDaoImpl(Connection connection) {
        this.connection = connection;
    }

    final String insertBarang = "INSERT INTO barangbesar (idbarang, idbarcode, namabarang, tipe, merek, modal, grosir, eceran, stok, stok_minimum, supplier, keterangan, gambar, garansi, lamagaransi) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public void insertBarang(BarangBesarDTO BarangBesarDTO) throws TambahBarangException {
        PreparedStatement statement = null;
        try {
//            connection.setAutoCommit(false);
            statement = connection.prepareStatement(insertBarang);
            statement.setString(1, BarangBesarDTO.getIdBarang());
            statement.setString(2, BarangBesarDTO.getIdBarcode());
            statement.setString(3, BarangBesarDTO.getNamaBarang());
            statement.setString(4, BarangBesarDTO.getTipe());
            statement.setString(5, BarangBesarDTO.getMerek());
            statement.setInt(6, BarangBesarDTO.getModal());
            statement.setInt(7, BarangBesarDTO.getGrosir());
            statement.setInt(8, BarangBesarDTO.getEceran());
            statement.setInt(9, BarangBesarDTO.getStok());
            statement.setInt(10, BarangBesarDTO.getStok_min());
            statement.setString(11, BarangBesarDTO.getSupplier());
            statement.setString(12, BarangBesarDTO.getKeterangan());
            try {
                if (BarangBesarDTO.getGambar() != null) {
                    statement.setBlob(13, new FileInputStream(BarangBesarDTO.getGambar()));
                } else {
                    statement.setNull(13, java.sql.Types.BLOB);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TambahBarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                connection.rollback();
            }
            statement.setString(14, BarangBesarDTO.getGaransi());
            statement.setString(15, String.valueOf(BarangBesarDTO.getLamaGaransi()));
            statement.executeUpdate();
//            connection.commit();
        } catch (Exception exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {

            }
            JOptionPane.showMessageDialog(null, "Insert barangkecil gagal karena " + exception, "Peringatan", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {

                }
            }
        }
    }

}

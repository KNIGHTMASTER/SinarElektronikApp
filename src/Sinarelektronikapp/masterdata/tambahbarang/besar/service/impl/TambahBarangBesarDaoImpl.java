/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.tambahbarang.besar.service.impl;

import Sinarelektronikapp.masterdata.barangbesar.entity.barang;
import Sinarelektronikapp.masterdata.tambahbarang.besar.error.TambahBarangException;
import Sinarelektronikapp.masterdata.tambahbarang.besar.service.TambahBarangBesarDao;
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
public class TambahBarangBesarDaoImpl implements TambahBarangBesarDao {

    private Connection connection;

    public TambahBarangBesarDaoImpl(Connection connection) {
        this.connection = connection;
    }

    final String insertBarang = "INSERT INTO barangbesar (idbarang, idbarcode, namabarang, tipe, merek, modal, grosir, grosir2, eceran, stok, stok_minimum, supplier, keterangan, gambar, garansi, lamagaransi) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public void insertBarang(barang barang) throws TambahBarangException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(insertBarang);
            statement.setString(1, barang.getIdBarang());
            statement.setString(2, barang.getIdBarcode());
            statement.setString(3, barang.getNamaBarang());
            statement.setString(4, barang.getTipe());
            statement.setString(5, barang.getMerek());
            statement.setInt(6, barang.getModal());
            statement.setInt(7, barang.getGrosir());
            statement.setInt(8, barang.getGrosir2());
            statement.setInt(9, barang.getEceran());
            statement.setInt(10, barang.getStok());
            statement.setInt(11, barang.getStok_min());
            statement.setString(12, barang.getSupplier());
            statement.setString(13, barang.getKeterangan());
            try {
                if (barang.getGambar() != null) {
                    statement.setBlob(14, new FileInputStream(barang.getGambar()));
                } else {
                    statement.setNull(14, java.sql.Types.BLOB);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TambahBarangBesarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                connection.rollback();
            }
            statement.setString(15, barang.getGaransi());
            statement.setString(16, String.valueOf(barang.getLamaGaransi()));
            statement.executeUpdate();
            connection.commit();
        } catch (Exception exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {

            }
            JOptionPane.showMessageDialog(null, "Insert barang besar gagal karena " + exception, "Peringatan", JOptionPane.ERROR_MESSAGE);
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

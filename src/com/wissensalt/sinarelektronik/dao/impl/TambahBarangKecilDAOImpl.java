package com.wissensalt.sinarelektronik.dao.impl;

import com.wissensalt.sinarelektronik.dao.ABaseDAO;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.entity.BarangKecilDTO;
import com.wissensalt.sinarelektronik.dao.TambahBarangKecilDAO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fauzi
 */
public class TambahBarangKecilDAOImpl extends ABaseDAO<BarangKecilDTO>implements TambahBarangKecilDAO {
    
    final String INSERT_BARANG = "INSERT INTO barang (idbarang, idbarcode, namabarang, tipe, merek, hargamodal, grosir, grosir2, eceran, satuan, stok, stok_minimum, supplier, keterangan, gambar, garansi, lamagaransi, kategori) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public void insertDetail(BarangKecilDTO dto, PreparedStatement ps) {
        try {
            ps = connection.prepareStatement(INSERT_BARANG);
            ps.setString(1, dto.getIdBarang());
            ps.setString(2, dto.getIdBarcode());
            ps.setString(3, dto.getNamaBarang());
            ps.setString(4, dto.getTipe());
            ps.setString(5, dto.getMerek());
            ps.setInt(6, dto.getHargamodal());
            ps.setInt(7, dto.getGrosir());
            ps.setInt(8, dto.getGrosir2());
            ps.setInt(9, dto.getEceran());
            ps.setString(10, dto.getSatuan());
            ps.setInt(11, dto.getStok());
            ps.setInt(12, dto.getStokMinimum());
            ps.setString(13, dto.getSupplier());
            ps.setString(14, dto.getKeterangan());
            try {
                if (dto.getGambar() != null) {
                    ps.setBlob(15, new FileInputStream(dto.getGambar()));
                } else {
                    ps.setNull(15, java.sql.Types.BLOB);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TambahBarangKecilDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ps.setString(16, dto.getGaransi());
            ps.setString(17, String.valueOf(dto.getLamaGaransi()));
            ps.setString(18, dto.getKategori());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TambahBarangKecilDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

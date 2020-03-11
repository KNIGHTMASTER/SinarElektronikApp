package com.wissensalt.sinarelektronik.masterdata.barangkecil.controller;

import com.wissensalt.sinarelektronik.dao.BarangKecilDAO;
import com.wissensalt.sinarelektronik.dao.impl.BarangKecilDAOImpl;
import com.wissensalt.sinarelektronik.inventory.barangkecil.view.JIFInventoryBarangKecilKecil;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.entity.BarangKecilDTO;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.model.BarangKecilModel;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.view.BarangKecilView;
import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangkecil.view.JIFBarangKecilBonusKaryawanBKView;
import com.wissensalt.sinarelektronik.penjualan.barangkecil.view.JIFKasirView;
import com.wissensalt.sinarelektronik.retur.barangkecil.view.JIFReturView2;
import com.wissensalt.sinarelektronik.retur.barangkecil.view.ProsesRetur;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class BarangController {
    private BarangKecilModel model;
    private BarangKecilDAO barangKecilDAO;

    public BarangController() {
        barangKecilDAO = new BarangKecilDAOImpl();
    }

    public void setModel(BarangKecilModel model) {
        this.model = model;
    }

    public void resetBarang(BarangKecilView view){
        model.resetBarang();
    }

    public void deleteBarang(BarangKecilView view){
        if(view.getTabelBarang().getSelectedRowCount()<1){
            JOptionPane.showMessageDialog(view, "pilih data terlebih dahulu");
            return;
        }else{
            if(JOptionPane.showConfirmDialog(view, "apakah pelanggan dengan id "+view.getTabelBarang().getValueAt(view.getTabelBarang().getSelectedRow(), 0) +" akan dihapus ?") == JOptionPane.YES_OPTION){
                String id=view.getTabelBarang().getValueAt(view.getTabelBarang().getSelectedRow(), 0).toString();
                JOptionPane.showMessageDialog(view, "Id  = "+id);
                model.setIdBarang(id);
                JOptionPane.showMessageDialog(view, "Sudah di set Id Barang");
                try {
                    model.deleteBarang();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BarangException ex) {
                    Logger.getLogger(BarangController.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(view, "Sudah di delete barangkecil");
            }
        }
    }
    public void cari(BarangKecilView view, BarangKecilView v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci().getText();
        String berdasarkan = v.getCmbCari().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "MerekDTO" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }

    }

    public void cari(JIFReturView2  view, BarangKecilView v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "MerekDTO" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }

    }

    public void cari6(JIFReturView2  view, JIFReturView2 v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "MerekDTO" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }

    }

    public void cariProsesRetur(ProsesRetur  view, ProsesRetur v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "MerekDTO" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }

    }


    public void cari(JIFKasirView view, JIFKasirView v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "MerekDTO" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }
    }

    public void cariBarangBonusKaryawan(JIFBarangKecilBonusKaryawanBKView view, JIFBarangKecilBonusKaryawanBKView v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "MerekDTO" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }
    }        
        
/*    public void cari2(ReturView view, ReturView v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "MerekDTO" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }                
    }    */

    public void cari3(JIFInventoryBarangKecilKecil view, JIFInventoryBarangKecilKecil v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "MerekDTO" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }
    }

    public void sort5(JIFReturView2 view) throws SQLException, BarangException{
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            //case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "MerekDTO" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            //case "satuan" : model.sortBarangbySatuan();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            //case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
        }
    }


    /**
     *
     * @param view
     * @throws SQLException
     * @throws BarangException
     */
    public void sortProsesRetur(ProsesRetur view) throws SQLException, BarangException{
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            //case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "MerekDTO" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            //case "satuan" : model.sortBarangbySatuan();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            //case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
        }
    }


    public void sort(BarangKecilView view) throws SQLException, BarangException{
        String berdasarkan = view.getCmbUrut().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            //case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "MerekDTO" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            //case "satuan" : model.sortBarangbySatuan();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            //case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
        }

    }

    public void sort(JIFKasirView view) throws SQLException, BarangException{

        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "MerekDTO" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            case "satuan" : model.sortBarangbySatuan();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
        }
    }

    public void sortBarangBonusKaryawan(JIFBarangKecilBonusKaryawanBKView view) throws SQLException, BarangException{

        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "MerekDTO" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            case "satuan" : model.sortBarangbySatuan();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
        }
    }    
    
/*    public void sort(ReturView view) throws SQLException, BarangException{
        
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "MerekDTO" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            case "satuan" : model.sortBarangbySatuan();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
    }    
    }    */

    public void sort(JIFInventoryBarangKecilKecil view) throws SQLException, BarangException{

        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "MerekDTO" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            case "satuan" : model.sortBarangbySatuan();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
        }
    }
}

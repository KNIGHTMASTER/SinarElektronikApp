package com.wissensalt.sinarelektronik.masterdata.barangtoko.reminderbarangtoko.controller;

import com.wissensalt.sinarelektronik.dao.BarangTokoDAO;
import com.wissensalt.sinarelektronik.dao.impl.BarangTokoDAOImpl;
import com.wissensalt.sinarelektronik.model.BarangTokoReminderModel;
import com.wissensalt.sinarelektronik.masterdata.barangtoko.reminderbarangtoko.view.ReminderView;
import com.wissensalt.sinarelektronik.penjualan.barangkecil.view.Kasirview;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class ReminderBarangTokoController {
    private BarangTokoReminderModel model;
    private BarangTokoDAO barangTokoDAO;

    public ReminderBarangTokoController() {
        barangTokoDAO = new BarangTokoDAOImpl();
    }

    public void setModel(BarangTokoReminderModel model) {
        this.model = model;
    }    
        
    public void resetBarang(ReminderView view){
        model.resetBarang();
    }

//    public void cari(String kataKunci, String berdasarkan) {
//        switch(berdasarkan){
//            case "id barangkecil" : model.search(barangTokoDAO.getBarangbyId(kataKunci)); break;
//            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
//            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
//            case "MerekDTO" : model.searchBarangbyMerek(kataKunci);break;
//            case "harga" : model.searchBarangbyHarga(kataKunci);break;
//            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
//            case "stok" : model.searchBarangbyStok(kataKunci);break;
//            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
//            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
//            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
//            default:;
//        }
//    }

    public void cari(ReminderView  view, ReminderView v) throws SQLException{
//        String kataKunci = view.getTxtKataKunci().getText();
//        String berdasarkan = v.getCmbCari().getSelectedItem().toString();
//        switch(berdasarkan){
//            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
//            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
//            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
//            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
//            case "MerekDTO" : model.searchBarangbyMerek(kataKunci);break;
//            case "harga" : model.searchBarangbyHarga(kataKunci);break;
//            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
//            case "stok" : model.searchBarangbyStok(kataKunci);break;
//            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
//            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
//            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
//            default:;
//        }
        
    }
    
    public void cari(Kasirview view, Kasirview v) throws SQLException{
//        String kataKunci = view.getTxtKataKunci1().getText();
//        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
//        switch(berdasarkan){
//            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
//            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
//            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
//            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
//            case "MerekDTO" : model.searchBarangbyMerek(kataKunci);break;
//            case "harga" : model.searchBarangbyHarga(kataKunci);break;
//            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
//            case "stok" : model.searchBarangbyStok(kataKunci);break;
//            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
//            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
//            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
//            default:;
//        }
        
    }    
    
    public void sort(ReminderView view) throws SQLException{        
//        String berdasarkan = view.getCmbUrut().getSelectedItem().toString();
//        switch(berdasarkan){
//            case "id barangkecil" : model.sortBarangbyId();break;
//            case "id barcode" : model.sortBarangbyBarcode();break;
//            case "nama barangkecil" : model.sortBarangbyNama();break;
//            case "tipe" : model.sortBarangbyTipe();break;
//            case "MerekDTO" : model.sortBarangbyMerek();break;
//            case "harga" : model.sortBarangbyHarga();break;
//            case "satuan" : model.sortBarangbySatuan();break;
//            case "stok" : model.sortBarangbyStok();break;
//            case "stok minimum" : model.sortBarangbyStokMin();break;
//            case "supplier" : model.sortBarangbySupplier();break;
//            case "keterangan" : model.sortBarangbyKeterangan();break;
//            default:;
//    }
    
    }
    
    public void sort(Kasirview view) throws SQLException{
//
//        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
//        switch(berdasarkan){
//            case "id barangkecil" : model.sortBarangbyId();break;
//            case "id barcode" : model.sortBarangbyBarcode();break;
//            case "nama barangkecil" : model.sortBarangbyNama();break;
//            case "tipe" : model.sortBarangbyTipe();break;
//            case "MerekDTO" : model.sortBarangbyMerek();break;
//            case "harga" : model.sortBarangbyHarga();break;
//            case "satuan" : model.sortBarangbySatuan();break;
//            case "stok" : model.sortBarangbyStok();break;
//            case "stok minimum" : model.sortBarangbyStokMin();break;
//            case "supplier" : model.sortBarangbySupplier();break;
//            case "keterangan" : model.sortBarangbyKeterangan();break;
//            default:;
//    }
    }      
}

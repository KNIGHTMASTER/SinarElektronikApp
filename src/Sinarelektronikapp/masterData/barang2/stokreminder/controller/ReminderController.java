/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.barang2.stokreminder.controller;

import Sinarelektronikapp.masterData.barang2.entity.barang;
import Sinarelektronikapp.masterData.barang2.error.BarangException;
import Sinarelektronikapp.masterData.barang2.stokreminder.model.ReminderModel;
import Sinarelektronikapp.masterData.barang2.stokreminder.view.ReminderView;
import Sinarelektronikapp.penjualan.view.Kasirview;
import java.sql.SQLException;

/**
 *
 * @author Fauzi
 */
public class ReminderController {
    private ReminderModel model;

    public ReminderController(ReminderModel model) {
        this.model = model;
    }

    public ReminderController() {
        
    }

    public void setModel(ReminderModel model) {
        this.model = model;
    }    
        
    public void resetBarang(ReminderView view){
        model.resetBarang();
    }
    
    
    private barang barangEntity;

    public barang getBarangEntity() {
        return barangEntity;
    }      
 
    public void cari(ReminderView  view, ReminderView v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci().getText();
        String berdasarkan = v.getCmbCari().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barang" : model.searchBarangbyId(kataKunci);break;
            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
            case "nama barang" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }
        
    }
    
    public void cari(Kasirview view, Kasirview v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barang" : model.searchBarangbyId(kataKunci);break;
            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
            case "nama barang" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }
        
    }    
    
    public void sort(ReminderView view) throws SQLException, BarangException{        
        String berdasarkan = view.getCmbUrut().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barang" : model.sortBarangbyId();break;
            case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barang" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            case "satuan" : model.sortBarangbySatuan();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
    }
    
    }
    
    public void sort(Kasirview view) throws SQLException, BarangException{
        
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barang" : model.sortBarangbyId();break;
            case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barang" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
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

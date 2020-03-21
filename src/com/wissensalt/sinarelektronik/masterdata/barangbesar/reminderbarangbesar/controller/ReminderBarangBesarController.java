package com.wissensalt.sinarelektronik.masterdata.barangbesar.reminderbarangbesar.controller;

import com.wissensalt.sinarelektronik.dao.ReminderBarangBesarDAO;
import com.wissensalt.sinarelektronik.dao.ReminderBarangBesarDAOImpl;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.reminderbarangbesar.model.ReminderModel;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.reminderbarangbesar.view.ReminderView;
import com.wissensalt.sinarelektronik.penjualan.barangkecil.view.Kasirview;

/**
 *
 * @author Fauzi
 */
public class ReminderBarangBesarController {
    private ReminderModel model;
    private ReminderBarangBesarDAO reminderBarangBesarDAO;

    public ReminderBarangBesarController(ReminderModel model) {
        this.model = model;
    }

    public ReminderBarangBesarController() {
        reminderBarangBesarDAO = new ReminderBarangBesarDAOImpl();
    }

    public void setModel(ReminderModel model) {
        this.model = model;
    }    
        
    public void resetBarang(ReminderView view){
        model.resetBarang();
    }

    private void cari(String kataKunci, String berdasarkan) {
        switch(berdasarkan){
            case "id barangkecil" : model.search(reminderBarangBesarDAO.getBarangbyId(kataKunci));break;
            case "id barcode" : model.search(reminderBarangBesarDAO.getBarangbyBarcode(kataKunci));break;
            case "nama barangkecil" : model.search(reminderBarangBesarDAO.getBarangbyNama(kataKunci));break;
            case "tipe" : model.search(reminderBarangBesarDAO.getBarangbyTipe(kataKunci));break;
            case "merek" : model.search(reminderBarangBesarDAO.getBarangbyMerek(kataKunci));break;
            case "harga" : model.search(reminderBarangBesarDAO.getBarangbyHarga(kataKunci));break;
            case "satuan" : model.search(reminderBarangBesarDAO.getBarangbySatuan(kataKunci));break;
            case "stok" : model.search(reminderBarangBesarDAO.getBarangbyStok(kataKunci));break;
            case "stok minimum" : model.search(reminderBarangBesarDAO.getBarangbyStokMin(kataKunci));break;
            case "supplier" : model.search(reminderBarangBesarDAO.getBarangbySupplier(kataKunci));break;
            case "keterangan" : model.search(reminderBarangBesarDAO.getBarangbyKeterangan(kataKunci));break;
            default:
        }
    }
    public void cari(ReminderView  view, ReminderView v) {
        String kataKunci = view.getTxtKataKunci().getText();
        String berdasarkan = v.getCmbCari().getSelectedItem().toString();
        cari(kataKunci, berdasarkan);
        
    }
    
    public void cari(Kasirview view, Kasirview v) {
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        cari(kataKunci, berdasarkan);
    }

    private void sort(String berdasarkan) {
        switch(berdasarkan){
            case "id barangkecil" : model.sort(reminderBarangBesarDAO.sortBarangbyId());break;
            case "id barcode" : model.sort(reminderBarangBesarDAO.sortBarangbyBarcode());break;
            case "nama barangkecil" : model.sort(reminderBarangBesarDAO.sortBarangbyNama());break;
            case "tipe" : model.sort(reminderBarangBesarDAO.sortBarangbyTipe());break;
            case "merek" : model.sort(reminderBarangBesarDAO.sortBarangbyMerek());break;
            case "harga" : model.sort(reminderBarangBesarDAO.sortBarangbyHarga());break;
            case "satuan" : model.sort(reminderBarangBesarDAO.sortBarangbySatuan());break;
            case "stok" : model.sort(reminderBarangBesarDAO.sortBarangbyStok());break;
            case "stok minimum" : model.sort(reminderBarangBesarDAO.sortBarangbyStok_min());break;
            case "supplier" : model.sort(reminderBarangBesarDAO.sortBarangbySupplier());break;
            case "keterangan" : model.sort(reminderBarangBesarDAO.sortBarangbyKeterangan());break;
            default:
        }
    }
    
    public void sort(ReminderView view) {
        String berdasarkan = view.getCmbUrut().getSelectedItem().toString();
        sort(berdasarkan);
    }
    
    public void sort(Kasirview view) {
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        sort(berdasarkan);
    }      
}

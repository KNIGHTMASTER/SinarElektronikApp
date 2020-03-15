package com.wissensalt.sinarelektronik.masterdata.supplier.controller;

import com.wissensalt.sinarelektronik.masterdata.supplier.model.SupplierModel;
import com.wissensalt.sinarelektronik.masterdata.supplier.view.SupplierView;
import com.wissensalt.sinarelektronik.masterdata.supplier.view.tambahSupplierView;

import javax.swing.*;

/**
 *
 * @author Fauzi
 */
public class supplierController {
    private SupplierModel model;

    public supplierController() {
    }

    public SupplierModel getModel() {
        return model;
    }

    public void setModel(SupplierModel model) {
        this.model = model;
    }

    
    public supplierController(SupplierModel model) {
        this.model = model;
    }
    
    public void resetSupplier(SupplierView view){
        model.resetSupplier();        
    }
    
    public void resetTambahSupplier(tambahSupplierView view){
        model.resetTambahSupplier();
    }
    
    public void insertSupplier(tambahSupplierView view){
        String idsupplier = view.getTxtIdsupplier().getText();
        String namaSupplier = view.getTxtNama().getText();
        String alamatSupplier = view.getTxtAlamat().getText();
        String kota = view.getTxtKota().getText();
        String propinsi = view.getTxtPropinsi().getText();
        String kodePost = view.getTxtKodePost().getText();
        String telepon = view.getTxtTelepon().getText();
        String fax = view.getTxtFax().getText();
        String bank = view.getTxtBank().getText();
        String nomorRek = view.getTxtNorek().getText();
        String atasNama = view.getTxtAtasNama().getText();
        String kontakPerson = view.getTxtKontakPerson().getText();
        String email = view.getTxtEmail().getText();
        String note = view.getTxtKeterangan().getText();
        
        if(namaSupplier.trim().equals("")){
            JOptionPane.showMessageDialog(view, "nama supplier tidak boleh kosong");
        }else{
            model.setIdsupplier(idsupplier);
            model.setNama(namaSupplier);
            model.setAlamat(alamatSupplier);
            model.setKota(kota);
            model.setPropinsi(propinsi);
            model.setKodePost(kodePost);
            model.setTelepon(telepon);
            model.setFax(fax);
            model.setBank(bank);
            model.setNomorRek(nomorRek);
            model.setAtasNama(atasNama);
            model.setKontakPerson(kontakPerson);
            model.setEmail(email);
            model.setNote(note);
            model.insertSupplier();
            JOptionPane.showMessageDialog(view, "Supplier insert successfull");
        }
    }
    
    public void search(SupplierView view, SupplierView view2) {
        String kataKunci = view.getTxtKataKunci().getText();
        String berdasarkan = view2.getCmbCari().getSelectedItem().toString();
        model.findByField(kataKunci, berdasarkan);
    }

    public void sort(SupplierView view) {
        String berdasarkan = view.getCmbUrut().getSelectedItem().toString();
        model.sortByField(berdasarkan);
    }
}


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.supplier.controller;

import Sinarelektronikapp.masterData.supplier.model.supplierModel;
import Sinarelektronikapp.masterData.supplier.view.supplierView;
import Sinarelektronikapp.masterData.supplier.view.tambahSupplierView;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Sinarelektronikapp.masterData.supplier.error.supplierException;

/**
 *
 * @author Fauzi
 */
public class supplierController {
    private supplierModel model;

    public supplierController() {
    }

    public supplierModel getModel() {
        return model;
    }

    public void setModel(supplierModel model) {
        this.model = model;
    }

    
    public supplierController(supplierModel model) {
        this.model = model;
    }
    
    public void resetSupplier(supplierView view){
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
            try {
                model.insertSupplier();
                JOptionPane.showMessageDialog(view, "Supplier telah dimasukkan");
            } catch (SQLException ex) {
                Logger.getLogger(supplierController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (supplierException ex) {
                Logger.getLogger(supplierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /*public void updateSupplier(suppli){
     }
    
    public void deleteSupplier(){
    * }*/
    
    public void search(supplierView view, supplierView view2) throws SQLException, supplierException{
        String kataKunci = view.getTxtKataKunci().getText();
        String berdasarkan = view2.getCmbCari().getSelectedItem().toString();
        switch(berdasarkan){
            case "idsupplier" : model.searchById(kataKunci);break;
            case "nama" : model.searchByNama(kataKunci);break;
            case "alamat" : model.searchByAlamat(kataKunci);break;
            case "kota" : model.searchByKota(kataKunci);break;
            case "propinsi" : model.searchByPropinsi(kataKunci);break;
            case "kodePost" : model.searchByKodePost(kataKunci);break;
            case "telepon" : model.searchByTelepon(kataKunci);break;                
            case "fax" : model.searchByFax(kataKunci);break;
            case "bank" : model.searchByBank(kataKunci);break;                                
            case "nomorRek" : model.searchByNomorRek(kataKunci);break;
            case "atasNama" : model.searchByAtasNama(kataKunci);break;
            case "kontakPerson" : model.searchByKontakPerson(kataKunci);break;
            case "email" : model.searchByEmail(kataKunci);break;
            case "note" : model.searchByNote(kataKunci);break;                        
        }
    }
    public void sort(supplierView view) throws SQLException, supplierException{
        String berdasarkan = view.getCmbUrut().getSelectedItem().toString();
        
        switch(berdasarkan){
            case "idsupplier" : model.sortById();break;
            case "nama" : model.sortByNama();break;
            case "alamat" : model.sortByAlamat();break;
            case "kota" : model.sortByKota();break;
            case "propinsi" : model.sortByPropinsi();break;
            case "kodePost" : model.sortByKodePost();break;
            case "telepon" : model.sortByTelepon();break;                
            case "fax" : model.sortByFax();break;
            case "bank" : model.sortByBank();break;                                
            case "nomorRek" : model.sortByNomorRek();break;
            case "atasNama" : model.sortByAtasNama();break;
            case "kontakPerson" : model.sortByKontakPerson();break;
            case "email" : model.sortByEmail();break;
            case "note" : model.sortByNote();break;                        
        }
    }
}


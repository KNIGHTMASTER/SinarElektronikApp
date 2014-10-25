/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.pelanggan.controller;

import Sinarelektronikapp.masterData.pelanggan.error.pelangganException;
import Sinarelektronikapp.masterData.pelanggan.model.pelangganModel;
import Sinarelektronikapp.masterData.pelanggan.view.pelangganView;
import java.awt.JobAttributes;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class pelangganController {
    private pelangganModel model;


    public void setModel(pelangganModel model) {
        this.model = model;
    }
    
    public void resetPelanggan(){
       model.resetPelanggan();
    }
    
    public void insertPelanggan(pelangganView view){
        String idPelanggan=view.getTxtIdPelanggan().getText();
        String nama=view.getTxtNama().getText();
        String alamat=view.getTxtALamat().getText();
        String kota =view.getTxtKota().getText();
        String propinsi =view.getTxtPropinsi().getText();
        String kodePost = view.getTxtKodePost().getText();
        String telepon = view.getTxtTelepon().getText();
        String fax = view.getTxtFax().getText();
        String kontakPerson = view.getTxtKontakPerson().getText();
        String catatan = view.getTxtCatatan().getText();
        
        if(idPelanggan.trim().equals("")){
            JOptionPane.showMessageDialog(view, "id masih kosong");            
        }else if(nama.trim().equals("")){
            JOptionPane.showMessageDialog(view, "nama masih kosong");
        }else{            
            try {
                model.setIdpelanggan(idPelanggan);
                model.setNama(nama);
                model.setAlamat(alamat);
                model.setKota(kota);
                model.setPropinsi(propinsi);
                model.setKodePost(kodePost);
                model.setTelepon(telepon);
                model.setFax(fax);
                model.setKontakPerson(kontakPerson);
                model.setCatatan(catatan);
                model.insertPelanggan();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    public void updatePelanggan(pelangganView view){
        String idPelanggan=view.getTxtIdPelanggan().getText();
        String nama=view.getTxtNama().getText();
        String alamat=view.getTxtALamat().getText();
        String kota =view.getTxtKota().getText();
        String propinsi =view.getTxtPropinsi().getText();
        String kodePost = view.getTxtKodePost().getText();
        String telepon = view.getTxtTelepon().getText();
        String fax = view.getTxtFax().getText();
        String kontakPerson = view.getTxtKontakPerson().getText();
        String catatan = view.getTxtCatatan().getText();
        
        if(idPelanggan.trim().equals("")){
            JOptionPane.showMessageDialog(view, "id masih kosong");            
        }else if(nama.trim().equals("")){
            JOptionPane.showMessageDialog(view, "nama masih kosong");
        }else{
            try {                
                model.setIdpelanggan(idPelanggan);
                model.setNama(nama);
                model.setAlamat(alamat);
                model.setKota(kota);
                model.setPropinsi(propinsi);
                model.setKodePost(kodePost);
                model.setTelepon(telepon);
                model.setFax(fax);
                model.setKontakPerson(kontakPerson);
                model.setCatatan(catatan);                
                model.updatePelanggan();
            } catch (SQLException ex) {
                Logger.getLogger(pelangganController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    public void deletePelanggan(pelangganView view){
        if(view.getTabelPelanggan().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "pilih data yang akan dihapus");
            return;
        }
        else{
            if(JOptionPane.showConfirmDialog(view, "apakah pelanggan dengan id "+view.getTxtIdPelanggan().getText()+" akan dihapus ?") == JOptionPane.YES_OPTION){
                String id=view.getTxtIdPelanggan().getText();
                model.setIdpelanggan(id);
                try {
                    model.deletePelanggan();
                } catch (SQLException ex) {
                    Logger.getLogger(pelangganController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (pelangganException ex) {
                    Logger.getLogger(pelangganController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void searchPelanggan(pelangganView view, pelangganView v) throws SQLException, pelangganException{
        String kataKunci = view.getTxtKataKunci().getText();
        String berdasarkan = view.getCmbCari().getSelectedItem().toString();
        switch(berdasarkan){
            case "id pelanggan" : model.getById(kataKunci);break;
            case "nama" : model.getByNama(kataKunci);break;
            case "alamat" : model.getByAlamat(kataKunci);break;
            case "kota" : model.getByKota(kataKunci);break;
            case "propinsi" : model.getByPropinsi(kataKunci);break;
            case "kode post" : model.getByKodePost(kataKunci);break;
            case "telepon" : model.getByTelepon(kataKunci);break;
            case "fax" : model.getByFax(kataKunci);break;                
            case "kontak person" : model.getByKontakPerson(kataKunci);break;
            case "catatan" : model.getByCatatan(kataKunci);break;                
            default:;
        }
    }
    
    public void sortPelanggan(pelangganView view) throws SQLException, pelangganException{        
        String berdasarkan = view.getCmbUrut().getSelectedItem().toString();
        switch(berdasarkan){
            case "id pelanggan" : model.sortById();break;
            case "nama" : model.sortByNama();break;
            case "alamat" : model.sortByAlamat();break;
            case "kota" : model.sortByKota();break;
            case "propinsi" : model.sortByPropinsi();break;
            case "kode post" : model.sortByKodePost();break;
            case "telepon" : model.sortByTelepon();break;
            case "fax" : model.sortByFax();break;                
            case "kontak person" : model.sortByKontakPerson();break;
            case "catatan" : model.sortByCatatan();break;                
            default:;
        }
    }    
}

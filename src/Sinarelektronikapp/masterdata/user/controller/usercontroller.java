/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.user.controller;

import Sinarelektronikapp.AppConstant;
import Sinarelektronikapp.masterdata.user.error.userException;
import Sinarelektronikapp.masterdata.user.model.usermodel;
import Sinarelektronikapp.masterdata.user.view.userView;
import Sinarelektronikapp.util.AES;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class usercontroller {    
    private usermodel model;

    public usercontroller(usermodel model) {
        this.model = model;
    }

    public usercontroller() {
        
    }
    
    public void resetUser(){
        model.resetUser();
    }
    
    public void insertUser(userView view) throws SQLException{
        //String idUser=view.getTxtIdUser().getText();
        String nama=view.getTxtNamaUSer().getText().trim();
        String password = view.getTxtPassword().getText().trim();
        String level=view.getCmbLevel().getSelectedItem().toString();
        String keterangan=view.getTxtKet().getText();
        
        if (password != null) {
            password = AES.encrypt(password, AppConstant.CONFIG_PASSWORD);
        }
        /*if(idUser.trim().equals("")){
            JOptionPane.showMessageDialog(view, "ID masih kosong");
            view.getTxtIdUser().setText("masukkan id");
            return;            
        }*/
        if(nama.trim().equals("")){
            JOptionPane.showMessageDialog(view, "nama masih kosong");            
            view.getTxtNamaUSer().setText("masukkan nama user");
            return;
        }else if(password.trim().equals("")){
            JOptionPane.showMessageDialog(view, "password masih kosong");            
            view.getTxtPassword().setText("12345");
            return;
        }else{
            //model.setIdUser(idUser);
            model.setNama(nama);
            model.setPassword(password);
            model.setLevel(level);
            if(keterangan == null){
                keterangan = "";
            }else{
                model.setKeterangan(keterangan);
            }            
            try {
                model.insertUser();
            } catch (userException ex) {
                JOptionPane.showMessageDialog(null, "Error di user controller dengan pesan "+ex);
            }
        }
    }
    
    public void updateUser(userView view) throws SQLException {
        if (view.getTabelUser().getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(view, "pilih data yang akan diupdate");
        } else {
            String idUser = view.getTxtIdUser().getText().trim();
            String nama = view.getTxtNamaUSer().getText().trim();
            String password = view.getTxtPassword().getText().trim();
            String level = view.getCmbLevel().getSelectedItem().toString();
            String keterangan = view.getTxtKet().getText();
            
            if (password != null) {
                password = AES.encrypt(password, AppConstant.CONFIG_PASSWORD);
            }
            
            model.setIdUser(idUser);
            model.setNama(nama);
            model.setPassword(password);
            model.setLevel(level);
            model.setKeterangan(keterangan);
            try {
                model.updateUser();
            } catch (userException ex) {
                Logger.getLogger(usercontroller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    public void deleteUser(userView view) throws SQLException{
        if(view.getTabelUser().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "pilih data yang akan di delete");
        }else{
            if(JOptionPane.showConfirmDialog(view, "Yakin data akan dihapus ?") == JOptionPane.YES_OPTION){
                String idUser = "";
                idUser = view.getTxtIdUser().getText();
                model.setIdUser(idUser);
                try {
                    model.deleteUser();
                } catch (userException ex) {
                    Logger.getLogger(usercontroller.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
        }        
    }
    
    public void searchUser(userView view, userView v) throws userException, SQLException{
        String cari=view.getCmbCari().getSelectedItem().toString();
        String keyword = v.getTxtCari().getText();
        switch(cari){
            case "Id user": model.searchById(keyword);break;                
            case "Nama": model.searchByNama(keyword);break;                
            case "Level": model.searchByLevel(keyword);break;                
            case "Keterangan": model.searchByKeterangan(keyword);break;                            
        }
    }
    
    public void sortUser(userView view) throws userException, SQLException{
        switch(view.getCmbUrut().getSelectedItem().toString()){
            case "Id user": model.sortById();break;                
            case "Nama": model.sortByNama();break;                
            case "Level": model.sortByLevel();break;                
            case "Keterangan": model.sortByKeterangan();break;                            
        }
    }
}

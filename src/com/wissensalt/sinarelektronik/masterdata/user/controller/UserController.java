package com.wissensalt.sinarelektronik.masterdata.user.controller;

import com.wissensalt.sinarelektronik.AppConstant;
import com.wissensalt.sinarelektronik.dao.UserDAO;
import com.wissensalt.sinarelektronik.dao.impl.UserDAOImpl;
import com.wissensalt.sinarelektronik.masterdata.user.entity.UserDTO;
import com.wissensalt.sinarelektronik.masterdata.user.model.UserModel;
import com.wissensalt.sinarelektronik.masterdata.user.view.UserView;
import com.wissensalt.sinarelektronik.util.AES;

import javax.swing.*;

/**
 *
 * @author Fauzi
 */
public class UserController {
    private UserModel model;
    private UserDAO userDAO;

    public UserController() {
        userDAO = new UserDAOImpl();
    }
    
    public void resetUser(){
        model.resetUser();
    }
    
    public void insertUser(UserView view)  {
        String nama=view.getTxtNamaUSer().getText().trim();
        String password = view.getTxtPassword().getText().trim();
        String level=view.getCmbLevel().getSelectedItem().toString();
        String keterangan=view.getTxtKet().getText();
        
        if (password != null) {
            password = AES.encrypt(password, AppConstant.CONFIG_PASSWORD);
        }
        if(nama.trim().equals("")){
            JOptionPane.showMessageDialog(view, "nama masih kosong");            
            view.getTxtNamaUSer().setText("masukkan nama user");
            return;
        }else if(password.trim().equals("")){
            JOptionPane.showMessageDialog(view, "password masih kosong");            
            view.getTxtPassword().setText("12345");
            return;
        }else{
            UserDTO userDTO = new UserDTO();
            userDTO.setNama(nama);
            userDTO.setPassword(password);
            userDTO.setLevel(level);
            userDTO.setKeterangan(keterangan);
            userDAO.insert(userDTO);
            model.insertUser(userDTO);
        }
    }
    
    public void updateUser(UserView view) {
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

            UserDTO userDTO = new UserDTO();
            userDTO.setIdUser(idUser);
            userDTO.setNama(nama);
            userDTO.setPassword(password);
            userDTO.setLevel(level);
            userDTO.setKeterangan(keterangan);
            userDAO.update(userDTO);
            model.updateUser(userDTO);
        }
    } 
    
    public void deleteUser(UserView view) {
        if(view.getTabelUser().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "pilih data yang akan di delete");
        }else{
            if(JOptionPane.showConfirmDialog(view, "Yakin data akan dihapus ?") == JOptionPane.YES_OPTION){
                String idUser;
                idUser = view.getTxtIdUser().getText();
                userDAO.deleteByString(idUser);
                model.deleteUser();
            }
        }        
    }
    
    public void searchUser(UserView view, UserView v) {
        String cari=view.getCmbCari().getSelectedItem().toString();
        String keyword = v.getTxtCari().getText();
        switch(cari){
            case "Id user": model.findByField(userDAO.searchById(keyword));break;
            case "Nama": model.findByField(userDAO.searchByname(keyword));break;
            case "Level": model.findByField(userDAO.searchByLevel(keyword));break;
            case "Keterangan": model.findByField(userDAO.searchByKet(keyword));break;
        }
    }
    
    public void sortUser(UserView view) {
        switch(view.getCmbUrut().getSelectedItem().toString()){
            case "Id user": model.sortByField(userDAO.sortById());break;
            case "Nama": model.sortByField(userDAO.sortByname());break;
            case "Level": model.sortByField(userDAO.sortByLevel());break;
            case "Keterangan": model.sortByField(userDAO.sortByKet());break;
        }
    }
}

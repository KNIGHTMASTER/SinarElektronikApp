package com.wissensalt.sinarelektronik.masterdata.user.model;

import com.wissensalt.sinarelektronik.masterdata.user.entity.UserDTO;
import com.wissensalt.sinarelektronik.masterdata.user.model.event.UserListener;
import com.wissensalt.sinarelektronik.model.BaseModel;

import java.util.List;

/**
 *
 * @author Fauzi
 */
public class UserModel extends BaseModel {

    public UserModel() {
    }
    
    private String idUser, nama, password, level, keterangan;
    
    private UserListener listener;

    public UserListener getListener() {
        return listener;
    }

    public void setListener(UserListener listener) {
        this.listener = listener;
    }        
    
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
        fireOnChange();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
        fireOnChange();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        fireOnChange();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
        fireOnChange();
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
        fireOnChange();
    }
    
    public void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }
    
    public void fireOnInsert(UserDTO userDTO){
        if(listener!=null){
            listener.onInsert(userDTO);
        }
    }
    
    public void fireOnUpdate(UserDTO userDTO){
        if(listener!=null){
            listener.onUpdate(userDTO);
        }
    }
    
    public void fireOnDelete(){
        if(listener!=null){
            listener.onDelete();
        }
    }
    
    public void fireOnSearch(List list){
        if(listener!=null){
            listener.onSearch(list);
        }
    }

     public void fireOnSort(List list){
        if(listener!=null){
            listener.onSort(list);
        }
    }
     
    public void resetUser(){
        setIdUser("");
        setKeterangan("");        
        setNama("");
        setPassword("");
    }    
    
    public void insertUser(UserDTO userDTO) {
        fireOnInsert(userDTO);
    }
    
    public void updateUser(UserDTO userDTO) {
        fireOnUpdate(userDTO);
    }
    
    public void deleteUser() {
        fireOnDelete();
    }
    
    public void findByField(List list) {
        fireOnSearch(list);
    }
    
     public void sortByField(List list) {
        fireOnSort(list);
    }
}

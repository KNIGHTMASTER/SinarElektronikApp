/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.user.model;

import Sinarelektronikapp.masterdata.user.database.userdatabase;
import Sinarelektronikapp.masterdata.user.entity.User;
import Sinarelektronikapp.masterdata.user.error.userException;
import Sinarelektronikapp.masterdata.user.model.event.userListener;
import Sinarelektronikapp.masterdata.user.service.userDao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public class usermodel {

    public usermodel() {
    }
    
    private String idUser, nama, password, level, keterangan;
    
    private userListener listener;

    public userListener getListener() {
        return listener;
    }

    public void setListener(userListener listener) {
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
    
    public void fireOnInsert(User user){
        if(listener!=null){
            listener.onInsert(user);
        }
    }
    
    public void fireOnUpdate(User user){
        if(listener!=null){
            listener.onUpdate(user);
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
    
    public void insertUser() throws userException, SQLException{
        userDao dao=userdatabase.getUserDao();
        User user=new User();
        //user.setIduser(idUser);
        user.setNama(nama);
        user.setPassword(password);
        user.setLevel(level);
        user.setKeterangan(keterangan);
        dao.insertUser(user);
        fireOnInsert(user);
    }
    
    public void updateUser() throws userException, SQLException{
        userDao dao=userdatabase.getUserDao();
        User user=new User();
        user.setIduser(idUser);
        user.setNama(nama);
        user.setPassword(password);
        user.setLevel(level);
        user.setKeterangan(keterangan);
        dao.updateUser(user);
        fireOnUpdate(user);
    }
    
    public void deleteUser() throws userException, SQLException{
        userDao dao = userdatabase.getUserDao();
        dao.deleteUser(idUser);
        fireOnDelete();
    }
    
    public void searchById(String keyword) throws userException, SQLException, SQLException{
        userDao dao = userdatabase.getUserDao();
        List<User> list=dao.searchById(keyword);
        fireOnSearch(list);
    }
    
    public void searchByNama(String keyword) throws userException, SQLException{
        userDao dao = userdatabase.getUserDao();
        List<User> list=dao.searchByname(keyword);
        fireOnSearch(list);
    }
    
    public void searchByLevel(String keyword) throws userException, SQLException{
        userDao dao = userdatabase.getUserDao();
        List<User> list=dao.searchByLevel(keyword);
        fireOnSearch(list);
    }
    
    public void searchByKeterangan(String keyword) throws userException, SQLException{
        userDao dao = userdatabase.getUserDao();
        List<User> list=dao.searchByKet(keyword);
        fireOnSearch(list);
    }
    
     public void sortById() throws userException, SQLException{
        userDao dao = userdatabase.getUserDao();
        List<User> list=dao.sortById();
        fireOnSort(list);
    }
     
     public void sortByNama() throws userException, SQLException{
        userDao dao = userdatabase.getUserDao();
        List<User> list=dao.sortByname();
        fireOnSort(list);
    }
     
     public void sortByLevel() throws userException, SQLException{
        userDao dao = userdatabase.getUserDao();
        List<User> list=dao.sortByLevel();
        fireOnSort(list);
    }
     
     public void sortByKeterangan() throws userException, SQLException{
        userDao dao = userdatabase.getUserDao();
        List<User> list=dao.sortByKet();
        fireOnSort(list);
    }     
}

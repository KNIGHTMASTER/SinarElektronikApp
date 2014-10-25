/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.user.service;

import Sinarelektronikapp.masterData.user.entity.User;
import Sinarelektronikapp.masterData.user.error.userException;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface userDao {
    public void insertUser(User user) throws userException;
    
    public void updateUser(User user) throws userException;
    
    public void deleteUser(String iduser) throws userException;
    
    public List<User> selectAllUser() throws userException;
    
    public List<User> searchById(String id) throws userException;
    
    public List<User> searchByname(String name) throws userException;
    
    public List<User> searchByLevel(String level) throws userException;
    
    public List<User> searchByKet(String ket) throws userException;
    
    public List<User> sortById() throws userException;
    
    public List<User> sortByname() throws userException;
    
    public List<User> sortByLevel() throws userException;
    
    public List<User> sortByKet() throws userException;    
    
    public int getLastId() throws userException;
}

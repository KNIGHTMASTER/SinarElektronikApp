/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.user.service.impl;

import Sinarelektronikapp.masterData.user.entity.User;
import Sinarelektronikapp.masterData.user.error.userException;
import Sinarelektronikapp.masterData.user.service.userDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class userDaoImpl implements userDao{
    
    private Connection connection;

    public userDaoImpl(Connection connection) {
        this.connection = connection;
    }        

    private final String insertUser = "INSERT INTO user (nama, password, level, keterangan) VALUES (?, ?, ?, ?) ";
    
    private final String updateUser = "UPDATE user SET nama=?, password=?, level=?, keterangan=? WHERE iduser=?";                                        
    
    private final String deleteUser = "DELETE FROM user WHERE iduser=?";
    
    private final String selectAllUser = "SELECT * FROM user";
    
    private final String searchById = "SELECT * FROM user WHERE iduser LIKE ?";
    
    private final String searchByName = "SELECT * FROM user WHERE nama LIKE ?";
    
    private final String searchByLevel = "SELECT * FROM user WHERE level LIKE ?";
    
    private final String searchByKet= "SELECT * FROM user WHERE keterangan LIKE ?";
    
    private final String sortById= "SELECT * FROM user ORDER BY iduser";
    
    private final String sortByNama= "SELECT * FROM user ORDER BY nama";
    
    private final String sortByLevel= "SELECT * FROM user ORDER BY level";
    
    private final String sortByKet= "SELECT * FROM user ORDER BY keterangan";
    
    private final String getLastId = "SELECT COUNT(iduser)+1 total FROM user";
    
    @Override
    public void insertUser(User user) throws userException {
        PreparedStatement ps=null;        
        try{
            connection.setAutoCommit(false);
            ps=connection.prepareStatement(insertUser, ps.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getNama());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getLevel());
            ps.setString(4, user.getKeterangan());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                user.setIduser(String.valueOf(rs.getInt(1)));
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Terjadi error pada insert di userdaoimpl dengan pesan = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void updateUser(User user) throws userException {        
        PreparedStatement ps=null;        
        try{
            connection.setAutoCommit(false);
            ps=connection.prepareStatement(updateUser);            
            ps.setString(1, user.getNama());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getLevel());
            ps.setString(4, user.getKeterangan());
            ps.setString(5, user.getIduser());
            ps.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Terjadi error pada Update dengan pesan = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void deleteUser(String iduser) throws userException {
        PreparedStatement ps=null;        
        try{
            connection.setAutoCommit(false);
            ps=connection.prepareStatement(deleteUser);            
            ps.setString(1, iduser);
            ps.executeUpdate();
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Terjadi error pada Delete dengan pesan = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<User> selectAllUser() throws userException {
        Statement ps=null;        
        List<User> list=new ArrayList<User>();
        try{            
            connection.setAutoCommit(false);
            ps=connection.createStatement();
            ResultSet rs = ps.executeQuery(selectAllUser);
            while(rs.next()){
                User user=new User();
                user.setIduser(rs.getString("iduser"));
                user.setNama(rs.getString("nama"));
                user.setPassword(rs.getString("password"));
                user.setLevel(rs.getString("level"));
                user.setKeterangan(rs.getString("keterangan"));
                list.add(user);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Terjadi error pada select all user dengan pesan = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<User> searchById(String id) throws userException {
        PreparedStatement ps=null;        
        List<User> list=new ArrayList<User>();
        try{                    
            connection.setAutoCommit(false);
            ps=connection.prepareStatement(searchById);
            ps.setString(1, "%"+id+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user=new User();    
                user.setIduser(rs.getString("iduser"));
                user.setNama(rs.getString("nama"));
                user.setPassword(rs.getString("password"));
                user.setLevel(rs.getString("level"));
                user.setKeterangan(rs.getString("keterangan"));
                list.add(user);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Terjadi error pada search by id user dengan pesan = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<User> searchByname(String name) throws userException {
        PreparedStatement ps=null;        
        List<User> list=new ArrayList<User>();
        try{                
            connection.setAutoCommit(false);
            ps=connection.prepareStatement(searchById);
            ps.setString(1, "%"+name+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user=new User();        
                user.setIduser(rs.getString("iduser"));
                user.setNama(rs.getString("nama"));
                user.setPassword(rs.getString("password"));
                user.setLevel(rs.getString("level"));
                user.setKeterangan(rs.getString("keterangan"));
                list.add(user);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Terjadi error pada search by name user dengan pesan = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<User> searchByLevel(String level) throws userException {

        PreparedStatement ps=null;        
        List<User> list=new ArrayList<User>();
        try{            
            connection.setAutoCommit(false);
            ps=connection.prepareStatement(searchById);
            ps.setString(1, "%"+level+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user=new User();            
                user.setIduser(rs.getString("iduser"));
                user.setNama(rs.getString("nama"));
                user.setPassword(rs.getString("password"));
                user.setLevel(rs.getString("level"));
                user.setKeterangan(rs.getString("keterangan"));
                list.add(user);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Terjadi error pada search by level user dengan pesan = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<User> searchByKet(String ket) throws userException {

        PreparedStatement ps=null;        
        List<User> list=new ArrayList<User>();
        try{            
            connection.setAutoCommit(false);
            ps=connection.prepareStatement(searchById);
            ps.setString(1, "%"+ket+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user=new User();            
                user.setIduser(rs.getString("iduser"));
                user.setNama(rs.getString("nama"));
                user.setPassword(rs.getString("password"));
                user.setLevel(rs.getString("level"));
                user.setKeterangan(rs.getString("keterangan"));
                list.add(user);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Terjadi error pada search by keterangan user dengan pesan = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<User> sortById() throws userException {
        PreparedStatement ps=null;        
        List<User> list=new ArrayList<User>();
        try{            
            connection.setAutoCommit(false);
            ps=connection.prepareStatement(sortById);            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user=new User();            
                user.setIduser(rs.getString("iduser"));
                user.setNama(rs.getString("nama"));
                user.setPassword(rs.getString("password"));
                user.setLevel(rs.getString("level"));
                user.setKeterangan(rs.getString("keterangan"));
                list.add(user);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Terjadi error pada sort by id user dengan pesan = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<User> sortByname() throws userException {
        PreparedStatement ps=null;        
        List<User> list=new ArrayList<User>();
        try{            
            connection.setAutoCommit(false);
            ps=connection.prepareStatement(sortByNama);          
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user=new User();            
                user.setIduser(rs.getString("iduser"));
                user.setNama(rs.getString("nama"));
                user.setPassword(rs.getString("password"));
                user.setLevel(rs.getString("level"));
                user.setKeterangan(rs.getString("keterangan"));
                list.add(user);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Terjadi error pada search by nama user dengan pesan = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<User> sortByLevel() throws userException {
        PreparedStatement ps=null;        
        List<User> list=new ArrayList<User>();
        try{            
            connection.setAutoCommit(false);
            ps=connection.prepareStatement(searchById);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user=new User();            
                user.setIduser(rs.getString("iduser"));
                user.setNama(rs.getString("nama"));
                user.setPassword(rs.getString("password"));
                user.setLevel(rs.getString("level"));
                user.setKeterangan(rs.getString("keterangan"));
                list.add(user);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Terjadi error pada sort by level user dengan pesan = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public List<User> sortByKet() throws userException {
        PreparedStatement ps=null;        
        List<User> list=new ArrayList<User>();
        try{            
            connection.setAutoCommit(false);
            ps=connection.prepareStatement(sortByKet);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user=new User();            
                user.setIduser(rs.getString("iduser"));
                user.setNama(rs.getString("nama"));
                user.setPassword(rs.getString("password"));
                user.setLevel(rs.getString("level"));
                user.setKeterangan(rs.getString("keterangan"));
                list.add(user);
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Terjadi error pada sort by ket user dengan pesan = "+exception);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    @Override
    public int getLastId() throws userException {
        int hasil=0;
        PreparedStatement ps=null;        
        try{
            connection.setAutoCommit(false);
            ps=connection.prepareStatement(getLastId);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                hasil=rs.getInt("total");
            }
            connection.commit();
        }catch(SQLException exception){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(userDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return hasil;
    }
    
}
    
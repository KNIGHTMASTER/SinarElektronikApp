/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.config;

import Sinarelektronikapp.AppConstant;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author Fauzi
 */
public class UserLevel {
    private String userLevel;
    private static final String FILE_NAME = "userlevel.txt";

    private BasicTextEncryptor basicTextEncryptor;
    
    public UserLevel() {
        basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(AppConstant.CONFIG_PASSWORD);
    }
    
    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.userLevel);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserLevel other = (UserLevel) obj;
        if (!Objects.equals(this.userLevel, other.userLevel)) {
            return false;
        }
        return true;
    }
    
    Path fileUserLevel;
    
    public String getUserLevelActive(){        
        Scanner scan = null;
        try{
            /*BufferedReader br = Files.newBufferedReader(fileConfig, Charset.defaultCharset());            
            String getData = "";
            if((getData = br.readLine())!=null){
                //JOptionPane.showMessageDialog(null, "get data = "+getData);
                ipServer = getData;
                //JOptionPane.showMessageDialog(null, "ip server = "+ipServer);
            }*/
        scan = new Scanner(new FileReader(FILE_NAME));
            while (scan.hasNext()) {                
                userLevel = scan.nextLine();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error dalam membaca file cofiguration karena "+e, "Perhatian", JOptionPane.ERROR_MESSAGE);
        }finally{
            if (scan != null) {
                scan.close();
            }
        }
        
        if (userLevel != null) {
            userLevel = basicTextEncryptor.decrypt(userLevel);
        }else {
            userLevel = null;
        }
        return userLevel;
    }
    
    public void Filling(){       
        fileUserLevel= Paths.get(FILE_NAME);
        BufferedWriter bw = null;
        try{
            Files.deleteIfExists(fileUserLevel);
            fileUserLevel = Files.createFile(fileUserLevel);
            bw = Files.newBufferedWriter(fileUserLevel, Charset.defaultCharset());
            userLevel = basicTextEncryptor.encrypt(getUserLevel());
            bw.append(userLevel);
            bw.flush();
            //JOptionPane.showMessageDialog(null, "selesai mengisi fileConfig");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error dalam membuat file konfigurasi User karena "+e, "Perhatian", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(ActiveUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void deleteUserConfig(){
        fileUserLevel= Paths.get(FILE_NAME);
        BufferedWriter bw = null;
        try {        
            Files.deleteIfExists(fileUserLevel);
        } catch (IOException ex) {
            Logger.getLogger(ActiveUser.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(ActiveUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }    
    
}

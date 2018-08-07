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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author Fauzi
 */
public class ActiveUser {
    private String userName;
    private final String FILE_NAME = "user.txt";
    
    private final BasicTextEncryptor basicTextEncryptor;

    public ActiveUser() {
        basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(AppConstant.CONFIG_PASSWORD);
    }
    
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    Path fileUser;
    
    public String getUserActive(){
        String user="";
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
                user = scan.nextLine();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error dalam membaca file cofiguration karena "+e);
        }finally{
            if (scan != null) {
                scan.close();
            }
        }
        if (user != null) {
            user = basicTextEncryptor.decrypt(user);
        }else {
            user = null;
        }
        return user;
    }
    
    public void Filling(){       
        fileUser= Paths.get(FILE_NAME);
        BufferedWriter bw = null;
        try{
            Files.deleteIfExists(fileUser);
            fileUser = Files.createFile(fileUser);
            userName = basicTextEncryptor.encrypt(getUserName());
            bw = Files.newBufferedWriter(fileUser, Charset.defaultCharset());
            bw.append(userName);
            bw.flush();
            //JOptionPane.showMessageDialog(null, "selesai mengisi fileConfig");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error dalam membuat file konfigurasi User karena "+e);
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
        fileUser= Paths.get(FILE_NAME);
        BufferedWriter bw = null;
        try {        
            Files.deleteIfExists(fileUser);
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

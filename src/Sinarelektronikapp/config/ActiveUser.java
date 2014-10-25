/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.config;

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

/**
 *
 * @author Fauzi
 */
public class ActiveUser {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.userName);
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
        final ActiveUser other = (ActiveUser) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        return true;
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
        scan = new Scanner(new FileReader("user.txt"));
            while (scan.hasNext()) {                
                user = scan.nextLine();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error dalam membaca file cofiguration karena "+e);
        }finally{
            scan.close();
        }
        return user;
    }
    
    public void Filling(){       
        fileUser= Paths.get("user.txt");
        BufferedWriter bw = null;
        try{
            Files.deleteIfExists(fileUser);
            fileUser = Files.createFile(fileUser);
            bw = Files.newBufferedWriter(fileUser, Charset.defaultCharset());
            bw.append(getUserName());
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
        fileUser= Paths.get("user.txt");
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

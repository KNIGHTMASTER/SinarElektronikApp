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
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author Fauzi
 */
public class InternetProtocol {
    String ip;
    
    private BasicTextEncryptor basicTextEncryptor;
    public InternetProtocol() {
        basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(AppConstant.CONFIG_PASSWORD);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    Path fileConfig;
    
    public String getIpServer(){
        String ipServer="";
        Scanner scan = null;
        try{
            /*BufferedReader br = Files.newBufferedReader(fileConfig, Charset.defaultCharset());            
            String getData w = "";
            if((getData = br.readLine())!=null){
                //JOptionPane.showMessageDialog(null, "get data = "+getData);
                ipServer = getData;
                //JOptionPane.showMessageDialog(null, "ip server = "+ipServer);
            }*/
        scan = new Scanner(new FileReader("config.txt"));
            while (scan.hasNext()) {                
                ipServer = scan.nextLine();
            }            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error dalam membaca file cofiguration karena "+e);
        } finally{
            if (scan != null) {
                scan.close();
            }
        }
        if (ipServer != null) {           
            ipServer = basicTextEncryptor.decrypt(ipServer);
        }else {
            ipServer = null;
        }
        return ipServer;
    }
    
    public String tes(){        
        fileConfig= Paths.get("config.txt");
        BufferedWriter bw = null;
        try{
            Files.deleteIfExists(fileConfig);
            fileConfig = Files.createFile(fileConfig);
            //JOptionPane.showMessageDialog(null, "selesai membuat file");                      
            ip = basicTextEncryptor.encrypt(getIp());
            
            bw = Files.newBufferedWriter(fileConfig, Charset.defaultCharset());
            bw.append(ip);
            bw.flush();
                        
            //JOptionPane.showMessageDialog(null, "selesai mengisi fileConfig");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error dalam membuat file konfigurasi karena "+e);
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(InternetProtocol.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        String hasil = "";
        Connection c = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://"+getIpServer()+"/sinarelektronik?;", "root", "P@ssw0rd");
            hasil = "Sukses";
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error Testing Karena = "+e);
        }
        return hasil;
    }
    
}

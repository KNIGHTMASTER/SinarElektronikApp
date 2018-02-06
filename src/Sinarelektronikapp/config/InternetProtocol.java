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
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class InternetProtocol {
    String ip;

    public InternetProtocol() {
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
        try{
            /*BufferedReader br = Files.newBufferedReader(fileConfig, Charset.defaultCharset());            
            String getData = "";
            if((getData = br.readLine())!=null){
                //JOptionPane.showMessageDialog(null, "get data = "+getData);
                ipServer = getData;
                //JOptionPane.showMessageDialog(null, "ip server = "+ipServer);
            }*/
        Scanner scan = new Scanner(new FileReader("config.txt"));
            while (scan.hasNext()) {                
                ipServer = scan.nextLine();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error dalam membaca file cofiguration karena "+e);
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
            bw = Files.newBufferedWriter(fileConfig, Charset.defaultCharset());
            bw.append(getIp());
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
            c = DriverManager.getConnection("jdbc:mysql://"+getIpServer()+"/sinarelektronik?;", "root", "5430trisin9");
            hasil = "Sukses";
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error Testing Karena = "+e);
        }
        return hasil;
    }
    
}

package Sinarelektronikapp.config;

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

/**
 * Created on Aug 7, 2018
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class ApplicationMode {
    
    private String activeMode;
    
    private final String FILE_NAME = "ui-config.txt";
    private final String DEFAULT = "1";

    public String getActiveMode() {
        return activeMode;
    }

    public void setActiveMode(String activeMode) {
        this.activeMode = activeMode;
    }
    
    
    public String getApplicationMode() {
        String result="";
        try(Scanner scan = new Scanner(new FileReader(FILE_NAME))) {
            while (scan.hasNext()) {                
                result = scan.nextLine();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error dalam membaca file cofiguration karena "+e);
        }
        if (result != null && result.length() > 0) {
            result = result.split("=")[1].trim();
        }
        return result;
    }
    
    public void filling() {
        Path fileAppMode= Paths.get(FILE_NAME);
        BufferedWriter bw = null;        
        try{
            Files.deleteIfExists(fileAppMode);
            fileAppMode = Files.createFile(fileAppMode);            
            bw = Files.newBufferedWriter(fileAppMode, Charset.defaultCharset());
            bw.append(DEFAULT);
            bw.flush();
        }catch(IOException e){
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
}
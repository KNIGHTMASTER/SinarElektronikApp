/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.report.core;

import Sinarelektronikapp.config.InternetProtocol;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Fauzi
 */
public class Report {
    
    public Connection conn = null;
        
    
    InternetProtocol ip = new InternetProtocol();
    
    public Report() {
        koneksi();
        Validation v = new Validation();
        v.validasiReport();
    }
    
        
	
    public void koneksi(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+ip.getIpServer()+"/sinarelektronik?;", "root", "5430trisin9");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error pada koneksi report karena = "+e);
        }
    }
    
    private URL reportPath = getClass().getResource("\\masterData\\report\\");
    public void buildReport(String vName){
        Blob reportSource=null;
        byte [] data = null;
        String reportDest="";
        
        koneksi();
        Statement s = null;
        try {
            s =  conn.createStatement();
            ResultSet rs=s.executeQuery("SELECT pathsource, pathdest FROM report WHERE nama='"+vName+"'");
            if(rs.next()){
                reportSource = rs.getBlob("pathsource");                
                data = reportSource.getBytes(1, (int)reportSource.length());
                reportDest = rs.getString("pathdest");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error karena = "+ex);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }     
        
        try{
            File reportTemplate = new File("D:\\a.jrxml");
            //File reportTemplate = new File("c:\\"+vName+".jrxml");
            FileOutputStream fos = new FileOutputStream(reportTemplate);
            fos.write(data);
            fos.close();            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate.getAbsolutePath());
            JasperPrint jasperprint=JasperFillManager.fillReport(jasperReport, null, conn);
            JasperExportManager.exportReportToHtmlFile(jasperprint, reportDest);
            JasperViewer.viewReport(jasperprint, false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}

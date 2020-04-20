/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.report.core;

import Sinarelektronikapp.config.InternetProtocol;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
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
public class ReportParam {
    public Connection conn = null;

    InternetProtocol ip = new InternetProtocol();            
    
    public ReportParam() {
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
    
    //private URL reportPath = getClass().getResource("\\masterdata\\report\\");
    
    public void buildReportStruk(String vName, int idTransaksi){
        Blob reportSource=null;
        byte [] data = null;
        String reportDest="";        
        String tes = null;        
        koneksi();
        Statement s = null;
        try {
            s = conn.createStatement();
            ResultSet rs=s.executeQuery("SELECT pathsource, pathdest FROM report WHERE nama='"+vName+"'");
            if(rs.next()){
                reportSource = rs.getBlob("pathsource");
                reportDest = rs.getString("pathdest");
                data = reportSource.getBytes(1, (int)reportSource.length());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error karena = "+ex);
        }finally{
            if(s!=null){
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportParam.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }     

            HashMap hashMap = new HashMap();
            hashMap.put("id", idTransaksi);                                
        try{
            File reportTemplate = new File("D:\\a.jrxml");
            FileOutputStream fos = new FileOutputStream(reportTemplate);
            fos.write(data);
            fos.close();            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate.getAbsolutePath());
            JasperPrint jasperprint=JasperFillManager.fillReport(jasperReport, hashMap, conn);
            JasperExportManager.exportReportToHtmlFile(jasperprint, reportDest);
            JasperViewer.viewReport(jasperprint, false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }  

    public void buildReport(String vName, Date tanggal){
        Blob reportSource=null;
        byte [] data = null;
        String reportDest="";        
        
        koneksi();
        try {
            Statement s = conn.createStatement();
            ResultSet rs=s.executeQuery("SELECT pathsource, pathdest FROM report WHERE nama='"+vName+"'");
            if(rs.next()){
                reportSource = rs.getBlob("pathsource");
                reportDest = rs.getString("pathdest");
                data = reportSource.getBytes(1, (int)reportSource.length());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error karena = "+ex);
        }        

            HashMap hashMap = new HashMap();
            hashMap.put("Tanggal", tanggal);
                    
            
        try{
            File reportTemplate = new File("D:\\a.jrxml");
            FileOutputStream fos = new FileOutputStream(reportTemplate);
            fos.write(data);
            fos.close();            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate.getAbsolutePath());
            JasperPrint jasperprint=JasperFillManager.fillReport(jasperReport, hashMap, conn);
            JasperExportManager.exportReportToHtmlFile(jasperprint, reportDest);
            JasperViewer.viewReport(jasperprint, false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }        
    public void buildReportDoubleParam(String vName, Date tanggalAwal, Date tanggalAkhir){
        Blob reportSource=null;
        byte [] data = null;
        String reportDest="";        
        
        koneksi();
        try {
            Statement s = conn.createStatement();
            ResultSet rs=s.executeQuery("SELECT pathsource, pathdest FROM report WHERE nama='"+vName+"'");
            if(rs.next()){
                reportSource = rs.getBlob("pathsource");
                data = reportSource.getBytes(1, (int)reportSource.length());
                reportDest = rs.getString("pathdest");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error karena = "+ex);
        }        
            HashMap<String, Object> hashMap = new HashMap<String, Object>();            
            hashMap.put("Tanggal", tanggalAwal);
            hashMap.put("tanggal2", tanggalAkhir);                                
        try{
            File reportTemplate = new File("D:\\a.jrxml");
            FileOutputStream fos = new FileOutputStream(reportTemplate);
            fos.write(data);
            fos.close();            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate.getAbsolutePath());
            JasperPrint jasperprint=JasperFillManager.fillReport(jasperReport, hashMap, conn);
            JasperExportManager.exportReportToHtmlFile(jasperprint, reportDest);
            JasperViewer.viewReport(jasperprint, false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }            
    
public void buildReportDoubleParamPriceList(String vName, String kategori, String merek){
        Blob reportSource=null;
        byte [] data = null;
        String reportDest="";        
        
        koneksi();
        try {
            Statement s = conn.createStatement();
            ResultSet rs=s.executeQuery("SELECT pathsource, pathdest FROM report WHERE nama='"+vName+"'");
            if(rs.next()){
                reportSource = rs.getBlob("pathsource");
                data = reportSource.getBytes(1, (int)reportSource.length());
                reportDest = rs.getString("pathdest");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error karena = "+ex);
        }        
            HashMap<String, Object> hashMap = new HashMap<String, Object>();            
            hashMap.put("kategori", kategori);
            hashMap.put("merek", merek);
        try{
            File reportTemplate = new File("D:\\"+vName+".jrxml");
            FileOutputStream fos = new FileOutputStream(reportTemplate);
            fos.write(data);
            fos.close();            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate.getAbsolutePath());
            JasperPrint jasperprint=JasperFillManager.fillReport(jasperReport, hashMap, conn);
            JasperExportManager.exportReportToHtmlFile(jasperprint, reportDest);
            JasperViewer.viewReport(jasperprint, false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }                
    
    public void buildReportTripleParam(String vName, String penjual, Date tanggalAwal, Date tanggalAkhir){
        Blob reportSource=null;
        byte [] data = null;
        String reportDest="";        
        
        koneksi();
        try {
            Statement s = conn.createStatement();
            ResultSet rs=s.executeQuery("SELECT pathsource, pathdest FROM report WHERE nama='"+vName+"'");
            if(rs.next()){
                reportSource = rs.getBlob("pathsource");
                data = reportSource.getBytes(1, (int)reportSource.length());
                reportDest = rs.getString("pathdest");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error karena = "+ex);
        }        
            HashMap<String, Object> hashMap = new HashMap<String, Object>();            
            hashMap.put("penjual", penjual);
            hashMap.put("Tanggal", tanggalAwal);
            hashMap.put("tanggal2", tanggalAkhir);                                
        try{
            File reportTemplate = new File("D:\\a.jrxml");
            FileOutputStream fos = new FileOutputStream(reportTemplate);
            fos.write(data);
            fos.close();            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate.getAbsolutePath());
            JasperPrint jasperprint=JasperFillManager.fillReport(jasperReport, hashMap, conn);
            JasperExportManager.exportReportToHtmlFile(jasperprint, reportDest);
            JasperViewer.viewReport(jasperprint, false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }                
    
    public void buildReportTripleParamPengembalian(String vName, Date tanggalAwal, Date tanggalAkhir, String supplier){
        Blob reportSource=null;
        byte [] data = null;
        String reportDest="";        
        
        koneksi();
        try {
            Statement s = conn.createStatement();
            ResultSet rs=s.executeQuery("SELECT pathsource, pathdest FROM report WHERE nama='"+vName+"'");
            if(rs.next()){
                reportSource = rs.getBlob("pathsource");
                data = reportSource.getBytes(1, (int)reportSource.length());
                reportDest = rs.getString("pathdest");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error karena = "+ex);
        }        
            HashMap<String, Object> hashMap = new HashMap<String, Object>();            
            hashMap.put("tanggal1", tanggalAwal);
            hashMap.put("tanggal2", tanggalAkhir);                                
            hashMap.put("supplier", supplier);                                
        try{
            File reportTemplate = new File("D:\\a.jrxml");
            FileOutputStream fos = new FileOutputStream(reportTemplate);
            fos.write(data);
            fos.close();            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate.getAbsolutePath());
            JasperPrint jasperprint=JasperFillManager.fillReport(jasperReport, hashMap, conn);
            JasperExportManager.exportReportToHtmlFile(jasperprint, reportDest);
            JasperViewer.viewReport(jasperprint, false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }         
    
   public void buildReportLimaParamInsentif(String vName, String penjual, Date tanggalAwal, Date tanggalAkhir, int persentase){
        Blob reportSource=null;
        byte [] data = null;
        String reportDest="";        
        
        koneksi();
        try {
            Statement s = conn.createStatement();
            ResultSet rs=s.executeQuery("SELECT pathsource, pathdest FROM report WHERE nama='"+vName+"'");
            if(rs.next()){
                reportSource = rs.getBlob("pathsource");
                data = reportSource.getBytes(1, (int)reportSource.length());
                reportDest = rs.getString("pathdest");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error karena = "+ex);
        }        
            HashMap<String, Object> hashMap = new HashMap<String, Object>();            
            hashMap.put("penjual", penjual);
            hashMap.put("Tanggal", tanggalAwal);
            hashMap.put("tanggal2", tanggalAkhir);                                
            hashMap.put("persentase", persentase);
        try{
            File reportTemplate = new File("D:\\a.jrxml");
            FileOutputStream fos = new FileOutputStream(reportTemplate);
            fos.write(data);
            fos.close();            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate.getAbsolutePath());
            JasperPrint jasperprint=JasperFillManager.fillReport(jasperReport, hashMap, conn);
            JasperExportManager.exportReportToHtmlFile(jasperprint, reportDest);
            JasperViewer.viewReport(jasperprint, false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }             
    
    public void buildReportSingleParam(String vName, String kategori){
        Blob reportSource=null;
        byte [] data = null;
        String reportDest="";        
        
        koneksi();
        try {
            Statement s = conn.createStatement();
            ResultSet rs=s.executeQuery("SELECT pathsource, pathdest FROM report WHERE nama='"+vName+"'");
            if(rs.next()){
                reportSource = rs.getBlob("pathsource");
                data = reportSource.getBytes(1, (int)reportSource.length());
                reportDest = rs.getString("pathdest");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error karena = "+ex);
        }        
            HashMap<String, Object> hashMap = new HashMap<String, Object>();            
            hashMap.put("kategori", kategori);
        try{            
            File reportTemplate = new File("D:\\a.jrxml");
            FileOutputStream fos = new FileOutputStream(reportTemplate);
            fos.write(data);
            fos.close();            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate.getAbsolutePath());
            JasperPrint jasperprint=JasperFillManager.fillReport(jasperReport, hashMap, conn);
            JasperExportManager.exportReportToHtmlFile(jasperprint, reportDest);
            JasperViewer.viewReport(jasperprint, false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }            
    
    
    public void buildReportQuadParam(String vName, Date tanggalAwal, Date tanggalAkhir, String supplier, String status){
        Blob reportSource=null;
        byte [] data = null;
        String reportDest="";        
        
        koneksi();
        try {
            Statement s = conn.createStatement();
            ResultSet rs=s.executeQuery("SELECT pathsource, pathdest FROM report WHERE nama='"+vName+"'");
            if(rs.next()){
                reportSource = rs.getBlob("pathsource");
                data = reportSource.getBytes(1, (int)reportSource.length());
                reportDest = rs.getString("pathdest");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error karena = "+ex);
        }        
            HashMap<String, Object> hashMap = new HashMap<String, Object>();                        
            hashMap.put("tanggal1", tanggalAwal);
            hashMap.put("tanggal2", tanggalAkhir);                                
            hashMap.put("supplier", supplier);
            hashMap.put("status", status);
        try{
            File reportTemplate = new File("D:\\a.jrxml");
            FileOutputStream fos = new FileOutputStream(reportTemplate);
            fos.write(data);
            fos.close();            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate.getAbsolutePath());
            JasperPrint jasperprint=JasperFillManager.fillReport(jasperReport, hashMap, conn);
            JasperExportManager.exportReportToHtmlFile(jasperprint, reportDest);
            JasperViewer.viewReport(jasperprint, false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }                    
    
}

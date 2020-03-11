package com.wissensalt.sinarelektronik.chart.core;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Administrator
 */
public class Core {

    public Core() {
    }

    public JFreeChart generatePieChart(String title, String [] names, Integer [] values){
        DefaultPieDataset dataSet = new DefaultPieDataset();
        for(int a=0; a<names.length; a++){
            dataSet.setValue(names[a], values[a]);
        }
        
        JFreeChart chart = ChartFactory.createPieChart(title, dataSet, true, true, false);
 
        return chart;        
    }
    
   public JFreeChart generateBarChart(String title, String[] names, Integer [] values) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for(int a=0; a<names.length; a++){
            dataSet.setValue(values[a], "Jumlah Transaksi", names[a]);
        }
        /*
        dataSet.setValue(791, "Population", "1750 AD");
        dataSet.setValue(978, "Population", "1800 AD");
        dataSet.setValue(1262, "Population", "1850 AD");
        dataSet.setValue(1650, "Population", "1900 AD");
        dataSet.setValue(2519, "Population", "1950 AD");
        dataSet.setValue(6070, "Population", "2000 AD");*/
 
        JFreeChart chart = ChartFactory.createBarChart(title, "Karyawan", "Jumlah Transaksi",dataSet, PlotOrientation.VERTICAL, false, true, false);
 
        return chart;
    }
   
public void writeChartToPDF(JFreeChart chart, int width, int height, String fileName) {
    PdfWriter writer = null;
 
    Document document = new Document();
 
    try {
        writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();
        PdfContentByte contentByte = writer.getDirectContent();
        PdfTemplate template = contentByte.createTemplate(width, height);
        Graphics2D graphics2d = template.createGraphics(width, height,new DefaultFontMapper());
        Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width, height);
 
        chart.draw(graphics2d, rectangle2d);
         
        graphics2d.dispose();
        contentByte.addTemplate(template, 0, 0);
 
    } catch (Exception e) {
        e.printStackTrace();
    }
    document.close();
}

public void openFile(String file){
        File openingFile = new File(file);
        if(openingFile.exists()){
            if(Desktop.isDesktopSupported()){
                try {
                    Desktop.getDesktop().open(openingFile);
                } catch (IOException ex) {
                    Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                System.out.println("Desktop is not supported");
            }
        }else{
            System.out.println("File is not Exist");
        }
        System.out.println("Done");
    }    

}

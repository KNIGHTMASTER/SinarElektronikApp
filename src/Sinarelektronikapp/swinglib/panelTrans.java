/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.swinglib;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import javax.swing.JPanel;

/**
 *
 * @author fauzi
 */
public class panelTrans extends JPanel{
private Color warna;
public panelTrans(){
    setOpaque(false);
    warna=new Color(getBackground().getRed(), getBackground().getGreen(), getBackground().getBlue(), 90);
}

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        warna=new Color(getBackground().getRed(), getBackground().getGreen(), getBackground().getBlue(), 90);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D grafis=(Graphics2D)g.create();        
        grafis.setColor(warna);
        grafis.fillRect(0, 0, getWidth(), getHeight());        
        grafis.dispose();
    }
    
}

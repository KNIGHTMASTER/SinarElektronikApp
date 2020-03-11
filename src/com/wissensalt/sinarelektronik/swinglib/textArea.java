/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.swinglib;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author fauzi
 */
public class textArea extends JTextArea{
private Paint glass=null;
    public textArea(){
        setForeground(Color.WHITE);
    setFont(new Font("Traditional Arabic", Font.BOLD, 16));
    setOpaque(false);
    setBorder(new EmptyBorder(3, 3, 3, 3));
    setEditable(true);
    setEnabled(true);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D grafis=(Graphics2D)g.create();
        Shape kotak=new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 50, 50);
        grafis.fill(kotak);
        glass=new GradientPaint(0, 0, Color.BLACK, getWidth(), getHeight(), Color.GREEN);
        grafis.draw(kotak);
    }
  
    
}

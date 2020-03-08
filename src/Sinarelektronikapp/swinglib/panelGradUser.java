/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.swinglib;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import javax.swing.JPanel;

/**
 *
 * @author fauzi
 */
public class panelGradUser extends JPanel{

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D grafis=(Graphics2D)g;        
        Paint cat=new GradientPaint(0, 0, Color.GRAY, getWidth(), getHeight(), Color.WHITE);
        Shape kotak=new Rectangle(0, 0, getWidth(), getHeight());
        grafis.setPaint(cat);
        grafis.fill(kotak);        
        grafis.draw(kotak);
    }
    
}

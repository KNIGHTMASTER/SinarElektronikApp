/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.swingResource;

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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author fauzi
 */
public class texfil extends JTextField{
    private boolean fokus;
    private Paint cat;
public texfil(){
    setForeground(Color.WHITE);
    setFont(new Font("Traditional Arabic", Font.BOLD, 16));
    setOpaque(false);
    setBorder(new EmptyBorder(3, 3, 3, 3));
    addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                setFokus(true);
            }
            @Override
            public void focusLost(FocusEvent e) {
                setFokus(false);
            }
                    
    });
}
public boolean isFokus(){
    return fokus;
}
public void setFokus(boolean fokus){
    this.fokus=fokus;
    repaint();
}
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D grafis=(Graphics2D)g;
        Shape kotak=new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 50, 50);
        if(isFokus()){
            cat=new GradientPaint(0, 0, Color.WHITE, getWidth(), getHeight(), Color.BLACK);
            setForeground(Color.BLACK);
        }
        else{
            cat=new GradientPaint(0, 0, Color.BLACK, getWidth(), getHeight(), Color.WHITE);
            setForeground(Color.WHITE);
        }
        grafis.setPaint(cat);
        grafis.fill(kotak);
        grafis.draw(kotak);
        super.paintComponent(g);
    }
    
}

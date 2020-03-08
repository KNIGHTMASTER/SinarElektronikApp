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
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

/**
 *
 * @author fauzi
 */
public class button extends JButton{
private Paint gradien;
private Paint glass;
private boolean fokus;
public button(){
    setForeground(Color.WHITE);
    setOpaque(false);
    setContentAreaFilled(false);
    setBorderPainted(false);
    setFocusPainted(false);
    addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                    setFokus(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
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
        Graphics2D grafis=(Graphics2D)g.create();        
        gradien=new GradientPaint(0, 0, Color.WHITE, getWidth(), getHeight(), Color.BLACK);
        Shape rorec=new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 50, 50);
        if(isFokus()){
            glass=new GradientPaint(0, 0, new Color(1F, 1F, 1F, 0F), 0, getHeight(), new Color(1F, 1F, 1F, 0.5F));            
        }
        else{
            glass=new GradientPaint(0, 0, new Color(1F, 1F, 1F, 0.5F), 0, getHeight(), new Color(1F, 1F, 1F, 0F));            
        }
        
        grafis.setPaint(gradien);
        grafis.fill(rorec);
        super.paintComponent(g);        
        grafis.setPaint(glass);
        grafis.fill(rorec);
        grafis.dispose();
    }
    
}

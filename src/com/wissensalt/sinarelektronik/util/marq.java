package com.wissensalt.sinarelektronik.util;

import java.awt.Graphics;
import javax.swing.JLabel;

public class marq extends JLabel {
    public static int LEFT_TO_RIGHT = 1;  
    public static int RIGHT_TO_LEFT = 2;  
    String text;  
    int Option;  
    int Speed;  
    
    public marq(String text, int Option, int Speed) {  
        this.Option = Option;  
        this.Speed = Speed;  
        this.setText(text);  
    }  
    
    public marq(int Option, int Speed) {  
        this.Option = Option;  
        this.Speed = Speed;  
        this.setText("Point Of Sales Sinar Elektronik Copyright © 2013: Sinar Elektronik, Inc");  
    }      
   
    @Override
    protected void paintComponent(Graphics g) {  
        if (Option == LEFT_TO_RIGHT) {  
            g.translate((int) ((System.currentTimeMillis() / Speed) % (getWidth() * 2) - getWidth()), 0);  
        } else if (Option == RIGHT_TO_LEFT) {  
            g.translate((int) (getWidth() - (System.currentTimeMillis() / Speed) % (getWidth() * 2)), 0);  
        }  
        super.paintComponent(g);  
        repaint(5);  
   }
}

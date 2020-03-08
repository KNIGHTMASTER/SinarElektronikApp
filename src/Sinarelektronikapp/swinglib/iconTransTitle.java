/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.swinglib;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author fauzi
 */
public class iconTransTitle extends JPanel{
    private Image ikon;
    
    public iconTransTitle(){
        setOpaque(false);
        ikon=new ImageIcon(getClass().getResource("/fauzi/freeapps/resources/title.png")).getImage();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gambar=(Graphics2D)g.create();
        gambar.setComposite(AlphaComposite.SrcOver.derive(0.9F));
        gambar.drawImage(ikon, 0, 0, null);
    }
    
}

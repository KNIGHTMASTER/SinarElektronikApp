/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.swingResource;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;

/**
 *
 * @author fauzi
 */
public class labeling extends JLabel{
    private Font arabic;
public labeling(){
    this.setOpaque(false);
    this.setForeground(Color.WHITE);    
    arabic=new Font("Traditional Arabic", Font.BOLD, 18);
    this.setFont(arabic);
}        
    
}

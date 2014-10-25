/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp;

import Sinarelektronikapp.view.MainFrame;
import Sinarelektronikapp.view.TestSplahScreen;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


/**
 *
 * @author Fauzi
 */
public class SinarElektronikApp {

    /**
     * @param args the command line arguments
     */
      
    public static void main(final String[] args){
        
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel(new NimbusLookAndFeel());                    
                    //new MainFrame().setVisible(true);
                    TestSplahScreen tss = new TestSplahScreen();
                    tss.main(args);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }

            }
        });        
    }
    
}

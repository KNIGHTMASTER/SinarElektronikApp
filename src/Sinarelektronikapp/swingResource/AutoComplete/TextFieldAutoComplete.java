/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.swingResource.AutoComplete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Fauzi
 */
public class TextFieldAutoComplete extends JTextField {

      private AutoComplete autoComplete;

public AutoComplete getAutoComplete() {
     return autoComplete;
}

public void setAutoComplete(AutoComplete autoComplete) {
     this.autoComplete = autoComplete;
}

public TextFieldAutoComplete() {
     super();
     addActionListener(new ActionListener() {

       @Override
        public void actionPerformed(ActionEvent e) {
        /* caret = posisi text ketika ada perubahan input
         * lengkapnya lihat javaDoc.
         */
         setCaretPosition(getDocument().getLength());
        }
   });
}


         @Override 
         public void replaceSelection(String content) { 
         if(getAutoComplete() == null){ 
             super.replaceSelection(content); }
         else{ 
              try{ 
                   super.replaceSelection(content); 
                   String AllText = getDocument().getText(0, getDocument().getLength()); 
                   if(getAutoComplete().getAutoComplete(AllText) != null){
                      setText(getAutoComplete().getAutoComplete(AllText)
                   ); 
                  // settext 
                  setCaretPosition(getDocument().getLength()); 
                 // tentukan posisi caret 
                  moveCaretPosition(AllText.length()); 
                  // pindahkan caret ke posisi terakhir 
                    } 
              }catch(BadLocationException ble){
                          JOptionPane.showMessageDialog(this, "Terjadi Error dengan pesan \n"+ble.getMessage()); 
              } 
          } 
      } 
}

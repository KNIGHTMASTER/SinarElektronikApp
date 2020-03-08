package Sinarelektronikapp.masterdata.barangbesar.dialog;

import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Fauzi
 */
public class DialogUpdateBulk extends JDialog {

    private List<String> itemsToUpdate;
    
    public void show(int locationX, int locationY) {
        JLabel jLabel = new JLabel();
        String s = "";
        for (String txt: itemsToUpdate) {
            s += txt;
        }
        
        jLabel.setText(s);
        
        
        this.add(jLabel);
        this.setLocation(locationX, locationY);
        this.setModal(true);       
        this.show(true);
    }
    
    private JPanel panelTop() {
        JPanel panelTop = new JPanel();
        return panelTop;
    }
    
    public DialogUpdateBulk() {
        this.setTitle("Update Bulk Barang Besar");
        this.setSize(900, 600);
    }

    public List<String> getItemsToUpdate() {
        return itemsToUpdate;
    }

    public void setItemsToUpdate(List<String> itemsToUpdate) {
        this.itemsToUpdate = itemsToUpdate;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.inventory.model;

import Sinarelektronikapp.inventory.entity.Inventory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class TabelModelInventory extends AbstractTableModel{
    private List<Inventory> list = new ArrayList<>();

    public void setList(List<Inventory> list) {
        this.list = list;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "no";
            case 1: return "kode";
            case 2: return "nama";
            case 3: return "jumlah";             
            case 4: return "subharga";                
            default:return "";
        }
    }            
    
    public boolean add(Inventory e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }
    }

    public Inventory get(int index) {
        return list.get(index);
    }

    public Inventory set(int index, Inventory element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public Inventory remove(int index) {
        try{
            return list.remove(index);
        }finally{
            fireTableRowsDeleted(index, index);
        }
    }

    public boolean removeAll(Collection<?> c) {
        try{
            return list.removeAll(c);
        }finally{
            fireTableDataChanged();
        }            
    }
    
    
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            //case 0 : return list.get(rowIndex).getId();
            //case 1 : return list.get(rowIndex).getUser();
            //case 2 : return list.get(rowIndex).getTanggal();
            //case 3 : return list.get(rowIndex).getJam();
            case 0 : return list.get(rowIndex).getId();
            case 1 : return list.get(rowIndex).getKode();
            case 2 : return list.get(rowIndex).getNama();
            case 3 : return list.get(rowIndex).getJumlah();                    
            case 4 : return list.get(rowIndex).getSubharga();
            default: return null;
        }
    }
    
    

    
}

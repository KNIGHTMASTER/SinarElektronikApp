/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterData.merek.model;

import Sinarelektronikapp.masterData.merek.entity.merek;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class tabelModelMerek extends AbstractTableModel{

    private List<merek> list = new ArrayList<merek>();

    public void setList(List<merek> list) {
        this.list = list;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "id Merek";
            case 1: return "nama Merek";
            default:return "";
        }
    }

    
    
    public boolean add(merek e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }
    }

    public merek get(int index) {
        return list.get(index);
    }

    public merek set(int index, merek element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public merek remove(int index) {
        try{
            return list.remove(index);
        }finally{
            fireTableRowsDeleted(index, index);
        }
    }        
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0 : return list.get(rowIndex).getIdmerek();
            case 1 : return list.get(rowIndex).getNamamerek();
            default: return null;
        }
    }
    
    

}

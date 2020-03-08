/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterdata.penjual.barangkecil.model;

import Sinarelektronikapp.masterdata.penjual.barangkecil.entity.tipe;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class tabelModelTipe extends AbstractTableModel{
    
    private List<tipe> list = new ArrayList<tipe>();

    public void setList(List<tipe> list) {
        this.list = list;
    }

    public boolean add(tipe e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }           
    }

    public tipe get(int index) {
        return list.get(index);
    }

    public tipe set(int index, tipe element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public tipe remove(int index) {
        try{
            return list.remove(index);
        }finally{
            fireTableRowsDeleted(index, index);
        }
    }

    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0 : return "id";
            case 1 : return "nama penjual";
            case 2 : return "insentif";
            default: return null;
        }
    }

    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0 : return list.get(rowIndex).getIdtipe();
            case 1 : return list.get(rowIndex).getNamaTipe();
            case 2 : return list.get(rowIndex).getInsentif();
            default: return null;
        }
    }

}

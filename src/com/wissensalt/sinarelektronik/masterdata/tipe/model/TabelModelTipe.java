package com.wissensalt.sinarelektronik.masterdata.tipe.model;

import com.wissensalt.sinarelektronik.masterdata.tipe.entity.TipeDTO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class TabelModelTipe extends AbstractTableModel{
    
    private List<TipeDTO> list = new ArrayList<>();

    public void setList(List<TipeDTO> list) {
        this.list = list;
    }

    public boolean add(TipeDTO e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }           
    }

    public TipeDTO get(int index) {
        return list.get(index);
    }

    public TipeDTO set(int index, TipeDTO element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public TipeDTO remove(int index) {
        try{
            return list.remove(index);
        }finally{
            fireTableRowsDeleted(index, index);
        }
    }

    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0 : return "id Tipe";
            case 1 : return "nama Tipe";                
            default: return null;
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
            case 0 : return list.get(rowIndex).getIdTipe();
            case 1 : return list.get(rowIndex).getNamaTipe();
            default: return null;
        }
    }

}

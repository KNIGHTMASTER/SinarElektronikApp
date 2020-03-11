package com.wissensalt.sinarelektronik.masterdata.merek.model;

import com.wissensalt.sinarelektronik.dto.MerekDTO;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public class TabelModelMerek extends AbstractTableModel{

    public TabelModelMerek() {
    }

    public TabelModelMerek(List<MerekDTO> list) {
        this.list = list;
    }

    private List<MerekDTO> list;

    public void setList(List<MerekDTO> list) {
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

    
    
    public boolean add(MerekDTO e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }
    }

    public MerekDTO get(int index) {
        return list.get(index);
    }

    public MerekDTO set(int index, MerekDTO element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public MerekDTO remove(int index) {
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
            case 0 : return list.get(rowIndex).getIdMerek();
            case 1 : return list.get(rowIndex).getNamaMerek();
            default: return null;
        }
    }
    
    

}

package com.wissensalt.sinarelektronik.masterdata.user.model;

import com.wissensalt.sinarelektronik.masterdata.user.entity.UserDTO;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class TabelModelUser extends AbstractTableModel{

    private List<UserDTO> list= new ArrayList<>();

    public void setList(List<UserDTO> list) {
        this.list = list;
        fireTableDataChanged();
    }
    
    

    public boolean add(UserDTO e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }
    }

    public UserDTO get(int index) {
        return list.get(index);
    }

    public UserDTO set(int index, UserDTO element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public UserDTO remove(int index) {
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
    public String getColumnName(int column) {
        switch(column){
            case 0 : return "Id ";
            case 1 : return "Nama ";
            case 2 : return "Password";
            case 3 : return "Level";
            case 4 : return "Keterangan";                
            default:return "";
        }
    }

    
    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return list.get(rowIndex).getIdUser();
            case 1: return list.get(rowIndex).getNama();
            case 2: return list.get(rowIndex).getPassword();
            case 3: return list.get(rowIndex).getLevel();
            case 4: return list.get(rowIndex).getKeterangan();
            default: return null;
        }
    }
    
}

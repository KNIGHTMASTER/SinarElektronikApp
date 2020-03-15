package com.wissensalt.sinarelektronik.masterdata.namabarang.model;

import com.wissensalt.sinarelektronik.masterdata.namabarang.entity.NamaBarangDTO;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class TabelModelNamaBarang extends AbstractTableModel{

    public List<NamaBarangDTO> list = new ArrayList<>();

    public List<NamaBarangDTO> getList() {
        return list;
    }

    public void setList(List<NamaBarangDTO> list) {
        this.list = list;
    }

    
    public boolean add(NamaBarangDTO e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }
    }

    public NamaBarangDTO get(int index) {
        return list.get(index);
    }

    public NamaBarangDTO set(int index, NamaBarangDTO element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public NamaBarangDTO remove(int index) {
        try{
            return list.remove(index);
        }finally{
            fireTableRowsDeleted(index, index);
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "id";
            case 1: return "nama barangkecil";
            default:return "";
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
            case 0:return list.get(rowIndex).getId();
            case 1:return list.get(rowIndex).getNamabarang();
            default:return null;
        }
    }
    
}

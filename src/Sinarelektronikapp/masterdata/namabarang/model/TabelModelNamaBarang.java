/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.namabarang.model;

import Sinarelektronikapp.masterdata.namabarang.Entity.NamaBarang;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class TabelModelNamaBarang extends AbstractTableModel{

    public List<NamaBarang> list = new ArrayList<NamaBarang>();

    public List<NamaBarang> getList() {
        return list;
    }

    public void setList(List<NamaBarang> list) {
        this.list = list;
    }

    
    public boolean add(NamaBarang e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }
    }

    public NamaBarang get(int index) {
        return list.get(index);
    }

    public NamaBarang set(int index, NamaBarang element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public NamaBarang remove(int index) {
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

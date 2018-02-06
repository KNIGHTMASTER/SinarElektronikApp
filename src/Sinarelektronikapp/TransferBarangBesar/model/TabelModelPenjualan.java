/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.TransferBarangBesar.model;

import Sinarelektronikapp.TransferBarangBesar.entity.ProsesKasir;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class TabelModelPenjualan extends AbstractTableModel{

    private List<ProsesKasir> list=new ArrayList<>();

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "No";
            case 1: return "Kode";                
            case 2: return "Nama Item";                
            case 3: return "Jml";
            default:return "";
        }
    }        

    public boolean add(ProsesKasir e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }
    }

    public ProsesKasir get(int index) {
        return list.get(index);
    }

    public ProsesKasir set(int index, ProsesKasir element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public ProsesKasir remove(int index) {
        try{
           return list.remove(index);
        }finally{
            fireTableRowsDeleted(index, index);
        }
    }   
        
    public void setList(List<ProsesKasir> list) {
        this.list = list;
        fireTableDataChanged();
    }
        
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:return list.get(rowIndex).getNo();
            case 1:return list.get(rowIndex).getKode();                
            case 2:return list.get(rowIndex).getNama();
            case 3:return list.get(rowIndex).getJml();
            default:return null;
        }
    }
    
}

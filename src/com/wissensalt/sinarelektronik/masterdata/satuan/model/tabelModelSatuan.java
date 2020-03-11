/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wissensalt.sinarelektronik.masterdata.satuan.model;

import com.wissensalt.sinarelektronik.masterdata.satuan.entity.satuan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class tabelModelSatuan extends AbstractTableModel{
    private List<satuan> list = new ArrayList<satuan>();

    public void setList(List<satuan> list) {
        this.list = list;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0 :return "id Satuan";
            case 1 :return "nama Satuan";
            default:return "";
        }
    }    
    
    public boolean add(satuan e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }
    }

    public satuan get(int index) {
        return list.get(index);
    }

    public satuan set(int index, satuan element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public satuan remove(int index) {
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
            case 0 :return list.get(rowIndex).getIdsatuan();
            case 1 :return list.get(rowIndex).getNamasatuan();
            default:return null;
        }
    }

}

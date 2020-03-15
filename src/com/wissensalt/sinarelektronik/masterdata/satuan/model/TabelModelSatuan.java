/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wissensalt.sinarelektronik.masterdata.satuan.model;

import com.wissensalt.sinarelektronik.masterdata.satuan.entity.SatuanDTO;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class TabelModelSatuan extends AbstractTableModel{
    private List<SatuanDTO> list = new ArrayList<SatuanDTO>();

    public void setList(List<SatuanDTO> list) {
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
    
    public boolean add(SatuanDTO e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }
    }

    public SatuanDTO get(int index) {
        return list.get(index);
    }

    public SatuanDTO set(int index, SatuanDTO element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public SatuanDTO remove(int index) {
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
            case 1 :return list.get(rowIndex).getNamaSatuan();
            default:return null;
        }
    }

}

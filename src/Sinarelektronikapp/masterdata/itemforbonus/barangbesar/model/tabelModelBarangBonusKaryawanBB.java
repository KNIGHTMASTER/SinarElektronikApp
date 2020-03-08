/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterdata.itemforbonus.barangbesar.model;

import Sinarelektronikapp.masterdata.itemforbonus.barangbesar.entity.BarangBonusKaryawanBB;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class tabelModelBarangBonusKaryawanBB extends AbstractTableModel{
    
    private List<BarangBonusKaryawanBB> list = new ArrayList<BarangBonusKaryawanBB>();

    public void setList(List<BarangBonusKaryawanBB> list) {
        this.list = list;
    }

    public boolean add(BarangBonusKaryawanBB e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }           
    }

    public BarangBonusKaryawanBB get(int index) {
        return list.get(index);
    }

    public BarangBonusKaryawanBB set(int index, BarangBonusKaryawanBB element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public BarangBonusKaryawanBB remove(int index) {
        try{
            return list.remove(index);
        }finally{
            fireTableRowsDeleted(index, index);
        }
    }

    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0 : return "Kode Barang";
            case 1 : return "Nama Barang";
            case 2 : return "Tipe Barang";
            case 3 : return "Merek Barang";
            default: return null;
        }
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
            case 0 : return list.get(rowIndex).getKodeBarang();
            case 1 : return list.get(rowIndex).getNama();
            case 2 : return list.get(rowIndex).getTipe();
            case 3 : return list.get(rowIndex).getMerek();
            default: return null;
        }
    }

}

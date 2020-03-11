package com.wissensalt.sinarelektronik.transaction.barangbesar.model;

import com.wissensalt.sinarelektronik.transaction.barangbesar.entity.TransaksiPenjualanBB;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class TabelModelTransaksiPenjualanBB extends AbstractTableModel{

    List<TransaksiPenjualanBB> list = new ArrayList<>();

    public List<TransaksiPenjualanBB> getList() {
        return list;
    }

    public void setList(List<TransaksiPenjualanBB> list) {
        this.list = list;
        fireTableDataChanged();
    }
    
    public boolean add(TransaksiPenjualanBB e) {
        try{
            return list.add(e);            
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }
    }

    public TransaksiPenjualanBB get(int index) {
        return list.get(index);
    }

    public TransaksiPenjualanBB set(int index, TransaksiPenjualanBB element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public TransaksiPenjualanBB remove(int index) {
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
            case 1: return "user";
            case 2: return "tanggal";
            case 3: return "jam";
            case 4: return "total";
            case 5: return "penjual";
            default:return null;
        }
    }
    
    
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:return list.get(rowIndex).getIdtransaksi();
            case 1:return list.get(rowIndex).getUser();
            case 2:return list.get(rowIndex).getTanggal();
            case 3:return list.get(rowIndex).getJam();
            case 4:return list.get(rowIndex).getTotal();
            case 5:return list.get(rowIndex).getPenjual();
            default:return null;
        }
    }
    
}

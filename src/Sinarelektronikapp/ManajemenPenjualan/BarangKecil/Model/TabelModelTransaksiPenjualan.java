/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.ManajemenPenjualan.BarangKecil.Model;

import Sinarelektronikapp.ManajemenPenjualan.BarangKecil.Entity.TransaksiPenjualan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class TabelModelTransaksiPenjualan extends AbstractTableModel{

    private List<TransaksiPenjualan> list = new ArrayList<TransaksiPenjualan>();

    public List<TransaksiPenjualan> getList() {
        return list;
    }

    public void setList(List<TransaksiPenjualan> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public boolean add(TransaksiPenjualan e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }
    }

    public TransaksiPenjualan get(int index) {
        return list.get(index);
    }

    public TransaksiPenjualan remove(int index) {
        try{
            return list.remove(index);
        }finally{
            fireTableRowsDeleted(index, index);
        }
    }

    public TransaksiPenjualan set(int index, TransaksiPenjualan element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:return "id";
            case 1:return "user";
            case 2:return "tanggal";
            case 3:return "jam";
            case 4:return "total";
            default:return null;
        }
    }
        
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:return list.get(rowIndex).getIdTransaksi();
            case 1:return list.get(rowIndex).getUser();
            case 2:return list.get(rowIndex).getTanggal();
            case 3:return list.get(rowIndex).getJam();
            case 4:return list.get(rowIndex).getTotal();
            default:return null;
        }
    }
    
}

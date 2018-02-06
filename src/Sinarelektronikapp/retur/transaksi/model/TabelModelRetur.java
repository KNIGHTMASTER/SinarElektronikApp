/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.retur.transaksi.model;

import Sinarelektronikapp.retur.transaksi.entity.Retur;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class TabelModelRetur extends AbstractTableModel{


    private List<Retur> list = new ArrayList<>();

    public void setList(List<Retur> list) {
        this.list = list;
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "no";
            case 1: return "kode";
            case 2: return "nama";
            case 3: return "jumlah";
            case 4: return "harga";
            case 5: return "subharga";
            case 6: return "supplier";
            case 7: return "penukaran";                
            default:return "";
        }
    }            
    
    public boolean add(Retur e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }
    }

    public Retur get(int index) {
        return list.get(index);
    }

    public Retur set(int index, Retur element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public Retur remove(int index) {
        try{
            return list.remove(index);
        }finally{
            fireTableRowsDeleted(index, index);
        }
    }

    public boolean removeAll(Collection<?> c) {
        try{
            return list.removeAll(c);
        }finally{
            fireTableDataChanged();
        }            
    }
    
    
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            //case 0 : return list.get(rowIndex).getId();
            //case 1 : return list.get(rowIndex).getUser();
            //case 2 : return list.get(rowIndex).getTanggal();
            //case 3 : return list.get(rowIndex).getJam();
            case 0 : return list.get(rowIndex).getId();
            case 1 : return list.get(rowIndex).getKode();
            case 2 : return list.get(rowIndex).getNama();
            case 3 : return list.get(rowIndex).getJumlah();
            case 4 : return list.get(rowIndex).getHarga();
            case 5 : return list.get(rowIndex).getSubharga();
            case 6 : return list.get(rowIndex).getSupplier();
            case 7 : return list.get(rowIndex).getPenukaran();
            default: return null;
        }
    }
    
    

    
}

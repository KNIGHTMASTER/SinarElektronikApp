/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.penjualan.barangkecil.garansi.model;

import com.wissensalt.sinarelektronik.penjualan.barangkecil.garansi.entity.Garansi;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class TabelModelGaransi extends AbstractTableModel{

    public List<Garansi> list = new ArrayList<Garansi>();

    public void setList(List<Garansi> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public boolean add(Garansi e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }
    }

    public Garansi remove(int index) {
        try{
            return list.remove(index);
        }finally{
            fireTableRowsDeleted(index, index);
        }             
    }

    public Garansi get(int index) {
            return list.get(index);
    }

    public Garansi set(int index, Garansi element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:return "Id Transaksi";
            case 1:return "Nama Barang";
            case 2:return "Jumlah";
            case 3:return "Masa Akhir";
            case 4:return "Status";
            default:return "";
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
            case 0: return list.get(rowIndex).getIdTransaksi();
            case 1: return list.get(rowIndex).getNamaBarang();
            case 2: return list.get(rowIndex).getJumlah();
            case 3: return list.get(rowIndex).getMasaakhir();
            case 4: return list.get(rowIndex).getStatus();
            default:return null;
        }
        
    }
    
}

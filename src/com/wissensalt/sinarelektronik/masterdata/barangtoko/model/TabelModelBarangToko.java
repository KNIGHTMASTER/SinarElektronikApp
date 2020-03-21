package com.wissensalt.sinarelektronik.masterdata.barangtoko.model;

import com.wissensalt.sinarelektronik.dto.BarangTokoDTO;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class TabelModelBarangToko extends AbstractTableModel{

    private List<BarangTokoDTO> list = new ArrayList<>();

    public void setList(List<BarangTokoDTO> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public boolean add(BarangTokoDTO e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }
    }

    public BarangTokoDTO get(int index) {
            return list.get(index);
    }

    public BarangTokoDTO set(int index, BarangTokoDTO element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public BarangTokoDTO remove(int index) {
        try{
            return list.remove(index);
        }finally{
            fireTableRowsDeleted(index, index);
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0 : return "Id Barang";
            case 1 : return "Nama Barang";
            case 2 : return "Tipe";
            case 3 : return "Merek";
            case 4 : return "Modal";
            case 5 : return "Grosir";
            case 6 : return "Eceran";
            case 7 : return "Stok";
            case 8 : return "Supplier";
            default: return null;
        }
    }


    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0 : return list.get(rowIndex).getIdBarang();
            case 1 : return list.get(rowIndex).getNamaBarang();
            case 2 : return list.get(rowIndex).getTipe();
            case 3 : return list.get(rowIndex).getMerek();
            case 4 : return list.get(rowIndex).getModal();
            case 5 : return list.get(rowIndex).getGrosir();
            case 6 : return list.get(rowIndex).getEceran();
            case 7 : return list.get(rowIndex).getStok();
            case 8 : return list.get(rowIndex).getSupplier();
            default: return null;
        }
    }
}

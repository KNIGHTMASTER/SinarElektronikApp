/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterdata.barangbesar.model;

import Sinarelektronikapp.config.UserLevel;
import Sinarelektronikapp.masterdata.barangbesar.entity.barang;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class tabelModelBarang extends AbstractTableModel{

    private List<barang> list = new ArrayList<>();
    private UserLevel userLevel = new UserLevel();

    public void setList(List<barang> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public boolean add(barang e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }
    }

    public barang get(int index) {
            return list.get(index);
    }

    public barang set(int index, barang element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public barang remove(int index) {
        try{
            return list.remove(index);
        }finally{
            fireTableRowsDeleted(index, index);
        }
    }
    
    @Override
    public String getColumnName(int column) {
        if (userLevel.getUserLevelActive().equals("Karyawan")) {
            switch(column){
                case 0 : return "Id Barang";
                case 1 : return "Nama Barang";
                case 2 : return "Tipe";
                case 3 : return "Merek";
                case 4 : return "Grosir";
                case 5 : return "Eceran";
                case 6 : return "Stok";
                default: return null;
            }
        } else {
            switch(column){
                case 0 : return "Id Barang";
                case 1 : return "Nama Barang";
                case 2 : return "Tipe";
                case 3 : return "Merek";
                case 4 : return "Modal";
                case 5 : return "Grosir";
                case 6 : return "Grosir 2";
                case 7 : return "Eceran";
                case 8 : return "Stok";
                case 9 : return "Supplier";
                default: return null;
            }
        }
    }
        
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return userLevel.getUserLevelActive().equals("Karyawan") ? 7 : 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (userLevel.getUserLevelActive().equals("Karyawan")) {
            switch(columnIndex){
                case 0 : return list.get(rowIndex).getIdBarang();
                case 1 : return list.get(rowIndex).getNamaBarang();
                case 2 : return list.get(rowIndex).getTipe();
                case 3 : return list.get(rowIndex).getMerek();
                case 4 : return list.get(rowIndex).getGrosir2();
                case 5 : return list.get(rowIndex).getEceran();
                case 6 : return list.get(rowIndex).getStok();
                default: return null;
            }
        } else {
            switch(columnIndex){
                case 0 : return list.get(rowIndex).getIdBarang();
                case 1 : return list.get(rowIndex).getNamaBarang();
                case 2 : return list.get(rowIndex).getTipe();
                case 3 : return list.get(rowIndex).getMerek();
                case 4 : return list.get(rowIndex).getModal();
                case 5 : return list.get(rowIndex).getGrosir();
                case 6 : return list.get(rowIndex).getGrosir2();
                case 7 : return list.get(rowIndex).getEceran();
                case 8 : return list.get(rowIndex).getStok();
                case 9 : return list.get(rowIndex).getSupplier();
                default: return null;
            }
        }
    }
}

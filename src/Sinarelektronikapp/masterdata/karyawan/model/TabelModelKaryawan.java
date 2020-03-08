/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.karyawan.model;

import Sinarelektronikapp.masterdata.karyawan.entity.Karyawan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class TabelModelKaryawan extends AbstractTableModel{

    private List<Karyawan> list = new ArrayList<>();

    public List<Karyawan> getList() {
        return list;
    }

    public void setList(List<Karyawan> list) {
        this.list = list;
        fireTableDataChanged();
    }

    
    public boolean add(Karyawan e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }
    }

    public Karyawan get(int index) {
        return list.get(index);
    }

    public Karyawan set(int index, Karyawan element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public Karyawan remove(int index) {
        try{
            return list.remove(index);
        }finally{
            fireTableRowsDeleted(index, index);
        }
    }

    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:return "id";
            case 1:return "nama";
            case 2:return "tanggal_lhr";                
            case 3:return "tempat_lhr";
            case 4:return "alamat";
            case 5:return "telepon";                
            case 6:return "agama";
            case 7:return "status";
            case 8:return "gaji";                                
            default:return "";                
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
            case 0:return list.get(rowIndex).getId();
            case 1:return list.get(rowIndex).getNama();
            case 2:return list.get(rowIndex).getTanggal_lahir();
            case 3:return list.get(rowIndex).getTempat_lahir();
            case 4:return list.get(rowIndex).getAlamat();
            case 5:return list.get(rowIndex).getTelepon();
            case 6:return list.get(rowIndex).getAgama();
            case 7:return list.get(rowIndex).getStatus();
            case 8:return list.get(rowIndex).getGaji();
            default:return null;
        }
    }
    
}

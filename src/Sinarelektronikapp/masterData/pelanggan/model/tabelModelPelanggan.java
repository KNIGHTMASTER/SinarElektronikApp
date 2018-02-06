/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterData.pelanggan.model;

import Sinarelektronikapp.masterData.pelanggan.entity.pelanggan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class tabelModelPelanggan extends AbstractTableModel{

    private List<pelanggan> list=new ArrayList<pelanggan>();

    public void setList(List<pelanggan> list) {
        this.list = list;
        fireTableDataChanged();
    }
        
    public boolean add(pelanggan e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }
    }

    public pelanggan get(int index) {
            return list.get(index);
    }

    public pelanggan set(int index, pelanggan element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public pelanggan remove(int index) {
        try{
            return list.remove(index);
        }finally{
            fireTableRowsDeleted(index, index);
        }      
    
    }
    
    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "Id Pelanggan";
            case 1: return "Nama";
            case 2: return "ALamat";
            case 3: return "kota";
            case 4: return "Propinsi";
            case 5: return "Kode Post";
            case 6: return "Telepon";
            case 7: return "Fax";
            case 8: return "Kontak";
            case 9: return "Keterangan";                
            default: return "";
        }
    }
    
    
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return list.get(rowIndex).getIdpelanggan();
            case 1: return list.get(rowIndex).getNama();
            case 2: return list.get(rowIndex).getAlamat();
            case 3: return list.get(rowIndex).getKota();
            case 4: return list.get(rowIndex).getPropinsi();
            case 5: return list.get(rowIndex).getKodePost();
            case 6: return list.get(rowIndex).getTelepon();
            case 7: return list.get(rowIndex).getFax();
            case 8: return list.get(rowIndex).getKontakPerson();
            case 9: return list.get(rowIndex).getCatatan();
            default:return null;
        }
    }
    
}

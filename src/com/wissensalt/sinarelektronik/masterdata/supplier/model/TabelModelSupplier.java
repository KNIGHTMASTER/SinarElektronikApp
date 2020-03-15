package com.wissensalt.sinarelektronik.masterdata.supplier.model;

import com.wissensalt.sinarelektronik.masterdata.supplier.entity.SupplierDTO;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class TabelModelSupplier extends AbstractTableModel{

    private List<SupplierDTO> list = new ArrayList<SupplierDTO>();

    public void setList(List<SupplierDTO> list) {
        this.list = list;
        fireTableDataChanged();
    }        

    public boolean add(SupplierDTO e) {
        try{
            return list.add(e);
        }finally{
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
        }        
    }

    public SupplierDTO get(int index) {
        return list.get(index);
    }

    public SupplierDTO set(int index, SupplierDTO element) {
        try{
            return list.set(index, element);
        }finally{
            fireTableRowsUpdated(index, index);
        }
    }

    public SupplierDTO remove(int index) {
        try{
            return list.remove(index);
        }finally{
            fireTableRowsDeleted(index, index);
        }
    }

    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0 : return "id";
            case 1 : return "nama";
            case 2 : return "alamat";
            case 3 : return "kota";
            case 4 : return "propinsi";
            case 5 : return "kode post";
            case 6 : return "telepon";
            case 7 : return "fax";
            case 8 : return "bank";
            case 9 : return "rekening";
            case 10 : return "atas nama";
            case 11 : return "kontak";
            case 12 : return "email";
            case 13 : return "note";                
            default: return "";
        }
    }
    
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 14;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0 : return list.get(rowIndex).getIdsupplier();
            case 1 : return list.get(rowIndex).getNama();
            case 2 : return list.get(rowIndex).getAlamat();
            case 3 : return list.get(rowIndex).getKota();
            case 4 : return list.get(rowIndex).getPropinsi();
            case 5 : return list.get(rowIndex).getKodePost();
            case 6 : return list.get(rowIndex).getTelepon();
            case 7 : return list.get(rowIndex).getFax();
            case 8 : return list.get(rowIndex).getBank();
            case 9 : return list.get(rowIndex).getNomorRek();
            case 10 : return list.get(rowIndex).getAtasNama();
            case 11 : return list.get(rowIndex).getKontakPerson();
            case 12 : return list.get(rowIndex).getEmail();
            case 13 : return list.get(rowIndex).getNote();
            default: return null;
        }
    }
    
}

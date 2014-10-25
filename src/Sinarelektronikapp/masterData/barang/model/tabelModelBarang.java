/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sinarelektronikapp.masterData.barang.model;

import Sinarelektronikapp.masterData.barang.entity.barang;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fauzi
 */
public class tabelModelBarang extends AbstractTableModel{

    private List<barang> list = new ArrayList<>();

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
        switch(column){
            case 0 : return "Id Barang";
            //case 1 : return "Id Barcode";
            case 1 : return "Nama Barang";
            case 2 : return "Tipe";                
            case 3 : return "Merek";
            case 4 : return "Modal";
            case 5 : return "Grosir";
            case 6 : return "Eceran";
            //case 6 : return "Satuan";
            case 7 : return "Stok";                
            //case 8 : return "Stok Minimum";
            case 8 : return "Supplier";                
            case 9: return "Kategori";
            //case 8: return "Keterangan";
            //case 11: return "path";
            default: return null;
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
            case 0 : return list.get(rowIndex).getIdBarang();
            //case 1 : return list.get(rowIndex).getIdBarcode();                
            case 1 : return list.get(rowIndex).getNamaBarang();                
            case 2 : return list.get(rowIndex).getTipe();
            case 3 : return list.get(rowIndex).getMerek();
            case 4 : return list.get(rowIndex).getHargamodal();
            case 5 : return list.get(rowIndex).getGrosir();
            case 6 : return list.get(rowIndex).getEceran();
            //case 6 : return list.get(rowIndex).getSatuan();
            case 7 : return list.get(rowIndex).getStok();
            //case 8 : return list.get(rowIndex).getStokMinimum();
            case 8 : return list.get(rowIndex).getSupplier();
            case 9 : return list.get(rowIndex).getKategori();
            //case 8 : return list.get(rowIndex).getKeterangan();
            //case 11 : return list.get(rowIndex).getPathGambar();
            default: return null;
        }
    }


}

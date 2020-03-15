package com.wissensalt.sinarelektronik.masterdata.barangkecil.controller;

import com.wissensalt.sinarelektronik.dao.BarangKecilDAO;
import com.wissensalt.sinarelektronik.dao.impl.BarangKecilDAOImpl;
import com.wissensalt.sinarelektronik.inventory.barangkecil.view.JIFInventoryBarangKecilKecil;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.model.BarangKecilModel;
import com.wissensalt.sinarelektronik.masterdata.barangkecil.view.BarangKecilView;
import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangkecil.view.JIFBarangKecilBonusKaryawanBKView;
import com.wissensalt.sinarelektronik.penjualan.barangkecil.view.JIFKasirView;
import com.wissensalt.sinarelektronik.retur.barangkecil.view.JIFReturView2;
import com.wissensalt.sinarelektronik.retur.barangkecil.view.ProsesRetur;

import javax.swing.*;

/**
 *
 * @author Fauzi
 */
public class BarangController {
    private BarangKecilModel model;
    private BarangKecilDAO barangKecilDAO;

    public BarangController() {
        barangKecilDAO = new BarangKecilDAOImpl();
    }

    public void setModel(BarangKecilModel model) {
        this.model = model;
    }

    public void resetBarang(BarangKecilView view){
        model.resetBarang();
    }

    public void deleteBarang(BarangKecilView view){
        if(view.getTabelBarang().getSelectedRowCount()<1){
            JOptionPane.showMessageDialog(view, "pilih data terlebih dahulu");
            return;
        }else{
            if(JOptionPane.showConfirmDialog(view, "apakah pelanggan dengan id "+view.getTabelBarang().getValueAt(view.getTabelBarang().getSelectedRow(), 0) +" akan dihapus ?") == JOptionPane.YES_OPTION){
                String id=view.getTabelBarang().getValueAt(view.getTabelBarang().getSelectedRow(), 0).toString();                
                model.setIdBarang(id);                
                barangKecilDAO.deleteByString(id);
                model.deleteBarang();
                JOptionPane.showMessageDialog(view, "Success delete barang kecil");
            }
        }
    }
    
    private void findById(String kataKunci) {        
        model.findByField(barangKecilDAO.getBarangbyId(kataKunci));
    }
    
    private void findByBarcode(String kataKunci) {
        model.findByField(barangKecilDAO.getBarangbyBarcode(kataKunci));
    }
    
    private void findByNama(String kataKunci) {
        model.findByField(barangKecilDAO.getBarangbyNama(kataKunci));
    }
    
    private void findByTipe(String kataKunci) {
        model.findByField(barangKecilDAO.getBarangbyTipe(kataKunci));
    }
    
    private void findByMerek(String kataKunci) {
        model.findByField(barangKecilDAO.getBarangbyMerek(kataKunci));
    }
    
    private void findByHarga(String kataKunci) {
        model.findByField(barangKecilDAO.getBarangbyHarga(kataKunci));
    }
    
    private void findBySatuan(String kataKunci) {
        model.findByField(barangKecilDAO.getBarangbySatuan(kataKunci));
    }
    
    private void findByStok(String kataKunci) {
        model.findByField(barangKecilDAO.getBarangbyStok(kataKunci));
    }
       
    private void findByStokMin(String kataKunci) {
        model.findByField(barangKecilDAO.getBarangbyStokMin(kataKunci));
    }
    
    private void findBySupplier(String kataKunci) {
        model.findByField(barangKecilDAO.getBarangbySupplier(kataKunci));
    }
    
    private void findByKeterangang(String kataKunci) {
        model.findByField(barangKecilDAO.getBarangbyKeterangan(kataKunci));
    }
    
    private void cari(String kataKunci, String berdasarkan) {
        switch(berdasarkan){
            case "id barangkecil" : findById(kataKunci);break;
            case "id barcode" : findByBarcode(kataKunci);break;
            case "nama barangkecil" : findByNama(kataKunci);break;
            case "tipe" : findByTipe(kataKunci);break;
            case "merek" : findByMerek(kataKunci);break;
            case "harga" : findByHarga(kataKunci);break;
            case "satuan" : findBySatuan(kataKunci);break;
            case "stok" : findByStok(kataKunci);break;
            case "stok minimum" : findByStokMin(kataKunci);break;
            case "supplier" : findBySupplier(kataKunci);break;
            case "keterangan" : findByKeterangang(kataKunci);break;
            default:;
        }
    }
    
    public void cari(BarangKecilView view, BarangKecilView v) {
        String kataKunci = view.getTxtKataKunci().getText();
        String berdasarkan = v.getCmbCari().getSelectedItem().toString();
        

    }

    public void cari(JIFReturView2  view, BarangKecilView v) {
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari().getSelectedItem().toString();
        cari(kataKunci, berdasarkan);
    }

    public void cari6(JIFReturView2  view, JIFReturView2 v) {
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        cari(kataKunci, berdasarkan);
    }

    public void cariProsesRetur(ProsesRetur  view, ProsesRetur v) {
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        cari(kataKunci, berdasarkan);
    }


    public void cari(JIFKasirView view, JIFKasirView v) {
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        cari(kataKunci, berdasarkan);
    }

    public void cariBarangBonusKaryawan(JIFBarangKecilBonusKaryawanBKView view, JIFBarangKecilBonusKaryawanBKView v) {
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        cari(kataKunci, berdasarkan);
    }   

    public void cari3(JIFInventoryBarangKecilKecil view, JIFInventoryBarangKecilKecil v) {
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        cari(kataKunci, berdasarkan);
    }

    private void sort(String berdasarkan) {
        switch(berdasarkan){
            case "id barangkecil" : model.sortByField(barangKecilDAO.sortBarangbyId());break;
            case "nama barangkecil" : model.sortByField(barangKecilDAO.sortBarangbyNama());break;
            case "tipe" : model.sortByField(barangKecilDAO.sortBarangbyTipe());break;
            case "merek" : model.sortByField(barangKecilDAO.sortBarangbyMerek());break;
            case "harga" : model.sortByField(barangKecilDAO.sortBarangbyHarga());break;
            case "stok" : model.sortByField(barangKecilDAO.sortBarangbyStok());break;
            case "stok minimum" : model.sortByField(barangKecilDAO.sortBarangbyStokMin());break;
            case "supplier" : model.sortByField(barangKecilDAO.sortBarangbySupplier());break;
            default:;
        }
    }
    
    public void sort5(JIFReturView2 view) {
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        sort(berdasarkan);
    }
    
    public void sortProsesRetur(ProsesRetur view) {
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        sort(berdasarkan);
    }


    public void sort(BarangKecilView view) {
        String berdasarkan = view.getCmbUrut().getSelectedItem().toString();
        sort(berdasarkan);
    }

    public void sort(JIFKasirView view) {
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        sort(berdasarkan);
    }

    public void sortBarangBonusKaryawan(JIFBarangKecilBonusKaryawanBKView view){
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        sort(berdasarkan);
    }    
    
    public void sort(JIFInventoryBarangKecilKecil view){
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        sort(berdasarkan);
    }
}

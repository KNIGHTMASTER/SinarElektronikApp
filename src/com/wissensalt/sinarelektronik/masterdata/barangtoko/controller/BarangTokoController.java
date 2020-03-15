package com.wissensalt.sinarelektronik.masterdata.barangtoko.controller;

import com.wissensalt.sinarelektronik.dao.BarangTokoDAO;
import com.wissensalt.sinarelektronik.dao.impl.BarangTokoDAOImpl;
import com.wissensalt.sinarelektronik.inventory.barangbesar.view.JIFInventoryBB;
import com.wissensalt.sinarelektronik.masterdata.barangtoko.model.BarangTokoModel;
import com.wissensalt.sinarelektronik.masterdata.barangtoko.view.BarangTokoView;
import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangbesar.view.JIFBarangBesarBonusKaryawanBBView;
import com.wissensalt.sinarelektronik.penjualan.barangbesar.view.JIFKasirView2;
import com.wissensalt.sinarelektronik.retur.barangbesar.view.JIFReturViewBarangTokoBesar;

import javax.swing.*;

/**
 *
 * @author Fauzi
 */
public class BarangTokoController {
    private BarangTokoModel barangTokoModel;
    private final BarangTokoDAO barangTokoDAO;

    public BarangTokoController() {
        barangTokoDAO = new BarangTokoDAOImpl();
    }

    public void setModel(BarangTokoModel model) {
        this.barangTokoModel = model;
    }

    public void resetBarang(BarangTokoView view){
        barangTokoModel.resetBarang();
    }

    public void deleteBarang(BarangTokoView view){
        if(view.getTabelBarang().getSelectedRowCount()<1){
            JOptionPane.showMessageDialog(view, "pilih data terlebih dahulu");
        }else{
            if(JOptionPane.showConfirmDialog(view, "apakah pelanggan dengan id "+view.getTabelBarang().getValueAt(view.getTabelBarang().getSelectedRow(), 0) +" akan dihapus ?") == JOptionPane.YES_OPTION){
                String id=view.getTabelBarang().getValueAt(view.getTabelBarang().getSelectedRow(), 0).toString();
                barangTokoDAO.deleteByString(id);
                barangTokoModel.deleteBarang();
                JOptionPane.showMessageDialog(view, "Success delete Barang");
            }
        }
    }

    private void cari(String kataKunci, String berdasarkan) {
        switch(berdasarkan){
            case "id barangkecil" : barangTokoModel.findByField(barangTokoDAO.getBarangbyId(kataKunci));break;
            case "nama barangkecil" : barangTokoModel.findByField(barangTokoDAO.getBarangbyNama(kataKunci));break;
            case "tipe" : barangTokoModel.findByField(barangTokoDAO.getBarangbyTipe(kataKunci));break;
            case "merek" : barangTokoModel.findByField(barangTokoDAO.getBarangbyMerek(kataKunci));break;
            case "harga" : barangTokoModel.findByField(barangTokoDAO.getBarangbyHarga(kataKunci));break;
            case "stok" : barangTokoModel.findByField(barangTokoDAO.getBarangbyStok(kataKunci));break;
            case "stok minimum" : barangTokoModel.findByField(barangTokoDAO.getBarangbyStokMin(kataKunci));break;
            case "supplier" : barangTokoModel.findByField(barangTokoDAO.getBarangbySupplier(kataKunci));break;
            case "keterangan" : barangTokoModel.findByField(barangTokoDAO.getBarangbyKeterangan(kataKunci));break;
            default:
        }
    }

    public void cari(BarangTokoView view, BarangTokoView v) {
        String kataKunci = view.getTxtKataKunci().getText();
        String berdasarkan = v.getCmbCari().getSelectedItem().toString();
        cari(kataKunci, berdasarkan);

    }

    public void cari4(JIFReturViewBarangTokoBesar view, JIFReturViewBarangTokoBesar v) {
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        cari(kataKunci, berdasarkan);
    }

    public void cari6(JIFInventoryBB view, JIFInventoryBB v) {
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        cari(kataKunci, berdasarkan);
    }

    private void sort(String berdasarkan) {
        switch(berdasarkan){
            case "id barangkecil" : barangTokoModel.sortByField(barangTokoDAO.sortBarangbyId());break;
            case "nama barangkecil" : barangTokoModel.sortByField(barangTokoDAO.sortBarangbyNama());break;
            case "tipe" : barangTokoModel.sortByField(barangTokoDAO.sortBarangbyTipe());break;
            case "merek" : barangTokoModel.sortByField(barangTokoDAO.sortBarangbyMerek());break;
            case "harga" : barangTokoModel.sortByField(barangTokoDAO.sortBarangbyHarga());break;
            case "stok" : barangTokoModel.sortByField(barangTokoDAO.sortBarangbyStok());break;
            case "stok minimum" : barangTokoModel.sortByField(barangTokoDAO.sortBarangbyStokMin());break;
            case "supplier" : barangTokoModel.sortByField(barangTokoDAO.sortBarangbySupplier());break;
            default:
        }
    }
    public void sort(BarangTokoView view) {
        String berdasarkan = view.getCmbUrut().getSelectedItem().toString();
        sort(berdasarkan);
    }

    public void sort2(BarangTokoView view) {
        String berdasarkan = view.getCmbUrut().getSelectedItem().toString();
        sort(berdasarkan);
    }

    public void sort(JIFKasirView2 view) {
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        sort(berdasarkan);
    }

    public void sortBBKBB(JIFBarangBesarBonusKaryawanBBView view) {
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        sort(berdasarkan);
    }

    public void sort(JIFReturViewBarangTokoBesar view) {
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        sort(berdasarkan);
    }


    public void sort6(JIFInventoryBB view) {
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        sort(berdasarkan);
    }


    public void cari(JIFKasirView2 view, JIFKasirView2 v) {
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        cari(kataKunci, berdasarkan);
    }


    public void cari7(JIFReturViewBarangTokoBesar view, JIFReturViewBarangTokoBesar v) {
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        cari(kataKunci, berdasarkan);
    }
}

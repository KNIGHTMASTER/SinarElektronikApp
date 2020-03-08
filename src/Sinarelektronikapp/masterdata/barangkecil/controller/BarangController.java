package Sinarelektronikapp.masterdata.barangkecil.controller;

import Sinarelektronikapp.inventory.barangkecil.view.JIFInventory;
import Sinarelektronikapp.masterdata.barangkecil.entity.barang;
import Sinarelektronikapp.masterdata.barangkecil.error.BarangException;
import Sinarelektronikapp.masterdata.barangkecil.model.barangModel;
import Sinarelektronikapp.masterdata.barangkecil.view.BarangView;
import Sinarelektronikapp.masterdata.itemforbonus.barangkecil.view.JIFBarangBonusKaryawanBKView;
import Sinarelektronikapp.penjualan.barangkecil.view.JIFKasirView;
import Sinarelektronikapp.retur.barangkecil.view.JIFReturView2;
import Sinarelektronikapp.retur.barangkecil.view.ProsesRetur;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class BarangController {
    private barangModel model;

    public BarangController(barangModel model) {
        this.model = model;
    }

    public BarangController() {
        
    }

    public void setModel(barangModel model) {
        this.model = model;
    }    
        
    public void resetBarang(BarangView view){
        model.resetBarang();
    }
    
    
    private barang barangEntity;

    public barang getBarangEntity() {
        return barangEntity;
    }      
  public void deleteBarang(BarangView view){
        if(view.getTabelBarang().getSelectedRowCount()<1){
            JOptionPane.showMessageDialog(view, "pilih data terlebih dahulu");
            return;
        }else{
        if(JOptionPane.showConfirmDialog(view, "apakah pelanggan dengan id "+view.getTabelBarang().getValueAt(view.getTabelBarang().getSelectedRow(), 0) +" akan dihapus ?") == JOptionPane.YES_OPTION){
                String id=view.getTabelBarang().getValueAt(view.getTabelBarang().getSelectedRow(), 0).toString();
                JOptionPane.showMessageDialog(view, "Id  = "+id);
                model.setIdBarang(id);
                JOptionPane.showMessageDialog(view, "Sudah di set Id Barang");
                try {
                    model.deleteBarang();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BarangException ex) {
                    Logger.getLogger(BarangController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    JOptionPane.showMessageDialog(view, "Sudah di delete barangkecil");
            }
        }
    }
    public void cari(BarangView  view, BarangView v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci().getText();
        String berdasarkan = v.getCmbCari().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }
        
    }
    
    public void cari(JIFReturView2  view, BarangView v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }
        
    }
    
    public void cari6(JIFReturView2  view, JIFReturView2 v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }
        
    }    
    
    public void cariProsesRetur(ProsesRetur  view, ProsesRetur v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }
        
    }        
    
    
    public void cari(JIFKasirView view, JIFKasirView v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }
    }    
    
    public void cariBarangBonusKaryawan(JIFBarangBonusKaryawanBKView view, JIFBarangBonusKaryawanBKView v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }
    }        
        
/*    public void cari2(ReturView view, ReturView v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }                
    }    */
    
    public void cari3(JIFInventory view, JIFInventory v) throws SQLException, BarangException{
        String kataKunci = view.getTxtKataKunci1().getText();
        String berdasarkan = v.getCmbCari1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.searchBarangbyId(kataKunci);break;
            case "id barcode" : model.searchBarangbyBarcode(kataKunci);break;
            case "nama barangkecil" : model.searchBarangbyNama(kataKunci);break;
            case "tipe" : model.searchBarangbyTipe(kataKunci);break;
            case "merek" : model.searchBarangbyMerek(kataKunci);break;
            case "harga" : model.searchBarangbyHarga(kataKunci);break;
            case "satuan" : model.searchBarangbySatuan(kataKunci);break;
            case "stok" : model.searchBarangbyStok(kataKunci);break;
            case "stok minimum" : model.searchBarangbyStokMin(kataKunci);break;
            case "supplier" : model.searchBarangbySupplier(kataKunci);break;
            case "keterangan" : model.searchBarangbyKeterangan(kataKunci);break;
            default:;
        }                
    }        

    public void sort5(JIFReturView2 view) throws SQLException, BarangException{        
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            //case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            //case "satuan" : model.sortBarangbySatuan();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            //case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
    }           
    }        
    

    /**
     *
     * @param view
     * @throws SQLException
     * @throws BarangException
     */
    public void sortProsesRetur(ProsesRetur view) throws SQLException, BarangException{        
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            //case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            //case "satuan" : model.sortBarangbySatuan();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            //case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
        }            
    }
    
    
    public void sort(BarangView view) throws SQLException, BarangException{        
        String berdasarkan = view.getCmbUrut().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            //case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            //case "satuan" : model.sortBarangbySatuan();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            //case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
    }
    
    }
    
    public void sort(JIFKasirView view) throws SQLException, BarangException{
        
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            case "satuan" : model.sortBarangbySatuan();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
    }    
    }
    
    public void sortBarangBonusKaryawan(JIFBarangBonusKaryawanBKView view) throws SQLException, BarangException{
        
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            case "satuan" : model.sortBarangbySatuan();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
    }    
    }    
    
/*    public void sort(ReturView view) throws SQLException, BarangException{
        
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            case "satuan" : model.sortBarangbySatuan();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
    }    
    }    */
    
    public void sort(JIFInventory view) throws SQLException, BarangException{
        
        String berdasarkan = view.getCmbUrut1().getSelectedItem().toString();
        switch(berdasarkan){
            case "id barangkecil" : model.sortBarangbyId();break;
            case "id barcode" : model.sortBarangbyBarcode();break;
            case "nama barangkecil" : model.sortBarangbyNama();break;
            case "tipe" : model.sortBarangbyTipe();break;
            case "merek" : model.sortBarangbyMerek();break;
            case "harga" : model.sortBarangbyHarga();break;
            case "satuan" : model.sortBarangbySatuan();break;
            case "stok" : model.sortBarangbyStok();break;
            case "stok minimum" : model.sortBarangbyStokMin();break;
            case "supplier" : model.sortBarangbySupplier();break;
            case "keterangan" : model.sortBarangbyKeterangan();break;
            default:;
        }    
    }        
}

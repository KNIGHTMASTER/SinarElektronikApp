package com.wissensalt.sinarelektronik.masterdata.barangbesar.controller;

import com.wissensalt.sinarelektronik.dao.BarangBesarDAO;
import com.wissensalt.sinarelektronik.dao.impl.BarangBesarDAOImpl;
import com.wissensalt.sinarelektronik.model.BarangBesarModel;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.view.BarangBesarView;
import com.wissensalt.sinarelektronik.masterdata.itemforbonus.barangbesar.view.JIFBarangBesarBonusKaryawanBBView;
import com.wissensalt.sinarelektronik.penjualan.barangbesar.view.JIFKasirView2;
import com.wissensalt.sinarelektronik.retur.barangbesar.view.JIFReturViewBarangTokoBesar;
import com.wissensalt.sinarelektronik.retur.barangbesar.view.ProsesRetur;
import com.wissensalt.sinarelektronik.transfer.barangbesar.view.JIFTransferBB;
import com.wissensalt.sinarelektronik.inventory.barangbesar.view.JIFInventoryBB;
import com.wissensalt.sinarelektronik.dto.BarangBesarDTO;
import com.wissensalt.sinarelektronik.masterdata.barangbesar.error.BarangException;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class BarangBesarController {
    private BarangBesarModel model;
    private final BarangBesarDAO barangBesarDAO;

    public BarangBesarController() {
        barangBesarDAO = new BarangBesarDAOImpl();
    }

    public void setModel(BarangBesarModel model) {
        this.model = model;
    }    
        
    public void resetBarang(BarangBesarView view){
        model.resetBarang();
    }
        
    private BarangBesarDTO barangBesarDTOEntity;

    public BarangBesarDTO getBarangBesarDTOEntity() {
        return barangBesarDTOEntity;
    }      
    
  public void deleteBarang(BarangBesarView view){
        if(view.getTabelBarang().getSelectedRowCount() < 1){
            JOptionPane.showMessageDialog(view, "pilih BarangKecilDTO terlebih dahulu");
        }else{
            if(JOptionPane.showConfirmDialog(view, "apakah BarangKecilDTO dengan id "+view.getTabelBarang().getValueAt(view.getTabelBarang().getSelectedRow(), 0) +" akan dihapus ?") == JOptionPane.YES_OPTION){
                String id=view.getTabelBarang().getValueAt(view.getTabelBarang().getSelectedRow(), 0).toString();
                JOptionPane.showMessageDialog(view, "Id  = "+id);
                model.setIdBarang(id);
                barangBesarDAO.deleteByString(id);
                model.deleteBarang();
                JOptionPane.showMessageDialog(view, "Success delete barangkecil");
            }
        }
    }
  
    public void cari(BarangBesarView view, BarangBesarView v) throws SQLException, BarangException{
        findByField(v.getCmbCari().getSelectedItem().toString(), view.getTxtKataKunci().getText());
    }      
    
    public void cariProsesReturBB(ProsesRetur view, ProsesRetur v) throws SQLException, BarangException{
        findByField(v.getCmbCari1().getSelectedItem().toString(), view.getTxtKataKunci1().getText());
    }                    
    
    public void cari4(JIFReturViewBarangTokoBesar view, JIFReturViewBarangTokoBesar v) throws SQLException, BarangException{
        findByField(v.getCmbCari1().getSelectedItem().toString(), view.getTxtKataKunci1().getText());
    }            
    
    public void cari6(JIFInventoryBB view, JIFInventoryBB v) throws SQLException, BarangException{
        findByField(v.getCmbCari1().getSelectedItem().toString(), view.getTxtKataKunci1().getText());
    }            
    
    public void sort(BarangBesarView view) throws SQLException, BarangException{
        sortBarangByField(view.getCmbUrut().getSelectedItem().toString());
    }        
    
    public void sortProsesRetur(ProsesRetur view) throws SQLException, BarangException{        
        sortBarangByField(view.getCmbUrut1().getSelectedItem().toString());
    }            
    
    public void sort2(BarangBesarView view) throws SQLException, BarangException {
        sortBarangByField(view.getCmbUrut().getSelectedItem().toString());
    }         
    
    public void sort(JIFKasirView2 view) throws SQLException, BarangException {
        sortBarangByField(view.getCmbUrut1().getSelectedItem().toString());
    }    
    
    public void sortBBKBB(JIFBarangBesarBonusKaryawanBBView view) throws SQLException, BarangException{
        sortBarangByField(view.getCmbUrut1().getSelectedItem().toString());
    }        
    
    public void sort(JIFTransferBB view) throws SQLException, BarangException{
        sortBarangByField(view.getCmbUrut1().getSelectedItem().toString());
    } 
    
    public void sort(JIFReturViewBarangTokoBesar view) throws SQLException, BarangException{
        sortBarangByField(view.getCmbUrut1().getSelectedItem().toString());
    }        
       
    public void sort6(JIFInventoryBB view) throws SQLException, BarangException{                
        sortBarangByField(view.getCmbUrut1().getSelectedItem().toString());
    }            
    
    
    public void cari(JIFKasirView2 view, JIFKasirView2 v) throws SQLException, BarangException{
        findByField(v.getCmbCari1().getSelectedItem().toString(), view.getTxtKataKunci1().getText());
    }               
    
    public void cariBBKBGudang(JIFBarangBesarBonusKaryawanBBView view, JIFBarangBesarBonusKaryawanBBView v) throws SQLException, BarangException{
        findByField(v.getCmbCari1().getSelectedItem().toString(), view.getTxtKataKunci1().getText());
    }                   
    
    public void cari(JIFTransferBB view, JIFTransferBB v) throws SQLException, BarangException{
        findByField(v.getCmbCari1().getSelectedItem().toString(), view.getTxtKataKunci1().getText());
    }                   

    private void findById(String kataKunci) throws BarangException, SQLException {
        model.findBarangByField(barangBesarDAO.getBarangbyId(kataKunci));
    }
    
    private void findByNama(String kataKunci) throws BarangException, SQLException {
        model.findBarangByField(barangBesarDAO.getBarangbyNama(kataKunci));
    }
    
    private void findByTipe(String kataKunci) throws BarangException, SQLException {
        model.findBarangByField(barangBesarDAO.getBarangbyTipe(kataKunci));
    }
    
    private void findByMerek(String kataKunci) throws BarangException, SQLException {
        model.findBarangByField(barangBesarDAO.getBarangbyMerek(kataKunci));
    }
    
    private void findByHarga(String kataKunci) throws BarangException, SQLException {
        model.findBarangByField(barangBesarDAO.getBarangbyHarga(kataKunci));
    }
    
    private void findByStock(String kataKunci) throws BarangException, SQLException {
        model.findBarangByField(barangBesarDAO.getBarangbyStok(kataKunci));
    }
    
    private void findByStockMinimum(String kataKunci) throws BarangException, SQLException {
        model.findBarangByField(barangBesarDAO.getBarangbyStokMin(kataKunci));
    }
    
    private void findBySupplier(String kataKunci) throws BarangException, SQLException {
        model.findBarangByField(barangBesarDAO.getBarangbySupplier(kataKunci));
    }
    
    private void findByKeterangan(String kataKunci) throws BarangException, SQLException {
        model.findBarangByField(barangBesarDAO.getBarangbyKeterangan(kataKunci));
    }
    
    private void findByField(String berdasarkan, String kataKunci) throws BarangException, SQLException {
        switch(berdasarkan){
            case "id barangkecil" : findById(kataKunci);break;
            case "nama barangkecil" : findByNama(kataKunci);break;
            case "tipe" : findByTipe(kataKunci);break;
            case "MerekDTO" : findByMerek(kataKunci);break;
            case "harga" : findByHarga(kataKunci);break;
            case "stok" : findByStock(kataKunci);break;
            case "stok minimum" : findByStockMinimum(kataKunci);break;
            case "supplier" : findBySupplier(kataKunci);break;
            case "keterangan" : findByKeterangan(kataKunci);break;
            default:;
        }  
    }
    
    private void sortBarangByField(String berdasarkan) throws BarangException, SQLException {
        switch(berdasarkan){
            case "id barangkecil" : model.sortByField(barangBesarDAO.sortBarangbyId());break;
            case "nama barangkecil" : model.sortByField(barangBesarDAO.sortBarangbyNama());break;
            case "tipe" : model.sortByField(barangBesarDAO.sortBarangbyTipe());break;
            case "MerekDTO" : model.sortByField(barangBesarDAO.sortBarangbyMerek());break;
            case "harga" : model.sortByField(barangBesarDAO.sortBarangbyHarga());break;
            case "stok" : model.sortByField(barangBesarDAO.sortBarangbyStok());break;
            case "stok minimum" : model.sortByField(barangBesarDAO.sortBarangbyStokMin());break;
            case "supplier" : model.sortByField(barangBesarDAO.sortBarangbySupplier());break;
            case "keterangan" : model.sortByField(barangBesarDAO.sortBarangbyKeterangan());break;
            default:;
    }
    }
}

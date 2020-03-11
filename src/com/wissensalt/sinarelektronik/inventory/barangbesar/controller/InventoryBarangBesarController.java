package com.wissensalt.sinarelektronik.inventory.barangbesar.controller;

import com.wissensalt.sinarelektronik.converter.InventoryBarangBesarConverter;
import com.wissensalt.sinarelektronik.dto.InventoryBarangBesarDTO;
import com.wissensalt.sinarelektronik.model.InventoryBarangBesarModel;
import com.wissensalt.sinarelektronik.inventory.barangbesar.view.JIFInventoryBB;
import javax.swing.JOptionPane;
import com.wissensalt.sinarelektronik.dao.InventoryBarangBesarDAO;
import com.wissensalt.sinarelektronik.dao.impl.InventoryBarangBesarDAOImpl;

/**
 *
 * @author Fauzi
 */
public class InventoryBarangBesarController {

    private InventoryBarangBesarModel model;
    private InventoryBarangBesarConverter inventoryBarangBesarConverter;
    private final InventoryBarangBesarDAO inventoryBarangBesarDAO;
    
    public InventoryBarangBesarController() {
        inventoryBarangBesarDAO = new InventoryBarangBesarDAOImpl();
    }

    public void setModel(InventoryBarangBesarModel model) {
        this.model = model;
    }
    
    public void insertInventory(JIFInventoryBB view){
        String id = view.getTxtnoBeli().getText();
        String user = view.getTxtuser().getText();
        String tanggal = view.getTanggalForDatabase();
        String jam = view.getTxtjam().getText();
        String kode = view.getTxtkodeitem().getText();
        String nama = view.getTxtnamabarang().getText();
        String harga = view.getTxthargaperitem().getText();
        String ekspedisi = view.getTxtEkspedisi().getText();
        String jumlah = view.getTxtJumlahBeli().getText();
        String subharga = view.getTxtSubHarga().getText();
        
        if(id.trim().equals("")){
            JOptionPane.showMessageDialog(view, "id transaksi inventory masih kosong");
        }else if(user.trim().equals("")){
            JOptionPane.showMessageDialog(view, "user transaksi inventory masih kosong");
        }else if(jam.trim().equals("")){
            JOptionPane.showMessageDialog(view, "jam transaksi inventory masih kosong");
        }else if(kode.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Kode transaksi inventory masih kosong");
        }else if(nama.trim().equals("")){
            JOptionPane.showMessageDialog(view, "nama barangkecil transaksi inventory masih kosong");
        }else if(harga.trim().equals("")){
            JOptionPane.showMessageDialog(view, "harga barangkecil transaksi inventory masih kosong");
        }else if(ekspedisi.trim().equals("")){
            JOptionPane.showMessageDialog(view, "ekspedisi barangkecil transaksi inventory masih kosong");
        }else if(jumlah.trim().equals("")){
            JOptionPane.showMessageDialog(view, "jumlah barangkecil transaksi inventory masih kosong");
        }else if(subharga.trim().equals("")){
            JOptionPane.showMessageDialog(view, "subharga barangkecil inventory masih kosong");       
        }else {
            model.setId(Integer.parseInt(id));
            model.setUser(user);
            model.setTanggal(tanggal);
            model.setJam(jam);
            model.setKode(kode);
            model.setNama(nama);
            model.setHarga(Integer.parseInt(harga));
            model.setEkspedisi(Integer.parseInt(ekspedisi));
            model.setJumlah(Integer.parseInt(jumlah));
            model.setSubHarga(Integer.parseInt(subharga));
            
            InventoryBarangBesarDTO inventoryBarangBesarDTO = new InventoryBarangBesarConverter().toDTO(model);
            inventoryBarangBesarDAO.insert(inventoryBarangBesarDTO);
            model.fireOnInsert(inventoryBarangBesarDTO);
        }
        
    }  
    
    public void deleteInventory(JIFInventoryBB view){
        if(view.getTabelProsesInventory().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "pilih data yang akan dihapus");
        }else if(JOptionPane.showConfirmDialog(view, "yakin data akan dihapus ?") == JOptionPane.YES_OPTION){
            String no = view.getTabelProsesInventory().getValueAt(view.getTabelProsesInventory().getSelectedRow(), 0).toString();
            model.setId(Integer.parseInt(no));            
            inventoryBarangBesarDAO.deleteByInt(model.getId());
            model.fireOnDelete();
        }
    }    
    
    public void tambahTransaksi(JIFInventoryBB view){
        inventoryBarangBesarDAO.truncate();
        model.fireOnTruncate();
    }
    
}

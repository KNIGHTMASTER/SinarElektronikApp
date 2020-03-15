package com.wissensalt.sinarelektronik.inventory.barangkecil.controller;

import com.wissensalt.sinarelektronik.converter.InventoryBarangKecilConverter;
import com.wissensalt.sinarelektronik.dao.InventoryBarangKecilDAO;
import com.wissensalt.sinarelektronik.dto.InventoryBarangKecilDTO;
import com.wissensalt.sinarelektronik.inventory.barangkecil.view.JIFInventoryBarangKecilKecil;
import com.wissensalt.sinarelektronik.model.InventoryBarangKecilModel;
import com.wissensalt.sinarelektronik.inventory.barangkecil.service.InventoryBarangKecilDAOImpl;

import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class InventoryBarangKecilController {

    private InventoryBarangKecilModel model;    
    private InventoryBarangKecilDAO dao;

    public InventoryBarangKecilController() {
        dao = new InventoryBarangKecilDAOImpl();
    }

    public InventoryBarangKecilModel getModel() {
        return model;
    }

    public void setModel(InventoryBarangKecilModel model) {
        this.model = model;
    }

    public InventoryBarangKecilController(InventoryBarangKecilModel model) {
        this.model = model;
    }
    
    public void insertInventory(JIFInventoryBarangKecilKecil view){
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
            model.setSubharga(Integer.parseInt(subharga));
    
            InventoryBarangKecilDTO inventoryDTO = new InventoryBarangKecilConverter().toDTO(model);            
            dao.insert(inventoryDTO);
            model.fireOnInsert(inventoryDTO);
        }
        
    }
    
    public void deleteInventory(JIFInventoryBarangKecilKecil view){
        if(view.getTabelProsesInventory().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "pilih data yang akan dihapus");
            return;
        } 
        
        String no = view.getTabelProsesInventory().getValueAt(view.getTabelProsesInventory().getSelectedRow(), 0).toString();
        model.setId(Integer.parseInt(no));
        dao.deleteByInt(model.getId());
        model.fireOnDelete();
    }    
    
    public void tambahTransaksi(JIFInventoryBarangKecilKecil view){
        dao.truncate();
        model.fireOnTruncate();
    }
    
}

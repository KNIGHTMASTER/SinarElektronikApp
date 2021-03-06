/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.inventory.controller;

import Sinarelektronikapp.inventory.model.InventoryModel;
import Sinarelektronikapp.inventory.view.JIFInventory;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class InventoryController {

    InventoryModel model;

    public InventoryController() {        
    }

    public InventoryModel getModel() {
        return model;
    }

    public void setModel(InventoryModel model) {
        this.model = model;
    }

    public InventoryController(InventoryModel model) {
        this.model = model;
    }
    
    public void insertInventory(JIFInventory view){
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
            return;            
        }else if(user.trim().equals("")){
            JOptionPane.showMessageDialog(view, "user transaksi inventory masih kosong");
            return;                        
        }else if(jam.trim().equals("")){
            JOptionPane.showMessageDialog(view, "jam transaksi inventory masih kosong");
            return;                        
        }else if(kode.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Kode transaksi inventory masih kosong");
            return;            
        }else if(nama.trim().equals("")){
            JOptionPane.showMessageDialog(view, "nama barang transaksi inventory masih kosong");
            return;                        
        }else if(harga.trim().equals("")){
            JOptionPane.showMessageDialog(view, "harga barang transaksi inventory masih kosong");
            return;                        
        }else if(ekspedisi.trim().equals("")){
            JOptionPane.showMessageDialog(view, "ekspedisi barang transaksi inventory masih kosong");
            return;                        
        }else if(jumlah.trim().equals("")){
            JOptionPane.showMessageDialog(view, "jumlah barang transaksi inventory masih kosong");
            return;                        
        }else if(subharga.trim().equals("")){
            JOptionPane.showMessageDialog(view, "subharga barang inventory masih kosong");
            return;                        
        }else{
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
            
            model.insertProsesInventory();
        }
        
    }
    
    public void updateInventory(JIFInventory view){
        
    }    
    
    public void deleteInventory(JIFInventory view){
        if(view.getTabelProsesInventory().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "pilih data yang akan dihapus");
            return;
        }else if(JOptionPane.showConfirmDialog(view, "yakin data akan dihapus ?") == JOptionPane.YES_OPTION){
            String no = view.getTabelProsesInventory().getValueAt(view.getTabelProsesInventory().getSelectedRow(), 0).toString();            
            model.setId(Integer.parseInt(no));            
            model.deleteProsesInventory();
        }
    }    
    
    public void tambahTransaksi(JIFInventory view){
        model.truncateProsesInventory();
    }
    
}

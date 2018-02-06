/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.ReturBarangBesar.transaksi.controller;

import Sinarelektronikapp.ReturBarangBesar.transaksi.model.ReturModel;
import Sinarelektronikapp.ReturBarangBesar.transaksi.view.JIFReturViewBarangBesar;
import Sinarelektronikapp.ReturBarangBesar.transaksi.view.ReturView;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class ReturController {
    ReturModel model;

    public ReturController() {        
    }

    public ReturModel getModel() {
        return model;
    }

    public void setModel(ReturModel model) {
        this.model = model;
    }

    public ReturController(ReturModel model) {
        this.model = model;
    }
    
    public void insertRetur(ReturView view){
        String id = view.getTxtnoretur().getText();        
        String user = view.getTxtuser().getText();
        String tanggal = view.getTanggalForDatabase();
        String jam = view.getTxtjam().getText();
        String kode = view.getTxtkodeitem().getText();
        String nama = view.getTxtnamabarang().getText();
        String jumlah = view.getTxtjumlahretur().getText();
        String subharga = view.getTxtSubHarga().getText();
        
        if(id.trim().equals("")){
            JOptionPane.showMessageDialog(view, "id transaksi retur masih kosong");
            return;            
        }else if(user.trim().equals("")){
            JOptionPane.showMessageDialog(view, "user transaksi retur masih kosong");
            return;                        
        }else if(jam.trim().equals("")){
            JOptionPane.showMessageDialog(view, "jam transaksi retur masih kosong");
            return;                        
        }else if(kode.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Kode transaksi retur masih kosong");
            return;            
        }else if(nama.trim().equals("")){
            JOptionPane.showMessageDialog(view, "nama barang transaksi retur masih kosong");
            return;                        
        }else if(jumlah.trim().equals("")){
            JOptionPane.showMessageDialog(view, "jumlah barang transaksi retur masih kosong");
            return;                        
        }else if(subharga.trim().equals("")){
            JOptionPane.showMessageDialog(view, "subharga barang retur masih kosong");
            return;                        
        }else{
            model.setId(Integer.parseInt(id));
            model.setUser(user);
            model.setTanggal(tanggal);
            model.setJam(jam);
            model.setKode(kode);
            model.setNama(nama);
            model.setJumlah(Integer.parseInt(jumlah));
            model.setSubharga(Integer.parseInt(subharga));
            
            model.insertRetur();
        }
        
    }
    
    public void insertRetur(JIFReturViewBarangBesar view){
        String id = view.getTxtnoretur().getText();        
        String user = view.getTxtuser().getText();
        String tanggal = view.getTanggalForDatabase();
        String jam = view.getTxtjam().getText();
        String kode = view.getTxtkodeitem().getText();
        String nama = view.getTxtnamabarang().getText();
        String jumlah = view.getTxtjumlahretur().getText();
        String subharga = view.getTxtSubHarga().getText();
        String asalBarang = view.getSumberBarang();
        String penukaran = view.getCmbPenukarang().getSelectedItem().toString();
        switch(asalBarang){
            case "barangtoko" : asalBarang = "toko";break;
            case "barangbesar" : asalBarang = "gudang";break;
            default:;
        }        
        if(id.trim().equals("")){
            JOptionPane.showMessageDialog(view, "id transaksi retur masih kosong");
            return;            
        }else if(user.trim().equals("")){
            JOptionPane.showMessageDialog(view, "user transaksi retur masih kosong");
            return;                        
        }else if(jam.trim().equals("")){
            JOptionPane.showMessageDialog(view, "jam transaksi retur masih kosong");
            return;                        
        }else if(kode.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Kode transaksi retur masih kosong");
            return;            
        }else if(nama.trim().equals("")){
            JOptionPane.showMessageDialog(view, "nama barang transaksi retur masih kosong");
            return;                        
        }else if(jumlah.trim().equals("")){
            JOptionPane.showMessageDialog(view, "jumlah barang transaksi retur masih kosong");
            return;                        
        }else if(subharga.trim().equals("")){
            JOptionPane.showMessageDialog(view, "subharga barang retur masih kosong");
            return;                        
        }else{
            model.setId(Integer.parseInt(id));
            model.setUser(user);
            model.setTanggal(tanggal);
            model.setJam(jam);
            model.setKode(kode);
            model.setNama(nama);
            model.setJumlah(Integer.parseInt(jumlah));
            model.setSubharga(Integer.parseInt(subharga));
            model.setAsalBarang(asalBarang);
            model.setPenukaran(penukaran);
            model.insertRetur();
        }
        
    }
    
    
    public void updateRetur(ReturView view){
        
    }    
    
    public void deleteRetur(ReturView view){
        if(view.getTabelRetur().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "pilih data yang akan dihapus");
            return;
        }else if(JOptionPane.showConfirmDialog(view, "yakin data akan dihapus ?") == JOptionPane.YES_OPTION){
            String no = view.getTabelRetur().getValueAt(view.getTabelRetur().getSelectedRow(), 0).toString();            
            model.setId(Integer.parseInt(no));            
            model.deleteRetur();
        }
    }    
    
    public void deleteRetur(JIFReturViewBarangBesar view){
        if(view.getTabelRetur().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "pilih data yang akan dihapus");
            return;
        }else if(JOptionPane.showConfirmDialog(view, "yakin data akan dihapus ?") == JOptionPane.YES_OPTION){
            String no = view.getTabelRetur().getValueAt(view.getTabelRetur().getSelectedRow(), 0).toString();            
            model.setId(Integer.parseInt(no));            
            model.deleteRetur();
        }
    }        
    
    public void tambahTransaksi(ReturView view){
        model.truncateRetur();
    }

     public void tambahTransaksi(JIFReturViewBarangBesar view){
        model.truncateRetur();
    }
}

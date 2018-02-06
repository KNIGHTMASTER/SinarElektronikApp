/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.retur.transaksi.controller;

import Sinarelektronikapp.retur.transaksi.model.ReturModel;
import Sinarelektronikapp.retur.transaksi.view.JIFReturView2;
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
    
    public void insertRetur(JIFReturView2 view){
        String id = view.getTxtnoretur().getText();        
        String user = view.getTxtuser().getText();
        String tanggal = view.getTanggalForDatabase();
        String jam = view.getTxtjam().getText();
        String kode = view.getTxtkodeitem().getText();
        String nama = view.getTxtnamabarang().getText();
        String jumlah = view.getTxtjumlahretur().getText();
        String harga = view.getTxthargaperitem().getText();
        String subharga = view.getTxtSubHarga().getText();
        String penukaran = view.getCmbPenukarang().getSelectedItem().toString();
        String supplier = view.getSupplier(kode);
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
            model.setHarga(Integer.parseInt(harga));
            model.setPenukarang(penukaran);
            model.setSubharga(Integer.parseInt(subharga));
            model.setSupplier(supplier);
            
            model.insertRetur();
        }
        
    }
    
/*    public void insertRetur(JIFReturView2 view){
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
        
    }        */
    
    
    public void deleteRetur(JIFReturView2 view){
        if(view.getTabelRetur().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "pilih data yang akan dihapus");
            return;
        }else if(JOptionPane.showConfirmDialog(view, "yakin data akan dihapus ?") == JOptionPane.YES_OPTION){
            String no = view.getTabelRetur().getValueAt(view.getTabelRetur().getSelectedRow(), 0).toString();            
            model.setId(Integer.parseInt(no));            
            model.deleteRetur();
        }
    }        
    

     public void tambahTransaksi(JIFReturView2 view){
        model.truncateRetur();
    }
    
}

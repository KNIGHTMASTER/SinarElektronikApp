/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.ManajemenPenjualan.BarangKecil.Controller;

import Sinarelektronikapp.ManajemenPenjualan.BarangKecil.Model.TransaksiPenjualanModel;
import Sinarelektronikapp.ManajemenPenjualan.BarangKecil.View.JIFManajemenTransaksiPenjualan;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class TransaksiPenjualanController {
    TransaksiPenjualanModel transaksiPenjualanModel;

    public TransaksiPenjualanController() {
    }

    public TransaksiPenjualanModel getTransaksiPenjualanModel() {
        return transaksiPenjualanModel;
    }

    public void setTransaksiPenjualanModel(TransaksiPenjualanModel transaksiPenjualanModel) {
        this.transaksiPenjualanModel = transaksiPenjualanModel;
    }
    
    public void deleteTransaksiPenjualan(JIFManajemenTransaksiPenjualan jifmtp){
        if(jifmtp.getTabelTransaksiPenjualan().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(jifmtp, "pilih data yang akan dihapus");
            return;
        }else if(JOptionPane.showConfirmDialog(jifmtp, "yakin data akan dihapus ?") == JOptionPane.YES_OPTION){
            String no = jifmtp.getTabelTransaksiPenjualan().getValueAt(jifmtp.getTabelTransaksiPenjualan().getSelectedRow(), 0).toString();
            transaksiPenjualanModel.setIdTransaksi(Integer.parseInt(no));
            transaksiPenjualanModel.deleteTransaksiPenjualan();
        }        
    }

    public void cari(JIFManajemenTransaksiPenjualan aThis) {
        String kataKunci = aThis.getTxtKataKunci().getText();
        String berdasarkan = aThis.getCmbCari().getSelectedItem().toString();
        JOptionPane.showMessageDialog(null, "kata kunci = "+kataKunci);
        JOptionPane.showMessageDialog(null, "berdasarkan= "+berdasarkan);
        JOptionPane.showMessageDialog(null, "oke contorller");
        transaksiPenjualanModel.cari(kataKunci, berdasarkan);
        JOptionPane.showMessageDialog(null, "oke contorller 2");
    }

    public void sort(JIFManajemenTransaksiPenjualan aThis) {
        String berdasarkan = aThis.getCmbUrut().getSelectedItem().toString();        
        transaksiPenjualanModel.sort(berdasarkan);
    }    
}

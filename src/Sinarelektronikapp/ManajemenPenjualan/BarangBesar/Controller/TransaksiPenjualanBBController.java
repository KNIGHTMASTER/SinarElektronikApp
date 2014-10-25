/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Controller;

import Sinarelektronikapp.ManajemenPenjualan.BarangBesar.Model.TransaksiPenjualanBBModel;
import Sinarelektronikapp.ManajemenPenjualan.BarangBesar.View.JIFTransaksiPenjualanBB;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class TransaksiPenjualanBBController {
    TransaksiPenjualanBBModel model;

    public TransaksiPenjualanBBModel getModel() {
        return model;
    }

    public void setModel(TransaksiPenjualanBBModel model) {
        this.model = model;
    }

    public TransaksiPenjualanBBController() {
    }

    public void deleteTransaksi(JIFTransaksiPenjualanBB jiftpbb){
        if(jiftpbb.getTabelTransaksiPenjualan().getSelectedRowCount()==0){
            JOptionPane.showMessageDialog(jiftpbb, "pilih data yang akan dihapus", "perhatian", JOptionPane.WARNING_MESSAGE);
            return;
        }else if(JOptionPane.showConfirmDialog(jiftpbb, "Apakah anda yakin akan menghapus data ?") == JOptionPane.YES_OPTION){
            int id = (int) jiftpbb.getTabelTransaksiPenjualan().getValueAt(jiftpbb.getTabelTransaksiPenjualan().getSelectedRow(), 0);
            model.setIdtransaksi(id);
            model.deleteTransaksiPenjualanBB();
        }
    }
    
}

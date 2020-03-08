/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.penjualan.barangkecil.garansi.controller;

import Sinarelektronikapp.penjualan.barangkecil.garansi.model.GaransiModel;
import Sinarelektronikapp.penjualan.barangkecil.garansi.view.GaransiView;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class GaransiController {
    private GaransiModel garansiModel;
    
    Date date=new Date();        
    
    SimpleDateFormat formatTanggal2=new SimpleDateFormat("yyyy-MM-dd");

    public GaransiModel getGaransiModel() {
        return garansiModel;
    }

    public void setGaransiModel(GaransiModel garansiModel) {
        this.garansiModel = garansiModel;
    }
    
    public void resetGaransi(GaransiView view){
        garansiModel.resetGaransi();
    }
        
    public void insertGaransi(GaransiView view){
        String idGaransi = view.getTxtIdGaransi().getText();
        String namaBarang = view.getTxtNamaBarang().getText();
        String jumlah = view.getTxtJumlah().getText();
        String masaAkhir = formatTanggal2.format(view.getjDateMasaAkhir().getDate());
        String status = view.getCmbStatus().getSelectedItem().toString();
        
        if(idGaransi.trim().equals("")){
            JOptionPane.showMessageDialog(view, "id garansi masih kosong");
            return;
        }else if(namaBarang.trim().equals("")){
            JOptionPane.showMessageDialog(view, "nama barangkecil masih kosong");
            return;            
        }else if(jumlah.trim().equals("")){
            JOptionPane.showMessageDialog(view, "jumlah barangkecil masih kosong");
            return;                        
        }else if(masaAkhir.trim().equals("")){
            JOptionPane.showMessageDialog(view, "masa akhir barangkecil masih kosong");
            return;                                    
        }else if(status.trim().equals("")){
            JOptionPane.showMessageDialog(view, "status masih kosong");
            return;                                                
        }else{
            garansiModel.setIdTransaksi(idGaransi);
            garansiModel.setJumlah(Integer.valueOf(jumlah));
            garansiModel.setMasaakhir(masaAkhir);
            garansiModel.setNamaBarang(namaBarang);
            garansiModel.setStatus(status);
            garansiModel.insertGaransi();
        }
    }
    
    public void updateGaransi(GaransiView view){
        if(view.getTabelGaransi().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(view, "Pilih data terlebih dahulu");
            return;            
        }else{
            String idGaransi = view.getTxtIdGaransi().getText();
            String namaBarang = view.getTxtNamaBarang().getText();
            String jumlah = view.getTxtJumlah().getText();
            String masaAkhir = formatTanggal2.format(view.getjDateMasaAkhir().getDate());
            String status = view.getCmbStatus().getSelectedItem().toString();
            
            if(idGaransi.trim().equals("")){
                JOptionPane.showMessageDialog(view, "id garansi masih kosong");
                return;
            }else if(namaBarang.trim().equals("")){
                JOptionPane.showMessageDialog(view, "nama barangkecil masih kosong");
                return;            
            }else if(jumlah.trim().equals("")){
                JOptionPane.showMessageDialog(view, "jumlah barangkecil masih kosong");
                return;                        
            }else if(masaAkhir.trim().equals("")){
                JOptionPane.showMessageDialog(view, "masa akhir barangkecil masih kosong");
                return;                                    
            }else if(status.trim().equals("")){
                JOptionPane.showMessageDialog(view, "status masih kosong");
                return;                                                
            }else{
                garansiModel.setIdTransaksi(idGaransi);
                garansiModel.setJumlah(Integer.valueOf(jumlah));
                garansiModel.setMasaakhir(masaAkhir);
                garansiModel.setNamaBarang(namaBarang);
                garansiModel.setStatus(status);
                garansiModel.updateGaransi();
            }               
        }     
    }
    
    public void deleteGaransi(GaransiView view){
        if(view.getTabelGaransi().getSelectedRowCount()==0){
            JOptionPane.showMessageDialog(view, "pilih data terlebih dahulu");
            return;
        }
        if(JOptionPane.showConfirmDialog(view, "Apakah data dengan id "+view.getTxtIdGaransi().getText()+" akan dihapus ?") == JOptionPane.YES_OPTION){
            garansiModel.setIdTransaksi(view.getTxtIdGaransi().getText());
            garansiModel.deleteGaransi();
        }
    }
    
    public void search(GaransiView view){
        String kataKunci = view.getTxtKataKunci().getText();
        String berdasarkan = view.getCmbCari().getSelectedItem().toString();
        
        switch(berdasarkan){
            case "Id Garansi":
                garansiModel.setIdTransaksi(kataKunci);
                garansiModel.searchById();
                break;
            case "Nama Barang":
                garansiModel.setNamaBarang(kataKunci);
                garansiModel.searchByNama();
                break;                
            case "Jumlah":
                garansiModel.setJumlah(Integer.valueOf(kataKunci));
                garansiModel.searchByNama();
                break;                                
            case "Masa Akhir":
                garansiModel.setMasaakhir(kataKunci);
                garansiModel.searchByNama();
                break;                                                
            case "Status":
                garansiModel.setStatus(kataKunci);
                garansiModel.searchByNama();
                break;
           default:;
        }
    }
    
    public void sort(GaransiView view){
        String kataKunci = view.getTxtKataKunci().getText();
        String berdasarkan = view.getCmbCari().getSelectedItem().toString();
        
        switch(berdasarkan){
            case "Id Garansi":
                garansiModel.sortById();
                break;
            case "Nama Barang":
                garansiModel.sortByNama();
                break;                
            case "Jumlah":
                garansiModel.sortByNama();
                break;                                
            case "Masa Akhir":
                garansiModel.sortByNama();
                break;                                                
            case "Status":
                garansiModel.sortByNama();
                break;
           default:;
        }
    }    
}

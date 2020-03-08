/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.karyawan.controller;

import Sinarelektronikapp.masterdata.karyawan.model.KaryawanModel;
import Sinarelektronikapp.masterdata.karyawan.view.KaryawanView;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Fauzi
 */
public class KaryawanController {

    private KaryawanModel karyawanModel;
    
    public KaryawanController() {
    }

    public KaryawanModel getKaryawanModel() {
        return karyawanModel;
    }

    public void setKaryawanModel(KaryawanModel karyawanModel) {
        this.karyawanModel = karyawanModel;
    }
    
    public void reset(KaryawanView view){
        karyawanModel.resetKaryawan();
    }
    
    SimpleDateFormat formatTanggal=new SimpleDateFormat("dd:MM:yyyy");
    
    public void insertKaryawan(KaryawanView view){
        int id = Integer.parseInt(view.getTxtId().getText());
        String nama = view.getTxtNama().getText();        
        String tanggalLahir = formatTanggal.format(view.getTxtTanggalLahir().getDate());
        String tempatLahir = view.getTxtTempatLahir().getText();
        String alamat = view.getTxtAlamat().getText();
        String telepon = view.getTxtTelepon().getText();
        String agama = view.getTxtAgama().getText();
        String status = "";
        if(view.getRbSingle().isSelected() == true){
            status = "single";
        }else if(view.getRbMenikah().isSelected() == true){
            status = "menikah";
        }else if(view.getRbJanda().isSelected() == true){
            status = "janda";
        }else if(view.getRbDuda().isSelected() == true){
            status = "duda";
        }
        int gaji = Integer.parseInt(view.getTxtGaji().getText());
        
        File gambar = view.getGambar();
        
        if(nama.trim().equals("")){
            JOptionPane.showMessageDialog(view, "nama masih kosong");
            return;
        }else if(tanggalLahir == null){
            JOptionPane.showMessageDialog(view, "tanggal lahir masih kosong");
            return;
        }else if(tempatLahir.trim().equals("")){
            JOptionPane.showMessageDialog(view, "tempat lahir masih kosong");
            return;
        }else if(alamat.trim().equals("")){
            JOptionPane.showMessageDialog(view, "alamat masih kosong");
            return;
        }else if(telepon.trim().equals("")){
            JOptionPane.showMessageDialog(view, "telepon kosong");
            return;            
        }else if(agama.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Agama kosong");
            return;                        
        }else if(status.trim().equals("")){
            JOptionPane.showMessageDialog(view, "Status kosong");
            return;                                    
        }else if(gaji == 0){
            JOptionPane.showMessageDialog(view, "Gaji kosong");
            return;                                                
        }else if(gambar == null){
            JOptionPane.showMessageDialog(view, "Gambar kosong");
            return;                                                            
        }else{
            karyawanModel.setId(id);
            karyawanModel.setNama(nama);
            karyawanModel.setTanggal_lahir(tanggalLahir);
            karyawanModel.setTempat_lahir(tempatLahir);
            karyawanModel.setAlamat(alamat);
            karyawanModel.setTelepon(telepon);
            karyawanModel.setAgama(agama);
            karyawanModel.setStatus(status);
            karyawanModel.setGaji(gaji);
            karyawanModel.setFoto(gambar);
            karyawanModel.insertKaryawan();
        }
    }
    
    public void updateKaryawan(KaryawanView view){
        if(view.getTabelKaryawan().getSelectedRowCount()==0){
            JOptionPane.showMessageDialog(view, "Pilih data terlebih dahulu");
            return;
            }else{
            int id = Integer.parseInt(view.getTxtId().getText());
            String nama = view.getTxtNama().getText();
            String tanggalLahir = formatTanggal.format(view.getTxtTanggalLahir().getDate());
            String tempatLahir = view.getTxtTempatLahir().getText();
            String alamat = view.getTxtAlamat().getText();
            String telepon = view.getTxtTelepon().getText();
            String agama = view.getTxtAgama().getText();
            String status = "";
            if(view.getRbSingle().isSelected() == true){
                status = "single";
            }else if(view.getRbMenikah().isSelected() == true){
                status = "menikah";
            }else if(view.getRbJanda().isSelected() == true){
                status = "janda";
            }else if(view.getRbDuda().isSelected() == true){
                status = "duda";
            }
            int gaji = Integer.parseInt(view.getTxtGaji().getText());
        
            File gambar = view.getGambar();
        
            if(nama.trim().equals("")){
                JOptionPane.showMessageDialog(view, "nama masih kosong");
                return;
            }else if(tanggalLahir == null){
                JOptionPane.showMessageDialog(view, "tanggal lahir masih kosong");
                return;
            }else if(tempatLahir.trim().equals("")){
                JOptionPane.showMessageDialog(view, "tempat lahir masih kosong");
                    return;
            }else if(alamat.trim().equals("")){
                JOptionPane.showMessageDialog(view, "alamat masih kosong");
                return;
            }else if(telepon.trim().equals("")){
                JOptionPane.showMessageDialog(view, "telepon kosong");
                return;            
            }else if(agama.trim().equals("")){
                JOptionPane.showMessageDialog(view, "Agama kosong");
                return;                        
            }else if(status.trim().equals("")){
                JOptionPane.showMessageDialog(view, "Status kosong");
                return;                                    
            }else if(gaji == 0){
                JOptionPane.showMessageDialog(view, "Gaji kosong");
                return;                                                
            }else if(gambar == null){
                JOptionPane.showMessageDialog(view, "Gambar kosong");
                return;                                                            
            }else{
                karyawanModel.setId(id);
                karyawanModel.setNama(nama);
                karyawanModel.setTanggal_lahir(tanggalLahir);
                karyawanModel.setTempat_lahir(tempatLahir);
                karyawanModel.setAlamat(alamat);
                karyawanModel.setTelepon(telepon);
                karyawanModel.setAgama(agama);
                karyawanModel.setStatus(status);
                karyawanModel.setGaji(gaji);
                karyawanModel.setFoto(gambar);
            
                karyawanModel.updateKaryawan();
            }
        }        
    }    
    
    public void deleteKaryawan(KaryawanView view){            
        if(view.getTabelKaryawan().getSelectedRowCount()==0){
            JOptionPane.showMessageDialog(view, "pilih data terleih dahulu");
            return;
        }else{
            karyawanModel.deleteKaryawan();
            karyawanModel.resetKaryawan();
        }
    }    
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinarelektronikapp.masterdata.karyawan.service;

import Sinarelektronikapp.masterdata.karyawan.Error.KaryawanException;
import Sinarelektronikapp.masterdata.karyawan.entity.Karyawan;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface KaryawanDao {
    
    public void insertKaryawan(Karyawan karyawan) throws KaryawanException;
    
    public void updateKaryawan(Karyawan karyawan) throws KaryawanException;
    
    public void deleteKaryawan(int id) throws KaryawanException;
    
    public List<Karyawan> selectAllKaryawan() throws KaryawanException;
    
    public int getLastIdKaryawan() throws KaryawanException;
}

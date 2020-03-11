package com.wissensalt.sinarelektronik.converter;

import com.wissensalt.sinarelektronik.dto.InventoryBarangKecilDTO;
import com.wissensalt.sinarelektronik.model.InventoryBarangKecilModel;

/**
 *
 * @author Fauzi
 */
public class InventoryBarangKecilConverter extends ABaseConverter<InventoryBarangKecilDTO, InventoryBarangKecilModel> {
        
    @Override
    public InventoryBarangKecilDTO toDTO(InventoryBarangKecilModel model) {
        InventoryBarangKecilDTO inventory = new InventoryBarangKecilDTO();
        inventory.setId(model.getId());
        inventory.setUser(model.getUser());
        inventory.setTanggal(model.getTanggal());
        inventory.setJam(model.getJam());
        inventory.setKode(model.getKode());
        inventory.setNama(model.getNama());
        inventory.setHarga(model.getHarga());
        inventory.setEkspedisi(model.getEkspedisi());
        inventory.setJumlah(model.getJumlah());
        inventory.setSubharga(model.getSubharga());
        
        return inventory;
    }

    @Override
    public InventoryBarangKecilModel toModel(InventoryBarangKecilDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

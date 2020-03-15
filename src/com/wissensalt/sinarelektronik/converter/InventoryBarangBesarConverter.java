package com.wissensalt.sinarelektronik.converter;

import com.wissensalt.sinarelektronik.dto.InventoryBarangBesarDTO;
import com.wissensalt.sinarelektronik.model.InventoryBarangBesarModel;

/**
 *
 * @author Fauzi
 */
public class InventoryBarangBesarConverter extends ABaseConverter<InventoryBarangBesarDTO, InventoryBarangBesarModel> {
        
    @Override
    public InventoryBarangBesarDTO toDTO(InventoryBarangBesarModel model) {
        InventoryBarangBesarDTO inventory = new InventoryBarangBesarDTO();
        inventory.setId(model.getId());
        inventory.setUser(model.getUser());
        inventory.setTanggal(model.getTanggal());
        inventory.setJam(model.getJam());
        inventory.setKode(model.getKode());
        inventory.setNama(model.getNama());
        inventory.setHarga(model.getHarga());
        inventory.setEkspedisi(model.getEkspedisi());
        inventory.setJumlah(model.getJumlah());
        inventory.setSubharga(model.getSubHarga());
        
        return inventory;
    }

    @Override
    public InventoryBarangBesarModel toModel(InventoryBarangBesarDTO dto) {
        InventoryBarangBesarModel inventoryBarangBesarModel = new InventoryBarangBesarModel();
        inventoryBarangBesarModel.setId(dto.getId());
        inventoryBarangBesarModel.setJumlah(dto.getJumlah());
        inventoryBarangBesarModel.setHarga(dto.getHarga());
        inventoryBarangBesarModel.setEkspedisi(dto.getEkspedisi());
        inventoryBarangBesarModel.setSubHarga(dto.getSubharga());
        inventoryBarangBesarModel.setUser(dto.getUser());
        inventoryBarangBesarModel.setTanggal(dto.getTanggal());
        inventoryBarangBesarModel.setJam(dto.getJam());
        inventoryBarangBesarModel.setKode(dto.getKode());
        inventoryBarangBesarModel.setNama(dto.getNama());
        
        return inventoryBarangBesarModel;
    }
}

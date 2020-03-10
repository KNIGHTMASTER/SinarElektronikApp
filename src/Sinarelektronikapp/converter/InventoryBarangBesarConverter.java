package Sinarelektronikapp.converter;

import Sinarelektronikapp.dto.InventoryBarangBesarDTO;
import Sinarelektronikapp.model.InventoryBarangBesarModel;

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
        inventory.setSubharga(model.getSubharga());
        
        return inventory;
    }

    @Override
    public InventoryBarangBesarModel toModel(InventoryBarangBesarDTO arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

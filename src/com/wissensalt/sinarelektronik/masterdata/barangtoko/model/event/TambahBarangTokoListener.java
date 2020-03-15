package com.wissensalt.sinarelektronik.masterdata.barangtoko.model.event;

import com.wissensalt.sinarelektronik.dto.BarangBesarDTO;

import com.wissensalt.sinarelektronik.masterdata.tambahbarang.besar.model.TambahBarangBesarModel;

/**
 *
 * @author Fauzi
 */
interface TambahBarangTokoListener {
    
    void onChange(TambahBarangBesarModel model);
    
    void onInsert(BarangBesarDTO barangBesarDTO);
    
    void onUpdate(BarangBesarDTO barangBesarDTO);
}

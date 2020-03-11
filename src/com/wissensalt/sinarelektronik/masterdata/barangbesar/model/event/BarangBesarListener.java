package com.wissensalt.sinarelektronik.masterdata.barangbesar.model.event;

import com.wissensalt.sinarelektronik.dto.BarangBesarDTO;
import com.wissensalt.sinarelektronik.model.BarangBesarModel;

import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface BarangBesarListener {
    void onChange(BarangBesarModel model);
    void onInsert(BarangBesarDTO BarangBesarDTO);
    void onUpdate(BarangBesarDTO BarangBesarDTO);
    void onDelete();
    void onSearch(List list);
    void onSort(List list);
}

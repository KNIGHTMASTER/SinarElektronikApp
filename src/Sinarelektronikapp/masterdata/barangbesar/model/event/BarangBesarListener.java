package Sinarelektronikapp.masterdata.barangbesar.model.event;

import Sinarelektronikapp.masterdata.barangbesar.entity.barang;
import Sinarelektronikapp.masterdata.barangbesar.model.BarangBesarModel;

import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface BarangBesarListener {
    void onChange(BarangBesarModel model);
    void onInsert(barang barang);
    void onUpdate(barang barang);
    void onDelete();
    void onSearch(List list);
    void onSort(List list);
}

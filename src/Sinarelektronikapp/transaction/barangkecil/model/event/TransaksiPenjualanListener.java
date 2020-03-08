package Sinarelektronikapp.transaction.barangkecil.model.event;

import Sinarelektronikapp.transaction.barangkecil.model.TransaksiPenjualanModel;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface TransaksiPenjualanListener {       
    public void onDelete();
    
    public void onSearch(List list);
    
    public void onSort(List list);
}

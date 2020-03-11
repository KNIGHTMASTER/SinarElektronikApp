package com.wissensalt.sinarelektronik.transaction.barangbesar.model.event;

import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface TransaksiPenjualanBBListener {
    public void onDelete();
    
    public void onSearch(List list);
    
    public void onSort(List list);
}

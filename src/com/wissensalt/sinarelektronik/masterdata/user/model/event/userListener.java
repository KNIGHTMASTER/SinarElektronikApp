/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.user.model.event;

import com.wissensalt.sinarelektronik.masterdata.user.entity.User;
import com.wissensalt.sinarelektronik.masterdata.user.model.usermodel;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface userListener {
    public void onChange(usermodel model);
    
    public void onInsert(User user);
    
    public void onUpdate(User user);
    
    public void onDelete();
    
    public void onSearch(List list);
    
    public void onSort(List list);
}

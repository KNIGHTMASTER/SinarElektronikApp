package com.wissensalt.sinarelektronik.masterdata.user.model.event;

import com.wissensalt.sinarelektronik.masterdata.user.entity.UserDTO;
import com.wissensalt.sinarelektronik.masterdata.user.model.UserModel;
import java.util.List;

/**
 *
 * @author Fauzi
 */
public interface UserListener {
    void onChange(UserModel model);
    
    void onInsert(UserDTO userDTO);
    
    void onUpdate(UserDTO userDTO);
    
    void onDelete();
    
    void onSearch(List list);
    
    void onSort(List list);
}

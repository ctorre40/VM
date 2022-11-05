/**
 * @date 10/19/2022
 * @author ctreb
 * 
 * Interface to retrieve my data and manipulate it 
 */
package com.ctrebollar.vendingmachine.dao;

import com.ctrebollar.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface VendingDao {
    List<Item> getAllItems() throws VendingPersistenceException;
    Item getItem(String itemName) throws VendingPersistenceException;
    void removeItem(String itemName) throws VendingPersistenceException; // not removing entire item so need to eval this
    int getInventoryCount(String itemName) throws VendingPersistenceException;
    Map<String, BigDecimal> availItems() throws VendingPersistenceException;
}

/**
 * @date 10/19/2022
 * @author ctreb
 * 
 * Interface containing business requirements and logic
 */
package com.ctrebollar.vendingmachine.service;

import com.ctrebollar.vendingmachine.dao.VendingPersistenceException;
import com.ctrebollar.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface VendingServiceLayer {
    List<Item> getAll() throws VendingPersistenceException;
    Item getItem(String itemName, BigDecimal money) throws VendingPersistenceException, InsufficientFundsException, NoItemInventoryException;
    void removeItemCount(String itemName) throws VendingPersistenceException, NoItemInventoryException;
    Map<String, BigDecimal> getAvailable()throws VendingPersistenceException;
    //check if amount is enough
    void isSufficient(BigDecimal userEnter, Item itemName) throws InsufficientFundsException;
    //get change -- calls on change class
    BigDecimal returnChange(BigDecimal change, Item itemName);
    
    
}

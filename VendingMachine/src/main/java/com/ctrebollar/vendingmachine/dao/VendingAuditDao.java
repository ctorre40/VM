/**
 * @date 10/19/2022
 * @author ctreb
 * 
 * Interface for writing to my audit file
 */
package com.ctrebollar.vendingmachine.dao;

import com.ctrebollar.vendingmachine.service.VendingServiceLayer;


public interface VendingAuditDao {
  
    public void writeAuditEntry(String entry) throws VendingPersistenceException;
    
}

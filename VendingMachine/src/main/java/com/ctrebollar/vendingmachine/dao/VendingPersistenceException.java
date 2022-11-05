/**
 * @date 10/19/2022
 * @author ctreb
 * 
 * Thrown when my file could not be loaded properly
 */
package com.ctrebollar.vendingmachine.dao;


public class VendingPersistenceException extends Exception{

    public VendingPersistenceException(String message) {
        super(message);
    }

    public VendingPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

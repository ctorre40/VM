/**
 * @date 10/19/2022
 * @author ctreb
 * 
 * Thrown when use tries to get item that does not exist or has
 * an inventory count of 0
 */
package com.ctrebollar.vendingmachine.service;


public class NoItemInventoryException extends Exception {

    public NoItemInventoryException(String message) {
        super(message);
    }

    public NoItemInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

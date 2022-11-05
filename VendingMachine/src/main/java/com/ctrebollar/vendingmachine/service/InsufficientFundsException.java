/**
 * @date 10/19/2022
 * @author ctreb
 * 
 * Thrown when user tries to enter amount that is
 * less than item cost
 */
package com.ctrebollar.vendingmachine.service;


public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

/**
 * @date 10/19/2022
 * @author ctreb
 * 
 * Interface that handles user input  
 */
package com.ctrebollar.vendingmachine.ui;

import java.math.BigDecimal;


public interface UserIO {
    void print(String msg);
    double readDouble(String msg, double min, double max);
    double readDouble(String msg);
    float readFloat(String msg, float min, float max);
    float readFloat(String msg);
    int readInt(String msg, int min, int max);
    int readInt(String msg);    
    String readString(String msg);
    long readLong(String msg, long min, long max);
    long readLong(String msg);   
    BigDecimal readBd(String msg);
}

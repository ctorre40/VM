/**
 * @date 10/19/2022
 * @author ctreb
 * 
 * contains the implementation of the user IO interface
 */
package com.ctrebollar.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.Scanner;
import org.springframework.stereotype.Component;

//@Component
public class UserIOConsoleImpl implements UserIO{
    
    final private Scanner console = new Scanner(System.in);
    
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String msg, double min, double max) {
        double result;
        do{
            result = readDouble(msg);
        }
        while(result < min || result > max);
        return result;
    }

    @Override
    public double readDouble(String msg) {
        while(true){
            try{
                return Double.parseDouble(this.readString(msg));
            }
            catch(NumberFormatException e){
                this.print("Invalid input");
            }
        }    
    }

    @Override
    public float readFloat(String msg, float min, float max) {
        float result;
        do{
            result = readFloat(msg);
        }
        while(result < min || result > max);
        return result;    
    }

    @Override
    public float readFloat(String msg) {
        while(true){
            try{
                return Float.parseFloat(this.readString(msg));
            }
            catch(NumberFormatException e){
                this.print("Invalid input");
            }
        }    
    }

    @Override
    public int readInt(String msg, int min, int max) {
        int result;
        do{
            result = readInt(msg);
        }
        while(result < min || result > max);
        return result;    
    }

    @Override
    public int readInt(String msg) {
        while(true){
            try{
                return Integer.parseInt(this.readString(msg));
            }
            catch(NumberFormatException e){
                this.print("Invalid input");
            }
        }    
    }
    
    @Override
    public long readLong(String msg, long min, long max) {
        long result;
        do{
            result = readLong(msg);
        }
        while(result < min || result > max);
        return result;    
    }

    @Override
    public long readLong(String msg) {
        while(true){
            try{
                return Long.parseLong(this.readString(msg));
            }
            catch(NumberFormatException e){
                this.print("Invalid input");
            }
        }    
    }

    @Override
    public String readString(String msg) {
        this.print(msg);
        return console.nextLine();
    }

    @Override
    public BigDecimal readBd(String msg) {
        boolean isValid = true;
        BigDecimal a = null;
        while(isValid){
            try{
                a = new BigDecimal(this.readString(msg));
                return a;
            }
            catch(NumberFormatException e){
                
                this.print("Enter a numerical amount");
            }
        }     
        return a;
    }
 
}

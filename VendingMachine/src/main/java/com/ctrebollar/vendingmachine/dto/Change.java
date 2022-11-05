/**
 * @Date 10/19/2022
 * @author ctreb
 * The purpose of this class is to take the amount the user entered
 * subtract it from the item cost
 * return the change in coins
 * This class uses my CoinValues enum to determine coin values
 */
package com.ctrebollar.vendingmachine.dto;

import static com.ctrebollar.vendingmachine.dto.CoinValues.DIME;
import static com.ctrebollar.vendingmachine.dto.CoinValues.NICKEL;
import static com.ctrebollar.vendingmachine.dto.CoinValues.PENNY;
import static com.ctrebollar.vendingmachine.dto.CoinValues.QUARTER;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class Change {
       
    
    
    public static BigDecimal calc(BigDecimal entered, BigDecimal realCost){
        
        //use enum values
        BigDecimal quart = new BigDecimal(QUARTER.enumToString());
        BigDecimal dim = new BigDecimal(DIME.enumToString());
        BigDecimal nick = new BigDecimal(NICKEL.enumToString());
        BigDecimal pen = new BigDecimal(PENNY.enumToString());
        //the purpose of this is to simplify the math
        BigDecimal hund = new BigDecimal("100");
        
        BigDecimal diff = entered.subtract(realCost);//difference
        System.out.println("You need $" + diff.toString() + " back");
        //since I mult. by 100 we don't need to worry about decimals while we calculate
        BigDecimal diffHun = diff.multiply(hund).setScale(0); 
       
        BigDecimal finalResult = diffHun; // what I ultimately return
            //will run until the change is zero and there is nothing more to give back
            while(diffHun.compareTo(BigDecimal.ZERO) > 0){
                
                BigDecimal quarterCount = diffHun.divide(quart, RoundingMode.DOWN);//divide by 25
                quarterCount = quarterCount.setScale(0);
                diffHun = diffHun.subtract(quart.multiply(quarterCount));
                
                System.out.println("QUARTERS: " + quarterCount);
               
                
            
                BigDecimal dimeCount = diffHun.divide(dim, RoundingMode.DOWN); // divide by 10
                dimeCount = dimeCount.setScale(0);
                diffHun = diffHun.subtract(dim.multiply(dimeCount));
                
                System.out.println("DIMES: " + dimeCount);
            
               
                    BigDecimal nickCount = diffHun.divide(nick, RoundingMode.DOWN); // divide by 5
                    nickCount = nickCount.setScale(0);
                    diffHun = diffHun.subtract(nick.multiply(nickCount));
                    
                    System.out.println("NICKELS: " + nickCount); 
             
                  
                        BigDecimal penCount = diffHun.divide(pen, RoundingMode.DOWN); // divide by 1
                        penCount = penCount.setScale(0);
                        diffHun = diffHun.subtract(pen.multiply(penCount));
                                              
                        System.out.println("PENNIES: " + penCount);
                
                    finalResult = diffHun;
        }
        
        finalResult = diffHun;//should be zero
        return finalResult;

    }
    
}
        
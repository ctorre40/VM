/**
 * @Date 10/19/2022
 * @author ctreb
 * 
 * Created to give my coin amounts a set  value
 */
package com.ctrebollar.vendingmachine.dto;



public enum CoinValues {
    QUARTER(25), DIME(10), NICKEL(5), PENNY(1);
    private int val;
    
    CoinValues(int val){
        this.val = val;
    }  

    public String enumToString(){
        switch(this){
            case QUARTER:
                return "25";
            case DIME:
                return "10";
            case NICKEL:
                return "5";
            case PENNY:
                return "1";
            //default:
                //throw some exception?
        }
        return "";
    }
}

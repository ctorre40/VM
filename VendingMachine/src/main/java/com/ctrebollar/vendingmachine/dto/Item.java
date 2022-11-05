/**
 * @date 10/19/2022
 * @author ctreb
 * The purpose of this is to create my items as objects
 * 
 */
package com.ctrebollar.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;


public class Item {

    @Override
    public String toString() {
        return "Item{" + "itemName=" + itemName + ", price=" + price + ", invCount=" + invCount + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.itemName);
        hash = 59 * hash + Objects.hashCode(this.price);
        hash = 59 * hash + this.invCount;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.invCount != other.invCount) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }
    private String itemName;
    private BigDecimal price; // bigdemical?
    private int invCount;
    
    
    public Item(String itemName){
        this.itemName = itemName;
    }


    public Item(String itemName, String price, int invCount){
        this.itemName = itemName;
        this.price = new BigDecimal(price);
        this.invCount = invCount;
    }

    public String getName(){
        return itemName;
    }
    public BigDecimal getPrice(){
        return price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }
    public int getCount(){
        return invCount;
    }
    public void setCount(int invCount){
        this.invCount = invCount;
    }
    
    
}

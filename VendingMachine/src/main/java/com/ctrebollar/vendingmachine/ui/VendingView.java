/**
 * @date 10/19/2022
 * @author ctreb
 * 
 * This class contains the methods that communicate with the user 
 */
package com.ctrebollar.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendingView {
    @Autowired
    private UserIO io;
    
    @Autowired
    public VendingView(UserIO io){
        this.io = io;
    }
    public void displayMenu(){
        io.print("*** Menu ***");
    }
    public String promptUserToPick(){
        return io.readString("\nPlease select one of the "
                + "options above by entering the letters next to the item \n*** ENTER \"EXIT\" TO EXIT ***");
    }   
    public void displayItemList(Map<String, BigDecimal> itemsAvail){ 
        //needs to show our filtered items
        itemsAvail.entrySet().forEach(entry ->
        System.out.println("Item: " + entry.getKey() + ": $" +entry.getValue()));
    } 
    public BigDecimal userMoney() {
        //BigDecimal will properly handle many decimals
        return io.readBd("Enter how much money you have");
    }
    public void displayInsufficientBanner(){
        io.print("You do not have enough cash.");
    }
    public void displayItemCost(BigDecimal cost){
        io.print("This item costs " + cost);
    }

    public void noItemAvailable(){
        io.print("Not available try a different option");
    }
 
    public void displaySnickersSelection(){
        io.print("You chose the Snickers");
    }

    public void displayMWSelection(){
        io.print("You chose the Milky Way");
    }    

    public void displaySFSelection(){
        io.print("You chose the Swedish Fish");
    }    
       
    public void displayPotatoSelection(){
        io.print("You chose the Potato Chips");
    }    
      
    public void displayPretzelSelections(){
        io.print("You chose the Pretzels");
    }    

    public void exitBanner(){
        io.print("Enjoy!");
    }
    public void unknownComm(){
        io.print("\nUnknown Command\n");
    }
    public void errorBanner(String errorMsg){
        io.print("\n--- Error ---");
        io.print(errorMsg);
    }
}

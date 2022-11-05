/**
 * @date 10/19/2022
 * @author ctreb
 * 
 * This class orchestrates the actions of the view and service layer
 * to execute my program 
 */
package com.ctrebollar.vendingmachine.controller;

import com.ctrebollar.vendingmachine.dao.VendingPersistenceException;
import com.ctrebollar.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ctrebollar.vendingmachine.service.InsufficientFundsException;
import com.ctrebollar.vendingmachine.service.NoItemInventoryException;
import com.ctrebollar.vendingmachine.service.VendingServiceLayer;
import com.ctrebollar.vendingmachine.ui.VendingView;

@Component
public class VendingController {
    
    private VendingView view;
    private VendingServiceLayer sLayer;

    @Autowired
    public VendingController(VendingServiceLayer sLayer, VendingView view) {
        this.view = view;
        this.sLayer = sLayer;
    }
    
    public void run() {
        //entry to app
        boolean keepGoing = true;
        String itemSelection = "";
        
        BigDecimal userMoney = null;
        view.displayMenu();
     try {
         //make sure it gets the file
        listItemsAvailable();
     }
     catch(VendingPersistenceException e){
         view.errorBanner("Could not load file");
     }
    //two ways to end session: enter exist or successfully purchase item
           while (keepGoing) {
           try {
               //Display the menu and get a selection
               itemSelection = view.promptUserToPick().toUpperCase();

               if (itemSelection.equals("EXIT")) {
                   keepGoing = false;
                   break;
               }

                userMoney = view.userMoney(); // GET THE MONEY
             // down b/c of inifite decimals
                userMoney = userMoney.setScale(2, RoundingMode.DOWN); 
                selection(itemSelection, userMoney);
                keepGoing = false; // dispense only one item at a time
                break;
               
           } catch (NoItemInventoryException | VendingPersistenceException e) {
               //if not in stock or doesn't exist
               view.noItemAvailable();
               
            }
            catch(InsufficientFundsException e){
                //Tell user how much money they need
                view.displayInsufficientBanner();
                if(itemSelection.equals("SNICKERS")){
                    BigDecimal userAmount = new BigDecimal("1.50");
                    view.displayItemCost(userAmount);
                } 
                if(itemSelection.equals("SWEDISH FISH")){
                    BigDecimal userAmount = new BigDecimal("3.00");
                    view.displayItemCost(userAmount);
                }  
                if(itemSelection.equals("MILKY WAY")){
                    BigDecimal userAmount = new BigDecimal("1.25");
                    view.displayItemCost(userAmount);
                }
                if(itemSelection.equals("POTATO CHIPS")){
                    BigDecimal userAmount = new BigDecimal("2.50");
                    view.displayItemCost(userAmount);
                }
                if(itemSelection.equals("PRETZELS")){
                    BigDecimal userAmount = new BigDecimal("2.25");
                    view.displayItemCost(userAmount);
                }              
             }
           }
           exit();          
}
               
  
    private void listItemsAvailable() throws VendingPersistenceException {
        //only shows what is in stock
        Map<String, BigDecimal> menuOptions = sLayer.getAvailable();
        view.displayItemList(menuOptions);
    }
    private void exit(){
        view.exitBanner();
    }
    private void selection(String itemName, BigDecimal userAmount) throws VendingPersistenceException, InsufficientFundsException, NoItemInventoryException {
       //let the user know what they picked
        if(itemName.equals("SNICKERS")){
            view.displaySnickersSelection();
        }        
        if(itemName.equals("SWEDISH FISH")){
            view.displaySFSelection();
        }
        if(itemName.equals("POTATO CHIPS")){
            view.displayPotatoSelection();
        }
        if(itemName.equals("PRETZELS")){
            view.displayPretzelSelections();
        }
         if(itemName.equals("MILKY WAY")){
            view.displayMWSelection();
        }
         //create item with name and amount, pass in the users amt
         Item selected = sLayer.getItem(itemName, userAmount);
         
         
        sLayer.returnChange(userAmount, selected);
    }  
    
}

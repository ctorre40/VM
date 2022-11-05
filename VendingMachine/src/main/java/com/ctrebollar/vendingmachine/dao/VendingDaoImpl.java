/**
 * @date 10/19/2022
 * @author ctreb
 * 
 * Contains the methods to retrieve my data and manipulate it
 */
package com.ctrebollar.vendingmachine.dao;

import com.ctrebollar.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class VendingDaoImpl implements VendingDao{
    private Map<String, Item> itemList = new HashMap<>();
    private final String VENDING_FILE;
    public static final String DELIMITER = "::";
    
    public VendingDaoImpl(){
        VENDING_FILE = "vending.txt";
    }
    public VendingDaoImpl(String vendingTextFile){
        //for testing purposes
        VENDING_FILE = vendingTextFile;
    }
    
    

    @Override
    public List<Item> getAllItems() throws VendingPersistenceException{
        loadItems();
        return new ArrayList(itemList.values()); 
    }

    @Override
    public Item getItem(String itemName) throws VendingPersistenceException{
        loadItems();
        return itemList.get(itemName);
    }

    @Override
    public void removeItem(String itemName) throws VendingPersistenceException {
        loadItems();
        int currentCount = itemList.get(itemName).getCount();
        //we only change the int value named invCount
        itemList.get(itemName).setCount(currentCount-1);
        writeItems();
    }

    @Override
    public int getInventoryCount(String itemName) throws VendingPersistenceException{
        loadItems();
        return itemList.get(itemName).getCount();
    }

    @Override
    public Map<String, BigDecimal> availItems() throws VendingPersistenceException{
        //only shows available items
        loadItems();
        Map<String, BigDecimal> itemListAvail 
       = itemList.entrySet()
       .stream()
       .filter(map -> map.getValue().getCount() > 0)
       .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue().getPrice()));

        return itemListAvail;
    }
    private Item unmarshallItem(String itemAsText){
        String[] itemTokens = itemAsText.split(DELIMITER);
        String itemName = itemTokens[0];
        String itemPrice = itemTokens[1];
        Item unmarshItem = new Item(itemName);
        unmarshItem.setPrice(new BigDecimal(itemPrice));
        unmarshItem.setCount(Integer.parseInt(itemTokens[2]));
        return unmarshItem;
    }
    private void loadItems()throws VendingPersistenceException {
        Scanner scan;
        try{
            scan = new Scanner(new BufferedReader(new FileReader(VENDING_FILE)));
    
        } catch (FileNotFoundException e) {
            throw new VendingPersistenceException("Could not load vending options", e);
        }
        String currentLine;
        Item currentItem;
        while(scan.hasNextLine()){
            currentLine = scan.nextLine();
            currentItem = unmarshallItem(currentLine);
            itemList.put(currentItem.getName(), currentItem);
        } scan.close();
    }
    private String marshItem(Item anItem){
        String itemAsText = anItem.getName() + DELIMITER;
        itemAsText+= anItem.getPrice() + DELIMITER;
        itemAsText+= anItem.getCount();
        return itemAsText;
    }
    private void writeItems() throws VendingPersistenceException {
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(VENDING_FILE));
        } catch (IOException e) {
            throw new VendingPersistenceException("Could not save", e);
        }
        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for(Item cItem : itemList){
            itemAsText = marshItem(cItem);
            out.println(itemAsText);
            out.flush();
        } out.close();
    }
    

  
}

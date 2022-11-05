/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.ctrebollar.vendingmachine.dao.VendingDao;
import com.ctrebollar.vendingmachine.dao.VendingDaoImpl;
import com.ctrebollar.vendingmachine.dto.Item;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *TEST
 * get all
 * get item
 * get inv count
 * remove 
 * avail items
 * 
 * @author ctreb
 */
public class VendingDaoImplTest {
    VendingDao testDao; // = new VendingDaoImpl("testVending.txt");

    
    @BeforeEach
    public void setUp() throws Exception{
        String testFile = "daoTestFile.txt";
        testDao = new VendingDaoImpl(testFile);
    }

    @Test
    public void testGetItem() throws Exception {
        this.setUp();
        Item item1 = testDao.getItem("SNICKERS");        
        assertEquals(item1.getName(), "SNICKERS", "Should be snickers");      
    }

    @Test
    public void testGetAll() throws Exception {
        this.setUp();
        List<Item> testItems = testDao.getAllItems();
        assertNotNull(testItems, "Should not be null");
        assertEquals(5, testItems.size(), "Should be 5");
    }    
    
    @Test
    public void testRemoveCount() throws Exception {
        this.setUp();
        Item item1 = testDao.getItem("SNICKERS"); 
        String itemName = item1.getName();
        int current = testDao.getInventoryCount(itemName); 
        testDao.removeItem(itemName);
        int newCount = testDao.getInventoryCount(itemName);//one less
        assertEquals(current, (newCount+1), "Should be same");         
    }
    
    @Test
    public void testGetStockAmount() throws Exception {
        this.setUp();
        String itemName = "MILKY WAY";
        int current = testDao.getInventoryCount(itemName); 
        assertEquals(0, current, "Should be 0");
    }  
    
    @Test
    public void testGetAvailable() throws Exception {
        this.setUp();
        Map<String, BigDecimal> availItems = testDao.availItems();
        assertFalse(availItems.containsKey("MILKY WAY"));
        assertEquals(4, availItems.size(), "Should be 4");
    } 

    
}

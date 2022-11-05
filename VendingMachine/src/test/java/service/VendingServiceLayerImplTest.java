/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.ctrebollar.vendingmachine.service.InsufficientFundsException;
import com.ctrebollar.vendingmachine.service.VendingServiceLayerImpl;
import com.ctrebollar.vendingmachine.service.VendingServiceLayer;
import com.ctrebollar.vendingmachine.dao.VendingAuditDao;
import com.ctrebollar.vendingmachine.dao.VendingAuditDaoImpl;
import com.ctrebollar.vendingmachine.dao.VendingDao;
import com.ctrebollar.vendingmachine.dao.VendingDaoImpl;
import com.ctrebollar.vendingmachine.dao.VendingPersistenceException;
import com.ctrebollar.vendingmachine.dto.Change;
import com.ctrebollar.vendingmachine.dto.Item;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ctreb
 * 
 * TEST THESE
 * get all
 * get item
 * get available
 * remove item count
 * is sufficient
 * return change
 */
public class VendingServiceLayerImplTest {
    private VendingServiceLayer service;
    

    
    public VendingServiceLayerImplTest() {
     
     VendingDao testDao = new VendingDaoImpl("serviceTestFile.txt");
     VendingAuditDao testAudit = new VendingAuditDaoImpl(); 
     service = new VendingServiceLayerImpl(testDao, testAudit);
     
    
     

    }

    @org.junit.jupiter.api.Test
    public void testGetAll() throws Exception {
        assertEquals(5, service.getAll().size(), "Should be 5");
    }
    
    @org.junit.jupiter.api.Test
    public void testGetItemValid() throws Exception{
        BigDecimal test = new BigDecimal("1.50");
        Item shouldBeSnickers = service.getItem("SNICKERS", test);
        assertNotNull(shouldBeSnickers, "Getting null but shouldn't");
        assertEquals("SNICKERS", shouldBeSnickers.getName(), "Should be snickers");
    }
     
    
    @org.junit.jupiter.api.Test
    public void removeItemCount() throws Exception {
        BigDecimal test = new BigDecimal("1.50");        
        Item item1 = service.getItem("SNICKERS", test);
        int currentCount = item1.getCount();
        service.removeItemCount("SNICKERS");
        int newCount = item1.getCount();
        assertEquals(currentCount, (newCount+1), "Should be same");      
    }    
    @org.junit.jupiter.api.Test
    public void testIsSufficientPass() throws Exception{

        BigDecimal a = new BigDecimal("1.51"); 
        BigDecimal b = new BigDecimal("1.50"); // snickers is 1.50
        Item itemA = service.getItem("SNICKERS", a);
        service.isSufficient(a, itemA);
        try{
            service.isSufficient(a, itemA);
        }
        catch(InsufficientFundsException e){
            fail("Should not have failed");
        }
        
    }
    
    @org.junit.jupiter.api.Test
    public void testIsSufficientFail() throws Exception {

        BigDecimal a = new BigDecimal("1.49"); 
        BigDecimal b = new BigDecimal("1.50"); // snickers is 1.50
        Item itemA = service.getItem("SNICKERS", b);
        if(a.compareTo(b)==-1){
            try{
                service.isSufficient(a, itemA);
                fail("Should fail");
            }
            catch(InsufficientFundsException e){
                assertTrue(true);
            }
        }
    }
    
    @org.junit.jupiter.api.Test
    public void testReturnChange() throws Exception {

        int n = 0;//money to give back
        BigDecimal a = new BigDecimal("1.50"); 
        Item itemA = service.getItem("SNICKERS", a);
        BigDecimal b = new BigDecimal("1.50");
        BigDecimal returned = service.returnChange(b, itemA);
        assertEquals(returned.toString(), Integer.toString(n), "Nothing to give back");
               
    }    
    
    
    
}

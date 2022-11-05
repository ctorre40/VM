/**
 * @date 10/19/2022
 * @author ctreb
 * 
 * Implementation file for my service layer interface 
 */
package com.ctrebollar.vendingmachine.service;

import com.ctrebollar.vendingmachine.dao.VendingAuditDao;
import com.ctrebollar.vendingmachine.dao.VendingDao;
import com.ctrebollar.vendingmachine.dao.VendingPersistenceException;
import com.ctrebollar.vendingmachine.dto.Change;
import com.ctrebollar.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class VendingServiceLayerImpl implements VendingServiceLayer{
    VendingDao vDao;
    private VendingAuditDao auditDao;

    @Autowired
    public VendingServiceLayerImpl(VendingDao vDao, VendingAuditDao auditDao) {
        this.vDao = vDao;
        this.auditDao = auditDao;
    }
    
    @Override
    public List<Item> getAll() throws VendingPersistenceException {
        List<Item> myList = vDao.getAllItems();
        return myList;
    }

    @Override
    public Item getItem(String itemName, BigDecimal money) throws VendingPersistenceException, InsufficientFundsException, NoItemInventoryException{
        Item selected = vDao.getItem(itemName);
        if(selected == null){
            throw new VendingPersistenceException("This item does not exist");
        }
        if(selected.getCount() <= 0){
            throw new NoItemInventoryException("The is not available");
        }
        else{
            isSufficient(money, selected);//if no exception thrown proceed
            removeItemCount(itemName);
            return selected;       
        }
    }

    @Override
    public Map<String, BigDecimal> getAvailable() throws VendingPersistenceException {
        Map<String, BigDecimal> iList = vDao.availItems();
        return iList;
    }

    @Override
    public void removeItemCount(String itemName) throws VendingPersistenceException, NoItemInventoryException {
        int check = vDao.getInventoryCount(itemName);
       // LocalDateTime myTime = LocalDateTime.now();
        if(check > 0){
            vDao.removeItem(itemName);
            auditDao.writeAuditEntry("Item: " + itemName + " removed");
        }
        else{
            throw new NoItemInventoryException("This item is not available!");
        }
    }

    @Override
    public void isSufficient(BigDecimal userEnter, Item itemName) throws InsufficientFundsException {
        // checks to see if the user amount is enough to proceed
        //throws exception if it is not
        BigDecimal userEnteredCheck = userEnter.setScale(2, RoundingMode.HALF_UP);
        if(userEnteredCheck.compareTo(itemName.getPrice()) == -1){
            throw new InsufficientFundsException("You do not have enough money. $" + userEnter + " is not enough.");
        }
    }

    @Override
    public BigDecimal returnChange(BigDecimal change, Item itemName) {
        //calls on the Change class to calculate how much money should be returned
        BigDecimal itemPrice = itemName.getPrice();
        BigDecimal returnToUser = Change.calc(change, itemPrice); // calc in change class
        return returnToUser; 
    }

    
}

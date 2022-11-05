/**
 * @date 10/19/2022
 * @author ctreb
 * 
 * Purpose: The purpose of this project is to simulate a vending machine.
 * The Vending machine is set to handle many exceptions and can return change to the user
 * 
 */
package com.ctrebollar.vendingmachine;

import com.ctrebollar.vendingmachine.controller.VendingController;
import com.ctrebollar.vendingmachine.dao.VendingAuditDao;
import com.ctrebollar.vendingmachine.dao.VendingAuditDaoImpl;
import com.ctrebollar.vendingmachine.dao.VendingDao;
import com.ctrebollar.vendingmachine.dao.VendingDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.ctrebollar.vendingmachine.service.VendingServiceLayer;
import com.ctrebollar.vendingmachine.service.VendingServiceLayerImpl;
import com.ctrebollar.vendingmachine.ui.UserIO;
import com.ctrebollar.vendingmachine.ui.UserIOConsoleImpl;
import com.ctrebollar.vendingmachine.ui.VendingView;


public class App {
    public static void main(String[] args) {
        /*
        UserIO myIO = new UserIOConsoleImpl();
        VendingView myView = new VendingView(myIO);
        VendingDao myDao = new VendingDaoImpl(); // DI/ploym
        VendingAuditDao myAuditDao = new VendingAuditDaoImpl(); // DI/ploym
        VendingServiceLayer myLayer = new VendingServiceLayerImpl(myDao, myAuditDao); // DI
        VendingController con = new VendingController(myLayer, myView); 
        con.run();
        */
        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingController controller = 
           ctx.getBean("controller", VendingController.class);
        controller.run();
      
        

    }
    
}

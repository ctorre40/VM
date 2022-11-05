/**
 * @date 10/19/2022
 * @author ctreb
 * 
 * Implements the methods to write to my audit file 
 */
package com.ctrebollar.vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;


public class VendingAuditDaoImpl implements VendingAuditDao {
    public static final String AUDIT_FILE = "audit.txt";

    @Override
    public void writeAuditEntry(String entry) throws VendingPersistenceException {
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        }
        catch(IOException e){
            throw new VendingPersistenceException("Could not write to audit", e);
        }
        LocalDateTime timeStamp = LocalDateTime.now();
        out.println(timeStamp.toString() + " : " + entry);
        out.flush();
    }
    
}

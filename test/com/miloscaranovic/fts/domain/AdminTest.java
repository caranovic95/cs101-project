/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miloscaranovic.fts.domain;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Milos PC
 */
public class AdminTest {
    
    public AdminTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getAdminID method, of class Admin.
     */
    @Test
    public void testGetAdminID() {
        System.out.println("getAdminID");
        Admin instance = new Admin();
        int expResult = 0;
        int result = instance.getAdminID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    //    fail("The test case is a prototype.");
    }

    /**
     * Test of setAdminID method, of class Admin.
     */
    @Test
    public void testSetAdminID() {
        System.out.println("setAdminID");
        int adminID = 0;
        Admin instance = new Admin();
        instance.setAdminID(adminID);
        // TODO review the generated test code and remove the default call to fail.
     //   fail("The test case is a prototype.");
    }

    /**
     * Test of getUsername method, of class Admin.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        Admin instance = new Admin();
        String expResult = null;
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
     //   fail("The test case is a prototype.");
    }

    /**
     * Test of setUsername method, of class Admin.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        Admin instance = new Admin();
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
    //    fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class Admin.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Admin instance = new Admin();
        String expResult = null;
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class Admin.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        Admin instance = new Admin();
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
    //    fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Admin.
     */


    
}

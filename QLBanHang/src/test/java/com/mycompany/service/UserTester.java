/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author CasMap
 */
public class UserTester {
    private static Connection CONN;
    
   @BeforeAll
   public static void beforeAll() {
        try {
            CONN = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @AfterAll
   public static void afterAll() {
       if (CONN != null)
           try {
               CONN.close();
       } catch (SQLException ex) {
           Logger.getLogger(SanPhamTester.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   @Test
   public void usernameNull() {
        try {
            UserService us = new UserService(CONN);
            Assertions.assertFalse(us.account("", "123456"));
        } catch (SQLException ex) {
            Logger.getLogger(UserTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
   public void passwordNull() {
        try {
            UserService us = new UserService(CONN);
            Assertions.assertFalse(us.account("minh01", ""));
        } catch (SQLException ex) {
            Logger.getLogger(UserTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }    
   
   @Test
   public void userAndPassNull() {
        try {
            UserService us = new UserService(CONN);
           
            Assertions.assertFalse(us.account("", ""));
        } catch (SQLException ex) {
            Logger.getLogger(UserTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
   public void userTonTai() {
        try {
            UserService us = new UserService(CONN);
            Assertions.assertTrue(us.account("minh01", "123456"));
        } catch (SQLException ex) {
            Logger.getLogger(UserTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
    public void testTimeout() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            new UserService(CONN).account("minh01", "123456");
        });
    }
}

package com.mycompany.service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mycompany.pojo.LoaiSP;
import java.sql.Connection;
import java.sql.SQLException;
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
public class LoaiSPTester {
    private static Connection CONN;
    
    @BeforeAll
    public static void beforeAll() {
        try {
            CONN = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSPTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterAll
    public static void tearDownClass() {
        if (CONN != null)
            try {
                CONN.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoaiSPTester.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Test
     public void testQuantity() {
        try {
            int expected = 3;
            LoaiSPService s = new LoaiSPService(CONN);
            List<LoaiSP> cates = s.getCates();
            
            System.out.println(cates);
            Assertions.assertTrue(cates.size() >= expected);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSPTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

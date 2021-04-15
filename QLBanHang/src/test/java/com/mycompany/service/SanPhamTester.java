/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.SanPham;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
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
public class SanPhamTester {
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
   public void themSanPham() throws IOException {
        try {
            SanPham s = new SanPham();
            s.setTenSP("Adidas Holographic");
            s.setSoLuong(50);
            s.setDonGiaNhap(new BigDecimal(1000000));
            s.setDonGiaBan(new BigDecimal(1800000));
//            s.setAnh("D:/Images/nike/blue.jpg");
            s.setAnh(Files.readAllBytes(new File("D:/Images/adidas/holographic.jpg").toPath()));
            s.setLoaiSP_id(2);
            SanPhamService sv = new SanPhamService(CONN);
            Assertions.assertTrue(sv.themSanPham(s));
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
   public void xoaSanPham() {
        try {
            SanPhamService sv = new SanPhamService(CONN);
            Assertions.assertTrue(sv.xoaSanPham(5));
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}

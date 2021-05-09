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
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
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
   public void testWithKeyWord(){
        try {
            SanPhamService sv = new SanPhamService(CONN);
            List<SanPham> sanPham = sv.getSanPhams("nike");
            sanPham.forEach(s -> {
                Assertions.assertTrue(s.getTenSP().toLowerCase().contains("nike"));
            });
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
   public void testUnknownWithKeyWord(){
        try {
            SanPhamService sv = new SanPhamService(CONN);
            List<SanPham> sanPham = sv.getSanPhams("sgg");
            
            Assertions.assertEquals(0, sanPham.size());
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
    public void testException() {
        Assertions.assertThrows(SQLDataException.class, () -> {
            new SanPhamService(CONN).getSanPhams(null);
        });
    }
   
    @Test
    public void testTimeout() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            new SanPhamService(CONN).getSanPhams("");
        });
    }
    
//   @Test
//   public void themSanPham() throws IOException {
//        try {
//            SanPham s = new SanPham();
//            s.setTenSP("Adidas Holographic");
//            s.setSoLuong(50);
//            s.setDonGiaNhap(new BigDecimal(1000000));
//            s.setDonGiaBan(new BigDecimal(1800000));
//            s.setAnh(Files.readAllBytes(new File("D:/Images/adidas/holographic.jpg").toPath()));
//            s.setLoaiSP_id(2);
//            SanPhamService sv = new SanPhamService(CONN);
//            Assertions.assertTrue(sv.themSanPham(s));
//        } catch (SQLException ex) {
//            Logger.getLogger(SanPhamTester.class.getName()).log(Level.SEVERE, null, ex);
//        }
//   }
//   
//    @Test
//   public void capNhatSanPham() throws IOException {
//        try {
//            SanPham s = new SanPham();
//            s.setTenSP("Adidas Holographic");
//            s.setSoLuong(30);
//            s.setDonGiaNhap(new BigDecimal(1000000));
//            s.setDonGiaBan(new BigDecimal(1800000));
//            s.setAnh(Files.readAllBytes(new File("D:/Images/adidas/holographic.jpg").toPath()));
//            s.setLoaiSP_id(2);
//            s.setIdSP(16);
//            SanPhamService sv = new SanPhamService(CONN);
//            Assertions.assertTrue(sv.capNhatSanPham(s));
//        } catch (SQLException ex) {
//            Logger.getLogger(SanPhamTester.class.getName()).log(Level.SEVERE, null, ex);
//        }
//   }
    
//   @Test
//   public void xoaSanPham() {
//        try {
//            SanPhamService sv = new SanPhamService(CONN);
//            Assertions.assertTrue(sv.xoaSanPham(5));
//        } catch (SQLException ex) {
//            Logger.getLogger(SanPhamTester.class.getName()).log(Level.SEVERE, null, ex);
//        }
//   }
    
    @Test
    public void idTonTai() {
        try {
            SanPhamService sv = new SanPhamService(CONN);
            Assertions.assertTrue(sv.kiemTraIdSpTonTai(1));
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void idKhongTonTai() {
        try {
            SanPhamService sv = new SanPhamService(CONN);
            Assertions.assertFalse(sv.kiemTraIdSpTonTai(0));
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

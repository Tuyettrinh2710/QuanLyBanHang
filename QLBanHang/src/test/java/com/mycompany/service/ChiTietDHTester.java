/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;


import com.mycompany.pojo.ChiTietHD;
import java.math.BigDecimal;
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
public class ChiTietDHTester {
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
            ChiTietHDService cs = new ChiTietHDService(CONN);
            List<ChiTietHD> c = cs.getHoaDon();
            
            System.out.println(c);
            Assertions.assertTrue(c.size() >= expected);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSPTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testTimeout() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            new ChiTietHDService(CONN).getHoaDon();
        });
    }
    
    @Test
    public void them() {
        try {
            ChiTietHD c = new ChiTietHD();
            c.setMaHD(5);
            c.setMaSp(14);
            c.setTenSP("Adidas Superstar");
            c.setSl(1);
            c.setDonGia(new BigDecimal(1800000));
            c.setGiamGia(0.f);
            c.setThanhTien(new BigDecimal(1800000));
            ChiTietHDService cs = new ChiTietHDService(CONN);
            Assertions.assertTrue(cs.themChiTietDH(c));
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietDHTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void themIdHDKhongHopLe() {
        try {
            ChiTietHD c = new ChiTietHD();
            c.setMaHD(0);
            c.setMaSp(12);
            c.setSl(1);
            c.setDonGia(new BigDecimal(7500000));
            c.setGiamGia(0.f);
            c.setThanhTien(new BigDecimal(7500000));
            ChiTietHDService cs = new ChiTietHDService(CONN);
            Assertions.assertFalse(cs.themChiTietDH(c));
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietDHTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void themIdSPKhongHopLe() {
        try {
            ChiTietHD c = new ChiTietHD();
            c.setMaHD(3);
            c.setMaSp(0);
            c.setSl(1);
            c.setDonGia(new BigDecimal(7500000));
            c.setGiamGia(0.f);
            c.setThanhTien(new BigDecimal(7500000));
            ChiTietHDService cs = new ChiTietHDService(CONN);
            Assertions.assertFalse(cs.themChiTietDH(c));
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietDHTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.DonHang;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
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
public class DonHangTester {
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
    public void them() {
        try {
            DonHang d = new DonHang();
            d.setKh_id("KH01");
            d.setSp_id(1);
            d.setDonGia(new BigDecimal(7500000));
            d.setTinhTrang("Đã giao");
            DonHangService ds = new DonHangService(CONN);
            Assertions.assertTrue(ds.themDH(d));
        } catch (SQLException ex) {
            Logger.getLogger(DonHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void themSaiMaKH() {
        try {
            DonHang d = new DonHang();
            d.setKh_id("KH010");
            d.setSp_id(22);
            d.setDonGia(new BigDecimal(7500000));
            d.setTinhTrang("Đã giao");
            DonHangService ds = new DonHangService(CONN);
            Assertions.assertFalse(ds.themDH(d));
        } catch (SQLException ex) {
            Logger.getLogger(DonHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void themSPKhongTonTai() {
        try {
            DonHang d = new DonHang();
            d.setKh_id("KH01");
            d.setSp_id(220);
            d.setDonGia(new BigDecimal(7500000));
            d.setTinhTrang("Đã giao");
            DonHangService ds = new DonHangService(CONN);
            Assertions.assertFalse(ds.themDH(d));
        } catch (SQLException ex) {
            Logger.getLogger(DonHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
